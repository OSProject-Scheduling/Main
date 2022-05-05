package GUI;
import java.awt.Container;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	Container contentpane;										// 컨테이너
	
	public MainFrame() {
		Base();																			// 기본 설정(MainFrame 설정)
		setContentPane(new MainPanel(this));
		setVisible(true);
	}
	
	
	private void Base() {					// Frame창 기본 설정
		setLayout(null);
		setSize(1514, 797);
		setTitle("OSProject");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
