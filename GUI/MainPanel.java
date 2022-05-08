package GUI;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainPanel extends JPanel{
	private ReadyQueuePanel ReadyQueuepanel = new ReadyQueuePanel();							// ReadyQueue
	private JScrollPane ReadyQueueScroll = new JScrollPane(ReadyQueuepanel);
	
	private MFQReadyQueue MFQreadyQueue = new MFQReadyQueue();									// MFQ용 ReadyQueue
	
	private JLabel CoreLabel_1 = new JLabel();
	private GhanttChartPanel GhanttChartpanel_1 = new GhanttChartPanel();							// GhanttChart
	private JScrollPane GhanttChartScroll_1 = new JScrollPane(GhanttChartpanel_1);
	
	private JLabel CoreLabel_2 = new JLabel();
	private GhanttChartPanel GhanttChartpanel_2 = new GhanttChartPanel();							// GhanttChart
	private JScrollPane GhanttChartScroll_2 = new JScrollPane(GhanttChartpanel_2);
	
	private JLabel CoreLabel_3 = new JLabel();
	private GhanttChartPanel GhanttChartpanel_3 = new GhanttChartPanel();							// GhanttChart
	private JScrollPane GhanttChartScroll_3 = new JScrollPane(GhanttChartpanel_3);
	
	private JLabel CoreLabel_4 = new JLabel();
	private GhanttChartPanel GhanttChartpanel_4 = new GhanttChartPanel();							// GhanttChart
	private JScrollPane GhanttChartScroll_4 = new JScrollPane(GhanttChartpanel_4);
	
	private InformationPanel informationpanel = new InformationPanel();							// Information Table
	
	private OptionPanel optionpanel;
	
	public JLabel Elec = new JLabel();
	
	private JLabel imag = new JLabel();
	ImageIcon KoreatechImg = new ImageIcon("image/Koreatech.png");
	private JLabel Student_1 = new JLabel("김성원 2018136015");
	private JLabel Student_2 = new JLabel("정찬영 2018136116");
	private JLabel Student_3 = new JLabel("김문웅 2020136015");
	private JLabel Student_4 = new JLabel("오준혁 2020136082");
	
	public MainPanel(MainFrame mainframe) {
		Base();																			// 기본 설정(MainFrame 설정)
		GhanttChartpanel_1.ScrollSetting(GhanttChartScroll_1);
		GhanttChartpanel_2.ScrollSetting(GhanttChartScroll_2);
		GhanttChartpanel_3.ScrollSetting(GhanttChartScroll_3);
		GhanttChartpanel_4.ScrollSetting(GhanttChartScroll_4);
		ReadyQueuepanel.ScrollSetting(ReadyQueueScroll);
		
		optionpanel = new OptionPanel(informationpanel, GhanttChartpanel_1,GhanttChartpanel_2,
				GhanttChartpanel_3,GhanttChartpanel_4,CoreLabel_1, CoreLabel_2, CoreLabel_3, CoreLabel_4,
				ReadyQueuepanel, MFQreadyQueue,  this, mainframe);
		add(optionpanel);
		
		ReadyQueueScroll.setBounds(10, 30, 700, 83);
		add(ReadyQueueScroll);
		
		add(MFQreadyQueue);
		
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
		
		GhanttChartScroll_1.getViewport().addChangeListener(new ListenAdditionsScrolled(GhanttChartScroll_1));
		GhanttChartScroll_2.getViewport().addChangeListener(new ListenAdditionsScrolled(GhanttChartScroll_2));
		GhanttChartScroll_3.getViewport().addChangeListener(new ListenAdditionsScrolled(GhanttChartScroll_3));
		GhanttChartScroll_4.getViewport().addChangeListener(new ListenAdditionsScrolled(GhanttChartScroll_4));
		
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
		
		JLabel GhanttChartLabel = new JLabel("<GhanttChart>");
		GhanttChartLabel.setLocation(10,130);
		GhanttChartLabel.setBackground(Color.WHITE);
		GhanttChartLabel.setSize(100,20);
		add(GhanttChartLabel);
		
		CoreLabel_1.setBackground(Color.WHITE);
		CoreLabel_1.setSize(100, 20);
		CoreLabel_1.setLocation(10, 330);
		CoreLabel_1.setText("<E Core>");
		add(CoreLabel_1);
		
		CoreLabel_2.setBackground(Color.WHITE);
		CoreLabel_2.setSize(100, 20);
		CoreLabel_2.setLocation(10, 330);
		CoreLabel_2.setText("<E Core>");
		add(CoreLabel_2);
		CoreLabel_2.setVisible(false);
		
		CoreLabel_3.setBackground(Color.WHITE);
		CoreLabel_3.setSize(100, 20);
		CoreLabel_3.setLocation(10, 330);
		CoreLabel_3.setText("<E Core>");
		add(CoreLabel_3);
		CoreLabel_3.setVisible(false);
		
		CoreLabel_4.setBackground(Color.WHITE);
		CoreLabel_4.setSize(100, 20);
		CoreLabel_4.setLocation(10, 330);
		CoreLabel_4.setText("<E Core>");
		add(CoreLabel_4);
		CoreLabel_4.setVisible(false);
		
		
		JLabel Table = new JLabel("<Table>");
		Table.setLocation(730,310);
		Table.setBackground(Color.WHITE);
		Table.setSize(100,20);
		add(Table);
		
		Elec.setText("<총 전력>: ");
		Elec.setLocation(500,130);
		Elec.setBackground(Color.WHITE);
		Elec.setSize(100,30);
		add(Elec);
		
		add(informationpanel);
		
		imag.setIcon(KoreatechImg);
		imag.setSize(150, 200);
		imag.setLocation(1250, 20);
		add(imag);
		
		Student_1.setLocation(1275, 230);
		Student_2.setLocation(1275, 250);
		Student_3.setLocation(1275, 270);
		Student_4.setLocation(1275, 290);
		
		Student_1.setSize(150, 20);
		Student_2.setSize(150, 20);
		Student_3.setSize(150, 20);
		Student_4.setSize(150, 20);
		
		add(Student_1);
		add(Student_2);
		add(Student_3);
		add(Student_4);
	}
	
	private class ListenAdditionsScrolled implements ChangeListener{
		JScrollPane ghanttChartScroll;
		public ListenAdditionsScrolled(JScrollPane ghanttChartScroll) {
			this.ghanttChartScroll = ghanttChartScroll;
		}
	    public void stateChanged(ChangeEvent e){
	    	GhanttChartScroll_1.getHorizontalScrollBar().setValue(ghanttChartScroll.getHorizontalScrollBar().getValue());
	    	GhanttChartScroll_2.getHorizontalScrollBar().setValue(ghanttChartScroll.getHorizontalScrollBar().getValue());
	    	GhanttChartScroll_3.getHorizontalScrollBar().setValue(ghanttChartScroll.getHorizontalScrollBar().getValue());
	    	GhanttChartScroll_4.getHorizontalScrollBar().setValue(ghanttChartScroll.getHorizontalScrollBar().getValue());
	    }
	}
}
