package com.testing;

import org.openqa.selenium.WebDriver;

import com.testing.driverself.GoogleDriver;

public class TestDriverS {

	public static void main(String[] args) {
		
		GoogleDriver gg=new GoogleDriver("ExeDriver/chromedriver.exe");
		WebDriver driver=gg.getDriver();
		driver.get("http://www.baidu.com");
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();
		

	}

}
