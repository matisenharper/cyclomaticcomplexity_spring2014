package cyclomaticComplexity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class CyclomaticComplexityAnalysis {

	public CyclomaticComplexityAnalysis () throws IOException{
		int count=1;
		String line="";
		ArrayList<String> linesOfCode =new ArrayList<String>();
		File file=new File("./src/tictactoe/TicTacToeImpl_Rocha.java");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while ((line = br.readLine()) != null) 
			{
				linesOfCode.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> tokens = new ArrayList<String>();
		tokens.add("if(");
		tokens.add("for(");
		tokens.add("while(");
		tokens.add("break;");
		br.close();
		for(String line2: linesOfCode){
			for(String token: tokens){
				String patternString=token;
				Pattern pattern = Pattern.compile(patternString);
				Matcher matcher = pattern.matcher(line2);

				count = 1;
				while(matcher.find()) {
					count++;
				}
			}
		}
	System.out.println(count);
	}
}
