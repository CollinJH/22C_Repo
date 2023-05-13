// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 05.13.2023

public class recursiveTriangle {
    public static void main(String[] args) {
        
        createTriangle('*', 10, 1);
    }

    public static void createTriangle(char character, int length, int startingLength) {



        if (length == 0) {
            return;
        } else if (length >= 1) {
            for (int i = 0; i < startingLength; i++) {
                System.out.printf("%s", character);
            }
            System.out.println();
            createTriangle(character, length - 1, startingLength + 1);
        }
        
    }
}