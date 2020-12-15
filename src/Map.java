public class Map {
    private boolean[][] cells;

    public Map(int width, int height){
        cells = new boolean[width][height];
    }

    int Width = cells.length +1;
    int Height = cells.length +1;

    public void MarkCellsUnvisited(){
        for(int x = 0; x < Width; x++){
            for(int y = 0; y < Height; y++){
                cells[x][y] = false;
            }
        }
    }
}
