//Jahir Montes 1001635994
package avgmain;
import java.util.*;
import filestuff.FileHandle;

public class AvgMain {
    
    public static int columnFinder(String[][] info, String columnName){
        int ret = -1;
            for(int i=0; i<info[0].length;i++)
            {
                if(info[0][i].trim().equals(columnName))
                {
                ret = i;
                }
            }            
        return ret;
    }//returns index of header
    
    public static double averager(Integer[] values1,Integer[] values2, int index){//returns average of two arrays
        double sum = 0;
        double average = 0;
        
  
        sum = values1[index] + values2[index];
        
        average = sum/2;
        return average;
    }
    
    public static Integer[] arrayConvert(String [][] info, int column1){
        
        Integer[] ret = new Integer[info.length-1];
        
        for(int i=0;i<info.length-1;i++)//put all info in 1st row
        {
            ret[i] = Integer.parseInt(info[i+1][column1]);
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        String[][] info = FileHandle.genFile();
        boolean check = false;
        String columnName1 = null;
        String columnName2 = null;
        
        int index1 = 0;
        int index2 = 0;
        System.out.println("Enter first column to find");
        columnName1 = in.nextLine();
        while(!check)
        {
            index1 = columnFinder(info, columnName1);//returns -1 if no column found
            if(index1==-1)
            {
                System.out.println("Sorry, we didn't find it");
                check = false;
            }
            else
            {
                check = true;
            }
        }
        
        boolean check2 = false;
        
        System.out.println("Enter second column to find");
        columnName2 = in.nextLine();
        
        while(!check2)
        {
            index2 = columnFinder(info, columnName2);//returns -1 if no column found
            if(index2==-1)
            {
                System.out.println("Sorry, we didn't find it");
                check2 = false;
            }
            else
            {
                check2 = true;
            }
        }
        Integer[] column1 = arrayConvert(info, index1);
        Integer[] column2 = arrayConvert(info, index2);
        System.out.println("");
        
        String [][] toFile = new String[column1.length][1];
        
        System.out.println("**Avg of "+columnName1+" and "+columnName2+"**\n");
        
        for(int i = 0; i<info.length-1;i++)
        {
            System.out.println(info[i+1][0]+": "+averager(column1,column2,i));
            String temp = info[i+1][0]+": "+averager(column1,column2,i);
            toFile[i][0] =temp;
        }
        
        FileHandle.fileOutputObj(toFile);
    }
    
}
