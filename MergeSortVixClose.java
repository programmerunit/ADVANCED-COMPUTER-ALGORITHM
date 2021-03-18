import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class MergeSortVixClose {    
static int count=0;
    public void mergeSort(Double[] array, int lo, int n) {
        int low = lo;
        int high = n;
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;
        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);
        int end_low = middle;
        int start_high = middle + 1;
        while ((lo <= end_low) && (start_high <= high)) {
            if (array[low] < array[start_high]) {
                low++;
            } else {
                Double Temp = array[start_high];
                for (int k = start_high - 1; k >= low; k--) {
                    array[k + 1] = array[k];
					 
					 
                }
                array[low] = Temp;
                low++;
                end_low++;
                start_high++;
				count++;
            }
        }
		
    }
	


    public static void main(String[] args) throws NumberFormatException, IOException {
        MergeSortVixClose pb = new MergeSortVixClose();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\punit\\Desktop\\Assignment\\vixclose.csv"));
            List<Double> lines = new ArrayList<Double>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Double.parseDouble(line));
            }
            br.close();
            Double[] inputArray = lines.toArray(new Double[lines.size()]);
			 Date past = new Date();
            pb.mergeSort(inputArray, 0, inputArray.length - 1);
			
      
          /* for (Double i : inputArray) {
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