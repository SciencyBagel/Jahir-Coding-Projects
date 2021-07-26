//Jahir Montes 1001635994
import java.util.Scanner;
public class FinalGrade {
    
    public static int[] enterInput(String message, int size){
        System.out.println(message);
        Scanner in = new Scanner(System.in);
        
        int [] info = new int[size];
        
        for(int i =0;i<info.length;i++)
        {
            System.out.printf("Enter grade %d\n",i+1);
            info[i]=in.nextInt();
        }
        
        return info;
    }
    
    public static double getAvg(int[] a){
        int total =0;
        
        for(int i =0;i<a.length;i++)
        {
            total = total+a[i];
        }
        
        double average = (double)total/a.length;
        
        return average;
        
    }
    
    public static double getWeightedGrade(int percent, double grade){
        
        double decimal = (double)percent/100;
        
        return grade*decimal;
        
    }
    
    public static void letterGrade(double grade){
        if(grade>=90)
        {
            System.out.println("You made an A!");
        }
        else if(grade<90&&grade>=80)
        {
            System.out.println("You made a B!");
        }
        else if(grade<80&&grade>=70)
        {
            System.out.println("You made a C!");
        }
        else
        {
            System.out.println("You made an F!");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("***CSE 1310 grade calculator!***\n");
        
        int[] testGrades = enterInput("Please enter test grades\n",3);
        
        int[] homeworkGrades = enterInput("\nPlease enter HW grades\n",8);
        
        int[] quizGrades = enterInput("\nPlease enter quiz grades\n",5);
        
        int[] finalExam = enterInput("\nPlease enter final exam grade\n",1);
        
        double homeworkAvg = getAvg(homeworkGrades);
        
        double quizAvg = getAvg(quizGrades);
        
        double finalAvg = getAvg(finalExam);
        
        double finalGrade = getWeightedGrade(15,homeworkAvg)+getWeightedGrade(20,quizAvg)+getWeightedGrade(10,(double)testGrades[0])+getWeightedGrade(12,(double)testGrades[1])
                +getWeightedGrade(13,(double)testGrades[2])+getWeightedGrade(30,finalAvg);
        System.out.printf("Total grade is: %.2f\n",finalGrade);
        letterGrade(finalGrade);
    }
    
}
