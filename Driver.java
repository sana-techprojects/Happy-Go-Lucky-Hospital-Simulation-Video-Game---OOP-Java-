import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A driver of an ambulance which picks up any unconscious pedestrians.
 * <p> Made By: Harithan Raveendran
 * <p> Modified by Sana Pardiwala
 * <p> Last Modified: Nov 10, 2023
 */
public class Driver extends SuperSmoothMover
{
    // A double that affects the speed of which the driver moves at.
    private double speed;
    // A boolean used to trigger the "turnRight()" method.
    private boolean turn;
    // A integer that provides a goal for the timer to reach when it is set.
    private static final int TURN_DURATION = 2000;
    // The four objects of "GreenfootImage" that allows the driver to visually change their direction.
    private GreenfootImage sRightImage;
    private GreenfootImage leftImage;
    private GreenfootImage sLeftImage;
    private GreenfootImage sUpImage;
    // An object of the "SimpleTimer" class.
    private SimpleTimer turnTimer;
    // Objects of the "Pedestrian" and "SidewalkB" classes that allow the driver's special interactions to trigger.
    Pedestrian p;
    SidewalkB s;
    
    private SimpleTimer timer = new SimpleTimer();
    /**
     * A constructor that sets the values of all the GreenfootImages, SimpleTimer, turn boolean and speed double.
     */
    public Driver(){
        
        speed = 1;
        turnTimer = new SimpleTimer();
        leftImage = new GreenfootImage("Driver2.png");
        sLeftImage = new GreenfootImage("Driver2S.png");
        sRightImage = new GreenfootImage("Driver3S.png"); 
        sUpImage = new GreenfootImage("Driver1.png");
        turn = false;
    }
    /**
     * An act method that activates the driver's special interactions.
     */
    public void act(){
        //If the boolean "turn" is false, activate the moveRight() method.
        if(!turn){
            moveRight();
        }
        //If the boolean "turn" is true, follow the series of steps for the next interaction.
        else{
            //An integer that keeps track of the timer's current time.
            int ms = turnTimer.millisElapsed();
            //If integer "ms" is greater than or equal to the integer "TURN_DURATION", make the driver face a pedestrian in its way.
            if(ms >= TURN_DURATION){
                setImage(sUpImage);
                /* If integer "ms" is greater than or equal to 4 seconds, "pick up" a pedestrian, make it face the ambulance, and move
                * backwards.
                */
                if(ms >= TURN_DURATION + 2000){
                    getWorld().removeObject(p);
                    setImage(leftImage);
                    setLocation(getX(), 570);
                    moveRight();
                }
            }
        }
        
    }
    /**
     * The method that contains all the code for the unique interaction the driver has with a pedestrian.
     */
    public void moveRight(){
        // Allows the driver to move to the right.
        setLocation(getX() + speed, getY());
        // Sets an offset point to allow the driver to know when it is in contact of a pedestrian.
        p = (Pedestrian)getOneObjectAtOffset(0, 0, Pedestrian.class);
        // The if statement that triggers the code within it when the driver is touching the pedestrian.
        if(p != null){
            //Stops the driver from moving, and puts the pedestrian in its appropriate standing position.
            speed = 0;
            setImage(sRightImage);
            //Starts the timer if the turn boolean is false.
            if(!turn){
                turnTimer.mark();
            }
            //Sets the turn boolean to true so that the timer will never reset.
            turn = true;
        }
        else{
            //Allows the driver to move again once the pedestrian is "picked up", 
            speed = 1;
        }
        //Stops the pedestrian and puts it in its appropriate standing position when it comes into contact with "SidewalkB."
        if(isTouching(SidewalkB.class)){
            speed = 0;
            setImage(sLeftImage);
        }
    }

}
