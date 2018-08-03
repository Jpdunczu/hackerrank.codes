import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
    // 1st thought:: change string to charArray();
    // compare each char of each string, if match found, set that char to *
    // if no match then that char is a delete.
    // repeat for each char in both strings.
    // runtime ~= worst case: the length of str1 times length of str2, squared
    // 2nd thought:: if the char from str 1 is not a match to any chars in str 2, then we automatically
    // know that the chars in str 2 do not match to the chars in str 1.
    // naive solution 2: implement sol 1, but after the initial scan is complete, count the *'s &
    // subtract that from str1.length and str2.length respectively, the sum of the differences is
    // the total number of deletions.
    // new worst case ~= length of str1 * length of str2 * length of str1 or length of str2
    // 3rd thought:: if instead of changing the chars to *'s for a match, instead we kept a running tally
    // then we would not need the 3rd pass over one of the strings, and our worst case is just
    // the length of the strings multplied.
    // 4th thought:: change the chars to * so there are no duplicate counts. 
        
        char[] aStr = a.toCharArray();
        char[] bStr = b.toCharArray();
        int matches = 0;
        for( int i = 0; i < aStr.length; i++) {
            for( int j = 0; j < bStr.length; j++) {
                if( aStr[i] == bStr[j] ) {
                    bStr[j] = '*';
                    matches++;
                    break;
                }
            }
        }
        return (aStr.length - matches) + (bStr.length - matches);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
