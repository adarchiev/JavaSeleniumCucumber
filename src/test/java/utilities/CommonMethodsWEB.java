package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethodsWEB {
	WebDriver driver = Driver.getDriver();
	private WebDriverWait wait;

	/**
	 * Refresh page.
	 */
	public void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * Wait for element using a String locator.
	 * 
	 * @param locator the locator
	 */
	protected void waitForElementVisibilityXpath(String locator) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

	protected void waitForElementVisibility(By by) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void highLighterMethod(WebDriver driver, WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		Thread.sleep(1000);
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public boolean validateTitleMatches(String title) {
		return driver.getTitle().equals(title);
	}

	public boolean validateURLMatches(String url) {
		return driver.getCurrentUrl().equals(url);
	}

	public boolean validateTitleContains(String title) {
		return driver.getTitle().contains(title);
	}

	public boolean validateURLContains(String url) {
		return driver.getCurrentUrl().contains(url);
	}
}
