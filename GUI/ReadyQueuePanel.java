package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Scheduling.Process;

public class ReadyQueuePanel extends JPanel{
	
	JScrollPane ReadyQueueScrollBar;
	JLabel ReadyQueueLabel;
	Color color;
	int PrevProcessList;
	int UpperCount = 8;
	int SetWidth = 621;
	
	public ReadyQueuePanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setBackground(Color.WHITE);
	}
	public void ScrollSetting(JScrollPane ReadyQueueScrollbar) {
		this.ReadyQueueScrollBar = ReadyQueueScrollbar;
	}
	
	public void create_form(LinkedList<Process> ProcessList) {
		removeAll();
		repaint();
		if(ReadyQueueScrollBar.getWidth() < 600) {
			UpperCount = 3;
			SetWidth = 222;
		}
		if(ProcessList.size()>UpperCount) {
			ReadyQueueScrollBar.setSize(ReadyQueueScrollBar.getWidth(), 100);
			setPreferredSize(new Dimension(SetWidth+80*(ProcessList.size()-UpperCount), getHeight()));
			ReadyQueueScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		else {
			ReadyQueueScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			ReadyQueueScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			ReadyQueueScrollBar.setSize(ReadyQueueScrollBar.getWidth(), 83);
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
				color = new Color(51,51,153);
				return color;
			case 2:
				color = new Color(153,51,51);
				return color;
			case 3:
				color = new Color(102,102,102);
				return color;
			case 4:
				color = new Color(0,152,0);
				return color;
			case 5:
				color = new Color(102,0,102);
				return color;
			case 6:
				color = new Color(204,51,0);
				return color;
			case 7:
				color = new Color(153,0,153);
				return color;
			case 8:
				color = new Color(204,153,0);
				return color;
			case 9:
				color=  new Color(0,153,153);
				return color;
			case 10:
				color = new Color(51,51,51);
				return color;
			case 11:
				color = new Color(102,51,102);
				return color;
			case 12:
				return Color.LIGHT_GRAY;
			case 13:
				color= new Color(51,0,51);
				return color;
			case 14:
				color = new Color(51,0,102);
				return color;
			case 15:
				color = new Color(0,102,153);
				return color;
			default:
				color = new Color(0,0,0);
				return color;
		}
	}
}
