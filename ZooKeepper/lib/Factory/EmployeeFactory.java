package ZooKeepper.lib.Factory;

import ZooKeepper.lib.Models.Employee;

/**
 * The {@code EmployeeFactory} class provides methods to create new Employee instances.
 */
public class EmployeeFactory {
    /**
     * Creates a new Employee instance with the specified name and enclosure ID.
     *
     * @param name        The name of the Employee.
     * @param enclosureId The ID of the enclosure where the Employee works.
     * @return A new Employee instance.
     */
    public static Employee CreateEmployee(String name, int enclosureId) {
        return new Employee.Builder()
                .setName(name)
                .setEnclosureID(enclosureId)
                .build();
    }
}
