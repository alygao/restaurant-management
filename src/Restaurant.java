package restaurantManagement1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Restaurant extends JFrame {

	private List<Table> tables = new ArrayList<>();
	private List<Reservation> reservationBook = new ArrayList<>();
	private Restaurant self = this;
	private JPanel mainPanel;
	private JButton orderButton;
	private JButton transactionButton;
	private JButton tipButton;
	private JButton reservationButton;
	private JButton reportingButton;
	private JButton menuButton;
	private JButton tableLayoutButton;
	private JButton employeeButton;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Restaurant restaurant = new Restaurant();
				restaurant.setVisible(true);
			}
		});
	}

	public Restaurant() {
		initUI();
	}

	private void initUI() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		getContentPane().add(mainPanel);

		orderButton = new JButton("Orders");
		orderButton.setBounds(50, 150, 125, 125);
		orderButton.addActionListener(new ButtonListener());
		mainPanel.add(orderButton);

		transactionButton = new JButton("Transactions");
		transactionButton.setBounds(200, 150, 125, 125);
		transactionButton.addActionListener(new ButtonListener());
		mainPanel.add(transactionButton);

		tipButton = new JButton("Tips");
		tipButton.setBounds(350, 150, 125, 125);
		tipButton.addActionListener(new ButtonListener());
		mainPanel.add(tipButton);

		reservationButton = new JButton("Reservations");
		reservationButton.setBounds(500, 150, 125, 125);
		reservationButton.addActionListener(new ButtonListener());
		mainPanel.add(reservationButton);

		menuButton = new JButton("Menu");
		menuButton.setBounds(50, 300, 125, 125);
		menuButton.addActionListener(new ButtonListener());
		mainPanel.add(menuButton);

		tableLayoutButton = new JButton("Table Layout");
		tableLayoutButton.setBounds(200, 300, 125, 125);
		tableLayoutButton.addActionListener(new ButtonListener());
		mainPanel.add(tableLayoutButton);
		
		employeeButton = new JButton("Employees");
		employeeButton.setBounds(350, 300, 125, 125);
		employeeButton.addActionListener(new ButtonListener());
		mainPanel.add(employeeButton);

		reportingButton = new JButton("Reporting");
		reportingButton.setBounds(500, 300, 125, 125);
		reportingButton.addActionListener(new ButtonListener());
		mainPanel.add(reportingButton);

		// icon image and size
		setDefaultLookAndFeelDecorated(true);
//		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("recipe icon.png")));
		setTitle("Restaurant Management");
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);

		// background image
//		menuBackground = new ImageIcon(getClass().getResource("menu background.JPG"));

	}

	public List<Table> getReservableTables() {
		List<Table> reservableTables = new ArrayList<>();
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).canBeReserved()) {
				reservableTables.add(tables.get(i));
			}
		}
		return reservableTables;
	}

	public List<Table> findAvailableTableForReservation(String name, int numPeople,
			ReserveTimePeriod reserveTimePeriod) {
		// save reserve tables to an array
		// check date and save already made reservations to an array
		// check each reservation to see if its within the range
		// if not, remove table from reserve tables
		// make sure to not check if table has already been removed

		List<Reservation> savedReservationsForDate = new ArrayList<>();
		List<Table> availableTableForReservation = new ArrayList<>();

		for (int i = 0; i < reservationBook.size(); i++) {
			if (reservationBook.get(i).getReserveTimePeriod().getDate().equals(reserveTimePeriod.getDate()) && reservationBook.get(i).getCustomer().getNumPeople() >= numPeople) {
				savedReservationsForDate.add(reservationBook.get(i));
			}
		}
		
		for (int i = 0; i < savedReservationsForDate.size(); i++) {
			availableTableForReservation.add(savedReservationsForDate.get(i).getTable());
		}

		for (int i = 0; i < savedReservationsForDate.size(); i++) {
			if (availableTableForReservation.contains(savedReservationsForDate.get(i).getTable())) {
				if ((savedReservationsForDate.get(i).getReserveTimePeriod().getTime() - 2 >= reserveTimePeriod.getTime())
						|| (savedReservationsForDate.get(i).getReserveTimePeriod().getTime() + 2 <= reserveTimePeriod
								.getTime())) {
					availableTableForReservation.remove(savedReservationsForDate.get(i).getTable());
				}
			}
		}

		return availableTableForReservation;
	}

	class ButtonListener implements ActionListener {

		/**
		 * actionPerformed performs the action that is needed to be performed from
		 * clicking a button
		 * 
		 * @param press used to determine which button is pressed
		 */
		public void actionPerformed(ActionEvent press) {
			if (press.getSource() == orderButton) {

			} else if (press.getSource() == tableLayoutButton) {
				TableLayoutDialog tableLayoutDialog = new TableLayoutDialog(tables);

			} else if (press.getSource() == reservationButton) {
				ReservationDialog reservationDialog = new ReservationDialog(self);

			}
		}
	}
}
