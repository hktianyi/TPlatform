package org.tplatform.util;

import org.tplatform.framework.util.SpringContextUtil;
import org.tplatform.core.service.impl.SysConfService;
import org.tplatform.framework.util.StringUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
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
      if (StringUtil.isEmpty(host))
        throw new RuntimeException("邮箱服务器配置异常");
      String prot = sysConfService.findVal("Email_Port");
      if (StringUtil.isEmpty(prot))
        prot = "80";
      String username = sysConfService.findVal("Email_Username");
      if (StringUtil.isEmpty(username))
        throw new RuntimeException("用户名配置异常");
      String password = sysConfService.findVal("Email_Password");
//      String password = new String(Hex.decodeHex(sysConfService.findVal("Email_Password").toCharArray()));
      if (StringUtil.isEmpty(password))
        throw new RuntimeException("密码配置异常");

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

  // 以HTML格式发送邮件
  public static boolean sendHtmlMail(String from, String to, String subject, String text) {

    try{
      // 创建默认的 MimeMessage 对象
      MimeMessage message = new MimeMessage(getSession(from));
      //创建邮件发送者地址
      message.setFrom(new InternetAddress(from));//设置邮件消息的发送者
      //创建邮件的接收者地址
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置邮件消息的接收者
      message.setSubject(subject);//设置邮件消息的主题
      message.setSentDate(new Date());//设置邮件消息发送的时间

      //MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象
      Multipart mainPart = new MimeMultipart();
      MimeBodyPart messageBodyPart = new MimeBodyPart();//创建一个包含HTML内容的MimeBodyPart
      //设置HTML内容
      messageBodyPart.setContent(text,"text/html; charset=utf-8");
      mainPart.addBodyPart(messageBodyPart);
      //将MimeMultipart对象设置为邮件内容
			message.setContent(mainPart);
      Transport.send(message);//发送邮件
      return true;
    }catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

}

