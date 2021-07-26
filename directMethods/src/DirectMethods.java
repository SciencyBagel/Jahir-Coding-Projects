//Jahir Montes 1001635994
public class DirectMethods {
    
    public static double time(int seconds){
        double ret=0;
        int hours = 0, minutes=0,secondsLeft=0;
        
        if(seconds>86400)//86400 seconds in a day
        {
            ret=-1;
        }
        else
        {
            hours = seconds/3600;
            minutes = (seconds%3600)/60;
            secondsLeft = (seconds%3600)%60;
            
            System.out.printf("%d hours %d minutes %d seconds\n",hours,minutes,secondsLeft);
            
            ret = (double)seconds/3600;
        }
        return ret;
    }
    
    public static boolean prime(int num){
        int counter=0;
        boolean ret=false; 
        
        for(int i=1;i<num+1;i++)
        {
            if(num%i==0)
            {
                counter++;//if this happens exactly twice, it is a prime number
            }
        }
        if(counter==2)
        {
            ret=true;
        }
        return ret;
    }
    
    public static int allFactors(int num){
        int counter=0;
        
         for(int i=1;i<num+1;i++)
         {
             if(num%i==0)
             {
                 counter++;
                 System.out.print(" "+i);
             }
         }
         System.out.println();
        return counter;   
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(time(18000));
        System.out.println(prime(13));
        System.out.println(allFactors(100));
    }
    
}
