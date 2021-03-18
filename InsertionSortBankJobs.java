import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.*;
import java.io.*;

public class InsertionSortBankJobs {    
static int s=0;
void insSort(String array[], int f){
String temp="";
for(int i=0;i<f;i++){
for(int j=i+1;j<f;j++){
if(array[i].compareToIgnoreCase(array[j])>0){
temp = array[i];
array[i]=array[j];
array[j]=temp;
s++;
}
}
}

}



	


    public static void main(String[] args) throws NumberFormatException, IOException {
        InsertionSortBankJobs pb = new InsertionSortBankJobs();
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\punit\\Desktop\\Assignment\\bankjobs.csv"));
            List<String> lines = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add((line));
            }
            br.close();
            String[] inputArray = lines.toArray(new String[lines.size()]);
			 Date past = new Date();
            pb.insSort(inputArray, inputArray.length);
			System.out.println("swaps:"+" "+s);
      
          /* for (String i : inputArray) {
                System.out.println(i);
				
            }*/
			//System.out.println("swaps"+count);
			Date future = new Date();
			System.out.println("Time (milliseconds)"+" "+"after sorting = " + (future.getTime() - past.getTime()));
        } catch (IOException ie) {
            System.out.print(ie.getMessage());
        }

    }
}