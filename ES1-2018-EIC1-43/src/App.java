import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * "Main" project class 
 *
 */
public class App {

	private newGui gui;
	private Facebook facebook;
	private Twitter twitter;
	private Boolean internetConnected = false;
	private Boolean facebookConnected = false;
	private Boolean twitterConnected = false;

	/**
	 * Default constructor
	 * @param gui
	 * @param facebook
	 * @param twitter
	 */
	
	public App(newGui gui, Facebook facebook, Twitter twitter) {
		this.gui = gui;
		this.facebook = facebook;
		this.twitter = twitter;
		this.internetConnected = true;
		this.gui.setApp(this);
	}

	public newGui getGui() {
		return gui;
	}

	public void setGui(newGui gui) {
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

	public Boolean isInternetConnected() {
		return internetConnected;
	}

	public void setInternetConnected(Boolean internetConnected) {
		this.internetConnected = internetConnected;
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
	
	/**
	 * Adds tweets into GUI
	 */
	public void addTweetsIntoGui() {
		String limit = "";
		ArrayList<String> iscte = twitter.getIscteTweets();
		ArrayList<String> me = twitter.getMeTweets();
		for (String s : me) {

			for (int i = 0; i < 70; i++) {
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

			for (int i = 0; i < 70; i++) {
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
	
	/**
	 * Shows tweets on GUI
	 * 
	 * @param i
	 * @return
	 */
	
	public String showTweetOnGui(int i) {
		if (i < twitter.getMeTweets().size()) {
			return twitter.getMeTweets().get(i);
		} else {
			return twitter.getIscteTweets().get(i - twitter.getMeTweets().size());
		}
	}
	
	/**
	 * Adds facebook posts into GUI
	 * 
	 */
	
	public void addPostsIntoGui() {
		String limit = "";
		ArrayList<String> a = facebook.getPosts();
		for (String s : a) {

			for (int i = 0; i < 70; i++) {
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
	
	/**
	 * 
	 * Shows posts on GUI
	 * 
	 * @param s
	 * @return
	 */

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

	/**
	 * Verifies internet connection state
	 * 
	 */
	
	public synchronized void verifyInternetConnection() {
		new InternetConnectionChecker(this).start();
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}

	public synchronized void notAll() {
		notifyAll();
	}
	
	/**
	 * Displays posts or tweets content to user
	 * 
	 */
	
	public void displayContent() {
		if (this.twitterConnected && !this.facebookConnected) {
			this.gui.getTxtBody().setText(showTweetOnGui(this.gui.getList().getSelectedIndex()));
		} else if (this.facebookConnected && !this.twitterConnected) {
			this.gui.getTxtBody().setText(showPostOnGui(this.gui.getList().getSelectedValue()));
		}

	}

	/**
	 * 
	 * Connects app to facebook
	 * 
	 */
	
	public void connectFacebook() {
		this.gui.clearList();
		this.gui.getTxtBody().setText("");
		addPostsIntoGui();
		this.twitterConnected = false;
		this.facebookConnected = true;
	}
	
	
	/**
	 * 
	 * Connects app to twitter
	 * 
	 */

	public void connectTwitter() {
		verifyInternetConnection();
		if (internetConnected) {
			this.gui.clearList();
			this.twitter.updateTwitter();
			addTweetsIntoGui();
			this.facebookConnected = false;
			this.twitterConnected = true;
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet" + "\n" + "Funcionalidade indisponivel", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * 
	 * publish the post or tweet content
	 * 
	 */
	
	public void post() {
		verifyInternetConnection();
		if (this.twitterConnected && !this.facebookConnected && this.internetConnected) {
			this.twitter.sendTweet(this.gui.getTxtSend().getText());
			this.twitter.updateTwitter();
			this.gui.clearList();
			this.gui.getTxtSend().setText("");
			addTweetsIntoGui();
			this.facebookConnected = false;
			this.twitterConnected = true;
		} else if (this.facebookConnected && !this.twitterConnected && this.internetConnected) {
			//
			//
			//
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet" + "\n" + "Funcionalidade indisponivel", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
