public class Player {
    int px, py, pH, pHmax, pRange, pAtk;
    boolean canL, canR, canU, canD, canUL, canUR, canDL, canDR;
    Player(int px, int py, int pH, int pHmax, int pRange, int pAtk,
           boolean canL, boolean canR, boolean canU, boolean canD,
           boolean canUL, boolean canUR, boolean canDL, boolean canDR){
        this.px = px;
        this.py = py;
        this.pH = pH;
        this.pHmax = pHmax;
        this.pRange = pRange;
        this.pAtk = pAtk;
        this.canL = canL;
        this.canR = canR;
        this.canU = canU;
        this.canD = canD;
        this.canUL = canUL;
        this.canUR = canUR;
        this.canDL = canDL;
        this.canDR = canDR;
    }
}
