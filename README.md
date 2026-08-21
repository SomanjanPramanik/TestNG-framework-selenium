# CreatioCRM Test Automation Framework

A Java-based **UI + API test automation framework** built with **Selenium WebDriver, TestNG, and REST-assured**, targeting [Creatio CRM](https://www.creatio.com/) (web UI) and the GitHub REST API (API layer). Built to demonstrate a production-style Page Object Model, cross-browser + parallel execution, data-driven testing, custom reporting, and DB validation.

## Key Features

- **Page Object Model with composition, not inheritance** — `Elements` classes hold locators, `Steps` classes hold actions, and `TestPageStepsObjects` wires page objects into the test layer via object composition. Keeps each page independently testable and avoids TestNG/base-class coupling issues.
- **Cross-browser execution** — Chrome, Firefox, and Edge via a factory-style `BasePage.setupBrowser()`, driven entirely by TestNG `<parameter>` values (no code changes to switch browsers).
- **Thread-safe parallel execution** — `WebDriver` is held in a `ThreadLocal`, so the same `CreatioCRME2EParalleltestng.xml` suite can run Chrome/Firefox/Edge concurrently without session bleed.
- **Data-driven testing** — Apache POI reads test data from Excel (`TestData/ExcelFiles/Test Data1.xlsx`) via a TestNG `@DataProvider`, keyed by test method name.
- **API test layer** — REST-assured based `ApiCommons` wraps GET/POST/PUT/PATCH/DELETE with reusable status/body/header/response-time assertions; `RepositoryApiTest` runs a full CRUD lifecycle against the GitHub Repos API (create → verify → update → delete).
- **DB validation layer** — JDBC/PostgreSQL utility (`DBUtils` + `DBreadData`) to query and validate backend data directly, not just through the UI.
- **Custom ExtentReports integration** — `TestListener` (UI) and `ApiTestListener` (API) auto-capture screenshots on failure and attach API response bodies to the report; reports are timestamped per run.
- **Retry-on-failure** — `RetryTest` (`IRetryAnalyzer`) retries a failed test up to 2 times before marking it a genuine failure, reducing flaky-test noise.
- **Externalized config** — all URLs, credentials, DB connection strings, and API tokens live in a git-ignored `Config/config.properties`, never committed to source control.

## Tech Stack

| Layer | Tools |
|---|---|
| Language / Build | Java 17, Maven |
| UI Automation | Selenium WebDriver 4.43 |
| Test Runner | TestNG 7.8 |
| API Testing | REST-assured 6.0, org.json |
| Data | Apache POI (Excel), Apache PDFBox, PostgreSQL JDBC |
| Reporting | ExtentReports 5.1 (Spark reporter) |
| Utilities | Apache Commons IO |

## Architecture

```
Test (TestNG @Test)
   └── extends TestPageStepsObjects   (composes all Steps objects, owns @DataProvider)
          └── extends BasePage        (browser lifecycle: launch / quit, ThreadLocal driver)
                 └── extends Reports  (ExtentReports lifecycle: @BeforeSuite / @AfterSuite)

Steps (action methods, calls WebCommons)
   └── uses  Elements (locators only, @FindBy)

API Test (TestNG @Test)
   └── extends ApiCommons             (REST-assured request builder + assertions)
          └── extends Reports
```

Composition over inheritance was a deliberate choice: Cucumber-style step definitions can't extend other step classes, and even in a plain TestNG framework, keeping `Elements` → `Steps` → composed-into-Test avoids a fragile inheritance chain and keeps each page object unit-testable in isolation.

## Project Structure

```
src/test/java/com/creatio/crm/
├── application/
│   ├── elements/         # Locators only (LoginPageElements, HomepageElement, ...)
│   ├── steps/             # Actions built on WebCommons (LoginPageSteps, SignUpPageSteps, ...)
│   └── tests/
│       ├── TestPageStepsObjects.java   # composes all Steps + Excel DataProvider
│       └── ApplicationTest.java        # UI test scenarios
├── api/
│   ├── pages/RepositoryApiPage.java    # JSON request-body builders
│   └── tests/RepositoryApiTest.java    # GitHub API CRUD test suite
└── framework/
    ├── base/BasePage.java              # browser setup/teardown (ThreadLocal WebDriver)
    ├── web/commons/WebCommons.java     # reusable Selenium actions & waits
    ├── api/commons/ApiCommons.java     # reusable REST-assured request/assert methods
    ├── db/commons/DBreadData.java      # DB query → List<Map<String,String>>
    ├── utilities/                      # DBUtils, ExcelUtils, PdfUtils, PropUtils
    ├── listeners/                      # TestListener, ApiTestListener, RetryTest
    └── reports/Reports.java            # ExtentReports lifecycle

TestRunner/           # TestNG suite XMLs (sequential, parallel, API)
Config/                # config.properties (git-ignored, holds secrets/URLs)
TestData/ExcelFiles/   # data-driven test inputs
```

## Setup

1. Install JDK 17 and Maven.
2. Create `Config/config.properties` in the project root (this file is git-ignored — you must create it locally):

```properties
url=https://www.creatio.com/
Base_url=https://api.github.com

# GitHub API
Bearer_token=token <your_github_pat>
user=<your_github_username>
repository=SampleRepositoryFromAPI
description=Created via REST-assured
private=true

# PostgreSQL (only needed for DB-assertion tests)
db_url=jdbc:postgresql://localhost:5432/<db_name>
db_username=<username>
db_password=<password>
```

3. `mvn clean install -DskipTests` to pull dependencies.

## Running Tests

```bash
# Sequential UI suite (Chrome)
mvn test -DsuiteFile=TestRunner/CreatioCRME2Etestng.xml

# Parallel cross-browser UI suite (Chrome + Firefox + Edge, 5 threads each)
mvn test -DsuiteFile=TestRunner/CreatioCRME2EParalleltestng.xml

# GitHub REST API suite
mvn test -DsuiteFile=TestRunner/GitHubRepositoryApiTesttestng.xml
```

Reports are generated per run in `Reports/*.html` (ExtentReports) and `test-output/` (native TestNG reports). Failure screenshots are saved to `Screenshots/`.

## Test Coverage (current)

- **UI (`ApplicationTest`)** — 8 scenarios: login page UI, cookie consent dialog + tabs, cookie-popup → privacy policy navigation, password recovery flow, cookie acceptance dismissal, data-driven sign-up, valid login, invalid login.
- **API (`RepositoryApiTest`)** — 9 scenarios covering the full repository lifecycle: pre-condition check, create, verify creation, get, update, verify update, delete, verify deletion, and negative/error-path checks.

## Design Talking Points (for interview)

- **Why composition over inheritance for step objects** — avoids deep, fragile class hierarchies and mirrors the constraint Cucumber enforces on step definitions, so the pattern transfers directly to a BDD framework.
- **Why ThreadLocal for the driver** — makes true parallel cross-browser execution safe without each thread stepping on another's session.
- **Why a retry analyzer instead of just re-running failed suites manually** — cuts down noise from transient UI flakiness (network/animation timing) without masking genuine failures (capped at 2 retries).
- **Why API + DB layers alongside UI** — UI-only automation can't catch backend data corruption; this framework can create data via API, verify it through the UI, and cross-check the database directly — a full-stack coverage story.

## Possible Next Steps

- Add GitHub Actions CI to run the API suite on every push (no browser dependency, fastest feedback loop).
- Externalize the retry count and thread count into `config.properties`.
- Add Allure or keep ExtentReports but publish it as a GitHub Pages artifact per CI run.
