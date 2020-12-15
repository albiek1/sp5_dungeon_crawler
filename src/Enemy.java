import processing.core.PApplet;

public class Enemy extends PApplet {
    int ex, ey, eH, eHmax, eRange;
    boolean canL, canR, canU, canD, canUL, canUR, canDL, canDR, eCanAttack, detected;
    Enemy(int ex, int ey, int eH, int eHmax, int eRange,
          boolean canL, boolean canR, boolean canU, boolean canD,
          boolean canUL, boolean canUR, boolean canDL, boolean canDR,
          boolean eCanAttack, boolean detected){
        this.ex = ex;
        this.ey = ey;
        this.eH = eH;
        this.eHmax = eHmax;
        this.eRange = eRange;
        this.canL = canL;
        this.canR = canR;
        this.canU = canU;
        this.canD = canD;
        this.canUL = canUL;
        this.canUR = canUR;
        this.canDL = canDL;
        this.canDR = canDR;
        this.eCanAttack = eCanAttack;
        this.detected = detected;
    }

    //enemy movement and attack is handled here
    public void enemyAI(int playerX, int playerY){
        if(playerX == ex && playerY < ey && canU && !eCanAttack && detected){
            ey -= 1;
        }else if(playerX == ex && playerY > ey && canD && !eCanAttack && detected){
            ey += 1;
        }else if(playerX < ex && playerY == ey && canL && !eCanAttack && detected){
            ex -= 1;
        }else if(playerX > ex && playerY == ey && canR && !eCanAttack && detected){
            ex += 1;
        }else if(playerX < ex && playerY < ey && canUL && !eCanAttack && detected){
            ex -= 1;
            ey -= 1;
        }else if(playerX > ex && playerY < ey && canUR && !eCanAttack && detected){
            ex += 1;
            ey -= 1;
        }else if(playerX < ex && playerY > ey && canDL && !eCanAttack && detected){
            ex -= 1;
            ey += 1;
        }else if(playerX > ex && playerY > ey && canDR && !eCanAttack && detected){
            ex += 1;
            ey += 1;
        }
        if(!detected){
            int check = round(random(0, round(dist(playerX, playerY, ex, ey))));
            if(check == 0){
                detected = true;
            }
        }
    }
}
