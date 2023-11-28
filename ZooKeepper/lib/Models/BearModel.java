package ZooKeepper.lib.Models;

import ZooKeepper.lib.Abstract.Animal;
import ZooKeepper.lib.Singleton.IdGeneratorSingleton;
import java.util.Random;

public class BearModel extends Animal{
    private BearModel(String species, int age, boolean isHealthy) {
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
    public String toString() {
        return "BearModel{" +
                "species='" + species + '\'' +
                ", age=" + ageMonths +
                ", isHealthy=" + isHealthy +
                ", weight=" + weight +
                '}';
    }

    @Override
    public void makeSound() {
        System.out.println("BEAR MAKES: ROOOOOOAAAARRR");
    }

    @Override
    public float feedAnimal(float foodWeight) {
        Random rand = new Random();
        this.weight += 0.01 * foodWeight + rand.nextFloat();
        float sickChance = rand.nextFloat();
        if(sickChance > 0.85){
            setHealthy(false);
        }
        return weight;
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

        public BearModel build() {
            BearModel bearModel = new BearModel(this.species, this.age, this.isHealthy);
            //Average bear weight in Kg
            bearModel.setWeight(362.87f);
            bearModel.id = IdGeneratorSingleton.getNewId();
            return bearModel;
        }
    }
}

