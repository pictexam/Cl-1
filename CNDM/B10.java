import java.io.*;
import java.net.*;
import java.util.Scanner;
public class B10
{
	
	public static void main (String args[]) {
		int n,t;
		float lambda,s,p,r;
		Scanner in = new Scanner(System.in);
		System.out.println("enter number of requests");
		n = in.nextInt();
		System.out.println("enter Time period");
		t = in.nextInt();
		lambda = n/t;
		System.out.println("Enter the time required to service a req in ms");
		s = in.nextFloat();
		s = s/1000;
		p = lambda/s/1000;
		System.out.println("The channel utilization is "+p);
		r = (p*s)/1000;
		System.out.println("The throughput of channel is "+r);
		
		
		
		
				}

}
