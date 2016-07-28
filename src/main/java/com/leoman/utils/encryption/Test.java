package com.leoman.utils.encryption;

import com.leoman.utils.WebserviceUtil;

public class Test {

	public static void main(String[] args) {

		String content = "{\"request_no\":\"1001\",\"service_code\":\"FS0001\",\"contract_id\":\"100002\",\"order_id\":\"0\",\"phone_id\":\"13913996922\",\"plat_offer_id\":\"100094\",\"channel_id\":\"1\",\"activity_id\":\"100045\"}";
		try {
			long lStart = System.currentTimeMillis();
			byte[] encryptResultStr = BackAES.encrypt(content, BackAES.TYPE_ECB);
			String encrypt = new String(encryptResultStr);
			System.out.println("加密后"+ encrypt);
			long lUseTime = System.currentTimeMillis() - lStart;
			System.out.println("加密耗时：" + lUseTime + "毫秒");


			lStart = System.currentTimeMillis();
			String decryptString=BackAES.decrypt(new String(encryptResultStr),BackAES.TYPE_ECB);
			System.out.println("解密后"+decryptString);
			lUseTime = System.currentTimeMillis() - lStart;
			System.out.println("解密耗时：" + lUseTime + "毫秒");

			String url = "http://localhost:8088/velocity/admin/test/aes?" + encrypt;
//			String url = "http://localhost:8088/velocity/admin/test/user?uname=admin";
			System.out.println("请求路径:" + url);

			WebserviceUtil.post(url);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
