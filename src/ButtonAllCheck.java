import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("static-access")
public class ButtonAllCheck extends JButton implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static PanelTable panelTable = null;
	private boolean isSpecialCheck = false;
	private int pageNumber = -1;
	public static final int ALIGN_ALL = 0;
	public static final int ALIGN_NONE = 1;
	public int ID=-1;
	public String[][] previousMapData = null;

	public ButtonAllCheck(PanelTable panelTable,int ID){
		this.panelTable = panelTable;
		this.ID = ID;
		this.initGUI();
	}
	private void initGUI(){
		this.addActionListener(this);
		switch(this.ID){
		case ALIGN_ALL :
			this.setText("Align All");

			break;
		case ALIGN_NONE :
			this.setText("Align None");
			break;
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.pageNumber == -1){
			this.pageNumber = this.panelTable.currPage - 1;
			this.isSpecialCheck = false;
		}else if(this.pageNumber == this.panelTable.currPage - 1){

		}else{
			this.pageNumber = this.panelTable.currPage - 1;
			this.isSpecialCheck = false;
		}
		if(this.isSpecialCheck){
			this.panelTable.setCheckMap(this.previousMapData, this.pageNumber);
			this.isSpecialCheck =false;
		}else{
			this.previousMapData = this.panelTable.getCheckMap(this.pageNumber);
			String[][] checkMap = new String[this.previousMapData.length][this.previousMapData[0].length];
			int columnSize = checkMap[0].length;
			int rowSize = checkMap.length;;
			String text = new String();
			switch(this.ID){
			case ALIGN_ALL :
				text = "X";
				break;
			case ALIGN_NONE :
				text = "";
				break;
			}

			for(int column = 0;column < columnSize;column++){
				for (int row = 0;row < rowSize;row++){
					checkMap[row][column] = text;
				}
			}
			this.panelTable.setCheckMap(checkMap, this.pageNumber);

			this.isSpecialCheck =true;
		}

	}

}
