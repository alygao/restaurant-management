package restaurantManagement1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class WaitingListDialog extends JDialog {

	private JPanel panel;
	private Restaurant restaurant;
	private ImageIcon background;
	private WaitingListTableModel waitingListTableModel;
	private JTable waitingListTable;
	private JButton deleteButton;

	public WaitingListDialog(Restaurant restaurant) {
		this.restaurant = restaurant;
		initUI();
	}

	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		setUndecorated(false);
		setSize(200, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		
		waitingListTableModel = new WaitingListTableModel();
		waitingListTable = new JTable(waitingListTableModel);
		waitingListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		waitingListTable.setBounds(25, 20, 150, 150);
//		waitingListTableModel.addRows(restaurant.getWaitingList().);

		JScrollPane tableListScrollPane = new JScrollPane(waitingListTable);
		tableListScrollPane.setBounds(25, 20, 150, 150);
		panel.add(tableListScrollPane);
		
		deleteButton = new JButton(new ImageIcon(getClass().getResource("delete button.JPG")));
		deleteButton.setBounds(40, 200, 120, 50);
		panel.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			/**
			 * actionPerformed Invoked when an action occurs
			 *
			 * @param press the action that occurs
			 */
			public void actionPerformed(ActionEvent press) {

				

			}
		});


		// background image
		background = new ImageIcon(getClass().getResource("small dialog background.JPG"));
		JLabel backgroundLabel = new JLabel(background);
		backgroundLabel.setBounds(0, 0, 200, 300);
		panel.add(backgroundLabel);

		setVisible(true);
	}

	class WaitingListTableModel extends AbstractTableModel {
		/**
		 * the names of each column in the table
		 */
		private final String[] tableLayoutListColumns = { "Customer Name", "Num. of People" };

		/**
		 * the class type for each column
		 */
		private final Class[] columnClasses = { String.class, int.class };

		/**
		 * the list of recipes that are to be displayed within the table
		 */
		private List<Customer> waitingListData = new ArrayList<>();

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
			return waitingListData.size();
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
		 * getValueAt finds the value at the specific row and column number *
		 * 
		 * @param row the row number
		 * @param col the column number
		 * @return the value at the specific row and column
		 */
		public Object getValueAt(int row, int col) {

			Customer customer = this.waitingListData.get(row);
			switch (col) {
			case 0:
				return customer.getName();
			default:
				return customer.getNumPeople();
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
			Customer customer = this.waitingListData.get(row);
			switch (col) {
			case 0:
				customer.setName((String) value);
				break;
			case 1:
				customer.setNumPeople((int) value);
			}

			fireTableCellUpdated(row, col);
		}

		/**
		 * updateRow when an table is modified, the row must be then updated
		 * 
		 * @param customer the customer to place in the table and add to the current
		 *                 list of tables
		 * @param row      the row that needs to be updated due to a change in the table
		 */
		public void updateRow(Customer customer, int row) {
			this.waitingListData.set(row, customer);
			fireTableRowsUpdated(row, row);
		}

		/**
		 * insertRow inserts a row in the table with a table
		 * 
		 * @param position the position to put the row
		 * @param customer the customer to place in the table and add to the current
		 *                 list of tables
		 */
		public void insertRow(int position, Customer customer) {
			this.waitingListData.add(customer);
			fireTableRowsInserted(0, getRowCount());
		}

		/**
		 * addRow adds a row at the bottom of the table with a new recipe
		 * 
		 * @param customer the table to be placed in the table
		 */
		public void addRow(Customer customer) {
			insertRow(getRowCount(), customer);
		}

		/**
		 * addRows adds 2+ rows into the table
		 * 
		 * @param tableList the list of tables that are to be put into the table
		 */
		public void addRows(List<Customer> waitingListData) {
			for (Customer customer : waitingListData) {
				addRow(customer);
			}
		}

		/**
		 * removeRow removes a specific row in the table
		 * 
		 * @param position the position of the recipe to be removed
		 */
		public void removeRow(int position) {
			this.waitingListData.remove(position);
			fireTableRowsDeleted(0, getRowCount());
		}

		/**
		 * getData gets the list of tables
		 * 
		 * @return the list of customers
		 */
		public List<Customer> getData() {
			return waitingListData;
		}

		/**
		 * setData gets the list of tables
		 * 
		 * @param data the list of tables
		 */
		public void setData(List<Customer> waitingListData) {
			this.waitingListData = waitingListData;
			fireTableRowsInserted(0, getRowCount());
		}

//		/**
//		 * clearAll
//		 * clears all rows in the table
//		 */
//		public void clearAll() {
//			setData(new List <Table>());
//			fireTableDataChanged();
//		}
	}

}
