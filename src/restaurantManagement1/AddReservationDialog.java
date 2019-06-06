package restaurantManagement1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;

public class AddReservationDialog extends JDialog {

	private Restaurant restaurant;
	private List<Table> availableTableForReservation;
	private ReserveTimePeriod reserveTimePeriod;
	private Reservation reservation;

	private JTextField reserveNameTextField;
	private JSpinner numPeopleSpinner;
	private DatePicker datePicker;
	private TimePicker timePicker;

	private JPanel panel;
	private JButton findAvailabilityButton;
	private JButton bookReservationButton;
	private JButton returnButton;

	private AvailableReservationTableModel reserveTableModel;
	private JTable possibleReserveTablesTable;

	private AddReservationDialog self = this;

	public AddReservationDialog(Restaurant restaurant) {
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
		
		// Date
		JLabel dateLabel = new JLabel("Date:");
		dateLabel.setBounds(25, 150, 300, 30);
		panel.add(dateLabel);
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		datePicker = new DatePicker(dateSettings);
		dateSettings.setDateRangeLimits( LocalDate.now(), LocalDate.now().plusDays( 60 ) );
		datePicker.setBounds( 150, 150, 200, 30 );
		datePicker.setDateToToday();
		datePicker.getComponentDateTextField().setEditable(false);
		panel.add( datePicker );
		
		// Time
		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setBounds(25, 200, 200, 30);
		panel.add(timeLabel);
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.setColor(TimeArea.TimePickerTextValidTime, Color.black);
        timeSettings.initialTime = LocalTime.now();
        timePicker = new TimePicker(timeSettings);
        timePicker.setBounds(150, 200, 150, 30);
        timePicker.getComponentTimeTextField().setEditable(false);
//        timePicker.setTimeToNow();
        panel.add( timePicker );

		findAvailabilityButton = new JButton("Find Availability");
		findAvailabilityButton.setBounds(100, 300, 200, 30);
		findAvailabilityButton.addActionListener(new ButtonListener());
		panel.add(findAvailabilityButton);

		returnButton = new JButton("Return");
		returnButton.setBounds(100, 350, 200, 30);
		returnButton.addActionListener(new ButtonListener());
		panel.add(returnButton);

		bookReservationButton = new JButton("Book Reservation");
		bookReservationButton.setBounds(800, 200, 150, 30);
		bookReservationButton.addActionListener(new ButtonListener());
		bookReservationButton.setVisible( false );
		panel.add(bookReservationButton);

		setVisible(true);

	}

	public void displayAvailableTables(List<Table> availableTableForReservation) {

//		bookReservationButton = new JButton("Book Reservation");
//		bookReservationButton.setBounds(800, 200, 150, 30);
//		bookReservationButton.addActionListener(new ButtonListener());
//		panel.add(bookReservationButton);
		bookReservationButton.setVisible( true );

		reserveTableModel = new AvailableReservationTableModel();
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
				
				if (restaurant.getTables().size() == 0) {
					JOptionPane.showMessageDialog(null, "Your restaurant currently has no tables.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				reserveTimePeriod = new ReserveTimePeriod(datePicker.getText(), timePicker.getText());
				availableTableForReservation = restaurant.findAvailableTableForReservation((int) numPeopleSpinner.getValue(), reserveTimePeriod);
				self.displayAvailableTables(availableTableForReservation);
			} else if (press.getSource() == bookReservationButton) {
				int selectedRow = possibleReserveTablesTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "Please choose a table to book a reservation.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				reservation = new Reservation(availableTableForReservation.get(selectedRow), reserveNameTextField.getText(), (int) numPeopleSpinner.getValue(),
						reserveTimePeriod);
				restaurant.bookReservation(reservation);
				dispose();

			} else if (press.getSource() == returnButton) {
				dispose();
//				ReservationBookDialog reservationDialog = new ReservationBookDialog(restaurant);
			
			}
		}
	}

	class AvailableReservationTableModel extends AbstractTableModel {
		/**
		 * the names of each column in the table
		 */
		private final String[] tableLayoutListColumns = { "Table Name", "Num. of Seats" };

		/**
		 * the class type for each column
		 */
		private final Class[] columnClasses = { String.class, int.class };

		/**
		 * the list of tables that are to be displayed within the table
		 */
		private List<Table> reserveTablesData = new ArrayList<>();

		@Override
		/**
		 * getColumnCount the number of columns in the table
		 * 
		 * @return the number of columns
		 */
		public int getColumnCount() {
			return this.tableLayoutListColumns.length;
		}

		@Override
		/**
		 * getRowCount the number of rows in the table
		 * 
		 * @return the number of rows
		 */
		public int getRowCount() {
			return reserveTablesData.size();
		}

		@Override
		/**
		 * getColumnName
		 * 
		 * @param col the column number
		 * @return the name of the column
		 */
		public String getColumnName(int col) {
			return this.tableLayoutListColumns[col];
		}

		@Override
		/**
		 * getValueAt finds the value at the specific row and column number
		 * 
		 * @param row the row number
		 * @param col the column number
		 * @return the value at the specific row and column
		 */
		public Object getValueAt(int row, int col) {

			Table table = this.reserveTablesData.get(row);
			switch (col) {
			case 0:
				return table.getTableName();
			default:
				return table.getNumSeats();
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
			Table table = this.reserveTablesData.get(row);
			switch (col) {
			case 0:
				table.setTableName((String) value);
				break;
			default:
				table.setNumSeats((int) value);
			}

			fireTableCellUpdated(row, col);
		}

		/**
		 * updateRow when an table is modified, the row must be then updated
		 * 
		 * @param table the recipe to place in the table and add to the current list of
		 *              tables
		 * @param row   the row that needs to be updated due to a change in the table
		 */
		public void updateRow(Table table, int row) {
			this.reserveTablesData.set(row, table);
			fireTableRowsUpdated(row, row);
		}

		/**
		 * insertRow inserts a row in the table with a table
		 * 
		 * @param position the position to put the row
		 * @param table    the table to place in the table and add to the current list
		 *                 of tables
		 */
		public void insertRow(int position, Table table) {
			this.reserveTablesData.add(table);
			fireTableRowsInserted(0, getRowCount());
		}

		/**
		 * addRow adds a row at the bottom of the table with a new recipe
		 * 
		 * @param table the table to be placed in the table
		 */
		public void addRow(Table table) {
			insertRow(getRowCount(), table);
		}

		/**
		 * addRows adds 2+ rows into the table
		 * 
		 * @param tableList the list of tables that are to be put into the table
		 */
		public void addRows(List<Table> tableList) {
			for (Table table : tableList) {
				addRow(table);
			}
		}

		/**
		 * removeRow removes a specific row in the table
		 * 
		 * @param position the position of the recipe to be removed
		 */
		public void removeRow(int position) {
			this.reserveTablesData.remove(position);
			fireTableRowsDeleted(0, getRowCount());
		}

		/**
		 * getData gets the list of tables
		 * 
		 * @return the list of tables
		 */
		public List<Table> getData() {
			return reserveTablesData;
		}

		/**
		 * setData gets the list of tables
		 * 
		 * @param data the list of tables
		 */
		public void setData(List<Table> tablesData) {
			this.reserveTablesData = tablesData;
			fireTableRowsInserted(0, getRowCount());
		}
	}

}
