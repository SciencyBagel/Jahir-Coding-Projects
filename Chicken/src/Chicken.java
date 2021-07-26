//Jahir Montes 1001635994
import java.util.Scanner;

public class Chicken {
    
    public static String [] getInput(String message){
        Scanner in=new Scanner(System.in);
        String[] chickenEggInfo= new String[3];
        
        System.out.println(message);
        
        for(int i=0;i<3;i++)
        {
            System.out.printf("Day %d: ",i+1);//day 
            chickenEggInfo[i]=in.nextLine();
        }
        return chickenEggInfo;
    }
    
    public static int totalEggs(String[] s){
        int total=0;
        
        for(int i=0;i<3;i++)
        {
            
            String[] temp=s[i].split("-");
            total+=Integer.parseInt(temp[1]);
        }
        return total;
    }

    public static void main(String[] args) {
        
        String[] farmerOne=getInput("-Info for first farmer:");
        String[] farmerTwo=getInput("-Info for second farmer:");
        
        int numOne=totalEggs(farmerOne);
        int numTwo=totalEggs(farmerTwo);
       
        if(numOne<numTwo)//farmer 2 has more
        {
            System.out.println("Farmer 2 has more eggs.");
        }
        else if(numTwo<numOne)//farmer 1 has more
        {
            System.out.println("Farmer 1 has more eggs");
        }
        else//same number
        {
            System.out.println("They have the same number of eggs");
        }
    }
    
}
