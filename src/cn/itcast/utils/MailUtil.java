package cn.itcast.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件工具类
 * @author Administrator
 */
public class MailUtil {
	
	/**
	 * 发送邮件
	 * @param to  发送给谁
	 * @param code  激活码
	 */
	public static void sendMail(String to,String code){
		// 1.属性对象
		Properties props = new Properties();
		// 发送邮件的协议
		props.setProperty("mail.smtp", "localhost");
		// 2.创建一个对象Session.
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@estore.com", "123");
			}
		});
		// 3.创建一个代表邮件的对象:
		Message message = new MimeMessage(session);
		
		try {
			// 设置发件人:
			message.setFrom(new InternetAddress("service@estore.com"));
			// 设置收件人:
			// TO:收件人.
			// CC:抄送
			// BCC:暗送,密送
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置邮件的主题:
			message.setSubject("ESTORE官方网站的激活邮件");
			// 设置邮件的正文:
			message.setContent("<h1>欢迎来到ESTROE购物天堂</h1><h3><a href='http://192.168.35.64/estore/user?method=active&code="+code+"'>http://192.168.35.64/estore/user?method=active&code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 发送邮件:
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
	}
}
