def servers = ['carbon-stg', 'carbon-preprod-akamai', 'prod', 'prod-eks'];
def mainFolder = "QA-Selenium/ONO";
def mainSTFolder = "${mainFolder}/Front End";
def envFolders = [
    'FE Smoke Tests - Staging',
    'FE Smoke Tests - Preproduction behind Akamai',
    'FE Smoke Tests - Production',
    'FE Smoke Tests - Production - EKS'
];

// Main Folders
folder("${mainFolder}"){
  description('ONO')
  displayName('ONO')
}
folder("${mainSTFolder}"){
  description('Front End')
  displayName('Front End')
}

def index = 0;
def tool = "'''";
// Smoke Tests Analytics
def jobslist = [
  [
    jobTitle: "UIUX - BuyersGuide - Buying Guide - LG5-4352 - LG5-6190", 
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "SmokeTestBuyingGuide",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - BuyersGuide - Car Match - LG5-5565", 
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "SmoketestBGCarMatchPage",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - BuyersGuide - Index Page - LG5-4345 - LG5-6190", 
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "SmokeTestBGIndexPage",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - BuyersGuide - Make Index Page - LG5-4349 - LG5-6190", 
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "SmokeTestBGMakeIndex",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - BuyersGuide - Make Model Page - LG5-4348 - LG5-6191", 
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "SmokeTestBGMakeModel",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - BuyersGuide - Price Page - LG5-4350 - LG5-6190", 
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "SmokeTestBGPricePage",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - BuyersGuide - Ranking Page - LG5-4351 - LG5-6190", 
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "SmokeTestBGRankingPage",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Homepage - AMAG - LG5-5557 - LG5-6190", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestHomePageAMAG",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Homepage - FW - LG5-5557 - LG5-6190", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestHomePageFW",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Homepage - HR - LG5-5557 - LG5-6190", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestHomePageHR",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Homepage - LG5-4367 - LG5-6190", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestHomePage",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Homepage - Low Rider - LG5-5557 - LG5-6190", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestHomePageLowRider",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Homepage - Super Street - LG5-5557 - LG5-6190", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestHomePageSuperStreet",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Homepage - Truck Trend - LG5-5557 - LG5-6190", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestHomePageTruckTrend",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Search - LG5-5254", 
    COMPONENT: "MT/Homepage/UIUX", 
    TESTCASE: "SmokeTestSearch",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - The Future - LG5-5591", 
    COMPONENT: "MT/TheFuture/UIUX", 
    TESTCASE: "SmoketestTheInEvitable",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Articles - CTT - LG5-6260", 
    COMPONENT: "MT/CTT/ADS", 
    TESTCASE: "SmokeTestCTTArticle",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Articles - Features - LG5-6263", 
    COMPONENT: "MT/Article/ADS", 
    TESTCASE: "SmokeTestFeaturesArticle",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Articles - News - LG5-6261",
    COMPONENT: "MT/Article/ADS",
    TESTCASE: "SmokeTestNewsArticle",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Articles - Reviews - LG5-6262", 
    COMPONENT: "MT/Article/ADS", 
    TESTCASE: "SmokeTestReviewsArticle",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Buyers Guide - Index Page - LG5-6264", 
    COMPONENT: "MT/BuyersGuide/ADS/", 
    TESTCASE: "SmokeTestBGMakeIndex",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Buyers Guide - Make - LG5-6265", 
    COMPONENT: "MT/BuyersGuide/ADS/", 
    TESTCASE: "SmokeTestBGMakeIndex",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Buyers Guide - Make Model - LG5-6266", 
    COMPONENT: "MT/BuyersGuide/ADS/", 
    TESTCASE: "SmokeTestBGMakeModel",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Buyers Guide - Make Model Year - LG5-6267", 
    COMPONENT: "MT/BuyersGuide/ADS/", 
    TESTCASE: "SmokeTestBGYearMakeModel",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Ads - Buyers Guide - Ranking Page - LG5-6269", 
    COMPONENT: "MT/BuyersGuide/ADS/", 
    TESTCASE: "SmokeTestBGRankingPage",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  // Smoke Tests Analytics
  [
    jobTitle: "Analytics - AMP Article - Features - Features - LG5-5201",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - Article - Features - Features - LG5-5200",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - AMP Article - News - News - LG5-4321",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - Article - News - News - LG5-4333",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - AMP Article - Reviews - Reviews - LG5-4323",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - Article - Reviews - Reviews - LG5-4337",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - BG Index Pages - LG5-4338",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGIndexPagesURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - BG Make Index Page - LG5-4327",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGMakeIndexPage.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - BG Make Model Page - LG5-4342",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGMakeModelIndexURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - BG Price Pages - LG5-5216",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGPriceIndexPagesURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - BG Ranking Page - LG5-4329",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestRankingIndexPage.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - Buying Guide Page Template - LG5-4330",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBuyingGuideIndexPageURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - CTT - LG5-4334",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/CTT/SmokeTestCTTArticleURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - Homepage - LG5-4335",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - Video - LG5-4336",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/Video/SmokeTestStandaloneVideoURL.json", 
    AMP: "", 
    PAGETYPE: "Video",
  ],
  [
    jobTitle: "Analytics - YMM - Discontinue Make - LG5-4331",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - YMM - Discontinued Make Model - LG5-4332",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - YMM - Discontinued Make Model Year - LG5-4325",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelYearURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - YMM - Make - LG5-5221",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestMakeURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - YMM - Make Model - LG5-5220",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestMakeModelURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "Analytics - YMM - Make Model Year - LG5-4326",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "AnalyticsSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestYMMURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  // Smoke Tests SEO
  [
    jobTitle: "SEO - AMP Article - Features - Features - LG5-5234",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "SEO - Article - Features - Features - LG5-5235",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "SEO - AMP Article - News - News - LG5-5229",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "SEO - Article - News - News - LG5-5226",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "SEO - AMP Article - Reviews - Reviews - LG5-5230",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "SEO - Article - Reviews - Reviews - LG5-5228",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "SEO - BuyersGuide - Buying Guide - LG5-5250",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/BuyersGuide/SmokeTestBuyingGuideIndexPageURL.json", 
    AMP: "", 
    PAGETYPE: "buyersguide",
  ],
  [
    jobTitle: "SEO - CTT - LG5-5239",
    COMPONENT: "MT/Article/SEO",
    TESTCASE: "SmokeTestSEO",
    URLFILE: "data/CTT/SmokeTestCTTArticleURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  // Smoke Tests UIUX Articles
  [
    jobTitle: "UIUX - AMP - Features - LG5-5243",
    COMPONENT: "MT/Article/UIUX",
    TESTCASE: "SmokeTestArticle",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Articles - Features - LG5-5244 - LG5-6190",
    COMPONENT: "MT/Article/UIUX",
    TESTCASE: "SmokeTestArticle",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - AMP - News Articles - LG5-4358",
    COMPONENT: "MT/Article/UIUX",
    TESTCASE: "SmokeTestArticle",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Articles - News Articles - LG5-4366 - LG5-6190",
    COMPONENT: "MT/Article/UIUX",
    TESTCASE: "SmokeTestArticle",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - AMP - Reviews Articles - LG5-4364",
    COMPONENT: "MT/Article/UIUX",
    TESTCASE: "SmokeTestArticle",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Articles - Reviews Articles - LG5-4365 - LG5-6190",
    COMPONENT: "MT/Article/UIUX",
    TESTCASE: "SmokeTestArticle",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - CTT - CTT Article - LG5-4368",
    COMPONENT: "MT/Article/UIUX",
    TESTCASE: "SmokeTestArticle",
    URLFILE: "data/CTT/SmokeTestCTTArticleURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  // Smoke Tests UIUX YMM
  [
    jobTitle: "UIUX - Discontinued Make - LG5-4353 - LG5-6190",
    COMPONENT: "MT/YMM/UIUX",
    TESTCASE: "SmokeTestYMM",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Discontinued Make Model - LG5-4354 - LG5-6191",
    COMPONENT: "MT/YMM/UIUX",
    TESTCASE: "SmokeTestYMM",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Discontinued Make Model Year - LG5-4355 - LG5-6191",
    COMPONENT: "MT/YMM/UIUX",
    TESTCASE: "SmokeTestYMM",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelYearURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "UIUX - Make Model Year- LG5-5252 - LG5-6191",
    COMPONENT: "MT/YMM/UIUX",
    TESTCASE: "SmokeTestYMM",
    URLFILE: "data/MotorTrend/YMM/SmokeTestYMMURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  // mParticle Jobs
  [
    jobTitle: "mParticle- Articles - Features - LG5-6580",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- Articles - News - LG5-6578",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- Articles - Reviews - LG5-6579",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Index Page - LG5-6543",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGIndexPagesURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Make Index Page - LG5-6544",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGMakeIndexPage.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Make Model Page - LG5-6545",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGMakeModelIndexURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Price Page - LG5-6731",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGPriceIndexPagesURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Ranking Page - LG5-6581",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestRankingIndexPage.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Buying Guide Page - LG5-6550",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBuyingGuideIndexPageURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- CTT - LG5-6581",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/CTT/SmokeTestCTTArticleURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- HP - LG5-6542",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Discontinued Make - LG5-6544",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Discontinued Make - LG5-6544 Model",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Discontinued Make Model Year - LG5-6546",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelYearURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Year Make Model Page - LG5-6546",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestYMMURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
];

envFolders.each{ env ->
  // Front End Smoke Tests Folders
  folder("${mainSTFolder}/${env}"){
    description("${env}")
    displayName("${env}")
  }
  // Jobs Creation
  jobslist.eachWithIndex{ item, i ->
    pipelineJob("${mainSTFolder}/${env}/${item["jobTitle"]}") {
      parameters {
        stringParam('CHECKOUT_TIMEOUT', '60', 'Wait up to 60 seconds for checkout')
        stringParam('BRANCH', 'main', '')
        stringParam('SLEEP', '3', '')
        stringParam('BUILD_TIMEOUT', '130', '')
        stringParam('TEST_TIMEOUT', '360', '')
        choiceParam('LOGLEVEL', ['', '--loglevel warn', '--loglevel verbose'], '')
        stringParam('COMPONENT', "${item["COMPONENT"]}", '')
        stringParam('TESTCASE', "${item["TESTCASE"]}", '')
        stringParam('SERVER', "${servers[index]}", '')
        stringParam('PAGESLUG', '', '')
        stringParam('URLFILE', "${item["URLFILE"]}", '')
        stringParam('PAGETYPE', "${item["PAGETYPE"]}", '')
        stringParam('AMP', "${item["AMP"]}", '')
        stringParam('MAKE', '', '')
        stringParam('MODEL', '', '')
        stringParam('YEAR', '', '')
        stringParam('BODYSTYLE', '', '')
      }
      definition {
        cps {
          script('''
            pipeline {
              agent any
              stages {
                stage('Running test') {
                  steps {
                    sh 'COMPONENT=${COMPONENT} TESTCASE=${TESTCASE} SERVER=${SERVER} PAGESLUG=${PAGESLUG} URLFILE=${URLFILE} PAGETYPE=${PAGETYPE} AMP=${AMP} MAKE=${MAKE} MODEL=${MODEL} YEAR=${YEAR} BODYSTYLE=${BODYSTYLE} npm run devtools-kubernetes'
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
  index += 1;
}

// 0 Jobs Creation
index = 0;
envFolders.each{ env ->
    job("${mainSTFolder}/${env}/0 - Build this Job to run all Smoke Tests - Local") {
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
                trigger("Ads - Articles - CTT - LG5-6260, Ads - Articles - Features - LG5-6263, Ads - Articles - News - LG5-6261, Ads - Articles - Reviews - LG5-6262, Ads - Buyers Guide - Index Page - LG5-6264, Ads - Buyers Guide - Make - LG5-6265, Ads - Buyers Guide - Make Model - LG5-6266, Ads - Buyers Guide - Make Model Year - LG5-6267, Ads - Buyers Guide - Ranking Page - LG5-6269, Analytics - AMP Article - Features - Features - LG5-5201, Analytics - AMP Article - News - News - LG5-4321, Analytics - AMP Article - Reviews - Reviews - LG5-4323, Analytics - Article - Features - Features - LG5-5200, Analytics - Article - News - News - LG5-4333, Analytics - Article - Reviews - Reviews - LG5-4337, Analytics - BG Index Pages - LG5-4338, Analytics - BG Make Index Page - LG5-4327, Analytics - BG Make Model Page - LG5-4342, Analytics - BG Price Pages - LG5-5216, Analytics - BG Ranking Page - LG5-4329, Analytics - Buying Guide Page Template - LG5-4330, Analytics - CTT - LG5-4334, Analytics - Homepage - LG5-4335, Analytics - Video - LG5-4336, Analytics - YMM - Discontinue Make - LG5-4331, Analytics - YMM - Discontinued Make Model - LG5-4332, Analytics - YMM - Discontinued Make Model Year - LG5-4325, Analytics - YMM - Make - LG5-5221, Analytics - YMM - Make Model - LG5-5220, Analytics - YMM - Make Model Year - LG5-4326, mParticle- Articles - Features - LG5-6580, mParticle- Articles - News - LG5-6578, mParticle- Articles - Reviews - LG5-6579, mParticle- BG - Buying Guide Page - LG5-6550, mParticle- BG - Index Page - LG5-6543, mParticle- BG - Make Index Page - LG5-6544, mParticle- BG - Make Model Page - LG5-6545, mParticle- BG - Price Page - LG5-6731, mParticle- BG - Ranking Page - LG5-6581, mParticle- CTT - LG5-6581, mParticle- HP - LG5-6542, mParticle- YMM - Discontinued Make - LG5-6544, mParticle- YMM - Discontinued Make - LG5-6544 Model, mParticle- YMM - Discontinued Make Model Year - LG5-6546, mParticle- YMM - Year Make Model Page - LG5-6546, SEO - AMP Article - Features - Features - LG5-5234, SEO - AMP Article - News - News - LG5-5229, SEO - AMP Article - Reviews - Reviews - LG5-5230, SEO - Article - Features - Features - LG5-5235, SEO - Article - News - News - LG5-5226, SEO - Article - Reviews - Reviews - LG5-5228, SEO - BuyersGuide - Buying Guide - LG5-5250, SEO - CTT - LG5-5239, UIUX - AMP - Features - LG5-5243, UIUX - AMP - News Articles - LG5-4358, UIUX - AMP - Reviews Articles - LG5-4364, UIUX - Articles - Features - LG5-5244 - LG5-6190, UIUX - Articles - News Articles - LG5-4366 - LG5-6190, UIUX - Articles - Reviews Articles - LG5-4365 - LG5-6190, UIUX - BuyersGuide - Buying Guide - LG5-4352 - LG5-6190, UIUX - BuyersGuide - Car Match - LG5-5565, UIUX - BuyersGuide - Index Page - LG5-4345 - LG5-6190, UIUX - BuyersGuide - Make Index Page - LG5-4349 - LG5-6190, UIUX - BuyersGuide - Make Model Page - LG5-4348 - LG5-6191, UIUX - BuyersGuide - Price Page - LG5-4350 - LG5-6190, UIUX - BuyersGuide - Ranking Page - LG5-4351 - LG5-6190, UIUX - CTT - CTT Article - LG5-4368, UIUX - Discontinued Make - LG5-4353 - LG5-6190, UIUX - Discontinued Make Model - LG5-4354 - LG5-6191, UIUX - Discontinued Make Model Year - LG5-4355 - LG5-6191, UIUX - Homepage - AMAG - LG5-5557 - LG5-6190, UIUX - Homepage - FW - LG5-5557 - LG5-6190, UIUX - Homepage - HR - LG5-5557 - LG5-6190, UIUX - Homepage - LG5-4367 - LG5-6190, UIUX - Homepage - Low Rider - LG5-5557 - LG5-6190, UIUX - Homepage - Super Street - LG5-5557 - LG5-6190, UIUX - Homepage - Truck Trend - LG5-5557 - LG5-6190, UIUX - Make Model Year- LG5-5252 - LG5-6191, UIUX - Search - LG5-5254, UIUX - The Future - LG5-5591") {
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
