package org.tplatform.util;

import org.tplatform.framework.util.Hex;
import org.tplatform.framework.util.SpringContextUtil;
import org.tplatform.core.service.impl.SysConfService;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Tianyi on 2015/9/14.
 */
public class MailUtil {

  private static final Map<String, Session> sessionMap = new HashMap<>();

  private MailUtil() {
  }

  public static void sendSimple(String from, String to, String subject, String text) {

    try {
      // 创建默认的 MimeMessage 对象
      MimeMessage message = new MimeMessage(getSession(from));

      // Set From: 头部头字段
      message.setFrom(new InternetAddress(from));

      // Set To: 头部头字段
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: 头部头字段
      message.setSubject(subject);

      // 设置消息体
      message.setText(text);

      // 发送消息
      Transport.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
//      Logger.e(MailUtil.class, "发送邮件失败！", e);
    }
  }

  private static Session getSession(String sender) {
    if (!sessionMap.containsKey(sender)) {

      SysConfService sysConfService = SpringContextUtil.getBean(SysConfService.class);
      String host = sysConfService.findVal("Email_Host");
//      if (StringUtil.isEmpty(host))
//        throw new RuntimeException("邮箱服务器配置异常");
      String prot = sysConfService.findVal("Email_Port");
//      if (StringUtil.isEmpty(prot))
//        prot = "80";
      String username = sysConfService.findVal("Email_Username");
//      if (StringUtil.isEmpty(username))
//        throw new RuntimeException("用户名配置异常");
      String password = new String(Hex.decodeHex(sysConfService.findVal("Email_Password").toCharArray()));
//      if (StringUtil.isEmpty(password))
//        throw new RuntimeException("密码配置异常");

      Properties props = System.getProperties();
      props.setProperty("mail.smtp.host", host);
      props.setProperty("mail.smtp.auth", "true");
      props.setProperty("mail.smtp.timeout", "30000");

      Session session = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      });
      sessionMap.put(sender, session);
    }
    return sessionMap.get(sender);
  }

}
