package sudoko;

import java.util.*;
import javax.swing.JFrame;

public class main {

	public static void main (String [] args) 
	{
		 
		 JFrame window = new JFrame("Choose your level");
	     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     window.setSize(500,200);
	     window.setResizable(false);
	     bord panel=new bord();
	     window.add(panel);
	     window.setVisible(true);
	     
	}
	
}
