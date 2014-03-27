package regularExpresionTests;

import java.io.*;
import java.lang.reflect.Method;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import engine.GraphEngine;
import engine.GraphEngineImpl;

public class RegexTest {

    public static void main(String[] args){
        
    	File file = new File("./src/regularExpresionTests/Test.java");
    	Method method = null;
    	
    	GraphEngine g = new GraphEngineImpl();
    	
    	g.getGraphModel(file,method);
    	
    	//s = s.replaceAll("//.*?\\\n", "\n");
    	//s = s.replaceAll("/\\*(.*?\\s*?)*?\\*/", "\n");
    	
    	
    	
    	/*System.out.print(s);
    	
    	Pattern pattern = 
        Pattern.compile("if\\s*\\(");
    	
    	Matcher matcher = pattern.matcher(s);

        boolean found = false;
        while (matcher.find()) {
        	int end = matcher.end();
        	char[] cs = s.toCharArray();
        	for(int i = 1;i>0;end++){
        		if(cs[end] =='{'){
        			i++;
        		}else if(cs[end] =='}'){
        			i--;
        		}
        	}
        	System.out.println(s.substring(matcher.end(), end-1));
        	System.out.println("I found the text \""+matcher.group()+"\" starting at index "+matcher.start()+" and ending at index "+end);
            found = true;
        }
        if(!found){
            System.out.println("No match found");
        }*/
    }
    
    public static String getText(File file){
    	File f = file;
    	String string = "";
    	try{
            InputStream ips=new FileInputStream(f); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String line;
            while ((line=br.readLine())!=null){
                string+=line+"\n";
            }
            br.close(); 
        }       
        catch (Exception e){
            System.out.println(e.toString());
        }
    	return string;
    }
}