//Jahir Montes 1001635994

import java.util.Scanner;

public class MonthsToNumbers {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        
        //variables
        String monthName;
        
        System.out.println("Please enter the name of a month");
        monthName=in.nextLine();
        monthName=monthName.toLowerCase();
        
        switch(monthName)
        {
            case "january": System.out.println("January is the 1st month");break;
            case "february": System.out.println("February is the 2nd month");break;
            case "march": System.out.println("March is the 3rd month");break;
            case "april": System.out.println("April is the 4th month");break;
            case "may": System.out.println("May is the 5th month");break;
            case "june": System.out.println("June is the 6th month");break;
            case "july": System.out.println("July is the 7th month");break;
            case "august": System.out.println("August is the 8th month");break;
            case "september": System.out.println("September is the 9th month");break;
            case "october": System.out.println("October is the 10th month");break;
            case "november": System.out.println("November is the 11th month");break;
            case "december": System.out.println("December is the 12th month");break;
            default: System.out.println("Unknown month: "+monthName);
            
            
        }
        
        
        
        
    }
    
}
