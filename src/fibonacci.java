public class fibonacci {
    private int i;
    private int j;
    private int times;
    private static int timesActual = 1;

    public fibonacci(int times) {
        this.i = 0;
        this.j = 1;
        this.times = times;
    }

    public static void main(String[] args) {
        fibonacci fibonacci = new fibonacci(18);
        fibonacci.fibonacci();
        System.out.println("\nFIB2\n");
        timesActual = 1;
        fibonacci.fibonacci2(0, 1);

        System.out.println(fibonacci.fibFunction(10));
    }


    private void fibonacci() {
        if (this.timesActual <= this.times) {
            System.out.println("i: " + this.i + "; j: " + this.j + "\n");
            int tmp = this.i;
            this.i = this.j;
            this.j = tmp + this.j;
            System.out.println("res: " + this.j + "\n");
            this.timesActual++;
            fibonacci();
        }
    }

    private void fibonacci2(int i, int j) {
        if (this.timesActual <= this.times) {
            System.out.println("i: " + i + "; j: " + j + "\n");
            int tmp = i;
            i = j;
            j = tmp + j;
            System.out.println("res: " + j + "\n");
            this.timesActual++;
            fibonacci2(i, j);
        }
    }

    private int fibFunction(int n) {
        if (n <= 1) {
            return n;
        }
        return fibFunction(n - 1) + fibFunction(n - 2);
    }

}
