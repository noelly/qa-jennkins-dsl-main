def jobsList = [
[
  jobTitle: 'Categories - Med - userIsAbletoScroll - MWBU-13575', 
  COMPONENT: 'categories', 
  TESTCASE: 'userIsAbletoScroll'],
[
  jobTitle: 'Continue Watching - High - userNotSignedIn - MWBU-12992  ', 
  COMPONENT: 'continueWatching', 
  TESTCASE: 'userNotSignedIn'],
[
  jobTitle: 'Episode Detail Page - High - episodeCardUI - MWBU-12857  ', 
  COMPONENT: 'episodeDetailPage', 
  TESTCASE: 'episodeCardUI'],
[
  jobTitle: 'Episode Detail Page - High - episodeDetailPageUI - MWBU-12752', 
  COMPONENT: 'episodeDetailPage', 
  TESTCASE: 'episodeDetailPageUI'],
[
  jobTitle: 'Episode Detail Page - High - episodeDetailSectionUI - MWBU-12757', 
  COMPONENT: 'episodeDetailPage', 
  TESTCASE: 'episodeDetailSectionUI'],
[
  jobTitle: 'Episode Detail Page - High - navigationPlayback - MWBU-12640', 
  COMPONENT: 'episodeDetailPage', 
  TESTCASE: 'navigationPlayback'],
[
  jobTitle: 'Episode Detail Page - High - readMore - MWBU-12762', 
  COMPONENT: 'episodeDetailPage', 
  TESTCASE: 'readMore'],
[
  jobTitle: 'Episode Detail Page - High - replayRail - MWBU-15070', 
  COMPONENT: 'episodeDetailPage', 
  TESTCASE: 'replayRail'],
[
  jobTitle: 'Episode Detail Page - High - seasonSection - MWBU-12763', 
  COMPONENT: 'episodeDetailPage', 
  TESTCASE: 'seasonSection'],
[
  jobTitle: 'Front Porch - High - frontPorchFunctionality - MWBU-14792', 
  COMPONENT: 'frontPorch', 
  TESTCASE: 'frontPorchFunctionality'],
[
  jobTitle: 'Front Porch - High - frontPorchOverview - MWBU-14788', 
  COMPONENT: 'frontPorch', 
  TESTCASE: 'frontPorchOverview'],
[
  jobTitle: 'Header - Med - nonSubscribedUserUI - MWBU-12745 ', 
  COMPONENT: 'header', 
  TESTCASE: 'nonSubscribedUserUI'],
[
  jobTitle: 'Header - Med - noSubsOptionsRedirect - MWBU-12746', 
  COMPONENT: 'header', 
  TESTCASE: 'noSubsOptionsRedirect'],
[
  jobTitle: 'Header - Med - stickyHeaderUI - MWBU-12756', 
  COMPONENT: 'header', 
  TESTCASE: 'stickyHeaderUI'],
[
  jobTitle: 'Landing Page - Med - retiredBauLandingPages - MWBU-13821', 
  COMPONENT: 'landingPage', 
  TESTCASE: 'retiredBauLandingPages'],
[
  jobTitle: 'Landing Page - Med - sonicLandingPages - MWBU-13820', 
  COMPONENT: 'landingPage', 
  TESTCASE: 'sonicLandingPages'],
[
  jobTitle: 'Run Locally - Analytics Adobe - High - userNavigatesAcrossMtod - MWBU-13813  ', 
  COMPONENT: 'analyticsAdobe', 
  TESTCASE: 'userNavigatesAcrossMtod'],
[
  jobTitle: 'Run Locally - Carousel - Med - userNavigatesCarousel - MWBU-12638', 
  COMPONENT: 'carousel', 
  TESTCASE: 'userNavigatesCarousel'],
[
  jobTitle: 'Run Locally - Show Card - High - myStuffIcon - MWBU-14653', 
  COMPONENT: 'showCard', 
  TESTCASE: 'myStuffIcon'],
[
  jobTitle: 'Run Locally - Show Detail Page - Medium - freeEpisodesRailRedesign - MWBU-16031', 
  COMPONENT: 'showDetailPage', 
  TESTCASE: 'freeEpisodesRailRedesign'],
[
  jobTitle: 'Search - Med - clickShowAndEpCards - MWBU-12854', 
  COMPONENT: 'search', 
  TESTCASE: 'clickShowAndEpCards'],
[
  jobTitle: 'Search - Med - noResults - MWBU-12771', 
  COMPONENT: 'search', 
  TESTCASE: 'noResults'],
[
  jobTitle: 'Search - Med - searchUI - MWBU-12945', 
  COMPONENT: 'search', 
  TESTCASE: 'searchUI'],
[
  jobTitle: 'Show Card - High - showCard - MWBU-13023', 
  COMPONENT: 'showCard', 
  TESTCASE: 'showCard'],
[
  jobTitle: 'Show Card - High - showCardUI - MWBU-13021', 
  COMPONENT: 'showCard', 
  TESTCASE: 'showCardUI'],
[
  jobTitle: 'Show Detail Page - High - showDetailPageFunctionality - MWBU-15110', 
  COMPONENT: 'showDetailPage', 
  TESTCASE: 'showDetailPageFunctionality'],
[
  jobTitle: 'Show Detail Page - High - showDetailPageHeroLayout - MWBU-15451', 
  COMPONENT: 'showDetailPage', 
  TESTCASE: 'showDetailPageHeroLayout'],
[
  jobTitle: 'Show Detail Page - High - showDetailPageUI - MWBU-15087', 
  COMPONENT: 'showDetailPage', 
  TESTCASE: 'showDetailPageUI'],
[
  jobTitle: 'Sign In - High - signInLinks - MWBU-12961', 
  COMPONENT: 'signIn', 
  TESTCASE: 'signInLinks'],
[
  jobTitle: 'Sign In - High - signInUI - MWBU-12942', 
  COMPONENT: 'signIn', 
  TESTCASE: 'signInUI'],
[
  jobTitle: 'Seo - Med - sitemapXmlContent - MWBU-13520', 
  COMPONENT: 'seo', 
  TESTCASE: 'sitemapXmlContent'],
[
  jobTitle: 'Support Links - High - footerLinks - MWBU-12776', 
  COMPONENT: 'supportLinks', 
  TESTCASE: 'footerLinks'],
[
  jobTitle: 'Support Links - High - footerLinksRedirect - MWBU-12777', 
  COMPONENT: 'supportLinks', 
  TESTCASE: 'footerLinksRedirect'],
[
  jobTitle: 'Support Links - High - siteMapPage - MWBU-13136', 
  COMPONENT: 'supportLinks', 
  TESTCASE: 'siteMapPage']
]


def path = 'QA-Selenium/MTOD/Regression Tests/Preprod/';
def server = 'preprod';
def tool = "'''";

jobsList.each{ currJob -> 
  pipelineJob("${path}" + "${currJob["jobTitle"]}") {
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
    choice(choices: ['preprod', 'staging', 'www', 'dev'], description: '', name: 'ENV')
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

// The following are to generate set jobs dynamically based in number of jobTitles
// Number of expected set jobs
def numberOfSetJobs = 6
def maxStagesPerJob = Math.round(Math.ceil(jobsList.size/numberOfSetJobs))
// String used to dynamically build the job stages (i.e. jobTitles included in the set)
def setJobStages = ""
// Array holding copies of the above string, the final set jobs will be generated using these
def setJobsStagesStrings = []

jobsList.eachWithIndex{ currJob, currJobIndex -> 
  def int currentJobSet = Math.round(Math.floor(currJobIndex/maxStagesPerJob))
  if (setJobsStagesStrings.size < currentJobSet + 1) {
    setJobsStagesStrings.add([
      jobName: "${path}${currentJobSet + 1} - Set Job ${currentJobSet + 1}",
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

def triggerString = ""

setJobsStagesStrings.each {stagesString ->
  triggerString = triggerString + "${stagesString["jobName"]},"
  pipelineJob("${stagesString["jobName"]}") {
    parameters {
      stringParam("ENV", "${server}", "Environment being used for testing")
      stringParam('BRANCH', 'main', '')
    }
    definition {
      cps {
        script("${stagesString["jobStages"]}")
      }
    }
  }
}

job("${path}/0 - Run all set jobs") {
  parameters {
    stringParam("ENV", "${server}", "Environment being used for testing")
    stringParam('BRANCH', 'main', '')
  }
  triggers {
    cron '''H 04 * * 0-5'''
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
