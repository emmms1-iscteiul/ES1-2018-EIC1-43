package EIC1_43.BDA;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * 
 * 
 * Class responsible for checking app internet connection state
 *
 */
public class InternetConnectionChecker extends Thread {

	private App app;
	
	
	/**
	 * 
	 * Default constructor 
	 * 
	 * @param app
	 */

	public InternetConnectionChecker(App app) {
		this.app = app;
	}
	
	/**
	 * 
	 * Verifies internet connection state and connects to the Internet
	 * 
	 */

	public void run() {
		try {
			sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			URL url = new URL("http://www.google.com");
			URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			this.app.setInternetConnected(true);
			this.app.notAll();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			this.app.setInternetConnected(false);
			this.app.notAll();
		}
	}
}
