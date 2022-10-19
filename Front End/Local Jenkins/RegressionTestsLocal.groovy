def servers = ['carbon-stg', 'carbon-preprod-akamai', 'prod', 'pod1-stg', 'pod2-stg', 'pod3-stg', 'carbon-dev'];
def mainFolder = "QA-Selenium/ONO";
def regressionFolder = "${mainFolder}/Front End";
def envFolders = [
  'Regression - Staging', 
  'Regression - Preprod Behind Akamai', 
  'Regression - Production',
  'Regression - Pod1 - Staging',
  'Regression - Pod2 - Staging',
  'Regression - Pod3 - Staging',
  'Regression - Dev', 
];

// Main Folders
folder("${mainFolder}"){
  description('ONO')
  displayName('ONO')
}
folder("${regressionFolder}"){
  description('Front End')
  displayName('Front End')
}

// Regression Main Jobs
def index = 0;
def tool = "'''";
def jobslist = [
  [ 
    jobTitle: "ADS - Make Bodystyle - AdOps Requirements - CSIN-178", 
    COMPONENT: "MT/Make+Bodystyle/ADS/", 
    TESTCASE: "AdsOpsRequirements",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - AMP Articles - News - Article Global Elements - LG5-5313", 
    COMPONENT: "MT/AMPArticles/ANALYTICS/Adobe/NewsArticles", 
    TESTCASE: "ArticleGlobalElementsAMP",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - AMP Articles - News Articles List - LG5-5314", 
    COMPONENT: "MT/AMPArticles/ANALYTICS/Adobe/NewsArticles/", 
    TESTCASE: "MTNewsArticlesAMPList",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Articles - Blockquote - LG5-338", 
    COMPONENT: "MT/Article/ANALYTICS/Adobe", 
    TESTCASE: "ArticleBlockquote",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Articles - Elements - LG5-711", 
    COMPONENT: "MT/Article/ANALYTICS/Adobe", 
    TESTCASE: "ArticlesElements",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Articles - Global Elements - LG5-712", 
    COMPONENT: "MT/Article/ANALYTICS/Adobe", 
    TESTCASE: "ArticleGlobalElements",
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Articles - Hero Image - LG5-335", 
    COMPONENT: "MT/Article/ANALYTICS/Adobe", 
    TESTCASE: "ArticleHeroImage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Articles - Related Articles - LG5-343", 
    COMPONENT: "MT/Article/ANALYTICS/Adobe", 
    TESTCASE: "ArticleRelatedArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Articles - Social Media - LG5-334", 
    COMPONENT: "MT/Article/ANALYTICS/Adobe", 
    TESTCASE: "ArticleSocialMedia", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Articles - Title - LG5-333", 
    COMPONENT: "MT/Article/ANALYTICS/Adobe", 
    TESTCASE: "ArticleTitle", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - BG - Buying Guides - LG5-4605 - LG5-3179", 
    COMPONENT: "MT/BuyersGuide/ANALYTICS/Adobe", 
    TESTCASE: "BGBuyingGuideList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - BG - Index Page - LG5-4605 - LG5-2823", 
    COMPONENT: "MT/BuyersGuide/ANALYTICS/Adobe", 
    TESTCASE: "BGIndexPage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - BG - Price Index - LG5-4605 - LG5-2840", 
    COMPONENT: "MT/BuyersGuide/ANALYTICS/Adobe", 
    TESTCASE: "BGPriceIndexList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - BG - Ranking Index - LG5-4605 - LG5-2833", 
    COMPONENT: "MT/BuyersGuide/ANALYTICS/Adobe", 
    TESTCASE: "BGRankingIndexList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - CTT - LG5-5312", 
    COMPONENT: "MT/CTT/ANALYTICS/Adobe", 
    TESTCASE: "CTTArticleList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"Analytics - Homepage - LG5-263", 
    COMPONENT: "MT/Homepage/ANALYTICS/Adobe/", 
    TESTCASE: "MTHomePage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Make Bodystyle - BI Requirements - CSIN-85", 
    COMPONENT: "MT/Make+Bodystyle/ANALYTICS/", 
    TESTCASE: "BIRequirements", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - YMM - Discontinued Make List - LG5-4605 - LG5-5298", 
    COMPONENT: "MT/YMM/ANALYTICS/Adobe/", 
    TESTCASE: "DiscontinuedMakeList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - YMM - Discontinued Make Model List - LG5-4605 - LG5-5298", 
    COMPONENT: "MT/YMM/ANALYTICS/Adobe/", 
    TESTCASE: "DiscontinuedMakeModelList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - YMM - Discontinued Make Model Year - LG5-4605 - LG5-5298", 
    COMPONENT: "MT/YMM/ANALYTICS/Adobe/", 
    TESTCASE: "DiscontinuedMakeModelYear", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - YMM - Make Index List - LG5-4605 - LG5-5298", 
    COMPONENT: "MT/YMM/ANALYTICS/Adobe/", 
    TESTCASE: "MakeIndexList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - YMM - Make Model List - LG5-4605 - LG5-5298", 
    COMPONENT: "MT/YMM/ANALYTICS/Adobe/", 
    TESTCASE: "MakeModelList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - YMM - Make Model Year List - LG5-4605 - LG5-5298", 
    COMPONENT: "MT/YMM/ANALYTICS/Adobe/", 
    TESTCASE: "YearMakeModelList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "SEO - Make Bodystyle - SEO Requirements - CSIN-161", 
    COMPONENT: "MT/Make+Bodystyle/SEO/", 
    TESTCASE: "SEORequirements", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "SEO - Make Bodystyle - SEO Textbox - CSIN-103", 
    COMPONENT: "MT/Make+Bodystyle/SEO/", 
    TESTCASE: "SEOTextBox", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "SEO - News - Articles - LG5-4673", 
    COMPONENT: "MT/Article/SEO/", 
    TESTCASE: "SEONewsArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "SEO - Reviews - Articles - LG5-4673", 
    COMPONENT: "MT/Article/SEO/", 
    TESTCASE: "SEOReviewsArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Blockquote - LG5-202", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXBlockquote", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Email Signup Footer - LG5-209", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXEmailSignUpFooter", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Email Signup Right Column - LG5-261", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXEmailSignUpRightRail", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"UIUX - Articles - Footer Section - LG5-4425", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXFooter", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Header - LG5-189", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXHeader", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Hero Image - LG5-192", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXHeroImage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Inline Image - LG5-200", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXInlineImage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Inline Video - LG5-216", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXInlineVideo", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Photo Gallery - LG5-201", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXPhotoGallery", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"UIUX - Articles - Related Articles- LG5-208", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXRelatedArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Social Media - LG5-191", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXSocialMedia", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"UIUX - Articles - Text - LG5-195", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXText", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Articles - Title - LG5-190", 
    COMPONENT: "MT/Article/UIUX", 
    TESTCASE: "UIUXTitle", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Top Ranking Vehicle By Make - LG5-5495", 
    COMPONENT: "MT/BuyersGuide/UIUX", 
    TESTCASE: "UIUXTopRankingVehicleByMake", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Top Ten YMM - LG5-5328 - LG5-2024", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXYMMTopTen", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Vehicle Body Types - LG5-5585", 
    COMPONENT: "MT/BuyersGuide/UIUX", 
    TESTCASE: "UIUXVehicleBodyTypes", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Vehicle Ranking Class - LG5-5546", 
    COMPONENT: "MT/BuyersGuide/UIUX", 
    TESTCASE: "UIUXVehicleRankingClasses", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Breadcrumb - LG5-1732", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXBreadcrumb", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Collage - LG5-2234", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXCollage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"UIUX - BG - YMM - Cost To Own - LG5-1730", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXYMM-CostToOwn", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Hero - LG5-2025", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXHero", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Hero Pricing - LG5-2035", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXHeroPricing", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Hero Trim - LG5-2031", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXHeroTrim", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Image Gallery - LG5-2039", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXYMM-ImageGallery", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - MTScore - LG5-2027", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXMTScore", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"UIUX - BG - YMM - News and Reviews - LG5-2034", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXNewsReviews", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Overview - LG5-1728", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXOverview", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"UIUX - BG - YMM - Sign Up Modal - LG5-2038", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXSignUpModal", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Spec Table - LG5-2030", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXYMMSpecTable", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Top Competitors - LG5-2032", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXTopCompetitors", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM - Year List - LG5-2024", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXYearList", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - YMM From Find Your Car Module- LG5-5329", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXYMMFromFindYourCarModule", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Ads Position - LG5-836", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageAdsPosition", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Email Sign Up Right Column - LG5-841", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageEmailSignUpRightColumn", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Find Your Next Car - LG5-2238", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageFindYourNextCar", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Footer - LG5-4425", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageFooter", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle:"UIUX - Homepage - Header - LG5-5331", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageHeader", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Hero - LG5-827 - LG5-828", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageHero", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Hot Reads - LG5-838", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageHotReads", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Newsletter - LG5-835", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageNewsletter", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - River - LG5-831", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageRiver", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Homepage - Trending Videos - LG5-840", 
    COMPONENT: "MT/Homepage/UIUX/", 
    TESTCASE: "UIUXhomepageTrendingVideos", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Makes Logo - CSIN-128", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXMakeLogos", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Not Rated - CSIN-108", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXNotRated", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Photo Mosaic Gallery - CSIN-106", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXPhotoMosaicGallery", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Related Content- CSIN-126", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXRelatedArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Sticky Header - CSIN-110", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXStickyHeader", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Vehicle Card - CSIN-109", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXVehicleCards", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Video Player - CSIN-140 - 1 video - no carousel", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXVideoPlayer", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Video Player - CSIN-140 - 1 video - no carousel", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXVideoPlayer", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
    MAKE: "nissan", 
    BODYSTYLE: "sedan",
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Video Player - CSIN-140 - 5 Videos + Carousel", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXVideoPlayer", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - Make Bodystyle - Video Player - CSIN-140 - No Video", 
    COMPONENT: "MT/Make+Bodystyle/UIUX/", 
    TESTCASE: "UIUXVideoPlayer", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MAKE: "kia", 
    BODYSTYLE: "van",
  ],
  [
    jobTitle: "UIUX - YMM - Price Range - LG5-5535", 
    COMPONENT: "MT/YMM/UIUX", 
    TESTCASE: "UIUXPriceRange", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - YMM - Ratings And Features - LG5-5498", 
    COMPONENT: "MT/BuyersGuide/UIUX", 
    TESTCASE: "UIUXTopRankingVehicleByMake", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  // Analytics Verticals
  [
    jobTitle: "Analytics - Features - Buying Advice - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/BuyingAdvice/BuyingAdviceURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Buying Advice AMP - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/BuyingAdvice/BuyingAdviceURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Car Lists - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/CarLists/CarListsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Car Lists AMP - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/CarLists/CarListsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Car Profiles - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/CarProfiles/CarProfilesURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Car Profiles AMP - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/CarProfiles/CarProfilesURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Features - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/Features/FeaturesURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Features AMP - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/Features/FeaturesURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Lifestyle - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/LifeStyle/LifestyleURLs.json", 
    AMP: "", 
    PAGETYPE: "lowrider", 
  ],
  [
    jobTitle: "Analytics - Features - Lifestyle AMP - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/LifeStyle/LifestyleURLs.json", 
    AMP: "Y", 
    PAGETYPE: "lowrider", 
  ],
  [
    jobTitle: "Analytics - Features - Opinion - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/Opinion/OpinionURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Features - Opinion AMP - LG5-5275",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Features/Opinion/OpinionURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Auctions - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/Auctions/AuctionsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Auctions AMP - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/Auctions/AuctionsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Awards - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/Awards/AwardsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Awards AMP - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/Awards/AwardsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Future Cars - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/FutureCars/FutureCarsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Future Cars AMP - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/FutureCars/FutureCarsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - News - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/News/NewsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - News AMP - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/News/NewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Spy Photos - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/SpyPhotos/SpyPhotosURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - News - Spy Photos AMP - LG5 - 5282",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/News/SpyPhotos/SpyPhotosURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Comparison - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/ComparisonTests/ComparisonTestsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Comparison AMP - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/ComparisonTests/ComparisonTestsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - First Drives - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/FirstDrives/FirstDriveURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - First Drives AMP - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/FirstDrives/FirstDriveURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - First Test - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/FirstTest/FirstTestURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - First Test AMP - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/FirstTest/FirstTestURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Interior - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/Interior/InteriorURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Interior AMP - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/Interior/InteriorURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Long-Term - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/Long-Term/LongTermURLs.json", 
    AMP: "",
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Long-Term AMP - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/Long-Term/LongTermURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Reviews - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/Reviews/ReviewsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - Reviews - Reviews AMP - LG5-5284",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/Reviews/Reviews/ReviewsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - VehicleGenres - ClassicCars - LG5-5287",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/VehicleGenres/ClassicCars/ClassicCarsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - VehicleGenres - ClassicCars AMP - LG5-5287",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/VehicleGenres/ClassicCars/ClassicCarsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - VehicleGenres - Concept Cars - LG5-5287",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/VehicleGenres/ConceptCars/ConceptCarsURLs.json", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "Analytics - VehicleGenres - Concept Cars AMP - LG5-5287",
    COMPONENT: "MT/ExtendedAnalytics", 
    TESTCASE: "MTVertical", 
    URLFILE: "data/MotorTrend/VehicleGenres/ConceptCars/ConceptCarsURLs.json", 
    AMP: "Y", 
    PAGETYPE: "", 
  ],
  // CSIN Car Compare tests
  [
    jobTitle: "UIUX - BG - Car Compare - Buyer's Guide Index - CSIN-271 - CSIN-279 - CSIN-288",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTBGIndex", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Car Match - CSIN-277 - CSIN-284 - CSIN-258",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTCarMatch", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Make+Body Style - CSIN-271 - CSIN-483",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTMakeBodyStyle", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Make Index - CSIN-271 - CSIN-480",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTMakeIndex", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Make Model - CSIN-271 - CSIN-481",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTMakeModel", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Make Model Year - CSIN-271 - CSIN-482",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTMakeModelYear", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Price Page - CSIN-271 - CSIN-478",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTPricePage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - RankingPage - CSIN-271 - CSIN-478",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTRankingPage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Sticky Modal Error Handling- CSIN-292",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTStickyModalError", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Sticky Modal Persistence - CSIN-540",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTStickyModalPersistence", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "", 
  ],
  [
    jobTitle: "UIUX - BG - Car Compare - Sticky Modal UI and Functionality - CSIN-279 - CSIN-284 - CSIN-288 - CSIN-363 - CSIN-438",
    COMPONENT: "MT/BuyersGuide/UIUX/", 
    TESTCASE: "UIUXCTStickyModalUI", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  // mParticle Jobs
  [
    jobTitle: "mParticle - HP - Screen View",
    COMPONENT: "MT/MPArticle/HomePage", 
    TESTCASE: "MPArticleHomePage", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "mParticle - HP - Newsletter Registration Event",
    COMPONENT: "MT/MPArticle/HomePage", 
    TESTCASE: "MPArticleNewsletter", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "mParticle - Articles - Features",
    COMPONENT: "MT/MPArticle/Articles", 
    TESTCASE: "MPArticleFeatureArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "mParticle - Articles - News",
    COMPONENT: "MT/MPArticle/Articles", 
    TESTCASE: "MPArticleNewsArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "mParticle - Articles - Reviews",
    COMPONENT: "MT/MPArticle/Articles", 
    TESTCASE: "MPArticleReviewsArticles", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
  [
    jobTitle: "mParticle - BG - Index Page",
    COMPONENT: "MT/MPArticle/BG", 
    TESTCASE: "MPArticleBGIndex", 
    URLFILE: "", 
    AMP: "", 
    PAGETYPE: "",
    MOBILE: "",  
  ],
]

envFolders.each{ env ->
  // Front End Regression Tests Folders
  folder("${regressionFolder}/${env}"){
    description("${env}")
    displayName("${env}")
  }
  // Jobs Creation
  jobslist.eachWithIndex{ item, i ->
    pipelineJob("${regressionFolder}/${env}/${item["jobTitle"]}") {
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
        stringParam('MOBILE', '', '')
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
    pipelineJob("${regressionFolder}/${envFolders[index]}/1a - Regression Set") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('ADS - Make Bodystyle - AdOps Requirements - CSIN-178') {
                    build job: 'ADS - Make Bodystyle - AdOps Requirements - CSIN-178', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - AMP Articles - News - Article Global Elements - LG5-5313') {
                    build job: 'Analytics - AMP Articles - News - Article Global Elements - LG5-5313', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - AMP Articles - News Articles List - LG5-5314') {
                    build job: 'Analytics - AMP Articles - News Articles List - LG5-5314', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Articles - Blockquote - LG5-338') {
                    build job: 'Analytics - Articles - Blockquote - LG5-338', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Articles - Elements - LG5-711') {
                    build job: 'Analytics - Articles - Elements - LG5-711', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Articles - Global Elements - LG5-712') {
                    build job: 'Analytics - Articles - Global Elements - LG5-712', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Articles - Hero Image - LG5-335') {
                    build job: 'Analytics - Articles - Hero Image - LG5-335', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Articles - Related Articles - LG5-343') {
                    build job: 'Analytics - Articles - Related Articles - LG5-343', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Articles - Social Media - LG5-334') {
                    build job: 'Analytics - Articles - Social Media - LG5-334', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Articles - Title - LG5-333') {
                    build job: 'Analytics - Articles - Title - LG5-333', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG - Buying Guides - LG5-4605 - LG5-3179') {
                    build job: 'Analytics - BG - Buying Guides - LG5-4605 - LG5-3179', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG - Index Page - LG5-4605 - LG5-2823') {
                    build job: 'Analytics - BG - Index Page - LG5-4605 - LG5-2823', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG - Price Index - LG5-4605 - LG5-2840') {
                    build job: 'Analytics - BG - Price Index - LG5-4605 - LG5-2840', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - BG - Ranking Index - LG5-4605 - LG5-2833') {
                    build job: 'Analytics - BG - Ranking Index - LG5-4605 - LG5-2833', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - CTT - LG5-5312') {
                    build job: 'Analytics - CTT - LG5-5312', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Buying Advice AMP - LG5-5275') {
                    build job: 'Analytics - Features - Buying Advice AMP - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Car Lists - LG5-5275') {
                    build job: 'Analytics - Features - Car Lists - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Car Lists AMP - LG5-5275') {
                    build job: 'Analytics - Features - Car Lists AMP - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Car Profiles - LG5-5275') {
                    build job: 'Analytics - Features - Car Profiles - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Car Profiles AMP - LG5-5275') {
                    build job: 'Analytics - Features - Car Profiles AMP - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${regressionFolder}/${envFolders[index]}/1b - Regression Set") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Features - LG5-5275') {
                    build job: 'Analytics - Features - Features - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Features AMP - LG5-5275') {
                    build job: 'Analytics - Features - Features AMP - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Lifestyle - LG5-5275') {
                    build job: 'Analytics - Features - Lifestyle - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Lifestyle AMP - LG5-5275') {
                    build job: 'Analytics - Features - Lifestyle AMP - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Opinion - LG5-5275') {
                    build job: 'Analytics - Features - Opinion - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Features - Opinion AMP - LG5-5275') {
                    build job: 'Analytics - Features - Opinion AMP - LG5-5275', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Homepage - LG5-263') {
                    build job: 'Analytics - Homepage - LG5-263', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Make Bodystyle - BI Requirements - CSIN-85') {
                    build job: 'Analytics - Make Bodystyle - BI Requirements - CSIN-85', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Auctions - LG5 - 5282') {
                    build job: 'Analytics - News - Auctions - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Auctions AMP - LG5 - 5282') {
                    build job: 'Analytics - News - Auctions AMP - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Awards - LG5 - 5282') {
                    build job: 'Analytics - News - Awards - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Awards AMP - LG5 - 5282') {
                    build job: 'Analytics - News - Awards AMP - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Future Cars - LG5 - 5282') {
                    build job: 'Analytics - News - Future Cars - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Future Cars AMP - LG5 - 5282') {
                    build job: 'Analytics - News - Future Cars AMP - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - News - LG5 - 5282') {
                    build job: 'Analytics - News - News - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - News AMP - LG5 - 5282') {
                    build job: 'Analytics - News - News AMP - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Spy Photos - LG5 - 5282') {
                    build job: 'Analytics - News - Spy Photos - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - News - Spy Photos AMP - LG5 - 5282') {
                    build job: 'Analytics - News - Spy Photos AMP - LG5 - 5282', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Comparison - LG5-5284') {
                    build job: 'Analytics - Reviews - Comparison - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Comparison AMP - LG5-5284') {
                    build job: 'Analytics - Reviews - Comparison AMP - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle - Articles - Features') {
                    build job: 'mParticle - Articles - Features', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle - Articles - News') {
                    build job: 'mParticle - Articles - News', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle - Articles - Reviews') {
                    build job: 'mParticle - Articles - Reviews', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle - BG - Index Page') {
                    build job: 'mParticle - BG - Index Page', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${regressionFolder}/${envFolders[index]}/1c - Regression Set") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Comparison AMP - LG5-5284') {
                    build job: 'Analytics - Reviews - Comparison AMP - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - First Drives - LG5-5284') {
                    build job: 'Analytics - Reviews - First Drives - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - First Drives AMP - LG5-5284') {
                    build job: 'Analytics - Reviews - First Drives AMP - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - First Test - LG5-5284') {
                    build job: 'Analytics - Reviews - First Test - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - First Test AMP - LG5-5284') {
                    build job: 'Analytics - Reviews - First Test AMP - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Interior - LG5-5284') {
                    build job: 'Analytics - Reviews - Interior - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Interior AMP - LG5-5284') {
                    build job: 'Analytics - Reviews - Interior AMP - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Long-Term - LG5-5284') {
                    build job: 'Analytics - Reviews - Long-Term - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Long-Term AMP - LG5-5284') {
                    build job: 'Analytics - Reviews - Long-Term AMP - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Reviews - LG5-5284') {
                    build job: 'Analytics - Reviews - Reviews - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - Reviews - Reviews AMP - LG5-5284') {
                    build job: 'Analytics - Reviews - Reviews AMP - LG5-5284', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - VehicleGenres - ClassicCars - LG5-5287') {
                    build job: 'Analytics - VehicleGenres - ClassicCars - LG5-5287', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - VehicleGenres - ClassicCars AMP - LG5-5287') {
                    build job: 'Analytics - VehicleGenres - ClassicCars AMP - LG5-5287', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - VehicleGenres - Concept Cars - LG5-5287') {
                    build job: 'Analytics - VehicleGenres - Concept Cars - LG5-5287', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - VehicleGenres - Concept Cars AMP - LG5-5287') {
                    build job: 'Analytics - VehicleGenres - Concept Cars AMP - LG5-5287', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Discontinued Make List - LG5-4605 - LG5-5298') {
                    build job: 'Analytics - YMM - Discontinued Make List - LG5-4605 - LG5-5298', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Discontinued Make Model List - LG5-4605 - LG5-5298') {
                    build job: 'Analytics - YMM - Discontinued Make Model List - LG5-4605 - LG5-5298', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Discontinued Make Model Year - LG5-4605 - LG5-5298') {
                    build job: 'Analytics - YMM - Discontinued Make Model Year - LG5-4605 - LG5-5298', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Make Index List - LG5-4605 - LG5-5298') {
                    build job: 'Analytics - YMM - Make Index List - LG5-4605 - LG5-5298', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Make Model List - LG5-4605 - LG5-5298') {
                    build job: 'Analytics - YMM - Make Model List - LG5-4605 - LG5-5298', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('Analytics - YMM - Make Model Year List - LG5-4605 - LG5-5298') {
                    build job: 'Analytics - YMM - Make Model Year List - LG5-4605 - LG5-5298', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${regressionFolder}/${envFolders[index]}/1d - Regression Set") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - Make Bodystyle - SEO Requirements - CSIN-161') {
                    build job: 'SEO - Make Bodystyle - SEO Requirements - CSIN-161', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - Make Bodystyle - SEO Textbox - CSIN-103') {
                    build job: 'SEO - Make Bodystyle - SEO Textbox - CSIN-103', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - News - Articles - LG5-4673') {
                    build job: 'SEO - News - Articles - LG5-4673', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('SEO - Reviews - Articles - LG5-4673') {
                    build job: 'SEO - Reviews - Articles - LG5-4673', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Blockquote - LG5-202') {
                    build job: 'UIUX - Articles - Blockquote - LG5-202', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Email Signup Footer - LG5-209') {
                    build job: 'UIUX - Articles - Email Signup Footer - LG5-209', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Email Signup Right Column - LG5-261') {
                    build job: 'UIUX - Articles - Email Signup Right Column - LG5-261', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Footer Section - LG5-4425') {
                    build job: 'UIUX - Articles - Footer Section - LG5-4425', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Header - LG5-189') {
                    build job: 'UIUX - Articles - Header - LG5-189', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Hero Image - LG5-192') {
                    build job: 'UIUX - Articles - Hero Image - LG5-192', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Inline Image - LG5-200') {
                    build job: 'UIUX - Articles - Inline Image - LG5-200', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Inline Video - LG5-216') {
                    build job: 'UIUX - Articles - Inline Video - LG5-216', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Photo Gallery - LG5-201') {
                    build job: 'UIUX - Articles - Photo Gallery - LG5-201', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Related Articles- LG5-208') {
                    build job: 'UIUX - Articles - Related Articles- LG5-208', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Social Media - LG5-191') {
                    build job: 'UIUX - Articles - Social Media - LG5-191', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Text - LG5-195') {
                    build job: 'UIUX - Articles - Text - LG5-195', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Articles - Title - LG5-190') {
                    build job: 'UIUX - Articles - Title - LG5-190', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle - HP - Newsletter Registration Event') {
                    build job: 'mParticle - HP - Newsletter Registration Event', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('mParticle - HP - Screen View') {
                    build job: 'mParticle - HP - Screen View', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${regressionFolder}/${envFolders[index]}/1e - Regression Set") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - Top Ranking Vehicle By Make - LG5-5495') {
                    build job: 'UIUX - BG - Top Ranking Vehicle By Make - LG5-5495', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - Top Ten YMM - LG5-5328 - LG5-2024') {
                    build job: 'UIUX - BG - Top Ten YMM - LG5-5328 - LG5-2024', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - Vehicle Body Types - LG5-5585') {
                    build job: 'UIUX - BG - Vehicle Body Types - LG5-5585', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - Vehicle Ranking Class - LG5-5546') {
                    build job: 'UIUX - BG - Vehicle Ranking Class - LG5-5546', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Breadcrumb - LG5-1732') {
                    build job: 'UIUX - BG - YMM - Breadcrumb - LG5-1732', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Collage - LG5-2234') {
                    build job: 'UIUX - BG - YMM - Collage - LG5-2234', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Cost To Own - LG5-1730') {
                    build job: 'UIUX - BG - YMM - Cost To Own - LG5-1730', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Hero - LG5-2025') {
                    build job: 'UIUX - BG - YMM - Hero - LG5-2025', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Hero Pricing - LG5-2035') {
                    build job: 'UIUX - BG - YMM - Hero Pricing - LG5-2035', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Hero Trim - LG5-2031') {
                    build job: 'UIUX - BG - YMM - Hero Trim - LG5-2031', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Image Gallery - LG5-2039') {
                    build job: 'UIUX - BG - YMM - Image Gallery - LG5-2039', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - MTScore - LG5-2027') {
                    build job: 'UIUX - BG - YMM - MTScore - LG5-2027', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - News and Reviews - LG5-2034') {
                    build job: 'UIUX - BG - YMM - News and Reviews - LG5-2034', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Overview - LG5-1728') {
                    build job: 'UIUX - BG - YMM - Overview - LG5-1728', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Sign Up Modal - LG5-2038') {
                    build job: 'UIUX - BG - YMM - Sign Up Modal - LG5-2038', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Spec Table - LG5-2030') {
                    build job: 'UIUX - BG - YMM - Spec Table - LG5-2030', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Top Competitors - LG5-2032') {
                    build job: 'UIUX - BG - YMM - Top Competitors - LG5-2032', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM - Year List - LG5-2024') {
                    build job: 'UIUX - BG - YMM - Year List - LG5-2024', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - BG - YMM From Find Your Car Module- LG5-5329') {
                    build job: 'UIUX - BG - YMM From Find Your Car Module- LG5-5329', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    pipelineJob("${regressionFolder}/${envFolders[index]}/1f - Regression Set") {
      parameters {
        stringParam('SERVER', "${server}", '')
        stringParam('BRANCH', 'main', '')
      }
      definition {
        cps {
          script('''
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: 'carbon-stg', description: '', name: 'SERVER', trim: false), string(defaultValue: 'main', description: '', name: 'BRANCH', trim: false)]), [$class: 'JobLocalConfiguration', changeReasonComment: '']])

            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Ads Position - LG5-836') {
                    build job: 'UIUX - Homepage - Ads Position - LG5-836', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Email Sign Up Right Column - LG5-841') {
                    build job: 'UIUX - Homepage - Email Sign Up Right Column - LG5-841', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Find Your Next Car - LG5-2238') {
                    build job: 'UIUX - Homepage - Find Your Next Car - LG5-2238', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Footer - LG5-4425') {
                    build job: 'UIUX - Homepage - Footer - LG5-4425', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Header - LG5-5331') {
                    build job: 'UIUX - Homepage - Header - LG5-5331', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Hero - LG5-827 - LG5-828') {
                    build job: 'UIUX - Homepage - Hero - LG5-827 - LG5-828', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Hot Reads - LG5-838') {
                    build job: 'UIUX - Homepage - Hot Reads - LG5-838', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Newsletter - LG5-835') {
                    build job: 'UIUX - Homepage - Newsletter - LG5-835', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - River - LG5-831') {
                    build job: 'UIUX - Homepage - River - LG5-831', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Homepage - Trending Videos - LG5-840') {
                    build job: 'UIUX - Homepage - Trending Videos - LG5-840', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Makes Logo - CSIN-128') {
                    build job: 'UIUX - Make Bodystyle - Makes Logo - CSIN-128', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Not Rated - CSIN-108') {
                    build job: 'UIUX - Make Bodystyle - Not Rated - CSIN-108', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Photo Mosaic Gallery - CSIN-106') {
                    build job: 'UIUX - Make Bodystyle - Photo Mosaic Gallery - CSIN-106', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Related Content- CSIN-126') {
                    build job: 'UIUX - Make Bodystyle - Related Content- CSIN-126', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Sticky Header - CSIN-110') {
                    build job: 'UIUX - Make Bodystyle - Sticky Header - CSIN-110', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Vehicle Card - CSIN-109') {
                    build job: 'UIUX - Make Bodystyle - Vehicle Card - CSIN-109', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Video Player - CSIN-140 - 1 video - no carousel') {
                    build job: 'UIUX - Make Bodystyle - Video Player - CSIN-140 - 1 video - no carousel', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Video Player - CSIN-140 - 5 Videos + Carousel') {
                    build job: 'UIUX - Make Bodystyle - Video Player - CSIN-140 - 5 Videos + Carousel', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - Make Bodystyle - Video Player - CSIN-140 - No Video') {
                    build job: 'UIUX - Make Bodystyle - Video Player - CSIN-140 - No Video', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - YMM - Price Range - LG5-5535') {
                    build job: 'UIUX - YMM - Price Range - LG5-5535', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
            catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                stage('UIUX - YMM - Ratings And Features - LG5-5498') {
                    build job: 'UIUX - YMM - Ratings And Features - LG5-5498', parameters: [string(name: 'SERVER', value: String.valueOf(SERVER)), string(name: 'BRANCH', value: String.valueOf(BRANCH))]
                }
            }
          ''' 
          )
        }
      }
    }
    index += 1;
}

// Create 0 Jobs
def setsToRun = "";
def zeroJobIndex = 0;
envFolders.each{ env -> 
  // Main 0 Jobs
  job("${regressionFolder}/${env}/0 - Run all Regression tests") {
    parameters {
          stringParam('BRANCH', 'main', '')
          stringParam('SERVER', "${servers[zeroJobIndex]}", '')
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
        trigger("1a - Regression Set,1b - Regression Set,1c - Regression Set,1d - Regression Set,1e - Regression Set,1f - Regression Set") {
          condition('SUCCESS')
          parameters {
            currentBuild()
          }
        }
      }
    }
  }
  zeroJobIndex += 1;
}