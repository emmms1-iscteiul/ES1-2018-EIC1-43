package EIC1_43.BDA;

/**
 * Classe abstracta que representa as tres subclasses FacebookMessage, TwitterMessage e EmailMessage,
 * que estendem esta classe e herdam/sobrepoem os seu atributos e metodos.
 * 
 * Criar uma classe generica para os tres tipos de mensagens existentes, proporcionara trabalhar com listas que
 * contem todos os tipos de mensagens e não com uma por cada tipo.
 * @author Goncalo
 *
 */

public abstract class Messages {
	
	private String content;
	
	/**
	 * Construtor da classe Message. 
	 * 
	 * @param content argumento que recebe uma string relativa ao conteudo da mensagem.
	 */
	
	public Messages (String content) {
		
		this.setContent(content);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Metodo a ser sobreposto por cada uma das subclasses de mensagens. 
	 */
	
	public abstract void tratarDataHora ();
	
	public abstract String toStringtxt();
		
}
