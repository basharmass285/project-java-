package ZooKeepper.lib.Factory;

import ZooKeepper.lib.Abstract.Animal;
import ZooKeepper.lib.Models.*;

/**
 * The {@code AnimalFactory} class is responsible for creating instances of different animal types.
 */
public class AnimalFactory {
    /**
     * Creates a new instance of a bear with the specified species, age, and health status.
     *
     * @param species   The species of the bear.
     * @param age       The age of the bear in months.
     * @param isHealthy The health status of the bear (true if healthy, false otherwise).
     * @return A new {@code BearModel} instance.
     */
    public static BearModel createBear(String species, int age, boolean isHealthy) {
        return new BearModel.Builder()
                .setSpecies(species)
                .setAge(age)
                .setHealthy(isHealthy)
                .build();
    }

    /**
     * Creates a new instance of a monkey with the specified species, age, and health status.
     *
     * @param species   The species of the monkey.
     * @param age       The age of the monkey in months.
     * @param isHealthy The health status of the monkey (true if healthy, false otherwise).
     * @return A new {@code MonkeyModel} instance.
     */
    public static MonkeyModel createMonkey(String species, int age, boolean isHealthy) {
        return new MonkeyModel.Builder()
                .setSpecies(species)
                .setAge(age)
                .setHealthy(isHealthy)
                .build();
    }

    /**
     * Creates a new instance of a crocodile with the specified species, age, and health status.
     *
     * @param species   The species of the crocodile.
     * @param age       The age of the crocodile in months.
     * @param isHealthy The health status of the crocodile (true if healthy, false otherwise).
     * @return A new {@code CrocodileModel} instance.
     */
    public static CrocodileModel createCrocodile(String species, int age, boolean isHealthy) {
        return new CrocodileModel.Builder()
                .setSpecies(species)
                .setAge(age)
                .setHealthy(isHealthy)
                .build();
    }

    /**
     * Clones an existing animal.
     *
     * @param cloningTarget The animal to be cloned.
     * @return A new instance of the cloned animal, or null if cloning fails.
     */
    public static Animal cloneAnimal(Animal cloningTarget) {
        Animal resultAnimal = null;
        if (cloningTarget instanceof BearModel) {
            resultAnimal = AnimalFactory.createBear(cloningTarget.getSpecies(), 1, true);
        } else if (cloningTarget instanceof MonkeyModel) {
            resultAnimal = AnimalFactory.createMonkey(cloningTarget.getSpecies(), 1, true);
        } else if (cloningTarget instanceof CrocodileModel) {
            resultAnimal = AnimalFactory.createCrocodile(cloningTarget.getSpecies(), 1, true);
        } else {
            System.out.println("Not a known animal. Couldn't clone.");
        }
        return resultAnimal;
    }

}
