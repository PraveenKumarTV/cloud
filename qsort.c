#include <stdio.h>

int piv(int *arr, int low, int high) {
    int pivot = arr[low];
    int i = low + 1; 
    int j = high;

    while (i <= j) {
        while (i <= high && arr[i] <= pivot) {
            i++;
        }
        while (j >= low && arr[j] > pivot) {
            j--;
        }
        if (i < j) {
            
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
    
    arr[low] = arr[j];
    arr[j] = pivot;
    return j; 
}

void qs(int *arr, int low, int high) {
    if (low < high) {
        int pind = piv(arr, low, high);
        qs(arr, low, pind - 1); 
        qs(arr, pind + 1, high); 
    }
}

int main() {
    int arr[] = {4, 6, 2, 5, 7, 9, 1, 3};
    int n = sizeof(arr) / sizeof(arr[0]);
    qs(arr, 0, n - 1); 
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    return 0;
}
