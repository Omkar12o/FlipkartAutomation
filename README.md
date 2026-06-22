# Flipkart End-to-End Test Automation Framework

An industry-style automation framework that tests core e-commerce flows on [Flipkart](https://www.flipkart.com) using **Selenium WebDriver, Cucumber (BDD), TestNG, Maven, and the Page Object Model**, with HTML reporting (Extent Reports) and CI integration (GitHub Actions).

## Tech Stack

| Layer | Tool |
|---|---|
| Language | Java 11 |
| Browser Automation | Selenium WebDriver 4.20 |
| BDD Framework | Cucumber 7.18 |
| Test Runner | TestNG 7.10 |
| Build Tool | Maven |
| Design Pattern | Page Object Model (POM) |
| Driver Management | WebDriverManager (auto-resolves browser driver version) |
| Reporting | ExtentReports (HTML) + Cucumber HTML/JSON reports |
| Logging | Log4j2 |
| CI/CD | GitHub Actions |

## What This Project Tests

- **Search** — valid product search, multiple product types (data-driven), invalid/empty search results
- **Sort & Filter** — price low-to-high, high-to-low, rating filter
- **Cart** — add to cart, remove from cart, verify cart contents match added product
- **Login** — mobile number validation (valid format, invalid format, empty, special characters)

This covers the core flows an interviewer expects from an e-commerce automation suite: a happy path, a negative path, and data-driven coverage for each major feature.

## Project Structure

```
FlipkartAutomation/
├── src/main/java/com/flipkart/
│   ├── pages/              # Page Object classes (HomePage, SearchResultsPage, ProductPage, CartPage, LoginPage)
│   └── utils/               # DriverManager, ConfigReader, ExtentReportManager
├── src/test/java/com/flipkart/
│   ├── stepdefinitions/      # Cucumber step definitions
│   ├── hooks/                # Before/After hooks, screenshot-on-failure
│   └── runner/                # TestNG-Cucumber runner
├── src/test/resources/
│   ├── features/              # .feature files (Gherkin scenarios)
│   ├── config/config.properties
│   ├── cucumber.properties
│   └── log4j2.xml
├── .github/workflows/maven-tests.yml   # CI pipeline
├── testng.xml
└── pom.xml
```

## How to Run

### Prerequisites
- Java 11+
- Maven
- Google Chrome installed

### Setup
```bash
git clone <your-repo-url>
cd FlipkartAutomation
mvn clean install
```

### Run all tests
```bash
mvn test
```

### Run a specific tag (e.g. only smoke tests)
Edit `src/test/java/com/flipkart/runner/TestRunner.java` and add to `@CucumberOptions`:
```java
tags = "@smoke"
```
or pass it via command line:
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### Run headless (e.g. in CI)
```bash
mvn test -Dheadless=true
```

## Reports

After execution, two reports are generated in `test-output/`:
- `ExtentReport_<timestamp>.html` — rich HTML report with pass/fail status, screenshots on failure
- `cucumber-report.html` — Cucumber's native BDD report showing Gherkin steps and results

Screenshots are automatically captured and attached for any failed step.

## Key Design Decisions

- **Page Object Model** — every page's locators and actions live in one class, so if Flipkart changes a CSS class, only one file needs updating, not every test.
- **ThreadLocal WebDriver** — supports safe parallel execution across multiple Cucumber scenarios.
- **WebDriverManager** — automatically downloads the correct ChromeDriver binary that matches the installed Chrome version, eliminating the classic `SessionNotCreatedException`/WebSocket 403 driver-mismatch errors.
- **`--remote-allow-origins=*`** — a required Chrome flag for Chrome 111+ that fixes DevTools WebSocket connection refusals during driver startup.
- **Config externalized** — browser, URL, timeouts, and headless mode are all driven from `config.properties`, not hardcoded.

## Notes on Flipkart-Specific Locators

Flipkart frequently rotates its CSS class names (common with large e-commerce sites to prevent scraping). Locators in this project use a combination of multiple selector fallbacks (e.g. `div._1AtVbE, div._4ddWXP, div.cPHDOP`) to stay more resilient, but if Flipkart updates its DOM, locators in the `pages` package may need refreshing. This is normal maintenance for any real-world automation suite testing a live third-party site — worth mentioning in an interview as a demonstration of understanding flaky/brittle test causes.

## Author

Omkar Mohite — QA Engineer
