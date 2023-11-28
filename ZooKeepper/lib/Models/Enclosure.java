package ZooKeepper.lib.Models;

import java.util.ArrayList;

import ZooKeepper.lib.Observer.Observer;
import ZooKeepper.lib.Observer.Subject;
import ZooKeepper.lib.Singleton.IdGeneratorSingleton;

/**
 * The {@code Enclosure} class represents an enclosure where animals are kept.
 * It implements the {@link Subject} interface to manage and notify observers about enclosure events.
 */
public class Enclosure implements Subject {
    private ArrayList<Observer> observers = new ArrayList<>();
    private String animalDescription;
    private int id;

    /**
     * Constructs a new {@code Enclosure} with the specified animal description and identification.
     *
     * @param animalDescription The description of the animals in the enclosure.
     * @param identification    The unique identification number of the enclosure.
     */
    private Enclosure(String animalDescription, int identification) {
        this.animalDescription = animalDescription;
        this.id = identification;
    }

    /**
     * Gets the unique identification number of the enclosure.
     *
     * @return The identification number of the enclosure.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the number of animals in the enclosure.
     *
     * @return The count of animals in the enclosure.
     */
    public int getAnimalCount() {
        return observers.size();
    }

    /**
     * Gets the description of the animals in the enclosure.
     *
     * @return The description of the animals.
     */
    public String getAnimalDescription() {
        return animalDescription;
    }

    /**
     * Sets the description of the animals in the enclosure.
     *
     * @param animalDescription The new description of the animals.
     */
    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }

    /**
     * Places food in the enclosure and notifies observers about the feeding action.
     *
     * @param foodWeight The weight of food placed in the enclosure (in kilograms).
     */
    public void putFood(float foodWeight) {
        System.out.println(foodWeight + "kg of Food is placed in the cage.");
        notifyObservers(Actions.FEED, foodWeight);
    }

    /**
     * Gives medicine to sick animals in the enclosure and notifies observers.
     */
    public void putMedicine() {
        System.out.println("Medicine was given to the sick animals of this enclosure.");
        notifyObservers(Actions.MEDICINE, null);
    }

    /**
     * Adds an observer to the enclosure's list of observers.
     *
     * @param observer The observer to add.
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Adds a list of observers to the enclosure's list of observers.
     *
     * @param observer The list of observers to add.
     */
    public void addObserver(ArrayList<Observer> observer) {
        observers.addAll(observer);
    }

    /**
     * Removes an observer from the enclosure's list of observers.
     *
     * @param observer The observer to remove.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers about an action and its associated value.
     *
     * @param action The action to notify observers about.
     * @param value  The value associated with the action.
     */
    @Override
    public void notifyObservers(Actions action, Object value) {
        for (Observer observer : observers) {
            observer.update(action, value);
        }
    }

    /**
     * Gets the list of observers for the enclosure.
     *
     * @return The list of observers.
     */
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    /**
     * The {@code Builder} class is responsible for creating instances of {@code Enclosure}.
     */
    public static class Builder {
        private String descriptioString;

        /**
         * Sets the description of the enclosure.
         *
         * @param description The description of the enclosure.
         * @return The {@code Builder} instance.
         */
        public Builder setDescription(String description) {
            this.descriptioString = description;
            return this;
        }

        /**
         * Builds and returns an {@code Enclosure} with the specified description and a unique ID.
         *
         * @return The newly created {@code Enclosure} instance.
         */
        public Enclosure build() {
            Enclosure enclosureCreated = new Enclosure(this.descriptioString, IdGeneratorSingleton.getNewId());
            return enclosureCreated;
        }
    }
}
