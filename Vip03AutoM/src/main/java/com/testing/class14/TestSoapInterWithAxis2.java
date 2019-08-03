package com.testing.class14;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.axis2.context.NamedValue;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.testing.inter.SoapInterKeyWord;

public class TestSoapInterWithAxis2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SoapInterKeyWord soap=new SoapInterKeyWord();
		try {
			//调用createcon建立起和wsdl之间的链接，后续的接口方法请求全部基于这一个地址
			soap.createCon("http://www.testingedu.com.cn/inter/SOAP?wsdl");
			String result=soap.doSoap("http://soap.testingedu.com/", "auth", "");
			//保存参数
			System.out.println(result);
			String tokenValue=JsonPath.read(result,"$.token");
			String token= "{\"token\":\""+tokenValue+"\",\"Pragma\":\"no-cache\"}";
			//添加header
			List<NamedValue> headers=new ArrayList<NamedValue>();
			JSONObject json = new JSONObject(token);
			Iterator<String> jsonit = json.keys();
			while (jsonit.hasNext()) {
				String jsonkey = jsonit.next();
				//将键值对以namedvalue的形式完成实例化创建
				NamedValue h = new NamedValue(jsonkey, json.get(jsonkey).toString());
				System.out.println(h);
				headers.add(h);
			}
			soap.addHeader(headers);
			//调用注册
			soap.doSoap("http://soap.testingedu.com/", "register", "{\"arg0\":\"roy3\",\"arg1\":\"123456\",\"arg2\":\"这是个老师\",\"arg3\":\"测试账号\"}");
			//调用授权
			soap.doSoap("http://soap.testingedu.com/", "auth", "");
			//登录
			result = soap.doSoap("http://soap.testingedu.com/", "login", "{\"arg0\":\"roy3\",\"arg1\":\"123456\"}");
			String id=JsonPath.read(result,"$.userid");
			//获取用户信息
			soap.doSoap("http://soap.testingedu.com/", "getUserInfo", "{\"arg0\":\""+id+"\"}");
			//注销登录
			soap.doSoap("http://soap.testingedu.com/", "logout","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
