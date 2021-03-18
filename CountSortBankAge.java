import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class CountSortBankAge {  
static int c=0;  
void countSort(Integer arr[]) 
    { 
        int n = arr.length; 
  
        // The output character array that will have sorted arr 
        Integer output[] = new Integer[n]; 
  
        // Create a count array to store count of inidividul 
        // characters and initialize count array as 0 
        int count[] = new int[256]; 
        for (int i = 0; i < 256; ++i) 
		{
			c++;
			count[i] = 0; 
		}
  
        // store count of each character 
        for (int i = 0; i < n; ++i) 
		{
			c++;
            ++count[arr[i]]; 
		}
  
        // Change count[i] so that count[i] now contains actual 
        // position of this character in output array 
        for (int i = 1; i <= 255; ++i) 
		{
			c++;
            count[i] += count[i - 1]; 
		}
  
        // Build the output character array 
        // To make it stable we are operating in reverse order. 
        for (int i = n - 1; i >= 0; i--) { 
		c++;
            output[count[arr[i]] - 1] = arr[i]; 
            --count[arr[i]]; 
        } 
  
        // Copy the output array to arr, so that arr now 
        // contains sorted characters 
        for (int i = 0; i < n; ++i) 
            arr[i] = output[i]; 
    } 
	


    public static void main(String[] args) throws NumberFormatException, IOException {
        CountSortBankAge pb = new CountSortBankAge();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\punit\\Desktop\\Assignment\\bankage.csv"));
            List<Integer> lines = new ArrayList<Integer>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Integer.parseInt(line));
            }
            br.close();
            Integer[] inputArray = lines.toArray(new Integer[lines.size()]);
			 Date past = new Date();
            pb.countSort(inputArray);
			
      
          // for (Integer i : inputArray) 
		  //{  System.out.println(i);
				
           // }
			System.out.println("comparison"+c);
			Date future = new Date();
			System.out.println("Time (milliseconds)"+" "+"after sorting = " + (future.getTime() - past.getTime()));
        } catch (IOException ie) {
            System.out.print(ie.getMessage());
        }

    }
}