import java.util.Scanner;

public class Convert {
	public static int convertString(String S) {
	    int result = 0;
	    for (int i = 0; i < S.length(); i++) {
            result = result * 10 + S.charAt(i) - '0'; //Find the number by taking the difference between ascii characters and add with result multiplied by 10
	    }
	    return result;
	}
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String S=sc.next(); //Getting the string from user
		Convert c=new Convert();
		System.out.println(c.convertString(S));	
	}

}
