import java.util.ArrayList;

public class App {

	private Gui gui;
	private Facebook facebook;
	private Twitter twitter;
	private Boolean facebookConnected = false;
	private Boolean twitterConnected = false;

	public App(Gui gui, Facebook facebook, Twitter twitter) {
		this.gui = gui;
		this.facebook = facebook;
		this.twitter = twitter;
		this.gui.setApp(this);
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public Boolean isFacebookConnected() {
		return facebookConnected;
	}

	public void setFacebookConnected(Boolean facebookConnected) {
		this.facebookConnected = facebookConnected;
	}

	public Boolean isTwitterConnected() {
		return twitterConnected;
	}

	public void setTwitterConnected(Boolean twitterConnected) {
		this.twitterConnected = twitterConnected;
	}

	public void addTweetsIntoGui() {
		String limit = "";
		ArrayList<String> iscte = twitter.getIscteTweets();
		ArrayList<String> me = twitter.getMeTweets();
		for (String s : me) {

			for (int i = 0; i < 15; i++) {
				if (s.length() > i) {
					limit = limit + s.charAt(i);
				} else {
					break;
				}
			}
			this.gui.getModelList().addElement("  me:  " + limit);
			limit = "";
		}
		for (String s : iscte) {

			for (int i = 0; i < 15; i++) {
				if (s.length() > i) {
					limit = limit + s.charAt(i);
				} else {
					break;
				}
			}
			this.gui.getModelList().addElement("ISCTE: " + limit);
			limit = "";
		}

	}

	public String showTweetOnGui(int i) {
		if (i < twitter.getMeTweets().size()) {
			return twitter.getMeTweets().get(i);
		} else {
			return twitter.getIscteTweets().get(i - twitter.getMeTweets().size());
		}
	}

	public void addPostsIntoGui() {
		String limit = "";
		ArrayList<String> a = facebook.getPosts();
		for (String s : a) {

			for (int i = 0; i < 15; i++) {
				if (s.length() > i) {
					limit = limit + s.charAt(i);
				} else {
					break;
				}
			}
			this.gui.getModelList().addElement(limit);
			limit = "";
		}
	}

	public String showPostOnGui(String s) {
		ArrayList<String> a = facebook.getPosts();
		String aux = "";
		for (int i = 0; i < a.size(); i++) {
			aux = a.get(i).substring(0, s.length());
			if (aux.equals(s)) {
				return a.get(i);
			}
		}
		return "";
	}

	public void displayContent() {
		if (this.twitterConnected && !this.facebookConnected) {
			this.gui.getTxtBody().setText(showTweetOnGui(this.gui.getList().getSelectedIndex()));
		} else if (this.facebookConnected && !this.twitterConnected) {
			this.gui.getTxtBody().setText(showPostOnGui(this.gui.getList().getSelectedValue()));
		}

	}

	public void connectFacebook() {
		this.gui.clearList();
		this.gui.getTxtBody().setText("");
		addPostsIntoGui();
		this.twitterConnected = false;
		this.facebookConnected = true;
	}

	public void connectTwitter() {
			this.gui.clearList();
			this.gui.getTxtBody().setText("");
			this.twitter.updateTwitter();
			addTweetsIntoGui();
			this.facebookConnected = false;
			this.twitterConnected = true;
	}

	public void post() {
		if (this.twitterConnected && !this.facebookConnected) {
			this.twitter.sendTweet(this.gui.getTxtBody().getText());
			this.twitter.updateTwitter();
			this.gui.clearList();
			this.gui.getTxtBody().setText("");
			addTweetsIntoGui();
			this.facebookConnected = false;
			this.twitterConnected = true;
		} else if (this.facebookConnected && !this.twitterConnected) {
			//
			//
			//
		} 
	}
}
