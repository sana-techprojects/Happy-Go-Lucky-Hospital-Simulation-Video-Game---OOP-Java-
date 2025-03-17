import greenfoot.*;
import java.util.ArrayList;

/**
 * Write a description of class Patient here.
 * 
 * @authors Sana, vincent, harithan
 * @version 2.0 (11/14/2023)
 */
public abstract class Patient extends Person
{
    protected boolean added, isCheckedIn, isSick, moving, marked, marked2, marked3, ignore, checkedUp, gotChecked, emergencyP, healthyP;
    protected boolean repAdded = false;
    protected double patience, distanceToPatient;
    protected int startingPatience, reputation, currentIndex, actCount, redrawFrequency;
    protected ArrayList<Coordinate> destinations;
    public boolean left = false;
    // Timer to pause the patient temporarily
    SimpleTimer pauseTimer = new SimpleTimer();
    SimpleTimer waitTimer = new SimpleTimer();
    SimpleTimer patTimer = new SimpleTimer();
    // ArrayList to get all the chairs in the world
    protected ArrayList<Chair> chairs;
    GreenfootImage emerPatient = new GreenfootImage("EmerPatient_test (1).png");
    //created an arraylist to store the coordinates of the pathing specifically created for the EmerPatient
    protected int[] targetX = {717, 717, 392, 360, 360};
    protected int[] targetY = {625, 303, 303, 303, 145};
    protected Patient p, p2;
    protected Receptionist r;
    protected Chair chair, chair2;
    protected HealthBar he;
    protected ArrayList<VerticalHealthBar> v;
    protected VerticalHealthBar vb;
    protected Doctor da, d, d2, d3;

    VerticalHealthBar rep = new VerticalHealthBar();
    HealthBar patienceBar;

    public static int numPatientsLeft = 0;

    /**
     * Constructor for a Patient
     */
    public Patient () {
        marked = false;
        marked2 = false;
        marked3 = false;
        isCheckedIn = false;
        moving = true; // Patients will always start by moving to the recepionist's desk
        ignore = false;
        added = false;

        actCount = 0;
        redrawFrequency = 8;
        destinations = new ArrayList<Coordinate>();
        speed = 3.2;

        enableStaticRotation();

        // Automatically set initial destination

        he = new HealthBar();
        rep = new VerticalHealthBar();

    }

    public void act()
    {

        //if (rep != null && repAdded && he.getH() <= 3) {
        //    rep.decrease();
        //}

        // Act count - so we can only redraw on certain acts rather than every act
        //Adds an health bar to the world to accompany a patient. 

        /*
         * NOTE: Does not need an if method to prevent emergency patient from
         * inheriting act() method, just don't write super.act() in the 
         * emerPatient class.
         */
        if(!emergencyP){
            if(!added){
                getWorld().addObject(he, this.getX(), this.getY() + 20);
                //This boolean and if statement prevents patients from having more than 1 health bar.
                added = true;
            }
            //Constantly sets the location of the health bar so that it is following its patient constantly.
            he.setLocation(getX(), getY() + 20);
            actCount++;
            //if(!isCheckedIn && destinations.size() > 0){
            lineUp();
            //   
            if(moving) {
                // If I don't have a destination, and there are destinations
                // in my list, set my current destination to the next one in my list
                if (destinations.size() > 0){
                    moveTowardsDestination();
                } else if (destinations.isEmpty() && !isCheckedIn) {
                    moving = false;
                }

                // Call the redrawPath method in World and pass it my current coordinate and list
                // of destinations
                if (actCount % redrawFrequency == 0){ // only once every redrawFreqency acts (ie. 6 or 8
                    // acts in between each redraw, to reduce CPU use)
                    ((MainSim)getWorld()).redrawPath (new Coordinate (getX(), getY()), destinations);
                }
            }
            if(marked && !isCheckedIn){ 
                int msElapsed = pauseTimer.millisElapsed();
                if(msElapsed >= 2000 && detectEmptyChair()) {
                    goToChair();
                    moving = true;
                }
            }
            if(marked2)
            {
                int msElapsed = waitTimer.millisElapsed();
                if(msElapsed >= 3000){
                    if(!healthyP){
                        getsCalled();
                    }
                    else{
                        followDoc();
                        checkedUp = true; 
                    }
                }
            }
            //A timer that decreases the width of the health bar affiliated with each patient.
            if(marked3){
                int msElapsed3 = patTimer.millisElapsed();
                if(msElapsed3 % 100 == 0){
                    he.decrease();
                    numPatientsLeft++;
                }
                /*If the width of the health bar reaches 3 or lower, then remove it from the world and set coordiantes to allow the patient
                 * to leave.
                 */
                if(getY() == 620){
                    setLocation(getX() + speed * -1, getY());
                }
            }
        }
        else{
            moveTowardsDestination();
        }
        /*
         * If a patient's y position is 620, they will move to the left forever.  
         * Used in conjunction with the "leave()" method.
         */
        if(he.getH() <= 3 && !ignore){
            ignore = true;
            getWorld().removeObject(he);
            if(destinations.size() > 0){
                destinations.remove(0);
            }
            destinations.add(new Coordinate(690, getY()));
            destinations.add(new Coordinate(690, 620));
            leave();
            //testing

            left = true;
            //System.out.println("patient leaving = " + left);
            /*destinations.add(new Coordinate(660, 300));
            destinations.add(new Coordinate(660, 620));*/
        }
    }

    //public void addedToWorld(World world) {
    // This method is called automatically when the actor is added to the world.
    // Now, we can add the health bar to the world.
    //if (!repAdded) {
    //getWorld().addObject(rep, 1045, 130);
    //repAdded = true;
    //}
    //super.addedToWorld(world);
    //}

    /**
     * This method allows patients to move to whatever
     * destination is assigned via an ArrayList
     * 
     * Borrowed from Mr. Cohen's code
     */
    public void moveTowardsDestination(){
        // Use method to figure out the exact distance between self and destination
        if(emergencyP){
            if (currentIndex < targetX.length) {
                int targetXCoord = targetX[currentIndex];
                int targetYCoord = targetY[currentIndex];

                // Calculate distances along X and Y axes
                int dx = targetXCoord - getX();
                int dy = targetYCoord - getY();

                // Calculate distance to the current destination
                //Formula found through Greenfoot Help Forum
                double distanceToDestination = Math.sqrt(dx * dx + dy * dy);

                // If close enough to the current destination, move to the next one
                if (distanceToDestination < speed) {
                    setLocation(targetXCoord, targetYCoord);
                    currentIndex++;
                } else {
                    // Turn towards and move towards the current destination
                    turnTowards(targetXCoord, targetYCoord);
                    move(speed);
                }
            } else {
                // When the Emergency Patient has reached the last point, it will get removed from the World
                getWorld().removeObject(this);
            }
        }
        else{
            double distanceToDestination = getDistance(new Coordinate(getX(), getY()), destinations.get(0));

            // If I'm so close to my destination that I'm about to overshoot it, set my
            // location to the exact destination location instead of using calculated movement
            if (distanceToDestination < speed){
                setLocation(destinations.get(0).getX(), destinations.get(0).getY());
                destinations.remove(0);
                /*
                // Automatically set a new destination (for example, move to (700, 630))
                if (!destinations.isEmpty()) {
                destinations.add(new Coordinate(790, 630));
                }
                 */
            } else { // as long as I'm not close
                // Turn towards and move towards my destination
                turnTowards(destinations.get(0).getX(), destinations.get(0).getY());
                move(speed);

            }

        }
    }

    /**
     * Method that will constantly run once the patient is at the reptionist's 
     * desk. Once an empty seat will be avaliable, it will return true. Otherwise,
     * it will return false.
     * 
     * @author vincent
     */
    public boolean detectEmptyChair() {
        chairs = (ArrayList<Chair>)getWorld().getObjects(Chair.class);
        for(Chair c : chairs) {
            if(c.isEmptyStatus()) {
                isCheckedIn = true;
                return true;
            }
        }
        return false;
    }

    /** Note: Add to the Master (11/14/2023)
     * 
     * This code will make a patient move to any empty chair      * using 
     * using an ArrayList. The code is based on Mr. Cohen's
     * bug tracking code in the bug simulation world.
     * 
     * @author vincent, Mr. Cohen
     * 
     */
    public void goToChair() {
        double closestTargetDistance = 0;
        p2 = (Patient)getOneObjectAtOffset(0, getImage().getHeight(), Patient.class);
        chairs = (ArrayList<Chair>)getWorld().getObjects(Chair.class);
        Chair closestChair = chairs.get(0);

        for(Chair c : chairs) {
            //distanceToPatient = MyWorld.getDistance(this, c);

            if(c.isEmptyStatus()) {
                closestChair = c;
                closestTargetDistance = distanceToPatient;
            }

        }
        if(closestChair.isEmptyStatus()) {
            if(closestChair.getX() > getX()){
                destinations.add(new Coordinate(getX() + 75, getY()));
            }
            else {
                destinations.add(new Coordinate(getX() - 75, getY()));
            }
            destinations.add(new Coordinate(closestChair.getX(), closestChair.getY()));
        }
    }

    /**
     * Static method that gets the distance between the x,y coordinates of two Actors
     * using Pythagorean Theorum.
     *
     * @param a     First Actor
     * @param b     Second Actor
     * @return distance The distance from the center of a to the center of b.
     */
    public static double getDistance (Coordinate a, Coordinate b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return distance;
    }

    public void addDestination (Coordinate c){
        destinations.add(c);
    }

    public void lineUp(){
        p = (Patient)getOneObjectAtOffset(0, getImage().getHeight() * -1, Patient.class);
        if(p == null) {
            p = (Patient)getOneObjectAtOffset(getImage().getWidth() + 15, 0, Patient.class);
        }

        r = (Receptionist)getOneObjectAtOffset(0, getImage().getHeight() * -2, Receptionist.class);
        chair = (Chair)getOneObjectAtOffset(0, 0, Chair.class);
        if(p != null && !marked){
            speed = 0;
            //Sets a timer that allows the patient's health bar to decrease.
            if(!marked3){
                patTimer.mark();
                marked3 = true;
            }
        }
        else{
            speed = 3.2;
        }
        if(r != null){
            if(!moving){
                speed = 0; 
                //Sets a timer that allows the patient's health bar to decrease.
                /*if(!marked3){
                patTimer.mark();
                marked3 = true;
                }*/
            }
            if(!marked){
                pauseTimer.mark();  
                marked = true;
            }
        }
        if(chair != null && !marked2){
            waitTimer.mark();
            marked2 = true;
            if(!marked3){
                patTimer.mark();
                marked3 = true;
            }
        }
        if(getY() == 620){
            setLocation(getX() + speed * -1, getY());
        }
    }

    //Two sets of coordinates that allows a patient to leave.
    public abstract void leave();

    public boolean getCheck(){
        return checkedUp;
    }

    public abstract void getsCalled();

    public abstract void followDoc();
}