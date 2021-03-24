import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Arrays;

class Pair
{
    public int max, min;
 
    public Pair(int max, int min) {
        this.max = max;
        this.min = min;
    }
}
 
class Main
{
    // Divide and conquer solution to find the minimum and maximum number in an array
    public static void findMinAndMax(Integer[] A, int left, int right, Pair p)
    {
        // if the array contains only one element
 
        if (left == right)            // common comparison
        {
            if (p.max < A[left]) {    // comparison 1
                p.max = A[left];
            }
 
            if (p.min > A[right]) {    // comparison 2
                p.min = A[right];
            }
 
            return;
        }
 
        // if the array contains only two elements
 
        if (right - left == 1)            // common comparison
        {
            if (A[left] < A[right])    // comparison 1
            {
                if (p.min > A[left]) {    // comparison 2
                    p.min = A[left];
                }
 
                if (p.max < A[right]) {    // comparison 3
                    p.max = A[right];
                }
            }
            else {
                if (p.min > A[right]) {    // comparison 2
                    p.min = A[right];
                }
 
                if (p.max < A[left]) {    // comparison 3
                    p.max = A[left];
                }
            }
 
            return;
        }
 
        // find the middle element
        int mid = (left + right) / 2;
 
        // recur for the left subarray
        findMinAndMax(A, left, mid, p);
 
        // recur for the right subarray
        findMinAndMax(A, mid + 1, right, p);
    }
 
    
	public static void main(String[] args) throws NumberFormatException, IOException {
        MinMax pb = new MinMax();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\punit\\Desktop\\Assignment\\bankbalance.csv"));
            List<Integer> lines = new ArrayList<Integer>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Integer.parseInt(line));
				
            }
            br.close();
            Integer[] inputArray = lines.toArray(new Integer[lines.size()]);
			 Date past = new Date();
			 
           
			Pair p = new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE);
        findMinAndMax(inputArray, 0, inputArray.length - 1, p);
 
        System.out.println("The minimum array element is " + p.min);
        System.out.println("The maximum array element is " + p.max);
			
      
       
			Date future = new Date();
			
        } catch (IOException ie) {
            System.out.print(ie.getMessage());
        }

    }
}