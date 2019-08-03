package com.testing.class9;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class BaiduIPTest {

	public static void main(String[] args) {
	CloseableHttpClient httpclient=	HttpClients.createDefault();
	HttpGet httpget=new HttpGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=1.1.1.1&co=&resource_id=6006&t=1563802333966&ie=utf8&oe=gbk");
	try {
		HttpResponse resp=httpclient.execute(httpget);
		HttpEntity respen=resp.getEntity();
		String respstr=respen.toString();
		System.out.println(respstr);
	} catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	

	}

}
