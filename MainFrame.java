import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame{
	Container contentpane = getContentPane();
	ReadyQueuePanel ReadyQueuepanel = new ReadyQueuePanel();
	JScrollPane ReadyQueueScroll = new JScrollPane(ReadyQueuepanel);
	
	GhanttChartPanel GhanttChartpanel = new GhanttChartPanel();
	JScrollPane GhanttChartScroll = new JScrollPane(GhanttChartpanel);
	
	InformationPanel informationpanel = new InformationPanel();
	
	JButton btn = new JButton("practice");
	public MainFrame() {
		Base();
		GhanttChartpanel.ScrollSetting(GhanttChartScroll);
		contentpane.add(new OptionPanel(informationpanel, GhanttChartpanel));
		ReadyQueueScroll.setBounds(10, 10, 700, 100);
		contentpane.add(ReadyQueueScroll);
		
		GhanttChartScroll.setBounds(10, 120, 700, 150);
		contentpane.add(GhanttChartScroll);
		
		btn.setSize(100, 100);
		btn.setLocation(100, 500);
		//btn.addActionListener(new practice());
		contentpane.add(btn);
		
		contentpane.add(informationpanel);
		
		setVisible(true);
		
	}
	
	
	private void Base() {					// Frame창 기본 설정
		setLayout(null);
		setSize(1014, 797);
		setTitle("OSProject");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentpane.setBackground(Color.white);
	}
	
//	private class practice implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			GhanttChartpanel.prac();
//			GhanttChartScroll.revalidate();
//			GhanttChartScroll.getHorizontalScrollBar().setValue(GhanttChartScroll.getHorizontalScrollBar().getMaximum());
//		}
//	}
}
