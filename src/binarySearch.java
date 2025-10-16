public class binarySearch {
    public static void main(String[] args) {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int result = binarySearch(array, 64, 0, array.length-1);
        if (result == -1) {
            System.out.println("Target not found\n");
        } else {
            System.out.println("Target found at " + result + ".\n");
        }
    }

    private static int binarySearch(int[] a, int target, int left, int right) {
        if (left <= right) {
            //first calc middle
            int middle = left + (right - left) / 2;
            if (a[middle] == target) {
                return middle;
            } else if (target > a[middle]) { //Right
                return binarySearch(a, target, middle + 1, right); //+1 because middle already checked and wasn't the target
            } else if (target < a[middle]) { //Left
                return binarySearch(a, target, left, middle - 1); //-1 because middle already checked and wasn't the target
            }
        }

        return -1;
    }
}
