package org.tplatform.framework.util;

/**
 * 密码工具类
 * @author lh
 *
 */
public class PasswordUtil {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return new StringBuffer().append(Hex.encodeHex(salt)).append(Hex.encodeHex(hashPassword)).toString();
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		
		if(password!=null&&password.length()>=16){
			byte[] salt = Hex.decodeHex(password.substring(0,16).toCharArray());
			byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
			return password.equals(new StringBuffer().append(Hex.encodeHex(salt)).append(Hex.encodeHex(hashPassword)).toString());
		}else{
			return false;
		}
	}
}
