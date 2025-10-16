public class insertionSort {
    public void insertionSortAlg(int[] a, int n) {
        //iterate through all array
        for (int i = 0; i < n; ++i) {
            //store current element (value & position)
            int tmp = a[i];
            int j = i;

            while (j > 0 && tmp < a[j - 1]) { // if j <= 0 means it is firs element and doesn't have any left elements to check!
                //switch
                a[j] = a[j - 1];
                //move on to next left 
                --j;
            }
            a[j] = tmp; //place current element into its correct position
        }
    }
}
