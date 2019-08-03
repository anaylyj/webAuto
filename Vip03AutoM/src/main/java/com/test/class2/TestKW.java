package com.test.class2;

import org.apache.log4j.BasicConfigurator;

import com.testing.autocommon.AutoLogger;
import com.testing.web.KeywordOfWeb;

public class TestKW {

	public static void main(String[] args) {
		KeywordOfWeb kw = new KeywordOfWeb();	
		
		AutoLogger.log.info("---------测试开始------------");
		kw.openBrowser("Chrome");
		kw.visitWeb("https://www.baidu.com/");
		kw.inputByName("wd", "特斯汀学院");
		kw.click("//input[@id='sudfz']");
		kw.halt("10");
		kw.assertTitle("特斯汀学院");
		kw.closeBrowser();
		AutoLogger.log.info("---------测试结束------------");

	}

}
