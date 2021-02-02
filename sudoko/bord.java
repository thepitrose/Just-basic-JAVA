package sudoko;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.border.MatteBorder;



public class bord extends JPanel{
	
	 private JPanel upPanel = new JPanel();								//the bord Panel 
	 private final int M = 9;
	 private  int gameBoard[][] = new int [M][M];						//user sudoku array
	 private  JTextField  jBoard[][]= new JTextField  [M][M];			//squares for the numbers 
	 private JButton lv1,lv2,lv3,end;									//Buttons
	 private int[][] mattest = new int[M][M];							//the sudoku array
	 private makebord bob;												//bob is the caller of the bord  method
	 private JFrame bordFrame = new JFrame("Im a Sodoko Window!");		// bord Frame


	 
//=================================================================================  main method
	 
	public bord ()
	{
		 bob = new makebord(mattest);								// make the bord
		
		 for (int i=0 ; i<M ; i++)
		 {
			 for (int j=0 ; j<M ; j++)
			 {
				 gameBoard[i][j]=mattest[i][j];						// copy the date to the user array
			 }
		 }
		 	
	        this.setLayout(new BorderLayout());							//Create the Frame
	        bordFrame.setLayout(new BorderLayout());					//Create the Frame
	        bordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Create the Frame
	        bordFrame.setSize(1040,1040);								//Create the Frame
	        bordFrame.setResizable(false);								//Create the Frame
	        

	        
	        
 //--------------------------------------------------- creating the Panels



	    upPanel = new JPanel(new GridLayout(M, M));
	       
	    Listener lis = new Listener();
	       
	        for (int i = 0; i < M; i++) 
	        {
	           for (int j = 0; j < M; j++) 
	           {	
//----------------------------------------------------------------------------------------------   	   Create the squares for the numbers 
	        	   
	        	   
	        	   if (mattest[i][j]!=0)																// if the square is not "empty"
	        	   {
	        	   String temp = Integer.toString(mattest[i][j]);										// get the num for the squares
	        	   temp = "                  " + temp;													// spice to make it more beautiful
	        	   jBoard[i][j] = new JTextField (temp);												// set the num in the squares			
	        	   jBoard[i][j].setPreferredSize(new Dimension  (100,100));								// Set size
	        	   upPanel.add(jBoard[i][j]);															// add to the panel
	        	   jBoard[i][j].setEditable(false);														// disable editable
	        	   jBoard[i][j].setVisible(false);														// set Visible
	        	   jBoard[i][j].addActionListener(lis);													// add Listener
	        	   }
	        	   
	        	   jBoard[i][j].setBorder(new MatteBorder(1, 1, 1, 1, Color.black));					// Create boundaries to make it beautiful
	        	   
	        	   if(j==3 || j==6)
	        	   {
	        		   jBoard[i][j].setBorder(new MatteBorder(1, 3, 1, 1, Color.black));				// Create boundaries to make it beautiful
	        		   
	        		  
	        	   }
	        	   
	        	   if(i==3 || i==6)
	        	   {
	        		   jBoard[i][j].setBorder(new MatteBorder(3, 1, 1, 1, Color.black));				// Create boundaries to make it beautiful
	        	   }
	        	   
	        	   
	           }
	           upPanel.setBorder(BorderFactory.createLineBorder(Color.red));							// Create boundaries to make it beautiful
	           
	           
	           
	        }
		 
	        jBoard[3][3].setBorder(new MatteBorder(3, 3, 1, 1, Color.black));							// Create boundaries to make it beautiful
	        jBoard[3][6].setBorder(new MatteBorder(3, 3, 1, 1, Color.black));							// Create boundaries to make it beautiful
	        jBoard[6][3].setBorder(new MatteBorder(3, 3, 1, 1, Color.black));							// Create boundaries to make it beautiful
	        jBoard[6][6].setBorder(new MatteBorder(3, 3, 1, 1, Color.black));							// Create boundaries to make it beautiful
	        
	        
	        lv1 = new JButton("level 1");		//Create level Button
	        lv2 = new JButton("level 2");		//Create level Button
	        lv3 = new JButton("level 3");		//Create level Button
	        end = new JButton("end game");		//Create End game Button
	        
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new GridLayout(1, 1));
	        
	        buttonPanel.add(lv1);			//add the Button
	        buttonPanel.add(lv2);			//add the Button
	        buttonPanel.add(lv3);			//add the Button
	        bordFrame.add(end);				//add the Button


	        lv1.addActionListener(lis);		// add Listener to the Button
	        lv2.addActionListener(lis);		// add Listener to the Button
	        lv3.addActionListener(lis);		// add Listener to the Button
	        end.addActionListener(lis);		// add Listener to the Button
	        
	        
	        bordFrame.add(upPanel,BorderLayout.NORTH);		// add to the game panel bord
	        bordFrame.add(end,BorderLayout.CENTER);			// add to the game panel bord
	        this.add(buttonPanel,BorderLayout.CENTER);		// add to the main panel bord
	}


	
//================================================================================= Listeners
    

	
    private class Listener implements ActionListener 
    { public void actionPerformed(ActionEvent e)
    	{ 
    	
    	if(e.getSource()==lv1)
    	{
    		bordFrame.setVisible(true);			//Self-explained
    		newgame(mattest , 1);
    	}
    	
    	if(e.getSource()==lv2)					//Self-explained
    	{
    		bordFrame.setVisible(true);
    		newgame(mattest , 2);
    	}
    	
    	if(e.getSource()==lv3)					//Self-explained
    	{
    		bordFrame.setVisible(true);
    		newgame(mattest , 3);
    	}
    	
    	
       	for (int i = 0; i < M; i++) 
   	        {
   	           for (int j = 0; j < M; j++) 
   	           {
   	        	String str = jBoard[i][j].getText();	// get the num the user set in the bord to the user array
   	        	
   	        	setnum(str,i,j);
   	           }
   	        }
       	  
       	  
       	if (e.getSource()==end)						// if the user choose to end the game
    	{
    		  int cc = 0;


    	        for (int i = 0; i < M; i++)
    	        {
    	            for (int j = 0; j < M; j++)
    	            {
    	                if (gameBoard [i][j]!=mattest[i][j])	//Compares your user board to the correct board
    	                {
    	                    cc--;   							//If there is a mistake
    	                }

    	            } 

    	        }

    	        if (cc!=0)										//If there is a mistake show a idiot message
    	        {

    	        	Random rnd = ThreadLocalRandom.current();
                    int Messagenumber = rnd.nextInt(5 - 1)+1;

                    if (Messagenumber==1)
                    { 	
    	               JOptionPane.showMessageDialog(null, "user is idiot " ,"End Game",JOptionPane.INFORMATION_MESSAGE); //show message 
                    }
                    
                    if (Messagenumber==2)
                    { 	
    	             JOptionPane.showMessageDialog(null, " Please stop embarrassing yourselft " ,"End Game",JOptionPane.INFORMATION_MESSAGE); //show message 
                    }
                    
                    if (Messagenumber==3)
                    { 	
    	             JOptionPane.showMessageDialog(null, " Does sudoku make you smarter? Not in your case " ,"End Game",JOptionPane.INFORMATION_MESSAGE); //show message 
                    }
                    
                    if (Messagenumber==4)
                    { 	
    	             JOptionPane.showMessageDialog(null, " Go, please go " ,"End Game",JOptionPane.INFORMATION_MESSAGE); //show message 
                    }
                    
                    if (Messagenumber==5)
                    { 	
    	             JOptionPane.showMessageDialog(null, " Wow .. surely your mom is really proud in her little disappointment " ,"End Game",JOptionPane.INFORMATION_MESSAGE); //show message 
                    }
                    
    	        }
    	        if (cc==0)		//If there is no mistake show the good message
    	        {
    	             JOptionPane.showMessageDialog(null, " user is ok not a total idiot " ,"End Game",JOptionPane.INFORMATION_MESSAGE); //show message 
    	        }
    	        
    	        bordFrame.dispatchEvent(new WindowEvent(bordFrame, WindowEvent.WINDOW_CLOSING));		// close the windows when end game
    	        
    		}
      		
    	} 
      	
    }

    
//================================================================================= New game
    
    
    private void newgame(int[][] mattest, int lv)
    {
    	int tt = 0;

    	if (lv==1)											// level 1
    	{
            while (tt<30)
            {
                Random rnd = ThreadLocalRandom.current();
                int index = rnd.nextInt(8 + 1);
                int indey = rnd.nextInt(8 + 1);
                if(gameBoard[index][indey]!=0)
                {
                	gameBoard[index][indey]=0;				// Create 30 blank cells
                    tt++;
                }
            }
    		
    		
    	  for (int i = 0; i < M; i++) 
	        {
	           for (int j = 0; j < M; j++) 
	           {	
	        	   if (gameBoard[i][j]==0)
	        	   {
	        		   jBoard[i][j].setText("                  ");		// set the blank cells squares as editable 
	        		   jBoard[i][j].setEditable(true);					// set the blank cells squares as editable 
	        		   
	        		   
	        	   }
    	  
	        	   jBoard[i][j].setVisible(true);
	           }

	        }
    	}
    	
    	if (lv==2)
    	{
    		
            while (tt<40)										// Create 40 blank cells
            {
                Random rnd = ThreadLocalRandom.current();
                int index = rnd.nextInt(8 + 1);
                int indey = rnd.nextInt(8 + 1);
                if(gameBoard[index][indey]!=0)
                {
                	gameBoard[index][indey]=0;
                    tt++;
                }
            }
    		
    		
    	  for (int i = 0; i < M; i++)
	        {
	           for (int j = 0; j < M; j++) 
	           {	
	        	   if (gameBoard[i][j]==0)
	        	   {
	        		   jBoard[i][j].setText("                  ");		// set the blank cells squares as editable 
	        		   jBoard[i][j].setEditable(true);					// set the blank cells squares as editable 
	        		   
	        		   
	        	   }
    	  
	        	   jBoard[i][j].setVisible(true);
	           }

	        }
    	}
    	
    	if (lv==3)
    	{
    		
            while (tt<50)									// Create 30 blank cells
            {
                Random rnd = ThreadLocalRandom.current();
                int index = rnd.nextInt(8 + 1);
                int indey = rnd.nextInt(8 + 1);
                if(gameBoard[index][indey]!=0)
                {
                	gameBoard[index][indey]=0;
                    tt++;
                }
            }
    		
    		
    	  for (int i = 0; i < M; i++) 
	        {
	           for (int j = 0; j < M; j++) 
	           {	
	        	   if (gameBoard[i][j]==0)
	        	   {
	        		   jBoard[i][j].setText("                  ");		// set the blank cells squares as editable 
	        		   jBoard[i][j].setEditable(true);					// set the blank cells squares as editable 
	        		   
	        		   
	        	   }
    	  
	        	   jBoard[i][j].setVisible(true);
	           }

	        }
    	}
    }
    
    
//=================================================================================    
    
    
    private void setnum (String str, int x , int y)		// When the user enters a value into the square,	enter it to the user array
    {
    	int res = 0;
    	char sp = ' ';
        for (int i = 0; i < str.length(); i++) {
        	if(str.charAt(i)!=(sp))
        	{
            res = res * 10 + str.charAt(i) - '0';
        	}
        }
        gameBoard[x][y]=res;


    }
    
   
}
