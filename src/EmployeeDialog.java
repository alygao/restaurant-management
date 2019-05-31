/**
 * Project - Freshqo
 * EmployeeDialog.java
 * A File to Create the Employee Dialog Pop-up
 * @author Zaid Omer
 * @version June 14 2019
 */

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        employeeNameLabel.setBounds(25, 50, 300, 30);
        addEmployeesPanel.add(employeeNameLabel);

        employeeNameTextField = new JTextField();
        employeeNameTextField.setBounds(150, 50, 300, 30);
        addEmployeesPanel.add(employeeNameTextField);

        //Employee Pay
        JLabel payLabel = new JLabel("Pay (Hourly Rate $):");
        payLabel.setBounds(25, 100, 300, 30);
        addEmployeesPanel.add(payLabel);

        payTextField = new JTextField();
        payTextField.setBounds(150, 100, 300, 30);
        addEmployeesPanel.add(payTextField);

        //Employee userID
        JLabel userIDLabel = new JLabel ("User ID:");
        userIDLabel.setBounds(25, 150, 300, 30);
        addEmployeesPanel.add(userIDLabel);

        userIDTextField = new JTextField();
        userIDTextField.setBounds(150, 150, 300, 30);
        addEmployeesPanel.add(userIDTextField);

        //Enter user password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(25, 200, 300, 30);
        addEmployeesPanel.add(passwordLabel);

        passwordTextField = new JPasswordField(20);
        passwordTextField.setBounds(150, 200, 300, 30);
        addEmployeesPanel.add(passwordTextField);

        //Type of Employee
        JLabel employeeTypeLabel = new JLabel("Type of Employee: ");
        employeeTypeLabel.setBounds(25, 250, 300, 30);
        addEmployeesPanel.add(employeeTypeLabel);

        JRadioButton chefRadioButton = new JRadioButton("Chef");
        chefRadioButton.setBounds(150, 250, 300, 30);
        addEmployeesPanel.add(chefRadioButton);

        JRadioButton waiterRadioButton = new JRadioButton("Waiter");
        waiterRadioButton.setBounds(150, 280, 300, 30);
        addEmployeesPanel.add(waiterRadioButton);

        ButtonGroup employeeTypeGroup = new ButtonGroup();
        employeeTypeGroup.add(chefRadioButton);
        employeeTypeGroup.add(waiterRadioButton);

        //Submit data, make employee
        createEmployee = new JButton("Add Employee");
        createEmployee.setBounds(25, 350, 150, 30);
        //createEmployee.addActionListener(new ReservationDialog.ButtonListener());
        addEmployeesPanel.add(createEmployee);

        //Set Visible
        setVisible(true);
    }
}



