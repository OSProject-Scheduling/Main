package Manager;
import java.awt.*; 
import javax.swing.*; 
import javax.swing.table.*;

public class Renderer extends DefaultTableCellRenderer{
	int row;
	String name;
	boolean isRun;
	Color color;
	public Renderer() {
		this.setHorizontalAlignment(SwingConstants.CENTER);
		System.out.print("as");
	}
	@Override 
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		c.setBackground(ChooseColor(row));
		c.setForeground(Color.WHITE);
		System.out.print("ddla");
		this.row++;
		return c;
	}
	public Color ChooseColor(int row) {
		switch(row + 1){
			case 1:
				color = new Color(51,51,153);
				return color;
			case 2:
				color = new Color(153,51,51);
				return color;
			case 3:
				color = new Color(102,102,102);
				return color;
			case 4:
				color = new Color(0,152,0);
				return color;
			case 5:
				color = new Color(102,0,102);
				return color;
			case 6:
				color = new Color(204,51,0);
				return color;
			case 7:
				color = new Color(153,0,153);
				return color;
			case 8:
				color = new Color(204,153,0);
				return color;
			case 9:
				color=  new Color(0,153,153);
				return color;
			case 10:
				color = new Color(51,51,51);
				return color;
			case 11:
				color = new Color(102,51,102);
				return color;
			case 12:
				return Color.LIGHT_GRAY;
			case 13:
				color= new Color(51,0,51);
				return color;
			case 14:
				color = new Color(51,0,102);
				return color;
			default:
				color = new Color(153,51,0);
				return color;
		}
	}
}
