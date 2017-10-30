import java.util.*;

public class TSP implements Runnable
{
    int size;
    int routes[][];//edges
    String cities[];//vertices
    
    Thread runners[];
    int threadCompleteCount;
    
    String solution;
    int totDistance;
    
    TSP()
    {
        try
        {
            int  i, j;
            String choice;

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the number of cities ");

            size = scan.nextInt();
            scan.skip("\n");

            cities = new String[size];
            routes = new int[size][size];

            System.out.println("Set city names : ");
            for(i=0; i< size; i++)
            {
                System.out.println("City " + (i+1) );
                cities[i] = scan.nextLine();
            }

            System.out.println("Set interconnecting routes ");
            for(i =0; i< size; i++)
            {
		
                for(j =i+1; j<size; j++ )
                {
                    System.out.println("Is there a route between " + cities[i] + " and " + cities[j] + "(y/n) : " );
                    choice = scan.nextLine();
                    if(choice.equalsIgnoreCase("y"))
                    {   
                        System.out.println("Enter distance : ");
                        routes[i][j] = routes[j][i] = scan.nextInt();
                        scan.skip("\n");
                    }
                    else
                    {//no route
                        routes[i][j] = routes[j][i] = 999;
                    }
                }//for(j
            }//for(i    
            
       
            threadCompleteCount = 0;
            solution = "Nearest Neighbour Algorithm couldnt form a tour to visit all cities";
            totDistance = 999;
            
            runners = new Thread[size];
            for(i =0 ; i< size; i++)
            {
                runners[i]= new Thread(this, String.valueOf(i));
                runners[i].start();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Err : "+ ex);
        } 
    }//TSP()
    
    
    void display()
    {
        int i, j;
        for(i = 0; i< size; i++)
        {
            System.out.println();
            System.out.print(cities[i] + " :  ");
            for(j =0; j< size; j++)
            {
                System.out.print( cities[j] + "(" + routes[i][j] + ")   ");
            }
        }//for(i ...
        System.out.println();
    }
    
    public void run()
    {
        int sPos = Integer.parseInt(Thread.currentThread().getName());
        solveTSPUsingNearestNeighbour(sPos);
        threadCompleteCount++;
    }
    
    boolean solveTSPUsingNearestNeighbour(int startPos)
    {
        boolean isTourComplete = false;
        try
        {
            String solution = "";
            int i, j, min,  currentPos, nextPos,  totDistance;
            int visitedCities[];
            int vi;
            
            //initializations and allocations
            
            visitedCities = new int[size];
            vi =0 ;
            totDistance = 0;
            
            //mark startPos as visited
            visitedCities[vi] = startPos;
            vi++;

            solution = cities[startPos];

            currentPos = startPos; 
            //tour 
            
            while(! isTourComplete)
            {
                nextPos = -1; 
                min = 999;

                for(i =0; i < size; i++)
                {
                    if(routes[currentPos][i] != 999 && currentPos != i)
                    {
                        int flag = 0; 

                        //check for being unvisited
                        for(j =0; j < vi; j++)
                        {
                            if(visitedCities[j] == i)
                            {
                                flag = 1;
                                break;
                            }
                        }//for(j ...

                        if(flag == 0)
                        {//unvisited
                            if(routes[currentPos][i] < min)
                            {
                                min = routes[currentPos][i];
                                nextPos = i;
                            } 
                        }
                    }//if(routes...

                }//for(i ...

                if(nextPos != -1)
                {//move to next city
                    totDistance += min;
                    visitedCities[vi] = nextPos;
                    vi++;
                    solution = solution + "  " + cities[nextPos];
                    currentPos = nextPos;
                }
                else
                {
                    break;
                }

                if(vi == size)
                {
                    //tour back to start city
                    if(routes[currentPos][startPos] != 999)
                    {
                        solution = solution + "  " + cities[startPos];
                        totDistance += routes[currentPos][startPos];
                        isTourComplete = true;
                        if(totDistance < this.totDistance)
                        {
                            this.totDistance = totDistance;
                            this.solution = solution + "\nTotal Distance : " + totDistance;
                        }
                    }
                    else
                    {
                        isTourComplete = false;
                        break;
                    }
                }
            }//while
            
        }
        catch(Exception ex)
        {
            solution = "Err : " + ex.getMessage();
        } 
        return isTourComplete;
    }
    
    void displaySolution()
    {
        
        while(threadCompleteCount < size)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception ex)
            {}
        }//while
        System.out.println("Solution : " + solution);
    }

    public static void main(String[] args) 
    {
        
        TSP tsp = new TSP();
        tsp.display();
	    tsp.displaySolution();
                
    }

}
/*
____________________________________________________

OUTPUT-
tanvi@tanvi-Inspiron-3542:~/practice$ javac TSP.java 
tanvi@tanvi-Inspiron-3542:~/practice$ java TSP 
Enter the number of cities 
5
Set city names : 
City 1
a 
City 2
b
City 3
c
City 4
d
City 5
e
Set interconnecting routes 
Is there a route between a  and b(y/n) : 
y
Enter distance : 
6
Is there a route between a  and c(y/n) : 
y
Enter distance : 
3
Is there a route between a  and d(y/n) : 
n
Is there a route between a  and e(y/n) : 
y
Enter distance : 
8
Is there a route between b and c(y/n) : 
y
Enter distance : 
4
Is there a route between b and d(y/n) : 
y
Enter distance : 
1
Is there a route between b and e(y/n) : 
y
Enter distance : 
3
Is there a route between c and d(y/n) : 
n
Is there a route between c and e(y/n) : 
y
Enter distance : 
2
Is there a route between d and e(y/n) : 
y
Enter distance : 
6

a  :  a (0)   b(6)   c(3)   d(999)   e(8)   
b :  a (6)   b(0)   c(4)   d(1)   e(3)   
c :  a (3)   b(4)   c(0)   d(999)   e(2)   
d :  a (999)   b(1)   c(999)   d(0)   e(6)   
e :  a (8)   b(3)   c(2)   d(6)   e(0)   
Solution : b  d  e  c  a   b
Total Distance : 18
tanvi@tanvi-Inspiron-3542:~/practice$ 
*/
