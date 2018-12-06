package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

import javax.swing.JPanel;

import org.junit.Test;

public class TwitterMessageTest {

	public TwitterMessageTest() {
	}

	@Test
	public void construtorTest() {

		Date date = new Date();
		String content = "Teste da classe TwitterMessage a ser efectuado";

		TwitterMessage twitterMessage = new TwitterMessage(date, content);

		assertTrue(twitterMessage.getDate().equals(date));
		assertTrue(twitterMessage.getContent().equals(content));
	}

}
