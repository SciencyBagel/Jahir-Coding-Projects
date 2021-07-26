import java.util.*;

public class LanguageConversion {
    //get user input
    public static String[] getUserIn(String prompt){
            Scanner in = new Scanner(System.in);
        
            System.out.println(prompt);
            String[] input = in.nextLine().toLowerCase().split(" ");
            
            
        
            return input;
    }
    //identify the case
    public static int caseIdentify(String[] input){//case 1 is similar to "Ang lalaki ay pogi. 
        //case 2 is similar to "pogi ang lalaki
        
        if(input.length==4)//case 1 found
        {
            return 1;
        }
        else if(input.length==3)//case 2 found
        {
            return 2;
        }
        else//no case found
        {
            return -1;
        }
    }
    //convert case1 to case 2
    public static String[] case1ToCase2(String[] case1){
        String[] case2 = {case1[3],case1[0],case1[1]};
        return case2;
    }
    //convert case 2 to case 1
    public static String[] case2ToCase1(String[] case2){
        String[] case1={case2[1],case2[2],"ay",case2[0]};
        return case1;
    }

    public static void main(String[] args) {
        String[] input=getUserIn("Type in sentance for conversion");
        
        if(caseIdentify(input)==1)//case 1 identified, convert to case 2
        {
            input = case1ToCase2(input);
            for(int i=0;i<input.length;i++)
            {
                System.out.print(input[i]+" ");
            }
        }
        else if(caseIdentify(input)==2)//case 2 identified, convert to case 1
        {
            input = case2ToCase1(input);
            for(int i=0;i<input.length;i++)
            {
                System.out.println(input[i]+" ");
            }
        }
        else
        {
            System.out.println("unkown sentance");
        }
    }
    
}
