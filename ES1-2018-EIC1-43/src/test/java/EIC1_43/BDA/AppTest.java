package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import twitter4j.TwitterException;

public class AppTest {

	public AppTest() {
	}

	@Test
	public void builderTest() {
		Gui gui = new Gui();
		Facebook fb = new Facebook();
		Twitter twt = new Twitter();
		App app = new App(gui, fb, twt);

		assertTrue(app.getFacebook().equals(fb));
		assertTrue(app.getTwitter().equals(twt));
		assertTrue(app.getGui().equals(gui));
		assertTrue(app.isInternetConnected());
	}

	@Test
	public void addTweetsIntoGuiTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.addTweetsIntoGui();

		assertTrue(app.getGui().getModelList() != null);
		assertTrue(app.getGui().getModelList().size() >= 0);
	}

	@Test
	public void showTweetOnGuiTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		ArrayList<String> info = new ArrayList<String>();
		info.add("bPcMjzld5PjWFDYMl401Ak0kw");
		info.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
		info.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
		info.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");

		try {
			app.getTwitter().setInfo(info);
			app.getTwitter().updateTwitter();
		} catch (TwitterException e) {
			fail("falha para dados validos");
		}
		if (app.getTwitter().getMeTweets() != null && app.getTwitter().getMeTweets().size() > 0) {
			app.showTweetOnGui(0);
			app.showTweetOnGui(app.getTwitter().getMeTweets().size());
		} else {
			assertTrue(app.getTwitter().getMeTweets() == null || app.getTwitter().getMeTweets().size() == 0);
		}
	}

	@Test
	public void setAndGetTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
	
		app.setGui(null);
		assertTrue(app.getGui() == null);
		
		app.setFacebook(null);
		assertTrue(app.getFacebook() == null);
		
		app.setTwitter(null);
		assertTrue(app.getTwitter() == null);
		
		app.setFacebookConnected(true);
		assertTrue(app.isFacebookConnected());
		
		app.setTwitterConnected(true);
		assertTrue(app.isTwitterConnected());
		
		app.setEmailConnected(true);
		assertTrue(app.getEmailConnected());
		
		app.setEmail(null);
		assertTrue(app.getEmail() == null);
	}
	
	@Test
	public void showPostOnGuiTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
				+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
		app.getFacebook().setAccesstoken(validAccessToken);
		try {
			app.getFacebook().setClient();
			app.getFacebook().updatePosts();
		} catch (Exception e) {
			fail("Falha para dados validos");
		}

		if (app.getFacebook().getPosts() != null && app.getFacebook().getPosts().size() > 0) {
			app.showPostOnGui(app.getFacebook().getPosts().size() - 1);
		} else {
			assertTrue(app.getFacebook().getPosts() == null || app.getFacebook().getPosts().size() == 0);
		}
	}

	@Test
	public void addPostsIntoGuiTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
				+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
		app.getFacebook().setAccesstoken(validAccessToken);
		try {
			app.getFacebook().setClient();
			app.getFacebook().updatePosts();
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
		app.addPostsIntoGui();
	}

	@Test
	public void verifyInternetConnectionTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.verifyInternetConnection();
	}

	@Test
	public void displayContentTest1() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
				+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
		app.getFacebook().setAccesstoken(validAccessToken);
		app.setFacebookConnected(true);
		try {
			app.getFacebook().setClient();
			app.getFacebook().updatePosts();
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
		if (app.getFacebook().getPosts() != null && app.getFacebook().getPosts().size() > 0) {
			app.displayContent(true);
		}
	}

	@Test
	public void connectFacebookTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
				+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
		try {
			app.connectFacebook(validAccessToken);
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
	}

	@Test
	public void connectTwitterTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		ArrayList<String> info = new ArrayList<String>();
		info.add("bPcMjzld5PjWFDYMl401Ak0kw");
		info.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
		info.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
		info.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");
		try {
			app.connectTwitter(info);
		} catch (TwitterException e) {
			fail("Falha para dados validos");
		}
	}

	@Test
	public void connectEmailTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String username = "ola123ola123ola123software@gmail.com";
		String password = "modfzrjlqjmkruhp";

		try {
			app.connectEmail(username, password);
		} catch (Exception e) {
			fail("Falha para dados validos");
		}

	}

	@Test
	public void twitterPostTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		ArrayList<String> info = new ArrayList<String>();
		info.add("bPcMjzld5PjWFDYMl401Ak0kw");
		info.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
		info.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
		info.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");
		try {
			app.connectTwitter(info);
		} catch (TwitterException e) {
			fail("Falha para dados validos");
		}
		boolean posted = false;
		while (!posted) {
			app.getGui().postFrameContent();
			app.getGui().getTxtSend().setText(Math.random() + "");
			try {
				app.post(null, null);
				posted = true;
			} catch (Exception e) {
				fail("Falha para dados validos");
			}
		}
	}

	@Test
	public void emailPostTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String username = "ola123ola123ola123software@gmail.com";
		String password = "modfzrjlqjmkruhp";

		app.getGui().postFrameContent();
		app.getGui().getTxtSend().setText(Math.random() + "");
		try {
			app.connectEmail(username, password);
			app.post(username, "Assunto");
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
	}

	@Test
	public void disconnectTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
				+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
		try {
			app.connectFacebook(validAccessToken);
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
		app.disconnect();
	}

	@Test
	public void setDcTest() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
				+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
		try {
			app.connectFacebook(validAccessToken);
		} catch (Exception e) {
			fail("Falha para dados validos");
		}
		app.setDc();
	}
	
	@Test
	public void facebookValidationTest1() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.facebookValidation("s");
	}
	
	@Test
	public void facebookValidationTest2() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.setFacebookConnected(true);
		app.facebookValidation(null);
	}
	
	@Test
	public void facebookValidationTest3() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.facebookValidation("lol");
	}
	
	@Test
	public void facebookValidationTest4() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.facebookValidation("");
	}
	
	@Test
	public void emailValidationTest1() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.setFacebookConnected(true);
		app.emailValidation(null);
	}
	
	@Test
	public void emailValidationTest2() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.emailValidation("s");
	}
	
	@Test
	public void emailValidationTest3() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.emailValidation("ola");
	}
	
	@Test
	public void postValidationTest1() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.setFacebookConnected(true);
		app.postValidation();
	}
	
	@Test
	public void postValidationTest2() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.setTwitterConnected(true);
		app.postValidation();
	}
	
	@Test
	public void sendValidationTest1() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String username = "ola123ola123ola123software@gmail.com";
		String password = "modfzrjlqjmkruhp";
		try {
			app.connectEmail(username, password);
		} catch (Exception e) {
			fail("falha para dados validos");
		}
		app.sendValidation("mail");
	}
	
	@Test
	public void sendValidationTest2() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String username = "ola123ola123ola123software@gmail.com";
		String password = "modfzrjlqjmkruhp";
		try {
			app.connectEmail(username, password);
		} catch (Exception e) {
			fail("falha para dados validos");
		}
		app.sendValidation("mailFail");
	}
	
	@Test
	public void sendValidationTest3() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.sendValidation(null);
	}
	
	@Test
	public void sendValidationTest4() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		String validAccessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
				+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
		try {
			app.connectFacebook(validAccessToken);
		} catch (Exception e) {
			fail("falha para dados validos");
		}
		app.sendValidation(null);
	}
	
	@Test
	public void twitterValidationTest1() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.setFacebookConnected(true);
		app.twitterValidation(null);
	}
	
	@Test
	public void twitterValidationTest2() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.twitterValidation("s");
	}
	
	@Test
	public void twitterValidationTest3() {
		App app = new App(new Gui(), new Facebook(), new Twitter());
		app.twitterValidation("ola");
	}
}
