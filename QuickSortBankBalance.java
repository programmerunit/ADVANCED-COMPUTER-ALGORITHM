import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuickSortBankBalance {    
static int count=0;
    int partition(Double arr[], int low, int high) 
    { 
        Double pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                Double temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
				count++;
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        Double temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
		count++;
  
        return i+1; 
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(Double arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
	


    public static void main(String[] args) throws NumberFormatException, IOException {
        QuickSortBankBalance pb = new QuickSortBankBalance();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\punit\\Desktop\\Assignment\\bankbalance.csv"));
            List<Double> lines = new ArrayList<Double>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Double.parseDouble(line));
            }
            br.close();
            Double[] inputArray = lines.toArray(new Double[lines.size()]);
			 Date past = new Date();
            pb.sort(inputArray, 0, inputArray.length - 1);
			
      
          /* for (Integer i : inputArray) {
                System.out.println(i);
				
            }*/
			System.out.println("swaps"+count);
			Date future = new Date();
			System.out.println("Time (milliseconds)"+" "+"after sorting = " + (future.getTime() - past.getTime()));
        } catch (IOException ie) {
            System.out.print(ie.getMessage());
        }

    }
}