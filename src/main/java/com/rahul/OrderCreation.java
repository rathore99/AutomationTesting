package com.rahul;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class OrderCreation {
	
	private String userName;
	private String password;
	
	
	WebDriver driver;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private ConfigFileReader configFileReader;
	public OrderCreation() {
		ConfigFileReader configFileReader = new ConfigFileReader();
	}
	@Test
	public void browser() {
		ConfigFileReader configFileReader = new ConfigFileReader();
		String genericPathChrome =  configFileReader.getDriverPath();
	//	System.out.println(genericPathChrome);
		System.setProperty("webdriver.chrome.driver", genericPathChrome);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitWait(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

	}
	
	public void readCredentials() {
		ConfigFileReader configFileReader = new ConfigFileReader();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(configFileReader.getFilePath());
		} catch (FileNotFoundException e) {
			System.out.println("file not found...please try again");
			e.printStackTrace();
		}
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("some error occurred");
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		XSSFRow row = sheet.getRow(0);
		Long uname = (long) (row.getCell(0).getNumericCellValue());
		setUserName(uname.toString());
		setPassword(row.getCell(1).getStringCellValue().toString());
		System.err.println(userName+password);
	}

	@Test
	public void login() {
		ConfigFileReader configFileReader = new ConfigFileReader();
		readCredentials();
		System.out.println(configFileReader.getURL());
		driver.get(configFileReader.getURL());
		driver.findElement(By.xpath("(//input[@type=\"text\"])[2]")).sendKeys(getUserName());
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(getPassword());
		driver.findElement(By.xpath("(//button[@type=\"submit\"])[2]")).click();


		
	}
	@Test
	public void search() {
		//driver.findElement(By.xpath("(//input[@type=\"text\"])")).sendKeys("Book");
		
		
	}
	@Test
	public void takeScreenShot() throws IOException {
		String path = "D:\\Coding\\java ssi\\selenium\\TestingAssignment\\screenshot\\";
		TakesScreenshot screenshot = (TakesScreenshot)driver; 
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source,new File(path+"xyz.png"));
	}

}
