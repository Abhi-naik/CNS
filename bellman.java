import java.io.*;
import java.util.*;
public class bellman
{
	public static final int max_val=999;
	private int distance[];
	private int vertices;
	public static void main(String args[])
	{
		int vertices=0;
		int source;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of vertices:");
		vertices=sc.nextInt();
		int adjMatrix[][]=new int[vertices+1][vertices+1];
		System.out.println("Enter adjacency matrix:");
		for(int sn=1;sn<=vertices;sn++)
		{
			for(int dn=1;dn<=vertices;dn++)
			{
				adjMatrix[sn][dn]=sc.nextInt();
				if(sn==dn)
				{
					adjMatrix[sn][dn]=0;
					continue;
				}
				if(adjMatrix[sn][dn]==0)
				{
				adjMatrix[sn][dn]=max_val;
				}
			}
		}
		System.out.println("Enter the source vertex:");
		source=sc.nextInt();
		bellman bellman=new bellman(vertices);
		bellman.calculation(source,adjMatrix);
		sc.close();
	}
	public bellman(int vertices)
	{
		this.vertices=vertices;
		distance=new int[vertices+1];
	}
	public void calculation(int source,int adjMatrix[][])
	{
		for(int node=1;node<=vertices;node++)
		{
			distance[node]=max_val;
		}
		distance[source]=0;
		for(int node=1;node<=vertices;node++)
		{
			for(int sn=1;sn<=vertices;sn++)
			{
				for(int dn=1;dn<=vertices;dn++)
				{
					if(adjMatrix[sn][dn]!=max_val)
					{
						if(distance[dn]>distance[sn]+adjMatrix[sn][dn])
							distance[dn]=distance[sn]+adjMatrix[sn][dn];
					}
				}	

			}
		}
		for(int sn=1;sn<=vertices;sn++)
		{
			for(int dn=1;dn<=vertices;dn++)
			{
				if(adjMatrix[sn][dn]!=max_val)
				{
					if(distance[dn]>distance[sn]+adjMatrix[sn][dn])
						System.out.println("Negative edge cycle");
				}
			}
		}
		for(int vertex=1;vertex<=vertices;vertex++)
		{
			System.out.println("Distance of source " + source + "to " + vertex + "is " + distance[vertex]);
		}
	}
}
