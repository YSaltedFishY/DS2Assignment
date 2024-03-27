public class Board {
    Cell[][] cells;
    private int rowNum;
    private int colNum;
    private int numMove;

    public Board(){
        // Initialize the board
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
        // Getters   
    public int getNumMove() {
        return numMove;
    }
    // Getters
    public int getColNum() {
        return colNum;
    }

<<<<<<< Updated upstream
    public int getRowNum() {
        return rowNum;
    }

    // Getters
=======
    public int getColNum() {
        return colNum;
    }

>>>>>>> Stashed changes
    public Cell getCell(int row, int col) {
        if (row >= 0 && row < rowNum && col >= 0 && col < colNum) {
            return cells[row][col];
        } else {
            return null; 
        }
    }
<<<<<<< Updated upstream
    
    // Board consturctor 
=======

>>>>>>> Stashed changes
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

 // Print the board 
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

    // Make a move - player 
    public int[] makeMove(Move move,Player player){
        // iterate through the rows
        for(int row = rowNum - 1; row >= 0; row--){
            // if the cell is empty
            if(cells[row][move.getCol()].getPlayer() == null){
                // set the player
                cells[row][move.getCol()].setPlayer(player);
                // increment the number of moves 
                numMove++;
                // return the row and column of the move
                return new int [] {row, move.getCol()};
            }
        }
        // 
        return new int [] {-1, move.getCol()};
    }

    

     // check if the board is full - draw 
    public boolean isFull(){
        if(numMove == rowNum * colNum) return true;
        return false;
    }


     // check if the player has won  
    public boolean winCheck(Player player , int row, int col){
        int count = 0;


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


        
        int diff=colNum-col-1;
        if(diff>row) diff=row;

        int c_start=(col+diff) ;
        int r_start=row-diff;

        count=0;


        while (r_start<rowNum && c_start>=0) {
            System.out.println("current row,col: " + r_start + "," + (c_start) + cells[r_start][c_start].getPlayer());

            if (cells[r_start][c_start].getPlayer() == player) {
                count++;
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


        while(c_start<colNum && r_start<rowNum ){

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
