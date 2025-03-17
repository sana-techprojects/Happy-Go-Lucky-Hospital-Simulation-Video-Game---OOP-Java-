import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The ER class represents an Emergency Room checkpoint in the simulation.
 * It checks whether an emergency patient is present at the checkpoint and whether the patient is alive.
 * 
 * <p><b>Author:</b></p>
 * <ul>
 *   <li>Vincent</li>
 * </ul>
 * 
 * <p><b>Version:</b> 1.2 (11/18/2023)</p>
 * 
 * @author Vincent
 * @version 1.2 (11/18/2023)
 */
public class ER extends Checkpoint
{
    // Image for the Emergency Room checkpoint
    GreenfootImage erImage;
    
    /**
     * Constructs an ER checkpoint and initializes it with an empty status.
     */
    public ER() {
        super();
        erImage = new GreenfootImage("ER.png");
        setImage(erImage);
    }
    
    /**
     * Act - do whatever the ER wants to do.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Check for an EmergencyPatient and Patient at the checkpoint
        EmerPatient emergencypatient = (EmerPatient)getOneObjectAtOffset(0, 0, EmerPatient.class);
        Patient patient = (Patient)getOneObjectAtOffset(0, 0, Patient.class);
        
        // Update isEmpty status based on the presence of a live emergency patient
        if(emergencypatient != null && !emergencypatient.getDeadStatus()) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }
    }
}
