package com.javajy.sdk.pay;

import java.util.HashMap;
import java.util.Map;

public class PayTest {

	public static void main(String[] args) {
		

		FRPayUtil util;
		try {
			util = new FRPayUtil();
			util.setAppid("wx3785bcc0cef80969");
	        util.setMchid("1605978112");
	        Map<String, String> data = new HashMap<String,String>();
	        data.put("orderNum", "2016090910595900000014");
	        data = util.queryOrderByOrderNum(data);
	        System.out.println(data.get("trade_state"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
