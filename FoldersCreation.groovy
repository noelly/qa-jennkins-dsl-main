def mainFolder = "QA-Selenium/ONO";

// Main Folders
folder("${mainFolder}"){
  description('my description')
  displayName('ONO')
}

// Core Web Vitals Folders
def mainCWVFolder = "${mainFolder}/Core Web Vitals";
def envFolders = [
  'Core Web Vitals - Staging',
  'Core Web Vitals - Preprod Behind Akamai',
  'Core Web Vitals - Production'
  ];
def platformFolders = [
  'Desktop',
  'Mobile'
  ];
def metricsFolders = [
  'CLS',
  'FCP',
  'LCP',
  'SI',
  'TBT',
  'TTI'
  ];

folder("${mainCWVFolder}"){
  description('Core Web Vitals')
  displayName('Core Web Vitals')
}
envFolders.each { env -> 
  folder("${mainCWVFolder}/${env}"){
    description("Core Web Vitals - ${env}")
    displayName("${env}")
  }
  platformFolders.each { platform ->
      folder("${mainCWVFolder}/${env}/${platform}"){
        description("Core Web Vitals - ${env} - ${platform}")
        displayName("${platform}")
      }
      metricsFolders.each { metric ->
        folder("${mainCWVFolder}/${env}/${platform}/${metric}"){
          description("Core Web Vitals - ${env} - ${platform} - ${metric}")
          displayName("${metric}")
        }
      }
  }
}
// Front End Main Folder
def mainFEFolder = "${mainFolder}/Front End";
folder("${mainFEFolder}"){
  description('Front End')
  displayName('Front End')
}
// Front End Smoke Tests Folders
envFolders = [
  'FE Smoke Tests - Staging',
  'FE Smoke Tests - Preproduction behind Akamai',
  'FE Smoke Tests - Production'
  ];

envFolders.each { env ->
  folder("${mainFEFolder}/${env}"){
    description("${env}")
    displayName("${env}")
  }
}

// Front End Regression Tests Folders
envFolders = [
  'Regression - Staging',
  'Regression - Preprod Behind Akamai',
  'Regression - Production'
  ];

envFolders.each { env -> 
  folder("${mainFEFolder}/${env}"){
    description("${env}")
    displayName("${env}")
  }
}
