import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class InsertionSortAge {    
static int count=0;
    public void insSort(int arr[],int n)
{
 int s=0,c=0,b=0;
 int i,j,temp;
 b=1;

 for(i=1;i<n;i++)
 {
 b=b+2;
 j=i-1;
 temp=arr[i]; 
7 | P a g e
 b=b+2;
 while(j>=0 && arr[j]>temp)
 {
 arr[j+1]=arr[j];
 j--;
 b=b+2;
 s++;
 c++;
 }
 c++;
 arr[j+1]=temp;
 b++;
 }
 System.out.printf("\nswap: %d\tcomparisions:%d\tbasic computations:%d\n",s,c,b);
}

	


    public static void main(String[] args) throws NumberFormatException, IOException {
        InsertionSortAge pb = new InsertionSortAge();
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
            pb.insSort(inputArray, inputArray.length - 1);
			
      
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