import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class HealthyPatient here.
 * 
 * @Harithan, Rebecca
 * @version (a version number or a date)
 */
public class HealthyPatient extends Patient
{
    private boolean marked4, ignore, occupied, addedFirst, leaving;
    private int first, available, comTimePassed;
    private Chair chair, chair2;
    private HealthyPatient h;
    private Patient p;
    private ArrayList<Doctor> doctors;
    private Doctor d;
    private Doctor d2;
    private Doctor d3;
    private Doctor da;
    private ArrayList<HealthyPatient> hPatients;
    private SimpleTimer comTimer, resetTimer;
    
    private GreenfootImage patientImage1 = new GreenfootImage("P-1.png");
    private GreenfootImage patientImage2 = new GreenfootImage("P-2.png");
    
    /**
     * Act - do whatever the HealthyPatient wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public HealthyPatient(){
        super ();
        emergencyP = false;
        healthyP = true;
        comTimer = new SimpleTimer();
        resetTimer = new SimpleTimer();
        marked2 = false;
        marked4 = false;
        checkedUp = false;
        occupied = false;
        leaving = false;
        first = 0;
        available = 0;
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
        if(marked4){
            int msElapsed = comTimer.millisElapsed();
            if(msElapsed >= 15000 && !leaving){
                destinations.add(new Coordinate(getX() - 15, getY()));
                destinations.add(new Coordinate(getX() - 15, getY() - 90));
                destinations.add(new Coordinate(690, getY() - 90));
                destinations.add(new Coordinate(690, 620));
                leaving = true; 
                da.setPat(false);
                /*da.setPat(true);
                da.setBringPat(false);
                da.setMove(false);*/
                /*if(checkedUp){
                da.addFirst();
                }*/
                //checkedUp = false;
            }
            //checkedUp = false;
        }
    }

    public void followDoc(){
        /*
        doctors = (ArrayList<Doctor>)getWorld().getObjects(Doctor.class);
        d = doctors.get(0);
        d2 = doctors.get(1);
        d3 = doctors.get(2);
        da = doctors.get(available);
        for(Doctor doc : doctors){
        if(d.getPat() == false && d.getX() == 540){
        d.setPat(true);
        available = 0;
        da = doctors.get(available);
        }

        else if(d.getPat() == true && d2.getPat() == false && d2.getX() == 540){
        d2.setPat(true);
        available = 1;
        da = doctors.get(available);
        }
        else if(d.getPat() == true && d2.getPat() == true && d3.getPat() == false){
        d3.setPat(true);
        available = 2;
        da = doctors.get(available);
        }
        if(da.getX() == 520 && checkedUp){
        getWorld().removeObject(he);
        destinations.add(new Coordinate(getX() + 50, getY()));
        destinations.add(new Coordinate(da.getX() + 20, da.getY()));
        destinations.add(new Coordinate(da.getOgX(), da.getY()));
        destinations.add(new Coordinate(da.getOgX() - 30, da.getOgY() + 105));
        marked2 = false;
        //available++;
        }
        }
        if(available > 2){
        available = 0;
        }
        timeMark();
         */
        doctors = (ArrayList<Doctor>)getWorld().getObjects(Doctor.class);
        d = doctors.get(0);
        d2 = doctors.get(1);
        d3 = doctors.get(2);
        da = doctors.get(available);
        for(Doctor doc : doctors){
            if(d.getPat() == false && !occupied){
                d.setPat(true); 
                d.setBringingPat(true);
                d2.setBringingPat(false);
                d3.setBringingPat(false); 
                available = 0;
                occupied = true; 
            }
            else if(d2.getPat() == false && !occupied){
                d2.setPat(true);
                d.setBringingPat(false);
                d2.setBringingPat(true);
                d3.setBringingPat(false);
                available = 1;
                occupied = true;
            }
            else if(d3.getPat() == true && !occupied){
                d3.setPat(true);
                d.setBringingPat(false);
                d2.setBringingPat(false);
                d3.setBringingPat(true);
                available = 2;
                occupied = true;
                resetTimer.mark();
            }
        }
    }
    
    public void leave() {
        destinations.add(new Coordinate(650, getY()));
        destinations.add(new Coordinate(650, 620));
        destinations.add(new Coordinate(0, 620));
    }
    
    public void timeMark(){
        //if(getY() == 430){
        comTimer.mark();
        marked4 = true;
        //}
    }

    public void checked(boolean checkedUp){
        this.checkedUp = checkedUp;
    }

    public boolean getChairTime(){
        return marked2;
    }

    public void setIgnore(boolean ignore){
        this.ignore = ignore;
    }

    public void resetTimer(){
        waitTimer.mark();
    }

    public boolean getLeave(){
        return leaving;
    }

    public void removePatienceBar(){
        getWorld().removeObject(he);
    }
    
    public void getsCalled(){
        
    }
}
