package org.tplatform.framework.util;

import org.tplatform.framework.log.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tianyi on 2014/12/3.
 */
public class MD5Util {

  private final static String ALGORITHM = "MD5";  //加密算法

  private MD5Util() {
  }

  /**
   * MD5普通加密
   *
   * @param rawPass 明文
   * @return
   */
  public final static String encode(String rawPass) {
    return encode(rawPass, null);
  }

  /**
   * MD5普通加密
   *
   * @param rawPass 明文
   * @return
   */
  public final static String encode(String rawPass, boolean toLowerCase) {
    return encode(rawPass, null, toLowerCase);
  }

  /**
   * MD5盐值加密
   *
   * @param rawPass 明文
   * @param key     盐值
   * @return
   */
  public final static String encode(String rawPass, String key) {
    String saltedPass = (StringUtil.isEmpty(key)) ? rawPass : rawPass + key;
    try {
      return Hex.encodeHexStr(MessageDigest.getInstance(ALGORITHM).digest(saltedPass.getBytes()));
    } catch (NoSuchAlgorithmException e) {
      Logger.e("encode", e);
      return null;
    }
  }

  /**
   * MD5盐值加密
   *
   * @param rawPass 明文
   * @param key     盐值
   * @return
   */
  public final static String encode(String rawPass, String key, boolean toLowerCase) {
    String saltedPass = (StringUtil.isEmpty(key)) ? rawPass : rawPass + key;
    try {
      return Hex.encodeHexStr(MessageDigest.getInstance(ALGORITHM).digest(saltedPass.getBytes()), toLowerCase);
    } catch (NoSuchAlgorithmException e) {
      Logger.e("encode", e);
      return null;
    }
  }

  /**
   * 加密文件
   *
   * @param inputFile
   * @return
   * @throws IOException
   */
  public static String encodeFile(String inputFile) throws IOException {
    int bufferSize = 256 * 1024;// 缓冲区大小（这个可以抽出一个参数）
    FileInputStream fileInputStream = null;
    DigestInputStream digestInputStream = null;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
      fileInputStream = new FileInputStream(inputFile);
      digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
      byte[] buffer = new byte[bufferSize];// read的过程中进行MD5处理，直到读完文件
      while (digestInputStream.read(buffer) > 0) ;
      messageDigest = digestInputStream.getMessageDigest();// 获取最终的MessageDigest
      return Hex.encodeHexStr(messageDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        digestInputStream.close();
      } catch (Exception e) {
        Logger.e("encodeFile", e);
      }
      try {
        fileInputStream.close();
      } catch (Exception e) {
        Logger.e("encodeFile", e);
      }
    }
  }

//  /**
//   * 将字节数组换成成16进制的字符串
//   *
//   * @param byteArray
//   * @return
//   */
//  public static String byteArrayToHex(byte[] byteArray) {
//    // 首先初始化一个字符数组，用来存放每个16进制字符
//    char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//    // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
//    char[] resultCharArray = new char[byteArray.length * 2];
//    // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
//    int index = 0;
//    for (byte b : byteArray) {
//      resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
//      resultCharArray[index++] = hexDigits[b & 0xf];
//    }
//    // 字符数组组合成字符串返回
//    return new String(resultCharArray);
//  }
}
