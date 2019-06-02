package restaurantManagement1;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class Restaurant extends JFrame {

	private List<Table> tables = new ArrayList<>();
	private List<Reservation> reservationBook = new ArrayList<>();
	private List<MenuItem> menu = new ArrayList<>();
	private Queue<Customer> waitingList = new Queue<>();
	private DoublyLinkedList<Chef> chefs = new DoublyLinkedList<>();
	private DoublyLinkedList<Waiter> waiters = new DoublyLinkedList<>();
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

		setJMenuBar(createMenu());

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

		// background image
		homepageBackground = new ImageIcon(getClass().getResource("freshqo homepage.JPG"));
		JLabel homepageBackgroundLabel = new JLabel(homepageBackground);
		homepageBackgroundLabel.setBounds(0, 0, 1000, 600);
		mainPanel.add(homepageBackgroundLabel);

		// icon image and size
		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("freshqo icon.JPG")));
		setTitle("Freshqo Management");
		setSize(1000, 600);
		setResizable(false);
		setUndecorated(false); // TODO: change to true
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private JMenuBar createMenu() {
		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("File");

		// Open File
		JMenuItem fileOpen = new JMenuItem("Open");
		fileOpen.setMnemonic(KeyEvent.VK_O);
		fileOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDataFile();
			}
		});

		// Save File
		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.setMnemonic(KeyEvent.VK_S);
		fileSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveDataFile();
			}
		});

		// Exit File
		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				closeApplication();
			}
		});

		file.add(fileOpen);
		file.add(fileSave);
		file.add(eMenuItem);
		menubar.add(file);

		return menubar;
	}

	/**
	 * closeApplication Asks if the user is sure they want to close the program. If
	 * yes, it will automatically save the data.
	 */
	protected void closeApplication() {
		int ret = JOptionPane.showConfirmDialog(mainPanel, "Are you sure you want to close the application?",
				"Close Application", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (ret == JOptionPane.YES_OPTION) {
			saveConfigurationAndData();
			System.exit(0);
		}
	}

	/**
	 * openDataFile Opens user's own file explorer and allows user to choose a data
	 * file the file to be opened that was previously saved from before
	 */
	protected void openDataFile() {
		JFileChooser fileopen = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Application data files", ".fqo");
		fileopen.addChoosableFileFilter(filter);

		int ret = fileopen.showDialog(mainPanel, "Open file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			try {
				loadDataFile(file);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "The file name could not be found.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;

			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "The file cannot be used with this program.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

	/**
	 * loadDataFile load the RecipeDatabase from the file specified by the filename.
	 * 
	 * @param filename the file name of the database file.
	 * @throws FileNotFoundException  if the file cannot be found
	 * @throws IOException            if there is an error in reading the file
	 * @throws ClassNotFoundException the RecipeDatabase class cannot be found.
	 * @throws ClassCastException     the data stored in the file is not a
	 *                                RecipeDatabase object
	 */
	private void loadDataFile(String filename)
			throws FileNotFoundException, IOException, ClassNotFoundException, ClassCastException {
		loadDataFile(new File(filename));
	}

	/**
	 * loadDataFile load the RecipeDatabase from the file specified.
	 * 
	 * @param file the File object defining the database file.
	 * @throws FileNotFoundException  if the file cannot be found
	 * @throws IOException            if there is an error in reading the file
	 * @throws ClassNotFoundException the RecipeDatabase class cannot be found.
	 * @throws ClassCastException     the data stored in the file is not a
	 *                                RecipeDatabase object
	 */
	@SuppressWarnings("unchecked")
	private void loadDataFile(File file)
			throws FileNotFoundException, IOException, ClassNotFoundException, ClassCastException {
		this.configuration.setProperty("database.filename", file.getAbsolutePath());
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//			self = (Restaurant) ois.readObject(); //TODO: fix
			tables = (List<Table>) ois.readObject();
			reservationBook = (List<Reservation>) ois.readObject();
			menu = (List<MenuItem>) ois.readObject();
			waitingList = (Queue<Customer>) ois.readObject();
		}
	}

	/**
	 * saveDataFile save data file saves the file under its own unique extension
	 */
	protected void saveDataFile() {
		JFileChooser filesave = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Application data files", ".fqo");
		filesave.addChoosableFileFilter(filter);

		int ret = filesave.showDialog(mainPanel, "Save file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = filesave.getSelectedFile();
			saveDataFile(file);
		}
	}

	/**
	 * saveDataFile converts the String filename into a file
	 * 
	 * @param filename the name of the file to be saved
	 */
	private void saveDataFile(String filename) {
		saveDataFile(new File(filename));
	}

	/**
	 * saveDataFile saves the data into a file
	 * 
	 * @param file the file which will hold the saved data
	 */
	private void saveDataFile(File file) {
		this.configuration.setProperty("database.filename", file.getAbsolutePath());
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(tables);
			oos.writeObject(reservationBook);
			oos.writeObject(menu);
			oos.writeObject(waitingList);
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "The file cannot be used with this program.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected Properties configuration = new Properties();
	public static final String CONFIGURATION_FILENAME = "restaurant.properties";
	public static final String DEFAULT_DATABASE_FILENAME = "restaurant.fqo";

	/**
	 * saveConfigurationAndData saves configuration and data under the file
	 */
	protected void saveConfigurationAndData() {

		String dbFilename = this.configuration.getProperty("database.filename", DEFAULT_DATABASE_FILENAME);
		this.saveDataFile(dbFilename);
		this.configuration.setProperty("database.filename", dbFilename);
		try {
			this.configuration.store(new FileOutputStream(CONFIGURATION_FILENAME), "");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The file type cannot be used to save your data.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * loadConfigurationAndData loads configuration and data from a saved file
	 */
	protected void loadConfigurationAndData() {
		try {
			this.configuration.load(new FileInputStream(CONFIGURATION_FILENAME));
			String dbFilename = this.configuration.getProperty("database.filename", DEFAULT_DATABASE_FILENAME);
			try {
				this.loadDataFile(dbFilename);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"The data file cannot be open or does not exist. A default one will be created on the exit of the application.",
						"Warning", JOptionPane.WARNING_MESSAGE);
			} catch (ClassNotFoundException | ClassCastException e) {
				JOptionPane.showMessageDialog(null,
						"The data file is corrupt and hence cannot be used. A new data file will be created on the exit of the application.",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"The configuration file does not exist or is corrupt. The default value will be used.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
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

	public List<Reservation> getReservationBook() {
		return reservationBook;
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
//			System.out.println("date of reservation: " + reservationBook.get(i).getReserveTimePeriod().getDate());
			if (reservationBook.get(i).getReserveTimePeriod().getDate().equals(reserveTimePeriod.getDate())) {
				savedReservationsForDate.add(reservationBook.get(i));
			}
		}
//		System.out.println(savedReservationsForDate.size());
		for (int i = 0; i < savedReservationsForDate.size(); i++) {
			if (availableTableForReservation.contains(savedReservationsForDate.get(i).getTable())) {
				if ((savedReservationsForDate.get(i).getReserveTimePeriod().getTimeInDouble() - 2 <= reserveTimePeriod
						.getTimeInDouble())
						&& (savedReservationsForDate.get(i).getReserveTimePeriod().getTimeInDouble()
								+ 2 >= reserveTimePeriod.getTimeInDouble())) {
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

	public void addChef(Chef chefToAdd) {
		chefs.add(chefToAdd);
		JOptionPane.showMessageDialog(null, "Chef successfully added.");
	}

	public void addWaiter(Waiter waiterToAdd) {
		waiters.add(waiterToAdd);
		JOptionPane.showMessageDialog(null, "Waiter successfully added.");
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
			} else if (press.getSource() == transactionButton) {

			} else if (press.getSource() == tipButton) {

			} else if (press.getSource() == menuButton) {
				MenuDialog menuDialog = new MenuDialog(self);

			} else if (press.getSource() == tableLayoutButton) {
				SetupDialog tableLayoutDialog = new SetupDialog(tables);

			} else if (press.getSource() == reservationButton) {
				ReservationBookDialog reservationBookDialog = new ReservationBookDialog(self);

			} else if (press.getSource() == employeeButton) {
				EmployeeDialog employeeDialog = new EmployeeDialog(self);
			}
		}
	}

	public static String getCurrentTime() {
		Date date = new Date();
		String strDateFormat = "hh:mma";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	public double convertTimeToDouble(String time) {
		double convertedTime = 0;
		convertedTime = Double.parseDouble(time.substring(0, time.indexOf(":")));
		convertedTime += Double.parseDouble(time.substring(time.indexOf(":")+1, time.indexOf(":") + 3)) / 60;
		if (time.substring(time.indexOf(":") + 4).equals("PM")) {
			convertedTime += 12;
		}
		return convertedTime;
	}

	public String getCurrentDate() {
		DatePickerSettings dateSettings = new DatePickerSettings();
		DatePicker datePicker = new DatePicker(dateSettings);
		datePicker.setDateToToday();
		return datePicker.getText();
	}

	public Table findAvailableTableForWalkInCustomer(Customer customer) {
		List<Table> availableTables = new ArrayList<>();
		double currentTime = convertTimeToDouble(getCurrentTime());
		for (int i = 0; i < tables.size(); i++) {
			if (!tables.get(i).isOccupied()) {
				availableTables.add(tables.get(i));
			}
		}
		for (int i = 0; i < reservationBook.size(); i++) {
			if (reservationBook.get(i).getReserveTimePeriod().getDate().equals(getCurrentDate())
					&& convertTimeToDouble(
							getCurrentTime()) < reservationBook.get(i).getReserveTimePeriod().getTimeInDouble() + 2
					&& convertTimeToDouble(
							getCurrentTime()) > reservationBook.get(i).getReserveTimePeriod().getTimeInDouble() - 2) {
				availableTables.remove(reservationBook.get(i).getTable());
			}
		}
		if (availableTables.size() > 0) {
			return getAppropriateTable(availableTables, customer.getNumPeople()); // TODO: change so its more efficient (doesnt return a 10seat
															// table for 2 people)
		} else {
			return null;
		}
	}

	private Table getAppropriateTable(List<Table> availableTables, int numPeople) {
		int minDifference = Integer.MAX_VALUE;
		Table bestTable = null;
		for (int i = 0; i < availableTables.size(); i++) {
			if (availableTables.get(i).getNumSeats() - numPeople > 0 && availableTables.get(i).getNumSeats() - numPeople < minDifference ) {
				bestTable = availableTables.get(i);
				minDifference = availableTables.get(i).getNumSeats();
			}
		}
		return bestTable;
	}

	public Queue<Customer> getWaitingList() {
		return waitingList;
	}
}