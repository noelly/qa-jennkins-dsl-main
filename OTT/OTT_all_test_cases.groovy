def basePath = "QA-Selenium/ONO/OTT-DSL"
def environments = [
  [
    environmentName: "Staging",
    environmentParam: "ott-stg",
  ], 
  [
    environmentName: "Preprod",
    environmentParam: "ott-preprod",
  ],
  [
    environmentName: "Production",
    environmentParam: "ott-prod",
  ],
  [
    environmentName: "Staging - Pod 1",
    environmentParam: "ott-pod1-stg",
  ],
  [
    environmentName: "Staging - Pod 2",
    environmentParam: "ott-pod2-stg",
  ],
  [
    environmentName: "Staging - Pod 3",
    environmentParam: "ott-pod3-stg",
  ],
]

def ottjobs = [
  [DISPLAYNAME: "articleV1.3-feed", COMPONENT: "API/OTTArticleV1.3", TESTCASE: "feed"], 
  [DISPLAYNAME: "articleV1.3-slideshow", COMPONENT: "API/OTTArticleV1.3", TESTCASE: "slideshow"], 
  [DISPLAYNAME: "articleV2.0-by_slug", COMPONENT: "API/OTTArticleV2.0", TESTCASE: "by_slug"], 
  [DISPLAYNAME: "articleV2.0-feed", COMPONENT: "API/OTTArticleV2.0", TESTCASE: "feed"], 
  [DISPLAYNAME: "articleV2.0-feed-id", COMPONENT: "API/OTTArticleV2.0", TESTCASE: "feed-id"], 
  [DISPLAYNAME: "articleV2.0-feedv2", COMPONENT: "API/OTTArticleV2.0", TESTCASE: "feedv2"], 
  [DISPLAYNAME: "articleV2.0-header", COMPONENT: "API/OTTArticleV2.0", TESTCASE: "header"], 
  [DISPLAYNAME: "articleV2.0-rssfeed", COMPONENT: "API/OTTArticleV2.0", TESTCASE: "rssfeed"], 
  [DISPLAYNAME: "articleV2.0-slideshow", COMPONENT: "API/OTTArticleV2.0", TESTCASE: "slideshow"], 
  [DISPLAYNAME: "counter-action", COMPONENT: "API/OTTCounter", TESTCASE: "action"], 
  [DISPLAYNAME: "counter-feed", COMPONENT: "API/OTTCounter", TESTCASE: "feed"], 
  [DISPLAYNAME: "counter-feed-id", COMPONENT: "API/OTTCounter", TESTCASE: "feed-id"], 
  [DISPLAYNAME: "counter-hotreads", COMPONENT: "API/OTTCounter", TESTCASE: "hotReads"], 
  [DISPLAYNAME: "ott-article", COMPONENT: "API/OTT", TESTCASE: "articles"], 
  [DISPLAYNAME: "ott-brands", COMPONENT: "API/OTT", TESTCASE: "brands"], 
  [DISPLAYNAME: "redirect-appleumc", COMPONENT: "API/OTTRedirect", TESTCASE: "appleumc"], 
  [DISPLAYNAME: "redirect-articlev13", COMPONENT: "API/OTTRedirect", TESTCASE: "articlev13"], 
  [DISPLAYNAME: "redirect-articlev20", COMPONENT: "API/OTTRedirect", TESTCASE: "articlev20"], 
  [DISPLAYNAME: "redirect-videov20", COMPONENT: "API/OTTRedirect", TESTCASE: "videov20"], 
  [DISPLAYNAME: "videoV1.0-availability", COMPONENT: "API/OTTVideoV1.0", TESTCASE: "availability"], 
  [DISPLAYNAME: "videoV1.0-catalog", COMPONENT: "API/OTTVideoV1.0", TESTCASE: "catalog"], 
  [DISPLAYNAME: "videoV1.0-googlefeed", COMPONENT: "API/OTTVideoV1.0", TESTCASE: "googlefeed"], 
  [DISPLAYNAME: "videoV1.0-liveEventAvailability", COMPONENT: "API/OTTVideoV1.0", TESTCASE: "liveEventAvailability"], 
  [DISPLAYNAME: "videoV1.0-liveEventCatalog", COMPONENT: "API/OTTVideoV1.0", TESTCASE: "liveEventCatalog"], 
  [DISPLAYNAME: "videoV1.0-rokuxml", COMPONENT: "API/OTTVideoV1.0", TESTCASE: "rokuxml"], 
  [DISPLAYNAME: "videoV2.0-doubleclick", COMPONENT: "API/OTTVideoV2.0", TESTCASE: "doubleclick"], 
  [DISPLAYNAME: "videoV2.0-feed", COMPONENT: "API/OTTVideoV2.0", TESTCASE: "feed"], 
  [DISPLAYNAME: "videoV2.0-feed-id", COMPONENT: "API/OTTVideoV2.0", TESTCASE: "feed-id"], 
  [DISPLAYNAME: "videoV2.0-rssfeed", COMPONENT: "API/OTTVideoV2.0", TESTCASE: "rssfeed"],
]

// This is just a terminator string used below to simplify string handling
def tool = "'''";

// The following are to generate set jobs dynamically based in number of jobs and number of ottjobs
// Number of expected set jobs
def numberOfSetJobs = 3
// String used to dynamically build the job stages (i.e. ottjobs included in the set)
def setJobStages = ""
// Array holding copies of the above string, the final set jobs will be generated using these
def setJobsStagesStrings = []
// This just makes things easier
def maxStagesPerJob = Math.round(Math.ceil(ottjobs.size/numberOfSetJobs))

environments.eachWithIndex { environment, environmentIndex ->
  folder("${basePath}/${environment["environmentName"]}") {
    description("Automatically created via DSL plugin")
    displayName("${environment["environmentName"]}")
  }
  ottjobs.eachWithIndex { ottjob, ottjobIndex ->
    def int currentJobSet = Math.round(Math.floor(ottjobIndex/maxStagesPerJob))
    if (setJobsStagesStrings.size < currentJobSet + 1) {
      setJobsStagesStrings.add([
        jobName: "${basePath}/${environment["environmentName"]}/${currentJobSet + 1} - Set Job ${currentJobSet + 1}",
        jobStages: "",
      ])
    }

    setJobsStagesStrings[currentJobSet]["jobStages"] = setJobsStagesStrings[currentJobSet]["jobStages"] + '''
  catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
      stage(''' + "'${ottjob["DISPLAYNAME"]}'" + ''') {
          build job: ''' + "'${ottjob["DISPLAYNAME"]}'" + ''', parameters: [string(name: 'ENV', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
      }
  }
'''
    pipelineJob("${basePath}/${environment["environmentName"]}/${ottjob["DISPLAYNAME"]}") {
      parameters {
        stringParam("ENV", "${environment["environmentParam"]}", "Environment being used for testing")
        stringParam("BRANCH", "main", "Branch from where the code comes from")
        stringParam("COMPONENT", "${ottjob["COMPONENT"]}", "Folder containing the tests to be executed")
        stringParam("TESTCASE", "${ottjob["TESTCASE"]}", "File containing the dynamic test generator")
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
                          
                          catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                            stage('Execution stage - ''' + "${ottjob["DISPLAYNAME"]}" + ''' Query') {
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
        ''')
        }
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
