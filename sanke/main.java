package sanke;

import java.util.*;

import javax.swing.JFrame;


public class main {

	public static void main (String [] args) 
	{
		 
		 JFrame window = new JFrame();
	     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     window.setSize(1040,1040);
	     window.setResizable(false);
	     gamepanel panel=new gamepanel();
	     window.add(panel);
	     window.setVisible(true);
	     
	}
	
}
