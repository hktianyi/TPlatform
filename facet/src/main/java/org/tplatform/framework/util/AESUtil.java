package org.tplatform.framework.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES Coder
 * secret key length:	128bit, default:	128 bit
 * mode:	ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128
 * padding:	Nopadding/PKCS5Padding/ISO10126Padding/
 * Created by Tianyi on 2014/11/24.
 */
public class AESUtil {

  /**
   * 密钥算法
   */
  private static final String KEY_ALGORITHM = "AES";
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES";//"AES/ECB/PKCS5Padding"

  /**
   * 初始化密钥
   */
  public static void createSecretKey(String password, String path) {
    //返回生成指定算法的秘密密钥的 KeyGenerator 对象
    KeyGenerator kg;
    try {
      kg = KeyGenerator.getInstance(KEY_ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return;
    }
    //初始化此密钥生成器，使其具有确定的密钥大小
    //AES 要求密钥长度为 128
    kg.init(128, new SecureRandom(password.getBytes()));
    //生成一个密钥
    SecretKey secretKey = kg.generateKey();
    Key key = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);

    FileOutputStream fileOut = null;
    ObjectOutputStream out = null;
    try {
      fileOut = new FileOutputStream(path);
      out = new ObjectOutputStream(fileOut);
      out.writeObject(key);
      System.out.println("成功生成私钥文件：" + path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) out.close();
        if (fileOut != null) fileOut.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 加密
   *
   * @param data    待加密数据
   * @param keyPath 密钥文件路径
   * @return byte[]    加密数据
   * @throws Exception
   */
  public static byte[] encrypt(byte[] data, String keyPath) throws Exception {
    return encrypt(data, readKey(keyPath), DEFAULT_CIPHER_ALGORITHM);
  }

  /**
   * 加密
   *
   * @param data            待加密数据
   * @param key             密钥
   * @param cipherAlgorithm 加密算法/工作模式/填充方式
   * @return byte[]    加密数据
   * @throws Exception
   */
  public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
    //实例化
    Cipher cipher = Cipher.getInstance(cipherAlgorithm);
    //使用密钥初始化，设置为加密模式
    cipher.init(Cipher.ENCRYPT_MODE, key);
    //执行操作
    return cipher.doFinal(data);
  }


  /**
   * 解密
   *
   * @param data    待解密数据
   * @param keyPath 密钥文件路径
   * @return byte[]    解密数据
   * @throws Exception
   */
  public static byte[] decrypt(byte[] data, String keyPath) throws Exception {
    return decrypt(data, readKey(keyPath), DEFAULT_CIPHER_ALGORITHM);
  }

  /**
   * 解密
   *
   * @param data            待解密数据
   * @param key             密钥
   * @param cipherAlgorithm 加密算法/工作模式/填充方式
   * @return byte[]    解密数据
   * @throws Exception
   */
  public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
    //实例化
    Cipher cipher = Cipher.getInstance(cipherAlgorithm);
    //使用密钥初始化，设置为解密模式
    cipher.init(Cipher.DECRYPT_MODE, key);
    //执行操作
    return cipher.doFinal(data);
  }

  /**
   * 根据路径获取key文件
   *
   * @param keyPath
   * @return
   */
  private static Key readKey(String keyPath) {
    FileInputStream fileIn = null;
    ObjectInputStream in = null;
    Key key = null;
    try {
      fileIn = new FileInputStream(keyPath);
      in = new ObjectInputStream(fileIn);
      key = (Key) in.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (in != null) in.close();
        if (fileIn != null) fileIn.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return key;
  }

  public static void main(String[] args) throws Exception {
    String filePath = "E:/app.key";
//        createSecretKey("yunqiandai", filePath);

    String data = "云钱袋AES加解密测试";
    System.out.println("加密前数据==>" + data);
    byte[] encryptData = encrypt(data.getBytes(), filePath);
    System.out.println("加密后数据==>" + Hex.encodeHexStr(encryptData));
    byte[] decryptData = decrypt(encryptData, filePath);
    System.out.println("解密后数据==>" + new String(decryptData));

  }
}
