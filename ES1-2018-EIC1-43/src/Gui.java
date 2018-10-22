import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

	private JFrame frame;
	private JTextArea txtBody;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JButton btnDisplay;
	private JButton btnConnectFacebook;
	private JButton btnConnectTwitter;
	private JButton btnPost;
	private JPanel btnPanelConnect;
	private JPanel btnPanelSouth;

	public Gui() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		this.frame.setVisible(true);
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane1 = new JScrollPane();
		txtBody = new JTextArea();
		txtBody.setRows(100);
		txtBody.setColumns(100);
		txtBody.setLineWrap(true);
		txtBody.setText("");
		frame.getContentPane().add(scrollPane1, BorderLayout.CENTER);
		txtBody.setFont(new Font("Serif", Font.PLAIN, 25));
		scrollPane1.setViewportView(txtBody);

		JScrollPane scrollPane2 = new JScrollPane();
		model = new DefaultListModel<>();
		list = new JList<String>(model);
		frame.getContentPane().add(scrollPane2, BorderLayout.EAST);
		list.setFont(new Font("Serif", Font.PLAIN, 25));
		scrollPane2.setViewportView(list);
		scrollPane2.setMinimumSize(new Dimension(500, 200));

		btnDisplay = new JButton("Display Content");
		btnDisplay.setPreferredSize(new Dimension(180, 40));
		btnDisplay.setFont(new Font("Serif", Font.PLAIN, 20));

		btnPost = new JButton("Post");
		btnPost.setPreferredSize(new Dimension(180, 40));
		btnPost.setFont(new Font("Serif", Font.PLAIN, 20));

		btnPanelSouth = new JPanel();
		btnPanelSouth.add(btnDisplay);
		btnPanelSouth.add(btnPost);
		frame.getContentPane().add(btnPanelSouth, BorderLayout.SOUTH);
		btnPost.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnConnectFacebook = new JButton("Connect Facebook");
		btnDisplay.setFont(new Font("Serif", Font.PLAIN, 20));

		btnConnectTwitter = new JButton("Connect Twitter");
		btnDisplay.setFont(new Font("Serif", Font.PLAIN, 20));

		btnPanelConnect = new JPanel();
		btnPanelConnect.add(btnConnectFacebook);
		btnPanelConnect.add(btnConnectTwitter);
		frame.getContentPane().add(btnPanelConnect, BorderLayout.NORTH);
		btnConnectFacebook.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnConnectTwitter.setAlignmentX(Component.RIGHT_ALIGNMENT);

		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
			}

		});

		btnConnectFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}

		});

		btnConnectTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
				
		});

		btnPost.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}

		});
	}

	

}
