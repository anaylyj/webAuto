package com.testing.class3;

import org.openqa.selenium.WebDriver;

import com.testing.web.KeywordOfWeb;

public class CompletePersonalInfo {

	public static void main(String[] args) {
		KeywordOfWeb kw=new KeywordOfWeb();
		
		kw.openBrowser("Chrome");
		kw.visitWeb("http://www.testingedu.com.cn:8000/");
		//在商城首页点击登录
		kw.click("//a[text()='登录' and @class='red']");
		
		//在登录页输入用户名，密码，验证码
		kw.input("//input[@id='username']", "1355214222@qq.com");
		kw.input("//input[@id='password']", "aa111111");
		kw.input("//input[@id='verify_code']", "12");
		
		//点击登录
		//a[@name='sbtbutton']
		kw.click("//a[@name='sbtbutton']");
		
		kw.click("//div[@class='home-index-middle']//a[text()='个人信息']");
		
		//个人信息页面，上传头像，完善其他个人信息
		kw.click("//img[@id='preview']");
		kw.intoIFrame("//div[@class='layui-layer-content']/iframe");
		kw.uploadFile("//div[text()='点击选择图片']//following-sibling::div/input", "D:\\profileimg.jpg");
		kw.click("//div[@class='saveBtn']");
		//个人信息页面，完善其他个人信息
		kw.input("//input[@id='nickname']", "彤宝宝");
		kw.click("//input[@id='woman']");
		kw.input("//input[@id='birthday']", "2017-09-19");
		kw.click("//input[@value='确认保存']");
	}

}
