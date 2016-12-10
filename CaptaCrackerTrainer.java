import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CaptaCrackerTrainer {
	
	public static void main(String args[]) throws Exception
	{
		for(int q = 0; q < 25; q++)
		{
			BufferedReader br,bro;
			if(q < 10)
			{
				br = new BufferedReader(new InputStreamReader(new FileInputStream("sampleCaptchas/input/input0"+q+".txt")));
			}
			else
			{
				br = new BufferedReader(new InputStreamReader(new FileInputStream("sampleCaptchas/input/input"+q+".txt")));
			}
			String s[] = br.readLine().split(" ");
			int n = Integer.parseInt(s[0]);
			int m = Integer.parseInt(s[1]);
			ArrayList<String> as = new ArrayList<String>();
			HashMap<Integer,ArrayList<Integer>> hs = new HashMap<Integer,ArrayList<Integer>>();
			for(int i = 0; i < n; i++)
			{
				String y[] = br.readLine().split(" ");
				for(int j = 0; j < y.length; j++)
				{
					String t[] = y[j].split(",");
					int sum = 0;
					
					for(int k = 0; k < t.length; k++)
					{
						sum += Integer.parseInt(t[k]);
					}
					if(sum < 150)
					{
						//System.out.println(Arrays.toString(t));
						as.add("("+i+","+j+")");
						if(hs.containsKey(i))
						{
							ArrayList<Integer> ty = hs.get(i);
							ty.add(j);
							hs.put(i, ty);
						}
						else
						{
							ArrayList<Integer> ty = new ArrayList<Integer>();
							ty.add(j);
							hs.put(i, ty);
						}
					}
				}
				
			}
			Iterator<String> it = as.iterator();
			
			while(it.hasNext()){
				System.out.println(it.next());
			}
			
			
			int a[][] = new int[n][m];
			Set<Integer> se = hs.keySet();
			Iterator<Integer> it1 = se.iterator();
			while(it1.hasNext())
			{
				int k = it1.next();
				ArrayList<Integer> ty = hs.get(k);
				Iterator<Integer> in = ty.iterator();
				while(in.hasNext())
				{
					a[k][in.next()] = 1;
				}
			}
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < m; j++)
				{
					System.out.print(a[i][j]);
				}
				System.out.println();
			}
			PrintWriter y = new PrintWriter(new FileOutputStream(new File("train4.txt"),true));
			if(q < 10)
			{
				bro = new BufferedReader(new InputStreamReader(new FileInputStream("sampleCaptchas/output/output0"+q+".txt")));
			}
			else
			{
				bro = new BufferedReader(new InputStreamReader(new FileInputStream("sampleCaptchas/output/output"+q+".txt")));
			} 
			char target[] = bro.readLine().toCharArray();
			int counter = 0;
			int c = 5;
			while(counter != 5)
			{
				int sum = 0;
				StringBuffer bin = new StringBuffer();
				for(int i = 11; i <= 20; i++)
				{
					int start = c;
					for(int j = start; j <= start+8; j++)
					{
						sum += a[i][j];
					}
					if(i == 11 || i == 14 || i == 20)
					{
						for(int j = start; j <= start+8; j++)
						{
							bin.append(Integer.toString(a[i][j]));
						}
					}
					
				}
				System.out.println("The sum is:"+sum);
				System.out.println("The Last string:"+Integer.parseInt(bin.toString(), 2));
				y.print((sum + Integer.parseInt(bin.toString(),2))+ "=" + target[counter]);
				y.print(",");
				c += 9;
				counter++;
			}
			y.close();
		}
	}

}
