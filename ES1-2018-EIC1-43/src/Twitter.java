import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter {

	private static final String source = "ISCTE";
	private ArrayList<String> iscteTweets = new ArrayList<String>();
	private ArrayList<String> meTweets = new ArrayList<String>();
	private twitter4j.Twitter twitter;
	
	
	public Twitter() {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("aPfxAAjRjrk8qXO2GNxfSJRtw")
					.setOAuthConsumerSecret("SRS1wej8h16Cy1qAopvxqMlqO6s32GoI5RkmpqjZ8FIclfTunI")
					.setOAuthAccessToken("1050480069683150848-JMLbDeriDGUGgHxdCsk1XYXTncJA3M")
					.setOAuthAccessTokenSecret("ETTzdyDTpGenVbsL7f5TqrcTn2VkKtBvZNEzwx8QCOMSY");
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();
			List<Status> statuses = twitter.getHomeTimeline();
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getSource() {
		return source;
	}
	
	public ArrayList<String> getIscteTweets(){
		return this.iscteTweets;
	}
	
	public ArrayList<String> getMeTweets(){
		return this.meTweets;
	}
	
	public void sendTweet(String s) {
		try {
			twitter.updateStatus(s);
		} catch (TwitterException e) {
			System.out.println("Erro ao enviar tweet");
		}	
	}
	
	public void updateTwitter() {
		iscteTweets = new ArrayList<String>();
		meTweets = new ArrayList<String>();
		List<Status> statuses;
		try {
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
		} catch (TwitterException e) {
		}
	}
}
