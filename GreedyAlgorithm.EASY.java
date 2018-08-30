import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        /*
            Initial thoughts:
            1. go through add everything up.
            2. go through k times and subtract the smallest from the total
            3. return the result
            
            second thoughs:
            0. go through and count the 1's, subtract k from it.
            1. have an int pointing to the last index
            2. go through the array and find the min value
            3. switch the index with the min value and the last index
            4. move the last index pointer to point to one less
            5. do this (sum of 1's) - k times
            6. add the values from 0 to the last index pointer
            7. return the result
            
        */
        int result = 0;
        int ones = 0;
        int last = contests.length -1;
        int subtractFromResult = 0;
        
        for( int i = 0; i < contests.length; i++ ) {
            //result += contests[i][0];
            if( contests[i][1] == 1 ) {
                ones++;
            }
        }
        
        k = ones - k;
        for( int i = 0; i < k; i++ ) {
            int min = -1;
            int index = 0;
            for( int j = 0; j <= last; j++ ) {
                if( contests[j][1] == 1 && min == -1 ) {
                    min = contests[j][0];
                } else if ( contests[j][1] == 1 && contests[j][0] < min ) {
                    min = contests[j][0];
                    index = j;
                }
            }
            if( min != -1 ) {
                //int temp = contests[index][0];
                //int temp2 = contests[index][1];
                contests[index][0] = contests[last][0];
                contests[index][1] = contests[last][1];
                //contests[last][0] = temp;
                //contests[last][1] = temp2;
            }
            last = last-1;
            subtractFromResult += min;
        }
        
        for( int i = 0; i <= last; i++ ) {
            result += contests[i][0];
        }
        /*
        for( int i = last+1; i < contests.length; i++ ) {
            result -= contests[i][0];
        }
        */
        /*
        This won't work because min will always be the same value.
        for( int j = 0; j < k; j++ ) {
            int min = -1;
            for( int i = 0; i < contests.length; i++ ) {
                if( min == -1 && contests[i][1] == 1 ) {
                    min = contests[i][0];
                } else if ( contests[i][1] == 1 && contests[i][0] < min ) {
                    min = contests[i][0];
                }
                if( min != -1 ) {
                    result -= min;
                }
            }
        }
        */
        
        return result - subtractFromResult;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
