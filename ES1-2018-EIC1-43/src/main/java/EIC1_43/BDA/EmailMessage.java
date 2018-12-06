package EIC1_43.BDA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmailMessage extends Messages {

	private Date date;
	private String data;
	private String hora;
	private String title;
	private JButton viewButton;

	public EmailMessage(Date date, String content) {
		super(content);
		this.date = date;
		tratarDataHora();
	}

	public void addListner(ActionListener listener) {
		viewButton.addActionListener(listener);
	}

	@Override
	public Date getDate() {
		return date;
	}

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}

	private String Title() {

		title = "";
		for (int i = 0; i < 30; i++) {
			if (i < super.getContent().length()) {
				title = title + super.getContent().charAt(i);
			} else {
				break;
			}
		}

		return title;
	}

	@Override
	public void tratarDataHora() {

		String dateDate = date.toString();
		String week = "";
		String mes = "";
		String day = "";
		String year = "";
		String time = "";

		for (int i = 0; i != dateDate.length(); i++) {
			if (i < 3) {
				week = week + dateDate.charAt(i);
			}
			if (i > 3 && i < 7) {
				mes = mes + dateDate.charAt(i);
			}
			if (i > 7 && i < 10) {
				day = day + dateDate.charAt(i);
			}
			if (i > 10 && i < 16) {
				time = time + dateDate.charAt(i);
			}
			if (i > 23) {
				year = year + dateDate.charAt(i);
			}
		}

		data = week + " " + day + " " + mes + " " + year;
		hora = time;
	}

	@Override
	public String toStringTxt() {

		String content = super.getContent();
		content = content.replace("\n","").replace("\r","");
		
		title = title.replace("\n","").replace("\r","");
		
		String object = "email" + ";" + title + ";" + content + ";" + data + ";" + hora;
		return object;
	}

	public JPanel ObjectRepresentation() {

		JPanel panelMessage = new JPanel();
		panelMessage.setPreferredSize(new Dimension(930, 60));
		panelMessage.setLayout(null);

		JLabel labelImage = new JLabel(new ImageIcon("images/email.png"));
		labelImage.setBounds(5, 0, 60, 60);
		JLabel labelOrigem = new JLabel("EMAIL");
		labelOrigem.setBounds(75, 0, 100, 60);
		JLabel labelTitle = new JLabel(Title());
		labelTitle.setBounds(180, 0, 200, 60);
		JLabel labelDate = new JLabel(data);
		labelDate.setBounds(600, 0, 120, 60);
		JLabel labelHora = new JLabel(hora);
		labelHora.setBounds(750, 0, 70, 60);

		viewButton = new JButton("Ver");
		viewButton.setBounds(850, 15, 70, 30);

		panelMessage.add(labelImage);
		panelMessage.add(labelOrigem);
		panelMessage.add(labelTitle);
		panelMessage.add(labelDate);
		panelMessage.add(labelHora);
		panelMessage.add(viewButton);

		panelMessage.setBackground(new Color(250, 171, 171));

		return panelMessage;
	}

}
