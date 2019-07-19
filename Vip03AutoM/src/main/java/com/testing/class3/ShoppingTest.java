package com.testing.class3;

import org.openqa.selenium.By;

import com.testing.web.KeywordOfWeb;

public class ShoppingTest {

	public static void main(String[] args) {
		KeywordOfWeb kw = new KeywordOfWeb();
		kw.openBrowser("Chrome");
		// 访问网页
		kw.visitWeb("http://www.testingedu.com.cn:8000/");

		kw.multiElementText("//ul[@id='navitems']/li/a");
		// 点击登录按钮
		kw.click("//a[text()='登录' and @class='red']");
		// 调用bylocator参数传递by对象完成定位
		kw.inputBylocator(By.name("username"), "13800138006");
		kw.input("//input[@name='password']", "123456");
		// 输入万能验证码
		kw.input("//input[@name='verify_code']", "1");
		// 点击登录按钮
		kw.click("//a[@class='J-login-submit']");
		kw.halt("3");
		//定位元素判断其内容是cc2
		kw.assertElementTextIs("a[class='mu-m-phone']", "cc2");
		kw.click("//a[text()='首页']");
		kw.hover("//a[text()='手机数码']");
		kw.click("//a[text()='老人手机']/preceding-sibling::a");
		kw.switchWindow("商品列表");
		kw.click("//div[contains(@class,'shop-list-splb')]/ul/li[1]/div/div[1]/a");
		kw.click("//a[@id='join_cart']");
		kw.halt("3");

		// 切换到iframe
		//kw.intoIFrame("//div[@class='layui-layer-content']/iframe");
		
		kw.intoIFrameByName("layui-layer-iframe1");
		kw.click("//a[text()='去购物车结算']");

		// 点击去结算按钮
		kw.click("//a[text()='去结算']");
		// 因为隐式等待必须要在findelement时才生效，因此在js语句执行前手动强制等待。
		kw.halt("5");
		// 通过js语句填写备注
		String result = kw.getJs("document.getElementsByTagName(\"textarea\")[0].value=\"3333\"");
		System.out.println("js执行结果：" + result);
		// 滚动到0,300
		kw.scrollWindowStraight("300");
		// 点击提交订单
		kw.click("//button[@class='checkout-submit']");
		kw.halt("3");
		// 点击货到付款，以及确认
		kw.click("//img[@src='/plugins/payment/cod/logo.jpg']");
		kw.click("//a[text()='确认支付方式']");
		kw.assertPageContains("订单提交成功");

	}

}
