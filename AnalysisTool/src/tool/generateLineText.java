package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class generateLineText {
	
	private static String fileName = "dickinson.json";
	
	public static void generate() {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("textData.txt"));
			
			String line;
			
			while((line = br.readLine()) != null) {
				if (line.startsWith("    \"lines\": [")) {
					line = br.readLine();
					while(!line.startsWith("    ],")) {
						line = line.substring(7).substring(0, line.length()-9);
						System.out.println(line);
						bw.write(line + "\n");
						line = br.readLine();
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
