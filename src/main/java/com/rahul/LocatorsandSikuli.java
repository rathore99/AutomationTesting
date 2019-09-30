package com.rahul;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LocatorsandSikuli {
    WebDriver driverChrome;
    @BeforeTest
    public void beforeTest() {
    	System.out.println("before test annotation");
    } 
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

	@Test
	public void useLocators() throws FindFailed, InterruptedException {
		driverChrome.get("https://www.w3schools.com/");
		driverChrome.findElement(By.id("navbtn_references")).click();
		WebDriverWait wait = new WebDriverWait(driverChrome, 10);
		WebElement element;
		List<WebElement>abc =  wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"navbtn_tutorials\"]")));
		/*element.click();*/
		driverChrome.findElement(By.linkText("EXERCISES")).click();
		String titleName = driverChrome.findElement(By.tagName("title")).getText();
		System.out.println(titleName);
		sikuliMethod();
	}

	public void sikuliMethod() throws FindFailed, InterruptedException {

		Screen screen = new Screen();
		Pattern tryit = new Pattern("C:\\Users\\rahul\\Downloads\\cross.png");
		screen.click(tryit);
		driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Pattern homeIcon = new Pattern("C:\\Users\\rahul\\Downloads\\tryity.PNG");
		screen.click(homeIcon);
				}
	
	@AfterTest
	public void afterTest() {
		System.out.println("all test case executed");
	}

}
