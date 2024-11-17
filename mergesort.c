#include<stdio.h>

void merge(int left[],int l,int right[],int r,int arr[]){
    int p=0,q=0,i=0;
    while(p<l&&q<r){
        if(left[p]<=right[q]){
            arr[i]=left[p];
            p++;
        }
        else if(left[p]>right[q]){
            arr[i]=right[q];
            q++;
        }
        i++;
    }
    while(p<l){
        arr[i]=left[p];
        p++;i++;
    }
    while(q<r){
        arr[i]=right[q];
        q++;i++;
    }

}

void mergesort(int arr[],int l){
if(l<2){
    return;
}
int mid=l/2;
int left[mid];int right[l-mid];
for(int i=0;i<l;i++){
    if(i<mid){
        left[i]=arr[i];
    }
    else{
        right[i-mid]=arr[i];
    }
}
mergesort(left,mid);
mergesort(right,l-mid);
merge(left,mid,right,l-mid,arr);

}



int main(){
int arr[]={5,4,3,2,1};
mergesort(arr,5);
for(int i=0;i<5;i++){
        printf("%d ",arr[i]);
    }
}
