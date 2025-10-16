//https://www.youtube.com/watch?v=0SkOjNaO1XY

public class quickSort {
    public void quickSortAlg(int[] a, int l, int r) {
        if (l < r) {
            int p = partition(a, l, r);

            quickSortAlg(a, l, p - 1); //LEFT SUBARRAY
            quickSortAlg(a, p + 1, r); //RIGHT SUBARRAY
        }
    }

    public int partition(int[] arr, int l , int r) {
        int pivot = r; //last element as pivot
        
        int i = 0;      //i --> keep track of current element examining
        int j = l - 1;  //j --> keep track of elements less than i
        //from beggining of this array until element before pivot
        for (i = l; i < pivot; ++i) { 
            if (arr[i] < arr[pivot]) {
                ++j; //increment index of subarray less than pivot; we are locating one position more of the subarray to put the element less that is less than the pivot
                //swap
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;                
            } 
        }
        //NOW: [<][>=]pivot

        //after the for loop that rearranges the less-than subarray, and automatically makes that elements between j(not included) and i make the greater than or equal to pivot subarray
        //we now have the 2 subarrays ready (not ordered within themselves! but ready as: all less than elements in one place; all greater than or equal elements in another place)
        //we want the pivot to lay in between both subarrays

        //swap pivot with first element of subarray of greater than or equal
        int tmp = arr[j + 1];
        arr[j + 1] = arr[pivot];
        arr[pivot] = tmp;
        //RESULT: [<] pivot [>=]

        return j + 1;
    }
}
