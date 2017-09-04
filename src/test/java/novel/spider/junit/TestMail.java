package novel.spider.junit;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class TestMail {
	@Test
	public void testMail() throws Exception{
//		Properties pro = new Properties();
//		Session session = Session.getDefaultInstance(pro);
//		MimeMessage message = new MimeMessage(session);
//		message.setFrom(new InternetAddress("buptwww@qq.com", "USER_AA", "UTF-8"));
//		message.setRecipient(MimeMessage.RecipientType.TO, 
//				new InternetAddress("bupt_weiwenwu@163.com", "USER_BB", "UTF-8"));
//		message.setSubject("java邮件测试","UTF-8");
//		message.setContent("TEST这是邮件正文。。。", "text/html;charset=UTF-8");
//		message.setSentDate(new Date());
//		message.saveChanges();
//		OutputStream out = new FileOutputStream("MyEmail.eml");
//        message.writeTo(out);
//        out.flush();
//        out.close();
		Mail.send("哈哈哈哈，成功啦！");
	}
}
