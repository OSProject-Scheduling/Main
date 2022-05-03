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
	
	public void adding(JLabel ProcessName) {				// 지워야 함

		JLabel SecondLabel = new JLabel(Integer.toString(second));
		SecondLabel.setVerticalAlignment(JLabel.BOTTOM);
		SecondLabel.setHorizontalAlignment(JLabel.CENTER);
		SecondLabel.setPreferredSize(new Dimension(20, 120));
		add(SecondLabel);
		
		second++;
		JLabel label = new JLabel(ProcessName.getText());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(Color.yellow);
		label.setPreferredSize(new Dimension(60, 120));
		add(label);

		if(second == 9) {
			GhanttchartScroll.setBounds(10, 150, 700, 140);
		}

		if(second >= 9) {
			setPreferredSize(new Dimension(722 + (80*(second-8) ), 120));
		}
		revalidate();
		GhanttchartScroll.getHorizontalScrollBar().setValue(GhanttchartScroll.getHorizontalScrollBar().getMaximum());

	}
}
