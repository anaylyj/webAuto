package com.testing.class12;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jayway.jsonpath.JsonPath;

public class ParamTrans {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 创建client
		HttpClient client = HttpClients.createDefault();
		Map<String, String> paramsMap=new HashMap<String,String>();
		// 使用一个map来保存需要添加的头域键值对
		Map<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Accept", "*/*");
		headersMap.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
		headersMap.put("Content-Type", "x-www-form-urlencoded");
		Set<String> keySet = headersMap.keySet();
		System.out.println(keySet);
		/**
		 * 鉴权接口 auth
		 */
		HttpPost authPost = new HttpPost("http://testingedu.com.cn/inter/HTTP/auth");
		// 通过foreach循环遍历map中所有的键值对，逐个作为头域添加
		for (String key : keySet) {
			authPost.addHeader(key, headersMap.get(key));
		}
		HttpResponse authResp = client.execute(authPost);
		String authStr = EntityUtils.toString(authResp.getEntity());
		System.out.println(authStr);
		paramsMap.put("token", JsonPath.read(authStr, "$.token").toString());
//		// 解析得到token值
//		String tokenValue = JsonPath.read(authStr, "$.token");
//		System.out.println("token值是：" + tokenValue);
//		// 在头域map中添加token和对应的值
//		headersMap.put("token", tokenValue);
		headersMap.put("token", paramsMap.get("token"));
		/**
		 * 注册接口 register
		 */
		HttpPost registerPost = new HttpPost(
				"http://testingedu.com.cn/inter/HTTP/register?username=roy17&pwd=123456&nickname=roy13th&describe=teacher");
		// 通过foreach循环遍历map中所有的键值对，逐个作为头域添加
		for (String key : keySet) {
			registerPost.addHeader(key, headersMap.get(key));
		}
//				registerPost.setHeader("token",tokenValue);
		HttpResponse registerResp = client.execute(registerPost);
		String registerStr = EntityUtils.toString(registerResp.getEntity());
		System.out.println(registerStr);

		/**
		 * 登录接口Login
		 */
		HttpPost loginPost = new HttpPost(
				"http://www.testingedu.com.cn/inter/HTTP/login?username=roy17&password=123456");
		// 添加必要的请求头
//		loginPost.addHeader("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
//				loginPost.addHeader("token",tokenValue);
		for (String key : keySet) {
			loginPost.addHeader(key, headersMap.get(key));
		}
		// 执行请求方法，获取返回包
		HttpResponse loginRes = client.execute(loginPost);
		// 获取返回实体
		HttpEntity loginEntity = loginRes.getEntity();
		// 返回实体解析为String格式
		String loginString = EntityUtils.toString(loginEntity);
		System.out.println(loginString);
//		String id = JsonPath.read(loginString, "$.userid");
//		System.out.println(id);
		paramsMap.put("idValue", JsonPath.read(loginString, "$.userid").toString());
		System.out.println(paramsMap);
		/**
		 * 用户接口 getUserInfo
		 */
		
		String url="http://www.testingedu.com.cn/inter/HTTP//getUserInfo?id={idValue}";
		//替换{参数名}为参数值
		String urlResult="";
		//key是map中的每个键
		for(String key:paramsMap.keySet()) {
			url=url.replaceAll("\\{"+key+"\\}", paramsMap.get(key));
		}
		System.out.println("url结果："+url);
		
		HttpPost userPost = new HttpPost(url);
//				userPost.addHeader("token",tokenValue);
		for (String key : keySet) {
			userPost.addHeader(key, headersMap.get(key));
		}
		// 执行请求方法，获取返回包
		HttpResponse userRes = client.execute(userPost);
		// 获取返回实体
		HttpEntity userEntity = userRes.getEntity();
		// 返回实体解析为String格式
		String userString = EntityUtils.toString(userEntity);
		System.out.println(userString);
		/**
		 * 登出接口
		 */
		HttpPost logoutPost = new HttpPost("http://www.testingedu.com.cn/inter/HTTP/logout");
		// 添加必要的请求头
//		logoutPost.addHeader("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
//				logoutPost.addHeader("token",tokenValue);
		for (String key : keySet) {
			logoutPost.addHeader(key, headersMap.get(key));
		}
		// 执行请求方法，获取返回包
		HttpResponse logoutRes = client.execute(logoutPost);
		// 获取返回实体
		HttpEntity logoutEntity = logoutRes.getEntity();
		// 返回实体解析为String格式
		String logoutString = EntityUtils.toString(logoutEntity);
		System.out.println(logoutString);

	}

}
