//1001635994 
//Jahir Montes
import java.util.*;

public class NumeroDeux {
    
    
    public static int getUserNum(String message){
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        int userNum =in.nextInt();
        return userNum;
    }
    
    public static String getUserString(int n){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a word at of length "+n);
        String input = in.nextLine();
        
        return input;
    }
    
    public static boolean checkNumLength(int a, int check1, int check2){
        if(a>=check1 && a<=check2)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    

    public static void main(String[] args) {
        
        boolean choice = true;
        int userIn=0;
        while(choice)
        {
            userIn=getUserNum("Enter a number between 4-6:");
            choice=checkNumLength(userIn,4,6);
        }
        
        String input=getUserString(userIn);
        choice = true;
        int userIn2=0;
        
        while(choice)
        {
            userIn2=getUserNum("Enter another number (for substring) between 1-3:");
            choice=checkNumLength(userIn2,1,3);
        }
        
        System.out.println(input.substring(0,userIn2));
        
    }
}
