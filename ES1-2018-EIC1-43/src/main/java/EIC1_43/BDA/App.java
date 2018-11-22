package EIC1_43.BDA;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import twitter4j.TwitterException;

/**
 * "Main" project class
 *
 */
public class App {

	private EmailSessionBean email;
	private Gui gui;
	private Facebook facebook;
	private Twitter twitter;
	private Boolean internetConnected = false;
	private Boolean facebookConnected = false;
	private Boolean twitterConnected = false;
	private Boolean emailConnected = false;

	/**
	 * Default constructor
	 * 
	 * @param gui
	 * @param facebook
	 * @param twitter
	 */

	public App(Gui gui, Facebook facebook, Twitter twitter) {
		this.gui = gui;
		this.facebook = facebook;
		this.twitter = twitter;
		this.internetConnected = true;
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

	public Boolean getEmailConnected() {
		return emailConnected;
	}

	public void setEmailConnected(Boolean emailConnected) {
		this.emailConnected = emailConnected;
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
	 * Shows tweet on GUI
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
	 * 
	 * Shows post on GUI
	 * 
	 * @param s
	 * @return
	 */

	public String showPostOnGui(int i) {
		return facebook.getPosts().get(i);
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
			this.gui.getModelList().addElement("  me:  " + limit);
			limit = "";
		}
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
		if (displayValidation()) {
			if (this.twitterConnected && !this.facebookConnected) {
				this.gui.resultsFrameContent();
				this.gui.getTxtBody().setText(showTweetOnGui(this.gui.getList().getSelectedIndex()));
			} else if (this.facebookConnected && !this.twitterConnected) {
				this.gui.resultsFrameContent();
				this.gui.getTxtBody().setText(showPostOnGui(this.gui.getList().getSelectedIndex()));
			}
		}

	}

	public boolean displayValidation() {
		return this.gui.getList() != null && !this.gui.getList().isSelectionEmpty();
	}

	/**
	 * 
	 * Connects app to facebook
	 * 
	 */

	public void connectFacebook(String accessToken) throws Exception {
		verifyInternetConnection();
		this.facebook.setAccesstoken(accessToken);
		this.facebook.setClient();
		this.facebook.updatePosts();
		this.gui.clearList();
		addPostsIntoGui();
		this.twitterConnected = false;
		this.facebookConnected = true;
	}

	/**
	 * 
	 * Connects app to twitter
	 * 
	 * @throws TwitterException
	 * 
	 */

	public void connectTwitter(ArrayList<String> info) throws TwitterException {
		verifyInternetConnection();
		this.twitter.setInfo(info);
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
	 * @throws TwitterException
	 * 
	 */

	public void post(String destino, String assunto) throws Exception {
		verifyInternetConnection();
		if (this.twitterConnected && !this.facebookConnected && this.internetConnected && !this.emailConnected) {
			this.twitter.sendTweet(this.gui.getTxtSend().getText());
			this.twitter.updateTwitter();
			this.gui.clearList();
			this.gui.getTxtSend().setText("");
			addTweetsIntoGui();
			this.facebookConnected = false;
			this.twitterConnected = true;
			JOptionPane.showMessageDialog(null, "Post enviado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
		} else if (this.facebookConnected && !this.twitterConnected && this.internetConnected) {

		} else if (this.emailConnected && !this.facebookConnected && !this.twitterConnected && this.internetConnected) {
			this.email.sendEmail(destino, assunto, this.gui.getTxtSend().getText());
			this.gui.clearList();
			this.gui.getTxtSend().setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet" + "\n" + "Funcionalidade indisponivel", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void disconnect() {
		this.gui.clearList();
	}

	public void connectEmail(String username, String password) throws Exception {
		verifyInternetConnection();
		email = new EmailSessionBean(username, password);
		this.emailConnected = true;
	}

	public void facebookValidation() {
		if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
			JOptionPane.showMessageDialog(null, "Abandone o servico que esta a utilizar", "",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String accessToken = JOptionPane.showInputDialog("Introduza o access Token");
			if (accessToken != null) {
				try {
					connectFacebook(accessToken);
					this.gui.addDisconnect();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Dados de acesso ao facebook invalidos", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	public void emailValidation() {
		if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
			JOptionPane.showMessageDialog(null, "Abandone o servico que esta a utilizar", "",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			boolean isValid = true;
			String username = "";
			String password = "";
			String escolha = JOptionPane.showInputDialog("Pretende aceder ao email como default user? [s/n]");
			if (escolha != null && (escolha.equals("s") || escolha.equals("S"))) {
				username = "ola123ola123ola123software@gmail.com";
				password = "modfzrjlqjmkruhp";
			} else if (escolha != null && (escolha.equals("n") || escolha.equals("N"))) {
				username = JOptionPane.showInputDialog("Introduza o email");
				password = JOptionPane.showInputDialog("Introduza a password da aplicacao");
			} else if (escolha != null) {
				JOptionPane.showMessageDialog(null, "Resposta Invalida", "", JOptionPane.INFORMATION_MESSAGE);
				isValid = false;
			} else {
				isValid = false;
			}
			try {
				if (isValid) {
					connectEmail(username, password);
					this.gui.addDisconnect();
					JOptionPane.showMessageDialog(null, "Ligacao Estabelecida com Sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ligacao ao email sem sucesso.", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void postValidation() {
		if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
			if (this.facebookConnected) {
				JOptionPane.showMessageDialog(null, "Funcionalidade indisponivel", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				this.gui.postFrameContent();
			}
		}

	}

	public void sendValidation() {
		if (this.emailConnected) {
			String destino = JOptionPane.showInputDialog("Introduza o email destino");
			String assunto = JOptionPane.showInputDialog("Introduza o assunto do email");
			try {
				post(destino, assunto);
				JOptionPane.showMessageDialog(null, "Email enviado com sucesso!", "",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Dados incorretos. Erro ao enviar email.", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			try {
				post(null, null);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Erro ao enviar mensagem", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void setDc() {
		this.facebookConnected = false;
		this.twitterConnected = false;
		this.emailConnected = false;
		disconnect();
		this.gui.getResultsFrame().remove(this.gui.getDisconnectButton());
		this.gui.getResultsFrame().repaint();
	}
	
	public void twitterValidation() {
		if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
			JOptionPane.showMessageDialog(null, "Abandone o servico que esta a utilizar", "",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			ArrayList<String> info = new ArrayList<String>();
			String escolha = JOptionPane.showInputDialog("Pretende aceder ao Twitter como default user? [s/n]");
			if (escolha != null && (escolha.equals("s") || escolha.equals("S"))) {
				info.add("bPcMjzld5PjWFDYMl401Ak0kw");
				info.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
				info.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
				info.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");
			} else if (escolha != null && (escolha.equals("n") || escolha.equals("N"))) {
				info.add(JOptionPane.showInputDialog("Introduza AuthConsumerKey"));
				info.add(1, JOptionPane.showInputDialog("Introduza AuthConsumerSecret"));
				info.add(2, JOptionPane.showInputDialog("Introduza AuthAccessToken"));
				info.add(3, JOptionPane.showInputDialog("Introduza AuthAccessTokenSecret"));
			} else if (escolha != null) {
				JOptionPane.showMessageDialog(null, "Resposta Invalida", "", JOptionPane.INFORMATION_MESSAGE);
			}
			try {
				if (info.size() != 0) {
					connectTwitter(info);
					this.gui.getResultsFrame().add(this.gui.getDisconnectButton());
					this.gui.getResultsFrame().repaint();
				}

			} catch (TwitterException e1) {
				JOptionPane.showMessageDialog(null, "Dados de acesso ao Twitter inválidos", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}