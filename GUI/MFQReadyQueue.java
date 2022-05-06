package GUI;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MFQReadyQueue extends JPanel{
	JLabel HighReadyQueueLabel;
	public ReadyQueuePanel HighReadyQueue = new ReadyQueuePanel();							// HighReadyQueue
	JScrollPane HighReadyQueueScroll = new JScrollPane(HighReadyQueue);
	
	JLabel MiddleReadyQueueLabel = new JLabel();
	public ReadyQueuePanel MiddleReadyQueue = new ReadyQueuePanel();							// MiddleReadyQueue
	JScrollPane MiddleReadyQueueScroll = new JScrollPane(MiddleReadyQueue);
	
	JLabel LowReadyQueueLabel = new JLabel();
	public ReadyQueuePanel LowReadyQueue = new ReadyQueuePanel();							// LowReadyQueue
	JScrollPane LowReadyQueueScroll = new JScrollPane(LowReadyQueue);
	
	public MFQReadyQueue() {
		Base();
		HighReadyQueue.ScrollSetting(HighReadyQueueScroll);
		MiddleReadyQueue.ScrollSetting(MiddleReadyQueueScroll);
		LowReadyQueue.ScrollSetting(LowReadyQueueScroll);
		
		HighReadyQueueScroll.setBounds(0,20,220,83);
		add(HighReadyQueueScroll);
		
		MiddleReadyQueueScroll.setBounds(240,20,220,83);
		add(MiddleReadyQueueScroll);
		
		LowReadyQueueScroll.setBounds(480,20,220,83);
		add(LowReadyQueueScroll);
		
		ComponentSetting();
	}
	
	private void Base() {
		setLocation(10, 10);
		setSize(700, 120);
		setLayout(null);
		setBackground(Color.WHITE);
		setVisible(false);
	}
	
	private void ComponentSetting() {
		JLabel HighReadyQueueLabel = new JLabel("<HighReadyQueue>");
		HighReadyQueueLabel.setLocation(0,0);
		HighReadyQueueLabel.setBackground(Color.WHITE);
		HighReadyQueueLabel.setSize(120,20);
		add(HighReadyQueueLabel);
		
		JLabel MiddleReadyQueueLabel = new JLabel("<MiddleReadyQueue>");
		MiddleReadyQueueLabel.setLocation(240,0);
		MiddleReadyQueueLabel.setBackground(Color.WHITE);
		MiddleReadyQueueLabel.setSize(130,20);
		add(MiddleReadyQueueLabel);
		
		JLabel LowReadyQueueLabel = new JLabel("<LowReadyQueue>");
		LowReadyQueueLabel.setLocation(480,0);
		LowReadyQueueLabel.setBackground(Color.WHITE);
		LowReadyQueueLabel.setSize(120,20);
		add(LowReadyQueueLabel);
	}
	
}
