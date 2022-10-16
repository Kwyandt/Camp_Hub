import java.util.UUID;

/***
 * @author Jackson Ginn
 */

public class Activity{
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
    public Activity(String name, String description, String location){

    }

    /***
     * Constructor to load pre-existing Activity
     * @param id UUID for activity
     * @param name title of activity
     * @param description activity description
     * @param location site of activity
     */
    public Activity(String id, String name, String description, String location){

    }

    /***
     * Accessor for name
     * @return name of the activity
     */
    public String getName(){
        return name;
    }

    /***
     * Accessor for description
     * @return description of the activity
     */
    public String getDescription(){
        return description;
    }

    /***
     * Accessor for location
     * @return location of the activity
     */
    public String getLocation(){
        return location;
    }

    /***
     * Mutator for name
     * @param name new name value
     */
    public void setName(String name){

    }

    /***
     * Mutator for description
     * @param description new description value
     */
    public void setDescription(String description){

    }

    /***
     * Mutator for location
     * @param location new location value
     */
    public void setLocation(String location){

    }

    /***
     * toString for Activity
     * @return String representation of Activity
     */
    public String toString(){
        return "placeholder";
    }
}