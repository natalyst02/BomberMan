package NguHuynhDe.entities.mob;

import NguHuynhDe.Board;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.display.Screen;
import NguHuynhDe.display.Sprite;

public class Shield extends Mob{
    private boolean beActive = false;
    private double theRestTime = 0;
    private double coolDown = 0;
    protected Board GameBoard;
    private Player player;

    public Shield(int x, int y, double theRestTime, double coolDown, Board board, Player player) {
        super(x,y,board);
        this._x = x;
        this._y = y;
        GameSprite = Sprite.shield;
        GameBoard = board;
        this.theRestTime = theRestTime; // 5s
        this.coolDown = coolDown; //30s cd
        this.player = player;
    }


    public void setPosition(double x,double y){
        this._x  = x;
        this._y  = y;
    }


    public boolean  getActive(){
        return beActive;
    }

    public void setActive(boolean beActive) {
        this.beActive = beActive;
    }

    public double getcoolDown() {
        return coolDown;
    }

    public void settheRestTime(double theRestTime) {
        this.theRestTime = theRestTime;
    }

    @Override
    public void update() {
        if (coolDown > 0) {
            coolDown--;
        }


        if (theRestTime > 0){
            theRestTime--;
            this._x = player.getX();
            this._y = player.getY();
          
        }
        else if (beActive)
            {
            this.setActive(false);
            remove();

        }

    }



    @Override
    public void render(Screen screen) {


        if (theRestTime > 0)
        screen.renderEntity((int)_x, (int)_y - GameSprite.SIZE, this);
    }

    @Override
    protected void calculateMove() {

    }

    @Override
    protected void move(double xa, double ya) {

    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    @Override
    protected boolean canMove(double x, double y) {
        return false;
    }

    @Override
    public boolean checkCollide(Entity e) {
        return false;
    }
}
