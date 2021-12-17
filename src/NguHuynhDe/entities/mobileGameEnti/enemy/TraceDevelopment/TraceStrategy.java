/*
package NguHuynhDe.entities.mob.enemy.TraceDevelopment;


import NguHuynhDe.entities.mob.enemy.Enemy;

public interface TraceStrategy {

    Map gameMap = Map.getGameMap();

    int distanceBomb = 12000;
    int distanceTarget = 50000;
    int[] dx = {-1, 1, 0, 0, 0};
    int[] dy = {0, 0, -1, 1, 0};

    <Bomber> int trace(Enemy enemy, Bomber player);

    default int getDistance(Enemy enemy, Bomber player) {
        return (enemy.pixelX - player.pixelX) * (enemy.pixelX - player.pixelX)
                + (enemy.pixelY - player.pixelY) * (enemy.pixelY - player.pixelY);
    }

    default int getDistanceBomb(Character character) {
        int distanceFromBomb = Integer.MAX_VALUE;
        for (Bomb bomb : gameMap.bombs) {
            int distance = (bomb.pixelX - character.pixelX) * (bomb.pixelX - character.pixelX)
                    + (bomb.pixelY - character.pixelY) * (bomb.pixelY - character.pixelY);
            distanceFromBomb = Math.min(distanceFromBomb, distance);
        }
        return distanceFromBomb;
    }


    default boolean CheckFaceBoom(int X, int Y, int lengthBomb) {
        return false;
    }
}


 */