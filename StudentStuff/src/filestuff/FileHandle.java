//Jahir Montes 1001635994
package filestuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandle {
    
    //Converts ArrayList into 2D Array
    public static String[][] genFile(){
        ArrayList<String> lines = null;
        Scanner in = new Scanner(System.in);
        
        while(lines==null)
        {
            System.out.println("Enter file name:");
            String fileName=in.nextLine();
            lines=readFile(fileName);
        }
        String [][] result=new String[lines.size()][];///make 2d array of arraylist size. lines.size() will determine the amount of "rows"
        for(int i =0;i<lines.size();i++)
        {
            String line = lines.get(i);
            result[i]=line.split(",");
        }
        return result;
    }
    
  //converts file information into ArrayList
    public static ArrayList<String> readFile(String filename){
        File temp = new File(filename);//create a file object named temp
        Scanner input_file = null;
        try
        {
            input_file = new Scanner(temp);
        }
        catch(FileNotFoundException e)
        {
            System.out.printf("Failed to open file %s\n\n", filename);
            return null;
        }
        
        ArrayList<String> result = new ArrayList();
        
        while(input_file.hasNextLine())
        {
            String line = input_file.nextLine();
            result.add(line);
        }
        
        input_file.close();
        return result;
    }
    
}


