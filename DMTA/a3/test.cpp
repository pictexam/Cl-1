#include<iostream>
using namespace std;
int l=1;
int lcm(int a,int b){
	
	if(l%a==0 && l%b==0)
		return l;
	
	else{
		l++;
		return lcm(a,b);
	}
	
}


int main(){

	
	cout<<lcm(5,25);

return 0;
}
