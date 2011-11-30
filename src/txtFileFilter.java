

import java.io.File;

public class txtFileFilter extends javax.swing.filechooser.FileFilter{
	@Override
	public boolean accept(File f){
		/* �f�B���N�g���Ȃ疳�����ŕ\������ */
		if (f.isDirectory()){
			return true;
		}
		/* �g���q�����o���Appt��������\������ */
		String ext = getExtension(f);
		if (ext != null){
			if (ext.equals("txt")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	public String getDescription(){
		return ".txt file";
	}
	/* �g���q�����o�� */
	public static String getExtension(File f){
		String ext = null;
		String filename = f.getName();
		int dotIndex = filename.lastIndexOf('.');

		if ((dotIndex > 0) && (dotIndex < filename.length() - 1)){
			ext = filename.substring(dotIndex + 1).toLowerCase();
		}

		return ext;
	}

}
