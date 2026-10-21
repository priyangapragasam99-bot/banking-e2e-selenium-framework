package com.bankproject.tests;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.annotations.Listeners;
import com.bankproject.listeners.TestListener;

import com.bankproject.basetest.BaseTest;
import com.bankproject.page.LoginPage;
import com.bankproject.testdata.LoginDataPovider;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest{
	
	@Test
	public void verifyLoginFormCanBeSubmitted()
	{
		LoginPage page = new LoginPage(driver);
		
		page.enterUserName("standard_user");
		page.enterPassword("bank_sauce");
		page.clickLoginButton();
	}
	
	
	@Test(dataProvider = "invalidLoginData", dataProviderClass = LoginDataPovider.class)
	public void verifyInvalidCredentials(
	        String username, String password) 
	{
		LoginPage page = new LoginPage(driver);
		page.enterUserName(username);
		page.enterPassword(password);
		page.clickLoginButton();
		
		String actualMessage = page.invalidPassword();
		String expectedMessage = "The username or password you entered is incorrect.";
		Assert.assertEquals(actualMessage, expectedMessage, "Invalid login error message did not match!");
	}

}
