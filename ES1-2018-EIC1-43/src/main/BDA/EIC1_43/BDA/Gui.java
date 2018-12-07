package EIC1_43.BDA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JFrame filtrosFrame;
	private JTextArea txtBody;
	private JTextArea txtSend;
	private JButton disconnectButton;
	private JButton facebookButton;
	private JButton twitterButton;
	private JButton emailButton;
	private JButton offlineButton;
	private JButton filtros;
	private JButton btnPost;
	private JScrollPane scroll;
	private JPanel resultsArea;
	


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
	 * Metodo que desenha a janela de filtros onde posteriormente o utilizador podera selecionar/formar
	 * uma data cujo botão Aplicar Filtro irá utilizar para filtrar apenas as mensagens correspondentes 
	 * a data pretendida
	 * 
	 */
	
	public void filtrosFrameContent() {

		filtrosFrame = new JFrame("FILTRAR  MENSAGENS");
		filtrosFrame.setMinimumSize(new Dimension(400, 250));
		filtrosFrame.setLayout(null);
		filtrosFrame.setResizable(false);
		filtrosFrame.setLocationRelativeTo(null);
		filtrosFrame.setIconImage(new ImageIcon("images/ba.png").getImage());;

		
		JLabel label = new JLabel("Selecione uma data para filtrar as mensagens correspondentes.");
		label.setBounds(10, 10, 480, 30);
		
		JLabel label1 = new JLabel("Data Incial : ");
		label1.setBounds(10, 40, 100, 30);
		
		JLabel label11 = new JLabel("Dia :");
		JLabel label12 = new JLabel("Mês :");
		JLabel label13 = new JLabel("Ano :");
		label11.setBounds(10, 80, 30, 30);
		label12.setBounds(110, 80, 30, 30);
		label13.setBounds(220, 80, 30, 30);
		
		JLabel label14 = new JLabel("Hora :");
		JLabel label15 = new JLabel("Minutos :");
		label14.setBounds(10, 80, 30, 30);
		label15.setBounds(110, 80, 30, 30);
		
		JComboBox<String> boxDay = new JComboBox<String>();
		boxDay.setBounds(50, 80, 50, 30);
		
		boxDay.addItem("01"); boxDay.addItem("02"); boxDay.addItem("03"); boxDay.addItem("04");boxDay.addItem("05"); 
		boxDay.addItem("06"); boxDay.addItem("07"); boxDay.addItem("08"); boxDay.addItem("09"); boxDay.addItem("10"); 
		boxDay.addItem("11"); boxDay.addItem("12"); boxDay.addItem("13"); boxDay.addItem("14"); boxDay.addItem("15"); 
		boxDay.addItem("16"); boxDay.addItem("17"); boxDay.addItem("18"); boxDay.addItem("19"); boxDay.addItem("20"); 
		boxDay.addItem("21"); boxDay.addItem("22"); boxDay.addItem("23"); boxDay.addItem("24"); boxDay.addItem("25"); 
		boxDay.addItem("26"); boxDay.addItem("27"); boxDay.addItem("28"); boxDay.addItem("29"); boxDay.addItem("30"); 
		boxDay.addItem("31");
		
		JComboBox<String> boxMes = new JComboBox<String>();
		boxMes.setBounds(150, 80, 60, 30);
		
		boxMes.addItem("Jan"); boxMes.addItem("Feb"); boxMes.addItem("Mar"); boxMes.addItem("Apr");boxMes.addItem("May"); 
		boxMes.addItem("June"); boxMes.addItem("July"); boxMes.addItem("Aug"); boxMes.addItem("Sept"); boxMes.addItem("Oct"); 
		boxMes.addItem("Nov"); boxMes.addItem("Dec");
		
		JComboBox<String> boxAno = new JComboBox<String>();
		boxAno.setBounds(260, 80, 70, 30);
		
		boxAno.addItem("2018"); boxAno.addItem("2019");
		

		
		
		JButton aplicar = new JButton("APLICAR  FILTRO");
		aplicar.setBounds(120, 160, 200, 40);
		
		aplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String data = boxDay.getSelectedItem() + " " + boxMes.getSelectedItem() + " " + boxAno.getSelectedItem();
				
				app.readMessagesFiltradas(data);
				
				filtrosFrame.dispose();
			}

		});
			
		filtrosFrame.add(boxDay); filtrosFrame.add(label13);
		filtrosFrame.add(boxMes); filtrosFrame.add(label12);
		filtrosFrame.add(label); filtrosFrame.add(label11);
		filtrosFrame.add(boxAno); filtrosFrame.add(label1);
		
		filtrosFrame.add(aplicar);

		filtrosFrame.setVisible(true);
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
				app.sendValidation(null);
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
		disconnectButton.setBounds(250, 25, 140, 50);
		disconnectButton.setBackground(new Color(237,28,36));
		
		offlineButton = new JButton("Mensagens Offline");
		offlineButton.setBounds(50, 25, 140, 50);
		offlineButton.setBackground(new Color(0,0,0));
		offlineButton.setForeground(Color.WHITE);
		
		twitterButton = new JButton("   TWITTER", new ImageIcon("images/twitterminiicon.png"));
		twitterButton.setBackground(new Color(150,150,150));
		twitterButton.setBounds(460, 25, 150, 50);
		facebookButton = new JButton("   FACEBOOK", new ImageIcon("images/faceminiicon.png"));
		facebookButton.setBackground(new Color(150,150,150));
		facebookButton.setBounds(640, 25, 150, 50);
		emailButton = new JButton("   E-MAIL", new ImageIcon("images/emailminiicon.png"));
		emailButton.setBackground(new Color(150,150,150));
		emailButton.setBounds(820, 25, 150, 50);

		filtros = new JButton("Filtros");
		filtros.setBounds(200, 500, 200, 40);
		filtros.setBackground(new Color (151,210,125));

		btnPost = new JButton("POST");
		btnPost.setBounds(600, 500, 200, 40);
		btnPost.setBackground(new Color (151,210,125));

		resultsFrame.add(twitterButton);
		resultsFrame.add(facebookButton);
		resultsFrame.add(emailButton);
		resultsFrame.add(filtros);
		resultsFrame.add(btnPost);
		resultsFrame.add(disconnectButton);
		resultsFrame.add(offlineButton);

		JLabel label1 = new JLabel("RESULTS :");
		JPanel panel2 = new JPanel();
		panel2.add(label1);
		panel2.setBounds(5, 90, 80, 20);
                                      
		resultsArea = new JPanel(); 
		resultsArea.setPreferredSize(new Dimension(930, 5000));
		scroll = new JScrollPane(resultsArea); 
		scroll.setPreferredSize(new Dimension(965, 350));
		
		
		JPanel panel3 = new JPanel();
		panel3.add(scroll);
		panel3.setBounds(10, 120, 965, 600);

		resultsFrame.add(panel2);
		resultsFrame.add(panel3);

		                                            
		facebookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.facebookValidation(null);	
			}	
		});

		twitterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.twitterValidation(null);
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
		
		offlineButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				app.readMessages();
			}
		});

		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.emailValidation(null);
			}

		});
		
		filtros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				filtrosFrameContent();
				
			}

		});
	}
	/**
	 * Torna visivel a janela principal da aplicacao 
	 */
	
	public void open() {

		resultsFrame.setVisible(true);
	}
	
	/**
	 * Torna a app visível para a classe gui
	 * 
	 * @param app atribui uma classe app à GUI 
	 */

	public void setApp(App app) {
		this.app = app;
	}
	
	/**
	 * Adiciona ao painel resultsArea uma nova mensagem para ser visível para o utilizador
	 * 
	 * @param mensage  mensagem nova ser adicionada
	 */
	
	public void addMessage (JPanel mensage) {
		resultsArea.add(mensage);
	}
	
	/**
	 * Limpa todas as mensagens no painel resultsArea
	 */

	public void clearList() {
		resultsArea.removeAll();
	}
	
	/**
	 * 
	 * @return devolve o painel resultsArea
	 */
	
	public JPanel getResultsArea() {
		return resultsArea;
	}
	
	/**
	 * 
	 * @return devolve o conteudo (string) contida na JTextArea txtBody 
	 */
	
	public JTextArea getTxtBody() {
		return txtBody;
	}

	/**
	 * 
	 * @return obtem a mensagem inserida pelo utilizador para enviar a uma das aplicacoes
	 */
	
	
	public JTextArea getTxtSend() {
		return txtSend;
	}

	/**
	 * 
	 * @return devolve a JFrame (resultsFrame) que permite o utilizador visualizar o
	 * conteudo completo de uma mensagem especifica. 
	 */
	
	public JFrame getResultsFrame() {
		return resultsFrame;
	}
	
	/**
	 * Torna possivel a class app aceder e controlar o botao disconnectButton
	 * 
	 * @return devolve o botao disconnectedButton
	 */
	
	public JButton getDisconnectButton() {
		return disconnectButton;
	}

}
