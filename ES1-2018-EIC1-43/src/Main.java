import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

/**
 * 
 * 
 * Class which starts the program
 * 
 *
 */
public class Main {

	
	/**
	 * 
	 * runs application
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.google.com");
			URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			new App(new NewGui(), new Facebook(), new Twitter());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Sem Internet" + "\n" + "Conecte-se antes de iniciar a aplicação", "", JOptionPane.INFORMATION_MESSAGE);

		}
		
	}

}
