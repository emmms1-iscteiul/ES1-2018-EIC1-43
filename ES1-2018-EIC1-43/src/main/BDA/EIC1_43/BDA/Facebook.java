package EIC1_43.BDA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import com.restfb.types.User;


/**
 * 
 * facebook class. All facebook content remains here
 *
 */
public class Facebook {

	private String accessToken;
	private User myUser;
	private FacebookClient fbClient;
	private ArrayList<FacebookMessage> posts = new ArrayList<FacebookMessage>();
	
	
	/**
	 * updates posts
	 * 
	 * @throws Exception thrown in case it's not possible to update posts
	 */
	
	public void updatePosts() throws Exception {
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);
		List<Post> newsfeed = result.getData();
		for (Post aFeed : newsfeed) {
			if (aFeed.getMessage() != null) {
				
				FacebookMessage facebookMessage = new FacebookMessage(aFeed.getCreatedTime(), aFeed.getMessage()); 
				posts.add(facebookMessage);
			}
		}
		
		Collections.sort(posts, new Comparator<FacebookMessage>() {

			@Override
			public int compare(FacebookMessage arg0, FacebookMessage arg1) {
				return arg1.getDate().compareTo(arg0.getDate());
			}
			
		});
	}
	
	/**
	 * returns posts
	 * 
	 * @return ArrayList<FacebookMessage>
	 */

	public ArrayList<FacebookMessage> getPosts() {
		return this.posts;
	}
	
	/**
	 * 
	 * returns facebook user
	 * 
	 * @return User
	 */

	public User getMyUser() {
		return myUser;
	}

	/**
	 * sets facebook user
	 * @param myUser
	 */
	
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
	
	/**
	 * returns fbClient
	 * 
	 * @return FacebookClient
	 */
	
	public FacebookClient getFbClient5() {
		return fbClient;
	}
	
	/**
	 * sets FacebookClient
	 * 
	 * @param fbClient5
	 */

	public void setFbClient5(FacebookClient fbClient5) {
		this.fbClient = fbClient5;
	}

	/**
	 * returns facebookClient
	 * 
	 * @return FacebookClient
	 */
	public FacebookClient getFbClient2() {
		return fbClient;
	}
	
	/**
	 * sets facebook Client
	 * 
	 * @param fbClient2
	 */

	public void setFbClient2(FacebookClient fbClient2) {
		this.fbClient = fbClient2;
	}
	
	/**
	 * returns facebook accessToken
	 * @return String
	 */

	public String getAccesstoken() {
		return accessToken;
	}

	/**
	 * sets facebook posts
	 * 
	 * @param posts
	 */
	public void setPosts(ArrayList<FacebookMessage> posts) {
		this.posts = posts;
	}
	
	/**
	 * sets facebook accessToken
	 * 
	 * @param at
	 */
	
	public void setAccesstoken(String at) {
		this.accessToken = at;
	}
	
	/**
	 * sets facebook client
	 * @throws Exception thrown in case accesstoken's not valid
	 */
	public void setClient() throws Exception  {
		fbClient = new DefaultFacebookClient(accessToken);
		myUser = fbClient.fetchObject("me", User.class);
	}
}

