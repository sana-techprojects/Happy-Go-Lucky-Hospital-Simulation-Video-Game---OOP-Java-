import greenfoot.*; 

/**
 * The HospitalBuilding class represents a hospital building in the simulation.
 * It handles transitioning the hospital image which zooms in to the title of the simulation.
 * 
 * <p><b>Author:</b></p>
 * <ul>
 *   <li>Sana</li>
 * </ul>
 * 
 * @author Sana
 */
public class HospitalBuilding extends Actor
{
    // Hospital image
    private GreenfootImage hospitalImg;
    
    // Timer for managing transitions
    private SimpleTimer timer = new SimpleTimer();
    
    // Flag indicating whether the building is currently transitioning
    private boolean isTransitioning = false;
    
    // Step in the transition process
    private int transitionStep = 0;
    
    // Car object associated with the hospital building
    Car car = new Car();
    
    /**
     * Constructs a HospitalBuilding object with an initial hospital image and starts the timer.
     */
    public HospitalBuilding()
    {
        hospitalImg = new GreenfootImage("image-removebg-preview1.png");
        hospitalImg.scale(290, 250);
        setImage(hospitalImg);
        timer.mark();
    }

    /**
     * Act - do whatever the HospitalBuilding wants to do.
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Check if the transition should start
        if (!isTransitioning && timer.millisElapsed() > 36000) {
            startTransition();
        }
    
        // If transitioning, proceed with the transition
        if (isTransitioning) {
            transition();
        }
    }

    /**
     * Starts the transition by setting the initial location and marking the timer.
     */
    private void startTransition() {
        setLocation(550, 120);
        isTransitioning = true;
        timer.mark();  // Reset the timer for the next step
    }

    /**
     * Manages the different steps in the transition process.
     */
    private void transition() {
        switch (transitionStep) {
            case 0:
                if (timer.millisElapsed() > 2000) {
                    transitionStep = 1;
                    timer.mark();
                }
                break;
            case 1:
                // Display the original hospital image for 5 seconds
                if (timer.millisElapsed() > 2000) {
                    transitionStep = 2;
                }
                break;
            case 2:
                // Scale the image up until it reaches the width of the world
                getImage().scale(getImage().getWidth() + 10, getImage().getHeight() + 10);
                if (getImage().getWidth() >= getWorld().getWidth()) {
                    transitionStep = 3;
                }
                break;
            case 3:
                // Set the title screen background to pink
                getImage().setColor(Color.PINK);
                getImage().fill();
                transitionStep = 4;
                break;
            case 4:

                break;
        }
    }
}
