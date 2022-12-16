package tool;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataHandler {

	private static HashMap<Integer, String> FVPoems;
	private static HashMap<Integer, String> JPoems;
	private static HashMap<Integer, String> PPoems;
	
	public String FV = "Franklin Variorum 1998";
	public String JP = "Johnson Poems 1955";
	public String P = "Poems 1890";
	
	public DataHandler() {
		getData();
	}
	
	// Retrieves Poem String
	public String getPoem(String collection, int n) {
		if (collection.equals(FV)) {
			String poem = FVPoems.get(n);
			if (poem != null) {
				return poem;
			} else {
				return "Poem does not exist in database";
			}
		} else if (collection.equals(JP)) {
			String poem = JPoems.get(n);
			if (poem != null) {
				return poem;
			} else {
				return "Poem does not exist in database";
			}
		} else if (collection.equals(P)) {
				String poem = PPoems.get(n);
				if (poem != null) {
					return poem;
				} else {
					return "Poem does not exist in database";
				}
			}
		else {
			return "Collection does not exist in database";
		}
	}
	
	// Setup Method Populates HashMaps
	public static void getData() {
		FVPoems = readPoems("FV1998");
		JPoems = readPoems("JP1955");
		PPoems = readPoems("P1890");
	}
	
	private static HashMap<Integer, String> readPoems(String fileName) {
	    HashMap<Integer, String> poems = new HashMap<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	      String line;
	      int poemNumber = 0;
	      StringBuilder poem = new StringBuilder();
	      
	      while ((line = br.readLine()) != null) {
	        if (line.length() != 0 && line.charAt(0) == '*') {
	        	poems.put(poemNumber, poem.toString());
	        	poem = new StringBuilder();
	        	line = br.readLine();
	        	poemNumber = Integer.parseInt(line);
	        } else {
	        	poem.append(line);
	        }
	        poem.append("<br />");
	      }
	      
	      if (poem.length() > 0) {
	        poems.put(poemNumber, poem.toString());
	      }
	    } catch (IOException e) {
	      System.err.println("An error occurred while reading the file: " + e.getMessage());
	    }

	    return poems;
	  }
	
}
