
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ZooKeepper.lib.Abstract.Animal;
import ZooKeepper.lib.Factory.AnimalFactory;
import ZooKeepper.lib.Factory.EmployeeFactory;
import ZooKeepper.lib.Factory.EnclosureFactory;
import ZooKeepper.lib.Models.BearModel;
import ZooKeepper.lib.Models.CrocodileModel;
import ZooKeepper.lib.Models.Employee;
import ZooKeepper.lib.Models.Enclosure;
import ZooKeepper.lib.Models.MonkeyModel;

public class AppTests {

    @Test
    public void testCreateNewEnclosure() {
        String description = "Monkey";

        Enclosure enclosure = EnclosureFactory.createNewEnclosure(description);

        assertNotNull(enclosure);
        assertEquals(description, enclosure.getAnimalDescription());
    }

    @Test
    public void testCreateEmployee() {
        String name = "John Doe";
        int enclosureId = 1;

        Employee employee = EmployeeFactory.CreateEmployee(name, enclosureId);

        assertNotNull(employee);
        assertEquals(name, employee.getName());
        assertEquals(enclosureId, employee.getWorkplace());
    }

    @Test
    public void testCreateBear() {
        String species = "Brown Bear";
        int age = 5;
        boolean isHealthy = true;

        BearModel bear = AnimalFactory.createBear(species, age, isHealthy);

        assertNotNull(bear);
        assertEquals(species, bear.getSpecies());
        assertEquals(age, bear.getAge());
        assertEquals(isHealthy, bear.isHealthy());
    }

    @Test
    public void testCreateMonkey() {
        String species = "Spider Monkey";
        int age = 3;
        boolean isHealthy = false;

        MonkeyModel monkey = AnimalFactory.createMonkey(species, age, isHealthy);

        assertNotNull(monkey);
        assertEquals(species, monkey.getSpecies());
        assertEquals(age, monkey.getAge());
        assertEquals(isHealthy, monkey.isHealthy());
    }

    @Test
    public void testCreateCrocodile() {
        String species = "Nile Crocodile";
        int age = 10;
        boolean isHealthy = true;

        CrocodileModel crocodile = AnimalFactory.createCrocodile(species, age, isHealthy);

        assertNotNull(crocodile);
        assertEquals(species, crocodile.getSpecies());
        assertEquals(age, crocodile.getAge());
        assertEquals(isHealthy, crocodile.isHealthy());
    }

    @Test
    public void testCloneAnimal() {
        // Create a sample animal
        BearModel originalBear = AnimalFactory.createBear("Grizzly Bear", 7, true);

        // Clone the animal
        Animal clonedBear = AnimalFactory.cloneAnimal(originalBear);

        assertNotNull(clonedBear);
        assertNotSame(originalBear, clonedBear); // Ensure it's a different instance
        assertEquals(originalBear.getSpecies(), clonedBear.getSpecies());
        assertEquals(1, clonedBear.getAgeMonths());
        assertEquals(originalBear.isHealthy(), clonedBear.isHealthy());
    }

    @Test
    public void testCloneUnknownAnimal() {
        // Create an animal of an unknown type
        Animal unknownAnimal = new Animal("Unknown Animal", 1, true) {
            @Override
            public void makeSound() {
                // Not needed for this test
            }

            @Override
            public float feedAnimal(float foodWeight) {
                // Not needed for this test
                return 0;
            }
        };

        // Try to clone the unknown animal
        Animal clonedAnimal = AnimalFactory.cloneAnimal(unknownAnimal);

        assertNull(clonedAnimal);
    }

    private Enclosure enclosure;

    @Before
    public void setUp() {
        // Create a new Enclosure for each test
        enclosure = new Enclosure.Builder()
                .setDescription("Lion Enclosure")
                .build();
    }

    @Test
    public void testGetId() {
        assertNotEquals(0, enclosure.getId());
    }

    @Test
    public void testGetAnimalCount() {
        assertEquals(0, enclosure.getAnimalCount());
    }

    @Test
    public void testGetAnimalDescription() {
        assertEquals("Lion Enclosure", enclosure.getAnimalDescription());
    }

    @Test
    public void testSetAnimalDescription() {
        enclosure.setAnimalDescription("Tiger Enclosure");
        assertEquals("Tiger Enclosure", enclosure.getAnimalDescription());
    }

    @Test
    public void testAddObserver() {
        Animal observer = AnimalFactory.createBear("test", 1, true);
        enclosure.addObserver(observer);
        assertEquals(1, enclosure.getObservers().size());
    }

    @Test
    public void testRemoveObserver() {
        Animal observer = AnimalFactory.createBear("test", 1, true);
        enclosure.addObserver(observer);
        enclosure.removeObserver(observer);
        assertEquals(0, enclosure.getObservers().size());
    }

    @Test
    public void testNotifyObservers() {
        Animal observer = AnimalFactory.createBear("test", 1, true);
        float oldWeight = observer.getWeight();
        enclosure.addObserver(observer);
        enclosure.putFood(10.0f); // Notify observers about feeding
        assertNotEquals(oldWeight, ((Animal) observer).getWeight(), 0.01);
    }

}
