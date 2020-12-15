//to do:

import processing.core.PApplet;

import java.util.ArrayList;

public class dungeon_main extends PApplet {
    int mSizeX = 10;
    int mSizeY = 10;
    int px = 1;
    int py = 1;
    int pH = 10;
    int pHmax = 10;
    int pRange = 3;
    int pAtk = 3;
    int eH = 5;
    int eHmax = 5;
    int eRange = 1;
    boolean pCanAttack = false;
    ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    Tile[][] grid;
    Player p = new Player(px, py, pH, pHmax, pRange, pAtk,
            true, true, true, true,
            true, true, true, true);

    /*Enemy e = new Enemy(ex, ey, eH, eHmax, eRange,
            true, true, true, true,
            true, true, true, true,
            false);*/
    public void settings() {
        fullScreen();
        grid = new Tile[mSizeX][mSizeY];
        for (int y = 0; y < mSizeY; y++) {
            for (int x = 0; x < mSizeX; x++) {
                grid[x][y] = new Tile(false, false, false, x * 50, y * 50);
            }
        }
        grid[3][3].occupied = true;
        grid[3][4].occupied = true;
        grid[4][3].occupied = true;
        for (int x = 0; x < mSizeX; x++) {
            grid[x][0].occupied = true;
            grid[x][mSizeY - 1].occupied = true;
        }
        for (int y = 0; y < mSizeY - 1; y++) {
            grid[0][y].occupied = true;
            grid[mSizeX - 1][y].occupied = true;
        }
        for(int i = 0; i < 6; i++){
                int temp1 = 0;
                int temp2 = 0;
                while(grid[temp1][temp2].occupied || grid[temp1][temp2].hasPlayer){
                    temp1 = round(random(mSizeX-1));
                    temp2 = round(random(mSizeY-1));
                }
                grid[temp1][temp2].occupied = true;
        }
        /*for (int i = 0; i < 2; i++) {
            int t1 = 0;
            int t2 = 0;
            while (grid[t1][t2].occupied || grid[t1][t2].hasEnemy) {
                t1 = round(random(mSizeX - 1));
                t2 = round(random(mSizeY - 1));
                System.out.println(t1 + ", " + t2);
            }
            enemyArrayList.add(new Enemy(t1, t2, eH, eHmax, eRange, true, true, true, true, true, true, true, true, false, false));
            grid[t1][t2].hasEnemy = true;
        }*/
    }

    public static void main(String[] args) {
        PApplet.main("dungeon_main", args);
    }

    public void draw() {
        background(255);
        for (int y = 0; y < mSizeY; y++) {
            for (int x = 0; x < mSizeX; x++) {
                Tile a = grid[x][y];
                if (a.occupied) {
                    fill(0);
                } else if (a.hasEnemy) {
                    fill(0, 0, 255);
                } else {
                    fill(255);
                }
                rect(a.x, a.y, 50, 50);
                fill(0);
            }
        }
        if(enemyArrayList.size() < 2){
            int t1 = 0;
            int t2 = 0;
            while (grid[t1][t2].occupied || grid[t1][t2].hasEnemy || grid[t1][t2].hasPlayer) {
                t1 = round(random(mSizeX - 1));
                t2 = round(random(mSizeY - 1));
                System.out.println(t1 + ", " + t2);
            }
            enemyArrayList.add(new Enemy(t1, t2, eH, eHmax, eRange, true, true, true, true, true, true, true, true, false, false));
            grid[t1][t2].hasEnemy = true;
        }
        for (int i = enemyArrayList.size() - 1; i >= 0; i--) {
            Enemy e = enemyArrayList.get(i);
            for (int i1 = 0; i1 < mSizeX - 1; i1++) {
                for (int i2 = 0; i2 < mSizeY - 1; i2++) {
                    if (grid[i1][i2].hasEnemy && grid[i1][i2] != grid[e.ex][e.ey]) {
                        grid[i1][i2].hasEnemy = false;
                    }
                    if (grid[i1][i2].hasPlayer && grid[i1][i2] != grid[p.px][p.py]) {
                        grid[i1][i2].hasPlayer = false;
                    }
                }
            }
            fill(0, 255, 0);
            rect(p.px * 50, p.py * 50, 50, 50);
            PcanMove();
            unStickEnemies();
            EcanMove();
            fill(255, 0, 0);
            rect(e.ex * 50, e.ey * 50, 50, 50);
            fill(0);
            textAlign(CENTER, CENTER);
            text(e.eH + "/" + e.eHmax, e.ex * 50 + 25, e.ey * 50 + 25);
            if(e.eH <= 0){
                enemyArrayList.remove(e);
            }
        }
        text("HP: "+p.pH+"/"+p.pHmax, width/2, 20);
    }

    public void unStickEnemies(){
        for(int i = enemyArrayList.size()-1; i >= 0; i--){
            Enemy e = enemyArrayList.get(i);
            if(e.ex == p.px && e.ey == p.py){
                if(!grid[e.ex-1][e.ey].occupied || !grid[e.ex-1][e.ey].hasEnemy || !grid[e.ex-1][e.ey].hasPlayer){
                    e.ex -= 1;
                }else if(!grid[e.ex-1][e.ey-1].occupied || !grid[e.ex-1][e.ey-1].hasEnemy || !grid[e.ex-1][e.ey-1].hasPlayer){
                    e.ex -= 1;
                    e.ey -= 1;
                }else if(!grid[e.ex][e.ey-1].occupied || !grid[e.ex][e.ey-1].hasEnemy || !grid[e.ex][e.ey-1].hasPlayer){
                    e.ey -= 1;
                }else if(!grid[e.ex+1][e.ey-1].occupied || !grid[e.ex+1][e.ey-1].hasEnemy || !grid[e.ex+1][e.ey-1].hasPlayer){
                    e.ex += 1;
                    e.ey -= 1;
                }else if(!grid[e.ex+1][e.ey].occupied || !grid[e.ex+1][e.ey].hasEnemy || !grid[e.ex+1][e.ey].hasPlayer){
                    e.ex += 1;
                }else if(!grid[e.ex+1][e.ey+1].occupied || !grid[e.ex+1][e.ey+1].hasEnemy || !grid[e.ex+1][e.ey+1].hasPlayer){
                    e.ex += 1;
                    e.ey += 1;
                }else if(!grid[e.ex][e.ey+1].occupied || !grid[e.ex][e.ey+1].hasEnemy || !grid[e.ex][e.ey+1].hasPlayer){
                    e.ey += 1;
                }else if(!grid[e.ex-1][e.ey+1].occupied || !grid[e.ex-1][e.ey+1].hasEnemy || !grid[e.ex-1][e.ey+1].hasPlayer){
                    e.ex -= 1;
                    e.ey += 1;
                }
            }
        }
    }

    public void EcanMove() {
        for (int i = enemyArrayList.size() - 1; i >= 0; i--) {
            Enemy e = enemyArrayList.get(i);
            e.canD = !grid[e.ex][e.ey + 1].occupied && !grid[e.ex][e.ey + 1].hasPlayer && !grid[e.ex][e.ey + 1].hasEnemy;
            e.canU = !grid[e.ex][e.ey - 1].occupied && !grid[e.ex][e.ey - 1].hasPlayer && !grid[e.ex][e.ey - 1].hasEnemy;
            e.canR = !grid[e.ex + 1][e.ey].occupied && !grid[e.ex + 1][e.ey].hasPlayer && !grid[e.ex + 1][e.ey].hasEnemy;
            e.canL = !grid[e.ex - 1][e.ey].occupied && !grid[e.ex - 1][e.ey].hasPlayer && !grid[e.ex - 1][e.ey].hasEnemy;
            e.canUL = !grid[e.ex - 1][e.ey - 1].occupied && !grid[e.ex - 1][e.ey - 1].hasPlayer && !grid[e.ex - 1][e.ey - 1].hasEnemy;
            e.canUR = !grid[e.ex + 1][e.ey - 1].occupied && !grid[e.ex + 1][e.ey - 1].hasPlayer && !grid[e.ex + 1][e.ey - 1].hasEnemy;
            e.canDL = !grid[e.ex - 1][e.ey + 1].occupied && !grid[e.ex - 1][e.ey + 1].hasPlayer && !grid[e.ex - 1][e.ey + 1].hasEnemy;
            e.canDR = !grid[e.ex + 1][e.ey + 1].occupied && !grid[e.ex + 1][e.ey + 1].hasPlayer && !grid[e.ex + 1][e.ey + 1].hasEnemy;
            if (!grid[e.ex][e.ey].hasEnemy) {
                grid[e.ex][e.ey].hasEnemy = true;
            }
            e.eCanAttack = round(dist(e.ex, e.ey, p.px, p.py)) <= e.eRange;
        }
    }

    public void PcanMove() {
        for (int i = enemyArrayList.size() - 1; i >= 0; i--) {
            Enemy e = enemyArrayList.get(i);
            p.canD = !grid[p.px][p.py + 1].occupied && !grid[p.px][p.py + 1].hasEnemy;
            p.canU = !grid[p.px][p.py - 1].occupied && !grid[p.px][p.py - 1].hasEnemy;
            p.canR = !grid[p.px + 1][p.py].occupied && !grid[p.px + 1][p.py].hasEnemy;
            p.canL = !grid[p.px - 1][p.py].occupied && !grid[p.px - 1][p.py].hasEnemy;
            p.canUL = !grid[p.px - 1][p.py - 1].occupied && !grid[p.px - 1][p.py - 1].hasEnemy;
            p.canUR = !grid[p.px + 1][p.py - 1].occupied && !grid[p.px + 1][p.py - 1].hasEnemy;
            p.canDL = !grid[p.px - 1][p.py + 1].occupied && !grid[p.px - 1][p.py + 1].hasEnemy;
            p.canDR = !grid[p.px + 1][p.py + 1].occupied && !grid[p.px + 1][p.py + 1].hasEnemy;
            if (!grid[p.px][p.py].hasPlayer) {
                grid[p.px][p.py].hasPlayer = true;
            }
            pCanAttack = round(dist(p.px, p.py, e.ex, e.ey)) <= p.pRange;
        }
    }

    public void mousePressed() {
        Enemy e1 = enemyArrayList.get(0);
        Enemy e2 = enemyArrayList.get(1);
        for (int x = 0; x < mSizeX - 1; x++) {
            for (int y = 0; y < mSizeY - 1; y++) {
                if (mouseX < x * 50 + 50 && mouseX > x * 50) {
                    if (mouseY < y * 50 + 50 && mouseY > y * 50) {
                        System.out.println(x + ", " + y);
                        boolean b = (x == e1.ex && y == e1.ey) || (x == e2.ex && y == e2.ey);
                        boolean r1 = round(dist(p.px, p.py, e1.ex, e1.ey)) <= p.pRange;
                        boolean r2 = round(dist(p.px, p.py, e2.ex, e2.ey)) <= p.pRange;
                        if (p.px > x && p.py == y && p.canL && !(b && pCanAttack)) {
                            p.px -= 1;
                        } else if (p.px < x && p.py == y && p.canR && !(b && pCanAttack)) {
                            p.px += 1;
                        } else if (p.py > y && p.px == x && p.canU && !(b && pCanAttack)) {
                            p.py -= 1;
                        } else if (p.py < y && p.px == x && p.canD && !b && !pCanAttack) {
                            p.py += 1;
                        } else if (p.px > x && p.py > y && p.canUL && !(b && pCanAttack)) {
                            p.px -= 1;
                            p.py -= 1;
                        } else if (p.px < x && p.py > y && p.canUR && !(b && pCanAttack)) {
                            p.px += 1;
                            p.py -= 1;
                        } else if (p.px > x && p.py < y && p.canDL && !(b && pCanAttack)) {
                            p.px -= 1;
                            p.py += 1;
                        } else if (p.px < x && p.py < y && p.canDR && !(b && pCanAttack)) {
                            p.px += 1;
                            p.py += 1;
                        } else if (p.px == x && p.py == y) {
                            p.px += 0;
                            p.py += 0;
                        } else if (x == e1.ex && y == e1.ey && r1) {
                            e1.eH -= p.pAtk;
                            e1.detected = true;
                        } else if (x == e2.ex && y == e2.ey && r2){
                            e2.eH -= p.pAtk;
                            e2.detected = true;
                        }
                    }
                }
            }
        }
        for(Enemy temp : enemyArrayList){
            temp.enemyAI(p.px, p.py);
            unStickEnemies();
            if(temp.eCanAttack && temp.detected){
                p.pH -= 1;
            }
        }
    }
}