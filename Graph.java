//Mehaf Allazzam - 440021473
//Raghad Alshabana - 440021235


//////////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.List;

public class Graph 
{
    //////////////////////////////////////////////////////////////////////////////---------> CREATING A GRAPH(START)
    private int v;
    private ArrayList<Integer>[] adjList;

    public Graph(int vertices)
    {
        this.v = vertices;
        initAdjList();
    }

    @SuppressWarnings("unchecked")
    private void initAdjList()
    {
	adjList = new ArrayList[v];

	for (int i = 0; i < v; i++)
	adjList[i] = new ArrayList<>();
    }
    public void addEdge(int u, int v)
    {
	// Add v to u's list.
	adjList[u].add(v);
    }
    //////////////////////////////////////////////////////////////////////////////---------> CREATING A GRAPH(END)
    
    
    //////////////////////////////////////////////////////////////////////////////---------> C-DFS ALGORITHM(STATRT)
    
    static ArrayList<ArrayList<Integer>> AllPaths1=new ArrayList<ArrayList<Integer>>();// ---> Array List store lists of paths  
    
    public void CDFS(int s, int t ,int k) //----> to find the all paths from source to target
    {
	boolean[] isVisited = new boolean[v]; //---> array to cheak of visiting the vertices
	ArrayStack<Integer> stack = new ArrayStack<Integer>(); // ----> stack to use it explore the path
        
        stack.push(s); 

        printAllPaths(s, t, k,isVisited, stack); //---> all work in this call method (will print all paths)
    }
    
    int a=0;   
    void print_CDFS(ArrayStack<Integer> stack,int t) //----> this function stores the paths coming in stcks from another function called "printAllPaths" and store these paths in one list called "AllPaths1" 
    {
        ArrayList<Integer> path1 = new  ArrayList<Integer>(); // ---> each time will store one path 
        
      // The work here is just store the path and print it
      
        int n=stack.size();
        for(int a=0; a<n;a++)
            stack2.push(stack.pop());
            
        System.out.print("path : ");
           
        int m=stack2.size();
        for(int i=0;i<m;i++)
        {
            int v=stack2.pop();
            path1.add(i, v); // ----> just save one path 
             
            System.out.print(v+(v==t ? " "  : " -> "));
            stack.push(v); // return its value
        }
            
        AllPaths1.add(a,path1); // here add each path 
        a++; // ---> increment the index 
            
        System.out.println();
    }
    
    void printAllPaths(int s, int t, int k, boolean[] isVisited, ArrayStack<Integer> stack) //----> recursive function to find all paths from source to target
    {
        if (s==t) //----> if we reach the end of each path 
        {
            print_CDFS(stack,t); // -----> go to this method and stored the path in (AllPaths) and print it
                return;
        }
	isVisited[s] = true;

        for(Integer i : adjList[s])// -----> cheak for all neghebores of vertex s 
        {
            if (!isVisited[i] && stack.size()<=k) // ----> should cheack to the important condition of C-DFS
            {
                stack.push(i);
                printAllPaths(i, t, k, isVisited, stack); // ----> recursive to walk through, take also the adjecents of each i
                stack.pop(); // ---> after all recursive will continue from here ,the pop work like "go back" to take another path
            }
        }
        isVisited[s] = false;      
    }
    //////////////////////////////////////////////////////////////////////////////---------> C-DFS ALGORITHM(END)
    
    
    
    //////////////////////////////////////////////////////////////////////////////---------> T-DFS ALGORITHM(STATRT)
    
    static ArrayList<ArrayList<Integer>> AllPaths=new ArrayList<ArrayList<Integer>>();
    
    private void TDFS(Integer s, Integer t,int k)
    {
        ArrayStack<Integer> stack = new ArrayStack<>(); //----> stack stores actual vertices in the current path
        ArrayStack<Integer> stackTemp = new ArrayStack<>(); //----> temp stack stores actual vertices in the current path
        ArrayList<Integer> array=new  ArrayList<>(); //----> temp array
        boolean[] isVisited = new boolean[v]; //----> isVisited[] keeps track of vertices in current path.
      
        stack.push(s); //----> push the current vertex to the stack
        isVisited[s]=true; //----> mark that the vertex is visited
      
        int CurrentVertex=s;
        while(isAlone(CurrentVertex) != -1 && CurrentVertex!=t) //----> in this loop we used 'isAlone(Integer u)' function to know if the current vertex has only one chiled , if yes then push the child in the stack.
        {
            int y=isAlone(CurrentVertex);
            stack.push(y);
            CurrentVertex=y;
            isVisited[CurrentVertex]=true;
        }
         
        int m=stack.size(); //----> saving stack size befor popping its elements
        for(int i=0;i<m;i++) //----> from line 129-138 moving stack elements to an array;
            stackTemp.push(stack.pop());
          
        int n=stackTemp.size(); //----> saving stack size befor popping its elements
        for(int i=0;i<n;i++)
        {
            int temp=stackTemp.pop();
            array.add(temp);
            stack.push(temp);
        }
          
        if(CurrentVertex==t) //----> if we reached the target then print the path stored in the array and exit from this function.
        {
            for(int p=0;p<array.size();p++)
                //System.out.print(array.get(p)+" ");
                System.out.print(v+(v==t ? " "  : " -> "));
                return;
        }
          
        for (Integer i : adjList[CurrentVertex]) //----> by this for loop we want to find the all paths from all adjecent of the current vertex to the target because we want to take the shortest path
            CDFS2(i,t,k,isVisited);
        //----> now all the paths are stored in an ArrayList called "AllPaths"
          
        int result = shortestpath(); //----> this function will return the index of the shortest path from all paths
        boolean flag=false; //----> to check if we found shortest path and StackSize+ShortestPath <= K then there is  path 
             
        if(result==-1) //----> then there is no path found
        {
            System.out.println("No path found");
            return;
        }
         
        for(int j=0; j<AllPaths.size() ;j++)
        {
            if( stack.size()+(AllPaths.get(j).size()-1)<=k && AllPaths.get(j).size()<=AllPaths.get(result).size()) //----> if StackSize+Length Of Shortest Path <= K and if there is another path its size equal to the shortest path 
            {
                flag=true;
                    
                for(int p=0;p<array.size();p++) //----> print the vertices in the stack stored in an array
                {
                    //System.out.print(array.get(p)+" ");
                    v = array.get(p);
                    System.out.print(v+(v==t ? " "  : " -> "));
                }
                   
                for(int p=0;p<AllPaths.get(j).size();p++) //----> print the shortest path
                {
                    //System.out.print(AllPaths.get(j).get(p)+" ");
                    v = AllPaths.get(j).get(p);
                    System.out.print(v+(v==t ? " "  : " -> "));
                }
                     
                System.out.println();
            }
        }
        if(!flag)
            System.out.println("There is no path less than or equal to K");
    }
    
    private int isAlone(Integer u) //----> function to know if the vertex has only one chiled
    {
        if(adjList[u].size()==1)
            return adjList[u].get(0);
            return -1;
    }
    
    public void CDFS2(int s, int t ,int k,boolean[] isVisited) //----> to find the all paths from source to target
    {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        
        stack.push(s);

        printAllPaths2(s, t, isVisited, stack,k);
    }
    private void printAllPaths2(Integer s, Integer t,boolean[] isVisited,ArrayStack<Integer> stack,int k) //----> recursive function to find all paths from source to target
    {
        if (s.equals(t))
        {
            print_TDFS(stack,t);
                return;
        }
        isVisited[s] = true;

        for(Integer i : adjList[s])
        {       
            if (!isVisited[i] && stack.size()<=k)
            {	
                stack.push(i);
                printAllPaths2(i, t, isVisited, stack,k);

		stack.pop();
            }
	}
        isVisited[s] = false;               
    }
    
    ArrayStack<Integer> stack2=new  ArrayStack<Integer> (); //----> temp stack
    int count=0; //----> counter to know where to add the current path in the list 
        
    void print_TDFS(ArrayStack<Integer> stack,int t) //----> this function stores the paths coming in stcks from another function called "printAllPaths2" and store these paths in one list called "AllPaths" 
    {
        ArrayList<Integer> path1 = new  ArrayList<Integer>();
          
        int n = stack.size(); //----> saving stack size befor popping its elements
        for(int a=0; a<n;a++)
            stack2.push(stack.pop());
            
        int m=stack2.size(); //----> saving stack size befor popping its elements
        for(int i=0;i<m;i++)
        {
            int v=stack2.pop();
            path1.add(i, v);
            stack.push(v);
        }
            
        AllPaths.add(count,path1);
        count++; 
    }
    
    public int shortestpath() //----> this function finds the shortest path from all the paths stored in the list "AllPaths" and returns the index of the shortest path stored in the list or -1 if no paths were found
    {
        if(AllPaths.isEmpty()) 
            return -1;
        else
        {  
            int smallest=AllPaths.get(0).size();
            int result=0;
            for(int i=1 ; i<AllPaths.size();i++)  
            {
                if(AllPaths.get(i).size()<smallest)
                {
                    smallest=AllPaths.get(i).size();
                    result=i;
                }
            }
            return result;   
        }
    }
    //////////////////////////////////////////////////////////////////////////////---------> T-DFS ALGORITHM(END)
    
    
    public static void main(String[] args)
    {
	Graph g = new Graph(5); 
	g.addEdge(1,2);
	g.addEdge(1,3);
	g.addEdge(1,4);
	g.addEdge(2,3);
        g.addEdge(2,4);
        //g.addEdge(3,2);
        //g.addEdge(3,4);
        g.addEdge(4,3);
        //g.addEdge(4,2);
              
        int s = 1;
        int t = 2;
        int k=3;
	System.out.println("Using C-DFS algorithm all paths from "+ s + " to " + t +" with at most "+k+" hops :");
		
        g.CDFS(s, t,k);
               
        if(AllPaths1.isEmpty())
            System.out.println("There is no path");
                        
                
        System.out.println("Using T-DFS algorithm the sortest path from "+ s + " to " + t +" with at most "+k+" hops is : ");
        g.TDFS(s,t,k);
    }
}
        
