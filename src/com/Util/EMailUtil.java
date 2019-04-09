package com.Util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EMailUtil {
    //发件人地址
    public static String senderAddress = "13294183563@163.com";

    //发件人密码
    public static String senderPassword = "jackye1314520";

    //发送文字邮件
    public static boolean sendEmail(String receiverAddress){
        try{
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", "smtp.163.com");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.port", "465");
            Session session = Session.getInstance(props);
            session.setDebug(true);

            Message msg = getMessage(session, receiverAddress);
            Transport transport = session.getTransport();
            transport.connect(senderAddress, senderPassword);
            System.out.println(msg.getContent());
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //创建邮件实例对象
    public static MimeMessage getMessage(Session session, String receiverAddress) throws Exception{
        MimeMessage mimeMessage = new MimeMessage(session);
        //设置发件人
        mimeMessage.setFrom(new InternetAddress(senderAddress));

        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiverAddress));
        //设置邮件主题
        mimeMessage.setSubject("修改密码", "UTF-8");
        //设置内容 6位验证码
        mimeMessage.setContent(VCUtil.getVerificationCode(6), "text/html;charset=UTF-8");
        //设置发件时间
        mimeMessage.setSentDate(new Date());
        return mimeMessage;
    }
}
