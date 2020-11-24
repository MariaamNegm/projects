package numbers;
import java.util.Scanner;
public class Operations {

	public static void main(String[] args) {
		System.out.println("MATHEMATICAL CALCULATOR ");
		System.out.println("this calculator is to apply any of the operations on two numbers eithe they are whole numbers or decimal ones");
		double num , num1 , num2 , op ; 
		Scanner in = new Scanner(System.in);
		System.out.println("choose one of the operations :");
		System.out.println("1-summation");
		System.out.println("2-subtraction");
		System.out.println("3-multiplcation");
		System.out.println("4-division");
		num=in.nextDouble();
		System.out.println("Enter the first number:");
		num1=in.nextDouble();
		System.out.println("Enter the second number:");
		num2=in.nextDouble();
		if(num==1)
		{
		   op=num1+num2;
		   System.out.println("their summation is :"+ op);
		}
		else if(num==2)
		{ 
			op=num1-num2;
			System.out.println("their subtraction is :"+ op);
		}
		else if(num==3)
		{
			op=num1*num2;
			System.out.println("their multiplcation is :"+ op);
		}
		else if (num==4)
		{
			if(num2==0)
			{
				System.out.println("Error, Run the program again ");
			}
			else
			{
				op=num1/num2;
			    System.out.println("their division is :"+ op);
			}
		}
		else
		{
			  System.out.println("INVALID ENTRY,PLEASE RUN THE PROGRAM AGAIN");
		}

}
}
