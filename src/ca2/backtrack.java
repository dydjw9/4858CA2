package ca2;

import java.util.*;



public class backtrack {
	
	debug debugg=new debug(true);
	
	
private int[][] numava=new int[3][10],sudo=new int[10][10],cellnumava=new int[10][10];
private int[] numbinary={1023,1023-1,1023-2,1023-4,1023-8,1023-16,1023-32,1023-64,1023-128,
		1023-256,1023-512},cellnumindex=new int[100];
private int currentcell=0;
private Stack<Integer> filledcell=new Stack<Integer>();
public static void main(String[] args) {
	// TODO Auto-generated method stub


backtrack sod=new backtrack();

sod.initiate();
}
public void initiate()
{
	/*int[][] sudos={{0,0,7,0,0,0,0,2,0,0},
			{6,0,0,9,0,0,10,0,0,0},
			{0,5,0,0,3,0,0,4,0,1},
			{0,0,0,7,6,0,9,0,0,10},
			{0,0,0,2,0,7,0,0,1,0},
			{0,4,0,3,0,0,8,0,0,0},
			{0,0,1,0,9,8,0,0,5,0},
			{0,2,0,0,0,0,0,3,0,0},
			{0,0,3,0,5,0,2,0,0,0},
			{1,0,2,0,0,0,0,0,4,0}};*/
	int[][] sudos={{10,8,7,1,4,9,5,2,6,3},
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
	getnumava();              //calculate row column block binary array
	
	
	
	debugg.start();
	numavacount();             //calculate each cell binary array
/*
	while(currentcell<100)
	{
		if(sudo[currentcell/10][currentcell%10]!=0)
		{currentcell++;continue;}
		if(currentcell==0)
		{
			if(getnum(currentcell)==0)
			{System.out.println("nosolution");break;}
			else nextcell();
		}
		else
		{nextcell();}
		
	//System.out.println("Current cell is"+currentcell);
	}
	printsudo(sudo);
*/int indexcounter=0,solutioncounter=0;
boolean nosolution=false;
while(indexcounter<100)
{
	if(cellnumindex[indexcounter]>=10)
	{
		indexcounter++;
	}
	
	while(currentcell<100)
	{
		if(sudo[currentcell/10][currentcell%10]!=0)
		{currentcell++;continue;}
		if(currentcell==0)
		{
			if(getnum(currentcell)==0)
			{nosolution=true;break;}
			else nextcell();
		}
		else
		{nextcell();}
		
	//System.out.println("Current cell is"+currentcell);
	}
	if(!nosolution)
	{
		solutioncounter++;
	printsudo(sudo);
	filledcell.pop();
	sudo[9][9]=0;
	getnumava();
	currentcell=98;
	nextcell();
	}
	else break;
	
}
System.out.println("");
	System.out.println(solutioncounter+" solutions found");
	
	
	debugg.stop();
	
}
public void nextcell()
{
	int cell=currentcell;
	int row=cell/10,column=cell%10,block=(row/2)*2+column/5;
	if(getnum(currentcell)!=0)
	{
		
		sudo[row][column]=getnum(currentcell);
		filledcell.push(currentcell);
		//printsudo(sudo);
		
		numava[0][row]=numava[0][row]&numbinary[sudo[row][column]];
		numava[1][column]=numava[1][column]&numbinary[sudo[row][column]];
		numava[2][block]=numava[2][block]&numbinary[sudo[row][column]];
		cellnumindex[cell]++;
		currentcell++;

	}
	else 
	{previouscell();}
	}
public void previouscell()
{


	do{	cellnumindex[currentcell]=0;
	//if(!filledcell.empty())
		currentcell=filledcell.pop();
	
	sudo[currentcell/10][currentcell%10]=0;
	//printsudo(sudo);
	getnumava();
	
	}while(getnum(currentcell)==0&&currentcell>0);
}
public void Minnunavacount(int[][] sudos)
{
	int min=99;
	
}
public void numavacount()

{	
	int row,column,block;
	for(int i=0;i<10;i++)
		{for(int j=0;j<10;j++)
			{
			cellnumindex[i*10+j]=0;
			if(sudo[i][j]==0)
			{
			row=i;column=j;block=(row/2)*2+column/5;
				int avanum=numava[0][row] & numava[1][column] & numava[2][block];
				cellnumava[i][j]=avanum;}
			else
			{
				cellnumava[i][j]=0;
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
	System.out.println(" ");
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
public int getnum(int cell)
{
	int index=cellnumindex[cell];
	int row=cell/10,column=cell%10,block=(row/2)*2+column/5;
	int binary=numava[0][row] & numava[1][column] & numava[2][block];
	/*
	System.out.println("getnum");
	System.out.println(Integer.toBinaryString(numava[0][row]));
	System.out.println(Integer.toBinaryString(numava[1][column]));
	System.out.println(Integer.toBinaryString(numava[2][block]));
	System.out.println(Integer.toBinaryString(binary));
	*/
	while(index<10)
	{
		if((binary>>index)%2==1)
			{cellnumindex[cell]=index;
			return index+1;}
		else index++; 
	}
	cellnumindex[cell]=index;
	return 0;
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