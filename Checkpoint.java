import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Checkpoint class represents a checkpoint in the simulation.
 * It checks whether a patient is present at the checkpoint or not.
 * 
 * <p><b>Author:</b></p>
 * <ul>
 *   <li>Vincent</li>
 * </ul>
 * 
 * <p><b>Version:</b> 11/30/27</p>
 * 
 * @vincent
 * @version 11/30/27
 */
public class Checkpoint extends Actor
{
    // Flag to indicate whether the checkpoint is empty or not
    protected boolean isEmpty;
    
    /**
     * Initializes the Checkpoint with an initial state of being empty.
     */
    public Checkpoint()
    {
        isEmpty = true;
    }
    
    /**
     * Act - do whatever the Checkpoint wants to do.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Check for a Patient at different offsets and intersections
        Patient patient = (Patient)getOneObjectAtOffset(getImage().getWidth(), 0, Patient.class);
        if(patient == null) {
            patient = (Patient)getOneObjectAtOffset(0, 0, Patient.class); 
        }
        if(patient == null) {
            patient = (Patient)getOneIntersectingObject(Patient.class);
        }
        
        // Update isEmpty status based on the presence of a patient
        if(patient != null) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }
    }
    
    /**
     * Sets the empty status of the checkpoint.
     * 
     * @param isEmpty The empty status to be set.
     */
    public void setEmptyStatus(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
    /**
     * Gets the current empty status of the checkpoint.
     * 
     * @return True if the checkpoint is empty, false otherwise.
     */
    public boolean isEmptyStatus() {
        return isEmpty;
    }
}
