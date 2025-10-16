public class mergeSort {
    public void mergeSortAlg(int[] a, int l, int r) {
        int mid = l + ((r - l) / 2);
        if (l < r) {
            mergeSortAlg(a, l, mid); //left
            mergeSortAlg(a, mid + 1, r); //right

            merge(a, l, mid, r);
        }
        //return a;
    }
    public static void merge(int[] a, int l, int mid, int r) {
        int p = l, q = mid + 1, k = 0; //p --> walks left side; q --> walks right side; k --> walks temporary array
        int[] arr = new int[(r - l) + 1]; //temporary array

        //MAIN MERGE LOOP
        while (p <= mid && q <= r) { //while both sides have elements, pick smaller from a[p] or a[q] and appends it to arr[]
            if (a[p] <= a[q]) {
                arr[k++] = a[p++]; //arr[k++] takes initial value of k and then increments its value k++
            } else {
                arr[k++] = a[q++];
            }
        }

        //Drain LEFTOVERS from LEFT
        while (p <= mid) {
            arr[k++] = a[p++];
        }

        //Drain LEFTOVERS from RIGHT
        while (q <= r) {
            arr[k++] = a[q++];
        }

        //COPY BACK TO ORIGINAL ARRAY
        for (int i = 0; i < k; ++i) {
            a[l + i] = arr[i];
        }
    }
}
