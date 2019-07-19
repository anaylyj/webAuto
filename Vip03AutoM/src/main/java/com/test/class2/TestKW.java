package com.test.class2;

import com.testing.web.KeywordOfWeb;

public class TestKW {

	public static void main(String[] args) {
		KeywordOfWeb kw = new KeywordOfWeb();
		kw.openBrowser("Chrome");
		kw.visitWeb("https://www.baidu.com/");
		kw.inputByName("wd", "特斯汀学院");
		kw.click("//input[@id='su']");
		kw.halt("10");
		kw.assertTitle("特斯汀学院");
		kw.closeBrowser();

	}

}
