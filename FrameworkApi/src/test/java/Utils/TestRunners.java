package Utils;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Runner class is the entry point of the framework.
 * Run the Runner Class to trigger the Test Suite Execution.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
		features="features",	
		glue= {"stepDefination","Utils"},
		plugin= {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome=true,
		tags={"@RegressionSuite"}
		
		)
public class TestRunners{


}
