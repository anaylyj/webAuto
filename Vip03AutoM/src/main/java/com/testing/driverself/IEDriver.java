package com.testing.driverself;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IEDriver {
	private WebDriver driver;
	
	public InternetExplorerDriverService service=null;
	
	
	public IEDriver(String driverPath) {
		//设置IE 驱动路径
		System.setProperty("webdriver.ie.driver", driverPath);
		
		DesiredCapabilities ieCapabilities=DesiredCapabilities.internetExplorer();
		//设置忽略安全校验
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
		//创建一个IEDriver的服务，用于连接IE
		try {
			service=new InternetExplorerDriverService.Builder().usingDriverExecutable(new File(driverPath)).usingAnyFreePort().build();
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("log--error:service 启动错误！！");
		}
		
		try {
			//创建一个IE的浏览器实例
			this.driver=new RemoteWebDriver(service.getUrl(), ieCapabilities);
			
			//让浏览器访问空白页面
			driver.get("about:blank");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("log--error:创建driver失败！！");
		}
		
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
	public void close() {
		driver.quit();
		service.stop();
	}

}
