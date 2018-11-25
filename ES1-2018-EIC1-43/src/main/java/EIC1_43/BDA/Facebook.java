package EIC1_43.BDA;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;


public class Facebook {

	private String accessToken;
	private User myUser;
	private FacebookClient fbClient;
	private ArrayList<FacebookMessage> posts = new ArrayList<FacebookMessage>();
	
	
// Post: opcao indisponivel 
	
//	public void post(String post) {
//		fbClient.publish( "me/feed", FacebookType.class, Parameter.with("message", post));
//	}
	
	public void updatePosts() throws Exception {
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);
		List<Post> newsfeed = result.getData();
		for (Post aFeed : newsfeed) {
			if (aFeed.getMessage() != null) {
				// Cria e adiciona à lista posts, objectos do tipo FacebookMessage para todos os resultados obtidos do facebook
				FacebookMessage facebookMessage = new FacebookMessage(aFeed.getCreatedTime(), aFeed.getMessage()); 
				posts.add(facebookMessage);
			}
		}
		// Compara os objectos FacebookMessage por data e ordena os resultados do mais recente para o mais antigo
		Collections.sort(posts, new Comparator<FacebookMessage>() {

			@Override
			public int compare(FacebookMessage arg0, FacebookMessage arg1) {
				return arg1.getData().compareTo(arg0.getData());
			}
			
		});
	}

	public ArrayList<FacebookMessage> getPosts() {
		return this.posts;
	}

	public User getMyUser() {
		return myUser;
	}

	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
	
	public FacebookClient getFbClient5() {
		return fbClient;
	}

	public void setFbClient5(FacebookClient fbClient5) {
		this.fbClient = fbClient5;
	}

	public FacebookClient getFbClient2() {
		return fbClient;
	}

	public void setFbClient2(FacebookClient fbClient2) {
		this.fbClient = fbClient2;
	}

	public String getAccesstoken() {
		return accessToken;
	}

	public void setPosts(ArrayList<FacebookMessage> posts) {
		this.posts = posts;
	}
	
	public void setAccesstoken(String at) {
		this.accessToken = at;
	}
	
	public void setClient() throws Exception  {
		fbClient = new DefaultFacebookClient(accessToken);
		myUser = fbClient.fetchObject("me", User.class);
	}
}

