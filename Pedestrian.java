import greenfoot.*; 
/**
 * The world of which the intro simulation takes place in.
 * <p> Made By: Harithan Raveendran
 * <p> Modified by Sana Pardiwala
 * <p> Borrowed code from: Danpost
 * <p> Last Modified: Nov 10, 2023
 */ 
public class Pedestrian extends SuperSmoothMover
{ 
    private GreenfootImage leftImage;
    private GreenfootImage sLeftImage;
    private GreenfootImage sDownImage;
    private GreenfootImage downImage;
    private GreenfootImage deathImage;
    private boolean leftTrue, downTrue, turnTrue, timeStart, turn, carIn, dead;
    private double speed;
    private int turnActsLeft, ambActsLeft;
    SidewalkB s;
    Road1B r;
    Car c;
    Ambulance a;
    SimpleTimer turnTimer;
    SpeechBubble speechBubble;
    private static final int TURN_DURATION = 2000;
    private static final int AMB_DURATION = 3000;
    public Pedestrian() { 
        leftImage = new GreenfootImage("Normal_Ped1.png");
        sLeftImage = new GreenfootImage("Normal_Ped1S.png");
        downImage = new GreenfootImage("Normal_Ped2.png");
        sDownImage = new GreenfootImage("Normal_Ped2S.png");
        deathImage = new GreenfootImage("Normal_Ped_Death.png"); 
        turnTimer = new SimpleTimer();
        speechBubble = new SpeechBubble();
        c = new Car();
        a = new Ambulance();
        speed = 1;
        turnActsLeft = 0;
        leftTrue = false;
        downTrue = false;
        timeStart = false;
        turn = false;
        carIn = false;
        dead = false;
    }

    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!turnTrue){
            moveLeft();            
        }
        else if(!dead){
            turnActsLeft++;
            if(turnActsLeft >= 50){
                moveDown();
            }
        }
        turn();
        if(speed == 0 && dead == false){
            int mst = turnTimer.millisElapsed();
            setImage(sLeftImage);
            if(mst >= TURN_DURATION){
                if(!turnTrue){
                    setImage(sDownImage);
                    turnTrue = true;
                    speed = 1;
                }
            }
        }
        if(!carIn){
            spawnCar();
        }
        if(dead == true){
            ambActsLeft++;
            if(ambActsLeft >= 180){
                getWorld().addObject(a, 50, 550);
            }
        }
        
        if (speechBubble != null) {
            speechBubble.setLocation(getX(), getY() - 40); // Adjust the Y offset as needed
        }
    }

    public void addedToWorld(World world) {
        world.addObject(speechBubble, getX(), getY() - 40); // Adjust the Y offset as needed
    }
    
    public void knockDown(){
        speed = 0;
        setImage(deathImage);
        dead = true;
        
        // Remove the SpeechBubble from the world
        if (speechBubble != null) {
            getWorld().removeObject(speechBubble);
            speechBubble = null; // Set the reference to null to avoid potential issues
        }
    }

    public void turn(){
        s = (SidewalkB)getOneObjectAtOffset(0, 0, SidewalkB.class);
        if(s != null){
            if(!turn){
                speed = 0;
                if(!timeStart){
                    turnTimer.mark();
                    timeStart = true;
                }
                turn = true;
            }
        }
    }

    
    public void moveDown(){
        if(!downTrue){
            setImage(downImage);
            downTrue = true;
        }
        setLocation(getX(), getY() + speed);
    }

    public void moveLeft(){
        if(!leftTrue){
            setImage(leftImage);
            leftTrue = true;
        }
        setLocation(getX() - speed, getY());
    }

    public void spawnCar(){
        r = (Road1B)getOneObjectAtOffset(0, 0, Road1B.class);
        if(r != null){
            getWorld().addObject(c, 50, 550);
            carIn = true;
        }
    }
}
