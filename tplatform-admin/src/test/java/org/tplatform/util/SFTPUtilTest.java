package org.tplatform.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by tianyi on 2017/2/18.
 */
public class SFTPUtilTest {


  public static void main(String[] args) {
    String host = "127.0.0.1";
    String username = "root";
    String password = "";
    int port = 7100;
    int db = 1;
    // 生成SFTP加密串
    // ftpHost|ftpPort|ftpUserName|ftpPassword|ftpPath
    System.out.println("SFTP加密串: " + Base64.encodeBase64String(String.format("%s|%s|%s|%s|/home/cdn/wg", host, port, username, Base64.encodeBase64String(password.getBytes())).getBytes()));
    // 生成Redis加密串
    System.out.println("Redis加密串: " + Base64.encodeBase64String(String.format("%s|%s|%s|%s", host, port, Base64.encodeBase64String(password.getBytes()), db).getBytes()));
  }
}
