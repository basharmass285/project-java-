package ZooKeepper.lib.Singleton;

import java.util.ArrayList;

import ZooKeepper.lib.Models.Employee;

public class EmployeeDatabaseSingleton {
    private static ArrayList<Employee> employeesDatabase = new ArrayList<>();

    // Create - Add an item to the database
    public static void create(Employee item) {
        employeesDatabase.add(item);
    }

    // Read - Retrieve all items from the database
    public static ArrayList<Employee> readAll() {
        return new ArrayList<>(employeesDatabase); // Return a copy to prevent direct modification
    }

    // Read - Retrieve a specific item by index
    public static Employee read(int id) {
        if (id >= 0) {
            for (Employee a : employeesDatabase)
                if (a.getId() == id)
                    return a;
        }
        return null; // Index out of bounds or item not found
    }

    // Update - Update an item at a specific index
    public static boolean update(Employee updatedItem) {
        if (updatedItem != null) {
            for (int index = 0; index < employeesDatabase.size(); index++) {
                if (employeesDatabase.get(index).getId() == updatedItem.getId()) {
                    employeesDatabase.set(index, updatedItem);
                    return true; // Update successful
                }
            }
        }
        return false; // Index out of bounds or item not found
    }

    // Delete - Remove an item at a specific id
    public static boolean delete(int id) {
        int index = -1;
        for (int i = 0; i < employeesDatabase.size(); i++) {
            if (employeesDatabase.get(i).getId() == id)
                index = i;
        }
        if (index >= 0) {
            employeesDatabase.remove(index);
            return true;
        } else
            return false; // Index out of bounds or item not found
    }
}
