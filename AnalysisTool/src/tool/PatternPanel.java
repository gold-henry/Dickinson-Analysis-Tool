package tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class PatternPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JScrollPane scroll;
	private JPanel itemContainer;
	
	private String sortBy = "Correlation";
	
	private String selectedWord;
	
	public PatternPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// itemContainer contains a list of elements
		itemContainer = new JPanel();
		itemContainer.setLayout(new BoxLayout(itemContainer, BoxLayout.PAGE_AXIS));
		itemContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		// scroll pane is the main element
		scroll = new JScrollPane(itemContainer);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(450, 800));
		
		// add the starting label
		JLabel header = new JLabel("Word Correlations");
		itemContainer.add(header);
		Border border = header.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		header.setBorder(new CompoundBorder(border, margin));
		
		add(scroll);
		
		String[] sortTypes = {"Correlation", "Significance"};
		JComboBox selector = new JComboBox(sortTypes);
		selector.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String selection = (String)cb.getSelectedItem();
				sortBy = selection;
				constructCorrelationList(selectedWord);
			}
			
		});
		
		JPanel div = new JPanel();
		div.add(new JLabel("Sort by: "));
		div.add(selector);
		
		add(div);
	}
	
	// called by mouse listener in poem panel
	public void constructCorrelationList(String word) {
		
		selectedWord = word;
		
		itemContainer.removeAll();
		JLabel header = new JLabel("Word Correlations (" + selectedWord + "): ");
		itemContainer.add(header);
		Border border = header.getBorder();
		Border margin = new EmptyBorder(10,10,10,10);
		header.setBorder(new CompoundBorder(border, margin));
		
		ArrayList<CorrData> dataList = getData(word);
		
		// sorting
		
		if (sortBy.equals("Correlation")) {
			dataList.sort(new Comparator<CorrData>() {
				public int compare(CorrData o1, CorrData o2) {
					return (int)(o2.correlation * 100) - (int)(o1.correlation * 100);
				}
			});
		} else if (sortBy.equals("Significance")) {
			dataList.sort(new Comparator<CorrData>() {
				public int compare(CorrData o1, CorrData o2) {
					return (int)(o1.significance * 100) - (int)(o2.significance * 100);
				}
			});
		}
		
		JLabel l = new JLabel();
		for (CorrData d : dataList) {
			l = new JLabel(" | " + d.wordPair + " | Correlation: " + d.correlation + " | Significance: " + d.significance);
			l.setBorder(new CompoundBorder(border, margin));
			l.setOpaque(true);
			
			l.setBackground(computeColor(d.correlation, d.significance));
			
			itemContainer.add(l);
		}
	}
	
	private Color computeColor(float corr, float sig) {
		
		// corr controls color, closer to 1 is green, closer to 0 is yellow
		// sig controls alpha
		
		int r = 0;
		int g = 255;
		
		if (corr < 0.5) {
			r = 255;
		}
		
		if (corr < 0) {
			g = 0;
		}
		
		int a = 255 - (int)(sig * 255);
		
		return new Color((int)r, (int)g, 0, 255 - (int)(sig * 200));
	}
	
	private ArrayList<CorrData> getData(String word) {
		HashSet<CorrData> corrSet = new HashSet<CorrData>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("correlationsBetter"));
			String line;
			StringTokenizer t;
			String word1;
			String word2;
			float corr;
			float sig;
			while((line = br.readLine()) != null) {
				// only care about line if it contains the specified word
				if (line.contains(word)) {
					t = new StringTokenizer(line);
					word1 = t.nextToken();
					word2 = t.nextToken();
					corr = Float.parseFloat(t.nextToken());
					sig = Float.parseFloat(t.nextToken());
					corrSet.add(new CorrData(word1, word2, corr, sig));
					//System.out.println("word1: "+word1+" word2: "+word2+" corr: "+corr+" sig: "+sig); // debugging
				}
			}
			return new ArrayList<CorrData>(corrSet);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private class CorrData {
		HashSet<String> wordPair;
		float correlation;
		float significance;
		public CorrData(String fw, String sw, float c, float s) {
			wordPair = new HashSet<String>(2);
			wordPair.add(fw);
			wordPair.add(sw);
			correlation = c;
			significance = s;
		}
	}
}
