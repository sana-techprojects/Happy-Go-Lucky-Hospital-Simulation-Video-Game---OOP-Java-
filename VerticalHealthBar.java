/**
 * The VerticalHealthBar class represents the reputation of the overall simulation.
 * It visually displays the health status of the hospital.
 * 
 * <p><b>Author:</b> Sana</p>
 * 
 * @version 1.0
 */
import greenfoot.*;

public class VerticalHealthBar extends Actor {
    private int health;
    private int previousNumPatientsLeft;
    private boolean isDead;
    private SimpleTimer deadTimer;

    /**
     * Constructor for the VerticalHealthBar class.
     * Initializes the health bar with default values.
     */
    public VerticalHealthBar() {
        health = 100;
        previousNumPatientsLeft = 0;
        isDead = false;
        deadTimer = new SimpleTimer();
        updateImage();
    }

    /**
     * Act method for the VerticalHealthBar class.
     * Handles health changes based on the number of patients and associated EmerPatient's status.
     */
    public void act() {
        // Check if numPatientsLeft has increased
        if (Patient.numPatientsLeft > previousNumPatientsLeft) {
            decreaseHealth();
        } else {
            increaseHealth();
        }

        // Check if the associated EmerPatient is dead
        EmerPatient associatedEmerPatient = getAssociatedEmerPatient();
        if (associatedEmerPatient != null && associatedEmerPatient.getDeadStatus()) {
            if (!isDead) {
                // If the EmerPatient is dead, drop the health bar significantly
                health = 0; // Set health to the bottom
                isDead = true;
                deadTimer.mark();
            } else {
                // If the patient is dead, keep the health bar at the bottom for a certain duration
                if (deadTimer.millisElapsed() < 5000) { // Adjust the duration (in milliseconds)
                    health = 0;
                } else {
                    // After the duration, allow the health bar to fluctuate again
                    isDead = false;
                }
            }
        }

        // Update the previous value of numPatientsLeft
        previousNumPatientsLeft = Patient.numPatientsLeft;

        updateImage();
    }

    /**
     * Increases the health based on a random fluctuation.
     */
    private void increaseHealth() {
        int fluctuation = Greenfoot.getRandomNumber(6); // Random value between 0 and 5
        health += fluctuation * 2; // Increase the multiplier for a more noticeable change

        // Ensure health stays within a valid range (0 to 100)
        health = Math.max(0, Math.min(100, health));
    }

    /**
     * Decreases the health based on a random fluctuation.
     */
    private void decreaseHealth() {
        int fluctuation = Greenfoot.getRandomNumber(16) + 5; // Random value between 5 and 20
        health -= fluctuation; // Increase the fluctuation range for a more noticeable change

        // Ensure health stays within a valid range (0 to 100)
        health = Math.max(0, Math.min(100, health));
    }

    /**
     * Updates the health bar image based on the current health value.
     */
    private void updateImage() {
        GreenfootImage image = new GreenfootImage(60, 225);

        // Choose color based on health value
        if (health >= 70) {
            image.setColor(Color.GREEN);
        } else if (health >= 30) {
            image.setColor(Color.YELLOW);
        } else {
            image.setColor(Color.RED);
        }

        int barHeight = (int) (health * 2.5); // Adjust multiplier for the desired height
        image.fillRect(0, 225 - barHeight, 60, barHeight);
        setImage(image);
    }
    
    /**
     * Gets the current reputation (health) value.
     * 
     * @return The current health value.
     */
    public int getReputation() {
        return health;
    }
    
    /**
     * Retrieves the EmerPatient associated with this health bar.
     * 
     * @return The associated EmerPatient, or null if not found.
     */
    private EmerPatient getAssociatedEmerPatient() {
        // Find the EmerPatient associated with this health bar
        Actor emerPatient = getOneIntersectingObject(EmerPatient.class);
        if (emerPatient != null && emerPatient instanceof EmerPatient) {
            return (EmerPatient) emerPatient;
        }
        return null;
    }
}
