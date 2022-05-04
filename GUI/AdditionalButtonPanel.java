package GUI;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Manager.ProjectManager;

public class AdditionalButtonPanel extends JPanel{
	ProjectManager manager;	
	
	JButton PauseButton = new JButton("Pause");
	
	JButton ReStartButton = new JButton("Restart");
	
	JButton ReSetButton = new JButton("Reset");
	
	public AdditionalButtonPanel(ProjectManager manager) {
		this.manager = manager;
		// manager.runpanel = this;
		Base();															// 초기 설정
		ComponentSetting();												// 구성요소 설정
	}
	
	private void Base(){
		setSize(240, 200);
		setLocation(10, 335);
		setBackground(Color.YELLOW);
		setLayout(null);
	}
	
	private void ComponentSetting() {
		
		PauseButton.setSize(100, 30);								// PauseButton adding
		PauseButton.setLocation(10, 10);
		PauseButton.setOpaque(true);
		PauseButton.setBackground(Color.green);
		PauseButton.addActionListener(new ActionListener() {		// ActionListener
			public void actionPerformed(ActionEvent e) {
				manager.algorithm.timer.cancel();
			}
		});
		add(PauseButton);
		
		ReStartButton.setSize(100, 30);								// RestartButton adding
		ReStartButton.setLocation(130, 10);
		ReStartButton.setOpaque(true);
		ReStartButton.setBackground(Color.green);
		ReStartButton.addActionListener(new ActionListener() {		// ActionListener
			public void actionPerformed(ActionEvent e) {
				manager.algorithm.start();
			}
		});
		add(ReStartButton);
		
		ReSetButton.setSize(220, 30);								// ResetButton adding
		ReSetButton.setLocation(10, 50);
		ReSetButton.setOpaque(true);
		ReSetButton.setBackground(Color.green);
		ReSetButton.addActionListener(new ActionListener() {      // ActionListener
	         public void actionPerformed(ActionEvent e) {
	            if(!(manager.algorithm == null)) manager.algorithm.timer.cancel();
	            manager.mainFrame.setContentPane(new MainPanel(manager.mainFrame));
	         }
	      });
		add(ReSetButton);
	}
}
