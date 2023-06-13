// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 05.20.2023

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        String userPalindrome;
        Boolean checkedPalindrome;
        do {
            System.out.println("Please enter a palindrome: ");

            userPalindrome = myScanner.nextLine().strip();
        } while (userPalindrome.length() == 0);

        userPalindrome = userPalindrome.toLowerCase();
        checkedPalindrome = checkPalindrome(userPalindrome);

        if (!checkedPalindrome) {
            System.out.printf("%s%s%s", "String: ", userPalindrome, ", is not a palindrome \n");
        } else {
            System.out.printf("%s%s%s", "String: ", userPalindrome, ", is a palindrome \n");
        }


        
    }



    private static boolean checkPalindrome(String userPalindrome) {

        LinkedStack<Character> stack = new LinkedStack<>();
        LinkedQueue<Character> queue = new LinkedQueue<>();

        for(int i = 0; i < userPalindrome.length(); i++) {
            stack.push(userPalindrome.charAt(i));
            queue.enqueue(userPalindrome.charAt(i));
        }

        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                return false;
            }
        }

        return true;
    }
}

