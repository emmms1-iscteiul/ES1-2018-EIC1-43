package EIC1_43.BDA;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import org.junit.Test;
import twitter4j.TwitterException;

public class TwitterTest {

	Twitter twitter;
	ArrayList<String> infoTest;
	
	
	public TwitterTest() {
		
	}
	@Test
	public void BuilderTest() {
		twitter = new Twitter();
		assertTrue(twitter != null);
		assertTrue(twitter.getIscteTweets() != null);
		assertTrue(twitter.getMeTweets() != null);
	}

	@Test
	public void setInfoValidTest() {
		
		twitter = new Twitter();
		infoTest = new ArrayList<String>();
		infoTest.add("bPcMjzld5PjWFDYMl401Ak0kw");
		infoTest.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
		infoTest.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
		infoTest.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");
		
		try {
			twitter.setInfo(this.infoTest);
		} catch (TwitterException e) {
			fail("falha para dados validos");
		}
		
		assertTrue(twitter.getTwitter() != null);
		assertTrue(twitter.getCb() != null);
	}
	
	@Test
	public void setInfoFailTest() {
		
		twitter = new Twitter();
		infoTest = new ArrayList<String>();
		infoTest.add("Estes");
		infoTest.add(1,"dados");
		infoTest.add(2,"não são");
		infoTest.add(3,"validos");
		
		try {
			twitter.setInfo(this.infoTest);
			twitter.updateTwitter();
		} catch (TwitterException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void updateTwitterTest() {
		twitter = new Twitter();
		infoTest = new ArrayList<String>();
		infoTest.add("bPcMjzld5PjWFDYMl401Ak0kw");
		infoTest.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
		infoTest.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
		infoTest.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");
		
		try {
			twitter.setInfo(this.infoTest);
		} catch (TwitterException e) {
			fail("falha para dados validos");
		}
		
		try {
			twitter.updateTwitter();
		} catch (TwitterException e) {
			fail("Falha para dados validos");
		}
		
		assertTrue(twitter.getIscteTweets().size() >= 0);
		assertTrue(twitter.getMeTweets().size() >= 0);
	}
	
	@Test
	public void setAndGetTest() {
		twitter = new Twitter();
		
		twitter.setTwitter(null);
		assertTrue(twitter.getTwitter() == null);
		
		twitter.setIscteTweets(null);
		assertTrue(twitter.getIscteTweets() == null);
		
		twitter.setMeTweets(null);
		assertTrue(twitter.getMeTweets() == null);
		
		assertTrue(twitter.getSource().equals("ISCTE"));
		
		twitter.setCb(null);
		assertTrue(twitter.getCb() == null);
		
		twitter.setTf(null);
		assertTrue(twitter.getTf() == null);
	}

	
	@Test
	public void sendTweetTest() {
		twitter = new Twitter();
		infoTest = new ArrayList<String>();
		infoTest.add("bPcMjzld5PjWFDYMl401Ak0kw");
		infoTest.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
		infoTest.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
		infoTest.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");
		
		try {
			twitter.setInfo(this.infoTest);
		} catch (TwitterException e) {
			fail("falha para dados validos");
		}
		try {
			twitter.sendTweet("" + Math.random());
		} catch (TwitterException e) {
			fail("Falha para dados validos");
		}
	}
}