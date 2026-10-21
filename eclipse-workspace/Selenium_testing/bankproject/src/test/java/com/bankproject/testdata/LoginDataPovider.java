package com.bankproject.testdata;
import org.testng.annotations.DataProvider;
public class LoginDataPovider {
	
	  @DataProvider(name = "invalidLoginData")
	    public static Object[][] invalidLoginData() {
	        return new Object[][] {
	            {"test", "test"},
	            {"invalidUser", "wrongPassword"},
	            {"", "test"},
	            {"test", ""}
	        };
	    }

}
