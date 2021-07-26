//Jahir Montes 10016359974
import java.util.Scanner;

public class Movie {
    
    public static String[] enterMovies(int numOfMovies){
        String[] movies =new String[numOfMovies];
        Scanner in=new Scanner(System.in);
        for(int i=0;i<movies.length;i++)
        {
            System.out.println("***Enter a movie that starts with m ");
            movies[i]=in.nextLine().toLowerCase();
            boolean check=true;
            
            char firstLetter = movies[i].charAt(0);
            
            if(!(firstLetter == 'm'))//if first letter does not start with m
            {
                while(check)
                {
                    System.out.println("--That movie doesn't start with m");
                    System.out.printf("***Enter a movie that starts with m: ");
                    movies[i]=in.nextLine().toLowerCase();
                    
                    firstLetter = movies[i].charAt(0);//first letter of movie
                    
                    if(firstLetter=='m')
                    {
                        check = false;
                    }
                }
            }
        }
        return movies;
    }
    
    public static void movieLength(String[] s,int n){
        int movie;
        
        for(int i=0;i<s.length;i++)
        {
            movie =s[i].length();
            
            if((movie<n)&&(movie%2==0))
            {
                System.out.println(s[i]);
            }
        }
        
    }
    
    public static void main(String[] args) {
        
        
        String[] userIn=enterMovies(3);
        
        System.out.println("Movie titles with an even length less than 5");
        movieLength(userIn,5);
        
        
    }
    
}
