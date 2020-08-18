package web.cucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/java/features", glue="stepDefination"
)
public class TestRunner extends AbstractTestNGCucumberTests { //extending AbtractTestNGCucumberTests converts your Junit test to TestNg

}
