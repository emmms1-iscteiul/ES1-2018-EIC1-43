package EIC1_43.BDA;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSessionBean {

	private String email;
	private String password;
	private Properties props;
	private Session session;
	private Transport transport;
	
	public EmailSessionBean(String username, String password) throws Exception{
		this.email = username;
		this.password = password;
		props = new Properties();
		props = System.getProperties();
		props.put("mail.smtp.user",this.email); 
		props.put("mail.smtp.password", this.password);
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "25"); 
		props.put("mail.debug", "true"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.EnableSSL.enable","true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
		props.setProperty("mail.smtp.socketFactory.fallback", "false");   
		props.setProperty("mail.smtp.port", "465");   
		props.setProperty("mail.smtp.socketFactory.port", "465"); 
		session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", this.email,this.password);
	}

	public void sendEmail (String toEmail, String subject, String message) throws Exception {

			Message mailMessage = new MimeMessage(session);
			mailMessage.setFrom(new InternetAddress(email));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setContent(message,"text/html");
			mailMessage.setSubject(subject);
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
	}
}
