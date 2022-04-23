import java.awt.Font;

import javax.swing.JLabel;

public class BaseLabel extends JLabel{
	public BaseLabel(String name) {
		super(name);
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setHorizontalAlignment(JLabel.CENTER);
		setSize(100, 30);
	}
}
