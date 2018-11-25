package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.Test;

import twitter4j.TwitterException;

public class TwitterMessageTest {

	
	public TwitterMessageTest () {} 
	
	@Test
	public void construtorTest(){
		
		
		String representation1 = "", representation2 = "";
		Date date = new Date();
		String content1 = "Teste da classe TwitterMessage a ser efectuado";
		String content2 = "Teste twitter";
		
		TwitterMessage twitterMessage1 = new TwitterMessage (date, content1);
		TwitterMessage twitterMessage2 = new TwitterMessage (date, content2);
		
		assertTrue(twitterMessage1.getDate().equals(date));
		
		representation1 = twitterMessage1.ObjectRepresention();
		representation2 = twitterMessage2.ObjectRepresention();
		representation1.equals("Teste da classe TwitterMessage" + "               " + twitterMessage1.getData() + "   " + twitterMessage1.getHora());
		representation2.equals("Teste twitter                 " + "               " + twitterMessage2.getData() + "   " + twitterMessage2.getHora());
		
	}

}
