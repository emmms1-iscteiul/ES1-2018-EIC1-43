package EIC1_43.BDA;

import java.util.Date;

/**
 * Classe que trata as mensagens recebidas do facebook como objectos.
 * Por cada mensagem recebida e criada uma instância de FacebookMessage.
 * Cada FacebookMessage guarda em atributos informacao sobre si mesma como conteudo, da e hora de publicacao.
 * 
 * @author Goncalo
 *
 */

public class FacebookMessage extends Message {

	private Date date;
	private String data;
	private String hora;
	private String title;
	
	/**
	 * Construtor da classe. Instancias criadas com base num objecto do tipo Date e uma String.
	 * 
	 * @param date refere-se ao .getCreatedTime() dos objectos do tipo Post do facebook.
	 * @param content refere-se ao .getMessage() do objectos Post do facebook.
	 */
	
	public FacebookMessage(Date date, String content) {
		super(content);
		this.date = date;
		tratarDataHora();
	}
	
	public Date getDate() {
		return date;
	}

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}
	
	
	
	public void setData(String data) {
		this.data = data;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * A partir do conteudo da mensagem, este metodo retira os primeiros 30 caracteres
	 * que vao representar o titulo da mensagem quando esta aparecer como resultado
	 * na interface grafica com o utilizador.
	 * 
	 * @return devolve os primeiros 30 caracteres da string content. Caso esta não tenha 30 caracteres, alem de devolver
	 * a totalidade do conteudo, compensa o resto da string com "espacamentos" por forma a devolver sempre uma string com 30 caracteres.
	 */
	
	public String Title () {
		
		title = "";
		for (int i = 0; i < 30; i++) {
			if(i<super.getContent().length()) {
				title = title + super.getContent().charAt(i);
			} else {
				break;
			}
		}
		
		return title;
	}
	
	
	/**
	 * Metodo que a partir do objecto Date fornecido como argumento do construtor,
	 * obtem o .toString da Date e filtra a data e hora para os atributos correspondentes
	 * da presente classe.
	 * 
	 */
	
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
	
	/**
	 * 
	 * String de representacao de uma mensagem do facebook com base nos atributos da classe (Title, Data e Hora).
	 * 
	 * @return devolve a String final que representa cada Objecto FacebookMessage para este 
	 * aparecer na lista de resultados da interface grafica com o utilizador.
	 */
	
	public String ObjectRepresention () {
		
		String object =  Title() + "               " + data + "   " + hora;
		return object;
	}
}
