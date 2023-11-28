import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ZooKeepper.lib.Abstract.Animal;
import ZooKeepper.lib.Factory.AnimalFactory;
import ZooKeepper.lib.Factory.EmployeeFactory;
import ZooKeepper.lib.Factory.EnclosureFactory;
import ZooKeepper.lib.Models.Enclosure;
import ZooKeepper.lib.Observer.Observer;
import ZooKeepper.lib.Singleton.*;
import ZooKeepper.lib.Views.*;

/**
 * The {@code App} class is the main entry point of the ZooKeeper application.
 * It initializes the user interface and populates the zoo with enclosures,
 * animals, and employees.
 */
public class App {
    private static JFrame frame; // Main application window

    /**
     * The main method of the ZooKeeper application.
     *
     * @param args Command-line arguments (not used).
     * @throws Exception Any exception that might occur during application execution.
     */
    public static void main(String[] args) throws Exception {
        frame = InterfaceSingleton.getWindow();
        frame.setLayout(new BorderLayout());

        // Create and populate enclosures with animals
        Enclosure bearEnclosure = EnclosureFactory.createNewEnclosure("Bears");
        EnclosureDatabaseSingleton.create(bearEnclosure);

        Enclosure monkeyEnclosure = EnclosureFactory.createNewEnclosure("Monkeys");
        EnclosureDatabaseSingleton.create(monkeyEnclosure);

        Enclosure crocodileEnclosure = EnclosureFactory.createNewEnclosure("Crocodiles");
        EnclosureDatabaseSingleton.create(crocodileEnclosure);

        // Add animal models to the enclosures
        bearEnclosure.addObserver(AnimalFactory.createBear("Brown Bear", 5, true));
        bearEnclosure.addObserver(AnimalFactory.createBear("Polar Bear", 8, false));

        monkeyEnclosure.addObserver(AnimalFactory.createMonkey("Chimpanzee", 6, true));
        monkeyEnclosure.addObserver(AnimalFactory.createMonkey("Gorilla", 10, true));

        crocodileEnclosure.addObserver(AnimalFactory.createCrocodile("Nile Crocodile", 15, true));
        crocodileEnclosure.addObserver(AnimalFactory.createCrocodile("Saltwater Crocodile", 20, false));

        // Create and populate employees
        EmployeeDatabaseSingleton.create(EmployeeFactory.CreateEmployee("James Waterson", bearEnclosure.getId()));
        EmployeeDatabaseSingleton.create(EmployeeFactory.CreateEmployee("Taylor Rocktown", monkeyEnclosure.getId()));
        EmployeeDatabaseSingleton.create(EmployeeFactory.CreateEmployee("Detiny Eclipson", crocodileEnclosure.getId()));

        // Create a panel for option buttons
        JPanel menuPanel = new JPanel();
        JButton employeeButton = new JButton("Employees");
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeesView.ShowAllEmployees();
            }
        });
        JButton enclosuresButton = new JButton("Enclosures");
        enclosuresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnclosuresView.ShowAllEnclosures(); // Close the window
            }
        });
        JButton summaryButton = new JButton("Summary");
        summaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calculate and display zoo summary
                int totalAnimals = 0, sickAnimals = 0, employeesCount = 0, enclosuresCount = 0, speciesCount = 0;
                Map<String, Integer> map = new HashMap<>();
                ArrayList<Enclosure> enclosures = EnclosureDatabaseSingleton.readAll();
                enclosuresCount = enclosures.size();
                for (Enclosure enc : enclosures) {
                    ArrayList<Observer> animals = enc.getObservers();
                    totalAnimals += animals.size();
                    for (Observer o : animals) {
                        if (!((Animal) o).isHealthy())
                            sickAnimals++;
                        if (map.containsKey(((Animal) o).getSpecies()))
                            continue;
                        speciesCount++;
                        map.put(((Animal) o).getSpecies(), 0);
                    }
                }
                employeesCount = EmployeeDatabaseSingleton.readAll().size();

                JOptionPane.showMessageDialog(null,
                        "Total employees count: " + employeesCount + "\r\n" +
                                "Total enclosures count: " + enclosuresCount + "\r\n" +
                                "Total animals count: " + totalAnimals + "\r\n" +
                                "Sick animals: " + sickAnimals + "\r\n" +
                                "Different species: " + speciesCount,
                        "Zoo summary", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuPanel.add(enclosuresButton);
        menuPanel.add(employeeButton);
        menuPanel.add(summaryButton);

        // Create a panel for the exit button
        JPanel exitPanel = new JPanel();
        JButton exitButton = new JButton("Close");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });
        exitPanel.add(exitButton);
        frame.add(menuPanel, BorderLayout.CENTER);
        frame.add(exitPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

