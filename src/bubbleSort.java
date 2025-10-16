public class bubbleSort {
    private static int[] a;

    public bubbleSort() {
        this.a = new int[]{2, 7, 4, 1, 5, 3};
    }

    public int[] bubbleSortAlgorithm() {
        for (int j = this.a.length-1; j > 0; j--) { //a[0] will be sorted at last iteration
            for (int i = 0; i < j; i++) { 
                if (this.a[i] > this.a[i+1]) { //if first element is > second element
                    //swap
                    int tmp = this.a[i];
                    this.a[i] = this.a[i+1];
                    this.a[i+1] = tmp;
                }
            }
        }
        return this.a;
    }
    
    
}
