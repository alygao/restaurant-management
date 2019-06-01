package restaurantManagement1;

import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuDialog extends JDialog {
	
	private Restaurant restaurant;
	private JLabel menuItemNameLabel;
	private JFormattedTextField menuItemPriceLabel;
	private JLabel menuItemDescriptionLabel;
	private JLabel menuItemImageLabel;
	private JComboBox foodCategoryField;
	
	private JButton loadImageButton;
	
	private JPanel panel;
	
	public MenuDialog(Restaurant restaurant) {
		this.restaurant = restaurant;
		initUI();
	}
	
	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		setUndecorated(true);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
	}

}
