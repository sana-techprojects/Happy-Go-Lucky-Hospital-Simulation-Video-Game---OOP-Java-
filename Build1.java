import greenfoot.*;
public class Build1 extends Prop
{
    private Ambulance ambulance;
    private SimpleTimer timer = new SimpleTimer();

    public Build1(int xSizer, int ySizer, Ambulance ambulance) {
        super(xSizer, ySizer);
        timer.mark();
        this.ambulance = ambulance;
        img = new GreenfootImage("apartment-building.png");
    }

    public void act() {
        
        if(timer.millisElapsed() > 32000)
        {
            this.move(-10);
            if(this.getX() == 0)
            {
                move(0);
            }
        }
    }
}
