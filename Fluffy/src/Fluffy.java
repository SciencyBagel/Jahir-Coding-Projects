//Jahir 1001635994

import java.util.Scanner;

public class Fluffy
{
	public static void main(String[] args)
	{
		String s = "The big fluffy dog likes kibbles and bits";
		
		//turn the String s into an array of words
		String [] splitS=s.split(" ");
		
		//check if the last word in splitS is bits(boolean value will be true if the last word is bits)
		boolean check=splitS[splitS.length-1].equals("bits");
		
		//check length of splitS array
		int len=splitS.length;
		
		//print out variable len
		System.out.println("Length: "+len);
		
		//output the third word in the splitS array (should be fluffy)
		System.out.println(splitS[2]);
		
		//print out the array splitS
		for(int i=0;i<splitS.length;i++)
		{
			System.out.println(splitS[i]);
		}
	}
}
