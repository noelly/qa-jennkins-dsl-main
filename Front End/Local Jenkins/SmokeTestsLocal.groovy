def servers = ['carbon-stg', 'carbon-preprod-akamai', 'prod', 'pod1-stg', 'pod2-stg', 'pod3-stg', 'carbon-dev'];
def mainFolder = "QA-Selenium/ONO";
def mainSTFolder = "${mainFolder}/Front End";
def envFolders = [
    'FE Smoke Tests - Staging',
    'FE Smoke Tests - Preproduction behind Akamai',
    'FE Smoke Tests - Production',
    'FE Smoke Tests - Pod 1 - Staging',
    'FE Smoke Tests - Pod 2 - Staging',
    'FE Smoke Tests - Pod 3 - Staging',
    'FE Smoke Tests - Dev'
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
    jobTitle: "mParticle- Articles - Features",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/Features/SmokeTestFeaturesUrls.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- Articles - News",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/News/SmokeTestNewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- Articles - Reviews",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/Reviews/SmokeTestReviewsURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Index Page",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGIndexPagesURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Make Index Page",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGMakeIndexPage.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Make Model Page",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGMakeModelIndexURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Price Page",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBGPriceIndexPagesURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Ranking Page",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestRankingIndexPage.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- BG - Buying Guide Page",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/BuyersGuide/SmokeTestBuyingGuideIndexPageURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- CTT",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/CTT/SmokeTestCTTArticleURL.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- HP",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Discontinued Make",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Discontinued Make Model",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Discontinued Make Model Year",
    COMPONENT: "MT/ExtendedAnalytics",
    TESTCASE: "mParticleSmokeTest",
    URLFILE: "data/MotorTrend/YMM/SmokeTestDiscontinuedMakeModelYearURLs.json", 
    AMP: "", 
    PAGETYPE: "",
  ],
  [
    jobTitle: "mParticle- YMM - Year Make Model Page",
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

// Sets Creaation
index = 0;
servers.each { server ->
    pipelineJob("${mainSTFolder}/${envFolders[index]}/1a - Smoke Test Set 1") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - AMP Article - Features - Features - LG5-5201') {
                    build job: 'Analytics - AMP Article - Features - Features - LG5-5201', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - AMP Article - News - News - LG5-4321') {
                    build job: 'Analytics - AMP Article - News - News - LG5-4321', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - AMP Article - Reviews - Reviews - LG5-4323') {
                    build job: 'Analytics - AMP Article - Reviews - Reviews - LG5-4323', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Article - Features - Features - LG5-5200') {
                    build job: 'Analytics - Article - Features - Features - LG5-5200', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Article - News - News - LG5-4333') {
                    build job: 'Analytics - Article - News - News - LG5-4333', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Article - Reviews - Reviews - LG5-4337') {
                    build job: 'Analytics - Article - Reviews - Reviews - LG5-4337', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG Index Pages - LG5-4338') {
                    build job: 'Analytics - BG Index Pages - LG5-4338', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG Make Index Page - LG5-4327') {
                    build job: 'Analytics - BG Make Index Page - LG5-4327', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG Make Model Page - LG5-4342') {
                    build job: 'Analytics - BG Make Model Page - LG5-4342', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG Price Pages - LG5-5216') {
                    build job: 'Analytics - BG Price Pages - LG5-5216', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG Ranking Page - LG5-4329') {
                    build job: 'Analytics - BG Ranking Page - LG5-4329', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Buying Guide Page Template - LG5-4330') {
                    build job: 'Analytics - Buying Guide Page Template - LG5-4330', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BuyersGuide - Car Match - LG5-5565') {
                    build job: 'UIUX - BuyersGuide - Car Match - LG5-5565', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${mainSTFolder}/${envFolders[index]}/1b - Smoke Test Set 2") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - CTT - LG5-4334') {
                    build job: 'Analytics - CTT - LG5-4334', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Homepage - LG5-4335') {
                    build job: 'Analytics - Homepage - LG5-4335', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Video - LG5-4336') {
                    build job: 'Analytics - Video - LG5-4336', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Discontinue Make - LG5-4331') {
                    build job: 'Analytics - YMM - Discontinue Make - LG5-4331', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Discontinued Make Model - LG5-4332') {
                    build job: 'Analytics - YMM - Discontinued Make Model - LG5-4332', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Discontinued Make Model Year - LG5-4325') {
                    build job: 'Analytics - YMM - Discontinued Make Model Year - LG5-4325', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Make - LG5-5221') {
                    build job: 'Analytics - YMM - Make - LG5-5221', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Make Model - LG5-5220') {
                    build job: 'Analytics - YMM - Make Model - LG5-5220', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Make Model Year - LG5-4326') {
                    build job: 'Analytics - YMM - Make Model Year - LG5-4326', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - AMP Article - Features - Features - LG5-5234') {
                    build job: 'SEO - AMP Article - Features - Features - LG5-5234', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - AMP Article - Reviews - Reviews - LG5-5230') {
                    build job: 'SEO - AMP Article - Reviews - Reviews - LG5-5230', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - AMP Article - News - News - LG5-5229') {
                    build job: 'SEO - AMP Article - News - News - LG5-5229', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
              }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${mainSTFolder}/${envFolders[index]}/1c - Smoke Test Set 3") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - Article - Features - Features - LG5-5235') {
                    build job: 'SEO - Article - Features - Features - LG5-5235', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - Article - News - News - LG5-5226') {
                    build job: 'SEO - Article - News - News - LG5-5226', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - Article - Reviews - Reviews - LG5-5228') {
                    build job: 'SEO - Article - Reviews - Reviews - LG5-5228', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - BuyersGuide - Buying Guide - LG5-5250') {
                    build job: 'SEO - BuyersGuide - Buying Guide - LG5-5250', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - CTT - LG5-5239') {
                    build job: 'SEO - CTT - LG5-5239', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - AMP - Features - LG5-5243') {
                    build job: 'UIUX - AMP - Features - LG5-5243', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - AMP - News Articles - LG5-4358') {
                    build job: 'UIUX - AMP - News Articles - LG5-4358', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - AMP - Reviews Articles - LG5-4364') {
                    build job: 'UIUX - AMP - Reviews Articles - LG5-4364', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Features - LG5-5244 - LG5-6190') {
                    build job: 'UIUX - Articles - Features - LG5-5244 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - News Articles - LG5-4366 - LG5-6190') {
                    build job: 'UIUX - Articles - News Articles - LG5-4366 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Reviews Articles - LG5-4365 - LG5-6190') {
                    build job: 'UIUX - Articles - Reviews Articles - LG5-4365 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BuyersGuide - Buying Guide - LG5-4352 - LG5-6190') {
                    build job: 'UIUX - BuyersGuide - Buying Guide - LG5-4352 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${mainSTFolder}/${envFolders[index]}/1d - Smoke Test Set 4") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BuyersGuide - Index Page - LG5-4345 - LG5-6190') {
                    build job: 'UIUX - BuyersGuide - Index Page - LG5-4345 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BuyersGuide - Make Index Page - LG5-4349 - LG5-6190') {
                    build job: 'UIUX - BuyersGuide - Make Index Page - LG5-4349 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BuyersGuide - Make Model Page - LG5-4348 - LG5-6191') {
                    build job: 'UIUX - BuyersGuide - Make Model Page - LG5-4348 - LG5-6191', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BuyersGuide - Price Page - LG5-4350 - LG5-6190') {
                    build job: 'UIUX - BuyersGuide - Price Page - LG5-4350 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BuyersGuide - Ranking Page - LG5-4351 - LG5-6190') {
                    build job: 'UIUX - BuyersGuide - Ranking Page - LG5-4351 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - CTT - CTT Article - LG5-4368') {
                    build job: 'UIUX - CTT - CTT Article - LG5-4368', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - LG5-4367 - LG5-6190') {
                    build job: 'UIUX - Homepage - LG5-4367 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Search - LG5-5254') {
                    build job: 'UIUX - Search - LG5-5254', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Discontinued Make - LG5-4353 - LG5-6190') {
                    build job: 'UIUX - Discontinued Make - LG5-4353 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Discontinued Make Model - LG5-4354 - LG5-6191') {
                    build job: 'UIUX - Discontinued Make Model - LG5-4354 - LG5-6191', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Discontinued Make Model Year - LG5-4355 - LG5-6191') {
                    build job: 'UIUX - Discontinued Make Model Year - LG5-4355 - LG5-6191', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Model Year- LG5-5252 - LG5-6191') {
                    build job: 'UIUX - Make Model Year- LG5-5252 - LG5-6191', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - AMAG - LG5-5557 - LG5-6190') {
                    build job: 'UIUX - Homepage - AMAG - LG5-5557 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - FW - LG5-5557 - LG5-6190') {
                    build job: 'UIUX - Homepage - FW - LG5-5557 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - HR - LG5-5557 - LG5-6190') {
                    build job: 'UIUX - Homepage - HR - LG5-5557 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Low Rider - LG5-5557 - LG5-6190') {
                    build job: 'UIUX - Homepage - Low Rider - LG5-5557 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Super Street - LG5-5557 - LG5-6190') {
                    build job: 'UIUX - Homepage - Super Street - LG5-5557 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Truck Trend - LG5-5557 - LG5-6190') {
                    build job: 'UIUX - Homepage - Truck Trend - LG5-5557 - LG5-6190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - The Future - LG5-5591') {
                    build job: 'UIUX - The Future - LG5-5591', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${mainSTFolder}/${envFolders[index]}/1e - Smoke Test Set 5") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Articles - CTT - LG5-6260') {
                    build job: 'Ads - Articles - CTT - LG5-6260', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Articles - Features - LG5-6263') {
                    build job: 'Ads - Articles - Features - LG5-6263', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Articles - News - LG5-6261') {
                    build job: 'Ads - Articles - News - LG5-6261', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Articles - Reviews - LG5-6262') {
                    build job: 'Ads - Articles - Reviews - LG5-6262', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Buyers Guide - Index Page - LG5-6264') {
                    build job: 'Ads - Buyers Guide - Index Page - LG5-6264', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Buyers Guide - Make - LG5-6265') {
                    build job: 'Ads - Buyers Guide - Make - LG5-6265', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Buyers Guide - Make Model - LG5-6266') {
                    build job: 'Ads - Buyers Guide - Make Model - LG5-6266', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Buyers Guide - Make Model - LG5-6266') {
                    build job: 'Ads - Buyers Guide - Make Model - LG5-6266', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Buyers Guide - Make Model Year - LG5-6267') {
                    build job: 'Ads - Buyers Guide - Make Model Year - LG5-6267', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Ads - Buyers Guide - Ranking Page - LG5-6269') {
                    build job: 'Ads - Buyers Guide - Ranking Page - LG5-6269', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${mainSTFolder}/${envFolders[index]}/1f - Smoke Test Set 6") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- Articles - Features') {
                    build job: 'mParticle- Articles - Features', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- Articles - News') {
                    build job: 'mParticle- Articles - News', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- Articles - Reviews') {
                    build job: 'mParticle- Articles - Reviews', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- BG - Buying Guide Page') {
                    build job: 'mParticle- BG - Buying Guide Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- BG - Index Page') {
                    build job: 'mParticle- BG - Index Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- BG - Make Index Page') {
                    build job: 'mParticle- BG - Make Index Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- BG - Make Model Page') {
                    build job: 'mParticle- BG - Make Model Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- BG - Price Page') {
                    build job: 'mParticle- BG - Price Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- BG - Ranking Page') {
                    build job: 'mParticle- BG - Ranking Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- CTT') {
                    build job: 'mParticle- CTT', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- HP') {
                    build job: 'mParticle- HP', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- YMM - Discontinued Make') {
                    build job: 'mParticle- YMM - Discontinued Make', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- YMM - Discontinued Make Model') {
                    build job: 'mParticle- YMM - Discontinued Make Model', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- YMM - Discontinued Make Model Year') {
                    build job: 'mParticle- YMM - Discontinued Make Model Year', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle- YMM - Year Make Model Page') {
                    build job: 'mParticle- YMM - Year Make Model Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    index += 1;
}

// 0 Jobs Creation
index = 0;
envFolders.each{ env ->
    job("${mainSTFolder}/${env}/0 - Build this Job to run all Smoke Tests") {
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
                trigger("1a - Smoke Test Set 1,1b - Smoke Test Set 2,1c - Smoke Test Set 3,1d - Smoke Test Set 4,1e - Smoke Test Set 5,1f - Smoke Test Set 6") {
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
