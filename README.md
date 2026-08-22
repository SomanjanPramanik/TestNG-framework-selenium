# CreatioCRM Test Automation Framework

A Java-based **UI + API + Performance test automation framework** built with **Selenium WebDriver, TestNG, REST-assured, and Apache JMeter**, targeting [Creatio CRM](https://www.creatio.com/) (web UI) and the GitHub REST API (API + performance layers). Built to demonstrate a production-style Page Object Model, cross-browser + parallel execution, data-driven testing, custom reporting, DB validation, and programmatic JMeter performance testing.

## Key Features

- **Page Object Model with composition, not inheritance** — `Elements` classes hold locators, `Steps` classes hold actions, and `TestPageStepsObjects` wires page objects into the test layer via object composition. Keeps each page independently testable and avoids TestNG/base-class coupling issues.
- **Cross-browser execution** — Chrome, Firefox, and Edge via a factory-style `BasePage.setupBrowser()`, driven entirely by TestNG `<parameter>` values (no code changes to switch browsers).
- **Thread-safe parallel execution** — `WebDriver` is held in a `ThreadLocal`, so the same `CreatioCRME2EParalleltestng.xml` suite can run Chrome/Firefox/Edge concurrently without session bleed.
- **Data-driven testing** — Apache POI reads test data from Excel (`TestData/ExcelFiles/Test Data1.xlsx`) via a TestNG `@DataProvider`, keyed by test method name.
- **API test layer** — REST-assured based `ApiCommons` wraps GET/POST/PUT/PATCH/DELETE with reusable status/body/header/response-time assertions; `FunctionalRepositoryApiTest` runs a full CRUD lifecycle against the GitHub Repos API (create → verify → update → delete).
- **Performance test layer** — `JMeterCommons` drives Apache JMeter 5.6.3 programmatically via its Java API (no external JMeter install or CLI required): loads a `.jmx` test plan, executes it through `StandardJMeterEngine`, streams results to a timestamped CSV via `ResultCollector`, and generates a full HTML dashboard report (APDEX, throughput, response times, error breakdown) via `ReportGenerator`. Each run produces its own timestamped CSV + HTML report folder, so historical runs are never overwritten.
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
| Performance Testing | Apache JMeter 5.6.3 (Core, HTTP, Components — embedded via Java API) |
| Data | Apache POI (Excel), Apache PDFBox, PostgreSQL JDBC |
| Reporting | ExtentReports 5.1 (Spark reporter), JMeter HTML Dashboard |
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

Performance Test (TestNG @Test)
   └── calls JMeterCommons.runJMeterScript(jmxFile)
          ├── StandardJMeterEngine    (executes the .jmx test plan)
          ├── ResultCollector         (streams samples to a timestamped CSV)
          └── ReportGenerator         (builds the HTML dashboard from the CSV)
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
│   └── tests/
│       ├── FunctionalRepositoryApiTest.java    # GitHub API CRUD test suite
│       └── PerformanceRepositoryApiTest.java   # Triggers JMeter performance run
└── framework/
    ├── base/BasePage.java              # browser setup/teardown (ThreadLocal WebDriver)
    ├── web/commons/WebCommons.java     # reusable Selenium actions & waits, uniqueId() timestamp helper
    ├── api/commons/
    │   ├── ApiCommons.java              # reusable REST-assured request/assert methods
    │   └── JMeterCommons.java           # embedded JMeter engine runner + report generation
    ├── db/commons/DBreadData.java      # DB query → List<Map<String,String>>
    ├── utilities/                      # DBUtils, ExcelUtils, PdfUtils, PropUtils
    ├── listeners/                      # TestListener, ApiTestListener, RetryTest
    └── reports/Reports.java            # ExtentReports lifecycle

TestRunner/           # TestNG suite XMLs (sequential, parallel, API, performance)
Config/                # config.properties (git-ignored, holds secrets/URLs)
TestData/ExcelFiles/   # data-driven test inputs
src/test/resources/apache-jmeter-5.6.3/
├── JMeter Testing Script jmx file/   # .jmx test plans
├── JMeter reports/
│   ├── Summary reports/               # timestamped CSV results per run
│   └── JMeter html reports/           # timestamped HTML dashboard folder per run
└── bin/jmeter.properties              # JMeter engine configuration
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

> **Note (performance layer):** no separate JMeter installation is required — `ApacheJMeter_core`, `ApacheJMeter_http`, and `ApacheJMeter_components` are pulled in as Maven dependencies and driven entirely through JMeter's Java API (`JMeterCommons`). The `.jmx` test plan under `src/test/resources/apache-jmeter-5.6.3/JMeter Testing Script jmx file/` just needs to reference valid target endpoints (see `Config/config.properties`).

## Running Tests

```bash
# Sequential UI suite (Chrome)
mvn test -DsuiteFile=TestRunner/CreatioCRME2Etestng.xml

# Parallel cross-browser UI suite (Chrome + Firefox + Edge, 5 threads each)
mvn test -DsuiteFile=TestRunner/CreatioCRME2EParalleltestng.xml

# GitHub REST API suite (functional)
mvn test -DsuiteFile=TestRunner/FunctionalGitHubRepositoryApiTesttestng.xml

# GitHub REST API suite (performance — runs JMeter test plan, generates CSV + HTML report)
mvn test -DsuiteFile=TestRunner/PerformanceGitHubRepositoryApiTesttestng.xml
```

Reports are generated per run in `Reports/*.html` (ExtentReports) and `test-output/` (native TestNG reports). Failure screenshots are saved to `Screenshots/`. Performance run artifacts are saved per-run under `src/test/resources/apache-jmeter-5.6.3/JMeter reports/` — a timestamped `Performance testing(Github)_<timestamp>.csv` in `Summary reports/`, and a timestamped `Report_<timestamp>/index.html` dashboard in `JMeter html reports/`.

## Test Coverage (current)

- **UI (`ApplicationTest`)** — 8 scenarios: login page UI, cookie consent dialog + tabs, cookie-popup → privacy policy navigation, password recovery flow, cookie acceptance dismissal, data-driven sign-up, valid login, invalid login.
- **API (`FunctionalRepositoryApiTest`)** — 9 scenarios covering the full repository lifecycle: pre-condition check, create, verify creation, get, update, verify update, delete, verify deletion, and negative/error-path checks.
- **Performance (`PerformanceRepositoryApiTest`)** — JMeter-driven load test against the GitHub Repository API, producing per-run response-time, throughput, APDEX, and error-rate metrics via the generated HTML dashboard.
