package com.testing.class12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

public class ParamTest {

	//使用map存储参数键值对
	public static Map<String, String> paramMap=new HashMap<String,String>();
	
	//替换{参数名}为参数值
	public static String toParam(String origin) {
		//传递原字符串
		String param=origin;
		//遍历参数池中所有的键值对，替换能够匹配的{参数名}为参数值
		for(String key:paramMap.keySet()) {
		param=param.replaceAll("\\{"+key+"\\}", paramMap.get(key));
		}
		//返回值为替换之后的参数列表
		return param;
	}
	
	public static void main(String[] args) {
		//从结果中解析出id和status值
		String a="{\"status\":200,\"msg\":\"恭喜您，登录成功\",\"userid\":\"5\"}";
		String id=JsonPath.read(a,"$.userid");
		String status=JsonPath.read(a,"$.status").toString();
		//存入参数池中
		paramMap.put("id", id);
		paramMap.put("name", "roy");
		paramMap.put("status", status);
		paramMap.put("age", "28");
		System.out.println("参数池里的内容"+paramMap);
		//调用参数替换方法，从参数池中读取相应的值，替换参数列表中的内容
		String testParam="id1={id}&id={id}&status={status}&name=\"{nam}\"";
		System.out.println(toParam(testParam));
	}
}
