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

public class CaptaCracker {
	
	public static void main(String args[]) throws Exception
	{
		CaptaCracker co = new CaptaCracker();
		System.out.println(co.test(co.calculate()));
	}
	public HashMap<Integer,String> calculate()
	{
		//Get the train String from training the network with 25 input and outputs covering all 36 unique characters.
		String train = "133366302=E,32702625=G,102297676=Y,102457774=K,3201071=4,32702625=G,133369269=R,32702620=C,65018135=3,133394589=5,31654047=6,31657120=O,133394589=5,102436282=W,12607767=1,15734922=J,31654047=6,31460894=2,133699994=7,32702620=C,102340692=V,100860437=L,66085144=I,31460894=2,32702620=C,31657120=O,12607767=1,133369269=R,133699994=7,31657127=Q,31657120=O,102297676=Y,133718090=T,12783024=A,132320804=D,133181978=Z,133369269=R,102460858=M,31657127=Q,102436000=U,102485435=N,31657119=9,132320804=D,31657127=Q,66257184=S,133181978=Z,32702625=G,15734922=J,66257184=S,65018135=3,32702625=G,133181978=Z,102460858=M,132323878=B,12783024=A,15734922=J,12607767=1,3201071=4,132320804=D,102460858=M,133369252=P,31657127=Q,31657119=9,12783024=A,133366302=E,102340692=V,102436282=W,133181978=Z,132320804=D,31657120=O,102436282=W,32702625=G,66257184=S,133718090=T,133699994=7,102298026=X,102457774=K,102460858=M,66257184=S,31460894=2,12607767=1,132320804=D,31460894=2,102457774=K,132323878=B,31460894=2,12782676=0,132323878=B,102436274=H,31657127=Q,31657120=O,12783024=A,102436274=H,12782676=0,102340692=V,133394589=5,66085144=I,31561888=8,102340692=V,133366302=E,133181978=Z,31657119=9,133699994=7,102460858=M,133366302=E,32702620=C,100860437=L,31654047=6,31657119=9,102340692=V,102436274=H,32702620=C,133366302=E,31657119=9,12607767=1,102436282=W,133366302=E,100860437=L,102298026=X,102340692=V,102436000=U,102436274=H,102340692=V,133890462=F,31657120=O";	
		String t[] = train.split(",");
		HashMap<Integer,String> hs = new HashMap<Integer,String>();
		for(int i = 0; i < t.length; i++)
		{
			String y[] = t[i].split("=");
			if(!hs.containsKey(Integer.parseInt(y[0])))
			{
				hs.put(Integer.parseInt(y[0]),y[1]);
			}
		}
		return hs;
	}
	public String test(HashMap<Integer,String> hs1) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
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
		
		StringBuffer out = new StringBuffer();
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
			int ci = sum + Integer.parseInt(bin.toString(),2);
			out.append(hs1.get(ci));
			c += 9;
			counter++;
		}
		
	return out.toString();
	}

}
