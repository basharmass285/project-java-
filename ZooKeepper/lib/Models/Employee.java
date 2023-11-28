package ZooKeepper.lib.Models;

import ZooKeepper.lib.Singleton.IdGeneratorSingleton;

/**
 * The {@code Employee} class represents an employee in the ZooKeeper application.
 * An employee has a name, workplace, and an unique identifier.
 */
public class Employee implements Cloneable {
    /** The name of the employee. */
    private String name;
    /** The workplace identifier where the employee works. */
    private int workplace;
    /** The unique identifier for the employee. */
    private int id;

    /**
     * Constructs a new {@code Employee} object with the specified identifier, name, and workplace.
     *
     * @param id        The unique identifier for the employee.
     * @param name      The name of the employee.
     * @param workplace The workplace identifier where the employee works.
     */
    private Employee(int id, String name, int workplace) {
        this.id = id;
        this.name = name;
        this.workplace = workplace;
    }

    /**
     * Returns the unique identifier for the employee.
     *
     * @return The unique identifier for the employee.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the employee.
     *
     * @return The name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the workplace identifier where the employee works.
     *
     * @return The workplace identifier where the employee works.
     */
    public int getWorkplace() {
        return workplace;
    }

    /**
     * Clones the employee by creating a new instance with the same name, workplace, and identifier.
     *
     * @return A cloned instance of the employee.
     * @throws CloneNotSupportedException If cloning is not supported for this object.
     */
    @Override
    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }

    /**
     * Returns a string representation of the employee.
     *
     * @return A string describing the employee.
     */
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workplace=" + workplace +
                '}';
    }

    /**
     * The {@code Builder} class is used to create instances of {@code Employee} with a fluent interface.
     */
    public static class Builder {
        private String name;
        private int enclosureId;

        /**
         * Sets the name of the employee.
         *
         * @param name The name of the employee.
         * @return This builder for method chaining.
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the workplace identifier where the employee works.
         *
         * @param enclosureId The workplace identifier.
         * @return This builder for method chaining.
         */
        public Builder setEnclosureID(int enclosureId) {
            this.enclosureId = enclosureId;
            return this;
        }

        /**
         * Builds and returns a new {@code Employee} object based on the configured values.
         *
         * @return A new {@code Employee} object.
         */
        public Employee build() {
            Employee employeeCreated = new Employee(IdGeneratorSingleton.getNewId(), this.name, this.enclosureId);
            return employeeCreated;
        }
    }
}
