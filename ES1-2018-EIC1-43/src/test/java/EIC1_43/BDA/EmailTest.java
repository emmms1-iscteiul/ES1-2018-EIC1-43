package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import org.junit.Test;

public class EmailTest {
	
	EmailSessionBean email;
	String validEmailTest = "ola123ola123ola123software@gmail.com";
	String validPassTest = "modfzrjlqjmkruhp";
	
	public EmailTest() {
	}
	
	@Test
	public void builderTest() {
		try {
			email = new EmailSessionBean(validEmailTest,validPassTest);
		} catch (Exception e) {
			fail("Email Connection failed");
		}
		assertTrue(validEmailTest.equals(email.getEmail()));
		assertTrue(validPassTest.equals(email.getPassword()));	
	}
	
	@Test
	public void builderTestFail() {
		try {
			email = new EmailSessionBean("Vai Falhar","Se não estivermos enganados");
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void setAndGetTest() {
		try {
			email = new EmailSessionBean(validEmailTest,validPassTest);
		} catch(Exception e) {
			fail("Email Connection failed");
		}
		String emailTest = "ola@gmail.com";
		email.setEmail(emailTest);
		assertTrue(email.getEmail().equals(emailTest));
		
		String passTest = "123";
		email.setPassword(passTest);
		assertTrue(email.getPassword().equals(passTest));
		
		Properties propsTest = null;
		email.setProps(propsTest);
		assertTrue(email.getProps() == null);
		
		Session sessionTest = null;
		email.setSession(sessionTest);
		assertTrue(email.getSession() == null);
		
		Transport transportTest = null;
		email.setTransport(transportTest);
		assertTrue(email.getTransport() == null);
	}
	
	@Test
	public void sendEmailTest() {
		try {
			email = new EmailSessionBean(validEmailTest,validPassTest);
		} catch(Exception e) {
			fail("Email Connection failed");
		}
		
		try {
			email.sendEmail("fmsantana94@gmail.com", "Assunto", "Mensagem");
		} catch (Exception e) {
			fail("Falha ao enviar email para dados válidos");
		}
	}
	
	@Test
	public void sendEmailTestFail() {
		try {
			email = new EmailSessionBean("ola123ola123ola123software@gmail.com", "modfzrjlqjmkruhp");
		} catch (Exception e) {
			fail("Email Connection failed");
		}

		try {
			email.sendEmail("Vai falhar ou não percebemos a API", "Assunto", "Mensagem");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
}
