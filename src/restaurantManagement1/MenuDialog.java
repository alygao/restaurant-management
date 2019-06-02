package restaurantManagement1;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

public class MenuDialog extends JDialog {

	private Restaurant restaurant;
	private JTextField menuItemNameTextField;
	private JFormattedTextField menuItemPriceTextField;
	private JTextArea menuItemDescriptionTextArea;
	private JLabel menuItemImageLabel;
	private JComboBox menuItemCategoryChoice;

	private JButton loadImageButton;
	private JButton addItemButton;

	private JPanel panel;

	public MenuDialog(Restaurant restaurant) {
		this.restaurant = restaurant;
		initUI();
	}

	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		setUndecorated(false); // TODO: change to true
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);

		// Formatter to ensure user does not enter non-numerical values
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		NumberFormatter numFormatter = new NumberFormatter(decimalFormat);
		numFormatter.setAllowsInvalid(false); // specifies that invalid characters are not allowed

		JLabel menuItemNameLabel = new JLabel("Item Name:");
		menuItemNameLabel.setBounds(50, 100, 100, 30);
		menuItemNameTextField = new JTextField();
		menuItemNameTextField.setBounds(175, 100, 200, 30);

		JLabel menuItemPriceLabel = new JLabel("Item Price:");
		menuItemPriceLabel.setBounds(50, 150, 100, 30);
		menuItemPriceTextField = new JFormattedTextField(numFormatter);
		menuItemPriceTextField.setBounds(175, 150, 200, 30);

		JLabel menuItemDescriptionLabel = new JLabel("Item Description:");
		menuItemDescriptionLabel.setBounds(50, 200, 100, 30);
		JScrollPane menuItemDescriptionScrollPane = new JScrollPane();
		menuItemDescriptionTextArea = new JTextArea();
		menuItemDescriptionTextArea.setBounds(175, 200, 200, 75);
		menuItemDescriptionTextArea.setLineWrap(true);
		menuItemDescriptionTextArea.setWrapStyleWord(true);
		menuItemDescriptionTextArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		JLabel menuItemCategoryLabel = new JLabel("Item Category");
		menuItemCategoryLabel.setBounds(50, 300, 100, 30);
		String[] menuItemCategoryOptions = { "Appetizer", "Entree", "Dessert", "Beverage" };
		menuItemCategoryChoice = new JComboBox(menuItemCategoryOptions);
		menuItemCategoryChoice.setBounds(175, 300, 200, 30);

		menuItemImageLabel = new JLabel("Load Image Here . . .");
		menuItemImageLabel.setBounds(500, 100, 175, 175);
		Border recipeImageJLabeborder = BorderFactory.createLineBorder(Color.BLACK, 1);
		menuItemImageLabel.setBorder(recipeImageJLabeborder);

		loadImageButton = new JButton("Load Image");
		loadImageButton.setBounds(500, 300, 100, 30);
		loadImageButton.addActionListener(new ButtonListener());
		
		addItemButton = new JButton("Add Item");
		addItemButton.setBounds(50, 400, 100, 30);
		addItemButton.addActionListener(new ButtonListener());

		panel.add(menuItemNameLabel);
		panel.add(menuItemNameTextField);
		panel.add(menuItemPriceLabel);
		panel.add(menuItemPriceTextField);
		panel.add(menuItemDescriptionLabel);
		panel.add(menuItemDescriptionTextArea);
		panel.add(menuItemCategoryLabel);
		panel.add(menuItemCategoryChoice);
		panel.add(menuItemImageLabel);
		panel.add(loadImageButton);
		panel.add(addItemButton);

		setVisible(true);
	}

	/**
	 * ButtonListener inner class that checks which button was pressed and executes
	 * the corresponding action
	 * 
	 * @author Alyssa Gao
	 * @version 1.0
	 * @since May 5, 2019
	 */
	class ButtonListener implements ActionListener {

		/**
		 * actionPerformed performs the action that is needed to be performed from
		 * clicking a button
		 * 
		 * @param press used to determine which button is pressed
		 */
		public void actionPerformed(ActionEvent press) {
			if (press.getSource() == loadImageButton) {
				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("Image files", ".jpg");
				fileopen.addChoosableFileFilter(filter);

				int ret = fileopen.showDialog(panel, "Open file");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileopen.getSelectedFile();
					try {
						BufferedImage img = ImageIO.read(file);
						Image dimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						menuItemImageLabel.setIcon(new ImageIcon(dimg));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else if (press.getSource() == addItemButton) {
				for (int i = 0; i < restaurant.getMenu().size(); i++) {
					if ((restaurant.getMenu().get(i).getName()
							.equals((menuItemNameTextField.getText().toUpperCase())))) {
						JOptionPane.showMessageDialog(null, "You already have added an item with the same name.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				if ((menuItemNameTextField.getText().equals("")) || (menuItemPriceTextField.getText().equals(""))
						|| (menuItemDescriptionTextArea.getText().equals("")) || (menuItemImageLabel.getIcon() == null)) {
					JOptionPane.showMessageDialog(null, "You are still missing values in your item.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				MenuItem menuItem = new MenuItem(menuItemNameTextField.getText().toUpperCase(), (double) Double.parseDouble(menuItemPriceTextField.getText()),
						menuItemDescriptionTextArea.getText(), menuItemImageLabel.getIcon(), menuItemCategoryChoice.getSelectedItem().toString());
				restaurant.getMenu().add(menuItem);
//				recipeListTableModel.addRow(recipe);
				dispose();

			}
		}
	}

}
