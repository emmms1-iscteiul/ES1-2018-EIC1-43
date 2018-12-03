package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import org.junit.Test;


public class GuiTest {

	private Gui gui;
	
	public GuiTest() {}
	
	@Test
	public void construtorTest() {
		
		gui  = new Gui();
	}
	
	@Test
	public void secundaryFramesTest() {
		gui = new Gui();
		gui.postFrameContent();
		gui.resultsFrameContent();
		
	}
	
	@Test
	public void setAndGetersTest() {
		gui = new Gui();
		Facebook facebook = new Facebook();
		Twitter twitter = new Twitter();
		App app = new App(gui, facebook, twitter);
		JFrame frame = new JFrame();
		JTextArea textArea = new JTextArea();
		JList<String> jList = new JList<String>();
		
		gui.setSelectedFrame(frame);
		assertTrue(gui.getSelectedFrame().equals(frame));
		
		gui.setResultsFrame(frame);
		assertTrue(gui.getResultsFrame().equals(frame));
		
		gui.setSendFrame(frame);
		assertTrue(gui.getSendFrame().equals(frame));
		
		gui.setTxtBody(textArea);
		assertTrue(gui.getTxtBody().equals(textArea));
		
		gui.setList(jList);
		assertTrue(gui.getList().equals(jList));
		
		gui.setApp(app);
		assertTrue(gui.getApp().equals(app));
		
		gui.setTxtSend(textArea);
		assertTrue(gui.getTxtSend().equals(textArea));
		
	}
	
	@Test
	public void otherTests() {
		
		gui = new Gui();
		gui.clearList();
		assertTrue(gui.getModelList().size() == 0);
		
	}
	
	@Test
	public void setAndGetButtonsTest() {
		
		gui = new Gui ();
		assertTrue(gui.getDisconnectButton() != null);
	}
	

}
