package com.test.class2;

import org.openqa.selenium.By;

import com.testing.web.KeywordOfWeb;

public class ShopLoginTest {

	public static void main(String[] args) {
		KeywordOfWeb kw=new KeywordOfWeb();
		kw.openBrowser("Chrome");
		//访问网页
		kw.visitWeb("http://www.testingedu.com.cn:8000/");
		kw.multiElementText("//ul[@id='navitems']/li/a");
		//点击登录按钮
		kw.click("//a[text()='登录' and @class='red']");
		//调用bylocator参数传递by对象完成定位
		kw.inputBylocator(By.name("username"), "13800138006");
		kw.input("//input[@name='password']", "123456");
		//输入万能验证码
		kw.input("//input[@name='verify_code']", "1");
		//点击登录按钮
		kw.click("//a[@class='J-login-submit']");
		kw.halt("5");
		//定位元素判断其内容是cc2
		kw.assertElementTextIs("a[class='mu-m-phone']", "cc2");
		kw.halt("2");
		kw.closeBrowser();

	}

}
