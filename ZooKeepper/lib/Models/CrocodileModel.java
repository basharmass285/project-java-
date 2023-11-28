package ZooKeepper.lib.Models;

import java.util.Random;

import ZooKeepper.lib.Abstract.Animal;
import ZooKeepper.lib.Singleton.IdGeneratorSingleton;

public class CrocodileModel extends Animal {
    // Private constructor to enforce the use of the Builder
    private CrocodileModel(String species, int age, boolean isHealthy) {
        super(species, age, isHealthy);
    }

    // Getter methods for fields (if needed)
    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return ageMonths;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.ageMonths = age;
    }

    public void setHealthy(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }

    @Override
    public void makeSound() {
        System.out.println("COCODILES MAKE A HISSING SOUND");
    }

    @Override
    public float feedAnimal(float foodWeight) {
        Random rand = new Random();
        this.weight += 0.03 * foodWeight + rand.nextFloat();
        float sickChance = rand.nextFloat();
        if (sickChance > 0.95) {
            setHealthy(false);
        }
        return weight;
    }

    // Optional: A static factory method for creating a builder instance
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "CrocodileModel{" +
                "species='" + species + '\'' +
                ", age=" + ageMonths +
                ", isHealthy=" + isHealthy +
                ", weight=" + weight +
                '}';
    }

    // Builder class
    public static class Builder {
        private String species;
        private int age;
        private boolean isHealthy;

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setHealthy(boolean isHealthy) {
            this.isHealthy = isHealthy;
            return this;
        }

        public CrocodileModel build() {
            CrocodileModel crocodile = new CrocodileModel(species, age, isHealthy);
            // Average crocodile weight in kg
            crocodile.setWeight(700);
            crocodile.id = IdGeneratorSingleton.getNewId();
            return crocodile;
        }
    }

}
