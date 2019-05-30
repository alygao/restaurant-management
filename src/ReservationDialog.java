package restaurantManagement1;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;

import restaurantManagement1.TableLayoutDialog.TableLayoutTableModel;

public class ReservationDialog extends JDialog {

	private Restaurant restaurant;
	private JTextField reserveNameTextField;
	private JSpinner numPeopleSpinner;
	private JSpinner dateSpinner;
	private JSpinner hourTimeSpinner;
	private JSpinner minTimeSpinner;

	private JPanel panel;
	private JButton findAvailabilityButton;
	private JButton bookReservationButton;
	private JButton returnButton;
	
	private ReserveTableModel reserveTableModel;
	private JTable possibleReserveTablesTable;
	
	private ReservationDialog self = this;

	public ReservationDialog(Restaurant restaurant) {
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

		// GUI Components
		JLabel reserveNameLabel = new JLabel("Reservation Name:");
		reserveNameLabel.setBounds(25, 50, 300, 30);
		panel.add(reserveNameLabel);

		reserveNameTextField = new JTextField();
		reserveNameTextField.setBounds(150, 50, 300, 30);
		panel.add(reserveNameTextField);

		JLabel numPeopleLabel = new JLabel("Number of People:");
		numPeopleLabel.setBounds(25, 100, 300, 30);
		panel.add(numPeopleLabel);

		SpinnerModel tableSpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
		numPeopleSpinner = new JSpinner(tableSpinnerModel);
		numPeopleSpinner.setBounds(150, 100, 50, 30);
		panel.add(numPeopleSpinner);

		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setBounds(25, 150, 300, 30);
		panel.add(dateLabel);

		dateSpinner = new JSpinner(new SpinnerDateModel()); 
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner,"dd/MM/yyyy"));
		dateSpinner.setBounds(150, 150, 200, 30);
		panel.add(dateSpinner);

		JLabel timeLabel = new JLabel("Time:                                                 :");
		timeLabel.setBounds(25, 200, 200, 30);
		panel.add(timeLabel);
		SpinnerModel hourTimeSpinnerModel = new SpinnerNumberModel(0, 0, 24, 1);
		hourTimeSpinner = new JSpinner(hourTimeSpinnerModel);
		hourTimeSpinner.setBounds(150, 200, 50, 30);
		panel.add(hourTimeSpinner);
		SpinnerModel minTimeSpinnerModel = new SpinnerNumberModel(00, 00, 45, 15);
		minTimeSpinner = new JSpinner(minTimeSpinnerModel);
		minTimeSpinner.setBounds(210, 200, 60, 30);
		panel.add(minTimeSpinner);

		findAvailabilityButton = new JButton("Find Availability");
		findAvailabilityButton.setBounds(100, 300, 200, 30);
		findAvailabilityButton.addActionListener(new ButtonListener());
		panel.add(findAvailabilityButton);

		returnButton = new JButton("Return");
		returnButton.setBounds(100, 350, 200, 30);
		returnButton.addActionListener(new ButtonListener());
		panel.add(returnButton);
		setVisible(true);
	}
	
	public void displayAvailableTables(List<Table> availableTableForReservation) {
		reserveTableModel = new ReserveTableModel();
		possibleReserveTablesTable = new JTable(reserveTableModel);
		possibleReserveTablesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		possibleReserveTablesTable.setBounds(500, 50, 200, 400);
		reserveTableModel.addRows(availableTableForReservation);

		JScrollPane tableListScrollPane = new JScrollPane(possibleReserveTablesTable);
		tableListScrollPane.setBounds(500, 50, 200, 400);
		panel.add(tableListScrollPane);
	}

	class ButtonListener implements ActionListener {

		/**
		 * actionPerformed performs the action that is needed to be performed from
		 * clicking a button
		 * 
		 * @param press used to determine which button is pressed
		 */
		public void actionPerformed(ActionEvent press) {
			if (press.getSource() == findAvailabilityButton) {
				double time = (Integer) hourTimeSpinner.getValue() + ((Integer) minTimeSpinner.getValue())*0.01;
				Date date = (Date) dateSpinner.getValue();
				
				ReserveTimePeriod reserveTimePeriod = new ReserveTimePeriod(date, time);
				List<Table> availableTableForReservation = restaurant.findAvailableTableForReservation(reserveNameTextField.getText(),
						(int) numPeopleSpinner.getValue(), reserveTimePeriod);
				self.displayAvailableTables(availableTableForReservation);
						

			} else if (press.getSource() == returnButton) {
				dispose();
			}
		}
	}
	
	class ReserveTableModel extends AbstractTableModel{
		/**
		 * the names of each column in the table
		 */
		private final String[] tableLayoutListColumns = { "Table Num.", "Num. of Seats" };
	
		/**
		 * the class type for each column
		 */
		private final Class[] columnClasses = { int.class, int.class};
	
		/**
		 * the list of tables that are to be displayed within the table
		 */
		private List<Table> reserveTablesData = new ArrayList<>();
		
		@Override
		/**
		 * getColumnCount
		 * the number of columns in the table
		 * @return the number of columns
		 */
		public int getColumnCount() {
			return this.tableLayoutListColumns.length;
		}

		@Override
		/**
		 * getRowCount
		 * the number of rows in the table
		 * @return the number of rows
		 */
		public int getRowCount() {
			return reserveTablesData.size();
		}

		@Override
		/**
		 * getColumnName
		 * @param col the column number
		 * @return the name of the column
		 */
		public String getColumnName(int col) {
			return this.tableLayoutListColumns[col];
		}

		@Override
		/**
		 * getValueAt
		 * finds the value at the specific row and column number
		 * @param row the row number
		 * @param col the column number
		 * @return the value at the specific row and column
		 */
		public Object getValueAt(int row, int col) {

			Table table = this.reserveTablesData.get(row);
			switch (col) {
			case 0:
				return table.getTableNum();
			default:
				return table.getNumSeats();
			}
		}

		@Override
		/**
		 * getColumnClass
		 * finds the class type for a specific column
		 * @param col the column number
		 * @return the class type for the specific column
		 */
		public Class<?> getColumnClass(int col) {
			return this.columnClasses[col];
		}

		@Override
		/**
		 * isCellEditable
		 * checks if the user can edit the cell
		 * @param row the row number
		 * @param col the column number
		 * @return whether or not the cell is editable
		 */
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		@Override
		/**
		 * setValueAt
		 * sets a value at the specific row and column
		 * @param value the value to be set
		 * @param row the row number
		 * @param col the column number
		 */
		public void setValueAt(Object value, int row, int col) {
			Table table = this.reserveTablesData.get(row);
			switch (col) {
			case 0:
				table.setTableNum((int) value);
				break;
			default:
				table.setNumSeats((int) value);
			}

			fireTableCellUpdated(row, col);
		}
		
		/**
		 * updateRow
		 * when an table is modified, the row must be then updated
		 * @param table the recipe to place in the table and add to the current list of tables
		 * @param row the row that needs to be updated due to a change in the table
		 */
		public void updateRow ( Table table, int row ) {
			this.reserveTablesData.set( row, table );
			fireTableRowsUpdated( row, row );
		}

		/**
		 * insertRow
		 * inserts a row in the table with a table
		 * @param position the position to put the row 
		 * @param table the table to place in the table and add to the current list of tables
		 */
		public void insertRow(int position, Table table) {
			this.reserveTablesData.add(table);
			fireTableRowsInserted(0, getRowCount());
		}

		/**
		 * addRow
		 * adds a row at the bottom of the table with a new recipe
		 * @param table the table to be placed in the table
		 */
		public void addRow(Table table) {
			insertRow(getRowCount(), table);
		}

		/**
		 * addRows
		 * adds 2+ rows into the table
		 * @param tableList the list of tables that are to be put into the table
		 */
		public void addRows(List<Table> tableList) {
			for (Table table : tableList) {
				addRow(table);
			}
		}

		/**
		 * removeRow
		 * removes a specific row in the table
		 * @param position the position of the recipe to be removed
		 */
		public void removeRow(int position) {
			this.reserveTablesData.remove(position);
			fireTableRowsDeleted(0, getRowCount());
		}

		/**
		 * getData
		 * gets the list of tables
		 * @return the list of tables
		 */
		public List<Table> getData() {
			return reserveTablesData;
		}

		/**
		 * setData
		 * gets the list of tables
		 * @param data the list of tables
		 */
		public void setData(List<Table> tablesData) {
			this.reserveTablesData = tablesData;
			fireTableRowsInserted(0, getRowCount());
		}
	}

}
