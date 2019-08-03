package com.testing.class13;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.testing.inter.KeywordOfInter;

public class RestTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		KeywordOfInter key=new KeywordOfInter();
		key.testPost("http://www.testingedu.com.cn/inter/REST/auth", "");
		//存储拿到的token
		key.saveParam("tokenValue", "$.token");
		//添加token头域，值为上一步拿到的tokenValue
		key.addHeader("{\"token\":\"{tokenValue}\"}");
		key.testPost("http://www.testingedu.com.cn/inter/REST/user/register?%7B%22username%22%3A%22anay99%22%2C%22pwd%22%3A%22123456%22%2C%22nickname%22%3A%22roy%E8%80%81%E5%B8%88%22%2C%22describe%22%3A%22%E6%B5%8B%E8%AF%95%E8%B4%A6%E5%8F%B7%22%7D", "");
		key.testPost("http://www.testingedu.com.cn/inter/REST/login/anay99/123456", "");
		//保存id值
		key.saveParam("idValue", "$.userid");
		//在url中携带id参数并解析
		key.testPost("http://www.testingedu.com.cn/inter/REST/login/{idValue}", "");
		key.testPost("http://www.testingedu.com.cn/inter/REST/login", "");
	}

}
