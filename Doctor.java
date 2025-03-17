import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Doctor here.
 * 
 * @author Harithan
 * @version 2.8 (11/17/2023)
 */
public class Doctor extends MedStaff
{
    private ArrayList<Coordinate> destinations;
    private ArrayList<HealthyPatient> hPatients;
    private int actCount, redrawFrequency, first;
    private Coordinate currentDestination;
    private double speed;
    private boolean patReady, moving, bringingPat, added, goBackIn, marked;
    private Patient p;
    private HealthyPatient h;
    private HealthyPatient hPoint, hPoint2;
    private SimpleTimer goTimer;
    public Doctor(){
        actCount = 0;
        first = 0;
        destinations = new ArrayList<Coordinate>();
        goTimer = new SimpleTimer();
        speed = 2;
        redrawFrequency = 4;
        patReady = false;
        moving = false;
        bringingPat = false;
        goBackIn = false;
        marked = false;
        added = true;
        //this.first = first;
    }

    /**
     * Act - do whatever the Doctor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actCount++;
        if(destinations.size() > 0){
            moveTowardsDestination();
        }
        if(marked){
            int msElapsed = goTimer.millisElapsed();
            if(msElapsed >= 2000){
                addDestination(new Coordinate(getX() + 10, 387));
                marked = false;
            }
        }
        bringPat();
    }

    private void moveTowardsDestination(){
        double distanceToDestination = getDistance(new Coordinate(getX(), getY()), destinations.get(0));
        if (distanceToDestination < speed){
            setLocation(destinations.get(0).getX(), destinations.get(0).getY());
            destinations.remove(0);
        }
        else {
            turnTowards(destinations.get(0).getX(), destinations.get(0).getY());
            move(speed);
        }
    }

    private static double getDistance(Coordinate a, Coordinate b){
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return distance;
    }

    private void addDestination(Coordinate c){
        destinations.add(c);
    }

    public void setPat(boolean patReady){
        this.patReady = patReady;
    }

    public boolean getPat(){
        return patReady;
    }

    public void setBringPat(boolean bringingPat){
        this.bringingPat = bringingPat;
    }

    public void setBringingPat(boolean bringingPat){
        this.bringingPat = bringingPat;
    }

    public void setMove(boolean moving){
        this.moving = moving;
    }

    public void addFirst(){
        first++;
    }

    /*public void goToPat(){
    destinations.add(new Coordinate(getX() - 25, 298));
    destinations.add(new Coordinate(520, 298));
    }*/

    public void bringPat(){
        /*hPatients = (ArrayList<HealthyPatient>)getWorld().getObjects(HealthyPatient.class);
        p = (Patient)getOneObjectAtOffset(getImage().getWidth()/2, 0, Patient.class);
        //hPatients = (ArrayList<HealthyPatient>)getWorld().getObjects(HealthyPatient.class);
        if(p != null && p.getCheck() == true){
        if(!bringPat){
        speed = 2;
        destinations.add(new Coordinate(ogX - 25, getY()));
        destinations.add(new Coordinate(ogX, ogY));
        bringPat = true;
        }
        //p.followDoc();
        /*else{
        speed = 2;
        patReady = false;
        }
        }*/
        int xCoordinate = 100;
        hPatients = (ArrayList<HealthyPatient>)getWorld().getObjects(HealthyPatient.class);
        hPoint = (HealthyPatient)getOneObjectAtOffset(15, 43, HealthyPatient.class); 
        if(hPatients.size() > first){
            h = hPatients.get(first); 
            for(HealthyPatient hPat : hPatients){
                if(h.getCheck() == true){
                    if(hPoint == null && h.getY() != 430 && patReady && bringingPat){
                        h.removePatienceBar(); 
                        xCoordinate = h.getX(); 
                        h.addDestination(new Coordinate(xCoordinate, 305));
                        h.addDestination(new Coordinate(getX() - 15, 305)); 
                        h.addDestination(new Coordinate(getX() - 15, 430));
                        h.addDestination(new Coordinate(getX() + 15, 430));
                        addDestination(new Coordinate(getX() - 10, 290));
                        if(!marked){
                            goTimer.mark();
                            marked = true;
                        }
                        h.timeMark();  
                    }
                    if(hPatients.size() - 1 > first){
                        first++; 
                        h = hPatients.get(first);
                        xCoordinate += 145;
                        if(xCoordinate > 390){ 
                            xCoordinate = 100;
                        }
                        //h.addDestination(new Coordinate(xCoordinate, 305)); 
                    }
                    if(h.getX() > 417 && h.getLeave() == true){
                        patReady = false; 
                    }
                    /*destinations.add(new Coordinate(getX() - 15, 290));
                    destinations.add(new Coordinate(455, 290));*/
                    //destinations.add
                    //h.checked(true);
                }
                /*if(hPatients.indexOf(hPat) != first){
                hPat.resetTimer();
                }*/
                /*if(added && patReady){
                first++;
                h = hPatients.get(first);
                added = false;
                }*/
            }
        }
    }

}
