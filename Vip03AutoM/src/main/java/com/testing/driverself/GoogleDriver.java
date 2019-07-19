package com.testing.driverself;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleDriver {
	private WebDriver driver=null;
	
	public GoogleDriver(String driverPath) {
		//设置chrome驱动的路径
		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions option=new ChromeOptions();
		//去除Chrome浏览器上的黄色警告
		option.addArguments("--disable-infobars");
		
		//加载Chrome 用户文件
		option.addArguments("--user-data-dir=C:\\Users\\xn087454\\AppData\\Local\\Google\\Chrome\\User Data");
		
		//最大化浏览器窗口
		option.addArguments("--start-maximized");
		//白名单设置
		option.addArguments("--whitelisted-ips=\"\"");
		
		try {
			//创建一个chrome的浏览器实例
			this.driver=new ChromeDriver(option);
			//让浏览器访问空白页
			driver.get("about:blank");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("log-error:创建driver失败！！");
		}
		
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

}
