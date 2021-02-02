package sudoko;

import java.util.*;


public class makebord {
	
	private int[][]bord = new int[9][9];
	
	public makebord(int[][]bord)
	{
        newa(bord,9);     
        
        
        
//------------------------------------------------- Handles bug, and thus enhances the efficiency    
        if(bord[8][8]==0)						
        {
            for(int i=1; i<10;i++)
            {
            	if(bord[8][0]!=i && bord[8][1]!=i && bord[8][2]!=i && bord[8][3]!=i && bord[8][4]!=i && bord[8][5]!=i && bord[8][6]!=i && bord[8][7]!=i)
            	{
            		bord[8][8]=i;
            	}
            }      
        }
 //----------------------------------------------------
        
	}
	
	
	public void newa(int[][] bord, int xx)
    {
        List<Integer> solutionArray = new ArrayList<>();
       
        for (int i = 0; i < 9; i++)
        {
            solutionArray.add(i+1);            // array of num from 1 to 9 
        } 

        Collections.shuffle(solutionArray);	 // shuffle the array
        
        for (int i = 0; i < 9; i++)				// set the array as the top line of the bord
        {
            int xxx = solutionArray.get(i);		// set the array as the top line of the bord
            bord[0][i]=xxx;						// set the array as the top line of the bord
        } 

        
          gettopl(bord);						//set the top left of the bord
          gettopm(bord);						//set the top middle of the bord
          gettopr(bord);						//set the top right of the bord

        for (int i=3; i<xx; i++)
        {
            for (int j=0; j<9; j++)
            {

                if(i%3==0)
                {
                    fillt(bord,j,i,i,j,i);   //set line 4 and 7
                }

                if(i%3==1)
                {
                    fillm(bord,j,i,i,j,i);
                }

                if(i%3==2) 
                {
                    filld(bord,j,i,i,j,i);
                }
            }
        }
        
        

    }
	
	
	
	

//==========================================================================================	top square's
	
//-------------------------------------------------left
	
	public void gettopl(int[][] ar)                      // the remaining 6 num in the top left square have to be from the top row of the other to square
    {
        ArrayList<Integer> tamp = new ArrayList<>();
        
        for (int i =0 ;i< 6; i++)
        {
            tamp.add(ar[0][i+3]);  						//get the num
        }

        Collections.shuffle(tamp);						//mix the num

        for (int i = 0; i < 3; i++)
        {
            int xxx = tamp.get(i);						//set the num in the middle row of the top left square
            ar[1][i]=xxx;

        } 

        for (int i = 0; i < 3; i++)
        {
            int xxx = tamp.get(i+3);             	//set the remaining num in the bottom row of the top left square
            ar[2][i]=xxx;

        } 

    }
	
	
//-------------------------------------------------middle

	public void gettopm(int[][] ar)
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        
        //-------------------- fill middle row
        
        for (int i =1 ;i< 10; i++)
        {
            if(ar[1][0]!=i && ar[1][1]!=i && ar[1][2]!=i && ar[0][3]!=i && ar[0][4]!=i && ar[0][5]!=i) //get all the num that not in the left square middle row 
             tamp.add(i);																		       // and not in the middle square top row
        }

        Collections.shuffle(tamp);		//mix the num
        
        int j=0;
        int t=0;

        while (t<3)
        {
            if(tamp.get(j)==ar[2][0] || tamp.get(j)==ar[2][1] || tamp.get(j)==ar[2][2] || tamp.get(j)==ar[0][0] || tamp.get(j)==ar[0][1] || tamp.get(j)==ar[0][2])
            {

                int xxx = tamp.get(j);			// the num in the middle row have to be one of the num in the left square top and bottom row
                ar[1][t+3]=xxx;					// set the num or remove it
                tamp.remove(0);
                t++;

            }
            else
            {
                tamp.remove(0);
            }
        }

        tamp.clear(); 

        //-------------------- fill bottom row
        
        for (int i =1 ;i< 10; i++)					//the num of the bottom row cant be equal  the top and middle row num
        {
            if(ar[0][3]!=i && ar[0][4]!=i && ar[0][5]!=i && ar[1][3]!=i && ar[1][4]!=i && ar[1][5]!=i)
            tamp.add(i);
        }

        for (int i = 0; i < 3; i++)			// set the num
        {
            int xxx = tamp.get(i);
            ar[2][i+3]=xxx;

        } 

        for (int i = 0; i < 3; i++)			// if one of the num equal to a num form the top left square bottom row replace it with the num above him
        {
            if (ar[2][i+3]==ar[2][0] || ar[2][i+3]==ar[2][1] || ar[2][i+3]==ar[2][2])
            {
                int z=ar[2][i+3];
                ar[2][i+3]=ar[1][i+3];
                ar[1][i+3]=z;
            }

        } 

    }
	

	//-------------------------------------------------right
	public void gettopr (int[][] ar) 
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        ArrayList<Integer> tamp2 = new ArrayList<>();
        
        for (int i =1 ;i< 10; i++)
        {

            if(i!=ar[1][0] && i!=ar[1][1] && i!=ar[1][2] && i!=ar[1][3] && i!=ar[1][4] && i!=ar[1][5] )	//get all the num that are not equal to the num in the middle row of the left and middle square
            {
                tamp.add(i);
            }

            if(i!=ar[2][0] && i!=ar[2][1] && i!=ar[2][2] && i!=ar[2][3] && i!=ar[2][4] && i!=ar[2][5] )	//get all the num that are not equal to the num in the bottom row of the left and middle square
            {
                tamp2.add(i);
            }

        }

        Collections.shuffle(tamp);		//mix the num
        Collections.shuffle(tamp2);		//mix the num
        
        for (int i = 0; i < 3; i++)
        {
            int xxx = tamp.get(i);		// set right square middle row
            ar[1][i+6]=xxx;

            int yyy = tamp2.get(i);		// set right square bottom row
            ar[2][i+6]=yyy;

        } 

    }

//==========================================================================================	
	
//------------------------------------------------- row 4 and 7
	
	
	public void fillt(int[][] ar,int x , int xx,int y ,int yy, int t)
    {

        ArrayList<Integer> tamp = new ArrayList<>();	// array of improper num
        ArrayList<Integer> tamp2 = new ArrayList<>();	// array of num from 1 to 9

        for (int i =y-1 ;i>=0; i--)
        {  

            tamp.add(ar[i][yy]);		// add all the num from the top col

        }

        for (int i =x-1 ;i>=0; i--)
        {  
            tamp.add(ar[xx][i]);		// all the num for is left
        }

        for (int i =1 ;i< 10; i++)
        {
            tamp2.add(i);			// add the num from 1 to 9

        }

        for(int i=0; i<tamp.size();i++)
        {
            tamp2.remove(tamp.get(i));		// remove the improper num
        }

        Collections.shuffle(tamp2);		// mix the num

        if(tamp2.size()!=0)
        {
            ar[xx][yy]=tamp2.get(0);	// if the num is legal add him to the bord

        }
        else
        {
            for (int i=0; i<9; i++)
            {
                fillt(ar,i,t,t,i,t);  // if not try agian
            }

        }

    }
	
//------------------------------------------------- row 5 and 8

	public void fillm(int[][] ar,int x , int xx,int y ,int yy,int t)
    {

        ArrayList<Integer> tamp = new ArrayList<>();	 // array of improper num
        ArrayList<Integer> tamp2 = new ArrayList<>();	// array of num from 1 to 9
        

        for (int i =y-1 ;i>=0; i--)
        {  
            tamp.add(ar[i][yy]);							// add all the num from the top col
        }

        for (int i =x-1 ;i>=0; i--)
        {  
            tamp.add(ar[xx][i]);						// add all the num for is left
        }

        if(x%3==1)									// if is num is in a middle call - add all the num from the top row
        {
            tamp.add(ar[y-1][x-1]);				// top left
            tamp.add(ar[y-1][x]);				// above
            tamp.add(ar[y-1][x+1]);				// top right
 
        }

        if(x%3==2)										// if is num is in a right call - add all the num from the top row
        {	
            tamp.add(ar[y-1][x-2]);							// top right
            tamp.add(ar[y-1][x-1]);							//  top middle
            tamp.add(ar[y-1][x]);							// above

        }

        if(x%3==0)						// if is num is in a left call - add all the num from the top row
        {
            tamp.add(ar[y-1][x]);				// above
            tamp.add(ar[y-1][x+1]);				//  top middle
            tamp.add(ar[y-1][x+2]);				// top right
        }

        for (int i =1 ;i< 10; i++)
        {
            tamp2.add(i);					// array of num from 1 to 9
        }

        for(int i=0; i<tamp.size();i++)
        {
            tamp2.remove(tamp.get(i));		//If it already exists, remove it
        }

        Collections.shuffle(tamp2);		// mix the num
        
        if(tamp2.size()!=0)
        {
            ar[xx][yy]=tamp2.get(0);	// set the num in the array
        }
        else
        {
            newa(ar,xx);				// if not try agian
            for (int i=0; i<9; i++)
            {
                fillm(ar,i,t,t,i,t); 	// if not try agian 
            }
        }
        


    }
	
//------------------------------------------------- row 6 and 9

	public void filld(int[][] ar,int x , int xx,int y ,int yy, int t)
    {

        ArrayList<Integer> tamp = new ArrayList<>();	 // array of improper num
        ArrayList<Integer> tamp2 = new ArrayList<>();	// array of num from 1 to 9


        for (int i =y-1 ;i>=0; i--)
        {  
            tamp.add(ar[i][yy]);		// add all the num from the top col
        }

        for (int i =x-1 ;i>=0; i--)
        {  
            tamp.add(ar[xx][i]);		// add all the num for is left
        }

        if (x>2 && x<8)
        {
            if(x%3==1)						// if is num is in a middle call - add all the num from the top row
            {								// add all the num from above
                tamp.add(ar[y-1][x-1]);		//  top left
                tamp.add(ar[y-1][x]);		//  above
                tamp.add(ar[y-1][x+1]);		//  top right

                tamp.add(ar[y-2][x-1]);		// top top left
                tamp.add(ar[y-2][x]);		// top above
                tamp.add(ar[y-2][x+1]);		// top top right
            }

            if(x%3==2)							// if is num is in a right  call - add all the num from the top row
            {									// add all the num from above
                tamp.add(ar[y-1][x]);			// top left
                tamp.add(ar[y-1][x+1]);			// top above
                tamp.add(ar[y-1][x+2]);			// top right

                tamp.add(ar[y-2][x]);			// top top left
                tamp.add(ar[y-2][x+1]);			// top above
                tamp.add(ar[y-2][x+2]);			// top top right
            }

            if(x%3==0)							// if is num is in a left   call - add all the num from the top row
            { 									// add all the num from above
                tamp.add(ar[y-1][x]);			// top left
                tamp.add(ar[y-1][x+1]);			// top above
                tamp.add(ar[y-1][x+2]);			// top right

                tamp.add(ar[y-2][x]);			// top top left
                tamp.add(ar[y-2][x+1]);			// top above
                tamp.add(ar[y-2][x+2]);			// top top right

            }
        }
        
        else {										//Takes care of the corners

            if (x<8)								//left corner
            {   
                for (int i =0 ;i<3; i++)		
                {  
                    tamp.add(ar[xx-2][i]);          
                }

                for (int i =0 ;i<3; i++)
                {  
                    tamp.add(ar[xx-1][i]);                  
                }

            }
            else 									//right corner
            {
                tamp.add(ar[y-1][x]);
                tamp.add(ar[y-1][x-1]);
                tamp.add(ar[y-1][x-2]);

                tamp.add(ar[y-2][x]);
                tamp.add(ar[y-2][x-1]);
                tamp.add(ar[y-2][x-2]);

            }
        }

        for (int i =1 ;i< 10; i++)
        {
            tamp2.add(i);					// array of num from 1 to 9
        }

        for(int i=0; i<tamp.size();i++)
        {
            tamp2.remove(tamp.get(i));		//If it already exists, remove it
        }

        Collections.shuffle(tamp2);			// mix the num
        
        if(tamp2.size()!=0)			
        {
            ar[xx][yy]=tamp2.get(0);		// set the num in the array
        }
        
        else
        {
            newa(ar,xx);				// if not try agian
            for (int i=0; i<8; i++)
            {
               filld(ar,i,t,t,i,t);		// if not try agian 
            }

        }
        
        

    }

}
