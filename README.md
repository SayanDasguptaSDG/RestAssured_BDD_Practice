# RestAssured practice with Behavior Driven Development (BDD) Framework
## **To execute with maven:**
##### _mvn test_
#### (using tagging)
##### _mvn test -Dcucumber.filter.tags="@tagname or @anothertagname"_
## **To enable cucumber reporting:**
##### check https://github.com/damianszczepanik/maven-cucumber-reporting
##### * create a directory in "target" folder named "jsonReports"
##### * go to TestRunner.java and add the plugin description as:
##### * plugin = "json:target/jsonReports/cucumber-report.json" in @CucumberOptions, which will create s cucumber-report.json file inside jsonReports folder
##### This json file will now be used as an input to the reporting plugin and will generate test report under "target/cucumber-html-reports"folder
##### Known issue with parallel testing: https://github.com/junit-team/junit5/issues/1424
## **The maven command to generate test report:**
##### _mvn test verify_
