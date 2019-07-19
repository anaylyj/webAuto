package com.testing.driverself;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FFDriver {
	private WebDriver driver;

	public FFDriver(String binPath, String driverPath) {
		// 设置Firefox驱动的路径
		System.setProperty("webdriver.gecko.driver", driverPath);
		// 设置Firefox的安装目录
		if(binPath!=null && binPath.length()>0)
			System.setProperty("webdriver.firefox.bin", binPath);
		FirefoxOptions firefoxOptions=new FirefoxOptions() {
			FirefoxProfile profile=new FirefoxProfile();
		};
		
		try {
			driver=new FirefoxDriver(firefoxOptions);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("log--error:创建dirver失败！！");
		}

	}

	public WebDriver getDriver() {
		return driver;
	}

}
