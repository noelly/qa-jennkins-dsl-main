def basePath = "QA-Selenium/ONO/API-DSL"
def environments = [
  [
    environmentName: "Staging",
    environmentParam: "carbon-stg",
  ], 
  [
    environmentName: "Preprod",
    environmentParam: "carbon-preprod",
  ],
  [
    environmentName: "Production",
    environmentParam: "carbon-prod",
  ],
  [
    environmentName: "Staging - Pod 1",
    environmentParam: "carbon-csin-stg",
  ],
  [
    environmentName: "Staging - Pod 2",
    environmentParam: "carbon-pod2-stg",
  ],
  [
    environmentName: "Staging - Pod 3",
    environmentParam: "carbon-pod3-stg",
  ],
]

def endpoints = [
  [endpointName: "article", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "articleFeed", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "articlesByVehicleTags", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "brandConfig", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "cmsModels", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "cmsVehicleBodyStyleDetails", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "cmsVehicleRankingClasses", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "cmsVehicleTrims", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "contributorBySlug", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "contributors", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "curatedImages", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "curation", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "curations", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "hotReads", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "latest", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "latestPlaylistByBrand", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "latestPlaylistByMake", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "latestPlaylistByRankingClass", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "makes", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "models", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "module", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "modules", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "permissions", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "priceRanges", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "roles", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "search", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "tagById", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "tags", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "taxonomyTermById", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "taxonomyTerms", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "topRankVehiclesByMake", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "users", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "validateModuleName", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "validateRoute", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "vehicle", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "vehicleBodyStyles", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "vehicleBodyTypes", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "vehicleDetails", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "vehicleRankingClasses", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "vehiclesByRanking", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "verticalTagById", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "verticalTagBySlug", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "verticalTags", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "video", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "videoFeed", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "years", endpointSuite: "CarbonRegressionSuite"],
  [endpointName: "resource", endpointSuite: "CarbonRegression"],
  [endpointName: "searchArticles", endpointSuite: "CarbonRegression"],
  [endpointName: "vehiclesByPriceRange", endpointSuite: "CarbonRegression"],
]

// This is just a terminator string used below to simplify string handling
def tool = "'''";

// The following are to generate set jobs dynamically based in number of jobs and number of endpoints
// Number of expected set jobs
def numberOfSetJobs = 6
// String used to dynamically build the job stages (i.e. endpoints included in the set)
def setJobStages = ""
// Array holding copies of the above string, the final set jobs will be generated using these
def setJobsStagesStrings = []
// This just makes things easier
def maxStagesPerJob = Math.round(Math.ceil(endpoints.size/numberOfSetJobs))

environments.eachWithIndex { environment, environmentIndex ->
  folder("${basePath}/${environment["environmentName"]}") {
    description("Automatically created via DSL plugin")
    displayName("${environment["environmentName"]}")
  }
  endpoints.eachWithIndex { endpoint, endpointIndex ->
    def int currentJobSet = Math.round(Math.floor(endpointIndex/maxStagesPerJob))
    if (setJobsStagesStrings.size < currentJobSet + 1) {
      setJobsStagesStrings.add([
        jobName: "${basePath}/${environment["environmentName"]}/${currentJobSet + 1} - Set Job ${currentJobSet + 1}",
        jobStages: "",
      ])
    }

    setJobsStagesStrings[currentJobSet]["jobStages"] = setJobsStagesStrings[currentJobSet]["jobStages"] + '''
  catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
      stage(''' + "'${endpoint["endpointName"]}'" + ''') {
          build job: ''' + "'${endpoint["endpointName"]}'" + ''', parameters: [string(name: 'ENV', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
      }
  }
'''
    pipelineJob("${basePath}/${environment["environmentName"]}/${endpoint["endpointName"]}") {
      parameters {
        stringParam("ENV", "${environment["environmentParam"]}", "Environment being used for testing")
        stringParam("BRANCH", "main", "Branch from where the code comes from")
        stringParam("COMPONENT", "API", "Folder containing the tests to be executed")
        stringParam("TESTCASE", "data-driven-execution", "File containing the dynamic test generator")
        stringParam("SUITE", "${endpoint["endpointSuite"]}", "Suite of tests to be executed")
        stringParam("FILTER", "${endpoint["endpointName"]}", "Endpoint for which data will be generated")
        stringParam("ENDPOINT", "${endpoint["endpointName"]}", "Endpoint for which tests will be executed (should match FILTER)")
        stringParam("CHECKOUT_TIMEOUT", "60", "Wait up to 60 seconds for completing checkout")
        stringParam("BUILD_TIMEOUT", "180", "Wait up to 180 seconds for completing build")
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
                          - name: rotate
                  ''' + "${tool}" + '''
                }
              }
              stages {
                stage('Execution') {
                  steps {
                    script {
                      stage('Checkout')  {
                        retry(3) {
                          try {
                            echo "Initiating checkout phase."
                            timeout(time: "${CHECKOUT_TIMEOUT}" as Integer, unit: 'SECONDS') {
                              git branch: '${BRANCH}',
                              credentialsId: 'github',
                              url: 'git@github.com:motortrend/motortrend-lithium-web-automation.git'
                            }
                          } 
                          catch (err) {
                            error "Checkout did not complete in ${CHECKOUT_TIMEOUT} seconds."
                          }
                        }
                      }
                      
                      stage('Build'){
                        container ('qa-automation-npm'){
                          environment {
                            PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true
                          }
                          retry (3) {
                            try {
                              sleep(time:3,unit:"SECONDS")
                              timeout(time: "${BUILD_TIMEOUT}" as Integer, unit: 'SECONDS') {
                                sh 'npm install --omit=dev --loglevel verbose'
                              }
                            }
                            catch (err) {
                              error "Build did not complete in ${BUILD_TIMEOUT} seconds"
                            }
                          }
                          
                          catchError(buildResult: 'FAILURE', stageResult: 'SUCCESS') {
                            stage('Generate stage') {
                              try {
                                sleep(time:3,unit:"SECONDS")
                                sh 'COMPONENT=${COMPONENT} TESTCASE=dynamic-data-generator ENV=${ENV} FILTER=${FILTER} npm run devtools-kubernetes'
                              } catch (err) { 
                                echo "The test failed, let's retry"
                                retry(3) {
                                  sleep(time:3,unit:"SECONDS")
                                  sh 'COMPONENT=${COMPONENT} TESTCASE=dynamic-data-generator ENV=${ENV} FILTER=${FILTER} npm run devtools-kubernetes'
                                }
                              }
                            }
                          }
                          
                          catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                            stage('Execution stage - ''' + "${endpoint["endpointName"]}" + ''' Query') {
                              try {
                                sleep(time:3,unit:"SECONDS")
                                sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} SUITE=${SUITE} ENDPOINT=${ENDPOINT} ENV=${ENV} npm run devtools-kubernetes'
                              } catch (err) {
                                echo "The test failed, let's retry"
                                retry(3) {
                                  sleep(time:3,unit:"SECONDS")
                                  sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} SUITE=${SUITE} ENDPOINT=${ENDPOINT} ENV=${ENV} npm run devtools-kubernetes'
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
        ''')
        }
      }
    }
  }

  //carMatch generation
  setJobsStagesStrings[numberOfSetJobs - 1]["jobStages"] = setJobsStagesStrings[numberOfSetJobs - 1]["jobStages"] + '''
    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
        stage(''' + "'carMatch'" + ''') {
            build job: ''' + "'carMatch'" + ''', parameters: [string(name: 'ENV', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
        }
    }
    '''
  pipelineJob("${basePath}/${environment["environmentName"]}/carMatch") {
    parameters {
      stringParam("ENV", "${environment["environmentParam"]}", "Environment being used for testing")
      stringParam("BRANCH", "main", "Branch from where the code comes from")
      stringParam("COMPONENT", "API", "Folder containing the tests to be executed")
      stringParam("TESTCASE", "car-match-validation", "File containing the dynamic test generator")
      stringParam("THROTTLE", "100", "Maximum number of tests")
      stringParam("CHECKOUT_TIMEOUT", "60", "Wait up to 60 seconds for completing checkout")
      stringParam("BUILD_TIMEOUT", "180", "Wait up to 180 seconds for completing build")
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
                        - name: rotate
                ''' + "${tool}" + '''
              }
            }
            stages {
              stage('Execution') {
                steps {
                  script {
                    node('qa-automation-npm') {
                      stage('Checkout')  {
                        retry(3) {
                          try {
                            echo "Initiating checkout phase."
                            timeout(time: "${CHECKOUT_TIMEOUT}" as Integer, unit: 'SECONDS') {
                              git branch: '${BRANCH}',
                                    credentialsId: 'github',
                                    url: 'git@github.com:motortrend/motortrend-lithium-web-automation.git'
                            }
                          } 
                          catch (err) {
                            error "Checkout did not complete in ${CHECKOUT_TIMEOUT} seconds."
                          }
                        }
                      }
                      
                      stage('Build'){
                        container ('qa-automation-npm'){
                          environment {
                            PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true
                          }
                          retry (3) {
                            try {
                              sleep(time:3,unit:"SECONDS")
                              timeout(time: "${BUILD_TIMEOUT}" as Integer, unit: 'SECONDS') {
                                sh 'npm install --omit=dev --loglevel verbose'
                              }
                            }
                            catch (err) {
                              error "Build did not complete in ${BUILD_TIMEOUT} seconds"
                            }
                          }
                          
                          catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                            stage('Execution stage - Video Query') {
                              try {
                                sleep(time:3,unit:"SECONDS")
                                sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} ENV=${ENV} THROTTLE=${THROTTLE} npm run devtools-kubernetes'
                              } catch (err) {
                                echo "The test failed, let's retry"
                                retry(3) {
                                  sleep(time:3,unit:"SECONDS")
                                  sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} ENV=${ENV} THROTTLE=${THROTTLE} npm run devtools-kubernetes'
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
      ''')
      }
    }
  }

  //Curation Duplicates generation
  setJobsStagesStrings[numberOfSetJobs - 1]["jobStages"] = setJobsStagesStrings[numberOfSetJobs - 1]["jobStages"] + '''
    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
        stage(''' + "'Curation Duplicates'" + ''') {
            build job: ''' + "'Curation Duplicates'" + ''', parameters: [string(name: 'ENV', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
        }
    }
    '''
  pipelineJob("${basePath}/${environment["environmentName"]}/Curation Duplicates") {
    parameters {
      stringParam("ENV", "${environment["environmentParam"]}", "Environment being used for testing")
      stringParam("BRANCH", "main", "Branch from where the code comes from")
      stringParam("COMPONENT", "API", "Folder containing the tests to be executed")
      stringParam("TESTCASE", "validate-no-duplicate-contain-on-site", "File containing the dynamic test generator")
      stringParam("CHECKOUT_TIMEOUT", "60", "Wait up to 60 seconds for completing checkout")
      stringParam("BUILD_TIMEOUT", "180", "Wait up to 180 seconds for completing build")
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
                        - name: rotate
                ''' + "${tool}" + '''
              }
            }
            stages {
              stage('Execution') {
                steps {
                  script {
                    node('qa-automation-npm') {
                      stage('Checkout')  {
                        retry(3) {
                          try {
                            echo "Initiating checkout phase."
                            timeout(time: "${CHECKOUT_TIMEOUT}" as Integer, unit: 'SECONDS') {
                              git branch: '${BRANCH}',
                              credentialsId: 'github',
                              url: 'git@github.com:motortrend/motortrend-lithium-web-automation.git'
                            }
                          } 
                          catch (err) {
                            error "Checkout did not complete in ${CHECKOUT_TIMEOUT} seconds."
                          }
                        }
                      }
                      
                      stage('Build'){
                        container ('qa-automation-npm'){
                          environment {
                            PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true
                          }
                          retry (3) {
                            try {
                              sleep(time:3,unit:"SECONDS")
                              timeout(time: "${BUILD_TIMEOUT}" as Integer, unit: 'SECONDS') {
                                sh 'npm install --omit=dev --loglevel verbose'
                              }
                            }
                            catch (err) {
                              error "Build did not complete in ${BUILD_TIMEOUT} seconds"
                            }
                          }
                            
                          catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                            stage('Execution stage - Curation Duplicates') {
                              try {
                                sleep(time:3,unit:"SECONDS")
                                sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} ENV=${ENV} npm run devtools-kubernetes'
                              } catch (err) {
                                echo "The test failed, let's retry"
                                retry(3) {
                                  sleep(time:3,unit:"SECONDS")
                                  sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} ENV=${ENV} npm run devtools-kubernetes'
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
      ''')
      }
    }
  }

  def triggerString = ""

  setJobsStagesStrings.each {stagesString ->
    triggerString = triggerString + "${stagesString["jobName"]},"
    pipelineJob("${stagesString["jobName"]}") {
      parameters {
        stringParam("SERVER", "${environment["environmentParam"]}", "Environment being used for testing")
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script("${stagesString["jobStages"]}")
        }
      }
    }
  }

  job("${basePath}/${environment["environmentName"]}/0 - Run all set jobs") {
    parameters {
      stringParam("SERVER", "${environment["environmentParam"]}", "Environment being used for testing")
      stringParam('BRANCH', 'main', '')
    }
    steps {
          shell('''
            set SERVER=$SERVER
            echo Server is $SERVER

            set BRANCH=$BRANCHs
            echo Server is $BRANCH

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
