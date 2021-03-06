package com.testing.class12;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.testing.autocommon.ExcelReader;
import com.testing.autocommon.ExcelWriter;
import com.testing.inter.DataDrivenOfInter;
import com.testing.inter.KeywordOfInter;

public class TestWithDataDriven {

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH+mm+ss");
		String createdate = sdf.format(date);
		ExcelWriter excelWrite=new ExcelWriter("Cases/HTTPLogin.xlsx", "Cases/Result-HTTPLogin"+createdate+".xlsx");
		DataDrivenOfInter dataDriven=new DataDrivenOfInter(excelWrite);
		ExcelReader excelCase=new ExcelReader("Cases/HTTPLogin.xlsx");
		//遍历sheet页
		for(int sheetNo=0;sheetNo<excelCase.getTotalSheetNo();sheetNo++) {
			//输出sheet页的名称
//			System.out.println(excelCase.getSheetName(sheetNo));
			//通过下标，指定读取对应的sheet页
			excelCase.useSheetByIndex(sheetNo);
			excelWrite.useSheetByIndex(sheetNo);
			//循环读取每一行
			for(int line=0;line<excelCase.rows;line++) {
				List<String> lineContent=excelCase.readLine(line);
//				System.out.println(lineContent);
				//如果一行中前两个单元格为空，那么这是用例行
				if((lineContent.get(0).length()==0&&lineContent.get(1).length()==0)
						||(lineContent.get(0)==null&&lineContent.get(0)==null))
				{
					//通过关键字列，选择使用的关键字
					switch (lineContent.get(3)) {
					case "post":
						String response=dataDriven.testPost(lineContent.get(4), lineContent.get(5));
						excelWrite.writeCell(line, 11, response);
						break;
					case "saveParam":
						dataDriven.saveParam(lineContent.get(4), lineContent.get(5));
						break;
					case "addHeader":
						dataDriven.addHeader(lineContent.get(4));
						break;
					}
					// 第二步，执行校验列对应的断言
					switch (lineContent.get(7)) {
					case "equal":
						dataDriven.assertSame(lineContent.get(9), lineContent.get(8));
						break;
					case "contains":
						dataDriven.assertContains(lineContent.get(9), lineContent.get(8));
						break;
					}
				}
			}
		}
		excelWrite.save();
		
		

	}

}
