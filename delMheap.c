#include<stdio.h>
#include<stdlib.h>

void heapify(int arr[],int n,int i){
    int loc=0;int left=1,right=2;
    int last=arr[n];n=n-1;
    while(right<=n){
        if(last>=arr[left] && last>=arr[right]){
            arr[loc]=last;
            return;
        }
        if(arr[right]>=arr[left]){
            arr[loc]=arr[right];
            loc=right;
        }
        else{
            arr[loc]=arr[left];
            loc=left;
        }
        left=2*loc+1;
        right=2*loc+2;
    }
    if(left==n && last<arr[left]){
        arr[loc]=arr[left];
        loc=left;
    }
    arr[loc]=last;
}

int main(){
    int arr[]={90,15,10,7,12,2,5};
    int n=sizeof(arr)/sizeof(arr[0]);
    heapify(arr,n-1,0);
    for(int i=0;i<n-1;i++){
        printf("%d ",arr[i]);
    }
}