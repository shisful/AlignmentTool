

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
/**
 * presentationPanel���R���g���[������{�^���@���ׂĂ̑����̃N���X�ő�p�D
 * �R���X�g���N�^�̈�łǂ̃{�^����������D
 * @author hys
 *
 * @see
 */
@SuppressWarnings("serial")
public class ButtonPanelControl extends JButton {
	public static final int PREVIOUS = 0;
	public static final int NEXT = 1;
	public static final int SPECIFY = 2;
	private BufferedImage backgroundImage;
	public PanelTable panelsTable;
	private int panelNumber;
	public static Dimension size = new Dimension(50,40);
	/**
	 * �O�̃p�l���C���̃p�l���@�֐i�ރ{�^���̏ꍇ�̃R���X�g���N�^
	 * @param buttonKind
	 * @param panelTable
	 * @throws IOException
	 */
	public ButtonPanelControl(int buttonKind,PanelTable panelTable) throws IOException{
		if(buttonKind == PREVIOUS || buttonKind == NEXT) {
			this.panelsTable = panelTable;
			init(buttonKind);
		}
	}
	/**
	 * �p�l���ԍ����w�肵�Ă��̃p�l����\������{�^���̏ꍇ�̃R���X�g���N�^
	 * @param buttonKind
	 * @param panelTable
	 * @throws IOException
	 */
	public ButtonPanelControl(int buttonKind,int panelNumber,PanelTable panelsTable) throws IOException{

		if(buttonKind == SPECIFY){
			this.panelsTable = panelsTable;
			this.panelNumber = panelNumber;
			this.init(buttonKind);
		}
	}


	private void init(int buttonKind) throws IOException{
		switch(buttonKind){
		case PREVIOUS:
			this.initPrevious();
			break;
		case NEXT:
			this.initNext();
			break;
		case SPECIFY:
			this.initSpecify();
			break;
		}
	}
	private void initPrevious() throws IOException{
		this.setBorderPainted(false);
		this.setSize(size);
		java.net.URL imgURL = getClass().getResource("arrow_previous.png");
		this.backgroundImage = ImageIO.read(imgURL);
		this.addActionListener(new actionListener(this.panelsTable,PREVIOUS));
	}

	private void initNext() throws IOException{
		this.setBorderPainted(false);
		this.setSize(size);
		java.net.URL imgURL = getClass().getResource("arrow_next.png");
		this.backgroundImage = ImageIO.read(imgURL);
		this.addActionListener(new actionListener(this.panelsTable,NEXT));
	}

	private void initSpecify(){
		this.setBorderPainted(false);
		this.setSize(size);
		//	this.backgroundImage = ImageIO.read(new File("/Users/hys/Documents/workspace/JapaneseEnglishWordMap/src/arrow_down.png"));
		this.addActionListener(new actionListener(this.panelsTable,SPECIFY,panelNumber));
	}

	@Override
	public void paintComponent(Graphics g) {//image��w�i�ɐݒ�D
		Graphics2D g2D = (Graphics2D) g;

		double imageWidth = backgroundImage.getWidth();
		double imageHeight = backgroundImage.getHeight();
		double buttonWidth = this.getWidth();
		double buttonHeight = this.getHeight();

		// �摜���R���|�[�l���g�̉��{�̑傫�����v�Z
		double sx = (buttonWidth / imageWidth);
		double sy = (buttonHeight / imageHeight);

		// �X�P�[�����O
		AffineTransform af = AffineTransform.getScaleInstance(sx, sy);
		g2D.drawImage(backgroundImage, af, this);
	}
	/**
	 * ButtonPresentationPanelControl��actionListener�����������N���X�D
	 * @author hys
	 *
	 * @see
	 */
	private class actionListener implements ActionListener{
		private PanelTable panelTable;
		private int buttonKind;
		private int panelNumber;
		/**
		 * NEXT�{�^���CPREVIOUS�{�^����actionListener�̃R���X�g���N�^
		 * @param panelsPresentation
		 * @param buttonKind
		 */
		public actionListener(PanelTable panelTable,int buttonKind){
			if(buttonKind == NEXT||buttonKind == PREVIOUS){
				this.buttonKind = buttonKind;
				this.panelTable = panelTable;
			}else{
				System.out.println("error in ationListener of ButtonPresentationPanelControl");
			}
		}
		/**
		 * SPECIFY�{�^����actionListener�̃R���X�g���N�^
		 * @param panelsPresentation
		 * @param buttonKind
		 * @param panelNumber
		 */
		public actionListener(PanelTable panelTable,int buttonKind,int panelNumber){
			if(buttonKind == SPECIFY){
				this.buttonKind = buttonKind;
				this.panelTable = panelTable;
				this.panelNumber = panelNumber;
			}else{
				System.out.println("error in ationListener of ButtonPresentationPanelControl");
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(this.buttonKind){
			case NEXT:
				this.panelTable.showNextPanel();
				break;
			case PREVIOUS:
				this.panelTable.showPreviousPanel();
				break;
			case SPECIFY:
				this.panelTable.showSpecifiedPanel(this.panelNumber);
				break;
			}
		}
	}

}
