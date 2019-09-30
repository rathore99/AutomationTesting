package com.rahul;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/*
 * Class to implement webdriver methods
 */
public class WebDriverMethods {
	WebDriver driverChrome;

	@Test
	public void browser() {
		String path = System.getProperty("user.dir");
		String genericPathChrome = path + "\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", genericPathChrome);
		driverChrome = new ChromeDriver();
		driverChrome.manage().window().maximize();
		driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driverChrome.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	@Test(enabled = true)
	public void driverMethods() {
		driverChrome.get("https://www.google.com/");
		System.out.println("print current url: " + driverChrome.getCurrentUrl());
		System.out.println("print page source " + driverChrome.getPageSource());
		System.out.println("title of page " + driverChrome.getTitle());
//		WebElement element =driverChrome.findElement(By.tagName("input"));
		// element.sendKeys("hello world");
		driverChrome.get("https://www.facebook.com");
		driverChrome.navigate().back();
		driverChrome.navigate().forward();
		// driverChrome.quit();
		// driverChrome.close();

	}

	

}
