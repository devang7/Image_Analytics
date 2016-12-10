import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DayNight {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long d = 0;
        long n = 0;
        while(true){
            
            String t = br.readLine();
            if(t == null)
                break;
            String ti[] = t.split(" ");
            for(int i = 0; i < ti.length; i++){
                
                String y[] = ti[i].split(",");
                int sum = 0;
                for(int j = 0; j < y.length; j++){
                    
                    sum += Integer.parseInt(y[j]);
                    
                }
                if(sum > 240){
                    d++;
                }
                else
                    n++;
            }
        }
        if(d > n){
            System.out.println("day");
        }
        else{
            System.out.println("night");
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}