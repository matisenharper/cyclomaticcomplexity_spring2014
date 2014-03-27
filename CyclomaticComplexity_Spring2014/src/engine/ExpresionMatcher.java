package engine;

import java.util.regex.Pattern;

public class ExpresionMatcher{
	final static Pattern if_exp = Pattern.compile("if\\s*\\(");
	final static Pattern while_exp = Pattern.compile("while\\s*\\(");
	final static Pattern for_exp = Pattern.compile("for\\s*\\(");
	
	final static Pattern else_exp = Pattern.compile("\\s*else\\s*\\{");
	
	
	public static Pattern[] getPatternExpresions(){
		Pattern[] patterns = new Pattern[4];
		patterns[0]=if_exp;
		patterns[1]=else_exp;
		patterns[2]=while_exp;
		patterns[3]=for_exp;
		return patterns;
	}
	
	//gets the String of a Java code and removes all comments of it
	public static String removeComments(String s){
		s = s.replaceAll("//.*?\\\n", "\n");
    	s = s.replaceAll("/\\*(.*?\\s*?)*?\\*/", "\n");
    	return s;
	}
	
	//Returns the end of parenthesis started in start
	public static int getEndofParenthesis(String code, int start){
		int end = start;
		char[] cs = code.toCharArray();
		for(int i = 1;i>0;end++){
    		if(cs[end] =='('){
    			i++;
    		}else if(cs[end] ==')'){
    			i--;
    		}
    	}
		return end;
	}
	
	//Returns the end of the curly bracket started in start
	public static int getEndofCurly(String code, int start){
		int end = start;
		char[] cs = code.toCharArray();
		for(int i = 1;i>0;end++){
    		if(cs[end] =='{'){
    			i++;
    		}else if(cs[end] =='}'){
    			i--;
    		}
    	}
		return end;
	}
	
	public static int getNextCurly(String code, int start){
		int end = start;
		char[] cs = code.toCharArray();
		for(int i = 1;i>0;end++){
    		if(cs[end] =='{'){
    			i--;
    		}
    	}
		return end;
	}
}
