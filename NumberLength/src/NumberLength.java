//Jahir Montes 1001635994
import java.util.Scanner;
public class NumberLength {
//
    public static void main(String[] args) {
        /*Asks a user to enter a number between 4-6 (including 4 and 6). This input should be stored in a
        variable called num. If they enter a number out of range (not 4, 5 or 6), the program should say
        “Number out of range!” and exit.
        -Ask a user to enter a word with at least num (what the user entered before) letters and at most
        10 letters.
        -If they enter a word longer than this, the program should say “Too many letters!” and
        end.
        -If they enter a word shorter than num letters, the program should say “Too few letters!”
        and end.
        -Ask the user to enter another number between 1-3 (including 1 and 3) and store it in a variable
        called num2. You should print out that many letters of the word from the beginning.*/
        Scanner in=new Scanner(System.in);
        //variables
        int num;
        int num2;
        
        
        System.out.println("Please enter a number between 4-6");
        num = in.nextInt();
        
        in.nextLine();//absorb enter
        
        if(num<4||num>6)
        {
            System.out.println("Number out of range!");
                    
        }
        else 
        {
            System.out.printf("Enter a word with at least %d letters or at most 10 letters\n",num);
            String word = in.nextLine();
            int length = word.length();
            if(length>10)
            {
                System.out.println("Too many letters!");   
                return;
            }
            if(length<num)
            {
                System.out.println("Too few letters!");
                return;
            }
            System.out.println("Enter another number between 1-3");
            num2=in.nextInt();
            in.nextLine();
            System.out.println(word.substring(0,num2));
           
        }
    }
    
}
