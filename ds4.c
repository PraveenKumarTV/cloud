#include<stdio.h>
#include<stdlib.h>
typedef struct{
    int data;
    struct node* next;
    struct node* prev;
}node;

node* createll(int arr[],int n){
    node* head=(node*)malloc(sizeof(node));
    head->data=arr[0];
    head->prev=NULL;
    head->next=NULL;
    node* cur=head;
    for(int i=1;i<n;i++){
        node* tmp=(node*)malloc(sizeof(node));
        tmp->data=arr[i];
        tmp->prev=cur;cur->next=tmp;tmp->next=NULL;
        cur=cur->next;
    }
    return head;
}

node* insertfirst(node* head,int val){
    if(head==NULL)
    return NULL;
    node *nh=(node*)malloc(sizeof(node));
    nh->data=val;nh->next=head;nh->prev=NULL;
    head->prev=nh;
    head=nh;
    return head;
}

node* insertany(node* head,int val,int pos){
    if(head==NULL||pos<0)
    return NULL;
    if(head->next==NULL||pos==0)
    return insertfirst(head,val);
    int i=0;node* cur=head;
    while(cur!=NULL){

        if(i==pos-1){
            node* tmp=(node*)malloc(sizeof(node));
            tmp->data=val;
            if(cur->next==NULL){
                    tmp->next=NULL;
                cur->next=tmp;
                tmp->prev=cur;
                break;
            }
            else{
    tmp->next=cur->next;
            node* tmp2=cur->next;tmp2->prev=tmp;
            cur->next=tmp;tmp->prev=cur;
            break;
            }
        }
        i++;cur=cur->next;
    }
    return head;
}

node* deletefirst(node* head){
    if(head==NULL||head->next==NULL){
        return NULL;
    }
    node* tm=head->next;
    tm->prev=NULL;head->next=NULL;
    free(head);
    return tm;

}

node* deleteany(node* head,int pos){
    if(head==NULL||pos<0)
    return NULL;
    if(pos==0||head->next==NULL)
    return deletefirst(head);
    int i=0;node* cur=head;
    while(cur!=NULL){
        if(i==pos-1){
            node* tmp=cur->next;
            if(tmp->next==NULL){
                cur->next=NULL;tmp->prev=NULL;
                free(tmp);break;
            }
            node* tm=tmp->next;
            cur->next=tm;tmp->next==NULL;
            tm->prev=cur;tmp->prev=NULL;
            free(tmp);
            break;
        }
        i++;cur=cur->next;
    }
    return head;
}

int main(){
    int arr[]={1,2,3,4};
    node* head=(node*)malloc(sizeof(node));
    head=createll(arr,4);
    head=insertany(head,10,4);
    head=deleteany(head,4);
    while(head!=NULL){
        printf("%d ",head->data);
        head=head->next;
    }

}
