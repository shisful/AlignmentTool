import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class BirowTableRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 4809171586076780159L;
	private static final Color BIROW_COLOR = new Color(204, 204, 255);
	private static final Color ORG_COLOR=Color.WHITE;

	public Component getTableCellRendererComponent(JTable tb,
			Object val,boolean isSelected,
			boolean hasFocus,int r,int c){

		JLabel renderedLabel = (JLabel)super.getTableCellRendererComponent(tb, val, isSelected, hasFocus, r, c);
		renderedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		if(r % 2 == 0){
			renderedLabel.setBackground(BIROW_COLOR);
		}else{
			renderedLabel.setBackground(ORG_COLOR);
		}
		return renderedLabel;
	}
}
