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
    public Board(Cell[][] pieces){
        numMove = 0;
        rowNum = 6;
        colNum = 7;
        cells = new Cell[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                cells[i][j] = pieces[i][j];
                if(pieces[i][j]!=null){
                    numMove++;
                }
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
           // System.out.println("current row,col: " + r_start + "," + (c_start) + cells[r_start][c_start].getPlayer());

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
                //System.out.println("current row,col: " + r_start + "," + (c_start) + cells[r_start][c_start].getPlayer());

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
    public int boardScore(Player player, Player opponent){
        int score = 0;
        int count=0;
        int highest_count=0;
        //checking horizontal
        for(int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum - 3; j++) {
                if (cells[i][j].getPlayer().equals(player) && cells[i][j + 1].getPlayer().equals(player)) {
                    if (cells[i][j + 2].getPlayer().equals(player)) {
                        if (cells[i][j + 3].getPlayer().equals(player)) score += 1000;
                        else if (cells[i][j + 3].getPlayer() == null) score += 10;
                    } else if (cells[i][j + 2].getPlayer() == null && cells[i][j + 3].getPlayer().equals(player))
                        score += 10;
                    else if (cells[i][j + 2].getPlayer() == null && cells[i][j + 3].getPlayer() == null) score += 3;
                }
                if (cells[i][j].getPlayer().equals(opponent) && cells[i][j + 1].getPlayer().equals(opponent)) {
                    if (cells[i][j + 2].getPlayer().equals(opponent)) {
                        if (cells[i][j + 3].getPlayer() == null) {
                            if (cells[i][j+3].getPlayer().equals(opponent)) score -= 2000;
                        else score -= 50;}
                    } else if (cells[i][j + 2].getPlayer() == null && cells[i][j + 3].getPlayer().equals(opponent))
                        score -= 100;
                    else if (cells[i][j + 2].getPlayer() == null && cells[i][j + 3].getPlayer() == null) score -= 3;
                }

            }
        }
        //checking vertical
        for(int j = 0; j < colNum; j++) {
            for (int i = 0; i < rowNum - 3; i++) {
                if (cells[i][j].getPlayer().equals(player) && cells[i+1][j].getPlayer().equals(player)) {
                    if (cells[i+2][j].getPlayer().equals(player)) {
                        if (cells[i+3][j].getPlayer().equals(player)) score += 1000;
                        else if (cells[i+3][j].getPlayer() == null) score += 10;
                    } else if (cells[i+2][j].getPlayer() == null && cells[i+3][j].getPlayer().equals(player))
                        score += 10;
                    else if (cells[i+2][j].getPlayer() == null && cells[i+3][j].getPlayer() == null) score += 3;
                }
                if (cells[i][j].getPlayer().equals(opponent) && cells[i+1][j ].getPlayer().equals(opponent)) {
                    if (cells[i+2][j].getPlayer().equals(opponent)) {
                        if (cells[i+3][j].getPlayer() == null) {
                            if (cells[i+3][j].getPlayer().equals(opponent)) score -= 2000;
                            else score -= 50;}
                    } else if (cells[i+2][j].getPlayer() == null && cells[i+3][j].getPlayer().equals(opponent))
                        score -= 100;
                    else if (cells[i+2][j].getPlayer() == null && cells[i+3][j].getPlayer() == null) score -= 3;
                }
            }
        }
        //checking diagonal






        //Vertical 4



        //Diagonal 4
            for(int col = 3; col < colNum; col++) {
                for(int row=0; row<rowNum-3;row++ ){
                if (cells[row][col].getPlayer().equals(player) && cells[row+1][col-1].getPlayer().equals(player)) {
                    if (cells[row+2][col-2].getPlayer().equals(player)) {
                        if (cells[row+3][col-3].getPlayer().equals(player)) score += 1000;
                        else if (cells[row+3][col-3].getPlayer() == null) score += 10;
                    } else if (cells[row+2][col-2].getPlayer() == null && cells[row+3][col-3].getPlayer().equals(player))
                        score += 10;
                    else if (cells[row+2][col-2].getPlayer() == null && cells[row+3][col-3].getPlayer() == null) score += 3;
                }
                if (cells[row][col].getPlayer().equals(opponent) && cells[row+1][col-1].getPlayer().equals(opponent)) {
                    if (cells[row+2][col-2].getPlayer().equals(opponent)) {
                        if (cells[row+3][col-3].getPlayer().equals(opponent)) score -= 2000;
                        else if (cells[row+3][col-3].getPlayer() == null) score -= 50;
                    } else if (cells[row+2][col-2].getPlayer() == null && cells[row+3][col-3].getPlayer().equals(opponent))
                        score -= 10;
                    else if (cells[row+2][col-2].getPlayer() == null && cells[row+3][col-3].getPlayer() == null) score -= 3;
                }
            }
            }



                count=0;







        //System.out.println("If statement current i,j: " + i + "," + j + " count: " + count);
        //int[] start=new int[2];





        //Reverse diagonal

        count = 0;
        if(col>row) diff=row;
        else diff=col;

        c_start= col-diff;
        r_start=row-diff;
//        if(c_start<0) c_start=0;
//        if(r_start<0) r_start=0;


        while(c_start<colNum && r_start<rowNum ){
            //System.out.println("current row,col: " + r_start + "," + (c_start) + cells[r_start][c_start].getPlayer());

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