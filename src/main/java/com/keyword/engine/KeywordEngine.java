package com.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.resources.Base;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;

	public Workbook book;
	public Sheet sheet;
	Base base;

	public final String SCENARIO_SHEET_PATH = "D:\\Selenium\\FrameworkPractice\\KeywordDrivenHotSpot\\src"
			+ "\\main\\java\\com\\keywordData\\keywordData.xlsx";

	public void startExecution(String sheetName) {

		String locatorName = null;
		String locatorValue = null;
		FileInputStream file = null;
		WebElement element;
		
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
		}  catch (IOException e) {
			e.printStackTrace();
			}
		
		sheet = book.getSheet(sheetName);
		int k = 0;
		
	
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
				String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); // id=username
				if (!locatorColValue.equalsIgnoreCase("NA")) {
					locatorName = locatorColValue.split("=")[0].trim(); // id
					locatorValue = locatorColValue.split("=")[1].trim(); // username

				}
				String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

				// Now Switch case for Action field
				switch (action) {

				case "open browser":
					base = new Base();
					prop = base.init_prop();
					if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
						driver = base.initializeDriver(prop.getProperty("browser"));
					} else {
						driver = base.initializeDriver(value);
					}
					break;
				case "launch url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));
					} else {
						driver.get(value);
					}
					break;

				case "quit":
					driver.quit();
					break;
				default:
					break;

				}

				switch (locatorName) {
				case "id":
					element = driver.findElement(By.id(locatorValue));
					
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					locatorName = null;

					break;
				case "cssSelector":
				
					break;
				case "xpath":
					element = driver.findElement(By.xpath(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					locatorName = null;
					break;

				default:
					break;
				}
			} catch (Exception e) {
		
			
			}

		}
	}

}
