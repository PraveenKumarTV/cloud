#include<stdio.h>
void bubble(int arr[],int n){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(arr[i]<arr[j]){
                int tmp=arr[i];
                arr[i]=arr[j];
                arr[j]=tmp;
            }
        }
    }
}

void insertion(int arr[],int n){
    for(int i=0;i<n;i++){
        for(int j=0;j<i;j++){
            if(arr[j]>arr[i]){
                int tmp=arr[i];
                arr[i]=arr[j];
                arr[j]=tmp;
            }
        }
    }
}

void selection(int arr[],int n){
    for(int i=0;i<n-1;i++){
        int a=i;
        for(int j=i+1;j<n;j++){
            if(arr[a]>arr[j])
            a=j;
        }
        int tmp=arr[a];
        arr[a]=arr[i];
        arr[i]=tmp;
    }
}

int partition(int arr[],int i,int j){
    int p=arr[i];
    int l=i,h=j;
    while(l<h){
        while(arr[l]<=p && l<=j-1)
        l++;
        while(arr[h]>p && h>=i+1)
        h++;
        if(l<h){
            int tmp=arr[l];
            arr[l]=arr[h];
            arr[h]=tmp;
        }
    }
    int tmp=arr[i];
    arr[i]=arr[h];
    arr[h]=tmp;
    return h;
}

void quick(int arr[],int i,int n){
    if(i<n){
        int p=partition(arr,i,n);
        quick(arr,i,p-1);
        quick(arr,p+1,n);
    }
    
}

void print(int arr[],int n){
    for(int i=0;i<n;i++){
        printf("%d ",arr[i]);
    }
    printf("\n");
}

int main(){
    int n;printf("Enter no of elements:");scanf("%d",&n);
    int arr[n];
    for(int i=0;i<n;i++){
        printf("enter element %d:",i+1);
        scanf("%d",&arr[i]);
    }
    quick(arr,0,n-1);
    print(arr,n);

}