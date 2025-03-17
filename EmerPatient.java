import greenfoot.*;
import java.util.ArrayList;

/**
 * This emergency patient class are for patients that need to go to the 
 * emergency room instead of a checkin room
 * 
 * @author Sana, vincent
 * @version 1.2 (11/16/2023)
 */
public class EmerPatient extends Patient
{
    GreenfootImage emerPatient = new GreenfootImage("EmerPatient_test (1).png");
    GreenfootImage emerPatientER = new GreenfootImage("aliveEmerPatient.png");
    GreenfootImage angelImage = new GreenfootImage("angel.png");
    GreenfootImage deadPatientImage = new GreenfootImage("deadPatient.png");
    private int speed;
    private boolean inRoom;
    private boolean gotChecked;
    
    private boolean moving;
    protected ArrayList<Coordinate> destinations;
    private int actCount, redrawFrequency;
    
    protected ArrayList<ER> ers;
    private double distanceToPatient;
    
    protected boolean marked3; // Harithan's code
    SimpleTimer recoveryTimer = new SimpleTimer();
    
    private boolean isDead;
    private int successRate;
    
    /**
     * Constructor of the 
     */
    public EmerPatient(int userSuccessRate) {
        super();
        actCount = 0;
        moving = true;
        setImage(emerPatient);
        speed = 3;
        gotChecked = false;
        inRoom = false;
        isDead = false;
        oneTime = false;
        oneTime2 = false;
        
        successRate = userSuccessRate;
        
        destinations = new ArrayList<Coordinate>();
        
        // Coordinates for emergency patient to reach the ER
        destinations.add(new Coordinate(679, 625));
        destinations.add(new Coordinate(679, 284));
        destinations.add(new Coordinate(380, 284));
        destinations.add(new Coordinate(362, 125));
        enableStaticRotation(); // Does not flip the image upside down
        
        marked3 = false;
        
        if(destinations.isEmpty()) {
            getWorld().removeObject(this);
        }
    }
    
    public EmerPatient(boolean deadIndicator) {
        if(deadIndicator) {
            actCount = 0;
            moving = true;
            setImage(deadPatientImage);
            speed = 3;
            gotChecked = true;
            inRoom = true;
            isDead = true;
            oneTime = true;
            oneTime2 = true;
            
            destinations = new ArrayList<Coordinate>();
            
            leave();
            
            enableStaticRotation(); // Does not flip the image upside down
            
            marked3 = true;
        }
    }
    private int msElapsedtest;
    public void act() {
        
        detectER();
        if(moving) {
            if (destinations.size() > 0){
                moveTowardsDestination();
            } else if(destinations.isEmpty() && !inRoom && !marked3) {
                inRoom = true; // Makes sure this code is run one time
                goToER(); // Patient goes to the er
            } else if(destinations.isEmpty() && inRoom && !marked3) {
                marked3 = true;
                moving = false;
            } else {
                getWorld().removeObject(this);
            }
            
            
        }
        msElapsedtest = recoveryTimer.millisElapsed();
        if(marked3){ 
            int msElapsed = recoveryTimer.millisElapsed();
            
            if(msElapsed >= 2000) {
                liveOrDie();
            }
        }
    }

    /**
     * 
     */
    public void moveTowardsDestination() {
        // Use method to figure out the exact distance between self and destination
        double distanceToDestination = getDistance(new Coordinate(getX(), getY()), destinations.get(0));
    
        // If I'm so close to my destination that I'm about to overshoot it, set my
        // location to the exact destination location instead of using calculated movement
        if (distanceToDestination < speed){
            setLocation(destinations.get(0).getX(), destinations.get(0).getY());
            destinations.remove(0);
        } else { // as long as I'm not close
            // Turn towards and move towards my destination
            turnTowards(destinations.get(0).getX(), destinations.get(0).getY());
            move(speed);
        
        }
    }
    
    /**
     * Static method that gets the distance between the x,y coordinates of two Actors
     * using Pythagorean Theorum.
     *
     * @param a     First Actor
     * @param b     Second Actor
     * @return distance The distance from the center of a to the center of b.
     * 
     * @author Mr. Cohen
     */
    public static double getDistance (Coordinate a, Coordinate b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return distance;
    }
    
    /**
     * This method makes the emergency patient go to the any empty er, 
     * code is borrowed from the Bug Tracking Simulation by Mr. Cohen
     * 
     * @author Mr. Cohen, vincent
     */
    public void goToER() {
        double closestTargetDistance = 0;
        ers = (ArrayList<ER>)getWorld().getObjects(ER.class);
        ER closestER = ers.get(0);
        
        for(ER b : ers) {
            //distanceToPatient = MyWorld.getDistance(this, c);
            if(b.isEmptyStatus()) {
                closestER = b;
                closestTargetDistance = distanceToPatient;
                
            }
            
        }/*
        if(closestER.isEmptyStatus()) {*/
            destinations.add(new Coordinate(closestER.getX(), closestER.getY()));
            getImage().setTransparency(0);/*
        } else {
            moving = false;
        }
        */
    }
    
    
    private boolean oneTime2;
    /**
     * This method randomly decides the emergency patient lives or dies
     * 
     * @author vincent
     */
    public void liveOrDie() {
        if(!oneTime2) {
            if(Greenfoot.getRandomNumber(100) >= successRate) { // Success rate of being alive or dead
                // Add code to affect the reputation and money
                leaveAfterHealed();
            } else {
                // Add code to affect the reputation and money
                leave();
                getImage().setTransparency(255);
                setImage(angelImage);
                affectSpeed(-1);
                moving = true;
                
                EmerPatient deadCorpse = new EmerPatient(true);
                getWorld().addObject(deadCorpse, 374, 125);
                deadCorpse.affectSpeed(-1);
                
                isDead = true;
            }
            oneTime2 = true;
        }
    }
    
    public void affectSpeed(double speedChange) {
        speed += speedChange;
    }
    
    public boolean getDeadStatus() {
        return isDead;
    }
    
    private boolean oneTime;
    /**
     * Method is used to detect if an emergency patient is on a er. Borrowed 
     * from Harithan's code in the Patient class. Reworked for the er class 
     * instead of the chair class. However, basic functionality stays the 
     * same.
     * 
     * @author Harithan, vincent
     */
    public void detectER() {
        ER erDetection = (ER)getOneIntersectingObject(ER.class);
        if(erDetection != null && !oneTime) {
            recoveryTimer.mark();
            oneTime = true;
        }
    }
    
    public void pay() {
        
    }
    //Two sets of coordinates that allows a patient to leave.
    public void leave() {
        destinations.add(new Coordinate(362, 125));
        destinations.add(new Coordinate(380, 284));
        destinations.add(new Coordinate(679, 284));
        destinations.add(new Coordinate(679, 625));
        destinations.add(new Coordinate(0, 625));
    }
    //This is used to get the patient to leave AFTER
    //getting healed completely
    //After they leave, the money & reputation of the clinic will increase
    public void leaveAfterHealed() {
        leave();
        moving = true;
        getImage().setTransparency(255);
        setImage(emerPatientER); // This is a PLACEHOLDER image
    }
    
    public void followDoc() {
        
    }
    
    public void getsCalled() {
        
    }
}
