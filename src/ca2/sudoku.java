package ca2;
import java.util.*;



public class sudoku {

private int[][] numava=new int[3][10],sudo=new int[10][10],numavacount=new int[10][10];
private int[] numbinary={1023,1023-1,1023-2,1023-4,1023-8,1023-16,1023-32,1023-64,1023-128,
		1023-256,1023-512};


public static void main(String[] args) {
	// TODO Auto-generated method stub
	Integer a,b,c;
	a=623;
System.out.println(Integer.toBinaryString(a));




}


public void getonesolution(int[][] input)
{
	
}

public boolean issolution(int[][] numavaliable)
{
	boolean flag=true;
	for(int i=0;i<10;i++)
	{
		if(numavaliable[0][i]!=0)
			flag=false;
	}
	return flag;
}
public void initiate()
{
	int[][] sudos={{0,0,7,0,0,0,0,2,0,0},
			{6,0,0,9,0,0,10,0,0,0},
			{0,5,0,0,3,0,0,4,0,1},
			{0,0,0,7,6,0,9,0,0,10},
			{0,0,0,2,0,7,0,0,1,0},
			{0,4,0,3,0,0,8,0,0,0},
			{0,0,1,0,9,8,0,0,5,0},
			{0,2,0,0,0,0,0,3,0,0},
			{0,0,3,0,5,0,2,0,0,0},
			{1,0,2,0,0,0,0,0,4,0}};
	for(int i=0;i<10;i++)
	{
		numava[0][i]=1023;
		numava[1][i]=1023;
		numava[2][i]=1023;
	}
	sudo=sudos;
	printsudo(sudos);
	getnumava();
	
	
	debug debugg=new debug(true);
	debugg.start();
	numavacount();
	System.out.println();
	

	debugg.stop();
	
}
public void Minnunavacount(int[][] sudos)
{
	int min=99;
	
}
public void numavacount()

{	
	int row,column,block,min=99,minlocation=-1;
	for(int i=0;i<10;i++)
		{for(int j=0;j<10;j++)
			{
			if(sudo[i][j]==0)
			{
			row=i;column=j;block=(row/2)*2+column/5;
				int avanum=numava[0][row] & numava[1][column] & numava[2][block];
				numavacount[i][j]=avanum;}
			else
			{
				numavacount[i][j]=0;
			}
			}
		}
	}
public void getnumava()
{int row,column,block;

for(int i=0;i<10;i++)
{
	numava[0][i]=1023;
	numava[1][i]=1023;
	numava[2][i]=1023;
}

	for(int i=0;i<10;i++)
		{for(int j=0;j<10;j++)
			{
			row=i;column=j;block=(row/2)*2+column/5;
			numava[0][row]=numava[0][row]&numbinary[sudo[i][j]];
			numava[1][column]=numava[1][column]&numbinary[sudo[i][j]];
			numava[2][block]=numava[2][block]&numbinary[sudo[i][j]];
			}
		}
		
}
public static void printsudo(int[][] sudo)
{
	for(int i=0;i<10;i++)
		{System.out.println();
		for(int j=0;j<10;j++)
		{
			if(sudo[i][j]==0)
			{
				System.out.print(" x");
			}
			else if(sudo[i][j]==10)
			{
				System.out.print("10");
			}
			else 
			{System.out.print(" "+sudo[i][j]);}
		}
		}
	}
public static int CountOnes(int input)
{
	int sum=0;
	while(input!=0)
	{
		sum+=1;
		input=input & (input - 1);
		//System.out.println(Integer.toBinaryString((a-1) & 1));
		//System.out.println(Integer.toBinaryString(input));
	}
	return sum;
}
public static  Stack<Integer> getCandidates(int input)
{
	Stack<Integer> candidate=new Stack<Integer>();
	int i=0;
	while(input!=0)
	{
		i++;
		if(input%2==1)
		{
			candidate.add(i);
		}
		input=input>>1;
	}
	return candidate;
	
	}
}