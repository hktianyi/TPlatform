package org.tplatform.util;

import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

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
	 * @param plainPassword 原文
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

	public static class Digests {

		private static final String SHA1 = "SHA-1";
		private static final String MD5 = "MD5";

		private static SecureRandom random = new SecureRandom();

		/**
		 * 对输入字符串进行sha1散列.
		 */
		public static byte[] sha1(byte[] input) {
			return digest(input, SHA1, null, 1);
		}

		public static byte[] sha1(byte[] input, byte[] salt) {
			return digest(input, SHA1, salt, 1);
		}

		public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
			return digest(input, SHA1, salt, iterations);
		}

		/**
		 * 对字符串进行散列, 支持md5与sha1算法.
		 */
		private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
			try {
				MessageDigest digest = MessageDigest.getInstance(algorithm);

				if (salt != null) {
					digest.update(salt);
				}

				byte[] result = digest.digest(input);

				for (int i = 1; i < iterations; i++) {
					digest.reset();
					result = digest.digest(result);
				}
				return result;
			} catch (GeneralSecurityException e) {
				Logger.e("Digests.digest", e);
				throw new RuntimeException(e);
			}
		}

		/**
		 * 生成随机的Byte[]作为salt.
		 *
		 * @param numBytes byte数组的大小
		 */
		public static byte[] generateSalt(int numBytes) {
			Assert.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger), but is " + numBytes);

			byte[] bytes = new byte[numBytes];
			random.nextBytes(bytes);
			return bytes;
		}

		/**
		 * 对文件进行md5散列.
		 */
		public static byte[] md5(InputStream input) throws IOException {
			return digest(input, MD5);
		}

		/**
		 * 对文件进行sha1散列.
		 */
		public static byte[] sha1(InputStream input) throws IOException {
			return digest(input, SHA1);
		}

		private static byte[] digest(InputStream input, String algorithm) throws IOException {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
				int bufferLength = 8 * 1024;
				byte[] buffer = new byte[bufferLength];
				int read = input.read(buffer, 0, bufferLength);

				while (read > -1) {
					messageDigest.update(buffer, 0, read);
					read = input.read(buffer, 0, bufferLength);
				}

				return messageDigest.digest();
			} catch (GeneralSecurityException e) {
				Logger.e("Digests.digest", e);
				throw new RuntimeException(e);
			}
		}

	}

	public static class Hex {

		/**
		 * 用于建立十六进制字符的输出的小写字符数组
		 */
		private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

		/**
		 * 用于建立十六进制字符的输出的大写字符数组
		 */
		private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

		/**
		 * 将字节数组转换为十六进制字符数组
		 *
		 * @param data byte[]
		 * @return 十六进制char[]
		 */
		public static char[] encodeHex(byte[] data) {
			return encodeHex(data, true);
		}

		/**
		 * 将字节数组转换为十六进制字符数组
		 *
		 * @param data        byte[]
		 * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
		 * @return 十六进制char[]
		 */
		public static char[] encodeHex(byte[] data, boolean toLowerCase) {
			return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
		}

		/**
		 * 将字节数组转换为十六进制字符数组
		 *
		 * @param data     byte[]
		 * @param toDigits 用于控制输出的char[]
		 * @return 十六进制char[]
		 */
		protected static char[] encodeHex(byte[] data, char[] toDigits) {
			int l = data.length;
			char[] out = new char[l << 1];
			// two characters form the hex value.
			for (int i = 0, j = 0; i < l; i++) {
				out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
				out[j++] = toDigits[0x0F & data[i]];
			}
			return out;
		}

		/**
		 * 将字节数组转换为十六进制字符串
		 *
		 * @param data byte[]
		 * @return 十六进制String
		 */
		public static String encodeHexStr(byte[] data) {
			return encodeHexStr(data, true);
		}

		/**
		 * 将字节数组转换为十六进制字符串
		 *
		 * @param data        byte[]
		 * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
		 * @return 十六进制String
		 */
		public static String encodeHexStr(byte[] data, boolean toLowerCase) {
			return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
		}

		/**
		 * 将字节数组转换为十六进制字符串
		 *
		 * @param data     byte[]
		 * @param toDigits 用于控制输出的char[]
		 * @return 十六进制String
		 */
		protected static String encodeHexStr(byte[] data, char[] toDigits) {
			return new String(encodeHex(data, toDigits));
		}

		/**
		 * 将十六进制字符数组转换为字节数组
		 *
		 * @param data 十六进制char[]
		 * @return byte[]
		 * @throws RuntimeException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
		 */
		public static byte[] decodeHex(char[] data) {
			int len = data.length;
			if ((len & 0x01) != 0) {
				throw new RuntimeException("Odd number of characters.");
			}
			byte[] out = new byte[len >> 1];
			// two characters form the hex value.
			for (int i = 0, j = 0; j < len; i++) {
				int f = toDigit(data[j], j) << 4;
				j++;
				f = f | toDigit(data[j], j);
				j++;
				out[i] = (byte) (f & 0xFF);
			}
			return out;
		}

		/**
		 * 将十六进制字符转换成一个整数
		 *
		 * @param ch    十六进制char
		 * @param index 十六进制字符在字符数组中的位置
		 * @return 一个整数
		 * @throws RuntimeException 当ch不是一个合法的十六进制字符时，抛出运行时异常
		 */
		protected static int toDigit(char ch, int index) {
			int digit = Character.digit(ch, 16);
			if (digit == -1) {
				throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
			}
			return digit;
		}
	}
}
