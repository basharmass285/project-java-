package ZooKeepper.lib.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Random;

import ZooKeepper.lib.Abstract.Animal;
import ZooKeepper.lib.Factory.AnimalFactory;
import ZooKeepper.lib.Models.Enclosure;
import ZooKeepper.lib.Observer.Observer;
import ZooKeepper.lib.Singleton.InterfaceSingleton;

public class AnimalsView {
    private static JFrame animalsListFrame;
    private static JTable animalTable;
    // private static JTable enclosuresTable;
    // private static Enclosure enclosureSelected;

    public static void displayEnclosureAnimals(Enclosure enclosure, WindowAdapter windowAdapter) {
        // Create a JFrame to display the enclosure's animals
        animalsListFrame = new JFrame("Enclosure Animals - Enclosure " + enclosure.getId());
        animalsListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        animalsListFrame.setSize(800, 600);
        animalsListFrame.setLocationRelativeTo(InterfaceSingleton.getWindow());
        animalsListFrame.setLayout(new BorderLayout());
        animalsListFrame.addWindowListener(windowAdapter);

        animalTable = new JTable();
        updateAnimalsTable(enclosure);
        JScrollPane scrollPane = new JScrollPane(animalTable);
        animalsListFrame.add(scrollPane, BorderLayout.NORTH);

        JButton giveMedicines = new JButton("Give medicine");
        giveMedicines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null,
                        "All the sick animais of this enclosure will receive medicine. Do you want to continue?",
                        "Medicine confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (input == 0) {
                    enclosure.putMedicine();
                    updateAnimalsTable(enclosure);
                }
            }
        });

        JButton feedButton = new JButton("Feed");
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null,
                        "All the animais of this enclosure will be feed. They can randomly be sick and will increase the weight. Do you want to continue?",
                        "Feeding confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (input == 0) {
                    enclosure.putFood(100);
                    updateAnimalsTable(enclosure);
                }
            }
        });

        JButton cloningButton = new JButton("Clone");
        cloningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelected = animalTable.getSelectedRow();
                System.out.println(rowSelected);
                if (rowSelected >= 0) {
                    int input = JOptionPane.showConfirmDialog(null,
                            "An attempt to clone the selected animal will be performed. If successful, a new animal of the same species will be created. Do you want to continue?",
                            "Cloning confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (input == 0) {
                        String resultMessage = "";
                        Random random = new Random();
                        ArrayList<Observer> animals = enclosure.getObservers();
                        if (random.nextFloat() > 0.35) {
                            resultMessage = "Animal successfuly cloned!";
                            Animal cloningTarget = (Animal) animals.get(rowSelected);
                            Animal resutAnimal = AnimalFactory.cloneAnimal(cloningTarget);

                            if (resutAnimal == null)
                                resultMessage = "Cloning failed!";
                            else {
                                enclosure.addObserver(resutAnimal);
                            }

                        } else {
                            resultMessage = "Cloning failed!";
                        }
                        JOptionPane.showConfirmDialog(null,
                                resultMessage,
                                "Cloning result", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        updateAnimalsTable(enclosure);
                    }
                }
            }
        });

        JButton addAnimalButton = new JButton("Add new");
        addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnimalWindow(enclosure);
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animalsListFrame.dispose(); // Close the window
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(feedButton);
        buttonPanel.add(giveMedicines);
        buttonPanel.add(cloningButton);
        buttonPanel.add(addAnimalButton);
        buttonPanel.add(closeButton);
        // Display the JFrame
        animalsListFrame.add(buttonPanel, BorderLayout.SOUTH);
        animalsListFrame.setVisible(true);
    }

    private static void updateAnimalsTable(Enclosure enclosure) {
        // Create a table to display the animals
        DefaultTableModel tableModel = new DefaultTableModel(new Object[] { "Species", "Weight", "Age", "Is Healthy?" },
                0);

        // Add animals to the table
        ArrayList<Observer> observers = enclosure.getObservers();
        for (Observer observer : observers) {
            String animalDescription = ((Animal) observer).getSpecies();
            String animalWeighString = Float.toString(((Animal) observer).getWeight());
            String animalIsHealthy = ((Animal) observer).isHealthy() ? "Yes" : "No";
            String animalAge = Integer.toString(((Animal) observer).getAgeMonths());
            tableModel.addRow(new Object[] { animalDescription, animalWeighString, animalAge, animalIsHealthy });
        }
        animalTable.setModel(tableModel);
    }

    public static void addAnimalWindow(Enclosure enclosure) {
        JFrame addAnimalFrame;
        JTextField speciesField;
        JTextField ageField;
        JCheckBox healthyCheckBox;
        JComboBox<String> animalModelComboBox;
        addAnimalFrame = new JFrame("Add New Animal");
        addAnimalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addAnimalFrame.setSize(400, 200);
        addAnimalFrame.setLocationRelativeTo(animalsListFrame);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        speciesField = new JTextField();
        ageField = new JTextField();
        healthyCheckBox = new JCheckBox("Is Healthy");

        // Create a combo box with animal model names
        String[] animalModels = { "Bear", "Monkey", "Crocodile" };
        animalModelComboBox = new JComboBox<>(animalModels);

        panel.add(new JLabel("Species:"));
        panel.add(speciesField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Health:"));
        panel.add(healthyCheckBox);
        panel.add(new JLabel("Animal Model:"));
        panel.add(animalModelComboBox);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered values and create an animal instance
                String species = speciesField.getText();
                int age = Integer.parseInt(ageField.getText());
                boolean isHealthy = healthyCheckBox.isSelected();
                String selectedAnimalModel = (String) animalModelComboBox.getSelectedItem();

                Animal animal = createAnimal(selectedAnimalModel, species, age, isHealthy);

                // Add the new animal to the list and update the table
                enclosure.addObserver(animal);
                updateAnimalsTable(enclosure);

                // Close the Add Animal window
                addAnimalFrame.dispose();
            }
        });

        panel.add(saveButton);

        addAnimalFrame.add(panel);
        addAnimalFrame.setVisible(true);
    }

    private static Animal createAnimal(String animalModel, String species, int age, boolean isHealthy) {
        switch (animalModel) {
            case "Bear":
                return AnimalFactory.createBear(species, age, isHealthy);
            case "Monkey":
                return AnimalFactory.createMonkey(species, age, isHealthy);
            case "Crocodile":
                return AnimalFactory.createCrocodile(species, age, isHealthy);
            default:
                throw new IllegalArgumentException("Invalid animal model");
        }
    }
}
