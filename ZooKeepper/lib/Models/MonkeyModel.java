package ZooKeepper.lib.Models;

import java.util.Random;

import ZooKeepper.lib.Abstract.Animal;
import ZooKeepper.lib.Singleton.IdGeneratorSingleton;

public class MonkeyModel extends Animal{

    private MonkeyModel(String species, int age, boolean isHealthy) {
        super(species, age, isHealthy);
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return ageMonths;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    @Override
    public void makeSound() {
        System.out.println("MONKEY DOES: UH UH UH AH AH AH");
    }

    @Override
    public float feedAnimal(float foodWeight) {
        Random rand = new Random();
        this.weight += 0.01 * foodWeight + rand.nextFloat();
        float sickChance = rand.nextFloat();
        if (sickChance > 0.75) {
            setHealthy(false);
        }
        return weight;
    }

    @Override
    public String toString() {
        return "MonkeyModel{" +
                "species='" + species + '\'' +
                ", age=" + ageMonths +
                ", isHealthy=" + isHealthy +
                ", weight=" + weight +
                '}';
    }

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

        public MonkeyModel build() {
            MonkeyModel monkeyModel = new MonkeyModel(species,age, isHealthy);
            //average weight of a monkey
            monkeyModel.setWeight(6.7f);
            monkeyModel.id = IdGeneratorSingleton.getNewId();
            return monkeyModel;
        }
    }
}
