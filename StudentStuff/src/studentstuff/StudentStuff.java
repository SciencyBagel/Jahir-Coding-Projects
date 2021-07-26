//Jahir Montes 1001635994
package studentstuff;

import filestuff.FileHandle;
import java.util.Arrays;
import java.util.Scanner;
import filestuff.helpfulStuff;

public class StudentStuff {

    public static void main(String[] args) {
        String[][] studentInfo =FileHandle.genFile();
        
        Scanner in = new Scanner(System.in);
        System.out.println("Press:"
                + "\n0 to print out information associated with student"
                + "\n1 for names of all students for a class\n2 to type in a class and get class average"
                + "\n3 to leave the program");
        int response = in.nextInt();
        in.nextLine();
        
        while(response!=3)
        {
            if(response==0)
            {
                System.out.println("Enter student name:");
                String studentName = in.nextLine();
                //print out information
               
                //grade
                helpfulStuff g1 = new helpfulStuff();
                g1.printGrade(studentInfo, studentName);
                
                //class 
                g1.printClass(studentInfo, studentName);
                
                //final grade
                g1.printClassName(studentInfo, studentName);
            }
            else if(response==1)
            {
                System.out.println("Enter class name:");
                String className = in.nextLine();
                helpfulStuff.fileOutputObj(studentInfo, className);
            }
            else if(response==2)
            {
                System.out.println("Type in a class name or class number to get the overall average of all students that took that class");
                String className = in.nextLine();
                int counter =0;
                int loops=0;
                for(int i=0;i<studentInfo.length;i++)
                {
                    if(studentInfo[i][1].trim().equals(className)||studentInfo[i][2].trim().equals(className))
                    {
                       loops++;
                       counter += Integer.parseInt(studentInfo[i][3].trim());
                    }
                }
                System.out.println("Average for "+className+" is "+(double)counter/loops);
            }
            System.out.println("\nPress:"
                + "\n0 to print out information associated with student"
                + "\n1 for names of all students for a class\n2 to type in a class and get class average"
                + "\n3 to leave the program");
            response=in.nextInt();
            in.nextLine();
        }
        System.out.println("Bye");
    }
    
}
