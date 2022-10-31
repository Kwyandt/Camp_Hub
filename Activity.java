import java.util.UUID;

/***
 * @author Jackson Ginn
 */

public class Activity {
    private UUID id;
    private String name;
    private String description;
    private String location;

    /***
     * Constructor to create new Activity
     * @param name title of activity
     * @param description activity description
     * @param location site of activity
     */
    public Activity(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    /***
     * Constructor to load pre-existing Activity
     * @param id UUID for activity
     * @param name title of activity
     * @param description activity description
     * @param location site of activity
     */
    public Activity(String id, String name, String description, String location) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.description = description;
        this.location = location;
    }

    /***
     * Accessor for id
     * @return id of activity
     */
    public UUID getId() {
        return id;
    }

    /***
     * Accessor for name
     * @return name of the activity
     */
    public String getName() {
        return name;
    }

    /***
     * Accessor for description
     * @return description of the activity
     */
    public String getDescription() {
        return description;
    }

    /***
     * Accessor for location
     * @return location of the activity
     */
    public String getLocation() {
        return location;
    }

    /***
     * Mutator for name
     * @param name new name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Mutator for description
     * @param description new description value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /***
     * Mutator for location
     * @param location new location value
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * equals method for activity class
     * @param anActivity to be comapred to this.activity
     * @return boolean true or fasle
     */
    public boolean equals (Activity anActivity) {
        return anActivity != null &&
            this.name.equals(anActivity.getName()) &&
            this.description.equals(anActivity.getDescription()) &&
            this.location.equals(anActivity.getLocation());
    }

    /***
     * toString for Activity
     * @return String representation of Activity
     */
    public String toString() {
        return name + " - " + description;
    }
}