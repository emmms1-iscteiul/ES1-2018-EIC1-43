










import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {

	private newGui gui;
	private Facebook facebook;
	private Twitter twitter;
	private App app;
	
	
	
	@Test
	public void Test() {
		
		gui = new newGui();
		facebook = new Facebook();
		twitter = new Twitter();
		app = new App(gui,facebook,twitter);
		
		assertNotEquals(null,gui);
		assertNotEquals(null,facebook);
		assertNotEquals(null,twitter);
		assertNotEquals(null,app);
		assumeTrue(app.isInternetConnected());
		assumeNotNull(app.getGui());
		assumeNotNull(gui.getResultsFrame());
		assumeNotNull(gui.getAllButton());
		assumeNotNull(gui.getTwitterButton());
		assumeNotNull(gui.getFacebookButton());
		assumeNotNull(gui.getEmailButton());
		assumeNotNull(gui.getBtnDisplay());
		assumeNotNull(gui.getBtnPost());
		assumeNotNull(gui.getModelList());
		assumeNotNull(gui.getList());
		assumeNotNull(twitter.getIscteTweets());
		assumeNotNull(twitter.getMeTweets());
		assumeNotNull(twitter.getTwitter());
		
		gui.resultsFrameContent();
		assumeNotNull(gui.getSelectedFrame());
		assumeNotNull(gui.getTxtBody());
		
		gui.postFrameContent();
		assumeNotNull(gui.getSendFrame());
		assumeNotNull(gui.getTxtSend());
		
	}

}
