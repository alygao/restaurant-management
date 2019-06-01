package restaurantManagement1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Restaurant extends JFrame {

	private List<Table> tables = new ArrayList<>();
	private List<Reservation> reservationBook = new ArrayList<>();
	private List<MenuItem> menu = new ArrayList<>();
	private Queue<Customer> waitingList = new Queue<>();
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
	private ImageIcon homepageBackground;

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

		// background image
		homepageBackground = new ImageIcon(getClass().getResource("freshqo homepage.JPG"));
		JLabel homepageBackgroundLabel = new JLabel(homepageBackground);
		homepageBackgroundLabel.setBounds(0,0,1000,600);		
		mainPanel.add(homepageBackgroundLabel);

		orderButton = new JButton(new ImageIcon(getClass().getResource("orders button.JPG")));
		orderButton.setBounds(50, 150, 125, 125);
		orderButton.addActionListener(new ButtonListener());
		mainPanel.add(orderButton);

		transactionButton = new JButton(new ImageIcon(getClass().getResource("transactions button.JPG")));
		transactionButton.setBounds(200, 150, 125, 125);
		transactionButton.addActionListener(new ButtonListener());
		mainPanel.add(transactionButton);

		tipButton = new JButton(new ImageIcon(getClass().getResource("tips button.JPG")));
		tipButton.setBounds(350, 150, 125, 125);
		tipButton.addActionListener(new ButtonListener());
		mainPanel.add(tipButton);

		menuButton = new JButton(new ImageIcon(getClass().getResource("menu button.JPG")));
		menuButton.setBounds(500, 150, 125, 125);
		menuButton.addActionListener(new ButtonListener());
		mainPanel.add(menuButton);

		reservationButton = new JButton(new ImageIcon(getClass().getResource("reservations button.JPG")));
		reservationButton.setBounds(50, 300, 125, 125);
		reservationButton.addActionListener(new ButtonListener());
		mainPanel.add(reservationButton);

		tableLayoutButton = new JButton(new ImageIcon(getClass().getResource("general button.JPG")));
		tableLayoutButton.setBounds(200, 300, 125, 125);
		tableLayoutButton.addActionListener(new ButtonListener());
		mainPanel.add(tableLayoutButton);

		employeeButton = new JButton(new ImageIcon(getClass().getResource("employee button.JPG")));
		employeeButton.setBounds(350, 300, 125, 125);
		employeeButton.addActionListener(new ButtonListener());
		mainPanel.add(employeeButton);

		reportingButton = new JButton(new ImageIcon(getClass().getResource("reporting button.JPG")));
		reportingButton.setBounds(500, 300, 125, 125);
		reportingButton.addActionListener(new ButtonListener());
		mainPanel.add(reportingButton);

		// icon image and size
		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("freshqo icon.JPG")));
		setTitle("Freshqo Management");
		setSize(1000, 600);
		setResizable(false);
		setUndecorated(false); // TODO: change to true
		setLocationRelativeTo(null);

	}
	
	public List<MenuItem> getMenu() {
		return menu;
	}
	
	public List<MenuItem> getAppetizerMenu() {
		List<MenuItem> appetizerMenu = new ArrayList<>();
		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i).getCategory().equals("Appetizer")) {
				appetizerMenu.add(menu.get(i));
			}
		}
		return appetizerMenu;
	}
	
	public List<MenuItem> getEntreeMenu() {
		List<MenuItem> entreeMenu = new ArrayList<>();
		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i).getCategory().equals("Entree")) {
				entreeMenu.add(menu.get(i));
			}
		}
		return entreeMenu;
	}
	
	public List<MenuItem> getDessertMenu() {
		List<MenuItem> dessertMenu = new ArrayList<>();
		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i).getCategory().equals("Dessert")) {
				dessertMenu.add(menu.get(i));
			}
		}
		return dessertMenu;
	}
	
	public List<MenuItem> getBeverageMenu() {
		List<MenuItem> beverageMenu = new ArrayList<>();
		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i).getCategory().equals("Beverage")) {
				beverageMenu.add(menu.get(i));
			}
		}
		return beverageMenu;
	}

	public List<Table> getTables() {
		return tables;
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

	public List<Table> getReservableTables(int numPeople) {
		List<Table> reservableTables = new ArrayList<>();
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).canBeReserved() && tables.get(i).getNumSeats() >= numPeople) {
				reservableTables.add(tables.get(i));
			}
		}
		return reservableTables;
	}

	public List<Table> findAvailableTableForReservation(int numPeople, ReserveTimePeriod reserveTimePeriod) {
		// save reserve tables to an array
		// check date and save "already made" reservations to an array
		// check each reservation to see if its within the range
		// if not, remove table from reserve tables
		// make sure to not check if table has already been removed

		List<Table> availableTableForReservation = getReservableTables(numPeople);
		List<Reservation> savedReservationsForDate = new ArrayList<>();

//		System.out.println("Reserve date is " + reserveTimePeriod.getDate());
		for (int i = 0; i < reservationBook.size(); i++) {
			System.out.println("date of reservation: " + reservationBook.get(i).getReserveTimePeriod().getDate());
			if (reservationBook.get(i).getReserveTimePeriod().getDate()
					.equals (reserveTimePeriod.getDate())) {
				savedReservationsForDate.add(reservationBook.get(i));
			}
		}
		System.out.println(savedReservationsForDate.size());
		for (int i = 0; i < savedReservationsForDate.size(); i++) {
			if (availableTableForReservation.contains(savedReservationsForDate.get(i).getTable())) {
				if ((savedReservationsForDate.get(i).getReserveTimePeriod().getTime() - 2 <= reserveTimePeriod
						.getTime())
						&& (savedReservationsForDate.get(i).getReserveTimePeriod().getTime() + 2 >= reserveTimePeriod
								.getTime())) {
					availableTableForReservation.remove(savedReservationsForDate.get(i).getTable());
				}
			}
		}

		System.out.println(availableTableForReservation.size());

		return availableTableForReservation;
	}

	public void bookReservation(Reservation reservation) {
		reservationBook.add(reservation);
		JOptionPane.showMessageDialog(null, "Reservation has been added.");
	}

	public void claimReservation(String customerName) {
		boolean found = false;
		int reservationIndex = -1;
		for (int i = 0; i < reservationBook.size(); i++) {
			if (reservationBook.get(i).getCustomer().getName().equals(customerName)) {
				found = true;
				reservationIndex = i;
				break;
			}
		}
		if (!found) {
			JOptionPane.showMessageDialog(null, "There is no reservation under that name", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null,
				"Reservation has been claimed. Under 'Orders,' the table is now claimed by " + customerName);
		reservationBook.remove(reservationIndex);
		// TODO : change table now to claimed under orders dialog
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
				OrderDialog orderDialog = new OrderDialog(self);
			}else if (press.getSource() == transactionButton) {
			
			} else if (press.getSource() == tipButton) {
				
			}else if (press.getSource() == menuButton) {
				MenuDialog menuDialog = new MenuDialog (self);

			} else if (press.getSource() == tableLayoutButton) {
				TableLayoutDialog tableLayoutDialog = new TableLayoutDialog(tables);

			} else if (press.getSource() == reservationButton) {
				ReservationDialog reservationDialog = new ReservationDialog(self);

			}else if (press.getSource() == employeeButton) {
				EmployeeDialog employeeDialog = new EmployeeDialog();
			}
		}
	}
}
