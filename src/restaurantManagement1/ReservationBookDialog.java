package restaurantManagement1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import restaurantManagement1.AddReservationDialog.AvailableReservationTableModel;

public class ReservationBookDialog extends JDialog {

	private Restaurant restaurant;
	private JPanel panel;
	private DatePicker datePicker;

	private JButton lookUpButton;
	private JButton addReservationButton;
	private ViewReservationsTableModel viewReservationsTableModel;
	private JTable viewReservationsTable;

	public ReservationBookDialog(Restaurant restaurant) {
		this.restaurant = restaurant;
		initUI();
	}

	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		setUndecorated(false); // TODO : change to false
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);

		// Date
		JLabel dateLabel = new JLabel("Reservation Date:");
		dateLabel.setBounds(25, 50, 100, 30);
		panel.add(dateLabel);
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		datePicker = new DatePicker(dateSettings);
		dateSettings.setDateRangeLimits(LocalDate.now(), LocalDate.now().plusDays(60));
		datePicker.setBounds(150, 50, 200, 30);
		datePicker.setDateToToday();
		datePicker.getComponentDateTextField().setEditable(false);
		panel.add(datePicker);

		lookUpButton = new JButton("Look Up");
		lookUpButton.setBounds(25, 100, 100, 30);
		lookUpButton.addActionListener(new ButtonListener());
		panel.add(lookUpButton);

		addReservationButton = new JButton("<html>Add<p>Reservation</html>");
		addReservationButton.setBounds(850, 100, 125, 125);
		addReservationButton.addActionListener(new ButtonListener());
		panel.add(addReservationButton);

		viewReservationsTableModel = new ViewReservationsTableModel();
		viewReservationsTable = new JTable(viewReservationsTableModel);
		viewReservationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		viewReservationsTable.setBounds(25, 150, 500, 400);
		viewReservationsTableModel.addRows(restaurant.getReservationBook());

		JScrollPane tableListScrollPane = new JScrollPane(viewReservationsTable);
		tableListScrollPane.setBounds(25, 150, 500, 400);
		panel.add(tableListScrollPane);

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
			if (press.getSource() == lookUpButton) {
				List<Reservation> reservationsUnderSpecifiedDate = new ArrayList<>();
				for (int i = 0; i < restaurant.getReservationBook().size(); i++) {
					if (restaurant.getReservationBook().get(i).getReserveTimePeriod().getDate()
							.equals(datePicker.getText())) {
						reservationsUnderSpecifiedDate.add(restaurant.getReservationBook().get(i));
					}
				}

				viewReservationsTableModel.clearAll();
				viewReservationsTableModel.addRows(reservationsUnderSpecifiedDate);

			} else if (press.getSource() == addReservationButton) {
				AddReservationDialog addReservationDialog = new AddReservationDialog(restaurant);
//				dispose();
			}
		}
	}

	class ViewReservationsTableModel extends AbstractTableModel {
		/**
		 * the names of each column in the table
		 */
		private final String[] tableLayoutListColumns = { "Customer Name", "Num. of People", "Time", "Table No." };

		/**
		 * the class type for each column
		 */
		private final Class[] columnClasses = { String.class, int.class, String.class, int.class };

		/**
		 * the list of tables that are to be displayed within the table
		 */
		private List<Reservation> reservationData = new ArrayList<>();

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
			return reservationData.size();
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

			Reservation reservation = this.reservationData.get(row);
			switch (col) {
			case 0:
				return reservation.getCustomer().getName();
			case 1:
				return reservation.getCustomer().getNumPeople();
			case 2:
				return reservation.getReserveTimePeriod().getTime();
			default:
				return reservation.getTable().getTableNum();
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
			Reservation reservation = this.reservationData.get(row);
			switch (col) {
			case 0:
				reservation.getCustomer().setName((String) value);
				break;
			case 1:
				reservation.getCustomer().setNumPeople((int) value);
				break;
			case 2:
				reservation.getReserveTimePeriod().setTime((String) value);
				break;
			default:
				reservation.getTable().setTableNum((int) value);
			}

			fireTableCellUpdated(row, col);
		}

		/**
		 * updateRow when an table is modified, the row must be then updated
		 * 
		 * @param reservation the reservation to place in the table and add to the
		 *                    current list of tables
		 * @param row         the row that needs to be updated due to a change in the
		 *                    table
		 */
		public void updateRow(Reservation reservation, int row) {
			this.reservationData.set(row, reservation);
			fireTableRowsUpdated(row, row);
		}

		/**
		 * insertRow inserts a row in the table with a table
		 * 
		 * @param position    the position to put the row
		 * @param reservation the reservation to place in the table and add to the
		 *                    current list of tables
		 */
		public void insertRow(int position, Reservation reservation) {
			this.reservationData.add(reservation);
			fireTableRowsInserted(0, getRowCount());
		}

		/**
		 * addRow adds a row at the bottom of the table with a new reservation
		 * 
		 * @param reservation the reservation to be placed in the table
		 */
		public void addRow(Reservation reservation) {
			insertRow(getRowCount(), reservation);
		}

		/**
		 * addRows adds 2+ rows into the table
		 * 
		 * @param tableList the list of tables that are to be put into the table
		 */
		public void addRows(List<Reservation> reservationList) {
			for (Reservation reservation : reservationList) {
				addRow(reservation);
			}
		}

		/**
		 * removeRow removes a specific row in the table
		 * 
		 * @param position the position of the recipe to be removed
		 */
		public void removeRow(int position) {
			this.reservationData.remove(position);
			fireTableRowsDeleted(0, getRowCount());
		}

		/**
		 * getData gets the list of tables
		 * 
		 * @return the list of reservations
		 */
		public List<Reservation> getData() {
			return reservationData;
		}

		/**
		 * setData gets the list of reservations
		 * 
		 * @param reservationData the list of tables
		 */
		public void setData(List<Reservation> reservationData) {
			this.reservationData = reservationData;
			fireTableRowsInserted(0, getRowCount());
		}

		public void clearAll() {
			for (int i = 0; i < reservationData.size(); i++) {
				removeRow(i);
			}
		}
	}

}
