
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * �u�t�@�C�����J���vdialog���J���{�^���DreturnFile()�ɂ���đI�������t�@�C����File�N���X��Ԃ��D
 * @author hys
 *
 * @see
 */
@SuppressWarnings("serial")
public class InputFileDialog extends JButton {

	public static final File[] file = new File[3];
	public static final int ENGLISH = PanelTable.ENGLISH;
	public static final int JAPANESE = PanelTable.JAPANESE;
	public static final int MAPPINGDATA = PanelTable.MAPPINGDATA;
	public static boolean isEnglishChoosed=false;
	public static boolean isJapaneseChoosed=false;
	public static boolean isMappingDataChoosed=false;
	public static String directory = null;

	public InputFileDialog(int buttonKind){
		this.init(buttonKind);
	}

	private void init(int buttonKind){
		ActionListenerOpenDialog actionListenerOpenDialog = new ActionListenerOpenDialog(buttonKind);
		this.addActionListener(actionListenerOpenDialog);
		switch(buttonKind){
		case ENGLISH:
			this.setText("Open English");
			break;
		case JAPANESE:
			this.setText("Open Japanese");
			break;
		case MAPPINGDATA:
			this.setText("Open Alignment");
			break;
		}
	}

	class ActionListenerOpenDialog implements ActionListener {
		private String dialogTitle;
		private int fileSelectionMode;
		private FileFilter fileFilter;
		private boolean fileFilterFlag = false;
		private int buttonKind;
		public ActionListenerOpenDialog(int buttonKind){
			this.buttonKind = buttonKind;
			fileFilter = new txtFileFilter();
			fileFilterFlag = true;

			switch(buttonKind){
			case ENGLISH :
				dialogTitle = "Open English";
				fileSelectionMode = JFileChooser.FILES_ONLY;
				break;
			case JAPANESE :
				dialogTitle = "Open Japanese";
				fileSelectionMode = JFileChooser.FILES_ONLY;
				break;
			case MAPPINGDATA :
				dialogTitle = "Open Alignment";
				fileSelectionMode = JFileChooser.FILES_ONLY;
				break;
			}
		}
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser(directory);
			filechooser.setDialogTitle(dialogTitle);
			filechooser.setFileSelectionMode(fileSelectionMode);
			if(fileFilterFlag){
				filechooser.addChoosableFileFilter(fileFilter);
			}
			int selected = filechooser.showOpenDialog(null);
			if (selected == JFileChooser.APPROVE_OPTION){
				file[buttonKind] = filechooser.getSelectedFile();
				directory = file[buttonKind].getParent();
				switch(buttonKind){
				case ENGLISH:
					isEnglishChoosed = true;
					break;
				case JAPANESE:
					isJapaneseChoosed = true;
					break;
				case MAPPINGDATA:
					isMappingDataChoosed = true;
					break;
				}
				if(isJapaneseChoosed && isEnglishChoosed && isMappingDataChoosed){

					InputStreamReader fileReader = null;
					ArrayList<String[]>[] lineArray = new ArrayList[3];

					for (int i=0;i<3;i++){
						lineArray[i] = new ArrayList<String[]>();
						try{
							String[] wordArray;
							FileInputStream is = new FileInputStream(file[i]);
							fileReader = new InputStreamReader(is, "UTF-8");
							BufferedReader reader = new BufferedReader(fileReader);
							String line;
							for(int j=0;(line = reader.readLine()) != null;j++){
								wordArray = line.split(" ");
								lineArray[i].add(wordArray);
							}
						}	catch(IOException es){
						}
						finally{ try{ if(fileReader != null) fileReader.close(); } catch(IOException dae){} }
					}
					PanelTable.setData(lineArray);

				}
			}
		}

	}
}
