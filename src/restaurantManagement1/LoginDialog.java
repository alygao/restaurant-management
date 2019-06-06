package restaurantManagement1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {

	private Restaurant restaurant;
	private JPanel panel;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	private JButton loginButton;

	public LoginDialog(Restaurant restaurant) {
		this.restaurant = restaurant;
		initUI();
	}

	private void initUI() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		setUndecorated(false);
		setName("Login");
		setSize(200, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);

		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(10, 100, 75, 30);
		panel.add(usernameLabel);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(100, 100, 75, 30);
		panel.add(usernameTextField);

		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(10, 150, 75, 30);
		panel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(100, 150, 75, 30);
		panel.add(passwordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(40, 200, 120, 40);
		panel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			@Override
			/**
			 * actionPerformed Invoked when an action occurs
			 * 
			 * @param e the action that occurs
			 */
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < restaurant.getEmployees().size(); i++) {
					if (restaurant.getEmployees().get(i).getUserID().equals(usernameTextField.getText())
							&& restaurant.getEmployees().get(i).getPassword().equals(passwordField.getText())) {
						restaurant.setCurrentUser(restaurant.getEmployees().get(i));
						restaurant.enable();
						JOptionPane.showMessageDialog(null, "Login successful.");
						dispose();
						return;
					}

				}
				
                JOptionPane.showMessageDialog(null, "Login failed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

			}
		});
		setVisible(true);
	}

}
