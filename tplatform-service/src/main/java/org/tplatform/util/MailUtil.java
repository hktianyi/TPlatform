package org.tplatform.util;

import org.tplatform.domain.ConfigService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
//      Logger.e("发送邮件失败！", e);
    }
  }

  private static Session getSession(String sender) {
    if (!sessionMap.containsKey(sender)) {

      ConfigService configService = SpringContextUtil.getBean(ConfigService.class);
      String host = configService.getByKey("Email_Host");
      if (StringUtil.isEmpty(host))
        throw new RuntimeException("邮箱服务器配置异常");
      String prot = configService.getByKey("Email_Port");
      if (StringUtil.isEmpty(prot))
        prot = "80";
      String username = configService.getByKey("Email_Username");
      if (StringUtil.isEmpty(username))
        throw new RuntimeException("用户名配置异常");
      String password = configService.getByKey("Email_Password");
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


  public static boolean sendWithAttachment(String from, String to, String subject, String html, File attachment) {
    List<File> attachmentList = new ArrayList<>(1);
    if (attachment != null) {
      attachmentList.add(attachment);
    }
    return sendWithAttachment(from, to, subject, html, attachmentList);
  }


  public static boolean sendWithAttachment(String from, String to, String subject, String html, List<File> attachmentList) {
    try {
      // 创建默认的 MimeMessage 对象
      MimeMessage message = new MimeMessage(getSession(from));
      //创建邮件发送者地址
      message.setFrom(new InternetAddress(from));//设置邮件消息的发送者
      //创建邮件的接收者地址
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置邮件消息的接收者
      message.setSubject(subject);//设置邮件消息的主题
      message.setSentDate(new Date());//设置邮件消息发送的时间

      // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
      Multipart multipart = new MimeMultipart();

      // 添加邮件正文
      BodyPart contentPart = new MimeBodyPart();
      contentPart.setContent(html, "text/html;charset=UTF-8");
      multipart.addBodyPart(contentPart);

      // 添加附件的内容
      if (attachmentList != null && attachmentList.size() > 0) {
        attachmentList.forEach(attachment -> {
          BodyPart attachmentBodyPart = new MimeBodyPart();
          DataSource source = new FileDataSource(attachment);
          try {
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

            //MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
          } catch (MessagingException e) {
            e.printStackTrace();
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
        });
      }

      // 将multipart对象放到message中
      message.setContent(multipart);
      // 保存邮件
      message.saveChanges();
      Transport.send(message);//发送邮件

//      transport = session.getTransport("smtp");
//      // smtp验证，就是你用来发邮件的邮箱用户名密码
//      transport.connect(mailHost, sender_username, sender_password);
//      // 发送
//      Transport.sendMessage(message, message.getAllRecipients());

      System.out.println("send success!");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

}

