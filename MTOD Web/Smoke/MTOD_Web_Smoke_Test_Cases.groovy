def jobsList = [
[
  jobTitle: ' - Create Account - MWBU-15955', 
  COMPONENT:'smoke', 
  TESTCASE: 'createAccount'],
[
  jobTitle: ' - Login User From Backend - MWBU-16476', 
  COMPONENT:'smoke', 
  TESTCASE: 'loginUserFromBackend'],
[
  jobTitle: ' - Page Load - MWBU-15953', 
  COMPONENT:'smoke', 
  TESTCASE: 'pageLoad'],
[
  jobTitle: ' - Play Video - MWBU-15954', 
  COMPONENT:'smoke', 
  TESTCASE: 'playVideo'],
[
  jobTitle: ' - Sign In - MWBU-15956', 
  COMPONENT:'smoke', 
  TESTCASE: 'signIn'],
[
  jobTitle: ' - Sitemap XML - MWBU-16268', 
  COMPONENT:'smoke', 
  TESTCASE: 'sitemapXml']
]

def environments = [
[
  envName: 'Dev', 
  ENV: 'dev'], 
[
  envName: 'Preprod', 
  ENV: 'preprod'], 
[
  envName: 'Prod', 
  ENV: 'www'], 
[
  envName: 'Staging', 
  ENV: 'staging']
];

def path = 'QA-Selenium/MTOD/Smoke Tests/';
def tool = "'''";

jobsList.each{ currJob ->
  environments.each{ currEnv -> 
    pipelineJob("${path}" + "${currEnv["envName"]}${currJob["jobTitle"]}") {
      definition {
        cps {
          script('''
  pipeline {
    agent {
      kubernetes {
        inheritFrom 'qa-automation-npm'
        label 'npm'
        yaml ''' + "${tool}" + '''
        spec:
          containers:
            - name: npm
              image: 363308097987.dkr.ecr.us-west-2.amazonaws.com/jenkins-slave:node-05-15-2022
              command:
              - cat
              tty: true
          dnsPolicy: None
          dnsConfig:
              nameservers:
                - 169.254.20.10
                - 172.20.0.10
              searches:
                - selenium.svc.cluster.local
                - svc.cluster.local
                - cluster.local
                - ec2.internal
                - us-west-2.compute.internal
              options:
                - name: ndots
                  value: "1"
                - name: attempts
                  value: "3"
                - name: timeout
                  value: "1"
                - name: rotate'''
              + "${tool}" + '''
      }
    }
    parameters {
      choice(choices: [''' + "'${currEnv["ENV"]}'" + ''', 'staging', 'www', 'preprod', 'dev'], description: '', name: 'ENV')
      string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)
      string(defaultValue: ''' + "'${currJob["COMPONENT"]}'" + ''', description: '', name: 'COMPONENT', trim: false)
      string(defaultValue: ''' + "'${currJob["TESTCASE"]}'" + ''', description: '', name: 'TESTCASE', trim: false)
      string(defaultValue: '3', description: '', name: 'SLEEP', trim: false)
      string(defaultValue: '60', description: '', name: 'CHECKOUT_TIMEOUT', trim: false)
      string(defaultValue: '130', description: '', name: 'BUILD_TIMEOUT', trim: false)
      string(defaultValue: '360', description: '', name: 'TEST_TIMEOUT', trim: false)
      choice(choices: ['', '--loglevel warn', '--loglevel verbose'], 
      description: ' Select a log level, or leave empty if log is not needed', name: 'LOGLEVEL')
      password(name: 'PASSWORD', description: 'Email pass')
      password(name: 'BREACHED_PASSWORD', description: 'BREACHED Pass')
      password(name: 'ORG_PASSWORD', description: 'Org pass')
    }
    stages {
      stage('What Node') {
        steps {
            container('npm') {
              echo "we are running"
              sh "sleep 10; node -v"
            }
        }
      }
      stage('Checkout') {
          steps {
              script {
              try {
                  timeout(time: "${CHECKOUT_TIMEOUT}" as Integer, unit: 'SECONDS') {
                      git branch: '${BRANCH}',
                          credentialsId: 'github',
                          url: 'git@github.com:motortrend/motortrend-on-demand-web-automation.git'
                  }
              } catch (err) {
                      retry(3) {
                          echo(err)
                          sleep(time:"${SLEEP}" as Integer,unit:"SECONDS")
                          timeout(time: "${CHECKOUT_TIMEOUT}" as Integer, unit: 'SECONDS') {
                          git branch: '${BRANCH}',
                              credentialsId: 'github',
                              url: 'git@github.com:motortrend/motortrend-on-demand-web-automation.git'
                          }
                      }
                  }
              }
          }
      }
      stage('Build'){
          steps {
              script {
                  container ('qa-automation-npm'){
                      environment { 
                          PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true
                      }
                      try {
                          timeout(time: "${BUILD_TIMEOUT}" as Integer, unit: 'SECONDS') {
                              sh 'npm install --omit=dev ${LOGLEVEL}'
                          }
                      } catch (err) {
                          echo "Build did not complete in 130 seconds, let's retry"
                          retry(3) {
                              sleep(time:"${SLEEP}" as Integer,unit:"SECONDS")
                              timeout(time: "${BUILD_TIMEOUT}" as Integer, unit: 'SECONDS') {
                                  sh 'npm install --omit=dev ${LOGLEVEL}'
                              }
                          }
                      }
                      
                      stage('Regression Test') {
                        retry(3) {
                          try {
                              sleep(time:"${SLEEP}" as Integer,unit:"SECONDS")
                              timeout(time: "${TEST_TIMEOUT}" as Integer, unit: 'SECONDS') {
                                  withCredentials([string(credentialsId: 'MTODPW', variable: 'PASSWORD'), string(credentialsId: 'OrgPw', variable: 'ORG_PASSWORD'), string(credentialsId: 'BreachedPW', variable: 'BREACHED_PASSWORD')]) {
                                  sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} ENV=${ENV} PASSWORD=${PASSWORD} BREACHED_PASSWORD=${BREACHED_PASSWORD} ORG_PASSWORD=${ORG_PASSWORD} npm run devtools-kubernetes'
                                  }
                              }
                          } catch (err) {
                              error "Test did not complete in ${TEST_TIMEOUT} seconds"
                          }
                        }
                      }
                  }
              }
          }
      }
    }
}
                              '''
          )
        }
      }
    }
  }
}

// Array holding copies of the above string, the final set jobs will be generated using these
def setJobsStagesStrings = []

environments.eachWithIndex{ currEnv, currEnvIndex ->
  setJobsStagesStrings.add([
    jobName: "${path}0 - ${currEnv["envName"]} Set Job",
    jobStages: "",
    jobEnv: "${currEnv["ENV"]}"
  ])
  jobsList.eachWithIndex{ currJob, currJobIndex -> 
    setJobsStagesStrings[currEnvIndex]["jobStages"] = setJobsStagesStrings[currEnvIndex]["jobStages"] + '''
    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
        stage(''' + "'${currEnv["envName"]}${currJob["jobTitle"]}'" + ''') {
            build job: ''' + "'${currEnv["envName"]}${currJob["jobTitle"]}'" + ''', parameters: [string(name: 'ENV', value: String.valueOf(ENV)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
      }
    }
  '''
  }
}
 
// Now will create the set jobs
def triggerString = ""

setJobsStagesStrings.each {stagesString ->
  triggerString = triggerString + "${stagesString["jobName"]},"
  pipelineJob("${stagesString["jobName"]}") {
    triggers {
      cron '''H 06 * * 0-5'''
    }
    parameters {
      stringParam("ENV", "${stagesString["jobEnv"]}", "Environment being used for testing")
      stringParam('BRANCH', 'main', '')
    }
    definition {
      cps {
        script("${stagesString["jobStages"]}")
      }
    }
  }
}
