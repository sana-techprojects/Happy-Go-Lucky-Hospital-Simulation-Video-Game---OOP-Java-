/**
 * The PatientSpawner class represents an actor responsible for spawning patients in the simulation.
 * It determines the type of patient to spawn based on random values and manages the spawn frequency.
 * 
 * <p><b>Authors:</b></p>
 * <ul>
 *   <li>Sana</li>
 *   <li>Modified by: Vincent</li>
 *   <li>Version 2.0</li>
 * </ul>
 * 
 * @author Sana
 * @version 2.0
 */
import greenfoot.*;
import java.util.Random;

public class PatientSpawner extends Actor
{
    // Frequency at which patients are spawned
    private int spawnFrequency;
    
    // Timer to control the spawn frequency
    private int spawnTimer;
    
    // Random generator for determining patient types
    private Random random;
    
    // Image for displaying the spawner
    GreenfootImage spawnerImage = new GreenfootImage("patientSpawner.png");
    
    // Success rate for emergent patients
    private int emerPatientSuccessRate;
    
    // Ratio of sick patients among total patients
    private int sickRatio;
    
    // Sound effect for the Emergency Room (ER)
    GreenfootSound erSound;
    
    /**
     * Constructor for the PatientSpawner class.
     * 
     * @param userSickRatio The ratio of sick patients among total patients.
     * @param userSpawnFrequency The frequency at which patients are spawned.
     * @param userEmerPatientSuccessRate The success rate for emergent patients.
     */
    public PatientSpawner(int userSickRatio, int userSpawnFrequency, int userEmerPatientSuccessRate) {
        erSound = new GreenfootSound("erSound.mp3");
        
        spawnFrequency = userSpawnFrequency;
        spawnTimer = 0;
        random = new Random();
        
        sickRatio = userSickRatio;
        emerPatientSuccessRate = userEmerPatientSuccessRate;
        setImage(spawnerImage);
    }

    /**
     * Act method for the PatientSpawner class.
     * Handles the spawn frequency and triggers patient spawning.
     */
    public void act() {
        spawnTimer++;

        // Check if it's time to spawn a patient
        if (spawnTimer >= spawnFrequency && !detectPatient()) {
            spawnPatient();
            spawnTimer = 0; // Reset the timer
        }
    }
    
    /**
     * Checks for the presence of a patient near the spawner.
     * 
     * @return True if a patient is detected, false otherwise.
     * @author vincent
     */
    public boolean detectPatient() {
        Patient isPatientThere = (Patient)getOneObjectAtOffset(getImage().getWidth() + 100, 0, Patient.class);
        if(isPatientThere == null) {
            isPatientThere = (Patient)getOneObjectAtOffset(getImage().getWidth() + 50, 0, Patient.class);
        }
        if(isPatientThere == null) {
            isPatientThere = (Patient)getOneObjectAtOffset(getImage().getWidth() + 30, 0, Patient.class);
        }
        if(isPatientThere == null) {
            isPatientThere = (Patient)getOneObjectAtOffset(getImage().getWidth() + 20, 0, Patient.class);
        }
        if(isPatientThere == null) {
            isPatientThere = (Patient)getOneObjectAtOffset(getImage().getWidth() + 10, 0, Patient.class);
        }
        if(isPatientThere == null) {
            isPatientThere = (Patient)getOneIntersectingObject(Patient.class);
        }
        return isPatientThere != null;
    }
    
    /**
     * Spawns a patient based on random values and their respective types.
     * @author vincent
     */
    private void spawnPatient() {
        // Randomly decide whether to spawn a HealthyPatient, SickPatient, or EmerPatient
        int randomNumber = Greenfoot.getRandomNumber(100);

        if (randomNumber <= sickRatio) 
        {
            HealthyPatient healthyPatient = new HealthyPatient();
            getWorld().addObject(healthyPatient, getX(), getY());
        } 
        else if (randomNumber >= sickRatio) 
        {
            SickPatient sickPatient = new SickPatient();
            getWorld().addObject(sickPatient, getX(), getY());
        } 
        if(randomNumber % 15 == 0)
        {
            EmerPatient emergentPatient = new EmerPatient(emerPatientSuccessRate);
            getWorld().addObject(emergentPatient, getX(), getY());
            erSound.play();
        }
    }
}
