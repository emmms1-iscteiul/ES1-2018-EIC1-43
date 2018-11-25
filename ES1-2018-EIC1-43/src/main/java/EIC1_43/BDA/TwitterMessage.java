package EIC1_43.BDA;

import java.util.Date;

/**
 * Classe que trata as mensagens recebidas do twitter como objectos.
 * Por cada mensagem recebida é cria da uma instância de TwitterMessage.
 * Cada TwitterMessage guarda em atributos informação sobre si mesma como conteúdo, data e hora de publicação.
 * 
 * @author Gonçalo
 *
 */

public class TwitterMessage extends Message {
	
	private Date date;
	private String data;
	private String hora;
	
/**
 * 	
 * @param date
 * @param content
 */
	public TwitterMessage(Date date, String content) {
		super(content);
		this.date = date;
		tratarDataHora();
	}

	public Date getDate () {
		return date;
	}
	
	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}
	
	private String Title () {
		
		String title = "";
		for (int i = 0; i < 30; i++) {
			if(i < super.getContent().length()) {
				title = title + super.getContent().charAt(i);
			} else {
				title = title + " ";
			}
		}
		
		return title;
	}
	
	@Override
	public void tratarDataHora() {
		
		String dateDate = date.toString();
		String week = ""; String mes = ""; String day = ""; String year = "";
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
	
	public String ObjectRepresention () {
		
		String object =  Title() + "               " + data + "   " + hora;
		return object;
	}

}
