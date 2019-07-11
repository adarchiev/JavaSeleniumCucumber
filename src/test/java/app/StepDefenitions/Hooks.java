package app.StepDefenitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.Driver;

public class Hooks {
	
	@Before
	public void Before() throws MalformedURLException, IOException, ParseException, SQLException {
		WebDriver driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
	}

	@After
	public void After(Scenario scenario) throws Exception {
		System.out.println("AFTER");
		if (scenario.isFailed()) {

			// take screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			// adding screenshot to report
			scenario.embed(screenshot, "image/png");

		}

		
	}
}
