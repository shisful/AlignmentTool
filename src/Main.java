
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main extends JFrame{
	private static final long serialVersionUID = -4011833720554962245L;
	JTable table;
	JScrollPane scroll;
	public static PanelTable panelTable;
	public static boolean isFileOpened =false;
	public static Dimension size = new Dimension(900,700);
	public static Dimension PANEL_TABLE_SIZE = new Dimension(size.width-10,size.height-150);
	public static Point PANEL_TABLE_POINT = new Point(0,50);
	public static final int dialWidth = 150;
	public static final int dialHeight = 30;
	public Main(){
		this.initGUI();
	}

	private void initGUI(){
		this.setLayout(null);
		this.setSize(size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponents();

	}

	private void addComponents() {
		@SuppressWarnings("unused")
		LabelsMousePointGet labelsMousePointGet = new LabelsMousePointGet();
		for (int i =0 ;i<4;i++){
			this.add(LabelsMousePointGet.label[i]);
		}
		JLabel pageLabel = new JLabel("");
		panelTable = new PanelTable(pageLabel);
//------Add align all button-----
		ButtonAllCheck buttonAllCheck = new ButtonAllCheck(panelTable, ButtonAllCheck.ALIGN_ALL);
		this.add(buttonAllCheck);
		buttonAllCheck.setBounds(PANEL_TABLE_SIZE.width/5,PANEL_TABLE_SIZE.height +55,dialWidth,dialHeight);
		//------
//------Add align none button-----
		ButtonAllCheck buttonAllUnCheck = new ButtonAllCheck(panelTable, ButtonAllCheck.ALIGN_NONE);
		this.add(buttonAllUnCheck);
		buttonAllUnCheck.setBounds(PANEL_TABLE_SIZE.width*3/5,PANEL_TABLE_SIZE.height +55,dialWidth,dialHeight);

		//------
		this.add(panelTable);
		panelTable.setBounds(0,50,PANEL_TABLE_SIZE.width,PANEL_TABLE_SIZE.height);
		this.add(pageLabel);
		pageLabel.setBounds(PANEL_TABLE_SIZE.width/2-50, PANEL_TABLE_SIZE.height+50, 100, 20);

		//panelTable.setData(ArrayOfEnglishWord, ArrayOfJapaneseWord, ArrayOfMappingData);
		InputFileDialog englishDialog = new InputFileDialog(InputFileDialog.ENGLISH);
		InputFileDialog japaneseDialog = new InputFileDialog(InputFileDialog.JAPANESE);
		InputFileDialog mappingDataDialog = new InputFileDialog(InputFileDialog.MAPPINGDATA);
		this.add(englishDialog);
		this.add(japaneseDialog);
		this.add(mappingDataDialog);

		englishDialog.setBounds(10,10,dialWidth,dialHeight);
		japaneseDialog.setBounds(20+dialWidth,10,dialWidth,dialHeight);
		mappingDataDialog.setBounds(30+dialWidth*2,10,dialWidth,dialHeight);
		try {
			ButtonPanelControl buttonNext = new ButtonPanelControl(ButtonPanelControl.NEXT, panelTable);
			ButtonPanelControl buttonPrevious = new ButtonPanelControl(ButtonPanelControl.PREVIOUS, panelTable);
			this.add(buttonNext);
			buttonNext.setBounds(PANEL_TABLE_SIZE.width - ButtonPanelControl.size.width - 10,PANEL_TABLE_SIZE.height +50 + 5
					,ButtonPanelControl.size.width,ButtonPanelControl.size.height);
			this.add(buttonPrevious);
			buttonPrevious.setBounds(PANEL_TABLE_SIZE.width - ButtonPanelControl.size.width*2 - 15,PANEL_TABLE_SIZE.height +panelTable.getY() + 5
					,ButtonPanelControl.size.width,ButtonPanelControl.size.height);
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		JButton button = new JButton("LOAD!");
		button.setBounds(600,10,100,dialHeight);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelTable.reGenerateTable();
				isFileOpened = true;
			}

		});
		this.add(button);
		SaveButton saveButton = new SaveButton();
		saveButton.setBounds(button.getX()+button.getSize().width + 50, button.getY(),button.getSize().width,button. getSize().height);
		this.add(saveButton);
	}
	public static void main(String[] args) {
		new Main().setVisible(true);
	}



}