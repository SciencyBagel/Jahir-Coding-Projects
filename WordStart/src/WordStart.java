//Jahir Montes 1001635994
import java.util.Scanner;
public class WordStart {

  
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        String word;
        String message;
        
        System.out.println("Please enter a word:");
        word = in.nextLine();
        
        if(word.toLowerCase().charAt(0)>='a' && word.toLowerCase().charAt(0)<='z')
        {
            switch(word.toLowerCase().charAt(0))
            {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                message="starts with a vowel."; System.out.println(word+" "+message);break;
                default: message = "starts with a consonant"; System.out.println(word+" "+message);break;   
            }
        }
        else
        {
            System.out.println(word+" does not start with a vowel nor a consonant");
        }
    }
    
}
