/**
 * Project - Freshqo
 * EmployeeDialog.java
 * A File to Create the Employee Dialog Pop-up
 * @author Zaid Omer
 * @version June 14 2019
 */
package restaurantManagement1;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class EmployeeDialog extends JDialog{
    private JPanel viewEmployeesPanel;
    private JPanel addEmployeesPanel;
    private JTextField employeeNameTextField;
    private JTextField payTextField;
    private JTextField userIDTextField;
    private JPasswordField passwordTextField;
    private JButton createEmployee;
    private JSpinner dateSpinner;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField SINNumberTextField;
    private DatePicker datePicker;
    private JRadioButton chefRadioButton;
    private JRadioButton waiterRadioButton;

    EmployeeDialog(){
        initUI();
    }

    public void initUI(){
        setModalityType(ModalityType.APPLICATION_MODAL);

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        viewEmployeesPanel = new JPanel();
        viewEmployeesPanel.setLayout(null);
        getContentPane().add(viewEmployeesPanel);

        addEmployeesPanel = new JPanel();
        addEmployeesPanel.setLayout(null);
        getContentPane().add(addEmployeesPanel);

        //Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Employee", null, addEmployeesPanel);
        tabbedPane.addTab("View All Employees", null, viewEmployeesPanel);
        getContentPane().add(tabbedPane);

        //Add Employee Panel Setup

        //Employee Name
        JLabel employeeNameLabel = new JLabel("Employee Name:");
        employeeNameLabel.setBounds(25, 100, 300, 30);
        addEmployeesPanel.add(employeeNameLabel);

        employeeNameTextField = new JTextField();
        employeeNameTextField.setBounds(150, 100, 300, 30);
        addEmployeesPanel.add(employeeNameTextField);

        //Employee Pay
        JLabel payLabel = new JLabel("Pay (Hourly Rate $):");
        payLabel.setBounds(25, 150, 300, 30);
        addEmployeesPanel.add(payLabel);

        payTextField = new JTextField();
        payTextField.setBounds(150, 150, 300, 30);
        addEmployeesPanel.add(payTextField);

        //Employee userID
        JLabel userIDLabel = new JLabel ("User ID:");
        userIDLabel.setBounds(25, 200, 300, 30);
        addEmployeesPanel.add(userIDLabel);

        userIDTextField = new JTextField();
        userIDTextField.setBounds(150, 200, 300, 30);
        addEmployeesPanel.add(userIDTextField);

        //Enter user password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(25, 250, 300, 30);
        addEmployeesPanel.add(passwordLabel);

        passwordTextField = new JPasswordField(20);
        passwordTextField.setBounds(150, 250, 300, 30);
        addEmployeesPanel.add(passwordTextField);

        //Type of Employee
        JLabel employeeTypeLabel = new JLabel("Type of Employee: ");
        employeeTypeLabel.setBounds(25, 300, 300, 30);
        addEmployeesPanel.add(employeeTypeLabel);

        chefRadioButton = new JRadioButton("Chef");
        chefRadioButton.setBounds(150, 300, 300, 30);
        addEmployeesPanel.add(chefRadioButton);

        waiterRadioButton = new JRadioButton("Waiter");
        waiterRadioButton.setBounds(150, 330, 300, 30);
        addEmployeesPanel.add(waiterRadioButton);

        ButtonGroup employeeTypeGroup = new ButtonGroup();
        employeeTypeGroup.add(chefRadioButton);
        employeeTypeGroup.add(waiterRadioButton);

        //Date Added
        JLabel dateLabel = new JLabel("Date Hired:");
        dateLabel.setBounds(500, 100, 300, 30);
        addEmployeesPanel.add(dateLabel);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        datePicker = new DatePicker(dateSettings);
        dateSettings.setDateRangeLimits(null, LocalDate.now().plusDays( 60 ));
        datePicker.setBounds( 625, 100, 200, 30 );
        datePicker.setDateToToday();
        datePicker.getComponentDateTextField().setEditable(false);
        addEmployeesPanel.add( datePicker );

        //Phone Number
        JLabel phoneNumberLabel = new JLabel ("Phone Number:");
        phoneNumberLabel.setBounds(500, 150, 300, 30);
        addEmployeesPanel.add(phoneNumberLabel);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(625, 150, 300, 30);
        addEmployeesPanel.add(phoneNumberTextField);

        //Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(500, 200, 300, 30);
        addEmployeesPanel.add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(625, 200, 300, 30);
        addEmployeesPanel.add(emailTextField);

        //SIN Number
        JLabel SINNumberLabel = new JLabel("SIN Number:");
        SINNumberLabel.setBounds(500, 250, 300, 30);
        addEmployeesPanel.add(SINNumberLabel);

        SINNumberTextField = new JTextField();
        SINNumberTextField.setBounds(625, 250, 300, 30);
        addEmployeesPanel.add(SINNumberTextField);

        //Submit data, make employee
        createEmployee = new JButton("Add Employee");
        createEmployee.setBounds(25, 400, 150, 30);
        createEmployee.addActionListener(new EmployeeDialog.ButtonListener());
        addEmployeesPanel.add(createEmployee);

        //Set Visible
        setVisible(true);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent press){
            if (press.getSource() == createEmployee) {
                if((employeeNameTextField.getText().isEmpty()) || (payTextField.getText().isEmpty()) || (userIDTextField.getText().isEmpty()) || (passwordTextField.getText().isEmpty()) || (datePicker.getText().isEmpty()) || (phoneNumberTextField.getText().isEmpty()) || (emailTextField.getText().isEmpty()) || (SINNumberTextField.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null, "Please enter all required info.", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(!chefRadioButton.isSelected() && !waiterRadioButton.isSelected()){
                    JOptionPane.showMessageDialog(null, "Please select the employee type.", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    String employeeName = employeeNameTextField.getText();
                    double pay = Double.parseDouble(payTextField.getText());
                    String userID = userIDTextField.getText();
                    String password = passwordTextField.getText();

                    String dateHired = datePicker.getText();
                    String phoneNumber = phoneNumberTextField.getText();
                    String email = emailTextField.getText();
                    String SINNumber = SINNumberTextField.getText();
                }
            }
        }
    }
}



