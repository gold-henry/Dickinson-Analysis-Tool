package tool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

public class PoemPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	String collection = "Franklin Variorum 1998";
	int poemNum = 479;
	
	String Poem = "Poem not set";
	JTextPane textArea;
	JScrollPane scroll;
	
	String selectedWord = "death";
	
	JSplitPane wrapper;
	
	public String getPoem(String collection, int n) {
		return "<html><div><center>" + Main.dataHandler.getPoem(collection, n) + "</center></div></html>";
	}
	
	public void setSelection() {
		String NewSelectedWord = (String) textArea.getSelectedText();
		if (NewSelectedWord != null && !NewSelectedWord.equals(selectedWord)) {
			selectedWord = NewSelectedWord;
			Main.patternPanel.constructCorrelationList(selectedWord.toLowerCase());
			Main.contextPanel.setDefs(selectedWord.toLowerCase());
		}
	}
	
	public PoemPanel() {
		
		Poem = getPoem(collection, poemNum);
		
		// Text Area
		textArea = new JTextPane();
		textArea.setContentType("text/html"); // let the text pane know this is what you want
		textArea.setEditable(false); // as before
		textArea.setBackground(null); // this is the same as a JLabel
		textArea.setBorder(null); // remove the border
		
		textArea.addMouseListener(new mouseEvent());
		
		textArea.setText(Poem);
		
		// Scroll Panel
		scroll = new JScrollPane (textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setMaximumSize(new Dimension(100, 100));
		
		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setVisible(true);
		
		// Buttons
		JButton prevButton = new JButton("prev");
		JButton nextButton = new JButton("next");
		
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				poemNum --;
				updatePoemText();
				wrapper.setDividerLocation(600);
				Main.frame.revalidate();
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				poemNum ++;
				updatePoemText();
				wrapper.setDividerLocation(600);
				Main.frame.revalidate();
			}
		});
		
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		
		JPanel CSelectorPanel = new JPanel();
		String[] collections = {"Franklin Variorum 1998", "Johnson Poems 1955"};
		JComboBox CSelector = new JComboBox(collections);
		
		CSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String selection = (String)cb.getSelectedItem();
		        String NewCollection = "";
		        int s = 0;
		        if (selection.equals(Main.dataHandler.FV)) {
		        	s = 1;
		        	NewCollection = Main.dataHandler.FV;
		        } else if (selection.equals(Main.dataHandler.JP)) {
		        	s = 0;
		        	NewCollection = Main.dataHandler.JP;
		        }
		        
		        if (collection.equals(NewCollection)) {
		        	return;
		        }
		        
		        collection = NewCollection;
		        NewCollection = null;
		      
		        poemNum = Main.RPN.getRelNum(Main.RPN.nameToNum(collection), poemNum, s);

		        updatePoemText();
			}
		});
		
		buttonPanel.add(CSelector);
		
		setLayout(new BorderLayout());
		
		wrapper = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scroll, buttonPanel);
		wrapper.setDividerLocation(600);
		
		//add(wrapper, BorderLayout.PAGE_START);
		add(scroll, BorderLayout.NORTH);
		add(buttonPanel);
	}
	
	public void updatePoemText() {
		Poem = getPoem(collection, poemNum);
		textArea.setText(Poem);
		Main.frame.revalidate();
	}
	
	class mouseEvent implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setSelection();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
