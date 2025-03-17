import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The world of which the intro simulation takes place in.
 * <p> Made By: Harithan Raveendran
 * <p> Adding of sounds and worked on by: Sana Pardiwala
 * <p> Idea inspiration: Mr Cohen
 * <p> Borrowed code from: Danpost
 * <p> Last Modified: Nov 10, 2023
 */

public class IntroSim extends World
{
    //The two integers set the x and y values of all the objects added into the world.
    int x;
    int y = 596;
    //The objects that are only added into the world once.
    private Pedestrian p;
    private Road1B r;
    private SidewalkB s;
    private SimpleTimer timer = new SimpleTimer();
    
    SpeechBubble speechBubble;
    
    //Sounds
    GreenfootSound bgm;
    
    
    //testing
    Ambulance ambulance = new Ambulance();
    private int transitionCounter = 0;
    private final int TRANSITION_COORDINATE = -500;
    HospitalBuilding hospital;
    Title title = new Title();
    NextButton nextButton = new NextButton();
    
    /**
     * The constructor that calls on the "World()" superclass, defines the objects of the actors, and adds all the objects into the world.
     */
    public IntroSim()
    {    
        // Create a new world with 770x400 cells with a cell size of 1x1 pixels.
        super(1109, 650, 1, false);
        timer.mark();
        bgm = new GreenfootSound("forestday_bw66-16725.mp3");
        bgm.setVolume(40);
        
        p = new Pedestrian();
        hospital = new HospitalBuilding();
        
        speechBubble = new SpeechBubble();
        r = new Road1B(200, 200);
        s = new SidewalkB(200, 400);
        
        prepare();
        
        //testing
        addObject(hospital, 4000, 125);
        //addObject(hospital, 129, 123);
        
    }
    
    public void act()
    {
        if(timer.millisElapsed() > 41000)
        {
            //addObject(hospital, 4000, 125);
            addObject(title, 570, 325);
            addObject(nextButton, 900, 500);
            bgm.stop();
        }
        //if(p != null)
        //{
            //addObject(speechBubble, p.getX(), p.getY());
        //}
    }
    
    public void started()
    {
        bgm.playLoop();
    }
    
    //stops/ pauses background sound/ sound effects
    public void stopped()
    {
        bgm.stop();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //Every for loop allows the user's desired object to be added multiple times.
        for(int i = 0; i < 16; i++){
            addTile(Road1.class, 1000, 400, x, y);
            //x value changes by a specific amount to ensure that all the objects are spaced out from each other.
            x += 269;
        }
        x = 0;
        y = 398;
        for(int i = 0; i < 16; i++){
            addTile(Road1.class, 1000, 400, x, y);
            x += 269;
        }
        x = 0;
        y = 408;
        for(int i = 0; i < 16; i++){
            addTile(Road1.class, 1000, 400, x, y);
            x += 269;
        }
        x = 0;
        y = 568;
        for(int i = 0; i < 16; i++){
            addTile(Road1.class, 1000, 400, x, y);
            x += 269;
        }
        x = 0;
        y = 489;
        for(int i = 0; i < 28; i++){
            addTile(Road2.class, 555, 185, x, y);
            x += 150;
        }
        x = 0;
        y = 299;
        for(int i = 0; i < 33; i++){
            addTile(Sidewalk.class, 475, 325, x, y);
            x += 125;
        }
        x = 0;
        y = 186;
        for(int i = 0; i < 33; i++){
            addTile(Grass.class, 525, 525, x, y);
            x += 125;
        }
        x = 0;
        y = 71;
        for(int i = 0; i < 33; i++){
            addTile(Grass.class, 525, 525, x, y);
            x += 125;
        }
        x = 105;
        y = 125;
        for(int i = 0; i < 2; i++){
            addTile(Build1.class, 75, 75, x, y);
            x += 205;
        }
        //All the times "addTile" is called here is used for objects of "newProp" that are only added once.
        addTile(Build2.class, 75, 75, 557, y);
        x += 235;
        addTile(Build3.class, 75, 75, x, y);
        x += 250;
        x = 1000;
        for(int i = 0; i < 5; i++){
            addTile(Build1.class, 75, 75, x, y);
            x += 205;
        }
        addTile(Build2.class, 75, 75, x, y);
        x += 205;   
        for(int i = 0; i < 3; i++){
            addTile(Build1.class, 75, 75, x, y);
            x += 205;
        }
        x = 725;
        y = 300;
        addTile(SidewalkB.class, 200, 200, x, y);
        //All the addObject methods here are used for adding objects that are not subclasses of "newProp."
        x = 850;
        y = 540;
        addObject(r, 746,544);
        addObject(p, 1300, 300);
        addObject(s, 960, 560);
        Prop prop = new Prop(200, 200);
        
        addObject(p, 1300, 300);
        p.addedToWorld(this);
    }

    /**
     * A method that allows multiple objects of one class to be added to the world in a row.
     * @param Class is the desired actor that the user wishes to add multiple times.
     * @param xSize sets the width of all the actors.
     * @param ySize sets the height of all the actors.
     * @param x sets the x location of the first object that is added to the world.
     * @param y sets the y location of the first object that is added to the world.
     */
    private void addTile(Class cls, int xSize, int ySize, int x, int y){
        x = this.x;
        y = this.y;
        //A series of if statements that sets the value of the object of "Prop" to equal what the user put for their "cls" parameter.
        Prop newProp = new Road1(xSize, ySize);
        if (cls == Road2.class){
            newProp = new Road2(xSize, ySize);
        }
        else if (cls == Road1B.class){
            newProp = new Road1B(xSize, ySize);
        }
        else if (cls == Sidewalk.class){
            newProp = new Sidewalk(xSize, ySize);
        }
        else if (cls == Grass.class){
            newProp = new Grass(xSize, ySize);
        }
        else if (cls == SidewalkB.class){
            newProp = new SidewalkB(xSize, ySize);
        }
        else if (cls == Build1.class){
            newProp = new Build1(xSize, ySize, ambulance);
        }
        else if (cls == Build2.class){
            newProp = new Build2(xSize, ySize);
        }
        else if (cls == Build3.class){
            newProp = new Build3(xSize, ySize);
        }
        //Adds the user's desired object to the world.
        addObject(newProp, x, y);
        
    }
}