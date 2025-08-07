### Task summary

## Technology stack
- Platform: Android, emulator on SKD 31 with Android 12
- Language: Java 17, Appium 2.0
- Same app as proposed 

## Basic requirements
- POM implemented in `src/main/java/org/fresh/pages` where page factory was created for each page
- Configuration files are in files like `initConfig.example.properties` where properties of local machine and platform specific details are stored
- environment variables are stored in same file `initConfig.example.properties`

## Advanced requirements
- Helper methods for login, order details, and other common actions are implemented in `src/main/java/org/fresh/helpers` they can be resued to optimize test code
- Framework level action wrappers are located in `src/main/java/org/fresh/pages/BasePage.java` clicks, texting, scrolling, and other actions are implemented there
- Dynamic waits are done with fluent wait for each implemented action
- Retrier is implemented in `src/main/java/org/fresh/utilities/RetryListener.java` to retry failed tests
- Custom logging is added for framework startup and test execution


## Main task
Main test is created in testUserCanCompleteOrder in `src/test/java/org/fresh/tests/TestShopCheckout.java` which covers the given requirements
- In my version of app there were no payment information to fill, i later checked them as they are hardcoded in the app

## Additional test cases
- test for validating input of checkout form, login page and also test for blocked user
- there was no filtering in app so i created tests for checking if sorting works correctly
- tests for removing items from cart and checking if cart is empty, with two scenarios including opening cart

## Final task
- readme file is created with instructions on how to run the tests
- Repository preserves commits and sdlc was keept with development branch and mereg requests to main branch

## Coding choices

- Test data files are not present, I was thinking about leaving files without password and instruction how to fill them but such data can change any time
- Main test case was designed to work regardless of picked product, items can eg. sorted before purchase 
- Config files can be added to pom.xml where profiles can be added, then different config file can be executed with just different -P<profile_name> in mvn command
- Sorting tests were merged into one, its faster to run test basing on same screen at once than running them separately 