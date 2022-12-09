def tool = "'''";
def servers = ['carbon-stg', 'carbon-preprod-akamai', 'prod'];
def index = 0;

// Core Web Vitals Folders & Jobs
def mainCWVFolder = "QA-Selenium/ONO/Core Web Vitals/";
def envFolders = ['Core Web Vitals - Staging/', 'Core Web Vitals - Preprod Behind Akamai/', 'Core Web Vitals - Production/'];
def platformFolders = ['Desktop', 'Mobile'];
def metricsFolders = ['CLS', 'FCP', 'LCP', 'SI', 'TBT', 'TTI'];
def jiraTicket = ' - LG5-5713';
def jobs = [
    'Performance - AMP Page',
    'Performance - Awards',
    'Performance - BG Body Style',
    'Performance - BG Index Page',
    'Performance - BG Price',
    'Performance - Buying Guide',
    'Performance - Car Match',
    'Performance - Car of the Year',
    'Performance - Car Reviews',
    'Performance - Certified',
    'Performance - CTT',
    'Performance - Home Page',
    'Performance - Hot Rod',
    'Performance - Make',
    'Performance - Make Model',
    'Performance - Make Model Year',
    'Performance - Post',
    'Performance - The Inevitable',
    'Performance - Topi',
    'Performance - Video',
];
def pageSlugs = [
  '/events/2022-tokyo-auto-salon-best-of/amp/',
  '/intellichoice/',
  '/style/suv/',
  '/cars/',
  '/price/under-30000/',
  '/style/suv/buying-guide/',
  '/car-match/',
  '/awards/best-car-of-the-year/',
  '/car-reviews/',
  '/certified/',
  '/features/best-full-size-three-row-suvs/',
  '', // Home page
  '/hotrod/',
  '/cars/chevrolet/',
  '/cars/chevrolet/corvette/',
  '/cars/chevrolet/corvette/2022/',
  '/how-to/big-block-vs-small-block-v8/',
  '/theinevitable/',
  '/muscle-cars/',
  '/videos/'
];

envFolders.each{ env -> 
    platformFolders.each{ platform ->
        metricsFolders.each{ metric -> 
            def pageSlugIndex = 0;
            def setArray = [];
            jobs.each { job ->
                pipelineJob("${mainCWVFolder}" + "${env}" + "${platform}/" + "${metric}/" + "${job}" + " - " + "${metric}" + "${jiraTicket}") {
                    parameters {
                      stringParam('CHECKOUT_TIMEOUT', '60', 'Wait up to 60 seconds for checkout')
                      stringParam('BRANCH', 'main', '')
                      stringParam('SLEEP', '3', '')
                      stringParam('BUILD_TIMEOUT', '130', '')
                      stringParam('TEST_TIMEOUT', '360', '')
                      choiceParam('LOGLEVEL', ['', '--loglevel warn', '--loglevel verbose'], '')
                      stringParam('COMPONENT', 'MT/Performance/', '')
                      stringParam('TESTCASE', 'PerformanceTest', '')
                      stringParam('SERVER', "${servers[index]}", '')
                      stringParam('METRIC', "${metric}", '')
                      stringParam('PAGESLUG', "${pageSlugs[pageSlugIndex]}", '')
                      stringParam('PLATFORM', "${platform}", '')
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
            resources:
            limits:
            memory: 1500Mi
            cpu: 1500m
            requests:
            memory: 300Mi
            cpu: 300m
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
      string(name: 'CHECKOUT_TIMEOUT', defaultValue: '60', description: 'Wait up to 60 seconds for checkout')
    
      string(name: 'BRANCH', defaultValue: 'main', description: '')
    
      string(name: 'SLEEP', defaultValue: '3', description: '')

      string(name: 'BUILD_TIMEOUT', defaultValue: '130', description: '')
      
      string(name: 'TEST_TIMEOUT', defaultValue: '360', description: '')

      choice(name: 'LOGLEVEL', choices: ['', '--loglevel warn', '--loglevel verbose'], description: '')

      string(name: 'COMPONENT', defaultValue: 'MT/Performance/', description: '')

      string(name: 'TESTCASE', defaultValue: 'PerformanceTest', description: '')

      string(name: 'SERVER', defaultValue: ''' + "'${servers[index]}'" + ''', description: '')

      string(name: 'METRIC', defaultValue: ''' + "'${metric}'" + ''' , description: '')

      string(name: 'PAGESLUG', defaultValue: ''' + "'${pageSlugs[pageSlugIndex]}'" + ''', description: '')

      string(name: 'PLATFORM', defaultValue: ''' + "'${platform}'" + ''', description: '')

    }
    
  stages {
    stage('Checkout') {
        steps {
            script {
            try {
                timeout(time: "${CHECKOUT_TIMEOUT}" as Integer, unit: 'SECONDS') {
                    git branch: '${BRANCH}',
                        credentialsId: 'github',
                        url: 'git@github.com:motortrend/motortrend-lithium-web-automation.git'
                }
            } catch (err) {
                    retry(3) {
                        echo(err)
                        sleep(time:"${SLEEP}" as Integer,unit:"SECONDS")
                        timeout(time: "${CHECKOUT_TIMEOUT}" as Integer, unit: 'SECONDS') {
                        git branch: '${BRANCH}',
                            credentialsId: 'github',
                            url: 'git@github.com:motortrend/motortrend-lithium-web-automation.git'
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
                    
                    stage('Core Web Vital test') {
                            sleep(time:"${SLEEP}" as Integer,unit:"SECONDS")
                            timeout(time: "${TEST_TIMEOUT}" as Integer, unit: 'SECONDS') {
                                sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} SERVER=${SERVER} METRIC=${METRIC} PAGESLUG=${PAGESLUG} PLATFORM=${PLATFORM} npm run devtools-kubernetes'
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
                setArray[pageSlugIndex] = "${job}" + " - " + "${metric}" + "${jiraTicket}";
                pageSlugIndex += 1;
            }
///////////////////////// Metrics SETS creation /////////////////////
          // Create "Set" Script
          def jobsToRun = '';
          setArray.each {setJob ->
            jobsToRun += '''
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                  stage(''' + "'${setJob}'" + ''') {
                    build job: ''' + "'${setJob}'" + ''', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                  }
                }

            ''';
          }
          // Create "Set" Jobs
          pipelineJob("${mainCWVFolder}" + "${env}" + "${platform}/" + "${metric}/" + "1-1 Core Web Vitals") {
            parameters {
              stringParam('BRANCH', 'main', '')
              stringParam('SERVER', "${servers[index]}", '')
            }
            definition {
              cps {
                script('''
                properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: ''' + "'${servers[index]}'" + ''', description: '', name: 'SERVER'), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])
                
                ''' +
                "${jobsToRun}"
                )
              }
            }
          }
//////////////////////////////////
        }
    }
  index += 1;
}

//////////////////////// Main SETS creation ///////////////////////
// Create "Set" Jobs
index = 0;
envFolders.each{ env -> 
    metricsFolders.each { metric ->
      pipelineJob("${mainCWVFolder}${env}1 - Core Web Vitals - ${metric} - Staging - Main") {
        parameters {
          stringParam('BRANCH', 'main', '')
          stringParam('SERVER', "${servers[index]}", '')
        }
        definition {
          cps {
            script('''
                properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: ''' + "'${servers[index]}'" + ''', description: '', name: 'SERVER'), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    stage(''' + "'${metric} Desktop'" + ''') {
                        build job: 'Desktop/''' + "${metric}" + '''/1-1 Core Web Vitals', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                    }
                }
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    stage(''' + "'${metric} Mobile'" + ''') {
                        build job: 'Mobile/''' + "${metric}" + '''/1-1 Core Web Vitals', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                    }
                }
                '''
           )
          }
        }
      }
    }
  index += 1;
}

// Create 0 Jobs
def setsToRun = "";
def metricsIndex = 0;
metricsFolders.each { metric ->
  setsToRun += "${metric}/1-1 Core Web Vitals,"
}

index = 0;
envFolders.each{ env -> 
  platformFolders.each{ platform ->
      // Metrics 0 Job
      job("${mainCWVFolder}${env}${platform}/0 - Run all CWV tests - ${platform}") {
        parameters {
          stringParam('BRANCH', 'main', '')
          stringParam('SERVER', "${servers[index]}", '')
          stringParam('SLEEP', '3', '')
          stringParam('CHECKOUT_TIMEOUT', '60', 'Wait up to 60 seconds for checkout')
          stringParam('BUILD_TIMEOUT', '130', '')
          choiceParam('LOGLEVEL', ['', '--loglevel warn', '--loglevel verbose'], '')
        }
        steps {
          shell('''
            set SERVER=$SERVER
            echo Server is $SERVER

            set BRANCH=$BRANCHs
            echo Server is $BRANCH

            set SLEEP=$SLEEP
            echo Server is $SLEEP

            set CHECKOUT_TIMEOUT=$CHECKOUT_TIMEOUT
            echo Server is $CHECKOUT_TIMEOUT

            set BUILD_TIMEOUT=$BUILD_TIMEOUT
            echo Server is $BUILD_TIMEOUT

            set LOGLEVEL=$LOGLEVEL
            echo Server is $LOGLEVEL
          ''')
        }
        publishers {
            downstreamParameterized {
                trigger("${setsToRun}") {
                    condition('SUCCESS')
                    parameters {
                        currentBuild()
                    }
                }
            }
        }
      }
  }
  // Main 0 Jobs
  job("${mainCWVFolder}${env}/0 - Run all CWV tests") {
    parameters {
          stringParam('BRANCH', 'main', '')
          stringParam('SERVER', "${servers[index]}", '')
          stringParam('SLEEP', '3', '')
          stringParam('CHECKOUT_TIMEOUT', '60', 'Wait up to 60 seconds for checkout')
          stringParam('BUILD_TIMEOUT', '130', '')
          choiceParam('LOGLEVEL', ['', '--loglevel warn', '--loglevel verbose'], '')
    }
    steps {
          shell('''
            set SERVER=$SERVER
            echo Server is $SERVER

            set BRANCH=$BRANCHs
            echo Server is $BRANCH

            set SLEEP=$SLEEP
            echo Server is $SLEEP

            set CHECKOUT_TIMEOUT=$CHECKOUT_TIMEOUT
            echo Server is $CHECKOUT_TIMEOUT

            set BUILD_TIMEOUT=$BUILD_TIMEOUT
            echo Server is $BUILD_TIMEOUT

            set LOGLEVEL=$LOGLEVEL
            echo Server is $LOGLEVEL
          ''')
    }
    publishers {
      downstreamParameterized {
        trigger("1 - Core Web Vitals - CLS - Staging - Main,1 - Core Web Vitals - FCP - Staging - Main,1 - Core Web Vitals - LCP - Staging - Main,1 - Core Web Vitals - SI - Staging - Main,1 - Core Web Vitals - TBT - Staging - Main,1 - Core Web Vitals - TTI - Staging - Main") {
          condition('SUCCESS')
          parameters {
            currentBuild()
          }
        }
      }
    }
  }
  index += 1;
}
