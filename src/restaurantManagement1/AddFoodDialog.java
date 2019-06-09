package restaurantManagement1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddFoodDialog extends JDialog{
    private Restaurant restaurant;
    private ImageIcon homepageBackground;
    private int selectedRow;
    private String tableName;
    private String customerName;
    private int tableSize;
    private JLabel tableInfoLabel;
    private JPanel tablesFoodPanel;
    private JButton returnToHomepageButton;
    private JButton confirmOrderButton;
    private JLabel homepageBackgroundLabel;
    private JLabel totalPriceLabel;
    private JLabel appetizersLabel;
    private JLabel entreesLabel;
    private JLabel dessertsLabel;
    private JLabel beveragesLabel;
    private JTable appetizersTable;
    private JTable entreesTable;
    private JTable dessertsTable;
    private JTable beveragesTable;
    private FoodLayoutTableModel appetizerLayoutTableModel;
    private JLabel finalOrderLabel;
    private int quantity;
    private AddFoodDialog self = this;
    private List<MenuItem> menu;
    private MenuItem appetizerOrdered;
    private MenuItem entreeOrdered;
    private MenuItem dessertOrdered;
    private MenuItem beverageOrdered;
    private double totalPrice = 0;
    private String oldFinalOrder;
    private String orderToAddToLabel;
    private String newLabel;
    private Table table;
    private List<TableOrderItem> currentOrder;
    
    public AddFoodDialog(Restaurant restaurant, int selectedRow){
        this.restaurant = restaurant;
        this.selectedRow = selectedRow;
        menu = restaurant.getMenu();
        initUI();
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public void initUI(){

        setModalityType(ModalityType.APPLICATION_MODAL);
        setUndecorated(true); // TODO change to true
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        tablesFoodPanel = new JPanel();
        tablesFoodPanel.setLayout(null);

        confirmOrderButton = new JButton(new ImageIcon(getClass().getResource("confirm order button.JPG")));
        confirmOrderButton.setBounds(865, 345, 120, 75);
        confirmOrderButton.addActionListener(new ButtonListener());
        tablesFoodPanel.add(confirmOrderButton);

        returnToHomepageButton = new JButton(new ImageIcon(getClass().getResource("return home button.JPG")));
        returnToHomepageButton.setBounds(865, 435, 120, 75);
        returnToHomepageButton.addActionListener(new ButtonListener());
        tablesFoodPanel.add(returnToHomepageButton);
        
        List<Table> tables = restaurant.getTables();
        table = tables.get(selectedRow);
        tableName = tables.get(selectedRow).getTableName();
        customerName = tables.get(selectedRow).getCustomer().getName();
        tableSize = tables.get(selectedRow).getNumSeats();
        tableInfoLabel = new JLabel("<html>Table " + tableName + "<p>" + "Customer: " + customerName + "<p>" + "Table Size: " + tableSize + "</html>");
        tableInfoLabel.setBounds(25, -200, 100, 600);
        tablesFoodPanel.add(tableInfoLabel);
        tablesFoodPanel.add(returnToHomepageButton);

        //Price Label
        totalPriceLabel = new JLabel("Total Price: ");
        totalPriceLabel.setBounds(525, 520, 400, 75);
        totalPriceLabel.setVisible(true);
        tablesFoodPanel.add(totalPriceLabel);

        tablesFoodPanel.setVisible(true);

        //Appetizer Options
        appetizersLabel = new JLabel("Appetizers");
        appetizersLabel.setBounds(25, 140, 200, 20);
        tablesFoodPanel.add(appetizersLabel);
        appetizerLayoutTableModel = new FoodLayoutTableModel();
        appetizersTable = new JTable(appetizerLayoutTableModel);
        appetizersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        appetizersTable.setBounds(25, 160, 200, 200);
//        menu = restaurant.getMenu();
//        List<MenuItem> appetizers = new ArrayList<>();
//        for(int i = 0; i < menu.size(); i++){
//            if(menu.get(i).getCategory() == "Appetizer"){
//                appetizers.add(menu.get(i));
//            }
//        }
        appetizerLayoutTableModel.addRows(restaurant.getAppetizerMenu());
        appetizersTable.addMouseListener(new MyMouseListener());

        JScrollPane tableListScrollPane = new JScrollPane(appetizersTable);
        tableListScrollPane.setBounds(25, 160, 200, 200);
        tablesFoodPanel.add(tableListScrollPane);

        //Entree Options
        entreesLabel = new JLabel("Entrees");
        entreesLabel.setBounds(25, 370, 200, 20);
        tablesFoodPanel.add(entreesLabel);
        FoodLayoutTableModel entreeLayoutTableModel = new FoodLayoutTableModel();
        entreesTable = new JTable(entreeLayoutTableModel);
        entreesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        entreesTable.setBounds(25, 390, 200, 200);
//        List<MenuItem> entrees = new ArrayList<>();
//        for(int i = 0; i < menu.size(); i++){
//            if(menu.get(i).getCategory() == "Entree"){
//                entrees.add(menu.get(i));
//            }
//        }
        entreeLayoutTableModel.addRows(restaurant.getEntreeMenu());
        entreesTable.addMouseListener(new MyMouseListener());

        JScrollPane entreeTableListScrollPane = new JScrollPane(entreesTable);
        entreeTableListScrollPane.setBounds(25, 390, 200, 200);
        tablesFoodPanel.add(entreeTableListScrollPane);

        //Dessert Options
        dessertsLabel = new JLabel("Desserts");
        dessertsLabel.setBounds(250, 140, 200, 20);
        tablesFoodPanel.add(dessertsLabel);
        FoodLayoutTableModel dessertLayoutTableModel = new FoodLayoutTableModel();
        dessertsTable = new JTable(dessertLayoutTableModel);
        dessertsTable.setBounds(250, 160, 200, 200);
//        List<MenuItem> desserts = new ArrayList<>();
//        for(int i = 0; i < menu.size(); i++){
//            if(menu.get(i).getCategory().equals("Dessert")){
//                desserts.add(menu.get(i));
//            }
//        }
        dessertLayoutTableModel.addRows(restaurant.getDessertMenu());
        dessertsTable.addMouseListener(new MyMouseListener());

        JScrollPane dessertTableListScrollPane = new JScrollPane(dessertsTable);
        dessertTableListScrollPane.setBounds(250, 160, 200, 200);
        tablesFoodPanel.add(dessertTableListScrollPane);

        //Beverages Options
        beveragesLabel = new JLabel("Beverages");
        beveragesLabel.setBounds(250, 370, 200, 20);
        tablesFoodPanel.add(beveragesLabel);
        FoodLayoutTableModel beverageLayoutTableModel = new FoodLayoutTableModel();
        beveragesTable = new JTable(beverageLayoutTableModel);
        beveragesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        beveragesTable.setBounds(250, 390, 200, 200);
//        List<MenuItem> beverages = new ArrayList<>();
//        for(int i = 0; i <  menu.size(); i++){
//            if(menu.get(i).getCategory() == "Beverage"){
//                beverages.add(menu.get(i));
//            }
//        }
        beverageLayoutTableModel.addRows(restaurant.getBeverageMenu());
        beveragesTable.addMouseListener(new MyMouseListener());

        JScrollPane beverageTableListScrollPane = new JScrollPane(beveragesTable);
        beverageTableListScrollPane.setBounds(250, 390, 200, 200);
        tablesFoodPanel.add(beverageTableListScrollPane);

        finalOrderLabel = new JLabel("<html>");

        //Titled borders
        Border blackline = BorderFactory.createLineBorder(Color.black);
        finalOrderLabel.setBorder(blackline);
        finalOrderLabel.setBounds(525, 100, 260, 400);
        tablesFoodPanel.add(finalOrderLabel);

        // background image
        homepageBackground = new ImageIcon(getClass().getResource("freshqo background.JPG"));
        homepageBackgroundLabel = new JLabel(homepageBackground);
        homepageBackgroundLabel.setBounds(0, 0, 1000, 600);
        tablesFoodPanel.add(homepageBackgroundLabel);

        getContentPane().add(tablesFoodPanel);
        setVisible(true);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent press){
            if (press.getSource() == returnToHomepageButton) {
                dispose();
            }else if(press.getSource() == confirmOrderButton){
                
            }
        }
    }

    public class MyMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent press) {
            currentOrder = new ArrayList<TableOrderItem>();
            if (press.getClickCount() == 2 && appetizersTable.getSelectedRow() != -1) {
                appetizersTable = (JTable)press.getSource();
                int row = appetizersTable.getSelectedRow();
                FoodQuantityDialog appetizerQuantityDialog = new FoodQuantityDialog(restaurant, self);
                String appetizerName = (String)appetizersTable.getValueAt(row, 0);
                for(int i = 0; i < menu.size(); i++){
                    if(menu.get(i).getName().equals(appetizerName)){
                        appetizerOrdered = menu.get(i);
                        oldFinalOrder = finalOrderLabel.getText();
                        if(finalOrderLabel.getText().length() > 8){
                            oldFinalOrder = finalOrderLabel.getText().substring(0, finalOrderLabel.getText().length()-8);
                        }
                        orderToAddToLabel = "   " + appetizerName + " (" + quantity +") $" + appetizerOrdered.getPrice()*quantity;
                        newLabel = oldFinalOrder + "<br>" + orderToAddToLabel;
                        finalOrderLabel.setText(newLabel + "</html>");

                        totalPrice += appetizerOrdered.getPrice()*quantity;
                    }
                }
                totalPriceLabel.setText("Total Price + 13% HST: $" + (1.13*totalPrice));

                currentOrder.add(new TableOrderItem(beverageOrdered, quantity, null, null));
            }else if (press.getClickCount() == 2 && entreesTable.getSelectedRow() != -1) {
                entreesTable = (JTable)press.getSource();
                int row = entreesTable.getSelectedRow();
                FoodQuantityDialog entreeQuantityDialog = new FoodQuantityDialog(restaurant, self);
                String entreeName = (String)entreesTable.getValueAt(row, 0);
                for(int i = 0; i < menu.size(); i++){
                    if(menu.get(i).getName().equals(entreeName)){
                        entreeOrdered = menu.get(i);
                        oldFinalOrder = finalOrderLabel.getText();
                        if(finalOrderLabel.getText().length() > 8){
                            oldFinalOrder = finalOrderLabel.getText().substring(0, finalOrderLabel.getText().length()-8);
                        }
                        orderToAddToLabel = "   " + entreeName + " (" + quantity +") $" + entreeOrdered.getPrice()*quantity;
                        newLabel = oldFinalOrder + "<br>" + orderToAddToLabel;
                        finalOrderLabel.setText(newLabel + "</html>");

                        totalPrice += entreeOrdered.getPrice()*quantity;
                    }
                }
                totalPriceLabel.setText("Total Price + 13% HST: " + (1.13*totalPrice));
                currentOrder.add(new TableOrderItem(beverageOrdered, quantity, null, null));

            }else if (press.getClickCount() == 2 && dessertsTable.getSelectedRow() != -1) {
                dessertsTable = (JTable)press.getSource();
                int row = dessertsTable.getSelectedRow();
                FoodQuantityDialog foodQuantityDialog = new FoodQuantityDialog(restaurant, self);
                String dessertName = (String)dessertsTable.getValueAt(row, 0);
                for(int i = 0; i < menu.size(); i++){
                    if(menu.get(i).getName().equals(dessertName)){
                        dessertOrdered = menu.get(i);
                        oldFinalOrder = finalOrderLabel.getText();
                        if(finalOrderLabel.getText().length() > 8){
                            oldFinalOrder = finalOrderLabel.getText().substring(0, finalOrderLabel.getText().length()-8);
                        }
                        orderToAddToLabel = "   " + dessertName + " (" + quantity +") $" + dessertOrdered.getPrice()*quantity;
                        newLabel = oldFinalOrder + "<br>" + orderToAddToLabel;
                        finalOrderLabel.setText(newLabel + "</html>");

                        totalPrice += dessertOrdered.getPrice()*quantity;
                    }
                }
                totalPriceLabel.setText("Total Price + 13% HST: " + (1.13*totalPrice));
                currentOrder.add(new TableOrderItem(beverageOrdered, quantity, null, null));

            }else if (press.getClickCount() == 2 && beveragesTable.getSelectedRow() != -1) {
                beveragesTable = (JTable)press.getSource();
                int row = beveragesTable.getSelectedRow();
                FoodQuantityDialog foodQuantityDialog = new FoodQuantityDialog(restaurant, self);
                String beverageName = (String)beveragesTable.getValueAt(row, 0);
                for(int i = 0; i < menu.size(); i++){
                    if(menu.get(i).getName().equals(beverageName)){
                        beverageOrdered = menu.get(i);
                        oldFinalOrder = finalOrderLabel.getText();
                        if(finalOrderLabel.getText().length() > 8){
                            oldFinalOrder = finalOrderLabel.getText().substring(0, finalOrderLabel.getText().length()-8);
                        }
                        orderToAddToLabel = "   " + beverageName + " (" + quantity +") $" + beverageOrdered.getPrice()*quantity;
                        newLabel = oldFinalOrder + "<br>" + orderToAddToLabel;
                        finalOrderLabel.setText(newLabel + "</html>");

                        totalPrice += beverageOrdered.getPrice()*quantity;
                    }
                }
                totalPriceLabel.setText("Total Price + 13% HST: " + (1.13*totalPrice));
                currentOrder.add(new TableOrderItem(beverageOrdered, quantity, null, null));
            }
        }

        @Override
        public void mousePressed(MouseEvent press) {

        }

        @Override
        public void mouseReleased(MouseEvent press) {

        }

        @Override
        public void mouseEntered(MouseEvent press) {

        }

        @Override
        public void mouseExited(MouseEvent press) {


        }
    }

    class FoodLayoutTableModel extends AbstractTableModel{
        private final String[] foodLayoutListColumns = {"Dish", "Price"};
        private final Class[] columnClasses = {String.class, double.class};
        private List<MenuItem> foodData = new ArrayList<>();

        @Override
        /**
         * getColumnCount the number of columns in the table
         *
         * @return the number of columns
         */
        public int getColumnCount() {
            return this.foodLayoutListColumns.length;
        }

        @Override
        /**
         * getRowCount the number of rows in the table
         *
         * @return the number of rows
         */
        public int getRowCount() {
            return foodData.size();
        }

        @Override
        /**
         * getColumnName
         *
         * @param col the column number
         * @return the name of the column
         */
        public String getColumnName(int col) {
            return this.foodLayoutListColumns[col];
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

            MenuItem menuItem = this.foodData.get(row);
            switch (col) {
                case 0:
                    return menuItem.getName();
                // case 2:
                default:
                    return menuItem.getPrice();
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
            MenuItem menuItem = this.foodData.get(row);
            switch (col) {
                case 0:
                    menuItem.setName((String) value);
                    break;
                default:
                    menuItem.setPrice((double) value);
            }

            fireTableCellUpdated(row, col);
        }

        /**
         * updateRow when an table is modified, the row must be then updated
         *
         * @param menuItem the recipe to place in the table and add to the current list of
         *              tables
         * @param row   the row that needs to be updated due to a change in the table
         */
        public void updateRow(MenuItem menuItem, int row) {
            this.foodData.set(row, menuItem);
            fireTableRowsUpdated(row, row);
        }

        /**
         * insertRow inserts a row in the table with a table
         *
         * @param position the position to put the row
         * @param menuItem the food to show on the table
         */
        public void insertRow(int position, MenuItem menuItem) {
            this.foodData.add(menuItem);
            fireTableRowsInserted(0, getRowCount());
        }

        /**
         * addRow adds a row at the bottom of the table with a new recipe
         *
         * @param menuItem the food to be placed in the table
         */
        public void addRow(MenuItem menuItem) {
            insertRow(getRowCount(), menuItem);
        }

        /**
         * addRows
         * adds 2+ rows into the table
         * @param menuItemList the list of menu items that are to be put into the table
         */
        public void addRows(List<MenuItem> menuItemList) {
            for (MenuItem menuItem : menuItemList) {
                addRow(menuItem);
            }
        }

        /**
         * removeRow removes a specific row in the table
         *
         * @param position the position of the recipe to be removed
         */
        public void removeRow(int position) {
            this.foodData.remove(position);
            fireTableRowsDeleted(0, getRowCount());
        }

        /**
         * getData gets the list of tables
         *
         * @return the list of menu items
         */
        public List<MenuItem> getData() {
            return foodData;
        }

        /**
         * setData gets the list of tables
         *
         * @param foodData the list of food items
         */
        public void setData(List<MenuItem> foodData) {
            this.foodData = foodData;
            fireTableRowsInserted(0, getRowCount());
        }
    }
}
