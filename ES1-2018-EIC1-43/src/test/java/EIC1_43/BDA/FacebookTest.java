package EIC1_43.BDA;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FacebookTest {

	Facebook facebook;
	ArrayList<String> infoTest;
	
	public FacebookTest() {
		
	}
	
	@Test
	public void BuilderTest() {
		facebook = new Facebook();
		assertTrue(facebook != null);
		assertTrue(facebook.getPosts() != null);
	}

	@Test
	public void setAndGetTest() {
		facebook = new Facebook();
		
		facebook.setMyUser(null);
		assertTrue(facebook.getMyUser() == null);
		
		facebook.setFbClient5(null);
		assertTrue(facebook.getFbClient5() == null);
		
		facebook.setFbClient2(null);
		assertTrue(facebook.getFbClient2() == null);
		
		facebook.setAccesstoken(null);
		assertTrue(facebook.getAccesstoken() == null);
		
		facebook.setPosts(null);
		assertTrue(facebook.getPosts() == null);
	}
}
