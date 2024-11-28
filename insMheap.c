#include<stdio.h>

void heapify(int arr[],int *n,int el){
    (*n)++;
    int loc=*n;
    while(loc>0){
        int par=loc/2;
        if(arr[par]>el){
            arr[loc]=el;
            return;
        }
        arr[loc]=arr[par];
        loc=par;
    }
    arr[0]=el;
}

int main(){
    int arr[10]={19,15,10,7,12,2,5};
    int n=6;
    int e=20;
    heapify(arr,&n,e);
    for(int i=0;i<n;i++){
        printf("%d ",arr[i]);
    }

}
