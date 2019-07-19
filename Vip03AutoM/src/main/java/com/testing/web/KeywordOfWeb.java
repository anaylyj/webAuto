package com.testing.web;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.driverself.FFDriver;
import com.testing.driverself.GoogleDriver;
import com.testing.driverself.IEDriver;

/**
 * @author xn087454
 *
 */
public class KeywordOfWeb {
	public WebDriver driver;

	/**
	 * @author xn087454 打开浏览器的方法，其实就是完成driver实例化的过程
	 * @param browserType决定使用哪种浏览器
	 */
	public void openBrowser(String browserType) {
		try {
			switch (browserType) {
			case "Chrome":
				GoogleDriver gg = new GoogleDriver("ExeDriver/chromedriver.exe");
				driver = gg.getDriver();
				//调用隐式等待设置
				invisibleWait();
				break;
			case "FireFox":
				FFDriver ff = new FFDriver("C:\\Program Files\\Mozilla Firefox\\firefox.exe",
						"ExeDriver/geckodriver.exe");
				driver = ff.getDriver();
				//调用隐式等待设置
				invisibleWait();
				break;
			case "IE":
				IEDriver ie = new IEDriver("ExeDriver/IEDriver.exe");
				driver = ie.getDriver();
				//调用隐式等待设置
				invisibleWait();
				break;
			default:
				GoogleDriver chrome = new GoogleDriver("ExeDriver/chromedriver.exe");
				driver = chrome.getDriver();
				//调用隐式等待设置
				invisibleWait();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author xn087454 完成网页访问的方法
	 * @param url 访问的网站的url地址
	 */

	public void visitWeb(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inputByName(String name, String content) {
		try {
			WebElement ele = driver.findElement(By.name(name));
			// 清空当前输入框内容
			ele.clear();
			//元素输入simulate typing into an element, which may set its value
			ele.sendKeys(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void click(String XPath) {
		try {
			driver.findElement(By.xpath(XPath)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	/**
	 * @author xn087454 
	 * 显式等待 可以自由灵活地进行等待时间的指定
	 * @param 
	 */
	public void visibleWait(final String XPath) {
		WebDriverWait eWait = new WebDriverWait(driver, 10);
		eWait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				WebElement ele = d.findElement(By.xpath(XPath));
				return ele;
			}
		});
	}
	/**
	 * @author xn087454 
	 * 隐式等待 发生在元素定位时，也就是findElement方法执行时，等待这个元素能够被定位到。一次设置，在脚本范围内，driver对象中生效。
	 * 可以直接在完成driver实例化之后调用，切换窗口之后最好再设置一次。
	 * @param 
	 */
	public void invisibleWait() {
		//.manage()-->Gets the Option interface
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * @author xn087454 设置强制等待waitTime时长
	 * @param waitTime单位为秒
	 */
	public void halt(String waitTime) {
		try {
			int time = 0;
			time = Integer.parseInt(waitTime);
			Thread.sleep(time * 1000);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("log-error:等待时间输入有误！！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author xn087454 悬停方法
	 * @param 要悬停的元素的xpath表达式
	 */
	public void hover(String XPath) {
		try {
			//org.openqa.selenium.interactions.Actions类：用于模拟复杂用户手势的面向用户的API。使用这个类而不是直接使用键盘或鼠标。
			//复杂的用户手势包括，单击，双击，右键单击，鼠标移动到元素上，拖拽移动等
			Actions act = new Actions(driver);
			WebElement ele = driver.findElement(By.xpath(XPath));
			act.moveToElement(ele).perform();//.perform方法动作执行
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author xn087454
	 *
	 */
	public void switchWindow(String target) {
		// 创建一个字符串便于之后存放句柄
		String targetHandle = null;
		// 获取所有窗口句柄集合
		Set<String> allhandles = driver.getWindowHandles();
		// 循环尝试，找到目标窗口的句柄
		for (String handle : allhandles) {
			System.out.println(handle);
			// 遍历每一个句柄，判断窗口的辩题是否等于预期字符，如果尝试进行了切换之后，获取得到的标题是预期标题，那么这个句柄就是目标句柄
			// .switchTo()-->Send future commands to a different frame or window.
			if (driver.switchTo().window(handle).getTitle().equals(target)) {
				targetHandle = handle;
			}
		}
		// 切换到目标句柄的页面中
		try {
			driver.switchTo().window(targetHandle);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 进入iframe子页面
	public void intoIFrame(String XPath) {
		try {
			// 定位到iframe元素，然后传递webelement给switchTo().frame()方法
			WebElement frame = driver.findElement(By.xpath(XPath));
			driver.switchTo().frame(frame);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("切入iframe失败！");
		}
	}

	public void intoIFrameByName(String name) {
		try {
			driver.switchTo().frame(name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("切入iframe失败！");
		}
	}

	// 退出子页面
	public void outIframe() {
		try {
			// 切回主页面
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("切入主页面失败！");
		}
	}

	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取js运行结果
	public String getJs(String text) {
		String t = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			t = js.executeScript("return " + text).toString();
		} catch (Exception e) {
			System.out.println("JS脚本执行失败！");
		}
		return t;
	}

	// 执行无返回的js脚本
	public void runJs(String text) {
		// 强转driver为js执行器类型
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript(text);
		} catch (Exception e) {
			System.out.println("JS脚本执行失败！");
		}
	}

	// 执行浏览器滚动
	public void scrollWindowStraight(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String jsCmd = "window.scrollTo(0," + height + ")";
			js.executeScript(jsCmd);
		} catch (Exception e) {
			System.out.println("操作浏览器滚动条失败");
		}
	}

	public void assertTitle(String expect) {
		String title = getTitle();
		if (title.contains(expect)) {
			System.out.println("测试执行成功！");
		} else {
			System.out.println("测试执行失败！");
		}
	}
	public void uploadFile(String XPath,String filepath) {
		try {
			driver.findElement(By.xpath(XPath)).sendKeys(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void input(String XPath, String content) {
		driver.findElement(By.xpath(XPath)).clear();
		driver.findElement(By.xpath(XPath)).sendKeys(content);
	}

	/**
	 * @author xn087454 直接传递By类型进行定位
	 * @param by
	 * @param content
	 */
	public void inputBylocator(By by, String content) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(content);
	}

	public void assertElementTextIs(String cssSelector, String expect) {
		String text = driver.findElement(By.cssSelector(cssSelector)).getText();
		// 获取元素的href属性
		String attr = driver.findElement(By.cssSelector(cssSelector)).getAttribute("href");
		System.out.println(text);
		System.out.println("href属性值是：" + attr);

		if (text.equals(expect)) {
			System.out.println("测试成功");
		} else {
			System.out.println("测试失败");
		}
	}
	/**
	 * 断言页面源码中包含某个内容
	 * 
	 * @param target 页面中预期包含的内容
	 */
	public void assertPageContains(String target) {
		String pageContent = driver.getPageSource();
		if (pageContent.contains(target)) {
			System.out.println("测试成功！");
		} else {
			System.out.println("测试失败！");
		}
	}

	public void multiElementText(String XPath) {
		List<WebElement> alist = driver.findElements(By.xpath(XPath));
		for (WebElement ele : alist) {
			System.out.println(ele.getText());
		}
	}
	

}
