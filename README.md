Selenium Healenium AI Framework
AI-powered UI Test Automation with Self-Healing Locators

This project is a modern, enterprise-grade Selenium test automation framework enhanced with AI-driven Healenium self-healing locators, Dockerized backend services, ExtentReports v5 reporting, and a clean TestNG architecture.

This repo is designed to showcase senior-level automation engineering, framework design expertise, and practical use of AI in UI testing.

🌟 Key Features
🤖 AI-Driven Self-Healing Locators (Healenium)

Healenium automatically repairs broken Selenium locators at runtime using machine-learning–based selector imitation.

Reduces flaky tests

Saves time refactoring locators

Records historical locator paths

Provides auto-healing API services (Docker)

Modern Selenium 4 + Java 17 Architecture

Page Object Model (POM)

PageBase inheritance

Centralized Selenium utilities

Explicit waits wrapper

Java 17 (records, switch patterns, clean code)

🔥 Custom Built Utilities

DriverManager (lifecycle management)

PageManager (lazy-loaded pages)

Waits (explicit wait helpers)

SeleniumMethods (click, type, jsClick, hover, etc.)

ScreenshotUtil (captures screenshots on failure)

📊 Beautiful ExtentReports v5 HTML Reporting

Test summary

Step logging

Clickable screenshots on failure

Dark theme optional

Fully standalone HTML report

🐳 Dockerized Healenium Backend

Runs three containers:

Healenium backend service

Selector imitator AI service

Postgres database

Start with:

docker-compose -f docker-compose-web.yaml up -d

🎯 TestNG Power

TestNG annotations

DataProviders with external JSON test data

TestNG suite XML integration

Parallelizable architecture

Setup Instructions
1 Clone the Repository
git clone https://github.com/yourusername/selenium-healenium-ai-framework.git

2 Install Java 17

Verify:

java -version

3 Start Healenium

From the Healenium toolkit folder:

docker-compose -f docker-compose-web.yaml up -d


Validate:

http://localhost:7878/healenium
 → should return JSON “status: UP”

http://localhost:8000/docs
 → selector-imitator Swagger UI

4 Run Tests
mvn clean test

ExtentReports Output

After test execution, the report is generated here:

/reports/ExtentReport.html


Open it in your browser to view:

Pass / fail summary

Detailed steps

Exception logs

Screenshots

Sample Test (Readable & Fluent)

@Test(dataProvider = "dataProvider")
public void testBasicSearch(String location, String make, String model) {
    homepage
            .clickOnSelectLocation()
            .fillInSearchField(location)
            .selectSearchResult(location)
            .clickOnSelectMake()
            .clickOnSelectModel()
            .clickOnSearchButton()
            .verifyNavigationToInventorySearchPageSuccessful()
            .verifySearchSuccessful(make, model);
}


Readable. Fluent. Professional.

🧠 AI + Healenium Integration Highlights

All tests run through SelfHealingDriver

Broken locators are “healed” using stored historical paths

Selector Imitator uses AI to match changed DOM structures

Healed locators are stored and reused for future runs

Reduces flaky failures dramatically

Roadmap

Add Allure reporting (parallel with Extent)

Add GitHub Actions CI pipeline

Add Dockerized Selenium Grid support

Add element highlighting during interactions

Add step-logging to Extent from SeleniumMethods

Build test coverage around VIP and FAQ pages

Contributions

Pull requests are welcome.
Issues and feature suggestions encouraged.

Author

Syn H. Lee
Test Automation Engineer
Lawrenceville, Georgia

Passionate about:

Automation

AI-enhanced testing

Performance testing

Framework design

🦁 One Love & Maximum Respect

This repo is built with passion, pride, and a drive for excellence.

If this framework inspires you — star the repo! ⭐
