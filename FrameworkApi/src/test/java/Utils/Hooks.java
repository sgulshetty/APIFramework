package Utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;

/**
 * 
 * Hooks Class will generate the logs and store in Logs folder
 */
public class Hooks {
	public Logger logger;
/**
 * Before Hook will run before the scenario and start capturing logs and store in Logs folder
 * @param scenario to get the name of the scenario running
 */
	@Before
	public void before(Scenario scenario) {
		BasicConfigurator.configure();
		String currentDir = System.getProperty("user.dir");
		logger=Logger.getLogger("RateApiAutomation");
		PropertyConfigurator.configure(currentDir+"/log4j.properties");
		logger.setLevel(Level.DEBUG);
		RestAssured.baseURI=FileReaderManager.getInstance().getConfigReader().getBaseUrl();
		RestAssured.basePath=FileReaderManager.getInstance().getConfigReader().getBasePath();
		logger.info("------------------Scenario \""+scenario.getName()+"\" Started---------------------");
	}

	/**
	 * After Hook will signifies the end of the scenario in the Logs
	 * @param scenario
	 */
	@After
	public void after(Scenario scenario) {
		logger.info("------------------Scenario \""+scenario.getName()+"\" Ends---------------------");
	}
}
