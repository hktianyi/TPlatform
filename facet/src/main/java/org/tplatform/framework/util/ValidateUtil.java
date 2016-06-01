package org.tplatform.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验公共类
 */
public class ValidateUtil {
	
	/**
	* 验证邮箱
	* @param str
	* 		       待验证的字符串
	* @return boolean
	*/
	public static boolean isEmail(String str) {
	String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	return match(regex, str);
	}

	
	/**
	* @param regex
	* 			正则表达式字符串
	* @param str
	* 			要匹配的字符串
	* @return 正确返回 true, 否则返回 false;
	*/
	private static boolean match(String regex, String str) {
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(str);
	return matcher.matches();
	}
	
}
