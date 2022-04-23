import java.util.*;
class Graph{
	ArrayList<LinkedList<Integer>>adjList = new ArrayList<LinkedList<Integer>>();
	Graph(int vrt){
		for(int i = 0 ; i < vrt ; i ++){
			adjList.add(new LinkedList<>());
		}
	}
	
	public void addEdge(int source, int destination){
		this.adjList.get(source).add(destination);
		this.adjList.get(destination).add(source);
	}
	
	public void printAdjListOfGraph(){
		for(int i = 0; i < this.adjList.size(); i++){
			System.out.println(i + " --> " + this.adjList.get(i));
		}
	}
	
	int bfs(int source, int destination){
		boolean visited[] = new boolean[this.adjList.size()];
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(source);
		visited[source] = true;
		int pre[] = new int[this.adjList.size()];
		pre[source] = -1;
		while(! que.isEmpty()){
			int cur = que.poll();
			if(cur == destination){
				visited[destination] = true;
				break;
			}
			
			for(int it: this.adjList.get(cur)){
				if(! visited[it]){
					pre[it] = cur;
					que.add(it);
					visited[it] = true;
				}
			}		
		}
		if(!visited[destination]) return -1;
		int dis = 0, i = destination;
		while(i != source){
			dis++;
			System.out.println(pre[i] + "-->");
			i = pre[i];
		}
		return dis;
	}
	public boolean dfs(int source, int destination){
		Stack<Integer> st = new Stack<>();
		boolean visited[] = new boolean[this.adjList.size()];
		st.push(source);
		visited[source] = true;
		while(! st.isEmpty()){
			int cur = st.pop();
			if (cur == destination) return true;
			for(int it : this.adjList.get(cur)){
				if(!visited[it]){
					visited[it] = true;
					st.push(it);
					break;
				}
			}
		}
		return false;
	}
}
public class Main{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter num of vertices");
		int v = sc.nextInt();
		System.out.println("Enter num of edges");
		int ed = sc.nextInt();
		Graph graph = new Graph(v);
		
		for(int i = 0; i < ed; i++){
			//System.out.println("Enter edge : ");
			int s = sc.nextInt();
			int d = sc.nextInt();
			graph.addEdge(s,d);
		}
		graph.printAdjListOfGraph();
		
		System.out.println("Enter source and destination to find shortest path " );
		int ss = sc.nextInt();
		int dd = sc.nextInt();
		System.out.println(graph.bfs(ss,dd));
		System.out.println(graph.dfs(ss,dd));
	}
}