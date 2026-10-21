package com.bankproject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bankproject.utils.WaitUtils;

public class LoginPage {
	private final WebDriver driver;
    private final WaitUtils waitUtils;

    //Locator
	private final By UserNameLocator = By.id("login-username");
	private final By PasswordLocator = By.id("login-password");
	private final By loginButtonLocator = By.cssSelector("button[type='submit']");
	private final By invalidPasswordErrorMsg = By.cssSelector("span[data-testid=\"login-error-message\"]");
	
	//Constructor
	public LoginPage(WebDriver driver) {
	    this.driver = driver;
	    this.waitUtils = new WaitUtils(driver);
	}
	
	//Actions
	public void enterUserName(String userName)
	{
		WebElement userNameInput = waitUtils.waitForVisible(UserNameLocator);
		userNameInput.clear();
		userNameInput.sendKeys(userName);
	}
	
	public void enterPassword(String password)
	{
		WebElement passwordInput = waitUtils.waitForVisible(PasswordLocator);
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}
	
	public void clickLoginButton()
	{
		waitUtils.waitForClickable(loginButtonLocator).click();
		
	}
	
	public String invalidPassword()
	{
		WebElement msg = waitUtils.waitForVisible(invalidPasswordErrorMsg);
		 return msg.getText().trim();
	}
}
