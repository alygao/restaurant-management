package restaurantManagement1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class FoodQuantityDialog extends JDialog {

    private Restaurant restaurant;
    private AddFoodDialog addFoodDialog;
    private JPanel panel;
    private ImageIcon background;
    
    private JSpinner quantitySpinner;
    private JButton orderButton;

    public FoodQuantityDialog(Restaurant restaurant, AddFoodDialog addFoodDialog) {
        this.restaurant = restaurant;
        this.addFoodDialog = addFoodDialog;
        initUI();
    }

    private void initUI() {

        setModalityType(ModalityType.APPLICATION_MODAL);

        setUndecorated(false); // TODO change to true
        setSize(200, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);

        JLabel quantityLabel = new JLabel("Quantity of Item: ");
        quantityLabel.setBounds(10, 120, 125, 30);
        panel.add(quantityLabel);

        SpinnerModel quantitySpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        quantitySpinner = new JSpinner(quantitySpinnerModel);
        quantitySpinner.setBounds(135, 120, 50, 30);
        panel.add(quantitySpinner);

        orderButton = new JButton(new ImageIcon(getClass().getResource("order button.JPG")));
        orderButton.setBounds(40, 200, 120, 50);
        panel.add(orderButton);
        orderButton.addActionListener(new ActionListener() {
            @Override
            /**
             * actionPerformed Invoked when an action occurs
             *
             * @param press the action that occurs
             */
            public void actionPerformed(ActionEvent press) {

                if (press.getSource() == orderButton) {
                    int quantity = (int) quantitySpinner.getValue();
                    addFoodDialog.setQuantity(quantity);
                }
                dispose();

            }
        });

        // background image
        background = new ImageIcon(getClass().getResource("small dialog background.JPG"));
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, 200, 300);
        panel.add(backgroundLabel);

        setVisible(true);
    }

}
