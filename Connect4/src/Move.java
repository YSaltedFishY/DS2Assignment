public class Move {
    private int col;
    private int row;

    public Move(int col){
        this.col = col;
    }
    public int getCol(){
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}