package com.lava.lavafaq.utils;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import com.lava.lavafaq.bean.Person;
import com.sun.mail.util.MailSSLSocketFactory;



public class MailUtils {
	private static Logger logger = Logger.getLogger(MailUtils.class);
	//--------------参数---------------------
    public static final String FROM = "1143368701@qq.com";//发件人的email
    public static final String PWD = "jstejmtixfkjfhfe";//发件人密码--邮箱密码
    public static final String URL = "http://localhost:8080/lavaFAQ/login";//项目主页
    public static final int TIMELIMIT = 1000*60*60*24; //激活邮件过期时间24小时
    public static final String TITLE = "lavaFAQ账户激活邮件";
 
//---------------自定义函数-----------------
    public static Person sendMail(Person u) throws AddressException, MessagingException, NoSuchAlgorithmException {
        //注册邮箱
        String to  = u.getMail();
        //当前时间戳
        Long curTime = System.currentTimeMillis();
        //激活的有效时间
        Long activateTime = curTime+TIMELIMIT;
        //激活码--用于激活邮箱账号       
        u.setActiCode(UUIDUtils.getUUID()+curTime);     
        String actiCode = u.getActiCode();
        //过期时间       
        u.setToken_exptime(String.valueOf(activateTime));
        //发送的邮箱内容
        String content = "<p>您好 O(∩_∩)O~~<br><br>欢迎加入lavaFAQ!<br><br>帐户需要激活才能使用，赶紧激活成为lavaFAQ正式的一员吧:)<br><br>请在24小时内点击下面的链接立即激活帐户："
                +"<br><a href='"+URL+"/activatemail/?actiCode="+actiCode+"&email="+to+"'>"
                +URL+"/activatemail/?actiCode="+actiCode+"&email="+to+"</a></p>";
        //调用发送邮箱服务
        sendMailEntity(to, TITLE, content);
        return u;
    }

	
	 public static void sendMailEntity(String to,String title,String content) throws AddressException, MessagingException {

	        Properties props = new Properties(); //可以加载一个配置文件      
	        // 设置邮件服务器主机名
	        props.setProperty("mail.host", "smtp.qq.com");//指定邮件服务器，默认端口 25
	        // 发送服务器需要身份验证
	        props.setProperty("mail.smtp.auth", "true");//要采用指定用户名密码的方式去认证
	        // 发送邮件协议名称
	        props.setProperty("mail.transport.protocol", "smtp");

	     // 开启SSL加密，否则会失败
	        MailSSLSocketFactory sf = null;
			try {
				sf = new MailSSLSocketFactory();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        sf.setTrustAllHosts(true);
	        props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.ssl.socketFactory", sf);

	        //根据属性新建一个邮件会话  
	        Session session = Session.getInstance(props,new Authenticator() {
	        	@Override
	        	protected PasswordAuthentication getPasswordAuthentication() {
	        		// TODO Auto-generated method stub
	        		return new PasswordAuthentication(FROM, PWD);
	        	}
			});
	        session.setDebug(true); //有他会打印一些调试信息。  
	        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象  
	        message.setFrom(new InternetAddress(FROM));//设置发件人的地址  
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置收件人,并设置其接收类型为TO   	       
	        message.setSubject(title,"UTF-8");//设置标题  
	        //设置信件内容  
	        //message.setText(content,"UTF-8"); //发送 纯文本 邮件 todo  
	        message.setContent(content, "text/html;charset=utf-8"); //发送HTML邮件，内容样式比较丰富  
	        message.setSentDate(new Date());//设置发信时间  
	        //message.saveChanges();//存储邮件信息  
	        //发送邮件  
	        Transport.send(message);     
	        logger.debug("Transport.send"); 
	        /*//发送邮件 (二)
	        Transport transport = session.getTransport(SMTP);  
	        //Transport transport = session.getTransport();  
	        transport.connect(FROM, PWD);
	        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址  
	        transport.close(); */ 
	    }

}
