#include<stdio.h>
#include<stdlib.h>

typedef struct{
    int data;
    struct node* next;
}node;

node* createll(int arr[],int n){
    node* head=(node*)malloc(sizeof(node));
    node* cur=(node*)malloc(sizeof(node));
    head->data=arr[0];
    head->next=NULL;
    cur=head;
    for(int i=1;i<n;i++){
        node *tmp;
        tmp=(node*)malloc(sizeof(node));
        tmp->data=arr[i];
        tmp->next=NULL;
        cur->next=tmp;
        cur=tmp;
    }
    return head;

}

node* insertfirst(node* head,int val){
    if(head==NULL){
        return NULL;
    }
    node* tmp=(node*)malloc(sizeof(node));
    tmp->data=val;
    tmp->next=head;
    head=tmp;return head;
}

node* deletefirst(node* head){
    if(head==NULL || head->next==NULL)
        return NULL;
    node* tmp=head;
    head=tmp->next;free(tmp);
    return head;

}

node* insertany(node* head,int val,int pos){
    if(head==NULL || pos<0)
        return NULL;
    if(pos==1)
        return insertfirst(head,val);
    node* cur=head;int i=1;
    while(cur!=NULL){
        if(i==pos-1){
            node* tmp=(node*)malloc(sizeof(node));
            tmp->data=val;
            tmp->next=cur->next;
            cur->next=tmp;
            break;
        }
        i++;
        cur=cur->next;
    }
    return head;

}

node* deleteany(node* head,int pos){
    if(head==NULL || head->next==NULL || pos<0)
        return NULL;
    if(pos==1){
        return deletefirst(head);
    }
    node* cur=head;
    int i=1;
    while(cur!=NULL){
        if(i==pos-1){
            node* tmp=cur->next;
            cur->next=tmp->next;
            free(tmp);
            break;
        }
        i++;cur=cur->next;
    }
    return head;
}

int main(){
    int arr[]={1,2,3,4};node* head1=(node*)malloc(sizeof(node));
    head1=createll(arr,4);
    head1=insertany(head1,10,1);
    //head1=deleteany(head1,1);
    while(head1!=NULL){
        printf("%d ",head1->data);
        head1=head1->next;
    }
}
