
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("unchecked")
public class PanelTable extends JPanel{

	private static final long serialVersionUID = -5335691153210117889L;
	public static final int ENGLISH = 0;
	public static final int JAPANESE = 1;
	public static final int MAPPINGDATA = 2;
	public static ArrayList<String[]>[] arrayListOfSetData = new ArrayList[3];
	public static ArrayList<Point> tablePointOfLine = new ArrayList<Point>();
	public static ArrayList<JTable> MappingTable = new ArrayList<JTable>();
	public static ArrayList<JList> EnglishWordTable = new ArrayList<JList>(),JapaneseWordTable = new ArrayList<JList>();
	public static ArrayList<JScrollPane> scrollPane = new ArrayList<JScrollPane>();
	public static ArrayList<String[][]> stringTable = new ArrayList();
	public static JLabel tate = new JLabel();
	public static JLabel yoko = new JLabel();
	public static JLabel pages = new JLabel();
	public static int rowHeight = 16;
	public static int charWidth = 14;
	public static int currPage = 0, totalPage = 0;
	//public static DefaultTableModel defaultTableModel;
	public static Dimension size = Main.PANEL_TABLE_SIZE;
	public CardLayout cardLayout = new CardLayout();


	public PanelTable(JLabel pageLabel){
		this.setLayout(cardLayout);
		this.setSize(size);
		pages = pageLabel;
	}

	public static String[][] checkTable(int EnglishWordCount,int JapaneseWordCount,String[] MappingData){
		String[][] checkMap = new String[EnglishWordCount][JapaneseWordCount];
		for (int i=0;i<EnglishWordCount;i++){
			for (int j=0;j<JapaneseWordCount;j++){
				checkMap[i][j] = " ";
			}
		}
		Point checkPoint = new Point();
		for (int i =0 ; i<MappingData.length;i++){
			if( MappingData[i].indexOf("-") >0){
				String[] stringPair = MappingData[i].split("-");
				checkPoint.x = Integer.parseInt(stringPair[1]);
				checkPoint.y = Integer.parseInt(stringPair[0]);
				checkMap[checkPoint.x][checkPoint.y] = "X";
			}
		}
		return checkMap;
	}


	public static void setData(ArrayList<String[]>[] arrayListOfSetData){
		PanelTable.arrayListOfSetData = arrayListOfSetData;
		totalPage = arrayListOfSetData[ENGLISH].size();
		currPage = 1;
		for (int i=0;i<totalPage;i++){
			JList EnglishWordList = new JList(arrayListOfSetData[ENGLISH].get(i));
			EnglishWordList.setFixedCellHeight(rowHeight);
			EnglishWordTable.add(EnglishWordList);
			JList JapaneseWordList = new JList(arrayListOfSetData[JAPANESE].get(i));
			JapaneseWordTable.add(JapaneseWordList);

			String[][] checkMap = checkTable(arrayListOfSetData[ENGLISH].get(i).length,
					arrayListOfSetData[JAPANESE].get(i).length,
					arrayListOfSetData[MAPPINGDATA].get(i));
			stringTable.add(checkMap);

			DefaultTableModel defaultTableModel = new DefaultTableModel((String[]) arrayListOfSetData[JAPANESE].get(i),0);
			for(int j = 0 ; j < arrayListOfSetData[ENGLISH].get(i).length ; j++){
				defaultTableModel.addRow(checkMap[j]);
			}
			final JTable jtable = new JTable(defaultTableModel);

			jtable.setCellSelectionEnabled(true);
			jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jtable.setDefaultRenderer(Object.class,new BirowTableRenderer());
			jtable.setRowHeight(rowHeight);
			jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			String[] jWords = arrayListOfSetData[JAPANESE].get(i);
			for(int j = 0; j < jWords.length; j++)
				jtable.getColumnModel().getColumn(j).setPreferredWidth(jWords[j].length()*charWidth+6);

			jtable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					Point point = e.getPoint();
					int row= jtable.rowAtPoint(point);
					int column = jtable.columnAtPoint(point);
					check(row,column,jtable);
				}
			});
			jtable.addMouseMotionListener(new MouseAdapter(){
				public void mouseMoved(MouseEvent e) {
					//System.out.println(e.getPoint());
					LabelsMousePointGet.moveLabels(e.getPoint());
				}
			});
			MappingTable.add(jtable);
			JScrollPane jscrollPane = new JScrollPane(MappingTable.get(i));
			jscrollPane.setRowHeaderView(EnglishWordTable.get(i));
			//scrollPane.setColumnHeaderView(JapaneseWordTable);
			jscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jscrollPane.setSize(size);
			scrollPane.add(jscrollPane);
		}

	}

	public void reGenerateTable(){
		pages.setText(currPage+"/"+totalPage);
		for (int i=0;i<scrollPane.size();i++){
			String ID = ((Integer)i).toString();
			this.add(ID,scrollPane.get(i));
		}
	}
	public String[][] getCheckMap(int pageNumber){
		int columnSize = PanelTable.MappingTable.get(pageNumber).getModel().getColumnCount();
		int rowSize = PanelTable.MappingTable.get(pageNumber).getModel().getRowCount();
		String[][] checkMap = new String[rowSize][columnSize];
		for(int column = 0;column < columnSize;column++){
			for (int row = 0;row < rowSize;row++){
				checkMap[row][column] = (String) PanelTable.MappingTable.get(pageNumber).getModel().getValueAt(row,column);
			}
		}
		return checkMap;
	}
	public void setCheckMap(String[][] checkMap,int pageNumber){
		int columnSize = PanelTable.MappingTable.get(pageNumber).getModel().getColumnCount();
		int rowSize = PanelTable.MappingTable.get(pageNumber).getModel().getRowCount();
		//System.out.println(checkMap[0].length  + " " +  columnSize + " " +  checkMap.length  + " " +  rowSize);
		if (checkMap[0].length == columnSize && checkMap.length == rowSize){
			for(int column = 0;column < columnSize;column++){
				for (int row = 0;row < rowSize;row++){
					if(checkMap[row][column].equals("X")){
						PanelTable.MappingTable.get(pageNumber).getModel().setValueAt("X",row,column);
					}else{
						PanelTable.MappingTable.get(pageNumber).getModel().setValueAt("",row,column);
					}
				}
			}

		}else{
			System.out.println("wrong input!!@PanelTable");
		}
	}

	private static void check(int row,int column,JTable jtable){
		if(jtable.getModel().getValueAt(row, column).equals("X")){
			jtable.getModel().setValueAt("",row, column);
		}else{
			jtable.getModel().setValueAt("X",row, column);
		}
	}
	class MyTableModel extends DefaultTableModel{
		private static final long serialVersionUID = -2677379363148411290L;

		MyTableModel(String[] columnNames, int rowNum){
			super(columnNames, rowNum);
		}

		public Class getColumnClass(int col){
			return getValueAt(0, col).getClass();
		}
	}

	//�J�[�h���C�A�E�g��ύX����֐�
	public void showNextPanel(){
		cardLayout.next(this);
		if(++currPage > totalPage) currPage = 1;
		pages.setText(currPage+"/"+totalPage);
	}
	public void showPreviousPanel(){
		cardLayout.previous(this);
		if(--currPage == 0) currPage = totalPage;
		pages.setText(currPage+"/"+totalPage);
	}
	public void showSpecifiedPanel(int i){
		String ID = ((Integer)i).toString();
		cardLayout.show(this, ID);
		currPage = i;
		pages.setText(currPage+"/"+totalPage);
	}


}
