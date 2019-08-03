package com.testing.class12;

import com.testing.inter.KeywordOfInter;

public class TokenTestWithKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter key=new KeywordOfInter();
//		key.addHeader("{\"Accept\":\"*/*\",\"Content-Type\":\"x-www-form-urlencoded\"}");
		key.testPost("http://testingedu.com.cn/inter/HTTP/auth", "");
		key.assertSame("success","$.msg");
		key.saveParam("tokenValue", "$.token");
		key.addHeader("{\"token\":\"{tokenValue}\"}");
		//登录方法
		key.testPost("http://www.testingedu.com.cn/inter/HTTP/login", "username=roy17&password=123456");
		key.saveParam("idValue", "$.userid");
		key.assertContains("恭喜您","$.msg" );
		//获取用户信息
		key.testPost("http://www.testingedu.com.cn/inter/HTTP//getUserInfo", "id={idValue}");
		key.assertSame("查询成功", "$.msg");
		//退出登录
		key.testPost("http://www.testingedu.com.cn/inter/HTTP/logout", "");
		key.assertSame("用户已退出登录", "$.msg");
		
	}

}
