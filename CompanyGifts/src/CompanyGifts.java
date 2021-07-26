//Jahir F. Montes 100163994
import java.util.*;

public class CompanyGifts {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String identify;
        boolean exit = true;
        String[] customersArr = new String[7];//all the customers
        String customerInfo;
        boolean leave = true;

        while (exit) {
            System.out.println("***\nEnter the word 'customer' if you are a customer\nor your ID if you are the manager.");
            identify = in.nextLine().toLowerCase();

            if (identify.equals("exit"))//user exits program
            {
                exit = false;
                System.out.println("Bye!");
            } else if (identify.equals("abc 123"))//manager identified
            {
                while (leave) {
                    System.out.println("***\nHello manager. What would you like to do?\nPrint to screen or exit?\n***");
                    String mResponse = in.nextLine().toLowerCase();

                    if (mResponse.equals("exit")) {
                        leave = false;
                    } else if (mResponse.equals("print to screen"))//print all the customer information
                    {
                        if (customersArr[0] == null)//no customers have entered information
                        {
                            System.out.println("Sorry, no customers have entered any information yet.");
                        } else//customers have entered information
                        {
                            System.out.println("CUSTOMERS\n");

                            for (int i = 0; i < 7; i++)//7 iterations since there are 7 spots in customersArr 
                            {
                                if (customersArr[i] == null) {
                                    break;
                                } else {
                                    String[] customerInfoArr = new String[5];
                                    customerInfoArr = customersArr[i].split(" ");
                                    String firstName = customerInfoArr[0];
                                    String lastName = customerInfoArr[1];
                                    String birthDay = customerInfoArr[2];

                                    System.out.println("BIRTHDAY: " + birthDay + "  NAME: " + lastName + "," + firstName);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
