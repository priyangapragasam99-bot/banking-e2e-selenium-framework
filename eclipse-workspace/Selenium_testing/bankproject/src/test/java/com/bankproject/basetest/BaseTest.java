package com.bankproject.basetest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.bankproject.utils.ConfigReader;

public class BaseTest {

	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		ChromeOptions option = new ChromeOptions();
		driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ZERO);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getIntProperty("pageLoadTimeOut")));
		driver.get(ConfigReader.getProperty("url"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
		
	}
	
	
}
