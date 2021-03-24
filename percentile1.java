import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Percentile1 {    
static int count=0, countlist=0, s=0, p=0, q=0, tot1=0, tot2=0, tot3=0;
static Double twetyfifthpercentile=0.0, fiftythpercentile=0.0, hundredthpercentile=0.0;
   
    public static void main(String[] args) throws NumberFormatException, IOException {
       // QuickSortBankBalance pb = new QuickSortBankBalance();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\punit\\Desktop\\Assignment\\bankbalance.csv"));
            List<Double> lines = new ArrayList<Double>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Double.parseDouble(line));
				countlist++;
            }
            br.close();
            Double[] inputArray = lines.toArray(new Double[lines.size()]);
			 Date past = new Date();
           int a= countlist*25/100;
		   int b=countlist*50/100;
		int c=countlist*75/100;
		System.out.println(a);
		for(int i=0; i<countlist;i++)
		{
			if(inputArray[i]<inputArray[a])
			{
				//System.out.println(inputArray[i]);
				tot1++;
			}
			if(inputArray[i]<inputArray[b])
			{
				//System.out.println(inputArray[i]);
				tot2++;
			}
			if(inputArray[i]<inputArray[c])
			{
				//System.out.println(inputArray[i]);
				tot3++;
			}
			
		}
		System.out.println("25th percentile is "+ inputArray[a]);
		System.out.println("total records below 25th percentile "+tot1);
		System.out.println("50th percentile is "+ inputArray[b]);
		System.out.println("total records below 50th percentile "+tot2);
		System.out.println("75th percentile is "+ inputArray[c]);
		System.out.println("total records below 75th percentile "+tot3);
		
			
			
      
			Date future = new Date();
			System.out.println("Time (milliseconds)"+" "+"after sorting = " + (future.getTime() - past.getTime()));
        } catch (IOException ie) {
            System.out.print(ie.getMessage());
        }

    }
}