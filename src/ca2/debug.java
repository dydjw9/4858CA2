package ca2;
import java.util.Scanner;
public class debug {
	boolean flag;
	long start,stop;
	Scanner sc = new Scanner(System.in);
	
	public debug(boolean a)
	{flag=a;
	start=0;
	stop=0;
	}
	
	public void waitc()
	{
		if(flag)
		{sc.nextLine();}
	}
	public void start(){
		if(flag)
		{start=System.nanoTime();}
		}
	public void stop(){
		if(flag)
		{
		stop=System.nanoTime();
		double cost=(stop-start)/1000000;
		start=0;
		stop=0;
		System.out.println("Used Time is "+cost+" ms");}
		
		}
	
	
 public static <T> void print(T variable)
 {
	 
	 String name;
	 name=variable.getClass().getSimpleName();
	 System.out.println(name+" is "+variable);
 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
double asd=123;
//debug.<Integer>print(asd);
debug.print(asd);
	}

}
