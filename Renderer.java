import java.awt.*; 
import javax.swing.*; 
import javax.swing.table.*;

public class Renderer extends DefaultTableCellRenderer{
	int row;
	String name;
	boolean isRun;
	public Renderer() {
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
	@Override 
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		c.setBackground(ChooseColor(this.row));
		c.setForeground(Color.WHITE);
		this.row++;
		return c;
	}
	public Color ChooseColor(int row) {
		switch(row + 1){
			case 1:
				return Color.BLUE;
			case 2:
				return Color.RED;
			case 3:
				return Color.GRAY;
			case 4:
				return Color.GREEN;
			case 5:
				return Color.MAGENTA;
			case 6:
				return Color.ORANGE;
			case 7:
				return Color.PINK;
			case 8:
				return Color.YELLOW;
			case 9:
				return Color.CYAN;
			case 10:
				return Color.DARK_GRAY;
			case 11:
				return Color.WHITE;
			case 12:
				return Color.LIGHT_GRAY;
			case 13:
				Color color= new Color(100,100,100);
				return color;
			case 14:
				Color color1 = new Color(210,100,140);
				return color1;
			default:
				Color color2 = new Color(150,150,100);
				return color2;
		}
	}
}
