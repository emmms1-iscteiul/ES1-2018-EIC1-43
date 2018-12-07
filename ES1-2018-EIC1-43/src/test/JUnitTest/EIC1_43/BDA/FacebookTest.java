package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.Test;


public class FacebookTest {

	private Facebook facebook;
	private ArrayList<String> infoTest;
	private String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
			+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
	
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
	
	@Test
	public void setClientTest() {
		facebook = new Facebook();
		facebook.setAccesstoken(validAccessToken);
		try {
			facebook.setClient();
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
		
	}
	
	@Test
	public void updatePostsTest() {
		facebook = new Facebook();
		facebook.setAccesstoken(validAccessToken);
		try {
			facebook.setClient();
		} catch (Exception e1) {
			fail("Falha para dados validos");
		}
		try {
			facebook.updatePosts();
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
	}
}
