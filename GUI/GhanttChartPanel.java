package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Scrollbar;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GhanttChartPanel extends JPanel{
	JScrollPane GhanttchartScroll;
	Color color;
	public int second;	// core에 따라 달라짐
	
	public GhanttChartPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	}
	
	public void ScrollSetting(JScrollPane GhanttchartScroll) {
		this.GhanttchartScroll = GhanttchartScroll;
	}
	
	public void adding(JLabel ProcessName ,int row) {				// 지워야 함
		JLabel SecondLabel = new JLabel(Integer.toString(second));
		SecondLabel.setVerticalAlignment(JLabel.BOTTOM);
		SecondLabel.setHorizontalAlignment(JLabel.CENTER);
		SecondLabel.setPreferredSize(new Dimension(20, 110));
		add(SecondLabel);
		
		second++;
		JLabel label = new JLabel(ProcessName.getText());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(ChooseColor(row));
		label.setForeground(Color.white);
		label.setPreferredSize(new Dimension(60, 110));
		add(label);
		
		ForScrollBar();
	}
	
	public void addLastSecond() {
		JLabel SecondLabel = new JLabel(Integer.toString(second));
		SecondLabel.setVerticalAlignment(JLabel.BOTTOM);
		SecondLabel.setHorizontalAlignment(JLabel.CENTER);
		SecondLabel.setPreferredSize(new Dimension(20, 110));
		add(SecondLabel);
		ForScrollBar();
	}
	
	private void ForScrollBar() {
		if(second == 9) {
			GhanttchartScroll.setSize(700, 130);
		}

		if(second >= 9) {
			setPreferredSize(new Dimension(722 + (80*(second-8) ), 110));
		}
		revalidate();
		GhanttchartScroll.getHorizontalScrollBar().setValue(GhanttchartScroll.getHorizontalScrollBar().getMaximum());
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
			default:
				color = new Color(153,51,0);
				return color;
		}
	}
}