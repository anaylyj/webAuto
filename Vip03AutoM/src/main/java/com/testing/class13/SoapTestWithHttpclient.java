package com.testing.class13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jayway.jsonpath.JsonPath;
import com.testing.inter.KeywordOfInter;

public class SoapTestWithHttpclient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter key=new KeywordOfInter();
		key.testPostSoap("http://www.testingedu.com.cn/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:auth></soap:auth></soapenv:Body></soapenv:Envelope>");
		key.saveParam("tokenValue", "$.token");
		key.addHeader("{\"token\":\"{tokenValue}\"}");
		key.testPostSoap("http://www.testingedu.com.cn/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:login><arg0>roy96</arg0><arg1>123456</arg1></soap:login></soapenv:Body></soapenv:Envelope>");
	}

}
