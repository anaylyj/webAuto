package com.testing.autocommon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AutoLogger {
	//创建一个静态的logger对象，在全局范围内进行log4J功能的调用。
	public static final Logger log=LogManager.getLogger(AutoLogger.class);
}
