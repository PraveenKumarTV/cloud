#include<stdio.h>
#include<stdlib.h>
typedef struct{
    int data;
    struct node* next;
}node;

node* insertf(node* head,int val){
    node* nn=(node*)malloc(sizeof(node));
    nn->data=val;
    nn->next=NULL;
    if(head==NULL){
        return nn;
    }
    nn->next=head;
    head=nn;
    return head;
}

node* insert(node* head,int val,int pos){
        if(head==NULL || head->next==NULL){
            return insertf(head,val);
        }
        int i=0;
        node* nn=(node*)malloc(sizeof(node));
        nn->data=val;
        nn->next=NULL;
        node* cur=head;
        while(cur!=NULL){
            if(i==pos-1){
                nn->next=cur->next;
                cur->next=nn;
                break;
            }
            i++;
            cur=cur->next;
        }
        return head;

}

node* deletef(node* head){
    if(head==NULL||head->next==NULL){
        return NULL;
    }
    head=head->next;
    return head;
}

node* deleteany(node* head,int pos){
    if(pos==0||head==NULL||head->next==NULL){
        return deletef(head);
    }
    int i=0;
    node* cur=head;
    while(cur!=NULL){
        if(i==pos-1){
            node* tmp1=cur->next;
            node* tmp2=tmp1->next;
            cur->next=tmp2;
            break;
        }
        i++;
        cur=cur->next;
    }
    return head;
}

node* revpair(node* head,int k){
    if(head==NULL||head->next==NULL){
        return head;
    }
    int n=0;
    node* cur=head;
    while(cur!=NULL){
        n++;
        cur=cur->next;
    }
    int j=0;n=n/k;
    cur=head;
    while(n>0){
        int arr[k];
        //node* tmp1=cur;
        node* tmp2=cur;
        for(int i=0;i<k;i++){
            int a=cur->data;
            arr[i]=a;
            cur=cur->next;
        }
        for(int i=k-1;i>=0;i--){
            tmp2->data=arr[i];
            tmp2=tmp2->next;
        }
        n--;
    }
    return head;

}

int main(){
    node* head=(node*)malloc(sizeof(node));
    head->data=50;
    head->next=NULL;
    head=insertf(head,40);head=insertf(head,30);head=insertf(head,20);head=insertf(head,10);
    //head=insert(head,30,2);
    //head=deleteany(head,0);
    head=revpair(head,5);
    node* cur=(node*)malloc(sizeof(node));
    cur=head;
    while(cur!=NULL){
        printf("%d ",cur->data);
        cur=cur->next;
    }

}
