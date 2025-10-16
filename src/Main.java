import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*ExTwoSum exTwoSum = new ExTwoSum();
        System.out.println(Arrays.toString(exTwoSum.twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(exTwoSum.twoSum2(new int[]{3, 2, 4}, 6)));*/
        //ExLongestSubstring exLongestSubstring = new ExLongestSubstring();
        //exLongestSubstring.lengthOfLongestSubstring("abcabcbb");
        
        bubbleSort bubbleSort = new bubbleSort();
        int [] a = bubbleSort.bubbleSortAlgorithm();
        System.out.println("BubbleSort: \n[");
        for (int item : a) {
            System.out.println(item + ", ");
        }
        System.out.println("]\n");

        mergeSort mergeSort = new mergeSort();
        int[] a1 = new int[]{7, 9, 8, 1, 3, 2};
        mergeSort.mergeSortAlg(a1, 0, a1.length - 1);
        System.out.println("\nMergeSort: \n[");
        for (int item : a1) {
            System.out.println(item + ", ");
        }
        System.out.println("]\n");

        int[] a2 = new int[]{7, 9, 8, 1, 3, 2};
        insertionSort insertionSort = new insertionSort();
        insertionSort.insertionSortAlg(a2, a2.length);
        System.out.println("\nInsertionSort: \n[");
        for (int item : a2) {
            System.out.println(item + ", ");
        }
        System.out.println("]\n");

        int[] a3 = new int[]{7, 9, 8, 1, 3, 2};
        quickSort quickSort = new quickSort();
        quickSort.quickSortAlg(a3, 0, a3.length - 1);
        System.out.println("\nQuickSort: \n[");
        for (int item : a3) {
            System.out.println(item + ", ");
        }
        System.out.println("]\n");

        //loadBalancingAlba loadBalancingAlba = new loadBalancingAlba();
        /*System.out.println(Arrays.toString(loadBalancingAlba.route_requests(4, 5, 
                                                                            new String[]{"CONNECT,conn1,user1,object1", 
                                                                            "CONNECT,conn2,user2,object2", 
                                                                            "CONNECT,conn3,user3,object3", 
                                                                            "CONNECT,conn4,user4,object4", 
                                                                            "CONNECT,conn5,user5,object5", 
                                                                            "CONNECT,conn6,user6,object6", 
                                                                            "CONNECT,conn7,user7,object7", 
                                                                            "CONNECT,conn8,user8,object8", 
                                                                            "CONNECT,conn9,user9,object9", 
                                                                            "CONNECT,conn10,user10,object10"})));
*/
    }
}
