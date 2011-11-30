import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;


public class SaveButton extends JButton implements ActionListener{

	private static final long serialVersionUID = -4892539824068804751L;

	public SaveButton(){
		this.setText("SAVE");
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		JFileChooser filechooser = new JFileChooser();

		int selected = filechooser.showSaveDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION){
			File file = filechooser.getSelectedFile();
			save(file);
		}
	}

	public void save(File file){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file, false));


			for (int tableNumber = 0;tableNumber < PanelTable.MappingTable.size();tableNumber++){
				String data = new String();
				for (int column = 0;column < PanelTable.MappingTable.get(tableNumber).getModel().getColumnCount();column++){
					for (int row = 0;row < PanelTable.MappingTable.get(tableNumber).getModel().getRowCount();row++){
						if(PanelTable.MappingTable.get(tableNumber).getModel().getValueAt(row,column).equals("X")){
							data = data +column+"-"+row+" ";
						}
					}
				}
				// �V���ȃf�[�^�s�̒ǉ�
				bw.write(data);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
	}
}


