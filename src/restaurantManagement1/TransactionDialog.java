package restaurantManagement1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class TransactionDialog extends JDialog {

	private Restaurant restaurant;
	private ImageIcon homepageBackground;
	
	//view general tables panel components
	private JPanel panel;
	private JButton printButton;
	private JButton returnToHomepageButton;

	public TransactionDialog(Restaurant restaurant) {
		this.restaurant = restaurant;
		initUI();
	}

	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		setUndecorated(false); // TODO change to true
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		printButton = new JButton(new ImageIcon(getClass().getResource("print button.JPG")));
		printButton.setBounds(865, 75, 120, 75);
		printButton.addActionListener(new ButtonListener());
		panel.add(printButton);
		
		returnToHomepageButton = new JButton(new ImageIcon(getClass().getResource("return home button.JPG")));
		returnToHomepageButton.setBounds(865, 435, 120, 75);
		returnToHomepageButton.addActionListener(new ButtonListener());
		panel.add(returnToHomepageButton);
		
		setVisible(true);
	}
	
	class ButtonListener implements ActionListener {

		/**
		 * actionPerformed performs the action that is needed to be performed from
		 * clicking a button
		 * 
		 * @param press used to determine which button is pressed
		 */
		public void actionPerformed(ActionEvent press) {
			if (press.getSource() == printButton) {
				// claim only for the current date
				if (restaurant.getTables().size() == 0) {
					JOptionPane.showMessageDialog(null, "Your restaurant currently has no tables.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String customerNameUnderReservation = JOptionPane
						.showInputDialog("Please input the name under reservation: ");
				if (customerNameUnderReservation != null) {
					restaurant.claimReservation(customerNameUnderReservation.toUpperCase());
				}
			}else if (press.getSource() == returnToHomepageButton) {
				dispose();
			}
		}
	}
}
