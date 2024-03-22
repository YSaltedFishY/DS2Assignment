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
            System.out.print("|\n");
        }
        System.out.println("- A B C D E F G -");
    }

    public boolean makeMove(Move move,Player player){
        for(int row = rowNum - 1; row >= 0; row--){
            if(cells[row][move.getCol()].getPlayer() == null){
                cells[row][move.getCol()].setPlayer(player);
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

    public boolean winCheck(Player player){
        int count = 0;

        //Horizontal 4
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                if(cells[i][j].getPlayer() == player){
                    count++;
                }else {
                    count = 0;
                }
                if(count == 4){
                    System.out.println("Horizontal win");
                    return true;
                }
            }
            count = 0;
        }

        //Vertical 4 optimizable
        for(int j = 0; j < colNum; j++){
            for(int i = 0; i < rowNum; i++){
                if(cells[i][j].getPlayer() == player){
                    count++;
                }else{
                    count = 0;
                }
                if(count == 4){
                    System.out.println("Vertical win");
                    return true;
                }
            }
            count = 0;
        }

        //Diagonal 4
        for(int i = rowNum - 1; i >= 0; i--){
            for(int j = 0; j < colNum; j++){
                if(cells[i][j].getPlayer() == player) {
                    count++;
                    //System.out.println("If statement current i,j: " + i + "," + j + " count: " + count);
                    for (int row = i - 1; row >= 0; row--) {
                        if (j + 1 < colNum && cells[row][j+1].getPlayer() == player) {
                            count++;
                            //System.out.println("current i,j: " + row + "," + (j+1) + " count: " + count + cells[row][j+1].getPlayer().getName());
                            j++;
                        }
                        if(count == 4){
                            System.out.println("Diagonal win");
                            return true;
                        }
                    }
                }
                count = 0;
            }
            count = 0;
        }

        //Reverse diagonal
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                if(cells[i][j].getPlayer() == player) {
                    count++;
                    for (int row = i+1; row < rowNum; row++) {
                        if (j + 1 < colNum && cells[row][j+1].getPlayer() == player) {
                            count++;
                            j++;
                        }

                        if(count == 4){
                            System.out.println("Reverse Diagonal win");
                            return true;
                        }
                    }
                }
                count = 0;
            }
            count = 0;
        }

        return false;
    }

}
