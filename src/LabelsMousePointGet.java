import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;


public class LabelsMousePointGet{
	public static JLabel[] label = new JLabel[4];
	public static int TATE_UE = 0;
	public static int TATE_SHITA = 1;
	public static int YOKO_HIDARI =2;
	public static int YOKO_MIGI =3;
	public static int lineWidth = 10;
	public static Dimension size = Main.PANEL_TABLE_SIZE;
	public static Dimension tateSize = new Dimension(lineWidth,size.height);
	public static Dimension yokoSize = new Dimension(size.width,lineWidth);
	public static Color color = new Color(255,0,0,0);
	public LabelsMousePointGet(){
		for ( int i=0;i<4;i++){
		label[i] = new JLabel();
		label[i].setBackground(color);
		label[i].setOpaque(true);
		}
	}
	public static void moveLabels(Point mousePoint){
			Point newMouse = new Point();
			newMouse.x = mousePoint.x + Main.PANEL_TABLE_POINT.x+100+lineWidth/2;
			newMouse.y = mousePoint.y + Main.PANEL_TABLE_POINT.y+10+lineWidth/2;
			label[TATE_UE].setBounds(newMouse.x - lineWidth/2,Main.PANEL_TABLE_POINT.y,
					tateSize.width,newMouse.y - Main.PANEL_TABLE_POINT.y);

			label[TATE_SHITA].setBounds(label[TATE_UE].getX(),newMouse.y + lineWidth/2,
					tateSize.width,Main.PANEL_TABLE_SIZE.height - label[TATE_UE].getSize().height - lineWidth);

			label[YOKO_HIDARI].setBounds(Main.PANEL_TABLE_POINT.x,newMouse.y - lineWidth/2,
					newMouse.x - Main.PANEL_TABLE_POINT.x - lineWidth/2 , yokoSize.height);

			label[YOKO_MIGI].setBounds(label[YOKO_HIDARI].getX() + label[YOKO_HIDARI].getSize().width + tateSize.width,label[YOKO_HIDARI].getY(),
					Main.PANEL_TABLE_SIZE.width - label[YOKO_HIDARI].getSize().width - tateSize.width,yokoSize.height);

	}
}
