import java.util.ArrayList;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;


public class Facebook {

	private static final String accessToken = "EAAHtJPVEpV0BAJAbajrfQylqhsTSLeOLKyr7l2M8IZCrNymApkltSQXl4749Mlz1SiZCFazjrXFgTQrWL4TBS9Wa4Na3Y9aXC5CHRgjsapf3viuvJHHhVt1ZAbUcocqMioIzH87dp3He0HRZBZBg4Z"
			+ "B93phxR2qZAF1qzRFBl3xZBvOfRgF8fv71Seh9To73F9qvYT2TqOxspgZDZD";
	
	
	private FacebookClient fbClient5;
	private ArrayList<String> posts = new ArrayList<String>();

	public Facebook() {

//		String accessToken2 = accessToken;
//		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
//
//		User me2 = fbClient2.fetchObject("me", User.class);
//		String accessToken4 = accessToken;
//		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
//		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken("542217966232925",
//				"00ea3cddf9e1a7cbf886a8e8357c617e");
//
//		String accessToken5 = accessToken;
//		fbClient5 = new DefaultFacebookClient(accessToken5);
//
//		Connection<Post> result = fbClient5.fetchConnection("me/feed", Post.class);
//		List<Post> newsfeed = result.getData();
//		for (Post aFeed : newsfeed) {
//			if (aFeed.getMessage() != null) {
//				posts.add(aFeed.getMessage());
//			}
//		}
	}

	public ArrayList<String> getPosts() {
		return this.posts;
	}
	
	public void post(String s) {
	}

}
