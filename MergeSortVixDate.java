import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat; 
import java.util.*;
import java.io.*;
import java.text.ParseException; 

public class MergeSortVixDate {    
static int s=0;
public static void mergeSort(Date[] a, int from, int to) {
        if (from == to) {
            return;
        }
        int mid = (from + to) / 2;
        // sort the first and the second half
        mergeSort(a, from, mid);
        mergeSort(a, mid + 1, to);
        merge(a, from, mid, to);
    }// end mergeSort
//work

    public static void merge(Date[] a, int from, int mid, int to) {
        int n = to - from + 1;       // size of the range to be merged
        Date[] b = new Date[n];   // merge both halves into a temporary array b
        int i1 = from;               // next element to consider in the first range
        int i2 = mid + 1;            // next element to consider in the second range
        int j = 0;                   // next open position in b

        // as long as neither i1 nor i2 past the end, move the smaller into b
        while (i1 <= mid && i2 <= to) {
            if (a[i1].compareTo(a[i2]) < 0) {
                b[j] = a[i1];
                i1++;
            } else {
                b[j] = a[i2];
                i2++;
            }
            j++;
			s++;
        }

        // note that only one of the two while loops below is executed
        // copy any remaining entries of the first half
        while (i1 <= mid) {
            b[j] = a[i1];
            i1++;
            j++;
			s++;
        }

        // copy any remaining entries of the second half
        while (i2 <= to) {
            b[j] = a[i2];
            i2++;
            j++;
			s++;
        }

        // copy back from the temporary array
        for (j = 0; j < n; j++) {
            a[from + j] = b[j];
        }
    }//end merge


	


    public static void main(String[] args) throws NumberFormatException, IOException {
        MergeSortVixDate pb = new MergeSortVixDate();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\punit\\Desktop\\Assignment\\vixdate.csv"));
            List<Date> lines = new ArrayList<Date>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 

            String line;
            while ((line = br.readLine()) != null) {​​​​ 
                try {​​​​ 
                lines.add(dateFormat.parse(line.trim())); 
                }​​​​ catch (ParseException e) {​​​​ 
                    e.printStackTrace(); 
                }​​​​ 
                }​​​​ 
            br.close();
            Date[] inputArray = lines.toArray(new Date[lines.size()]);
			 //Date past = new Date();
            pb.mergeSort(inputArray, 0, inputArray.length - 1);
			System.out.println("swaps:"+" "+s);
      
          /* for (String i : inputArray) {
                System.out.println(i);
				
            }*/
			//System.out.println("swaps"+count);
			//Date future = new Date();
			//System.out.println("Time (milliseconds)"+" "+"after sorting = " + (future.getTime() - past.getTime()));
        } catch (IOException ie) {
            System.out.print(ie.getMessage());
        }

    }
}