package ZooKeepper.lib.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import ZooKeepper.lib.Factory.EnclosureFactory;
import ZooKeepper.lib.Models.Enclosure;
import ZooKeepper.lib.Singleton.EnclosureDatabaseSingleton;
import ZooKeepper.lib.Singleton.InterfaceSingleton;
import ZooKeepper.lib.Views.EnclosuresView;

/**
 * The {@code EnclosuresView} class provides a graphical user interface for
 * displaying and managing enclosures in the ZooKeeper application.
 */
public class EnclosuresView {
    private static JTable enclosuresTable;

    /**
     * Displays a window showing all enclosures in the zoo.
     */
    public static void ShowAllEnclosures() {
        JFrame frame = new JFrame("Enclosures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(InterfaceSingleton.getWindow());
        frame.setLayout(new BorderLayout());

        enclosuresTable = new JTable();
        updateEnclosuresTable();
        JScrollPane scrollPane = new JScrollPane(enclosuresTable);

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
        JButton newEnclosureButton = new JButton("Add");
        newEnclosureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description, message = "";
                description = JOptionPane.showInputDialog("Type the enclosure species description:");
                if (description != null) {
                    Enclosure created = EnclosureFactory.createNewEnclosure(description);
                    EnclosureDatabaseSingleton.create(created);
                    message = "Enclosure created";
                } else {
                    message = "Enclosure not created";
                }
                updateEnclosuresTable();
                JOptionPane.showMessageDialog(null, message);
            }
        });

        JButton showMore = new JButton("Show details");
        showMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = enclosuresTable.getSelectedRow();
                if (rowSelected >= 0) {
                    int enclosureId = (int) enclosuresTable.getValueAt(rowSelected, 0);
                    AnimalsView.displayEnclosureAnimals(EnclosureDatabaseSingleton.read(enclosureId), new WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            updateEnclosuresTable();
                        }
                    });
                }
            }
        });

        buttonPanel.add(newEnclosureButton);
        buttonPanel.add(showMore);
        buttonPanel.add(closeButton);
        frame.add(scrollPane, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setTitle("Enclosures");
        frame.setVisible(true);
    }

    /**
     * Updates the table displaying enclosure information.
     */
    public static void updateEnclosuresTable() {
        ArrayList<Enclosure> enclosures;
        String[] columnNames = { "id", "Enclosure Description", "Quantity of Animals" };
        enclosures = EnclosureDatabaseSingleton.readAll();
        DefaultTableModel result = new DefaultTableModel(columnNames, 0);
        for (Enclosure e : enclosures) {
            Object[] rowData = { e.getId(), e.getAnimalDescription(), e.getAnimalCount() };
            result.addRow(rowData);
        }
        enclosuresTable.setModel(result);
    }
}
