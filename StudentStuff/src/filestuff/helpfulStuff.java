
package filestuff;
import java.util.*;
import java.io.*;

public class helpfulStuff {
    
    public void printGrade(String [][] g, String n){
        //run loop for the length of 2D array which is "rows"
        for(int i=0;i<g.length;i++)
        {
            //g[i][0] because i changes which is the rows and [0] is constant because all the names are in the first index (column) 
            if(g[i][0].equals(n))
            {
                System.out.println("Grade: "+g[i][3].trim());
            }
        }
    }
    public void printClass(String [][] c, String n){
        for(int i=0;i<c.length;i++)
        {
            if(c[i][0].equals(n))
            {
                System.out.println("Class: "+c[i][1]);//g[i][1] because 1 is the column where the class numbers are
            }
        }
    }
    public void printClassName(String [][] c, String n){
        for(int i=0;i<c.length;i++)
        {
            if(c[i][0].equals(n))
            {
                System.out.println("Class Name: "+c[i][2]);
            }
        }
    }
    
    public static void fileOutputObj(String [][] toFile, String n)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter filename to save to:");
        String filename = in.nextLine();
        PrintWriter out = null;
        
        try{
            out = new PrintWriter(filename);
        }
        catch(FileNotFoundException e)
        {
            System.out.printf("Error: failed to open file %s.\n",filename);
            System.exit(0);
        }
        out.println("Students taking: "+n+"\n");
        for(int i=0;i<toFile.length;i++)
        {
            if(toFile[i][2].trim().equals(n))
            {
                out.println(toFile[i][0]);
            }
        }
        out.close();
        System.out.println("Done.");
        
    }
    
}
