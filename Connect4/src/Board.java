public class Board {
    Cell[][] cells;
    private int rowNum;
    private int colNum;
    private int numMove;

    public Board(){
        numMove = 0;
        rowNum = 6;
        colNum = 7;
        cells = new Cell[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                cells[i][j] = new Cell();
            }
        }
    }

    public int getNumMove() {
        return numMove;
    }

    public Board(int numMove, Cell[][] c){
        this.numMove = numMove;
        rowNum = 6;
        colNum = 7;
        cells = new Cell[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                cells[i][j] = c[i][j];
            }
        }
    }

    public Board other(){
        return new Board(numMove,cells);
    }


    public void printBoard(){
        System.out.println("----Connect 4----");
        for(int i = 0; i < rowNum; i++){
            System.out.print("| ");
            for(int j = 0; j < colNum; j++){
                System.out.print(cells[i][j] + " ");
            }
            System.out.print("| "+i+"\n");
//            System.out.print("|\n");
        }
        System.out.println("- 0 1 2 3 4 5 6 -");
        System.out.println("- A B C D E F G -");
    }

    public boolean makeMove(Move move,Player player){
        for(int row = rowNum - 1; row >= 0; row--){
            if(cells[row][move.getCol()].getPlayer() == null){
                cells[row][move.getCol()].setPlayer(player);
                move.setRow(row);
                numMove++;
                return true;
            }
        }
        return false;
    }

    public boolean isFull(){
        if(numMove == rowNum * colNum) return true;
        return false;
    }

    public boolean winCheck(Player player, Move move){
        int count = 0;
        int row=move.getRow();
        int col=move.getCol();

        //Horizontal 4
        for(int j = 0; j < colNum; j++) {
            if (cells[row][j].getPlayer() == player) {
                count++;
            } else {
                count = 0;
            }

            if(count == 4){
                System.out.println("Horizontal win");
                return true;
            }
        }


        //Vertical 4 optimizable
        for(int j = 0; j < rowNum; j++){
            if(cells[j][col].getPlayer() == player){
                count++;
            }else{
                count = 0;
            }
            if(count == 4){
                System.out.println("Vertical win");
                return true;
            }
        }


        //Diagonal 4


        //System.out.println("If statement current i,j: " + i + "," + j + " count: " + count);
        //int[] start=new int[2];
        int diff=colNum-col-1;
        if(diff>row) diff=row;

        int c_start=(col+diff) ;
        int r_start=row-diff;

        count=0;


        while (r_start<rowNum && c_start>=0) {
            System.out.println("current row,col: " + r_start + "," + (c_start) + cells[r_start][c_start].getPlayer());

            if (cells[r_start][c_start].getPlayer() == player) {
                count++;
                //System.out.println("current row,col: " + r_start + "," + (c_start) + " count: " + count + cells[r_start][c_start].getPlayer().getName());
                if (count == 4) {
                    System.out.println("Diagonal win");
                    return true;
                }
            }
            else {
                count=0;
            }
            r_start++;
            c_start--;

        }





        //Reverse diagonal

        count = 0;
        if(col>row) diff=row;
        else diff=col;

        c_start= col-diff;
        r_start=row-diff;
//        if(c_start<0) c_start=0;
//        if(r_start<0) r_start=0;


        while(c_start<colNum && r_start<rowNum ){
                System.out.println("current row,col: " + r_start + "," + (c_start) + cells[r_start][c_start].getPlayer());

                if(cells[r_start][c_start].getPlayer() == player) {
                    count++;

                    if(count == 4){
                        System.out.println("Reverse Diagonal win");
                        return true;
                    }

                }else {
                    count = 0;
                }
                r_start++;
                c_start++;

            }


        return false;
    }

}