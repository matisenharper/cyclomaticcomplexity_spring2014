package test;

//seu02.java student Name: Hebah Hajar
//Goals: Learn and use the script command to capture and print terminal session/ console output. Design and write a calculator program for doubles.
//September 4th, 2012
//*****************************************************
import java.util.Scanner;
import java.math.*;


public class test2_ROCHA
{
    public static void main (String[] args)
    {
	Scanner cscan = new Scanner (System.in);
	    double num1=0;
	double num2=0;
	double sum=0;
	double dif=0;
	double pro=0;
	double div=0;
	double squ=0;
	System.out.println("Please enter the first number: ");
	    num1=cscan.nextDouble();
	    System.out.println("please enter the second number (not zero): ");
	num2=cscan.nextDouble();
	System.out.println("You entered: " + num1 + " and " + num2);
	sum=num1+num2;
	System.out.println("sum is: " + sum );
	dif=num1-num2;
	System.out.println("dif is: " + dif);
	pro=num1*num2;
	System.out.println("pro is: " + pro);
	if (num1==0)
	    {
		System.out.println("Can't divied by Zero");
	    }
	else
	    {
		div=num1/num2;
		System.out.println("div is: " + div);
	    }
	if (num2<0)
	    {
		System.out.println("Can't take the squar root of a nagetive number");
	    }
	else
	    {
		squ=Math.sqrt(num2);
		System.out.println("squ is: " + squ);
	    }

	return;
    }
}

