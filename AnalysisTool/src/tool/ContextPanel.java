package tool;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class ContextPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HashMap<String, String> defMap;
	
	private JTextPane defs;
	private JLabel header;
	private JScrollPane scroll;
	
	public ContextPanel() {
		
		defMap = new HashMap<String, String>();
		
		generateDefs();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(450, 800));
		
		
		
		// add the starting label
				header = new JLabel("Definitions (death): ");
				add(header);
				Border border = header.getBorder();
				Border margin = new EmptyBorder(10,10,10,10);
				header.setBorder(new CompoundBorder(border, margin));
		
		// Definitions Pane
		defs = new JTextPane();
		defs.setText("definitions...");
		defs.setBorder(new CompoundBorder(border, margin));
		
		// scroll pane is the main element
		scroll = new JScrollPane(defs);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(450, 800));
		
		add(scroll);
		
		add(new JLabel("definitions sourced from Emily Dickinson Archive Lexicon"));
		
		setDefs("death");
	}
	
	public void setDefs(String word) {
		header.setText("Definitions ("+ Main.poemPanel.selectedWord + "): ");
		defs.setText(defMap.get(word));
	}
	
	public void generateDefs() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("definitions"));
			String line;
			String word;
			StringBuilder def = new StringBuilder();
			// for whole file
			while((line = br.readLine()) != null) {
				if (line.startsWith("*")) {
					line = br.readLine();
					word = new StringTokenizer(def.toString()).nextToken();
					defMap.put(word, def.toString());
					def = new StringBuilder();
				}
				def.append("\n\n" + line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
