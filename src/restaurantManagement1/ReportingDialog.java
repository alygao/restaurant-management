package restaurantManagement1;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class ReportingDialog extends JDialog {

	// VARIABLES
	private Restaurant restaurant;
	private JPanel panel;
	private JButton generateFinancialRatiosButtons;
	private JButton returnToHomepageButton;
	private JTextField revenueTextField;
	private JFormattedTextField enterExpensesTextField;
	private JTextField revenuePerSeatTextField;
	private JTextField netIncomeTextField;
	private JTextField profitMarginTextField;
	private double revenue;
	private double expenses = 0;
	private double revenuePerSeat = 0;
	private double netIncome = 0;
	private double profitMargin = 0;
	private ImageIcon homepageBackground;
	private JLabel homepageBackgroundLabel;
	private final DecimalFormat currencyFormat = new DecimalFormat("##0.00");

	public ReportingDialog(Restaurant restaurant) {
		if (!(restaurant.getCurrentUser() instanceof Manager)) {
			JOptionPane.showMessageDialog(null, "Only a manager can access the reporting function.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.restaurant = restaurant;
		//revenue = this.restaurant.getTotalSales();
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

		generateFinancialRatiosButtons = new JButton(
				new ImageIcon(getClass().getResource("generate ratio button.JPG")));
		generateFinancialRatiosButtons.setBounds(865, 75, 120, 75);
		generateFinancialRatiosButtons.addActionListener(new ButtonListener());
		panel.add(generateFinancialRatiosButtons);

		returnToHomepageButton = new JButton(new ImageIcon(getClass().getResource("return home button.JPG")));
		returnToHomepageButton.setBounds(865, 435, 120, 75);
		returnToHomepageButton.addActionListener(new ButtonListener());
		panel.add(returnToHomepageButton);

		JLabel salesLabel = new JLabel("Total Revenue: ");
		salesLabel.setBounds(25, 75, 100, 30);
		panel.add(salesLabel);

		revenueTextField = new JTextField("$ " + currencyFormat.format(revenue));
		revenueTextField.setEditable(false);
		revenueTextField.setBounds(200, 75, 150, 30);
		panel.add(revenueTextField);

		JLabel enterExpensesLabel = new JLabel("Enter Expenses: ");
		enterExpensesLabel.setBounds(25, 110, 100, 30);
		panel.add(enterExpensesLabel);

		// Formatter to ensure user does not enter non-numerical values
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		NumberFormatter numFormatter = new NumberFormatter(decimalFormat);
		numFormatter.setAllowsInvalid(false); // specifies that invalid characters are not allowed

		enterExpensesTextField = new JFormattedTextField(numFormatter);
		enterExpensesTextField.setBounds(200, 110, 150, 30);
		panel.add(enterExpensesTextField);

		JLabel netIncomeLabel = new JLabel("Net Income: ");
		netIncomeLabel.setBounds(25, 200, 100, 30);
		panel.add(netIncomeLabel);
		netIncomeLabel.setVisible(true);

		netIncomeTextField = new JTextField();
		netIncomeTextField.setBounds(200, 200, 150, 30);
		netIncomeTextField.setEditable(false);
		panel.add(netIncomeTextField);

		JLabel profitMarginLabel = new JLabel("Profit Margin: ");
		profitMarginLabel.setBounds(25, 240, 100, 30);
		panel.add(profitMarginLabel);
		profitMarginLabel.setVisible(true);

		profitMarginTextField = new JTextField();
		profitMarginTextField.setBounds(200, 240, 150, 30);
		profitMarginTextField.setEditable(false);
		panel.add(profitMarginTextField);

		JLabel revenuePerSeatLabel = new JLabel("Revenue Per Seat");
		revenuePerSeatLabel.setBounds(25, 280, 150, 30);
		panel.add(revenuePerSeatLabel);
		revenuePerSeatLabel.setVisible(true);

		revenuePerSeatTextField = new JTextField();
		revenuePerSeatTextField.setBounds(200, 280, 150, 30);
		revenuePerSeatTextField.setEditable(false);
		panel.add(revenuePerSeatTextField);

		getContentPane().add(panel);

		// background image
		homepageBackground = new ImageIcon(getClass().getResource("freshqo background.JPG"));
		homepageBackgroundLabel = new JLabel(homepageBackground);
		homepageBackgroundLabel.setBounds(0, 0, 1000, 600);
		panel.add(homepageBackgroundLabel);

		setVisible(true);
	}

	public void calculateAndDisplayRatios() {
		//revenuePerSeat = revenue / restaurant.getTotalSeats();
		netIncome = revenue - expenses;
		profitMargin = revenue/netIncome;
		
		netIncomeTextField.setText("$ " + netIncome);
		profitMarginTextField.setText(Double.toString(profitMargin));
		revenuePerSeatTextField.setText("$ " + Double.toString(revenuePerSeat));
	}

	class ButtonListener implements ActionListener {

		/**
		 * actionPerformed performs the action that is needed to be performed from
		 * clicking a button
		 * 
		 * @param press used to determine which button is pressed
		 */
		public void actionPerformed(ActionEvent press) {
			if (press.getSource() == generateFinancialRatiosButtons) {
				if (enterExpensesTextField.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(null, "Please enter a value in expenses.", "Error",
//							JOptionPane.ERROR_MESSAGE);
					return;
				}
				expenses = Double.parseDouble(enterExpensesTextField.getText());
				calculateAndDisplayRatios();

			} else if (press.getSource() == returnToHomepageButton) {
				dispose();
			}
		}
	}

}
