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
		SecondLabel.setPreferredSize(new Dimension(20, 120));
		add(SecondLabel);
		
		second++;
		JLabel label = new JLabel(ProcessName.getText());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(ChooseColor(row));
		label.setForeground(Color.white);
		label.setPreferredSize(new Dimension(60, 120));
		add(label);
		
		ForScrollBar();
	}
	
	public void addLastSecond() {
		JLabel SecondLabel = new JLabel(Integer.toString(second));
		SecondLabel.setVerticalAlignment(JLabel.BOTTOM);
		SecondLabel.setHorizontalAlignment(JLabel.CENTER);
		SecondLabel.setPreferredSize(new Dimension(20, 120));
		add(SecondLabel);
		ForScrollBar();
	}
	
	private void ForScrollBar() {
		if(second == 9) {
			GhanttchartScroll.setBounds(10, 150, 700, 140);
		}

		if(second >= 9) {
			setPreferredSize(new Dimension(722 + (80*(second-8) ), 120));
		}
		revalidate();
		GhanttchartScroll.getHorizontalScrollBar().setValue(GhanttchartScroll.getHorizontalScrollBar().getMaximum());
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
			case 15:
				Color color2 = new Color(150,150,100);
				return color2;
			default:
				Color colorDefault = new Color(10,10,10);
				return colorDefault;
		}
	}
}
