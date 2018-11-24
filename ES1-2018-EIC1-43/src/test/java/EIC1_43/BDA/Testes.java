package EIC1_43.BDA;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeTrue;

import EIC1_43.BDA.Facebook;
import EIC1_43.BDA.Gui;

class Testes {

	private Gui gui;
	private Facebook facebook;
	private Twitter twitter;
	private App app;
	
	
	public void Test() {
		
		gui = new Gui();
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
		assumeNotNull(gui.getDisconnectButton());
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
