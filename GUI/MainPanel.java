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
	
	JLabel CoreLabel_1 = new JLabel();
	GhanttChartPanel GhanttChartpanel_1 = new GhanttChartPanel();							// GhanttChart
	JScrollPane GhanttChartScroll_1 = new JScrollPane(GhanttChartpanel_1);
	
	JLabel CoreLabel_2 = new JLabel();
	GhanttChartPanel GhanttChartpanel_2 = new GhanttChartPanel();							// GhanttChart
	JScrollPane GhanttChartScroll_2 = new JScrollPane(GhanttChartpanel_2);
	
	JLabel CoreLabel_3 = new JLabel();
	GhanttChartPanel GhanttChartpanel_3 = new GhanttChartPanel();							// GhanttChart
	JScrollPane GhanttChartScroll_3 = new JScrollPane(GhanttChartpanel_3);
	
	JLabel CoreLabel_4 = new JLabel();
	GhanttChartPanel GhanttChartpanel_4 = new GhanttChartPanel();							// GhanttChart
	JScrollPane GhanttChartScroll_4 = new JScrollPane(GhanttChartpanel_4);
	
	InformationPanel informationpanel = new InformationPanel();							// Information Table
	
	OptionPanel optionpanel;
	
	public MainPanel(MainFrame mainframe) {
		Base();																			// 기본 설정(MainFrame 설정)
		GhanttChartpanel_1.ScrollSetting(GhanttChartScroll_1);
		GhanttChartpanel_2.ScrollSetting(GhanttChartScroll_2);
		GhanttChartpanel_3.ScrollSetting(GhanttChartScroll_3);
		GhanttChartpanel_4.ScrollSetting(GhanttChartScroll_4);
		ReadyQueuepanel.ScrollSetting(ReadyQueueScroll);
		HighReadyQueuepanel.ScrollSetting(HighReadyQueueScroll);
		MiddleReadyQueuePanel.ScrollSetting(MiddleReadyQueueScroll);
		LowReadyQueuePanel.ScrollSetting(LowReadyQueueScroll);
		
		optionpanel = new OptionPanel(informationpanel, GhanttChartpanel_1,GhanttChartpanel_2,
				GhanttChartpanel_3,GhanttChartpanel_4,CoreLabel_1, CoreLabel_2, CoreLabel_3, CoreLabel_4,
				ReadyQueuepanel, HighReadyQueuepanel, 
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
		
		GhanttChartScroll_1.setBounds(10, 350, 700, 113);
		add(GhanttChartScroll_1);
		
		GhanttChartScroll_2.setBounds(10, 320, 700, 113);
		add(GhanttChartScroll_2);
		GhanttChartScroll_2.setVisible(false);
		
		GhanttChartScroll_3.setBounds(10, 470, 700, 113);
		add(GhanttChartScroll_3);
		GhanttChartScroll_3.setVisible(false);
		
		GhanttChartScroll_4.setBounds(10, 620, 700, 113);
		add(GhanttChartScroll_4);
		GhanttChartScroll_4.setVisible(false);
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
		
		CoreLabel_1.setBackground(Color.WHITE);
		CoreLabel_1.setSize(100, 20);
		CoreLabel_1.setLocation(10, 330);
		CoreLabel_1.setText("<P Core>");
		add(CoreLabel_1);
		
		CoreLabel_2.setBackground(Color.WHITE);
		CoreLabel_2.setSize(100, 20);
		CoreLabel_2.setLocation(10, 330);
		CoreLabel_2.setText("<P Core>");
		add(CoreLabel_2);
		CoreLabel_2.setVisible(false);
		
		CoreLabel_3.setBackground(Color.WHITE);
		CoreLabel_3.setSize(100, 20);
		CoreLabel_3.setLocation(10, 330);
		CoreLabel_3.setText("<P Core>");
		add(CoreLabel_3);
		CoreLabel_3.setVisible(false);
		
		CoreLabel_4.setBackground(Color.WHITE);
		CoreLabel_4.setSize(100, 20);
		CoreLabel_4.setLocation(10, 330);
		CoreLabel_4.setText("<P Core>");
		add(CoreLabel_4);
		CoreLabel_4.setVisible(false);
		
		
		JLabel Table = new JLabel("<Table>");
		Table.setLocation(1350,290);
		Table.setBackground(Color.WHITE);
		Table.setSize(100,20);
		add(Table);
		
		add(informationpanel);
	}
}
