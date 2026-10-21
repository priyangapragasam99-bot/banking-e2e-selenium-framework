package com.bankproject.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	 private final WebDriverWait wait;

	    public WaitUtils(WebDriver driver) {
	        int timeout = ConfigReader.getIntProperty("explicitWait");

	        this.wait = new WebDriverWait(
	                driver, Duration.ofSeconds(timeout));
	    }

	    public WebElement waitForVisible(By locator) {
	        return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    public WebElement waitForClickable(By locator) {
	        return wait.until(
	                ExpectedConditions.elementToBeClickable(locator));
	    }

	    public boolean waitForUrlContains(String text) {
	        return wait.until(
	                ExpectedConditions.urlContains(text));
	    }

	    public boolean waitForElementInvisible(By locator) {
	        return wait.until(
	                ExpectedConditions.invisibilityOfElementLocated(locator));
	    }
	}

