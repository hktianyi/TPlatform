package org.tplatform.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.Assert;
import org.tplatform.framework.log.Logger;
import org.tplatform.framework.util.StringUtil;

import java.io.InputStream;
import java.util.Properties;

/**
 * SFTP上传文件
 */
@DependsOn("propertyUtil")
public class SFTPUtil {

  private static Session session;
  private static ChannelSftp channel;

  private static String root;
  private static String ftpHost;
  private static int ftpPort;
  private static String ftpUserName;
  private static String ftpPassword;

  static {
    //ftpHost|ftpPort|ftpUserName|ftpPassword
    String[] ftpInfo = new String(Base64.decodeBase64(PropertyUtil.getProInfo("ftpInfo"))).split("\\|");
    ftpHost = ftpInfo[0];
    ftpPort = Integer.parseInt(ftpInfo[1]);
    ftpUserName = ftpInfo[2];
    ftpPassword = ftpInfo[3];
    root = ftpInfo[4];
  }

  /**
   * 打开SFTP连接
   *
   * @throws JSchException
   */
  private static ChannelSftp init() throws JSchException {
    if (channel != null && channel.isConnected()) {
      return channel;
    }

    if (session == null || !session.isConnected()) {
      JSch jsch = new JSch(); // 创建JSch对象
      session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
      if (StringUtil.isNotEmpty(ftpPassword)) {
        session.setPassword(new String(Base64.decodeBase64(ftpPassword))); // 设置密码
      }
      Properties config = new Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config); // 为Session对象设置properties
      session.setTimeout(6000); // 设置timeout时间
      session.connect(); // 通过Session建立链接
    }

    channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
    channel.connect(); // 建立SFTP通道的连接
    return channel;
  }

  /**
   * 上传文件
   * @param in 文件输入流
   * @param dst 文件目录
   * @return 绝对路径
   * @throws SftpException
   */
  public static String upload(InputStream in, String dst) throws SftpException {
    Assert.notNull(in);
    Assert.hasText(dst);
    ChannelSftp channel = null;
    String absolutePath = root + dst;
    try {
      channel = init();
      channel.put(in, absolutePath);
    } catch (JSchException e) {
      Logger.e("创建FTP连接失败", e);
      throw new RuntimeException("创建FTP连接失败");
    } catch (SftpException e) {
      String[] paths = dst.split("/");
      String path = root;
      for (int i = 1; i < paths.length; i++) {
        if(StringUtil.isEmpty(paths[i - 1])) continue;
        path += "/" + paths[i - 1];
        try {
          channel.cd(path);
        } catch (SftpException e1) {
          channel.mkdir(path);
//          channel.cd(path);
        }
      }
      channel.put(in, absolutePath);
    }
    return absolutePath;
  }

  public static boolean rm(String dst) {
    Assert.hasText(dst);
    try {
      init().rm(root + dst);
      return true;
    } catch (JSchException e) {
      Logger.e("删除文件异常", e);
    } catch (SftpException e) {
      Logger.e("删除文件异常", e);
    }
    return false;
  }
}
