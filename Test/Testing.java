package Test;

public class Testing {

    public static void main(String[] args) {
        
        int x = 5;
        int y = 6;
        int z;

        z = x;
        x = y;
        y = z;

        System.out.println(x + " " + y);
        

    }

    public static int divide(int a, int b) throws ArithmeticException {

        if (b == 0 ) {
            throw new ArithmeticException("You have input a zero and is not divisable");
        }
        return a/b;
    }
}