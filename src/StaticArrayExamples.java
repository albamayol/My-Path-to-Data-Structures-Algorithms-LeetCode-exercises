public class StaticArrayExamples {
    
    // 1. Static array initialization with explicit values
    private static int[] numbers = {1, 2, 3, 4, 5};
    
    // 2. Static array initialization with size only (default values)
    private static int[] emptyNumbers = new int[5]; // All elements will be 0
    
    // 3. Static array initialization with explicit values using new keyword
    private static int[] moreNumbers = new int[]{10, 20, 30, 40, 50};
    
    // 4. Static array of strings
    private static String[] names = {"Alice", "Bob", "Charlie", "Diana"};
    
    // 5. Static array of doubles
    private static double[] prices = {9.99, 19.99, 29.99, 39.99};
    
    // 6. Static array of booleans
    private static boolean[] flags = {true, false, true, false};
    
    // 7. Static array initialization in static block
    private static int[] dynamicArray;
    
    static {
        // Static block - executed when class is loaded
        dynamicArray = new int[10];
        for (int i = 0; i < dynamicArray.length; i++) {
            dynamicArray[i] = i * i; // Fill with squares
        }
    }
    
    // 8. Static array of objects (custom class)
    private static Person[] people = {
        new Person("John", 25),
        new Person("Jane", 30),
        new Person("Bob", 35)
    };
    
    // 9. Static 2D array initialization
    private static int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    
    // 10. Static array using method call
    private static int[] methodArray = createArray();
    
    private static int[] createArray() {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i + 1) * 10;
        }
        return arr;
    }
    
    public static void main(String[] args) {
        // Demonstrate different static array initializations
        System.out.println("=== Static Array Examples ===");
        
        // Print numbers array
        System.out.print("Numbers: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Print empty numbers (shows default values)
        System.out.print("Empty numbers (default values): ");
        for (int num : emptyNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Print more numbers
        System.out.print("More numbers: ");
        for (int num : moreNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Print names
        System.out.print("Names: ");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();
        
        // Print prices
        System.out.print("Prices: ");
        for (double price : prices) {
            System.out.print("$" + price + " ");
        }
        System.out.println();
        
        // Print flags
        System.out.print("Flags: ");
        for (boolean flag : flags) {
            System.out.print(flag + " ");
        }
        System.out.println();
        
        // Print dynamic array (filled in static block)
        System.out.print("Dynamic array (squares): ");
        for (int num : dynamicArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Print people array
        System.out.println("People:");
        for (Person person : people) {
            System.out.println("  " + person);
        }
        
        // Print 2D matrix
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        
        // Print method array
        System.out.print("Method array: ");
        for (int num : methodArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Demonstrate that static arrays are shared across instances
        System.out.println("\n=== Static Array Sharing Demo ===");
        StaticArrayExamples obj1 = new StaticArrayExamples();
        StaticArrayExamples obj2 = new StaticArrayExamples();
        
        // Modify through one object
        numbers[0] = 999;
        
        // Both objects see the same change
        System.out.println("Object 1 sees: " + java.util.Arrays.toString(obj1.numbers));
        System.out.println("Object 2 sees: " + java.util.Arrays.toString(obj2.numbers));
        System.out.println("Class sees: " + java.util.Arrays.toString(numbers));
    }
    
    // Helper class for demonstration
    static class Person {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}

