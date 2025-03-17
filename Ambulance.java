import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An ambulance that is driving on the road. Upon coming across any people on the street, the drivers will come out to pick them up.
 * <p> Made By: Harithan Raveendran
 * <p> Last Modified: Nov 10, 2023
 */
public class Ambulance extends SuperSmoothMover
{
    private SimpleTimer timer = new SimpleTimer();
    // A double that effects the speed of the ambulance.
    private double speed;
    // An integer that is used in a timer to spawn the ambulance's driver and passenger into the world.
    private int actsLeft;
    // Two booleans that used individually for the driver and the pasenger.
    // An object of pedestrian that allows the ambulance to interact with pedestrians on the road.
    private Pedestrian p;
    // The objects of the driver and pedestrians that allow them to be spawned into the world.
    public Driver d;
    public Driver2 d2;
    
    private GreenfootSound siren = new GreenfootSound("siren.mp3");
    public boolean scenarioDone = false;
    
    /**
     * The constructor that sets the values of the "speed", "spawned" and "spawned2" variables.
     */
    public Ambulance(){
        timer.mark();
        siren.setVolume(100);
        speed = 5;
    }

    /**
     * The method that keeps allows all the ambulances interactions and other code to actually work.
     */
    public void act()
    {
        //First two lines of code are took from the other two methods in this class.
        drive();
        //siren.play();
        hit();
        //If the ambulance stops, start a timer that goes up to two seconds
        if(speed == 0){
            actsLeft++;
            //When two seconds pass, spawn the driver.
            if(actsLeft == 120){
                d = new Driver();
                getWorld().addObject(d, getX(), getY() + 10);   
                //siren.stop();
            }
            //When 20 seconds pass, spawn the passenger.
            if(actsLeft == 140){
                d2 = new Driver2();
                getWorld().addObject(d2, getX(), getY() + 25);
                //siren.stop();
            }
        }
        scenarioDone = true;
        
        if(timer.millisElapsed() > 32000)
        {
            this.move(-10);
            d.move(-10);
            d2.move(-10);
            if(this.getX() == 0 || d.getX() == 0 || d2.getX() == 0)
            {
                move(0);
            }
        }
    }
    
    public void stopped()
    {
        //siren.stop();
    }
    
    /**
     * The method that allows the ambulance to move via repeatedly changing its x position.
     */
    public void drive(){
        setLocation(getX() + speed, getY());
        //siren.play();
    }

    /**
     * The method that allows the ambulance to detect when it is touching a pedestrian.
     */
    public void hit(){
        //Sets an offset point on the ambulance, allowing to find out whether or not it is in contact of a pedestrian.
        p = (Pedestrian)getOneObjectAtOffset(getImage().getWidth()/2, 0, Pedestrian.class);
        if(p != null){
            //Stops the ambulance from moving upon touching a pedestrian.
            //siren.stop();
            speed = 0;
        }
    }
}
