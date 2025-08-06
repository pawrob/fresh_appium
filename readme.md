# fresh_appium

## Overview

This project automates mobile application testing for Android platforms using [Appium](https://appium.io/), Java, and Maven. The tests cover login, cart interactions, checkout process, product sorting and validation.


## Project Structure

- `src/main/java/org/fresh/` – test framework source code
    - `helpers/` – helper classes (e.g., login, order details)
    - `pages/` – application pages (Page Object Pattern)
        - `onboarding/` – login page
        - `shop/` – cart, shop, and checkout pages
    - `utilities/` – utilities (e.g., properties loader, retry listener)
- `src/main/resources/` – configuration files (e.g., `log4j2.xml`)
- `src/test/java/org/fresh/` – automated tests
- `src/test/resources/` – test resources (e.g., test data, APK file)
- `src/test/suites/` – TestNG configuration
- `src/test/testData/` – JSON files with test data

## Requirements

- **Java JDK** (11+)
- **Maven**
- **Node.js** (18+)
- **Appium** (installed globally)
- **Android Studio** (for Android tests)
- Emulator 

## Installation

1. Install Node.js and NPM: [Node.js](https://nodejs.org/)
2. Install Appium globally:

    ```bash
    npm install -g appium # or via homebrew on macOS
    ```
3. Install Maven dependencies:

    ```bash
    mvn clean install
    ```

4. Set up Android SDK and emulator:
6. Setup environment variables for Android SDK:
    - `ANDROID_HOME` pointing to your Android SDK directory
    - Add `platform-tools` and `tools` directories to your `PATH`
7. create `initConfig.android.properties` file in project root directory basing on example
8. Add the APK file to `src/test/resources/` directory.
9. Create a `src/test/testData/` directory and add JSON files with test data (`loginData.json`, `blockedUserData.json`).
    format:
     ```json
    [
        {
        "username" : "",
        "password" : ""
        }
    ]
     ```

## Running Tests
- Appium server starts automatically before tests and stops after all tests are completed. 
- Android emulator should be running before executing tests.
### To run the tests, use the following command:

```bash
mvn clean test -Pandroid 
```




