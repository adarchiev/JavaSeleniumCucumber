package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	@SuppressWarnings("unused")
	private WebDriver driver;
	WebDriverWait wait;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	@FindBy(xpath = "xpath/css")
	public WebElement username;

	@FindBy(xpath = "xpath/css")
	public WebElement password;

	@FindBy(xpath = "xpath/css")
	public WebElement login;

	public void login(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.login.click();
	}


}
