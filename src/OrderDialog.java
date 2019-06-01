import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OrderDialog extends JDialog {
	private Restaurant restaurant;
	private JPanel panel;
	private JButton claimReservationButton;

	public OrderDialog(Restaurant restaurant) {
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
		getContentPane().add(panel);

		claimReservationButton = new JButton("Claim Reservation");
		claimReservationButton.setBounds(750, 50, 200, 30);
		claimReservationButton.addActionListener(new ButtonListener());
		panel.add(claimReservationButton);

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
			if (press.getSource() == claimReservationButton) {
				// claim only for the current date
				String customerNameUnderReservation = JOptionPane
						.showInputDialog("Please input the name under reservation: ");
				if (customerNameUnderReservation != null) {
					//restaurant.claimReservation(customerNameUnderReservation.toUpperCase());
				}
			}
		}
	}
}
