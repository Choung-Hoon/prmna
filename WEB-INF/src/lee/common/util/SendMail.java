/*
* @(#)SendMail   1.0 2011/07/01
 */
package lee.common.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import lee.common.util.SendMailT.MyAuthenticator;

public class SendMail{
	
	private String host;
	private String id;
	private String pass;

	public SendMail(String host, String id, String pass) {
		this.host = host;
		this.id = id;
		this.pass = pass;
	}

	public SendMail() {
		this.host = "";
		this.id = "";
		this.pass = "";
	}

	public void sendMail(String name, String from, String to, String subject,
			String type, String content, String fpath, String fname,
			String rfname) {
		java.util.Properties properties = System.getProperties();
		
		properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", host);		
        properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
		javax.mail.Authenticator auth = new MyAuthenticator( id, pass );
		Session session = Session.getInstance(properties, auth );
		
		//Session session = Session.getInstance(properties, null );

		try {
			name = StringUtil.toEng(name);
			rfname = StringUtil.toEng(rfname);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(name + "<" + from + ">"));

			InternetAddress[] tos = InternetAddress.parse(to);
			message.setRecipients(Message.RecipientType.TO, tos);
			// message.setSubject(subject, "KSC5601");
			message.setSubject(subject);
			Multipart multipart = new MimeMultipart();

			// 메일 내용을 Setting한다.
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			if (type.equals("text")) {
				messageBodyPart.setText(content, "KSC5601");
				// messageBodyPart.setText(content, "UTF-8");
				// messageBodyPart.setContent(common.toKor(content),
				// "text/html;charset=KSC5601");
			} else {
				messageBodyPart
						.setContent(content, "text/html;charset=KSC5601");
				// messageBodyPart.setContent(content,
				// "text/html;charset=UTF-8");
			}

			multipart.addBodyPart(messageBodyPart);

			// 첨부파일
			if (!fpath.equals("") && !fname.equals("")) {
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(fpath + "/" + fname);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(rfname);
				multipart.addBodyPart(messageBodyPart);
			}

			message.setContent(multipart);

			Transport transport = session.getTransport("smtp");

			// SMTP서버, ID, Password
			transport.connect(this.host, this.id, this.pass); // SMTP접속시 ID와  Password가 필요할  경우 이곳에 설정해주세요.
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	  class MyAuthenticator extends javax.mail.Authenticator {
	    javax.mail.PasswordAuthentication pa;
	
	    public MyAuthenticator( String mailHostID, String mailHostPass ) {
	      pa = new javax.mail.PasswordAuthentication( mailHostID, mailHostPass );
	    }
	
	    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
	      return pa;
	    }
	  }	
	
	public static void main(String[] args) {
		// String host = "211.233.80.19";
		// String name="name";
		// SendMail mail = new SendMail(host,"resin","todnRkd10qhdwl!");
		// mail.sendMail(name, "reportf@ahnlab.com", "rmawhrw@nate.com",
		// "title", "text", "ssssssssssssss", "/home/resin", "a.txt", "a.zip");

		SendMail mail = new SendMail("127.0.0.1", "root", "root");
		mail.sendMail("tester", "koreanet@naver.com", "sjju16@empal.com",
				"title", "text", "This is test mail!", "", "", "");
	}
}
