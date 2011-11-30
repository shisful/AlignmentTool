import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class ButtonOneCell extends JLabel implements TableCellRenderer{
	
	private static final long serialVersionUID = -7943222456926945544L;
	public boolean isCheck = false;
	public ButtonOneCell(){
		this.setBackground(Color.BLACK);
	//	this.setBorderPainted(false);
		//this.addActionListener(this);
	}
	/*
	@Override
	public void actionPerformed(ActionEvent arg0) {

	}*/
	public void Action(){
		if(this.isCheck){
			this.uncheck();
		}else if(!this.isCheck){
			this.check();
		}
	}
	public void check(){
		if(!this.isCheck){
			this.setBackground(Color.BLACK);
			this.isCheck = true;
			this.repaint();
		}
	}
	public void uncheck(){
		if(this.isCheck){
			this.setBackground(Color.WHITE);
			this.isCheck = false;
			this.repaint();
		}
	}
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return this;
	}
	/*
	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}
	@Override
	public void cancelCellEditing() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}
	@Override
	public Object getCellEditorValue() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}
	@Override
	public boolean isCellEditable(EventObject arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}
	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}
	@Override
	public boolean shouldSelectCell(EventObject arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}
	@Override
	public boolean stopCellEditing() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}
	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}*/
}
