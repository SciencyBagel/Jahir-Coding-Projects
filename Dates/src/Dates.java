//Jahir Montes 1001635994
import java.util.Scanner;

public class Dates {
    
    public static int getUserInput(String mesage){
        System.out.println(mesage);
        Scanner in=new Scanner(System.in);
        
        return in.nextInt();
    }

    public static String DecToBinary(int userInput){
        String binNum="";//To build up binary nubmer
        
        int dividend = userInput;
        
        while(dividend !=0)
        {
            binNum=buildNum(dividend)+binNum;
            dividend = dividend/2;
        }
        
        return binNum;
    }
    
    public static String buildNum(int num){
        if(num%2==0)
        {
            return "0";
        }
        
        else
        {
            return "1";
        }
    }
    
    public static int BinaryToDec(int userInput){
        String binNum=Integer.toString(userInput);
        
        int numOfDigits=binNum.length();
        
        int answer=0;
        
        for(int i=0;i<numOfDigits;i++)
        {
            char curNum=binNum.charAt((numOfDigits-1)-i);
            
            answer=answer+valueToAdd(curNum,i);
        }
        
        return answer;
    }
    
    public static int valueToAdd(char c, int exp){
        int digit=Character.getNumericValue(c);
        
        double power=(double)exp;
        
        double value=digit*Math.pow(2, power);
        
        return (int)value;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        
        boolean check = true;
        
        while(check)//check is true until user types in quit
        {
            System.out.println("***Enter today's datek month followed by day:(b=binary d=decimal");
            String answer =in.nextLine().toLowerCase();
            
            if(answer.equals("quit"))
            {
                System.out.println("Bye");
                check = false;
            }
            
            String[] temp=answer.split(" ");
            
            if(temp.length!=2)
            {
                System.out.println("Wrong format. Enter again.");
                continue;
            }
            String date = temp[0];
            String format =temp[1];
            
            if(format.equals("d"))//they choose decimal
            {
                String[] dateArr=null;
                if(date.indexOf("/") != -1)
                {
                    dateArr = date.split("/");
                }
                else if(date.indexOf("-") !=-1)
                {
                    dateArr = date.split("-");
                }
                
                System.out.println("Date in binary: "+DecToBinary(Integer.parseInt(dateArr[0]))+"/"+DecToBinary(Integer.parseInt(dateArr[1])));
            }
            else if(format.equals("b"))//they choose binary
            {
                String[] dateArr=null;
                
                if(date.indexOf("/")!=-1)
                {
                    dateArr = date.split("/");
                }
                else if(date.indexOf("-") !=-1)
                {
                    dateArr = date.split("-");
                }
                System.out.println("Date in decimal: "+BinaryToDec(Integer.parseInt(dateArr[0]))+"/"+BinaryToDec(Integer.parseInt(dateArr[1])));
            }
            
        }
        
    }
    
}
