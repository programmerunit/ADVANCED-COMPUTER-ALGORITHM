import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuickSortBankAge {    
static int count=0, swaps=0;
    int partition(Integer arr[], int low, int high) 
    { 
        Integer pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
	count++;
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
		count++;
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
				swaps++;
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
		swaps++;
  
        return i+1; 
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(Integer arr[], int low, int high) 
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
        QuickSortBankAge pb = new QuickSortBankAge();
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
            pb.sort(inputArray, 0, inputArray.length - 1);
			
      
          /* for (Integer i : inputArray) {
                System.out.println(i);
				
            }*/
			System.out.println("swaps: "+swaps+" comparison: "+ count);
			Date future = new Date();
			System.out.println("Time (milliseconds)"+" "+"after sorting = " + (future.getTime() - past.getTime()));
        } catch (IOException ie) {
            System.out.print(ie.getMessage());
        }

    }
}