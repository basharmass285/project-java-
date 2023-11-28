package ZooKeepper.lib.Factory;

import ZooKeepper.lib.Models.Enclosure;

/**
 * The {@code EnclosureFactory} class provides methods to create new Enclosure instances.
 */
public class EnclosureFactory {
    /**
     * Creates a new Enclosure instance with the specified description.
     *
     * @param description The description of the Enclosure.
     * @return A new Enclosure instance.
     */
    public static Enclosure createNewEnclosure(String description){
        return new Enclosure.Builder().setDescription(description).build();
    }
}
