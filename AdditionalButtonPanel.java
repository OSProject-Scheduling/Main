import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdditionalButtonPanel extends JPanel{
	ProjectManager manager;	
	
	JButton ReSetButton = new JButton("Reset");
	
	JButton ReStartButton = new JButton("Restart");
	
	JButton PauseButton = new JButton("Pause");
	
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
		ReSetButton.setSize(220, 30);								// ResetButton adding
		ReSetButton.setLocation(10, 30);
		ReSetButton.setOpaque(true);
		ReSetButton.setBackground(Color.green);
		ReSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.addPanel.AlgorithmList.clear();
				manager.addPanel.Row = 0;
				manager.information.model.setNumRows(0);
				
			}
		});
		add(ReSetButton);
	}
}
