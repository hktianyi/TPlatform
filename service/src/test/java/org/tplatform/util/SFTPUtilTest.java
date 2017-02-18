package org.tplatform.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by tianyi on 2017/2/18.
 */
public class SFTPUtilTest {


  public static void main(String[] args) {
    String host = "114.215.109.29";
    String username = "root";
    String password = "p!9%L^2k";
    int port = 7100;
    // 生成SFTP加密串
    // ftpHost|ftpPort|ftpUserName|ftpPassword|ftpPath
    System.out.println("SFTP加密串: " + Base64.encodeBase64String(String.format("%s|%s|%s|%s|/home/cdn/tplatform", host, port, username, password).getBytes()));
    // 生成Redis加密串
    System.out.println("Redis加密串: " + Base64.encodeBase64String(String.format("%s|%s|%s", host, port, password).getBytes()));
  }
}
