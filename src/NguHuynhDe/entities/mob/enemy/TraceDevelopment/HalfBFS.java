/*
package NguHuynhDe.entities.mob.enemy.TraceDevelopment;


public class HalfBFS implements TraceStrategy {

    private static int time = 0;

    @Override
    public int trace(Enemy enemy, Bomber player) {
        if ((enemy.pixelX % Sprite.SCALED_SIZE) != 0 || (enemy.pixelY % Sprite.SCALED_SIZE != 0)) {
            return enemy.direction;
        }
        ++time;
        if (time <= 9)
            return new BFSvsDodge().trace(enemy, player);
        else {
            time = time % 20;
            return new RandomTrace().trace(enemy, player);
        }
    }
}

 */
