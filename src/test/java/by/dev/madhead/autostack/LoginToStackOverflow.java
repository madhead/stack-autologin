package by.dev.madhead.autostack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@Test
@ContextConfiguration("classpath:login-test.xml")
public class LoginToStackOverflow extends AbstractTestNGSpringContextTests {
	@Autowired
	private WebDriver driver;

	@Value("${autostack.waitTimeout}")
	private long waitTimeout;

	@Value("${autostack.login.startURL}")
	private String startURL;

	@Value("${autostack.login.openIdButtonCssSelector}")
	private String openIdButtonCssSelector;

	@Value("${autostack.login.loginFormFrameId}")
	private String loginFormFrameId;

	@Value("${autostack.login.emailInputId}")
	private String emailInputId;

	@Value("${autostack.login.email}")
	private String email;

	@Value("${autostack.login.passwordInputId}")
	private String passwordInputId;

	@Value("${autostack.login.password}")
	private String password;

	@Value("${autostack.login.submitButtonCssSelector}")
	private String submitButtonCssSelector;

	@Value("${autostack.login.unansweredLinkCssSelector}")
	private String unansweredLinkCssSelector;

	@Test
	public void login() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeout);

		driver.get(startURL);
		driver.findElement(By.cssSelector(openIdButtonCssSelector)).click();
		wait.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(loginFormFrameId));
		driver.findElement(By.id(emailInputId)).clear();
		driver.findElement(By.id(emailInputId)).sendKeys(email);
		driver.findElement(By.id(passwordInputId)).clear();
		driver.findElement(By.id(passwordInputId)).sendKeys(password);
		driver.findElement(By.cssSelector(submitButtonCssSelector)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id(unansweredLinkCssSelector)));
		driver.findElement(By.id(unansweredLinkCssSelector)).click();
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
}
