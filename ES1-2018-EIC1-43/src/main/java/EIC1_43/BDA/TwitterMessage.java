package EIC1_43.BDA;

import java.util.Date;

/**
 * Classe que trata as mensagens recebidas do twitter como objectos.
 * Por cada mensagem recebida é criada uma instancia de TwitterMessage.
 * Cada TwitterMessage guarda em atributos informacao sobre si mesma como conteudo, data e hora de publicacao.
 * 
 * @author Goncalo
 *
 */

public class TwitterMessage extends Message {
	
	private Date date;
	private String data;
	private String hora;
	
/**
 * Contrutor da classe. Instancias criadas com base num objecto do tipo Date e uma String.
 * 
 * @param date date refere-se ao .getCreatedAt() dos objectos do tipo Status do Twitter.
 * @param content refere-se ao .getText() do objectos Status do Twitter.
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
	
	/**
	 * A partir do conteudo da mensagem, este método retira os primeiros 30 caracteres
	 * que vao representar o titulo da mensagem quando esta aparecer como resultado
	 * na interface gráfica com o utilizador.
	 * 
	 * @return devolve os primeiros 30 caracteres da string content. Caso esta não tenha 30 caracteres, alem de devolver
	 * a totalidade do conteudo, compensa o resto da string com "espaçamentos" por forma a devolver sempre uma string com 30 caracteres.
	 */
	
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
	 * String de representaçao de uma mensagem do twitter com base nos atributos da classe (Title, Data e Hora).
	 * 
	 * @return devolve a String final que representa cada Objecto TwitterMessage para este 
	 * aparecer na lista de resultados da interface grafica com o utilizador.
	 */
	
	public String ObjectRepresention () {
		
		String object =  Title() + "               " + data + "   " + hora;
		return object;
	}

}
