package com.javajy.sdk.pay;

import java.util.HashMap;
import java.util.Map;

import com.javajy.sdk.pay.WXPayConstants.SignType;

public class FRPayUtil extends FRPayConfig {

	private String appid;
	private String mchid;
	
	public FRPayUtil() throws Exception {
		super();
	}

	public static void main(String[] args) throws Exception{
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("fee_type", "CNY");
        //data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        FRPayUtil util = new FRPayUtil();
        util.appid="wx3785bcc0cef80969";
        util.mchid="1605978112";
        util.pay(data);
	}
	
	public Map<String, String> wxgzhPay(Map<String, String> data) throws Exception{
		//data = pay2(data);
		data = pay(data);
		return payResult(data);
	}
	
	private Map<String, String> pay(Map<String, String> data) throws Exception{
        WXPay wxpay = new WXPay(this);
        Map<String, String> result = new HashMap<String, String>();
		try {
			data.put("device_info", "");
	        data.put("fee_type", "CNY");
	        //data.put("total_fee", "1");
	        data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
	        //data.put("product_id", "12");
            Map<String, String> resp = wxpay.unifiedOrder(data);
            result.put("prepay_id", resp.get("prepay_id"));
            result.put("appId", resp.get("appid"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}
	
	private Map<String, String> pay2(Map<String, String> data) throws Exception{
        WXPay wxpay = new WXPay(this);
        Map<String, String> result = new HashMap<String, String>();
		try {
			data.put("device_info", "");
	        data.put("fee_type", "CNY");
	        //data.put("total_fee", "1");
	        data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
	        //data.put("product_id", "12");
            Map<String, String> resp = wxpay.unifiedOrder(data);
            result.put("prepay_id", resp.get("prepay_id"));
            result.put("appId", resp.get("appid"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}
	
	private Map<String, String> payResult(Map<String, String> data) throws Exception{
		try {
	        data.put("appId",this.appid);//appid
			//data.put("appId",WXPayConstants.appid);//appid
	        data.put("nonceStr",WXPayUtil.generateNonceStr());//随机字符串
	        String prepayId = data.get("prepay_id");
	    	data.put("package","prepay_id="+data.get("prepay_id"));//扩展字段
	    	data.put("signType","HMAC-SHA256");
	    	data.put("timeStamp",WXPayUtil.getCurrentTimestamp()+"");//时间戳
	    	data.remove("prepay_id");
	    	String s = WXPayUtil.generateSignature(data, this.getKey(), SignType.HMACSHA256);  //签名
	    	data.put("package",prepayId);
	        data.put("paySign",s);
	        data.remove("signType");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return data;
	}
	
	public Map<String, String> queryOrderByOrderNum(Map<String, String> data) throws Exception{
		String orderNum = data.get("orderNum");
		System.out.println("orderNum="+orderNum);
		WXPay wxpay = new WXPay(this);
		return wxpay.queryOrderByOrderNum(orderNum,this.appid, this.mchid, this.getKey());
	}

	@Override
	public String getAppID() {
		return this.appid;
	}

	@Override
	public String getMchID() {
		return this.mchid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
}
