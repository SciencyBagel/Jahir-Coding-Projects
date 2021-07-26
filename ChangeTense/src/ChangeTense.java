//Jahir F. Montes 1001635994

import java.util.*;

public class ChangeTense {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean exit = true;
        String[] presentTense = {"eat", "walk", "flies", "dances", "cook", "bake", "speak", "shakes", "leaves", "eats"};
        String[] pastTense = {"ate", "walked", "flew", "danced", "cooked", "baked", "spoke", "shook", "left", "ate"};
        int i;
        String replace;

        while (exit) {
            replace = null;
            System.out.println("Please enter a sentence or q to quit");
            userInput = in.nextLine().toLowerCase();

            if (userInput.equals("q")) {
                exit = false;//making exit = false makes the while loop finish

            } else//user did not exit program
            {
                userInput = userInput.substring(0, userInput.length() - 1);//get rid of period
                String[] words = userInput.split(" "); //split string into array of words

                if (words[2].equals("yesterday"))//past-tense--->present tense
                {
                    //first check for irregulars
                    for (i = 0; i < pastTense.length; i++)//loop will stop once the length of the array is reached
                    {
                        if (words[1].equals(pastTense[i]))//irregular confirmed
                        {
                            replace = presentTense[i];
                            break;
                        }
                    }
                    if (replace == null)//did not find irregular, convert to present tense by removing -ed
                    {//convert to present tense
                        replace = words[1].substring(0, words[1].length() - 2);//cut out -ed from the word
                        System.out.println("Present tense is: " + words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + replace + " today.");
                    } else//found irregular, convert to present tense
                    {
                        if ((words[0].equals("she") || words[0].equals("he")) && (words[1].equals("ate")))//eat and eats are different beats beat
                        {
                            System.out.println("Present tense: " + words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + replace + "s " + "today");
                        } else {
                            System.out.println("Present tense is: " + words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + replace + " today.");
                        }
                    }
                } else if (words[2].equals("today"))//present tense---->past tense
                {
                    //check for irregulars
                    for (i = 0; i < presentTense.length; i++) {
                        replace = null;
                        if (words[1].equals(presentTense[i]))//irregular found
                        {
                            replace = pastTense[i];
                            break;
                        }
                    }//irregular not found, add -ed and print
                    if (replace == null) {
                        replace = words[1] + "ed";//add -ed
                        System.out.println("Past tense is: " + words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + replace + " yesterday.");//conversion
                    } else//irregular found, print
                    {
                        if ((words[0].equals("she") || words[0].equals("he")) && (words[1].equals("eats")))//eat and eats are different beats beat
                        {
                            System.out.println("Past tense: " + words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + replace + " today.");
                        } else {
                            System.out.println("Past tense is: " + words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + replace + " yesterday.");
                        }

                    }
                }
            }

        }
        System.out.println("bye!");
    }
}
