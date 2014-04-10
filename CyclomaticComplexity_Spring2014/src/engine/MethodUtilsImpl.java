package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class MethodUtilsImpl  implements MethodUtils{
	
	public MethodUtilsImpl(){
	}
	
	@Override
	public Method[] getMethods(File program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBody(File program, Method method) {
		String s = getText(program);
		return s;
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
