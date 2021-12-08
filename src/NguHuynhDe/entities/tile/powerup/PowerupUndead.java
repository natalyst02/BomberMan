package NguHuynhDe.entities.tile.powerup;

import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.display.SpriteInGame;
import java.util.Timer;
import java.util.TimerTask;

public class PowerupUndead extends Powerup {

    public PowerupUndead(int x, int y, int level, SpriteInGame sprite) {
        super(x, y, level, sprite);
    }

    @Override
    public boolean checkCollide(Entity e) {

        if(e instanceof Player) {
            ((Player) e).addPowerup(this);
            remove();
            return true;
        }

        return false;
    }

    @Override
    public void setValues() {
        beActive = true;
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10;

            public void run() {


                Player.undead=true;
                i--;
                if (i < 0) {
                    timer.cancel();
                    Player.undead=false;
                }
            }
        }, 0, 1000);

       // timer.schedule(active(), 2000);
    }




}
