package com.bankproject.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtils {

	 public static String captureScreenshot(
	            WebDriver driver, String testName) {

	        String timestamp = LocalDateTime.now().format(
	                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

	        Path screenshotDirectory =
	                Paths.get("test-output", "screenshots");

	        Path destination = screenshotDirectory.resolve(
	                testName + "_" + timestamp + ".png");

	        try {
	            Files.createDirectories(screenshotDirectory);

	            File source = ((TakesScreenshot) driver)
	                    .getScreenshotAs(OutputType.FILE);

	            Files.copy(
	                    source.toPath(),
	                    destination,
	                    StandardCopyOption.REPLACE_EXISTING);

	            return destination.toString();

	        } catch (IOException e) {
	            throw new RuntimeException(
	                    "Failed to save screenshot: " + destination, e);
	        }
	    }
}
