package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;
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
