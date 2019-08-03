package com.testing.class4;

import com.testing.web.KeywordOfWeb;

public class BackGroundOper {

	public static void main(String[] args) {
		KeywordOfWeb kw=new KeywordOfWeb();
		
		kw.openBrowser("Chrome");
		kw.visitWeb("http://112.74.191.10:8000/Admin/Admin/login");
		
		//在商城后台登录页面输入用户名密码 图形验证码
		kw.input("//input[@name='username']", "admin");
		kw.input("//input[@name='password']", "123456");
		kw.input("//input[@name='vertify']", "1");
		
		//点击登录
		kw.click("//input[@name='submit']");
		
		kw.click("//a[text()='商城']");
		
		//切到iframe
		kw.intoIFrameByName("workspace");
		kw.click("//div[@title='添加商品']");
		
		//填写商品通用信息
		kw.input("//input[@name='goods_name']", "戴森吸尘器2");
		kw.halt("2");
		kw.selectValue("//select[@id='cat_id']", "52");
		kw.selectValue("//select[@id='cat_id_2']", "53");
		kw.selectVisibleText("//select[@id='cat_id_3']", "吸尘器");
		
		kw.input("//input[@name='shop_price']", "3000");
		kw.input("//input[@name='market_price']", "3500");
		
	
		
		//是否包邮
		kw.click("//label[@id='is_free_shipping_label_1' and text()='是']");
		
		//
		kw.click("//span[@class='type-file-box']/input[@class='type-file-file']");
		kw.intoIFrame("//iframe[contains(@name,'layui-layer-iframe')]");
		kw.uploadFile("//div[text()='点击选择文件']/following-sibling::div/input[@name='file']",
				"E:\\GitRepo\\webAuto\\Vip03AutoM\\cleaner.jpg");
		kw.click("//div[@class='saveBtn']");

		// 文件上传在layui iframe中，切出来，再切到workspace里
		//kw.outIframe();
		kw.intoIFrameByName("workspace");
		// 输入商品关键字
		kw.input("//input[@name='keywords']", "吸尘器");
		// 切换到富文本框，通过js对其输入内容
		kw.intoIFrameByName("ueditor_0");
		kw.runJs("document.getElementsByTagName(\"p\")[0].innerText=\"这是一款高端吸尘器\"");
		// 再次切回workspace
		kw.outIframe();
		kw.intoIFrameByName("workspace");
		// 点击确认提交
		kw.click("//a[text()='确认提交']");
		kw.halt("5");
		kw.closeBrowser();
		
		

	}

}
