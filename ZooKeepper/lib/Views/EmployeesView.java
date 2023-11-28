package ZooKeepper.lib.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ZooKeepper.lib.Factory.EmployeeFactory;
import ZooKeepper.lib.Models.Employee;
import ZooKeepper.lib.Singleton.EmployeeDatabaseSingleton;
import ZooKeepper.lib.Singleton.InterfaceSingleton;
import ZooKeepper.lib.Views.EmployeesView;

/**
 * The {@code EmployeesView} class provides a graphical user interface for
 * displaying and managing employees in the ZooKeeper application.
 */
public class EmployeesView {
    private static JTable employeesTable;

    /**
     * Displays a window showing all employees in the zoo.
     */
    public static void ShowAllEmployees() {
        JFrame frame = new JFrame("Employees");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(InterfaceSingleton.getWindow());
        frame.setLayout(new BorderLayout());
        employeesTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeesTable);
        updateEmployeesTable();

        // Create "Close" button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = employeesTable.getSelectedRow();
                if (rowSelected >= 0) {
                    int employeeId = Integer.parseInt((String) employeesTable.getValueAt(rowSelected, 0));
                    EmployeeDatabaseSingleton.delete(employeeId);
                    updateEmployeesTable();
                }
            }
        });

        JButton newEmployeeButton = new JButton("Add");
        newEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, enclosureIdString, message = "";
                int enclosureId = -1;
                name = JOptionPane.showInputDialog("Employee name:");
                enclosureIdString = JOptionPane.showInputDialog("Type the enclosure id target:");

                try {
                    enclosureId = Integer.parseInt(enclosureIdString);
                    if (enclosureId >= 0 && name != null && !name.isEmpty()) {
                        EmployeeDatabaseSingleton.create(EmployeeFactory.CreateEmployee(name, enclosureId));
                        message = "Employee created";
                        updateEmployeesTable();
                    }
                } catch (Exception ex) {
                    message = "Cannot add new employee";
                }
                JOptionPane.showMessageDialog(null, message);
            }
        });

        buttonPanel.add(newEmployeeButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(closeButton);
        frame.add(scrollPane, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setTitle("Employees");
        frame.setVisible(true);
    }

    private static void updateEmployeesTable() {
        // Create a table to display the employees

        ArrayList<Employee> employeesList = EmployeeDatabaseSingleton.readAll();
        DefaultTableModel tableModel = new DefaultTableModel(new Object[] { "id", "Name", "Work in enclosure" }, 0);

        // Add employees to the table
        for (Employee emp : employeesList) {
            String employeeId = Integer.toString(emp.getId());
            String employeeName = emp.getName();
            String workplace = Integer.toString(emp.getWorkplace());
            tableModel.addRow(new Object[] { employeeId, employeeName, workplace });
        }
        employeesTable.setModel(tableModel);
    }
}

