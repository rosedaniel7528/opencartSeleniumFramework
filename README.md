# OpenCart Test Automation

[![Selenium Grid Tests](https://github.com/rosedaniel7528/opencartSeleniumFramework/actions/workflows/selenium-tests.yml/badge.svg)](https://github.com/rosedaniel7528/opencartSeleniumFramework/actions/workflows/selenium-tests.yml)

Selenium and TestNG test automation framework for an OpenCart demo store (https://awesomeqa.com). It covers the guest checkout flow and product search, and runs on Chrome and Firefox in parallel, locally or on a Dockerised Selenium Grid. A GitHub Actions pipeline runs the suite on every push and pull request, and nightly.

## Tech stack

- Java 17, Maven
- Selenium 4, TestNG 7
- Extent Reports (HTML report with screenshots embedded on failure)
- Log4j2 for logging
- Jackson for JSON test data
- Docker Compose (Selenium Grid) and GitHub Actions for CI

## Framework design

- **Page Object Model** with a shared `BasePage` (driver, waits, `PageFactory` setup).
- **Thread-safe driver handling:** `DriverManager` keeps one `WebDriver` per thread in a `ThreadLocal`, so tests can run in parallel.
- **Driver creation in one place:** `DriverFactory` and `BrowserOptions` build Chrome or Firefox for each execution mode.
- **Externalised configuration:** values come from a system property, then an environment variable, then `config.properties`.
- **Data-driven tests:** test data is read from JSON files.
- **Listener and retry:** `TestListener` writes the report and attaches a screenshot on failure, and `RetryAnalyzer` reruns flaky tests.

## Project structure

```
docker-compose.yml        Selenium Grid (hub, Chrome node, Firefox node)
.github/workflows/        CI pipeline
src/main/java/com/opencart
  config/       Browser, ExecutionMode, ConfigReader
  driver/       DriverManager, DriverFactory, BrowserOptions
  pages/        BasePage and page objects
  utilities/    ReusableMethods (explicit waits), ScreenshotUtil, JsonDataReader
src/main/resources
  config.properties, log4j2.xml
src/test/java/com/opencart
  base/         BaseTest
  listeners/    TestListener, RetryAnalyzer
  reporting/    ExtentManager
  tests/        CheckoutTest, SearchTest
src/test/resources
  suites/       cross-browser.xml (TestNG suite)
  testdata/     JSON test data
```

## Configuration

Defaults live in `src/main/resources/config.properties`. Any value can be overridden with `-Dkey=value` or with an environment variable (the key in upper case, dots replaced by underscores, for example `GRID_URL`).

| Key | Default | Description |
|---|---|---|
| `base.url` | https://awesomeqa.com/ui/index.php?route=common/home | Site under test |
| `grid.url` | http://localhost:4444 | Selenium Grid address, used in `remote` mode |
| `browser` | chrome | Used when the suite file does not pass `browserName` |
| `platform` | local | `local`, `remote` or `remote_git` |
| `explicit.wait` | 5 | Explicit wait timeout in seconds |
| `retry.count` | 1 | How many times a failed test is retried |

Execution modes:

- `local` starts the browser on your machine.
- `remote` connects to a Selenium Grid at `grid.url`.
- `remote_git` starts a headless browser, intended for CI runners.

## Running the tests

Prerequisites: JDK 17 and Maven, plus Chrome and/or Firefox for local runs.

```bash
git clone https://github.com/rosedaniel7528/opencartSeleniumFramework.git
cd opencartSeleniumFramework

# default suite: checkout and search tests on Chrome and Firefox
mvn clean test

# use a different suite file
mvn clean test -Dsuite.file=src/test/resources/suites/other.xml

# headless, as used in CI
mvn clean test -Dplatform=remote_git

# change the explicit wait
mvn clean test -Dexplicit.wait=10
```

## Running on a Selenium Grid (Docker)

`docker-compose.yml` starts a Selenium Grid with a hub, a Chrome node and a Firefox node (pinned image versions, healthchecks on every service).

```bash
docker compose up -d --wait        # waits until the grid is healthy
mvn clean test -Dplatform=remote   # tests run against http://localhost:4444
docker compose down
```

The grid UI is at http://localhost:4444/ui. You can watch the browsers live through noVNC at http://localhost:7901 (Chrome) and http://localhost:7902 (Firefox).

## Continuous integration

The workflow in `.github/workflows/selenium-tests.yml`:

- runs on push and pull request to `main`, nightly, and on demand (with an optional suite file input);
- starts the grid with `docker compose up -d --wait`, then runs the tests with `-Dplatform=remote`;
- fails the build when a test fails;
- always uploads the Extent report, logs and surefire results as an artifact, and always stops the grid.

## Reports and logs

- The Extent report is written to `reports/index.html`. Failed tests include an embedded screenshot.
- Logs are written to the console and to `logs/automation.log`.

## Contact

Rose Daniel
Email: [rosedaniel.s7528@gmail.com](mailto:rosedaniel.s7528@gmail.com)
GitHub: [rosedaniel7528](https://github.com/rosedaniel7528)
