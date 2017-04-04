import java.util.Scanner;

public class Solution {
	public String licenseKey(String S,int K){
		int s1=0;
		StringBuilder result=new StringBuilder();
        S=S.toUpperCase().replace("-", ""); //Change all the characters to uppercase and remove "-"
		int size=S.length();
		if(size%K==0){
			s1=K;
		}
		else{
			s1=size%K; //If the size of the string does have a remainder, append those much number of characters initially which is shown in the next step
		}
		result.append(S.substring(0,s1));
		while(s1<size) //If s1 is less than length of the string, then append "-" and K number of characters from string and continue this process unitl s1 greater than or equals size of the string
        {
			result.append("-"+S.substring(s1, s1+K));
			s1+=K;
		}
		
		return result.toString();
	}
	
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String S=sc.next(); //Get string from user
		int K=sc.nextInt(); //Get K from user
		Solution s=new Solution();
		System.out.println(s.licenseKey(S, K));	
	}
}

