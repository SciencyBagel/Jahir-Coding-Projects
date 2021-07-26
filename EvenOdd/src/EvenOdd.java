//Jahir Montes 1001635994
import java.util.Scanner;

public class EvenOdd {
    
    public static int getUserInput(String message){
        Scanner in=new Scanner(System.in);
        System.out.println(message);
        int integer = in.nextInt();
        return integer;
    }
    
    public static boolean checkIfEven(int n){
        int remainder=n%2;
        
        if(remainder == 0)//even
        {
            return true;
        }
        else//odd
        {
            return false;
        }
        
    }

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        boolean check = true;
        int input1=0;
        int input2=0;
        
        input1=getUserInput("--Enter an even number: ");
        check = checkIfEven(input1);
        
        if(!check)//if input1 is odd
        {
            check = true;
            while(check)
            {
                input1 = getUserInput("That is not even. Enter an even number.");
                check = !checkIfEven(input1);//if even, boolean is false which breaks the loop
            }
        }
        
        check = true;
        
        input2=getUserInput("--Ok thanks! Now enter an odd number: ");
        check =checkIfEven(input2);//it is even if check is true
        
        if(check)//if it is even, they did not obey the prompt
        {
            check = true;
            while(check)
            {
                input2=getUserInput("That is not odd. Enter an odd number.");
                check =checkIfEven(input2);//if odd this is false which will end loop
            }
        }
        System.out.println("\n **Thanks! Bye!**");
    }
    
}
