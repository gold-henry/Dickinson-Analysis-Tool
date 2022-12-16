package tool;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	public static String selectedWord = "none";
	
	public static DataHandler dataHandler;
	public static PoemPanel poemPanel;
	public static ContextPanel contextPanel;
	public static PatternPanel patternPanel;
	
	public static JFrame frame;
	
	public static RelativePoemNums RPN;
	
	public static void main(String[] args) {
		try {
			//generateLineText.generate();
			
			dataHandler = new DataHandler();
			poemPanel = new PoemPanel();
			contextPanel = new ContextPanel();
			patternPanel = new PatternPanel();
			
			patternPanel.constructCorrelationList("death");
			
			RPN = new RelativePoemNums();
			RPN.constructRelList();
			
			setUpPanels();
		} catch (Throwable t) {
		    JOptionPane.showMessageDialog(
		            null, t.getClass().getSimpleName() + ": " + t.getMessage());
		        throw t; // don't suppress Throwable
		}
	}
	
	public static void setUpPanels() {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1800, 1000);
		frame.setVisible(true);
		
		frame.add(patternPanel, BorderLayout.WEST);
		frame.add(contextPanel, BorderLayout.EAST);
		frame.add(poemPanel, BorderLayout.CENTER);
		
		
		frame.revalidate();

	}
}
