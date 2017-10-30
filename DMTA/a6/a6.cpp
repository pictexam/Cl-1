#include<iostream>
#include<cmath>
#include<algorithm>

using namespace std;

float distance(float a, float b, float c, float d)
{
	float x =abs(a-c);
	float y=abs(b-d);
	return sqrt(pow(x,2)+pow(y,2));
}

int main()
{
	
	cout<<"Enter number of data points";
	int no_data_pts, no_clusters;
	
	cin>>no_data_pts;
	float pts[no_data_pts][2], cent_pts[no_clusters][2];
	for(int i=0;i<no_data_pts;i++)
	{
		cout<<"\n enter point "<<i;
		cin>>pts[i][0]>>pts[i][1];
	}
	cout<<"\n enter no of clusters";
	cin>>no_clusters;
	
	for(int i=0;i<no_clusters;i++)
	{
		cout<<"\n Enter centroid "<<i;
		cin>>cent_pts[i][0]>>cent_pts[i][1];
	}	
	int cluster[no_data_pts]={0};
		
		float centr_dist[no_clusters];
	int flag=0;
	while(flag==0)
	{
		flag=1;
		int clust_count[no_clusters]={0};
		for(int i=0;i<no_data_pts;i++)
		{
			int temp;
			
			float min_dist=9999;
			for (int j = 0; j < no_clusters; ++j)
			{
				centr_dist[j]=distance(cent_pts[j][0],cent_pts[j][1], pts[i][0], pts[i][1] );
				if(centr_dist[j]<min_dist)
				{
					min_dist=centr_dist[j];
					temp=j;
				}
			}
			if(cluster[i]!=temp)
			{
				flag=0;
				cluster[i]=temp;
			}
			clust_count[temp]++;
		}

		
		for(int i=0;i<no_clusters;i++)
		{
			float xsum=0, ysum=0;
			for(int j=0;j<no_data_pts;j++)
			{
				if(cluster[j]==i)
				{
					xsum+=pts[j][0];
					ysum+=pts[j][1];
				}
			}
			cent_pts[i][0]=xsum/clust_count[i];
			cent_pts[i][1]=ysum/clust_count[i];
		}
		cout<<"\n Updated centroids are:";
		for(int i=0;i<no_clusters;i++)
		{
			cout<<"Centroid "<<i<<" :"<<cent_pts[i][0]<<", "<<cent_pts[i][1];
		}
		cout<<"\n Cluster Distribution:";
		for (int i = 0; i < no_data_pts; ++i)
		{
			cout<<"\n Point"<<i<<" belongs to cluster "<<cluster[i];
		}

	}
}

