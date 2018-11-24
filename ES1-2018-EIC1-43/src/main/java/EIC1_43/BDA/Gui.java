package EIC1_43.BDA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * 
 * 
 * Enables interaction with user
 * 
 */
public class Gui {

	private App app = new App(this, new Facebook(), new Twitter());

	private JFrame resultsFrame;
	private JFrame selectedFrame;
	private JFrame sendFrame;

	private JTextArea txtBody;
	private JTextArea txtSend;
	private JList<String> list;
	private DefaultListModel<String> model;

	private JButton disconnectButton;
	private JButton facebookButton;
	private JButton twitterButton;
	private JButton emailButton;
	private JButton refreshButton;

	private JButton btnDisplay;
	private JButton btnPost;

	/**
	 * 
	 * Default constructor
	 * 
	 */

	public Gui() {

		resultsFrame = new JFrame("Bom Dia Academia (BDA)");
		resultsFrame.setMinimumSize(new Dimension(1000, 600));
		resultsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		resultsFrame.setLayout(null);
		resultsFrame.setResizable(false);
		resultsFrame.setLocationRelativeTo(null);
		resultsFrame.setIconImage(new ImageIcon("images/ba.png").getImage());;

		addResultsFrameContent();
		this.open();

	}

	/**
	 * 
	 * opens a new window which contains tweet or post content
	 * 
	 */

	public void resultsFrameContent() {

		selectedFrame = new JFrame("RESULTS CONTENT");
		selectedFrame.setMinimumSize(new Dimension(500, 500));
		selectedFrame.setLayout(null);
		selectedFrame.setResizable(false);
		selectedFrame.setLocationRelativeTo(null);
		selectedFrame.setIconImage(new ImageIcon("images/ba.png").getImage());;

		txtBody = new JTextArea();
		txtBody.setPreferredSize(new Dimension(460, 700));
		txtBody.setRows(100);
		txtBody.setColumns(100);
		txtBody.setLineWrap(true);
		txtBody.setFont(new Font("Serif", Font.PLAIN, 20));
		JScrollPane scroll = new JScrollPane(txtBody);
		scroll.setBounds(5, 5, 485, 460);

		selectedFrame.add(scroll);

		selectedFrame.setVisible(true);

	}

	/**
	 * 
	 * 
	 * Opens a new window which enables user to type potential post or tweet content
	 * 
	 */

	public void postFrameContent() {

		sendFrame = new JFrame("POST");
		sendFrame.setMinimumSize(new Dimension(500, 500));
		sendFrame.setLayout(null);
		sendFrame.setResizable(false);
		sendFrame.setLocationRelativeTo(null);
		sendFrame.setIconImage(new ImageIcon("images/ba.png").getImage());;

		JButton sendButton = new JButton("SEND");
		sendButton.setBounds(150, 390, 200, 40);

		txtSend = new JTextArea();
		txtSend.setPreferredSize(new Dimension(460, 500));
		JScrollPane scroll = new JScrollPane(txtSend);
		scroll.setBounds(5, 50, 485, 300);

		JLabel label = new JLabel("Digite texto para publicar: ");
		label.setBounds(10, 10, 300, 20);

		sendFrame.add(scroll);
		sendFrame.add(sendButton);
		sendFrame.add(label);

		sendFrame.setVisible(true);

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.sendValidation();
			}

		});

	}

	/**
	 * 
	 * Opens main GUI window
	 * 
	 */

	private void addResultsFrameContent() {

		disconnectButton = new JButton("DISCONNECT  APP");
		disconnectButton.setBounds(160, 25, 150, 50);
		disconnectButton.setBackground(new Color(237,28,36));
		disconnectButton.setEnabled(false);
		refreshButton = new JButton(new ImageIcon("images/refresh.png"));
		refreshButton.setBounds(20, 25, 50, 50);
		twitterButton = new JButton("   TWITTER", new ImageIcon("images/twitterminiicon.png"));
		twitterButton.setBackground(new Color(150,150,150));
		twitterButton.setBounds(410, 25, 150, 50);
		facebookButton = new JButton("   FACEBOOK", new ImageIcon("images/faceminiicon.png"));
		facebookButton.setBackground(new Color(150,150,150));
		facebookButton.setBounds(590, 25, 150, 50);
		emailButton = new JButton("   E-MAIL", new ImageIcon("images/emailminiicon.png"));
		emailButton.setBackground(new Color(150,150,150));
		emailButton.setBounds(770, 25, 150, 50);

		btnDisplay = new JButton("DISPLAY CONTENT");
		btnDisplay.setBounds(200, 500, 200, 40);
		btnDisplay.setBackground(new Color (151,210,125));

		btnPost = new JButton("POST");
		btnPost.setBounds(600, 500, 200, 40);
		btnPost.setBackground(new Color (151,210,125));

		resultsFrame.add(twitterButton);
		resultsFrame.add(facebookButton);
		resultsFrame.add(emailButton);
		resultsFrame.add(btnDisplay);
		resultsFrame.add(btnPost);
		resultsFrame.add(refreshButton);
		resultsFrame.add(disconnectButton);

		JLabel label1 = new JLabel("RESULTS :");
		JPanel panel2 = new JPanel();
		panel2.add(label1);
		panel2.setBounds(5, 90, 80, 20);

		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setPreferredSize(new Dimension(980, 500));
		list.setFont(new Font("Serif", Font.PLAIN, 25));
		list.setBackground(new Color(240,248,255));
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(965, 350));
		JPanel panel3 = new JPanel();
		panel3.add(scroll);
		panel3.setBounds(10, 120, 965, 600);

		resultsFrame.add(panel2);
		resultsFrame.add(panel3);

		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.displayContent();
			}

		});

		facebookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.facebookValidation();	
			}	
		});

		twitterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.twitterValidation();
			}
		});

		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.setDc();
			}
		});

		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.postValidation();
			}
		});
		
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ação do botão disconnect
			}
		});

		this.emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.emailValidation();
			}

		});
	}

	public void open() {

		resultsFrame.setVisible(true);
	}

	public void setApp(App app) {
		this.app = app;
	}

	public void clearList() {
		model.removeAllElements();
	}

	public DefaultListModel<String> getModelList() {
		return this.model;
	}

	public JTextArea getTxtBody() {
		return txtBody;
	}

	public void setTxtBody(JTextArea txtBody) {
		this.txtBody = txtBody;
	}

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public App getApp() {
		return app;
	}

	public JTextArea getTxtSend() {
		return txtSend;
	}

	public void setTxtSend(JTextArea txtSend) {
		this.txtSend = txtSend;
	}

	public JFrame getResultsFrame() {
		return resultsFrame;
	}

	public void setResultsFrame(JFrame resultsFrame) {
		this.resultsFrame = resultsFrame;
	}

	public JFrame getSelectedFrame() {
		return selectedFrame;
	}

	public void setSelectedFrame(JFrame selectedFrame) {
		this.selectedFrame = selectedFrame;
	}

	public JFrame getSendFrame() {
		return sendFrame;
	}

	public void setSendFrame(JFrame sendFrame) {
		this.sendFrame = sendFrame;
	}

	public JButton getFacebookButton() {
		return facebookButton;
	}

	public void setFacebookButton(JButton facebookButton) {
		this.facebookButton = facebookButton;
	}

	public JButton getTwitterButton() {
		return twitterButton;
	}

	public void setTwitterButton(JButton twitterButton) {
		this.twitterButton = twitterButton;
	}

	public JButton getEmailButton() {
		return emailButton;
	}

	public void setEmailButton(JButton emailButton) {
		this.emailButton = emailButton;
	}

	public JButton getBtnDisplay() {
		return btnDisplay;
	}

	public void setBtnDisplay(JButton btnDisplay) {
		this.btnDisplay = btnDisplay;
	}

	public JButton getBtnPost() {
		return btnPost;
	}

	public void setBtnPost(JButton btnPost) {
		this.btnPost = btnPost;
	}

	public JButton getDisconnectButton() {
		return disconnectButton;
	}

	public void setDisconnectButton(JButton disconnectButton) {
		this.disconnectButton = disconnectButton;
	}

	
	public void addDisconnect() {
		disconnectButton.setEnabled(false);
	}
}
