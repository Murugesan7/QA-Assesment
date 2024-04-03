package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {

	public static WebDriver driver;

	@Test(priority = 1)
	void tc01_launchBrowser() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		System.out.println("Browser is Launched  \n");
		Thread.sleep(2000);
	}

	@Test(priority = 2)

	void tc02_navigateProductDescriptionPage() throws AWTException, InterruptedException {
		WebElement grossory = driver.findElement(By.xpath("(//img[@class='_2puWtW _3a3qyb'])[1]"));

		grossory.click();
		System.out.println("Web Page is Navigated to Product Description Page \n");
		Thread.sleep(2000);

		WebElement SearchBar = driver.findElement(By.name("q"));
		SearchBar.sendKeys("fortune atta");
		Thread.sleep(2000);

		WebElement searchBtn = driver.findElement(By.xpath("//button[@class='L0Z3Pu']"));

		searchBtn.click();
		Thread.sleep(2000);

		WebElement pincode = driver.findElement(By.xpath("//input[@class='_166SQN']"));
		pincode.sendKeys("600026");

		Thread.sleep(2000);

		Robot r = new Robot();

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(3000);

		WebElement gothumai = driver.findElement(By.xpath("(//img[@class='_396cs4'])[1]"));
		gothumai.click();

		Thread.sleep(4000);

	}

	@Test(priority = 3)
	void tc03_verifyImage() throws InterruptedException {
		Set<String> allWindowId = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(allWindowId);
		driver.switchTo().window(list.get(1));

		WebElement first = driver.findElement(By.xpath("(//img[@class='q6DClP'])[3]"));
		first.click();

		WebElement image = driver.findElement(By.xpath("//img[@class='_396cs4 _2amPTt _3qGmMb']"));

		Assert.assertEquals(image.isEnabled(), true);
		if (image.isDisplayed()) {
			System.out.println("The Image is Present in the Product Webpage \n");
			System.out.println("The Image Name is : " + image.getAttribute("alt") + "\n");
		} else {
			System.out.println("The Image is not present in the Product Webpage \n");
		}

		Thread.sleep(3000);
	}

	@Test(priority = 4)
	void tc04_verifyPrice() throws InterruptedException {
		WebElement price = driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']"));

		String expPrice = "₹284";
		String actPrice = price.getText();
		
		if (actPrice.contains(expPrice) == true) {
			System.out
					.println("The Actual Price of the Product was Matched with the Expected Price of the Product \n  ");
			System.out.println("The Price of the Product is  " + actPrice + "\n");
		} else {
			System.out.println(
					"The Actual Price of the Product was not Matched with the Expected Price of the Product \n  ");
			System.out.println("The Expected Price of the Product is  " + expPrice + "\n");
			System.out.println("The Actual   Price of the Product is  " + actPrice + "\n");

		}
		Thread.sleep(2000);
		
		Assert.assertEquals(actPrice.contains(expPrice), true);

		

		Thread.sleep(5000);
	}

	@Test(priority = 5)
	
	void tc05_closeTheBrowser() {
		System.out.println("Browser is Closed \n");
		driver.quit();

	}
}
