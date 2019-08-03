package com.testing.class12;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.testing.autocommon.ExcelWriter;

public class ExcelWriteTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH+mm+ss");
		String createdate = sdf.format(date);
		ExcelWriter excelWrite=new ExcelWriter("Cases/HTTPLogin.xlsx", "Cases/Result-HTTPLogin"+createdate+".xlsx");
		excelWrite.writeCell(0, 15, "测试正常输入");
		excelWrite.writeFailCell(0, 16, "测试异常输入");
		excelWrite.save();
		
	}

}
