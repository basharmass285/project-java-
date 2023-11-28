package ZooKeepper.lib.Abstract;

import ZooKeepper.lib.Observer.Observer;
import ZooKeepper.lib.Observer.Subject.Actions;
/**
 * The {@code Animal} class represents an individual animal in a zoo.
 * It contains information about the animal's species, age, and health status.
 * This class implements the Prototype pattern, allowing animals to reproduce
 * by creating new instances of themselves through cloning.
 */
public abstract class Animal implements Cloneable, Observer {
    /** The species of the animal. */
    protected int id; // Unique identifier for the animal
    protected String species; /** The species of the animal. */
    /** The age of the animal in months. */
    protected int ageMonths; /** The age of the animal in months. */
    /** The health status of the animal. */
    protected boolean isHealthy; /** The health status of the animal. */
    /** The health status of the animal. */
    protected float weight; /** The weight of the animal in kilograms. */

    /**
     * Constructs a new {@code Animal} object with the specified species, age, and
     * health status.
     *
     * @param species   The species of the animal.
     * @param ageMonths The age of the animal in months.
     * @param isHealthy The health status of the animal (true if healthy, false
     *                  otherwise).
     */
    public Animal(String species, int ageMonths, boolean isHealthy, float weight) {
        this.species = species;
        this.ageMonths = ageMonths;
        this.isHealthy = isHealthy;
        this.weight = weight;
    }

    public Animal(String species, int ageMonths, boolean isHealthy) {
        this.species = species;
        this.ageMonths = ageMonths;
        this.isHealthy = isHealthy;
        this.weight = 0;
    }

    /**
     * Function that makes the sound of the animal.
     */
    public abstract void makeSound();

    public int getId() {
        return id;
    }

    /**
     * Returns the species of the animal.
     *
     * @return The species of the animal.
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Returns the age of the animal in months.
     *
     * @return The age of the animal in months.
     */
    public int getAgeMonths() {
        return ageMonths;
    }

    /**
     * Returns the health status of the animal.
     *
     * @return {@code true} if the animal is healthy, {@code false} otherwise.
     */
    public boolean isHealthy() {
        return isHealthy;
    }

    /**
     * Sets the health status of the animal.
     *
     * @param healthy {@code true} if the animal is healthy, {@code false}
     *                otherwise.
     */
    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    /**
     * Returns the weight status of the animal.
     *
     * @return The weight of the animal in kilograms.
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Sets the weight status of the animal.
     *
     * @param weight The weight of the animal in kilograms.
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Feeds the animal with the specified amount of food and returns the remaining
     * food weight.
     *
     * @param foodWeight The weight of the food in kilograms.
     * @return The remaining food weight after feeding.
     */
    public abstract float feedAnimal(float foodWeight);

    /**
     * Clones the animal by creating a new instance with the same species, age,
     * and health status.
     *
     * @return A cloned instance of the animal.
     * @throws CloneNotSupportedException If cloning is not supported for this
     *                                    object.
     */
    @Override
    public Animal clone() throws CloneNotSupportedException {
        return (Animal) super.clone();
    }

    /**
     * Returns a string representation of the animal.
     *
     * @return A string describing the animal.
     */
    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", ageMonths=" + ageMonths +
                ", isHealthy=" + isHealthy +
                '}';
    }

    /**
     * Updates the animal's state based on the action and value received.
     *
     * @param actions The action performed (e.g., FEED, MEDICINE).
     * @param value   The value associated with the action.
     */
    @Override
    public void update(Actions actions, Object value) {
        if (actions.equals(Actions.FEED)) {
            this.feedAnimal((float) value);
        } else if (actions.equals(Actions.MEDICINE) && !this.isHealthy()) {
            this.setHealthy(true);
        }
    }
}
