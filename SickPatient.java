import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class SIckPatient here.
 * 
 * @Harithan, Rebecca
 * @version (a version number or a date)
 */
public class SickPatient extends Patient
{
    private int speed;
    private int currentIndex;
    private boolean isInBed;
    //created an arraylist to store the coordinates of the pathing specifically created for the EmerPatient
    private int[] targetX = {600, 392, 360};
    private int[] targetY = {303, 303, 145};
    
    private ArrayList<Bed> beds;
    
    private GreenfootImage patientImage1 = new GreenfootImage("P-4.png");
    private GreenfootImage patientImage2 = new GreenfootImage("P-5.png");
    
    /**
     * Act - do whatever the SIckPatient wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SickPatient()
    {
        speed = 2;
        currentIndex = 0;
        isInBed = false;
        destinations.add(new Coordinate(720, 630));
        destinations.add(new Coordinate(720, 110));
        
        if(Greenfoot.getRandomNumber(2) == 0) {
            setImage(patientImage1);
        } else {
            setImage(patientImage2);
        }
    }
    
    public void act()
    {
        super.act();
        if(marked2)
        {
            int msElapsed = waitTimer.millisElapsed();
            if(msElapsed >= 5000){
                getWorld().removeObject(he);
                getsCalled();
                if(getX() == 360 && getY() == 145)
                {
                    goToBed();
                }
            }
        }
    }
    
    /**
     * This method allows for sick patient to go to the 
     * treatment room
     */
    public void getsCalled()
    {
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
        }
    }
    
    /**
     * This method allows for sick patients to go to the closest bed
     * and lay on top of it
     */
    public void goToBed()
    {
        double closestTargetDistance = 0;
        p2 = (Patient)getOneObjectAtOffset(0, getImage().getHeight(), Patient.class);
        beds = (ArrayList<Bed>)getWorld().getObjects(Bed.class);
        Bed closestBed = beds.get(0);
        for(Bed b : beds) {
            //distanceToPatient = MyWorld.getDistance(this, c);
            
            if(b.isEmptyStatus()) {
                closestBed = b;
                closestTargetDistance = distanceToPatient;
            }
            
        }
        if(closestBed.isEmptyStatus()) {
            if(closestBed.getX() > getX()){
                destinations.add(new Coordinate(getX() + 75, getY()));  
            }
            else {
                destinations.add(new Coordinate(getX() - 75, getY()));
            }
            destinations.add(new Coordinate(closestBed.getX(), closestBed.getY() - 20));
        }
        
        isInBed = true;
    }
    
    public void leave()
    {
        destinations.add(new Coordinate(650, getY()));
        destinations.add(new Coordinate(650, 620));
    }
    
    public void leaveAfterHealed()
    {
        destinations.add(new Coordinate(getX(), 145));
        destinations.add(new Coordinate(360, 145));
        destinations.add(new Coordinate(360, 300));
        destinations.add(new Coordinate(650, 300));
        destinations.add(new Coordinate(650, 620));
        destinations.add(new Coordinate(0, 620));
    }
    
    public void followDoc() {
        
    }
    
    public void pay()
    {
        
    }
}
