package ca2;

import java.util.Stack;

public class selectsudo {
	private int[] numbinary={1023,1023-1,1023-2,1023-4,1023-8,1023-16,1023-32,1023-64,1023-128,
			1023-256,1023-512};
	private int solutioncount=0;
	public Stack<int[][]> solu=new Stack<int[][]>();
	private Stack<Integer> sudomemory=new Stack<Integer>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		debug debugg=new debug(true);
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
		int[][] sudoss={{10,8,7,1,4,9,5,2,6,3},
				{6,0,0,9,0,0,10,0,0,0},
				{0,5,0,0,3,0,0,4,0,1},
				{0,0,0,7,6,0,9,0,0,10},
				{0,0,0,2,0,7,0,0,1,0},
				{0,4,0,3,0,0,8,0,0,0},
				{0,0,1,0,9,8,0,0,5,0},
				{0,2,0,0,0,0,0,3,0,0},
				{0,0,3,0,5,0,2,0,0,0},
				{1,0,2,0,0,0,0,0,4,0}};
		selectsudo a=new selectsudo();
		debugg.start();
		 a.getonesolution(sudoss);
		// printsudo(sudos);
	System.out.println(a.solutioncount+" solutions found");
		debugg.stop();

	}

	public void getonesolution(int[][] sudo)
	{
		int[][] numava=new int[3][10],cellnumava=new int[10][10];
	
		numava=getnumava(sudo);
		
		if(issolution(numava))
		{solutioncount++;solu.push(sudo.clone());printsudo(sudo);
		clearoncell(sudomemory.pop(),sudo);}
		else
		{
		cellnumava=getcellnumava(sudo,numava);
		int minlocation=getminselection(sudo,cellnumava);
		int row=minlocation/10,column=minlocation%10;
		if(minlocation!=100)
		{
		Stack<Integer> numstack=getCandidates(cellnumava[row][column]);
		for(int insertnum:numstack)
		{
			//if(solutioncount>0)
			//	break;
			sudomemory.push(minlocation);
		sudo[row][column]=insertnum;
		//System.out.println("Now insert "+insertnum+"to row"+(row+1)+" column"+(column+1));
		
		//printsudo(sudo);
	//	int[][] clone=new int[10][10];
		//clone=sudo.clone();
		getonesolution(sudo);
		}
		if(!sudomemory.isEmpty())
		clearoncell(sudomemory.pop(),sudo);
	
		}
		else {clearoncell(sudomemory.pop(),sudo);
	}
		}
	}
	private void clearoncell(int location,int[][] sudos)
	{
		int row=location/10,column=location%10;
		sudos[row][column]=0;
		
	}

	private int getminselection(int[][] sudo,int[][] cellnumava)
	{
		int minlocation=100,min=99;
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				int row,column,block;
				row=i;column=j;block=(row/2)*2+column/5;
				int selection=CountOnes(cellnumava[i][j]);
				if(selection==0) continue;
				if(selection==0 && sudo[i][j]==0)
					return 100;
				if(min>selection)
				{
					min=selection;
					minlocation=i*10+j;
				}
				
			}
		}
		return minlocation;
	}
	
	private int[][] getcellnumava(int[][] sudo,int[][] numava)
	{
		int row,column,block;
		int[][] numavacount=new int[10][10];
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
		return numavacount;
	}
	private boolean issolution(int[][] numavaliable)
	{
		boolean flag=true;
		for(int i=0;i<10;i++)
		{
			if(numavaliable[0][i]!=0)
				flag=false;
		}
		return flag;
	}
	
	private int[][] getnumava(int[][] sudo)
	{int row,column,block;
	int[][] numava=new int[3][10];

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
			return numava;
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
