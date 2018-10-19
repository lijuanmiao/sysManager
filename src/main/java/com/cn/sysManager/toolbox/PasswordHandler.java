package com.cn.sysManager.toolbox;

import org.springframework.stereotype.Component;

/**
 * 密码加密类
 * @author lijm
 *
 */
@Component
public class PasswordHandler {

	/**
	 * 密码加密
	 * @param password
	 * @return
	 * @author lijm
	 */
	public static String getPassword(String password) {
		return Md5.getMD5ofStrByUpperCase("lijm2016" + password);
	}
}