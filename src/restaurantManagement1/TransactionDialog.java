package restaurantManagement1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.time.DayOfWeek;
import java.time.LocalDate;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class TransactionDialog extends JDialog {

	private Restaurant restaurant;
	private JLabel dateLabel;
	private ImageIcon homepageBackground;
	private JPanel panel;
	private JButton printButton;
	private JButton returnToHomepageButton;
	private JLabel homepageBackgroundLabel;
	private JTable transactionTable;
	private DatePickerSettings dateSettings;
	private DatePicker datePicker;
	private JButton lookUpButton;

	public TransactionDialog(Restaurant restaurant) {
		this.restaurant = restaurant;
		initUI();
	}

	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		setUndecorated(true); // TODO change to true
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(null);

		//Print Button
		printButton = new JButton(new ImageIcon(getClass().getResource("print button.JPG")));
		printButton.setBounds(865, 75, 120, 75);
		printButton.addActionListener(new ButtonListener());
		panel.add(printButton);

		//Return Button
		returnToHomepageButton = new JButton(new ImageIcon(getClass().getResource("return home button.JPG")));
		returnToHomepageButton.setBounds(865, 435, 120, 75);
		returnToHomepageButton.addActionListener(new ButtonListener());
		panel.add(returnToHomepageButton);

		//JTable of Transactions
		TransactionLayoutTableModel transactionTableLayout = new TransactionLayoutTableModel();
		transactionTable = new JTable(transactionTableLayout);
		transactionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		transactionTable.setBounds(25, 150, 500, 400);
		transactionTableLayout.addRows(restaurant.getHistoricalTransactions());

		JScrollPane transactionListScrollPane = new JScrollPane(transactionTable);
		transactionListScrollPane.setBounds(25, 150, 500, 400);
		panel.add(transactionListScrollPane);

		// Date
		dateLabel = new JLabel("<html>Reservation:<p>Date</html>");
		dateLabel.setBounds(25, 100, 100, 30);
		panel.add(dateLabel);
		dateSettings = new DatePickerSettings();
		dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		datePicker = new DatePicker(dateSettings);
		dateSettings.setDateRangeLimits(LocalDate.now(), LocalDate.now().plusDays(60));
		datePicker.setBounds(150, 100, 200, 30);
		datePicker.setDateToToday();
		datePicker.getComponentDateTextField().setEditable(false);
		panel.add(datePicker);

		lookUpButton = new JButton(new ImageIcon(getClass().getResource("look up button.JPG")));
		lookUpButton.setBounds(380, 100, 100, 30);
		lookUpButton.addActionListener(new ButtonListener());
		panel.add(lookUpButton);

		// background image
		homepageBackground = new ImageIcon(getClass().getResource("freshqo background.JPG"));
		homepageBackgroundLabel = new JLabel(homepageBackground);
		homepageBackgroundLabel.setBounds(0, 0, 1000, 600);
		panel.add(homepageBackgroundLabel);

		getContentPane().add(panel);
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

	class TransactionLayoutTableModel extends AbstractTableModel {
		private final String[] transactionLayoutColumns = {"Date", "Total ($)", "Subtotal ($)"};
		private final Class[] columnClasses = { String.class, Double.class , Double.class};
		private List<TableOrder> transactionData = new ArrayList<>();

		@Override
		/**
		 * getColumnCount the number of columns in the table
		 *
		 * @return the number of columns
		 */
		public int getColumnCount() {
			return this.transactionLayoutColumns.length;
		}

		@Override
		/**
		 * getRowCount the number of rows in the table
		 *
		 * @return the number of rows
		 */
		public int getRowCount() {
			return transactionData.size();
		}

		@Override
		/**
		 * getColumnName
		 *
		 * @param col the column number
		 * @return the name of the column
		 */
		public String getColumnName(int col) {
			return this.transactionLayoutColumns[col];
		}

		@Override
		/**
		 * getValueAt finds the value at the specific row and column number *
		 *
		 * @param row the row number
		 * @param col the column number
		 * @return the value at the specific row and column
		 */
		public Object getValueAt(int row, int col) {

			TableOrder transaction = this.transactionData.get(row);
			switch (col) {
				case 0:
					return transaction.getDate();
				case 1:
					return transaction.getTotal();
				default:
					return transaction.getSubtotal();
			}
		}

		@Override
		/**
		 * getColumnClass finds the class type for a specific column
		 *
		 * @param col the column number
		 * @return the class type for the specific column
		 */
		public Class<?> getColumnClass(int col) {
			return this.columnClasses[col];
		}

		@Override
		/**
		 * isCellEditable checks if the user can edit the cell
		 *
		 * @param row the row number
		 * @param col the column number
		 * @return whether or not the cell is editable
		 */
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		@Override
		/**
		 * setValueAt sets a value at the specific row and column
		 *
		 * @param value the value to be set
		 * @param row   the row number
		 * @param col   the column number
		 */
		public void setValueAt(Object value, int row, int col) {
			TableOrder transaction = this.transactionData.get(row);
			switch (col) {
				case 0:
					transaction.setDate((String) value);
					break;
				case 1:
					transaction.setTotal((double) value);
					break;
				default:
					transaction.setSubtotal((double) value);
			}

			fireTableCellUpdated(row, col);
		}

		/**
		 * updateRow when an table is modified, the row must be then updated
		 *
		 * @param transaction the transaction to place in the table and add to the current list
		 *                 of tables
		 * @param row      the row that needs to be updated due to a change in the table
		 */
		public void updateRow(TableOrder transaction, int row) {
			this.transactionData.set(row, transaction);
			fireTableRowsUpdated(row, row);
		}


		/**
		 * insertRow inserts a row in the table with a table
		 *
		 * @param position the position to put the row
		 * @param transaction the food to show on the table
		 */
		public void insertRow(int position, TableOrder transaction) {
			this.transactionData.add(transaction);
			fireTableRowsInserted(0, getRowCount());
		}

		/**
		 * addRow adds a row at the bottom of the table with a new recipe
		 *
		 * @param transaction the food to be placed in the table
		 */
		public void addRow(TableOrder transaction) {
			insertRow(getRowCount(), transaction);
		}

		/**
		 * addRows adds 2+ rows into the table
		 *
		 * @param transactionsList the list of transaction items that are to be put into the table
		 */
		public void addRows(List<TableOrder> transactionsList) {
			for (TableOrder transaction : transactionsList) {
				addRow(transaction);
			}
		}


		/**
		 * removeRow removes a specific row in the table
		 *
		 * @param position the position of the recipe to be removed
		 */
		public void removeRow(int position) {
			this.transactionData.remove(position);
			fireTableRowsDeleted(0, getRowCount());
		}

		/**
		 * getData gets the list of tables
		 *
		 * @return the list of transaction items
		 */
		public List<TableOrder> getData() {
			return transactionData;
		}

		/**
		 * setData gets the list of tables
		 *
		 * @param transactionData the list of transaction items
		 */
		public void setData(List<TableOrder> transactionData) {
			this.transactionData = transactionData;
			fireTableRowsInserted(0, getRowCount());
		}

	}
}
