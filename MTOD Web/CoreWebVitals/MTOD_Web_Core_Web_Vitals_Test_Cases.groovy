def jobsList = [
[
  jobTitle: 'Performance - Homepage - CLS - MWBU-16464', 
  PAGESLUG: '/', 
  METRIC: 'CLS'],
[
  jobTitle: 'Performance - Homepage - FCP - MWBU-16464', 
  PAGESLUG: '/', 
  METRIC: 'FCP'],
[
  jobTitle: 'Performance - Homepage - LCP - MWBU-16464', 
  PAGESLUG: '/', 
  METRIC: 'LCP'],
[
  jobTitle: 'Performance - Homepage - SI - MWBU-16464',
  PAGESLUG: '/', 
  METRIC: 'SI'],
[
  jobTitle: 'Performance - Homepage - TBT - MWBU-16464', 
  PAGESLUG: '/', 
  METRIC: 'TBT'],
[
  jobTitle: 'Performance - Homepage - TTI - MWBU-16464', 
  PAGESLUG: '/', 
  METRIC: 'TTI'],
[
  jobTitle: 'Performance - Originals - CLS - MWBU-16464',
  PAGESLUG:  '/channel/originals/4/', 
  METRIC: 'CLS'],
[
  jobTitle: 'Performance - Originals - FCP - MWBU-16464',
  PAGESLUG:  '/channel/originals/4/', 
  METRIC: 'FCP'],
[
  jobTitle: 'Performance - Originals - LCP - MWBU-16464',
  PAGESLUG:  '/channel/originals/4/', 
  METRIC: 'LCP'],
[
  jobTitle: 'Performance - Originals - SI - MWBU-16464', 
  PAGESLUG: '/channel/originals/4/', 
  METRIC: 'SI'],
[
  jobTitle: 'Performance - Originals - TBT - MWBU-16464',
  PAGESLUG:  '/channel/originals/4/', 
  METRIC: 'TBT'],
[
  jobTitle: 'Performance - Originals - TTI - MWBU-16464',
  PAGESLUG:  '/channel/originals/4/', 
  METRIC: 'TTI'],
[
  jobTitle: 'Performance - Roadkill - CLS - MWBU-16464', 
  PAGESLUG: '/show/roadkill/305/', 
  METRIC: 'CLS'],
[
  jobTitle: 'Performance - Roadkill - FCP - MWBU-16464', 
  PAGESLUG: '/show/roadkill/305/', 
  METRIC: 'FCP'],
[
  jobTitle: 'Performance - Roadkill - LCP - MWBU-16464', 
  PAGESLUG: '/show/roadkill/305/', 
  METRIC: 'LCP'],
[
  jobTitle: 'Performance - Roadkill - SI - MWBU-16464',
  PAGESLUG: '/show/roadkill/305/', 
  METRIC: 'SI'],
[
  jobTitle: 'Performance - Roadkill - TBT - MWBU-16464', 
  PAGESLUG: '/show/roadkill/305/', 
  METRIC: 'TBT'],
[
  jobTitle: 'Performance - Roadkill - TTI - MWBU-16464', 
  PAGESLUG: '/show/roadkill/305/', 
  METRIC: 'TTI']
]

def path = 'QA-Selenium/MTOD/Core Web Vitals/';
def environments = [
[
  envName: 'Staging/', 
  ENV: 'staging'],
[
  envName: 'Preprod/', 
  ENV: 'preprod'],
[
  envName: 'Prod/', 
  ENV: 'www']  
]
def platforms = [
[
  platformName: 'Mobile/', 
  PLATFORM: 'mobile'],
[
  platformName: 'Desktop/', 
  PLATFORM: 'desktop']
]
def tool = "'''";

jobsList.each{ currJob ->
  environments.each{ currEnv ->
    platforms.each{ currPlatform ->
      pipelineJob("${path}${currEnv["envName"]}${currPlatform["platformName"]}" + "${currJob["jobTitle"]}") {
        parameters {
          choiceParam('ENV', ["${currEnv["ENV"]}", 'www', 'preprod', 'staging', 'dev'], '')
          stringParam('BRANCH', 'main', '')
          stringParam('COMPONENT', 'performance', '')
          stringParam('TESTCASE', 'performanceCWV', '')
          stringParam('PAGESLUG', "${currJob["PAGESLUG"]}", '')
          stringParam('METRIC', "${currJob["METRIC"]}", '')
          stringParam('PLATFORM', "${currPlatform["PLATFORM"]}", '')
          stringParam('SLEEP', '3', '')
          stringParam('CHECKOUT_TIMEOUT', '60', '')
          stringParam('BUILD_TIMEOUT', '130', '')
          stringParam('TEST_TIMEOUT', '360', '')
          choiceParam('LOGLEVEL', ['', '--loglevel warn', '--loglevel verbose'], ' Select a log level, or leave empty if log is not needed')
        }
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
}

// The following are to generate set jobs dynamically based in number of jobTitles
// Number of expected set jobs
def numberOfSetJobs = 6
def maxStagesPerJob = Math.round(Math.ceil(jobsList.size/numberOfSetJobs))
// String used to dynamically build the job stages (i.e. jobTitles included in the set)
def setJobStages = ""
// Array holding copies of the above string, the final set jobs will be generated using these
def setJobsStagesStrings = []
def timeCounter = 16

environments.each{ currEnv ->
  timeCounter = timeCounter + 1
  platforms.each{ currPlatform ->
    jobsList.eachWithIndex{ currJob, currJobIndex -> 
      def int currentJobSet = Math.round(Math.floor(currJobIndex/maxStagesPerJob))
      if (setJobsStagesStrings.size < currentJobSet + 1) {
        setJobsStagesStrings.add([
          jobName: "${path}${currEnv["envName"]}${currPlatform["platformName"]}${currentJobSet + 1} - Set Job ${currentJobSet + 1}",
          jobStages: "",
        ])
      }

      setJobsStagesStrings[currentJobSet]["jobStages"] = setJobsStagesStrings[currentJobSet]["jobStages"] + '''
      catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
          stage(''' + "'${currJob["jobTitle"]}'" + ''') {
              build job: ''' + "'${currJob["jobTitle"]}'" + ''', parameters: [string(name: 'ENV', value: String.valueOf(ENV)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
        }
      }
    '''
    }

    // Now will create the set jobs
    def triggerString = ""

    setJobsStagesStrings.each {stagesString ->
      triggerString = triggerString + "${stagesString["jobName"]},"
      pipelineJob("${stagesString["jobName"]}") {
        parameters {
          stringParam("ENV", "${currEnv["ENV"]}", "Environment being used for testing")
          stringParam('BRANCH', 'main', '')
        }
        definition {
          cps {
            script("${stagesString["jobStages"]}")
          }
        }
      }
    }

    job("${path}${currEnv["envName"]}${currPlatform["platformName"]}0 - Run all set jobs") {
      parameters {
        stringParam("ENV", "${currEnv["ENV"]}", "Environment being used for testing")
        stringParam('BRANCH', 'main', '')
      }
      triggers {
        cron '''H ''' + "${timeCounter}" + ''' * * 0-5'''
      }
      steps {
            shell('''
              set ENV=$ENV
              echo Environment is $ENV
              set BRANCH=$BRANCHs
              echo Environment is $BRANCH
              echo "triggerString: ''' + "${triggerString}" + '''"
            ''')
      }
      publishers {
        downstreamParameterized {
          trigger(triggerString) {
            condition('SUCCESS')
            parameters {
              currentBuild()
            }
          }
        }
      }
    }

    setJobsStagesStrings.clear();
  }
}

