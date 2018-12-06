package EIC1_43.BDA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private ArrayList<Messages> appMessages = new ArrayList<Messages>();
	private int[] v = {0,0,0};

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

	public EmailSessionBean getEmail() {
		return email;
	}

	public void setEmail(EmailSessionBean email) {
		this.email = email;
	}

	/**
	 * Adds tweets into GUI
	 */
	public void addTweetsIntoGui() {

		ArrayList<TwitterMessage> iscte = twitter.getIscteTweets();
		ArrayList<TwitterMessage> me = twitter.getMeTweets();
		for (TwitterMessage s : me) {
			
			gui.addMessage(s.ObjectRepresentation());

			ActionListener action1 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					displayContent(s.getContent());
				}

			};

			s.addListner(action1);
		}
		for (TwitterMessage s : iscte) {
			
			gui.addMessage(s.ObjectRepresentation());

			ActionListener action2 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					displayContent(s.getContent());
				}

			};
			s.addListner(action2);
		}
		gui.getResultsArea().updateUI();
	}

	
	/**
	 * Adds facebook posts into GUI
	 * 
	 */

	public void writeMessages() {
		this.addMessages();
		
		Writer writer = null;
		
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("SaveMessages.txt"), "utf-8"));
		    
		    for(int i=0;i<appMessages.size();i++) {
		    	writer.write(appMessages.get(i).toStringTxt());
		    	writer.write("\n");
		    }
		    
		    
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao gerir ficheiro");
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}

	public void readMessages() {
		
		ArrayList<JPanel> lastMessages = new ArrayList<JPanel>();
		
		Scanner read;
		
		try {
			
			read = new Scanner (new File("SaveMessages.txt"));
			
			while(read.hasNextLine()) {
				
				String lineReading = read.nextLine();
				String [] vetorParametros = lineReading.split(";");
				
				if (vetorParametros[0].equals("twitter")) {
					JPanel object = ObjectRepresentation("images/"+vetorParametros[0]+".png", vetorParametros[0].toUpperCase(), 
							vetorParametros[1], vetorParametros[2], vetorParametros[3], vetorParametros[4], new Color(184,235,254));
					lastMessages.add(object);
					
				} else if (vetorParametros[0].equals("facebook")) {
					JPanel object = ObjectRepresentation("images/"+vetorParametros[0]+".png", vetorParametros[0].toUpperCase(), 
							vetorParametros[1], vetorParametros[2], vetorParametros[3], vetorParametros[4], new Color(103,155,218));
					lastMessages.add(object);
					
				} else if (vetorParametros[0].equals("email")) {
					JPanel object = ObjectRepresentation("images/"+vetorParametros[0]+".png", vetorParametros[0].toUpperCase(), 
							vetorParametros[1], vetorParametros[2], vetorParametros[3], vetorParametros[4], new Color(250, 171, 171));
					lastMessages.add(object);
					
				}
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		gui.clearList();
		
		for(JPanel j: lastMessages) {
			gui.addMessage(j);
		}
		gui.getResultsArea().updateUI();
		
	}
	
	public void readMessagesFiltradas(String data) {
		
		ArrayList<JPanel> filterMessages = new ArrayList<JPanel>();
		
		Scanner read;
		
		try {
			
			read = new Scanner (new File("SaveMessages.txt"));
			
			
			while(read.hasNextLine()) {
				
				String lineReading = read.nextLine();
				String [] vetorParametros = lineReading.split(";");
				
				if(vetorParametros[3].substring(4).equals(data)) {
					
					if (vetorParametros[0].equals("twitter")) {
						JPanel object = ObjectRepresentation("images/"+vetorParametros[0]+".png", vetorParametros[0].toUpperCase(), 
							vetorParametros[1], vetorParametros[2], vetorParametros[3], vetorParametros[4], new Color(184,235,254));
						filterMessages.add(object);
					
					} else if (vetorParametros[0].equals("facebook")) {
						JPanel object = ObjectRepresentation("images/"+vetorParametros[0]+".png", vetorParametros[0].toUpperCase(), 
							vetorParametros[1], vetorParametros[2], vetorParametros[3], vetorParametros[4], new Color(103,155,218));
						filterMessages.add(object);
					
					} else if (vetorParametros[0].equals("email")) {
						JPanel object = ObjectRepresentation("images/"+vetorParametros[0]+".png", vetorParametros[0].toUpperCase(), 
							vetorParametros[1], vetorParametros[2], vetorParametros[3], vetorParametros[4], new Color(250, 171, 171));
						filterMessages.add(object);
					
					}
				} 
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		gui.clearList();
		
		if (filterMessages.size()==0) {
			
			JOptionPane.showMessageDialog(null, "Não há mensagens para a data especificada.", "", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		for(JPanel j: filterMessages) {
			gui.addMessage(j);
		}
		gui.getResultsArea().updateUI();
	}
	
	
	
	private JPanel ObjectRepresentation (String imageCaminho, String origem, String title, String content, String data, String hora, Color color) {
		
		JPanel panelMessage = new JPanel();
		panelMessage.setPreferredSize(new Dimension(930, 60));
		panelMessage.setLayout(null);
		
		JLabel labelImage = new JLabel(new ImageIcon(imageCaminho));
		labelImage.setBounds(5, 0, 60, 60);
		JLabel labelOrigem = new JLabel(origem);
		labelOrigem.setBounds(75, 0, 100, 60);
		JLabel labelTitle = new JLabel(title);
		labelTitle.setBounds(180, 0, 200, 60);
		JLabel labelDate = new JLabel(data);
		labelDate.setBounds(600, 0, 120, 60);
		JLabel labelHora = new JLabel(hora);
		labelHora.setBounds(750, 0, 70, 60);
		
		JButton viewButton = new JButton("Ver");
		viewButton.setBounds(850, 15, 70, 30);
		
		viewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				gui.resultsFrameContent();
				gui.getTxtBody().setText(content);
				
			}
		});
		
		panelMessage.add(labelImage); panelMessage.add(labelOrigem);
		panelMessage.add(labelTitle); panelMessage.add(labelDate);
		panelMessage.add(labelHora); panelMessage.add(viewButton);
		
		panelMessage.setBackground(color);
		
		return panelMessage;
	}

	
	public void addMessages() {
		appMessages = new ArrayList<Messages>();

		if (this.emailConnected || v[2] == 1) {
			this.appMessages.addAll(email.getMails());
		}

	
		if (this.twitterConnected || v[1] == 1) {
			this.appMessages.addAll(twitter.getIscteTweets());
			this.appMessages.addAll(twitter.getMeTweets());
		}
	
		if (this.facebookConnected || v[0] == 1) {
			this.appMessages.addAll(this.facebook.getPosts());
		}
		
		Collections.sort(appMessages, new Comparator<Messages>() {

			@Override
			public int compare(Messages arg0, Messages arg1) {
				return arg0.getDate().compareTo(arg1.getDate());
			}
			
		});
		
		Collections.reverse(appMessages);
	}

	
	public void addPostsIntoGui() {

		ArrayList<FacebookMessage> a = facebook.getPosts();
		for (FacebookMessage s : a) {
			
			gui.addMessage(s.ObjectRepresentation());

			ActionListener action = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					displayContent(s.getContent());
				}

			};
			s.addListner(action);
		}
		gui.getResultsArea().updateUI();
	}

	public void addEmailsIntoGui() {
		ArrayList<EmailMessage> array = email.getMails();
		for (EmailMessage msg : array) {
			
			gui.addMessage(msg.ObjectRepresentation());

			ActionListener action = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					displayContent(msg.getContent());
				}

			};
			msg.addListner(action);
		}
		gui.getResultsArea().updateUI();
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
	 * @param isTest verifies if call comes from test
	 */

	public void displayContent(String content) {
		
		if (this.twitterConnected && !this.facebookConnected && !emailConnected) {
			
			this.gui.resultsFrameContent();
			this.gui.getTxtBody().setText(content);
			
		} else if (this.facebookConnected && !this.twitterConnected && !emailConnected) {
			this.gui.resultsFrameContent();
			
			this.gui.getTxtBody().setText(content);
		
		} else if (this.emailConnected && !this.facebookConnected && !this.twitterConnected) {
			this.gui.resultsFrameContent();
			this.gui.getTxtBody().setText(content);
		}
		

	}

	/**
	 * 
	 * Connects app to facebook
	 * 
	 * @param accessToken token needed to connect to facebook
	 * 
	 * @throws Exception
	 */

	public void connectFacebook(String accessToken) throws Exception {
		verifyInternetConnection();
		v[0] = 1;
		this.facebookConnected = true;
		this.facebook.setAccesstoken(accessToken);
		this.facebook.setClient();
		this.facebook.updatePosts();
		this.gui.clearList();
		gui.getResultsArea().updateUI();
		addPostsIntoGui();
		this.writeMessages();
		this.twitterConnected = false;
	
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
			this.twitter.setInfo(info);
			v[1] = 1;
			this.twitterConnected = true;
			this.gui.clearList();
			gui.getResultsArea().updateUI();
			this.twitter.updateTwitter();
			addTweetsIntoGui();
			this.writeMessages();
			this.facebookConnected = false;
			
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet" + "\n" + "Funcionalidade indisponivel", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * 
	 * publish the post or tweet content
	 * 
	 * @param destino post destination
	 * @param assunto post matter
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
			gui.getResultsArea().updateUI();
			this.gui.getTxtSend().setText("");
			addTweetsIntoGui();
			this.facebookConnected = false;
			this.twitterConnected = true;
			JOptionPane.showMessageDialog(null, "Post enviado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
			this.writeMessages();
		} else if (this.facebookConnected && !this.twitterConnected && this.internetConnected) {

		} else if (this.emailConnected && !this.facebookConnected && !this.twitterConnected && this.internetConnected) {
			this.email.sendEmail(destino, assunto, this.gui.getTxtSend().getText());
			this.gui.clearList();
			gui.getResultsArea().updateUI();
			this.gui.getTxtSend().setText("");
			this.writeMessages();
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet" + "\n" + "Funcionalidade indisponivel", "",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * 
	 * disconnects app from service
	 */

	public void disconnect() {
		this.gui.clearList();
		JOptionPane.showMessageDialog(null, "Fim da ligação ao serviço.", "", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * connects app to email
	 * 
	 * @param username name of email user
	 * @param password email user application's password
	 * @throws Exception request is not getting served
	 * 
	 */

	public void connectEmail(String username, String password) throws Exception {
		verifyInternetConnection();
		v[2] = 1;
		email = new EmailSessionBean(username, password);
		this.email.updateEmail();
		this.emailConnected = true;
		this.gui.clearList();
		gui.getResultsArea().updateUI();
		addEmailsIntoGui();
		this.writeMessages();
		this.facebookConnected = false;
		this.twitterConnected = false;
	}

	/**
	 * sets application disconnected
	 */
	public void setDc() {
		if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
			this.facebookConnected = false;
			this.twitterConnected = false;
			this.emailConnected = false;
			disconnect();
		}
	}

	/**
	 * checks facebook connection
	 * 
	 * @param test verifies if call comes from test
	 */
	public void facebookValidation(String test) {
		verifyInternetConnection();
		if(internetConnected) {
			if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
				JOptionPane.showMessageDialog(null, "Abandone o servico que esta a utilizar", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				boolean isValid = true;
				String accessToken = "";
				String escolha = "";
				if (test == null) {
					escolha = JOptionPane.showInputDialog("Pretende aceder ao email como default user? [s/n]");
				}
				test = "";
				if ((escolha != null && (escolha.equals("s") || escolha.equals("S")))
						|| (test != null && test.equals("s"))) {
					accessToken = "EAADfa40pTAwBAHVrPBdHGgr9JeN9XFqQVXvfcop6PUxl4Oa9nsDFD3A8lgW0sesvZAWDZBZCSj5sp0uhTiIQDFhWz"
							+ "3sB4sFfCVA6bcLJrZCk6ZAUFxNBtqnrUvosZAOuKTNCdZB9El8tubEPbJJ9RaKpIQuW3c0JIEwpvnSMdzwVwZDZD";
				} else if (escolha != null && (escolha.equals("n") || escolha.equals("N"))) {
					accessToken = JOptionPane.showInputDialog("Introduza o access Token");
				} else if (escolha != null || test.equals("lol")) {
					JOptionPane.showMessageDialog(null, "Resposta Invalida", "", JOptionPane.INFORMATION_MESSAGE);
					isValid = false;
				} else {
					isValid = false;
				}
				if (isValid && accessToken != null) {
					try {
						connectFacebook(accessToken);
						JOptionPane.showMessageDialog(null, "Ligacao Estabelecida com Sucesso", "",
								JOptionPane.INFORMATION_MESSAGE);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Dados de acesso ao facebook invalidos", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet. Servico online indisponivel.", "", JOptionPane.INFORMATION_MESSAGE);	
		}
	}

	/**
	 * checks email connection
	 * 
	 * @param test checks if call comes from test
	 */
	public void emailValidation(String test) {
		verifyInternetConnection();
		if(internetConnected) {
			
			if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
				JOptionPane.showMessageDialog(null, "Abandone o servico que esta a utilizar", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				boolean isValid = true;
				String username = "";
				String password = "";
				String escolha = "";
				if (test == null) {
					escolha = JOptionPane.showInputDialog("Pretende aceder ao email como default user? [s/n]");
				}
				if ((escolha != null && (escolha.equals("s") || escolha.equals("S"))) || test != null && test.equals("s")) {
					username = "ola123ola123ola123software@gmail.com";
					password = "modfzrjlqjmkruhp";
				} else if (escolha != null && (escolha.equals("n") || escolha.equals("N"))) {
					username = JOptionPane.showInputDialog("Introduza o email");
					password = JOptionPane.showInputDialog("Introduza a password da aplicacao");
				} else if (escolha != null || (test != null && test.equals("ola"))) {
					JOptionPane.showMessageDialog(null, "Resposta Invalida", "", JOptionPane.INFORMATION_MESSAGE);
					isValid = false;
				} else {
					isValid = false;
				}
				try {
					if (isValid) {
						connectEmail(username, password);
						JOptionPane.showMessageDialog(null, "Ligacao Estabelecida com Sucesso", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ligacao ao email sem sucesso.", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet. Servico online indisponivel.", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * checks if post is valid
	 * 
	 */

	public void postValidation() {
		if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
			verifyInternetConnection();
			if (internetConnected) {
				if (this.facebookConnected) {
					JOptionPane.showMessageDialog(null, "Funcionalidade indisponivel", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					this.gui.postFrameContent();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Sem internet. Servico online indisponivel.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	/**
	 * checks if send request is valid
	 * 
	 * @param test checks if call comes from test scenario
	 */
	public void sendValidation(String test) {
		String destino = "";
		String assunto = "";
		if (this.emailConnected) {
			if (test == null) {
				destino = JOptionPane.showInputDialog("Introduza o email destino");
				assunto = JOptionPane.showInputDialog("Introduza o assunto do email");
			}
			if (test != null && test.equals("mail")) {
				destino = "ola123ola123ola123software@gmail.com";
				assunto = "ola amigo";
			} else if (test != null && test.equals("mailFail")) {
				destino = "vai falhar";
				assunto = "adeus amigo";
			}
			try {
				post(destino, assunto);
				JOptionPane.showMessageDialog(null, "Email enviado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Dados incorretos. Erro ao enviar email.", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			try {
				post(null, null);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Erro ao enviar mensagem", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * checks twitter validation
	 * 
	 * @param test checks if call comes from test
	 */
	public void twitterValidation(String test) {
		verifyInternetConnection();
		if(internetConnected) {
			
			ArrayList<String> info = new ArrayList<String>();
			if (this.facebookConnected || this.twitterConnected || this.emailConnected) {
				JOptionPane.showMessageDialog(null, "Abandone o servico que esta a utilizar", "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				String escolha = "";
				if (test == null) {
					escolha = JOptionPane.showInputDialog("Pretende aceder ao Twitter como default user? [s/n]");
				}
				if ((escolha != null && (escolha.equals("s") || escolha.equals("S")))
						|| (test != null && test.equals("s"))) {
					info.add("bPcMjzld5PjWFDYMl401Ak0kw");
					info.add(1, "V6gRA7z4HTpaiuNaoIYZ6wDxFkaEEr4jZRLOOWYOe8fdHP9Ita");
					info.add(2, "1050480069683150848-Fjr0lz1c6jnRMCzg5VMr2xlhBD0hju");
					info.add(3, "6TdjqHUWSz3BzkXb3tXNn8XknD7MlCN1a9kewLzFMPLBV");
				} else if (escolha != null && (escolha.equals("n") || escolha.equals("N"))) {
					info.add(JOptionPane.showInputDialog("Introduza AuthConsumerKey"));
					info.add(1, JOptionPane.showInputDialog("Introduza AuthConsumerSecret"));
					info.add(2, JOptionPane.showInputDialog("Introduza AuthAccessToken"));
					info.add(3, JOptionPane.showInputDialog("Introduza AuthAccessTokenSecret"));
				} else if (escolha != null || (test != null && test.equals("ola"))) {
					JOptionPane.showMessageDialog(null, "Resposta Invalida", "", JOptionPane.INFORMATION_MESSAGE);
				}

				try {
					if (test != null && !test.equals("s")) {
						info.add("Penso");
						info.add(1, "que");
						info.add(2, "isto");
						info.add(3, "falha");
						connectTwitter(info);
					}
					if (info.size() != 0) {
						connectTwitter(info);
						this.gui.getResultsFrame().add(this.gui.getDisconnectButton());
						this.gui.getResultsFrame().repaint();
						JOptionPane.showMessageDialog(null, "Ligacao Estabelecida com Sucesso", "",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (TwitterException e1) {
					JOptionPane.showMessageDialog(null, "Dados de acesso ao Twitter inválidos", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Sem Internet. Servico online indisponivel.", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
