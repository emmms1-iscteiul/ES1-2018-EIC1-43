package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
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
		
		assertTrue(gui.getResultsFrame().equals(null));
		
		assertTrue(gui.getTxtBody().equals(null));
		
		assertTrue(gui.getTxtSend().equals(null));
		
	}
	
	@Test
	public void otherTests() {
		
		gui = new Gui();
		gui.clearList();
		
	}
	
	@Test
	public void setAndGetButtonsTest() {
		
		gui = new Gui ();
		assertTrue(gui.getDisconnectButton() != null);
	}
	

}
