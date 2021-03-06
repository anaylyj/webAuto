package com.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeDemo {

	public static void main(String[] args) {
		// Create a new instance of the Chrome driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.

		System.setProperty("webdriver.chrome.driver", "ExeDriver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// And now use this to visit Baidu
		driver.get("http://www.baidu.com");
		// Alternatively the same thing can be done like this
		// driver.navigate().to("http://www.google.com");

		// Find the text input element by its name

		WebElement element = driver.findElement(By.id("kw"));

		// Enter something to search for
		element.sendKeys("selenium");

		// Now submit the form. WebDriver will find the form for us from the element
		element.submit();

		// Check the title of the page
		System.out.println("Page title is: " + driver.getTitle());

		// Google's search is rendered dynamically with JavaScript.
		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("selenium");
			}
		});

		// Should see: "cheese! - Google Search"
		System.out.println("Page title is: " + driver.getTitle());

		// Close the browser
		driver.quit();

	}

}
