package sudoko;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

class cc
{

    public static void main(String args[])
    {
        int[][]bord = new int[9][9];
        int nu=0;
        newa(bord,9);
        int[][]user = new int[9][9];
        copyb(bord,user);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Difficulty level between 1-3 : ");
        while (sc.hasNextInt())
        {
            int num = sc.nextInt();
            if (num==1 || num==2 || num==3)
            {
                nu=num;
                break;   
            }
            sc.nextLine();

        }
        System.out.println("num is : " + nu);

        if (nu==1)
        {
            int tt = 0;

            while (tt<30)
            {
                Random rnd = ThreadLocalRandom.current();
                int index = rnd.nextInt(8 + 1);
                int indey = rnd.nextInt(8 + 1);
                if(user[index][indey]!=0)
                {
                    user[index][indey]=0;
                    tt++;
                }
            }
            System.out.println("level 1 board ");
            pp(user);
            finish(user,bord);
        }

        if (nu==2)
        {
           
            int tt = 0;

            while (tt<40)
            {
                Random rnd = ThreadLocalRandom.current();
                int index = rnd.nextInt(8 + 1);
                int indey = rnd.nextInt(8 + 1);
                if(user[index][indey]!=0)
                {
                    user[index][indey]=0;
                    tt++;
                }
            }
            System.out.println("level 2 board ");
            pp(user);
            finish(user,bord);
        }

        if (nu==3)
        {
            int tt = 0;

            while (tt<60)
            {
                Random rnd = ThreadLocalRandom.current();
                int index = rnd.nextInt(8 + 1);
                int indey = rnd.nextInt(8 + 1);
                if(user[index][indey]!=0)
                {
                    user[index][indey]=0;
                    tt++;
                }
            }
            System.out.println("level 3 board ");
            pp(user);
            finish(user,bord);
        }

        
        finish(bord,bord);
        
    }
    static void gettopr(int[][] ar)
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        for (int i =0 ;i< 6; i++)
        {

            tamp.add(ar[0][i+3]);

        }

        Collections.shuffle(tamp);

        for (int i = 0; i < 3; i++)
        {
            int xxx = tamp.get(i);
            ar[1][i]=xxx;

        } 

        for (int i = 0; i < 3; i++)
        {
            int xxx = tamp.get(i+3);
            ar[2][i]=xxx;

        } 

    }

    static void gettopm(int[][] ar)
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        for (int i =1 ;i< 10; i++)
        {
            if(ar[1][0]!=i && ar[1][1]!=i && ar[1][2]!=i && ar[0][3]!=i && ar[0][4]!=i && ar[0][5]!=i)
                tamp.add(i);

        }

        Collections.shuffle(tamp);
        int j=0;
        int t=0;

        while (t<3)
        {
            if(tamp.get(j)==ar[2][0] || tamp.get(j)==ar[2][1] || tamp.get(j)==ar[2][2] || tamp.get(j)==ar[0][0] || tamp.get(j)==ar[0][1] || tamp.get(j)==ar[0][2])
            {

                int xxx = tamp.get(j);
                ar[1][t+3]=xxx;
                tamp.remove(0);
                t++;

            }
            else
            {
                tamp.remove(0);
            }
        }

        tamp.clear(); 

        for (int i =1 ;i< 10; i++)
        {
            if(ar[0][3]!=i && ar[0][4]!=i && ar[0][5]!=i && ar[1][3]!=i && ar[1][4]!=i && ar[1][5]!=i)
                tamp.add(i);

        }

        for (int i = 0; i < 3; i++)
        {
            int xxx = tamp.get(i);
            ar[2][i+3]=xxx;

        } 

        for (int i = 0; i < 3; i++)
        {
            if (ar[2][i+3]==ar[2][0] || ar[2][i+3]==ar[2][1] || ar[2][i+3]==ar[2][2])
            {
                int z=ar[2][i+3];
                ar[2][i+3]=ar[1][i+3];
                ar[1][i+3]=z;
            }

        } 

    }

    static void gettopl(int[][] ar)
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        ArrayList<Integer> tamp2 = new ArrayList<>();
        for (int i =1 ;i< 10; i++)
        {

            if(i!=ar[1][0] && i!=ar[1][1] && i!=ar[1][2] && i!=ar[1][3] && i!=ar[1][4] && i!=ar[1][5] )
            {
                tamp.add(i);
            }

            if(i!=ar[2][0] && i!=ar[2][1] && i!=ar[2][2] && i!=ar[2][3] && i!=ar[2][4] && i!=ar[2][5] )
            {
                tamp2.add(i);
            }

        }

        Collections.shuffle(tamp);
        Collections.shuffle(tamp2);
        for (int i = 0; i < 3; i++)
        {
            int xxx = tamp.get(i);
            ar[1][i+6]=xxx;

            int yyy = tamp2.get(i);
            ar[2][i+6]=yyy;

        } 

    }

    static void fillt(int[][] ar,int x , int xx,int y ,int yy, int t)
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        ArrayList<Integer> tamp2 = new ArrayList<>();

        for (int i =y-1 ;i>=0; i--)
        {  

            tamp.add(ar[i][yy]);

        }

        for (int i =x-1 ;i>=0; i--)
        {  
            tamp.add(ar[xx][i]);
        }

        for (int i =1 ;i< 10; i++)
        {
            tamp2.add(i);

        }

        for(int i=0; i<tamp.size();i++)
        {
            tamp2.remove(tamp.get(i));
        }

        Collections.shuffle(tamp2);

        if(tamp2.size()!=0)
        {
            ar[xx][yy]=tamp2.get(0);

        }
        else
        {
            newa(ar,xx);
            for (int i=0; i<9; i++)
            {
                fillt(ar,i,t,t,i,t);
            }

        }

    }

    static void fillm(int[][] ar,int x , int xx,int y ,int yy,int t)
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        ArrayList<Integer> tamp2 = new ArrayList<>();

        for (int i =y-1 ;i>=0; i--)
        {  
            tamp.add(ar[i][yy]);
        }

        for (int i =x-1 ;i>=0; i--)
        {  
            tamp.add(ar[xx][i]);
        }

        if(x%3==1)
        {
            tamp.add(ar[y-1][x-1]);
            tamp.add(ar[y-1][x]);
            tamp.add(ar[y-1][x+1]);
            if (x==1)
            {
                tamp.add(ar[y-1][x+1]);
            }
        }

        if(x%3==2)
        {
            tamp.add(ar[y-1][x-2]);
            tamp.add(ar[y-1][x-1]);
            tamp.add(ar[y-1][x]);
        }

        if(x%3==0)
        {
            tamp.add(ar[y-1][x]);
            tamp.add(ar[y-1][x+1]);
            tamp.add(ar[y-1][x+2]);
        }

        for (int i =1 ;i< 10; i++)
        {
            tamp2.add(i);

        }

        for(int i=0; i<tamp.size();i++)
        {
            tamp2.remove(tamp.get(i));
        }

        Collections.shuffle(tamp2);
        if(tamp2.size()!=0)
        {
            ar[xx][yy]=tamp2.get(0);
        }
        else
        {
            newa(ar,xx);
            for (int i=0; i<9; i++)
            {
                fillm(ar,i,t,t,i,t);
            }
        }

    }

    static void filld(int[][] ar,int x , int xx,int y ,int yy, int t)
    {

        ArrayList<Integer> tamp = new ArrayList<>();
        ArrayList<Integer> tamp2 = new ArrayList<>();

        for (int i =y-1 ;i>=0; i--)
        {  
            tamp.add(ar[i][yy]);
        }

        for (int i =x-1 ;i>=0; i--)
        {  
            tamp.add(ar[xx][i]);
        }

        if (x>2 && x<8)
        {
            if(x%3==1)
            {
                tamp.add(ar[y-1][x-1]);
                tamp.add(ar[y-1][x]);
                tamp.add(ar[y-1][x+1]);

                tamp.add(ar[y-2][x-1]);
                tamp.add(ar[y-2][x]);
                tamp.add(ar[y-2][x+1]);
            }

            if(x%3==2)
            {
                tamp.add(ar[y-1][x]);
                tamp.add(ar[y-1][x+1]);
                tamp.add(ar[y-1][x+2]);

                tamp.add(ar[y-2][x]);
                tamp.add(ar[y-2][x+1]);
                tamp.add(ar[y-2][x+2]);
            }

            if(x%3==0)
            { 
                tamp.add(ar[y-1][x]);
                tamp.add(ar[y-1][x+1]);
                tamp.add(ar[y-1][x+2]);

                tamp.add(ar[y-2][x]);
                tamp.add(ar[y-2][x+1]);
                tamp.add(ar[y-2][x+2]);

            }
        }
        else {

            if (x<8)
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
            else 
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
            tamp2.add(i);
        }

        for(int i=0; i<tamp.size();i++)
        {
            tamp2.remove(tamp.get(i));
        }

        Collections.shuffle(tamp2);
        if(tamp2.size()!=0)
        {
            ar[xx][yy]=tamp2.get(0);
        }
        else
        {
            newa(ar,xx);
            for (int i=0; i<9; i++)
            {
                filld(ar,i,t,t,i,t);
            }
        }
    }

    static void newa(int[][] bord, int xx)
    {
        List<Integer> solutionArray = new ArrayList<>();
        ArrayList<Integer> tamp = new ArrayList<>();

        for (int i = 0; i < 9; i++)
        {
            solutionArray.add(i+1);
        } 

        Collections.shuffle(solutionArray);
        for (int i = 0; i < 9; i++)
        {
            int xxx = solutionArray.get(i);
            bord[0][i]=xxx;
        } 

        gettopr(bord);
        gettopm(bord);
        gettopl(bord);

        for (int i=3; i<xx; i++)
        {
            for (int j=0; j<8; j++)
            {

                if(i%3==0)
                {
                    fillt(bord,j,i,i,j,i);
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

    static void finish (int[][] x , int[][] y)
    {
        int cc = 0;
        System.out.println();
        System.out.println("__________finish___________________");

        for (int i = 0; i < x.length; i++)
        {
            for (int j = 0; j < x[0].length; j++)
            {
                if (x[i][j]!=y[i][j])
                {
                    cc--;   
                }

            } 

        }

        if (cc!=0)
        {
             System.out.println("__________user___is___idiot________________");
        }
        if (cc==0)
        {
             System.out.println("__________your___ok___good________________");
        }
    }

        static void copyb (int[][] x , int[][] y)
    {
        
        for (int i = 0; i < x.length; i++)
        {
            for (int j = 0; j < x[0].length; j++)
            {
                   int cc=x[i][j];
                   y[i][j]=cc;
            } 

        }

    }
    
    static void pp(int[][] bord)
    {
        System.out.println();
        System.out.println("__________star pp___________________");

        for (int i = 0; i < bord.length; i++)
        {
            if(i%3==0)
            {
                System.out.println("_____________________________");
            }

            for (int j = 0; j < bord[0].length; j++)
            {
                if(j%3==0)
                {
                    System.out.print("|");
                }
                System.out.print(bord[i][j] + ", ");

            } 

            System.out.println();
        }

        System.out.println("__________end pp___________________");
    }

}