package EIC1_43.BDA;

public abstract class Message {
	
	private String content;
	
	public Message (String content) {
		
		this.setContent(content);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public abstract void tratarDataHora ();
		
}
