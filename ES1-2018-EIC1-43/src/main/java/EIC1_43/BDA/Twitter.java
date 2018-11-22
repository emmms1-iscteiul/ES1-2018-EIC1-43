package EIC1_43.BDA;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * 
 * Class that represents twitter properties and saves its attributes
 * 
 *
 */
public class Twitter {

	private static final String source = "ISCTE";
	private ArrayList<String> iscteTweets = new ArrayList<String>();
	private ArrayList<String> meTweets = new ArrayList<String>();
	private twitter4j.Twitter twitter;
	private ConfigurationBuilder cb;
	private TwitterFactory tf;

	/**
	 * 
	 * Default Constructor
	 * 
	 */

	public String getSource() {
		return source;
	}

	public ArrayList<String> getIscteTweets() {
		return this.iscteTweets;
	}

	public ArrayList<String> getMeTweets() {
		return this.meTweets;
	}

	/**
	 * 
	 * Sends tweet which means twitter status update
	 * 
	 * @param s
	 */

	public void sendTweet(String s) {
		try {
			twitter.updateStatus(s);
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(null, "Erro no envio do tweet", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * 
	 * Updates app info about current tweets online
	 * @throws TwitterException 
	 * 
	 */

	public void updateTwitter() throws TwitterException {
		iscteTweets = new ArrayList<String>();
		meTweets = new ArrayList<String>();
		List<Status> statuses;
		statuses = twitter.getHomeTimeline();

		for (Status status : statuses) {
			if (status.getUser().getName() != null && status.getUser().getName().contains("ISCTE")) {
				iscteTweets.add(status.getText());
			}
		}

		for (Status status : statuses) {
			if (status.getUser().getName() != null && status.getUser().getName().contains("ES")) {
				meTweets.add(status.getText());
			}
		}
	}

	public twitter4j.Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(twitter4j.Twitter twitter) {
		this.twitter = twitter;
	}

	public void setIscteTweets(ArrayList<String> iscteTweets) {
		this.iscteTweets = iscteTweets;
	}

	public void setMeTweets(ArrayList<String> meTweets) {
		this.meTweets = meTweets;
	}

	public void setInfo(ArrayList<String> info) throws TwitterException {
		cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(info.get(0));
		cb.setOAuthConsumerSecret(info.get(1));
		cb.setOAuthAccessToken(info.get(2));
		cb.setOAuthAccessTokenSecret(info.get(3));
		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();

	}

}
