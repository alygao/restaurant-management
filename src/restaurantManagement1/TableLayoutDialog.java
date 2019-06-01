package restaurantManagement1;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;

public class TableLayoutDialog extends JDialog {

	private List<Table> tables;
	private String numPeople;
	private boolean canBeReserved;

	private JPanel addTablePanel;
	private JPanel viewTablesPanel;
	private JSpinner numSeatsSpinner;
	private JCheckBox canBeReservedCheckBox;
	private JButton addTableButton;
	private JButton deleteTableButton;
	private TableLayoutTableModel tableLayoutTableModel;
	private JTable tableLayoutTable;

	public TableLayoutDialog(List<Table> tables) {
		this.tables = tables;
		initUI();
	}

	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

//		setUndecorated(true);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		addTablePanel = new JPanel();
		addTablePanel.setLayout(null);
		getContentPane().add(addTablePanel);

		viewTablesPanel = new JPanel();
		viewTablesPanel.setLayout(null);
		getContentPane().add(viewTablesPanel);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Add Table", null, addTablePanel);
		tabbedPane.addTab("View Tables", null, viewTablesPanel);
		getContentPane().add(tabbedPane);

		// Add Table Panel
		JLabel numSeatsLabel = new JLabel("Number of Seats:");
		numSeatsLabel.setBounds(25, 50, 300, 30);

//		this.numSeatsTextField = new JTextField();
//		this.numSeatsTextField.setBounds(150, 50, 300, 30);
		addTablePanel.add(numSeatsLabel);
//		addTablePanel.add(numSeatsTextField);

		SpinnerModel tableSpinnerModel = new SpinnerNumberModel(2, 2, 10, 2);
		numSeatsSpinner = new JSpinner(tableSpinnerModel);
		numSeatsSpinner.setBounds(150, 50, 50, 30);
		addTablePanel.add(numSeatsSpinner);

		canBeReservedCheckBox = new JCheckBox("Reservable", false);
		canBeReservedCheckBox.setBounds(25, 100, 100, 30);
		addTablePanel.add(canBeReservedCheckBox);

		addTableButton = new JButton("Add");
		addTableButton.setBounds(50, 300, 125, 50);
		addTableButton.addActionListener(new ButtonListener());
		addTablePanel.add(addTableButton);

		// View Table Panel
		tableLayoutTableModel = new TableLayoutTableModel();
		tableLayoutTable = new JTable(tableLayoutTableModel);
		tableLayoutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLayoutTable.setBounds(25, 50, 400, 400);
		tableLayoutTableModel.addRows(tables);

		JScrollPane tableListScrollPane = new JScrollPane(tableLayoutTable);
		tableListScrollPane.setBounds(25, 50, 400, 400);
		viewTablesPanel.add(tableListScrollPane);
		
		deleteTableButton = new JButton("Delete");
		deleteTableButton.setBounds(150, 475, 100, 50);
		deleteTableButton.addActionListener(new ButtonListener());
		viewTablesPanel.add(deleteTableButton);
		
		
		
		setVisible(true);
	}
	
	class ButtonListener implements ActionListener {

		/**
		 * actionPerformed 
		 * performs the action that is needed to be performed from clicking a button
		 * @param press used to determine which button is pressed
		 */
		public void actionPerformed(ActionEvent press) {
			if (press.getSource() == addTableButton) {
				Table table = new Table ((int)numSeatsSpinner.getValue(),canBeReservedCheckBox.isSelected());
				tables.add(table);
				table.setTableNum(tables.indexOf(table));
				tableLayoutTableModel.addRow(table);
			}else if (press.getSource() == deleteTableButton) {
				int selectedRow = tableLayoutTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "Please choose a table to delete.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					tableLayoutTableModel.removeRow(selectedRow);
					tables.remove(selectedRow); 
					
					//re-organize table numbers
					for (int i = 0; i < tables.size(); i++) {
						tables.get(i).setTableNum(i);
					}
				}
			}
		}
	}
	
	class TableLayoutTableModel extends AbstractTableModel{
		/**
		 * the names of each column in the table
		 */
		private final String[] tableLayoutListColumns = { "Table Num.", "Num. of Seats", "Reservable" };
	
		/**
		 * the class type for each column
		 */
		private final Class[] columnClasses = { int.class, int.class, boolean.class};
	
		/**
		 * the list of recipes that are to be displayed within the table
		 */
		private List<Table> tablesData = new ArrayList<>();
		
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
			return tablesData.size();
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

			Table table = this.tablesData.get(row);
			switch (col) {
			case 0:
				return table.getTableNum();
			case 1:
				return table.getNumSeats();
			// case 2:
			default:
				return table.canBeReserved();
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
			Table table = this.tablesData.get(row);
			switch (col) {
			case 0:
				table.setTableNum((int) value);
				break;
			case 1:
				table.setNumSeats((int) value);
			// case 2:
			default:
				table.setCanBeReserved((boolean) value);
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
			this.tablesData.set( row, table );
			fireTableRowsUpdated( row, row );
		}

		/**
		 * insertRow
		 * inserts a row in the table with a table
		 * @param position the position to put the row 
		 * @param table the table to place in the table and add to the current list of tables
		 */
		public void insertRow(int position, Table table) {
			this.tablesData.add(table);
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
			this.tablesData.remove(position);
			fireTableRowsDeleted(0, getRowCount());
		}

		/**
		 * getData
		 * gets the list of tables
		 * @return the list of tables
		 */
		public List<Table> getData() {
			return tablesData;
		}

		/**
		 * setData
		 * gets the list of tables
		 * @param data the list of tables
		 */
		public void setData(List<Table> tablesData) {
			this.tablesData = tablesData;
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
