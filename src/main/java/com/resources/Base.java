package com.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\FrameworkPractice\\KeywordDrivenHotSpot\\drivers\\chromedriver.exe");
			if(prop.getProperty("headless").equalsIgnoreCase("yes"))
			{
				//headless Mode
				ChromeOptions option = new ChromeOptions();
				option.addArguments("----headles-----");
				driver = new ChromeDriver(option);
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			}
			else
			{
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			}
		}
		return driver;
	}
	
	public Properties init_prop()
	{
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("D:\\Selenium\\FrameworkPractice\\KeywordDrivenHotSpot\\src"
					+ "\\main\\java\\com\\resources\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	

}
