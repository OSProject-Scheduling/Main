import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame{
	Container contentpane = getContentPane();
	ReadyQueuePanel ReadyQueuepanel = new ReadyQueuePanel();
	JScrollPane ReadyQueueScroll = new JScrollPane(ReadyQueuepanel);
	
	HighReadyQueuePanel HighReadyQueuepanel = new HighReadyQueuePanel();
	JScrollPane HighReadyQueueScroll = new JScrollPane(HighReadyQueuepanel);
	
	MiddleReadyQueuePanel MiddleReadyQueuePanel = new MiddleReadyQueuePanel();
	JScrollPane MiddleReadyQueueScroll = new JScrollPane(MiddleReadyQueuePanel);
	
	LowReadyQueuePanel LowReadyQueuePanel = new LowReadyQueuePanel();
	JScrollPane LowReadyQueueScroll = new JScrollPane(LowReadyQueuePanel);
	
	GhanttChartPanel GhanttChartpanel = new GhanttChartPanel();
	JScrollPane GhanttChartScroll = new JScrollPane(GhanttChartpanel);
	
	InformationPanel informationpanel = new InformationPanel();
	
	public MainFrame() {
		Base();
		GhanttChartpanel.ScrollSetting(GhanttChartScroll);
		ReadyQueuepanel.ScrollSetting(ReadyQueueScroll);
		HighReadyQueuepanel.ScrollSetting(HighReadyQueueScroll);
		MiddleReadyQueuePanel.ScrollSetting(MiddleReadyQueueScroll);
		LowReadyQueuePanel.ScrollSetting(LowReadyQueueScroll);
		
		contentpane.add(new OptionPanel(informationpanel, GhanttChartpanel, ReadyQueuepanel, HighReadyQueuepanel, MiddleReadyQueuePanel, LowReadyQueuePanel));
		ReadyQueueScroll.setBounds(10, 30, 700, 83);
		contentpane.add(ReadyQueueScroll);
		
		HighReadyQueueScroll.setBounds(10,30,220,83);
		contentpane.add(HighReadyQueueScroll);
		
		MiddleReadyQueueScroll.setBounds(250,30,220,83);
		contentpane.add(MiddleReadyQueueScroll);
		
		LowReadyQueueScroll.setBounds(490,30,220,83);
		contentpane.add(LowReadyQueueScroll);
		
		GhanttChartScroll.setBounds(10, 150, 700, 123);
		contentpane.add(GhanttChartScroll);
		
		JLabel ReadyQueueLabel = new JLabel("<ReadyQueue>");
		ReadyQueueLabel.setLocation(10,10);
		ReadyQueueLabel.setBackground(Color.WHITE);
		ReadyQueueLabel.setSize(100,20);
		add(ReadyQueueLabel);
		
		JLabel GhanttChartLabel = new JLabel("<GhanttChart>");
		GhanttChartLabel.setLocation(10,130);
		GhanttChartLabel.setBackground(Color.WHITE);
		GhanttChartLabel.setSize(100,20);
		add(GhanttChartLabel);
		
		JLabel Table = new JLabel("<Table>");
		Table.setLocation(10,290);
		Table.setBackground(Color.WHITE);
		Table.setSize(100,20);
		add(Table);

		//btn.addActionListener(new practice());
		
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
