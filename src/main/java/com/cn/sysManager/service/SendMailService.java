package com.cn.sysManager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

/**
 * Created by lijm on 2019-03-22.
 */
@Service
public class SendMailService implements ISendMailService {

    private static final Logger logger = LoggerFactory.getLogger(SendMailService.class);

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    static final String myEmailSMTPHost = "smtp.163.com";
    static final String myEmailAccount="juanmiao0204@163.com";
    static final String myEmailPassword="38271330forgive";
    static final String sendEmailSubject = "发送邮件主题";
    static final String sendEmailText = "我想发送一封邮件给你";

    @Override
    public void sendSimpleEmail(String to) {

        logger.info("======准备发送简单邮件=======");

        String smtpHost = myEmailSMTPHost;//服务器地址
        String account = myEmailAccount; //发件人账户
        String password = myEmailPassword; //发件人账户密码
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpHost);
        mailSender.setUsername(account);
        mailSender.setPassword(password);


        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        javaMailProperties.put("mail.smtps.ssl.checkserveridentity", "false");
        mailSender.setJavaMailProperties(javaMailProperties);

        //创建邮件内容
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(account);
        message.setTo(to);
        message.setSubject(sendEmailSubject);
        message.setText(sendEmailText);
        //发送邮件
        mailSender.send(message);
    }

    @Override
    public void sendMessageAndFileEmail(String to, String orderNum)throws Exception{

        logger.info("======准备发送消息和带附件邮件=======");
        String smtpHost = myEmailSMTPHost;//服务器地址
        String account = myEmailAccount; //发件人账户
        String password = myEmailPassword; //发件人账户密码
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpHost);
        mailSender.setUsername(account);
        mailSender.setPassword(password);


        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        javaMailProperties.put("mail.smtps.ssl.checkserveridentity", "false");
        mailSender.setJavaMailProperties(javaMailProperties);

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true ,"UTF-8");

        helper.setFrom(mailSender.getUsername());
        helper.setTo(to);
        helper.setSubject(sendEmailSubject);
        helper.setText(sendEmailText);
        FileSystemResource fileAdd = new FileSystemResource(""+"/"+"");
        helper.addAttachment(fileAdd.getFilename(),fileAdd);
        // 发送邮件
        mailSender.send(msg);
    }

    @Override
    public void sendMessageAndHtmlEmail(String to, String orderNum)throws Exception {

        logger.info("======准备发送消息和带附件预览邮件=======");
        String smtpHost = myEmailSMTPHost;//服务器地址
        String account = myEmailAccount; //发件人账户
        String password = myEmailPassword; //发件人账户密码
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpHost);
        mailSender.setUsername(account);
        mailSender.setPassword(password);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        mailSender.setJavaMailProperties(javaMailProperties);

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true ,"UTF-8");

        helper.setFrom(mailSender.getUsername());
        helper.setTo(to);
        helper.setSubject(sendEmailSubject);
        helper.setText("<body><p>"+sendEmailText+"</p><img src='cid:file'/></body>", true);

        //查询附件
        FileSystemResource fileAdd = new FileSystemResource(""+"/"+"");
        helper.addInline(fileAdd.getFilename(),fileAdd);
        // 发送邮件
        mailSender.send(msg);
    }

    @Override
    public void sendMessageUrlEmail(String to,String orderNum)throws Exception{

        String smtpHost = myEmailSMTPHost;//服务器地址
        String account = myEmailAccount; //发件人账户
        String password = myEmailPassword; //发件人账户密码

        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", smtpHost);
        props.setProperty("mail.smtp.auth", "true");

        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(props,
                new Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(account, password);
                    }
                });

        Message mailMessage = new MimeMessage(sendMailSession);// 根据session创建一个邮件消息
        mailMessage.setFrom(new InternetAddress(account)); // 设置邮件消息的发送者
        mailMessage.setRecipient(Message.RecipientType.TO,
                new InternetAddress(to)); // 创建邮件的接收者地址，并设置到邮件消息中
        mailMessage.setSubject(sendEmailText);
        mailMessage.setSentDate(new Date()); // 设置邮件消息发送的时间

        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        BodyPart html = new MimeBodyPart();// 创建一个包含HTML内容的MimeBodyPart

        //设置HTML内容
        html.setContent("<html><body>"+""+"</body></html>",
                "text/html; charset=utf-8");
        mainPart.addBodyPart(html);//将MiniMultipart对象设置为邮件内容
        mailMessage.setContent(mainPart);
        //发送邮件
        Transport.send(mailMessage);

    }
}
