package EIC1_43.BDA;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.Test;

public class FbMsgTest {

	public FbMsgTest() {
		
	}
	
	@Test
	public void builderTest() {
		FacebookMessage fbmsg = new FacebookMessage(new Date(12,12,12),"ola");
		assertTrue(fbmsg.getDate() != null);
		assertTrue(fbmsg.getContent().equals("ola"));
	}
	
	@Test
	public void setAndGetTest() {
		FacebookMessage fbmsg = new FacebookMessage(new Date(1991,12,12),"ola");
		
		fbmsg.setContent("Adeus");
		assertTrue(fbmsg.getContent().equals("Adeus"));
		
		fbmsg.setData(null);
		assertTrue(fbmsg.getData() == null);;
		
		fbmsg.setHora(null);
		assertTrue(fbmsg.getHora() == null);
	}
	
	@Test
	public void titleTest() {
		FacebookMessage fbmsg = new FacebookMessage(new Date(12,12,1),"ola");
		fbmsg.Title();
		
		assertTrue(fbmsg.getTitle().equals("ola"));
	}
	
	@Test
	public void tratarDataHoraTest() {
		FacebookMessage fbmsg = new FacebookMessage(new Date(1994,12,12),"ola");
		fbmsg.tratarDataHora();
	}
	
	@Test
	public void objectRepTest() {
		FacebookMessage fbmsg = new FacebookMessage(new Date(1995,29,20),"ola");
		fbmsg.ObjectRepresentation();
		
	}
}
