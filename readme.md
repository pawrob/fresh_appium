# fresh_appium

## Overview

This project automates mobile application testing for Android platforms using [Appium](https://appium.io/), Java, and
Maven. The tests cover login, cart interactions, checkout process, product sorting and validation.

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

- **Java JDK** (17+)
- **Maven**
- **Node.js** (18+)
- **Appium** (installed globally)
- **Android Studio** (for Android tests)
- Emulator (Tested on android 12 and SKD 31)

## Environment Setup

1. Install Node.js and NPM: [Node.js](https://nodejs.org/)
2. Install Java  [Java JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
3. Install Maven: [Maven](https://maven.apache.org/install.html)
4. Check java version and later check java version in maven:

    ```bash
    java -version
    mvn -v
    ```
5. Install Appium globally:

    ```bash
    npm install -g appium # or via homebrew on macOS
    ```

6. (Mac specyfic) Set up Android SDK and emulator and after make sure to have all variables in path and .zshrc (for
   mac), eg:
    ```
    export JAVA_HOME=$(/usr/libexec/java_home)
    export MAVEN_HOME=/opt/homebrew/Cellar/maven/3.9.11
    export M2=$MAVEN_HOME/bin
    export ANDROID_HOME=/users/pawelbucki/Library/Android/sdk 
    export PATH=$PATH:/usr/local/git/bin/:/usr/local/bin:
    export PATH=$PATH:$ANDROID_HOME/platform-tools
    export PATH=$PATH:$ANDROID_HOME/tools
    export PATH=$PATH:$ANDROID_HOME/tools/bin
    export PATH=$PATH:$ANDROID_HOME/emulator
    ```

8. Install Appium Doctor to verify your Appium setup:

    ```bash
    npm install -g appium-doctor
    appium-doctor
    ```

## Project setup

1. clone the repository:

    ```bash
    git clone https://github.com/pawrob/fresh_appium.git
   ```
2. Navigate to the project directory:

    ```bash
    cd fresh_appium
    ```
3. Install Maven dependencies:

    ```bash
    mvn clean compile
    ```
4. create `initConfig.android.properties` file in project root directory basing on example and change if necessary
5. Add the APK file to `src/test/resources/`
   directory. [apk download](https://github.com/saucelabs/sample-app-mobile/releases/)
6. Fill password in files in `src/test/testData/` directory (it can be found in app main screen):
    - `blockedUserData.json`
    - `loginData.json`

## Running Tests

- Appium server starts automatically before tests and stops after all tests are completed.
- Android emulator should be running before executing tests.

### To run the tests, use the following command:

```bash
mvn test -Pandroid 
```
