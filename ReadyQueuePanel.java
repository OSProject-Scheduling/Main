import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ReadyQueuePanel extends JPanel{
	
	JScrollPane ReadyQueueScrollBar;
	JLabel ReadyQueueLabel;
	int PrevProcessList;
	
	public ReadyQueuePanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

	}
	public void ScrollSetting(JScrollPane ReadyQueueScrollbar) {
		this.ReadyQueueScrollBar = ReadyQueueScrollbar;
	}
	
	public void create_form(LinkedList<Process> ProcessList) {
		removeAll();
		repaint();
		if(ProcessList.size()>8) {
			ReadyQueueScrollBar.setBounds(10, 30, 700, 100);
			setPreferredSize(new Dimension(621+80*(ProcessList.size()-8), getHeight()));
			ReadyQueueScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		else {
			ReadyQueueScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			ReadyQueueScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			ReadyQueueScrollBar.setBounds(10, 30, 700, 83);
		}
		if (ProcessList.size() > 0) {
			for (int i = 0; i < ProcessList.size(); i++) {
				JLabel space = new JLabel();
				JLabel label = new JLabel(ProcessList.get(i).Name);
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setOpaque(true);
				label.setBackground(ChooseColor(ProcessList.get(i).Row));
				label.setForeground(Color.WHITE);
				label.setPreferredSize(new Dimension(60, 80));
				space.setPreferredSize(new Dimension(20,80));
				add(label);
				if(i <= ProcessList.size()-2) add(space);
			}
			
			revalidate();
			if(PrevProcessList > ProcessList.size()) ReadyQueueScrollBar.getHorizontalScrollBar().setValue(0);
			else ReadyQueueScrollBar.getHorizontalScrollBar().setValue(ReadyQueueScrollBar.getHorizontalScrollBar().getMaximum());
		}
		else {
			removeAll();
		}
		
		PrevProcessList = ProcessList.size();
	}
	public Color ChooseColor(int row) {
		switch(row + 1){
			case 1:
				return Color.BLUE;
			case 2:
				return Color.RED;
			case 3:
				return Color.GRAY;
			case 4:
				return Color.GREEN;
			case 5:
				return Color.MAGENTA;
			case 6:
				return Color.ORANGE;
			case 7:
				return Color.PINK;
			case 8:
				return Color.YELLOW;
			case 9:
				return Color.CYAN;
			case 10:
				return Color.DARK_GRAY;
			case 11:
				return Color.WHITE;
			case 12:
				return Color.LIGHT_GRAY;
			case 13:
				Color color= new Color(100,100,100);
				return color;
			case 14:
				Color color1 = new Color(210,100,140);
				return color1;
			default:
				Color color2 = new Color(150,150,100);
				return color2;
		}
	}
}
