package EIC1_43.BDA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Class which represents and manages email info, attributes and methods
 *
 *
 */
public class EmailSessionBean {

	private String email;
	private String password;
	private Properties props;
	private Session session;
	private Transport transport;
	private ArrayList<EmailMessage> mails = new ArrayList<EmailMessage>();
	
	/**
	 * 
	 * @param username email user name
	 * @param password email user password
	 * @throws Exception thrown in case constructor fails 
	 */
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
	
	/**
	 * 
	 * 
	 * @param toEmail mail's destination
	 * @param subject mail matter
	 * @param message mail body
	 * @throws Exception thrown in case it's not possible to send the email
	 */
	public void sendEmail (String toEmail, String subject, String message) throws Exception {

			Message mailMessage = new MimeMessage(session);
			mailMessage.setFrom(new InternetAddress(email));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setContent(message,"text/html");
			mailMessage.setSubject(subject);
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
	}
	
	public void updateEmail() throws Exception {
		URLName url;
		url = new URLName("imaps", "imap.gmail.com", 993, "INBOX", this.email, password);

		Session session = null;
		Properties props = null;
			try {
				props = System.getProperties();
			} catch (SecurityException sex) {
				props = new Properties();
			}
			session = Session.getInstance(props, null);
		
		Store store = session.getStore(url);
		store.connect();
		Folder folder = store.getFolder(url);

		folder.open(Folder.READ_WRITE);
		
		Message[] m = folder.getMessages();
		
		for(int i=0;i<m.length;i++) {
			EmailMessage msg = new EmailMessage(m[i].getReceivedDate(),m[i].getContent().toString());
			this.mails.add(msg);
		}
		
		Collections.sort(mails, new Comparator<EmailMessage>() {

			@Override
			public int compare(EmailMessage arg0, EmailMessage arg1) {
				return arg1.getDate().compareTo(arg0.getDate());
			}
			
		});
		
		folder.close(false);
		store.close();
		store = null;
		session = null;
	}

	
	
	public ArrayList<EmailMessage> getMails() {
		return mails;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
		
}
