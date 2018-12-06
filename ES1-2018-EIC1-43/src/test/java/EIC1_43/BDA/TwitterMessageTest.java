package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

import javax.swing.JPanel;

import org.junit.Test;

public class TwitterMessageTest {

	
	public TwitterMessageTest () {} 
	
	@Test
	public void construtorTest(){
		
		
		JPanel representation1 = null, representation2 = null;
		Date date = new Date();
		String content1 = "Teste da classe TwitterMessage a ser efectuado";
		String content2 = "Teste twitter";
		
		TwitterMessage twitterMessage1 = new TwitterMessage (date, content1);
		TwitterMessage twitterMessage2 = new TwitterMessage (date, content2);
		
		assertTrue(twitterMessage1.getDate().equals(date));
		
		//representation1 = twitterMessage1.ObjectRepresentation();
		//representation2 = twitterMessage2.ObjectRepresentation();
		representation1.equals("Teste da classe TwitterMessage" + "               " + twitterMessage1.getData() + "   " + twitterMessage1.getHora());
		representation2.equals("Teste twitter                 " + "               " + twitterMessage2.getData() + "   " + twitterMessage2.getHora());
		
	}

}
