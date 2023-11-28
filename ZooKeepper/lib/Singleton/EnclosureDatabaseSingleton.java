package ZooKeepper.lib.Singleton;

import java.util.ArrayList;

import ZooKeepper.lib.Models.Enclosure;

public class EnclosureDatabaseSingleton {
    private static ArrayList<Enclosure> enclosureDatabase = new ArrayList<>();

    // Create - Add an item to the database
    public static void create(Enclosure item) {
        enclosureDatabase.add(item);
    }

    // Read - Retrieve all items from the database
    public static ArrayList<Enclosure> readAll() {
        return new ArrayList<>(enclosureDatabase); // Return a copy to prevent direct modification
    }

    // Read - Retrieve a specific item by index
    public static Enclosure read(int id) {
        if (id >= 0) {
            for (Enclosure a : enclosureDatabase)
                if (a.getId() == id)
                    return a;
        }
        return null; // Index out of bounds or item not found
    }

    // Update - Update an item at a specific index
    public static boolean update(Enclosure updatedItem) {
        if (updatedItem != null) {
            for (int index = 0; index < enclosureDatabase.size(); index++) {
                if (enclosureDatabase.get(index).getId() == updatedItem.getId()) {
                    enclosureDatabase.set(index, updatedItem);
                    return true; // Update successful
                }
            }
        }
        return false; // Index out of bounds or item not found
    }

    // Delete - Remove an item at a specific id
    public static boolean delete(int id) {
        int index = -1;
        for (int i = 0; i < enclosureDatabase.size(); i++) {
            if (enclosureDatabase.get(i).getId() == id)
                index = i;
        }
        if (index >= 0) {
            enclosureDatabase.remove(index);
            return true;
        } else
            return false; // Index out of bounds or item not found
    }
}
