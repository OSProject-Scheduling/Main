package GUI;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainPanel extends JPanel{
	ReadyQueuePanel ReadyQueuepanel = new ReadyQueuePanel();							// ReadyQueue
	JScrollPane ReadyQueueScroll = new JScrollPane(ReadyQueuepanel);
	
	HighReadyQueuePanel HighReadyQueuepanel = new HighReadyQueuePanel();				// HighReadyQueue
	JScrollPane HighReadyQueueScroll = new JScrollPane(HighReadyQueuepanel);
	
	MiddleReadyQueuePanel MiddleReadyQueuePanel = new MiddleReadyQueuePanel();			// MiddleReadyQueue
	JScrollPane MiddleReadyQueueScroll = new JScrollPane(MiddleReadyQueuePanel);	
	
	LowReadyQueuePanel LowReadyQueuePanel = new LowReadyQueuePanel();					// LowReadyQueue
	JScrollPane LowReadyQueueScroll = new JScrollPane(LowReadyQueuePanel);
	
	GhanttChartPanel GhanttChartpanel = new GhanttChartPanel();							// GhanttChart
	JScrollPane GhanttChartScroll = new JScrollPane(GhanttChartpanel);
	
	InformationPanel informationpanel = new InformationPanel();							// Information Table
	
	OptionPanel optionpanel;
	
	public MainPanel(MainFrame mainframe) {
		Base();																			// 기본 설정(MainFrame 설정)
		GhanttChartpanel.ScrollSetting(GhanttChartScroll);
		ReadyQueuepanel.ScrollSetting(ReadyQueueScroll);
		HighReadyQueuepanel.ScrollSetting(HighReadyQueueScroll);
		MiddleReadyQueuePanel.ScrollSetting(MiddleReadyQueueScroll);
		LowReadyQueuePanel.ScrollSetting(LowReadyQueueScroll);
		
		optionpanel = new OptionPanel(informationpanel, GhanttChartpanel, ReadyQueuepanel, HighReadyQueuepanel, 
				MiddleReadyQueuePanel, LowReadyQueuePanel, mainframe);
		add(optionpanel);
		
		ReadyQueueScroll.setBounds(10, 30, 700, 83);
		add(ReadyQueueScroll);
		
		HighReadyQueueScroll.setBounds(10,30,220,83);
		add(HighReadyQueueScroll);
		
		MiddleReadyQueueScroll.setBounds(250,30,220,83);
		add(MiddleReadyQueueScroll);
		
		LowReadyQueueScroll.setBounds(490,30,220,83);
		add(LowReadyQueueScroll);
		
		GhanttChartScroll.setBounds(10, 150, 700, 123);
		add(GhanttChartScroll);
		
		ComponentSetting();																		// 구성요소 설정
	}
	
	
	private void Base() {					// Frame창 기본 설정
		setLayout(null);
		setSize(1514, 797);
		setBackground(Color.white);
	}
	
	private void ComponentSetting() {
		JLabel ReadyQueueLabel = new JLabel("<ReadyQueue>");
		ReadyQueueLabel.setLocation(10,10);
		ReadyQueueLabel.setBackground(Color.WHITE);
		ReadyQueueLabel.setSize(100,20);
		ReadyQueuepanel.ReadyQueueLabel = ReadyQueueLabel;
		add(ReadyQueueLabel);
		
		JLabel HighReadyQueueLabel = new JLabel("<HighReadyQueue>");
		HighReadyQueueLabel.setLocation(10,10);
		HighReadyQueueLabel.setBackground(Color.WHITE);
		HighReadyQueueLabel.setSize(120,20);
		HighReadyQueuepanel.HighReadyQueueLabel = HighReadyQueueLabel;
		add(HighReadyQueueLabel);
		HighReadyQueueLabel.setVisible(false);
		
		JLabel MiddleReadyQueueLabel = new JLabel("<MiddleReadyQueue>");
		MiddleReadyQueueLabel.setLocation(250,10);
		MiddleReadyQueueLabel.setBackground(Color.WHITE);
		MiddleReadyQueueLabel.setSize(130,20);
		MiddleReadyQueuePanel.MiddleReadyQueueLabel = MiddleReadyQueueLabel;
		add(MiddleReadyQueueLabel);
		MiddleReadyQueueLabel.setVisible(false);
		
		JLabel LowReadyQueueLabel = new JLabel("<LowReadyQueue>");
		LowReadyQueueLabel.setLocation(490,10);
		LowReadyQueueLabel.setBackground(Color.WHITE);
		LowReadyQueueLabel.setSize(120,20);
		LowReadyQueuePanel.LowReadyQueueLabel = LowReadyQueueLabel;
		add(LowReadyQueueLabel);
		LowReadyQueueLabel.setVisible(false);
		
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
		
		add(informationpanel);
	}
}
