import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A fast car that runs over nearby pedestrians.
 * <p> Made By: Harithan Raveendran
 * <p> Modified By: Sana Pardiwala
 * <p> Last Modified: Nov 10, 2023
 */
public class Car extends SuperSmoothMover
{
    //Double that affects the speed of thec ar.
    private double speed;
    //An object of pedestrian that is used for the "hit()" method.
    private Pedestrian p;
    
    //collision sound
    private GreenfootSound collision = new GreenfootSound("CarPedestrianCollision (2).mp3");
    GreenfootSound sadSound = new GreenfootSound("vine-boom.mp3");
    GreenfootSound sadMusic = new GreenfootSound("sadMusic (1).mp3");
    /**
     * A constructor that sets the speed of the car.
     */
    public Car(){
        speed = 15;
    }
    
    /**
     * The method that allows all the car's other ones to activate.
     */
    public void act()
    {
        drive();
        hit();
        remove();
    }
    
    /**
     * Allows the car to detect when it is in contact of a pedestrian.
     */
    public void hit(){
        //The offset point that allows the car to detect 
        p = (Pedestrian)getOneObjectAtOffset(0, 0, Pedestrian.class);
        //If the car is in contact of a pedestrian, the latter will be knocked down.
        if(p != null){
            p.knockDown();
            sadSound.play();
            sadMusic.play();
        }
        
    }
    
    
    
    /**
     * Allows the car to move via setting its location and constantly changing its x coordinate.
     */
    public void drive(){
        getWorld().stopped();
        collision.play();
        setLocation(getX() + speed, getY());
    }
    
    /**
     * Removes the car once its x coordinate is greater than or equal to 1 100.
     */
    public void remove(){
        
        if(getX() >= 1300){
            getWorld().removeObject(this);
        }
    }
}
