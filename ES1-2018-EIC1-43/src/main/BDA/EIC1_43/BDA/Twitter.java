package EIC1_43.BDA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private ArrayList<TwitterMessage> iscteTweets = new ArrayList<TwitterMessage>();
	private ArrayList<TwitterMessage> meTweets = new ArrayList<TwitterMessage>();
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

	/**
	 * returns iscte tweets
	 * 
	 * @return ArrayList<TwitterMessage>
	 */
	public ArrayList<TwitterMessage> getIscteTweets() {
		return this.iscteTweets;
	}
	
	/**
	 * returns user tweets
	 * 
	 * @return ArrayList<TwitterMessage>
	 */

	public ArrayList<TwitterMessage> getMeTweets() {
		return this.meTweets;
	}

	/**
	 * 
	 * Sends tweet which means twitter status update
	 * 
	 * @param s
	 */

	public void sendTweet(String s) throws TwitterException {
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
		iscteTweets = new ArrayList<TwitterMessage>();
		meTweets = new ArrayList<TwitterMessage>();
		List<Status> statuses;
		statuses = twitter.getHomeTimeline();

		for (Status status : statuses) {
			if (status.getUser().getName() != null && status.getUser().getName().contains("ISCTE")) {
				
				TwitterMessage twitterMessage = new TwitterMessage (status.getCreatedAt(), status.getText());
				iscteTweets.add(twitterMessage);
			}
		}
		
		Collections.sort(iscteTweets, new Comparator<TwitterMessage>() {

			@Override
			public int compare(TwitterMessage arg0, TwitterMessage arg1) {
				return arg1.getDate().compareTo(arg0.getDate());
			}
			
		});
		
		Collections.reverse(iscteTweets);

		for (Status status : statuses) {
			if (status.getUser().getName() != null && status.getUser().getName().contains("ES")) {
				
				TwitterMessage twitterMessage = new TwitterMessage (status.getCreatedAt(), status.getText());
				meTweets.add(twitterMessage);
			}
		}
		
		Collections.sort(meTweets, new Comparator<TwitterMessage>() {

			@Override
			public int compare(TwitterMessage arg0, TwitterMessage arg1) {
				return arg0.getDate().compareTo(arg1.getDate());
			}
			
		});
		
		Collections.reverse(meTweets);
	}
	
	/**
	 * returns twitter from twitter4j API
	 * 
	 * @return twitter4j.Twitter
	 */

	public twitter4j.Twitter getTwitter() {
		return twitter;
	}

	/**
	 * sets Twitter 
	 * 
	 * @param twitter
	 */
	
	public void setTwitter(twitter4j.Twitter twitter) {
		this.twitter = twitter;
	}

	/**
	 * sets IscteTweets 
	 * 
	 * @param iscteTweets
	 */
	public void setIscteTweets(ArrayList<TwitterMessage> iscteTweets) {
		this.iscteTweets = iscteTweets;
	}

	/**
	 * sets user tweets
	 * @param meTweets
	 */
	public void setMeTweets(ArrayList<TwitterMessage> meTweets) {
		this.meTweets = meTweets;
	}
	
	/**
	 * gets configurationBuilder - class needed for twitter process
	 * 
	 * @return ConfigurationBuilder
	 */
	public ConfigurationBuilder getCb() {
		return cb;
	}
	
	/**
	 * sets configurationBuilder
	 * @param cb
	 */

	public void setCb(ConfigurationBuilder cb) {
		this.cb = cb;
	}

	/**
	 * returns twitterfactory - class needed for twitter process
	 * 
	 * @return TwitterFactory
	 */
	public TwitterFactory getTf() {
		return tf;
	}

	/**
	 * sets twitter factory
	 * 
	 * @param tf
	 */
	public void setTf(TwitterFactory tf) {
		this.tf = tf;
	}

	
	/**
	 * sets twitter info
	 * 
	 * @param info information necessary to feed configuration builder
	 * @throws TwitterException thrown in case info's not valid 
	 */
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