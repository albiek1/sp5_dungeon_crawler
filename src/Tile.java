public class Tile{
    boolean occupied, hasEnemy, hasPlayer;
    int x, y;
    Tile(boolean occupied, boolean hasEnemy, boolean hasPlayer, int x, int y){
        this.occupied = occupied;
        this.x = x;
        this.y = y;
        this.hasEnemy = hasEnemy;
        this.hasPlayer = hasPlayer;
    }
}
