//Jahir Montes 1001635994
import java.util.*;
import java.io.*;

public class FilePractice {
    
    public static String [][] genFile(){
       Scanner in = new Scanner(System.in);
       
        //Create an ArrayList of Strings from the file name entered
        ArrayList<String> allLines = null;
        
        while(allLines==null)
        {
            System.out.println("Enter file name:");
            String fileName =in.nextLine(); 
            allLines = readFile(fileName);
        }
        
        //create a 2D array that will hold all the information
        String[][] result = new String[allLines.size()][];
        
        //this loop should run and capture all of the data
        for(int i = 0;i<allLines.size();i++)
        {
            String line = allLines.get(i);
            result[i] = line.split(",");
        }
        
        return result;
    }
    
    public static ArrayList<String> readFile(String fileName){
        File temp = new File(fileName);
        
        //create a scanner object to read in our file
        Scanner input_file;
        
        try
        {
            input_file = new Scanner(temp);
        }
        catch(Exception e)
        {
            System.out.printf("Failed to open file %s\n\n",fileName);
            return null;
        }
        
        ArrayList<String> result = new ArrayList<>();
        
        while(input_file.hasNextLine())
        {
            String line = input_file.nextLine();
            result.add(line);
        }
        input_file.close(); //close connection to file
        return result;
        
        
    }

    public static void main(String[] args) {
        //print out the second row with info in the file
        String [][] g=genFile();
        
        System.out.println("\nSecond row:");
        
        //print out the second row with the info in the file
        for(int i = 0;i<g[2].length;i++)
        {
            System.out.printf("%s ",g[2][i]);
        }
        for(int i = 0; i<g.length;i++)
        {
            if(g[i][0].contains("Fri"))
            {
                System.out.printf("\n\nDate: "+g[i][0]);
            }
        }
        System.out.println("");
        
    }
    
}
