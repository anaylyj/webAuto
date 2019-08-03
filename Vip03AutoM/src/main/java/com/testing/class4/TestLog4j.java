package com.testing.class4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j {

	public static void main(String[] args) {
		Logger logger=LogManager.getLogger(TestLog4j.class);
		
		logger.debug("This is a debug");
		
		logger.info("This is an info.");
		logger.info("----------------------------测试开始-----------------------------");
		logger.warn("This is a warning");
		
		try {
			Integer.parseInt("ss");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e, e.fillInStackTrace());
		}
		
		logger.fatal("This is a fatal error");
		return;

	}

}
