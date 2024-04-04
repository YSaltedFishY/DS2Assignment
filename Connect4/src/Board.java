public class Board {
    private Cell[][] cells;
    private int rowNum;
    private int colNum;
    private int numMove;
    private boolean gameOver;
    private int score;

    public Board(){
        numMove = 0;
        rowNum = 6;
        colNum = 7;
        gameOver = false;
        score = 0;
        cells = new Cell[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                cells[i][j] = new Cell();
            }
        }
    }

    public Board(int numMove, Cell[][] c){
        this.numMove = numMove;
        rowNum = 6;
        colNum = 7;
        gameOver = false;
        score = 0;
        cells = new Cell[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                cells[i][j] = new Cell();
                cells[i][j].setPlayer(c[i][j].getPlayer());
            }
        }
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public int getNumMove() {
        return numMove;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
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
//            System.out.print("| "+i+"\n");
            System.out.print("|\n");
        }
//        System.out.println("- 0 1 2 3 4 5 6 -");
        System.out.println("- A B C D E F G -");
    }

    public boolean makeMove(Move move,Player player){
        for(int row = rowNum - 1; row >= 0; row--){
            if(this.cells[row][move.getCol()].getPlayer() == null){
                this.cells[row][move.getCol()].setPlayer(player);
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

    //winCheck improved version
    public boolean winCheck(Player player,Player opponent,Move move){
        int oppScore = Opponentscore(opponent);
        int score = score(move,player);
        calculateScore(score,oppScore);
//        System.out.println("Current player: " + player.getCircle());
//        System.out.println("Opponent score: " + oppScore + "\n");

        int count = 0;
        int currRow = move.getRow();
        int currCol = move.getCol();

        //Horizontal 4
        for(int j = move.getCol(); j < colNum; j++){
            if(cells[move.getRow()][j].getPlayer() == player){
                count++;
//                System.out.println("Counting [" + j +"," + move.getRow() +"]");
            }else{
                break;
            }
            if(count == 4){
//                System.out.println("Horizontal win");

                gameOver = true;
                return true;
            }
        }
        if(count > 0){
            currCol = move.getCol()-1;
        }
        for(int j = currCol; j >= 0; j--){
            if(cells[move.getRow()][j].getPlayer() == player){
//                System.out.println("Counting [" + j +"," + move.getRow() +"]");
                count++;
            }else{
                break;
            }
            if(count == 4){
//                System.out.println("Horizontal win");

                gameOver = true;
                return true;
            }
        }

        count = 0;


        //Vertical 4
        for(int i = move.getRow(); i < rowNum; i++){
            if(cells[i][move.getCol()].getPlayer() == player){
//                System.out.println("Counting V [" + move.getCol() +"," + i +"]");
                count++;
            }else{
                break;
            }
            if(count == 4){
//                System.out.println("Vertical win");

                gameOver = true;
                return true;
            }
        }
        if(count > 0){
            currRow = move.getRow()-1;
        }
        for(int i = currRow; i >= 0; i--){
            if(cells[i][move.getCol()].getPlayer() == player){
//                System.out.println("Counting V [" + move.getCol() +"," + i +"]");
                count++;
            }else{
                break;
            }
            if(count == 4){
//                System.out.println("Vertical win");

                gameOver = true;
                return true;
            }
        }

        count = 0;


        //Diagonal 4
        currRow = move.getRow();
        currCol = move.getCol();
        if((move.getCol()+1) <= 6 && (move.getRow()-1) >= 0) {
//            System.out.println(cells[move.getRow()-1][move.getCol()+1].getPlayer() + "<-if player");
            if (cells[move.getRow()-1][move.getCol()+1].getPlayer() == player) {
                while (((currRow) >= 0 && (currCol) <= 6)) {
//                    System.out.println("while loop up");
//                    System.out.println(cells[currRow][currCol].getPlayer() == player);
//                    System.out.println("row: " + currRow +"col: " + currCol);
                    if (cells[currRow][currCol].getPlayer() == player) {
//                        System.out.println("Counting token in [" + currCol + "," + currRow+"]");
                        count++;

                        if(count == 4){
//                            System.out.println("Diagonal win!");
                            gameOver = true;
                            return true;
                        }
                    }else{
                        break;
                    }
                    currRow--;
                    currCol++;
                }
            }
        }
        if(count > 0) {
            currRow = move.getRow() + 1;
            currCol = move.getCol() - 1;
        }else{
            currRow = move.getRow();
            currCol = move.getCol();
        }
        if((move.getCol()-1) >= 0 && (move.getRow()+1) <= 5) {
            if (cells[move.getRow() + 1][move.getCol() - 1].getPlayer() == player) {
                while (((currRow) <= 5 && (currCol) >= 0)) {
//                    System.out.println(cells[currRow][currCol].getPlayer() == player);
                    if(currRow <= 5 && currCol >=0) {
                        if (cells[currRow][currCol].getPlayer() == player) {
//                            System.out.println("Counting token in [" + currCol + "," + currRow + "]");
                            count++;

                            if(count == 4){
//                                System.out.println("Diagonal win!");
                                gameOver = true;
                                return true;
                            }
                        }else{
                            break;
                        }
                    }
                    currRow++;
                    currCol--;
                }
            }
        }
//        System.out.println("Diagonal count " + count);

        count = 0;


        //Reverse diagonal
        currRow = move.getRow();
        currCol = move.getCol();
        if((move.getCol()-1) >= 0 && (move.getRow()-1) >= 0) {
//            System.out.println(cells[move.getRow()-1][move.getCol()+1].getPlayer() + "<-if player");
            if (cells[move.getRow()-1][move.getCol()-1].getPlayer() == player) {
                while (((currRow) >= 0 && (currCol) >= 0)) {
//                    System.out.println("while loop up");
//                    System.out.println(cells[currRow][currCol].getPlayer() == player);
//                    System.out.println("row: " + currRow +"col: " + currCol);
                    if (cells[currRow][currCol].getPlayer() == player) {
                        count++;
//                        System.out.println("Counting token in [" + currCol + "," + currRow+"]");
                        if(count == 4){
//                            System.out.println("Reverse Diagonal win!");
                            gameOver = true;
                            return true;
                        }
                    }else{
                        break;
                    }
                    currRow--;
                    currCol--;
                }
            }
        }
        if(count > 0) {
            currRow = move.getRow() + 1;
            currCol = move.getCol() + 1;
        }else{
            currRow = move.getRow();
            currCol = move.getCol();
        }
        if((move.getCol()+1) <= 6 && (move.getRow()+1) <= 5) {
            if (cells[move.getRow() + 1][move.getCol() + 1].getPlayer() == player) {
                while (((currRow) <= 5 && (currCol) <= 6)) {
                    if(currRow <= 5 && currCol <= 6) {
                        if (cells[currRow][currCol].getPlayer() == player) {
//                            System.out.println("Counting token in [" + currCol + "," + currRow + "]");
                            count++;

                            if(count == 4){
//                                System.out.println("Reverse Diagonal win!");
                                gameOver = true;
                                return true;
                            }
                        }else{
                            break;
                        }
                    }
                    currRow++;
                    currCol++;
//                    System.out.println("x["+currCol+","+currRow+"]y");
                }
            }
        }
//        System.out.println("Reverse Diagonal count " + count);


        return false;
    }

    public void calculateScore(int score,int oppScore){
        this.score = (score-oppScore);
    }

    private int score(Move move,Player player){
        int score = 0;

        //Check center for score
        if(move.getCol() == 3){
            score += 4;
        }

        //Horizontal
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum - 3; j++){
                //Check for line of 2
                if(cells[i][j].getPlayer() == player && cells[i][j+1].getPlayer() == player ){
                    if(cells[i][j+2].getPlayer() == null) {
                        score += 2;
                    }
                    //Checks the left side
                    if(j != 0 && cells[i][j-1].getPlayer() == null){
                        score += 2;
                    }
                    //Checks for third token
                    if(cells[i][j+2].getPlayer() == player){
                        //Horizontal win!
                        if(cells[i][j+3].getPlayer() == player) {
                            score += 1000;
                        }
                        if(cells[i][j+3].getPlayer() == null) {
                            score += 5;
                        }
                        //Checks the left side
                        if(j != 0 && cells[i][j-1].getPlayer() == null){
                            score += 5;
                        }
                    }
                }
            }
        }


        //Vertical
        for(int i = 0; i < colNum; i++){
            for(int j = rowNum-1; j > 2; j--){
                if(cells[j][i].getPlayer() == player  && cells[j-1][i].getPlayer() == player){
                    if(cells[j-2][i].getPlayer() == null) {
                        score += 2;
                    }
                    if(cells[j-2][i].getPlayer() == player){
                        //Vertical Connect 4
                        if(cells[j-3][i].getPlayer() == player){
                            score += 1000;
                        }
                        //Possible vertical connect 4 next move
                        if(cells[j-3][i].getPlayer() == null) {
                            score += 5;
                        }
                    }
                }
            }
        }



        //Diagonal
        for(int i = rowNum - 1; i > 3; i--){
            for(int j = 0; j < colNum - 3; j++){
                if(cells[i][j].getPlayer() == player  && cells[i-1][j+1].getPlayer() == player){
                    //line of 2 waiting to form line of 3
                    if(cells[i-2][j+2].getPlayer() == null && cells[i-1][j+2].getPlayer() != null) {
                        score += 2;
                    }
                    if(j != 0 && i != 5 && cells[i+1][j-1].getPlayer() == null){
                        if(i != 4 && cells[i+2][j-1].getPlayer() != null){
                            score += 2;
                        }
                        score +=2;
                    }

                    if(cells[i-2][j+2].getPlayer() == player){
                        //Connect 4 WIN!
                        if(cells[i-3][j+3].getPlayer() == player){
                            score += 1000;

                        }
                        //Not Connect 4 but possible win next move
                        if(cells[i-3][j+3].getPlayer() == null && cells[i-2][j+3].getPlayer() != null) {
                            score += 5;
                        }
                        //Possible connect 4 next turn
                        if(j != 0 && i != 5 && cells[i+1][j-1].getPlayer() == null){
                            if(i != 4 && cells[i+2][j-1].getPlayer() != null) {
                                score += 5;
                            }
                            score += 5;
                        }
                    }
                }
            }
        }


        //Reverse Diagonal
        for(int i = 0; i < rowNum-3; i++){
//            System.out.println("Reverse diagonal i: " + i);
            for(int j = 0; j < colNum-3; j++){
                if(cells[i][j].getPlayer() == player  && cells[i+1][j+1].getPlayer() == player ){
                    if(cells[i+2][j+2].getPlayer() == null && cells[i+3][j+2].getPlayer() != null) {
                        score += 2;
                    }
                    //Check left corner for possible line of 3
                    if(j != 0 && i != 0 && cells[i-1][j-1].getPlayer() == null && cells[i][j-1].getPlayer() != null){
                        score += 2;
                    }

                    //line of 3
                    if(cells[i+2][j+2].getPlayer() == player){
                        //Connect 4 WIN!
                        if(cells[i+3][j+3].getPlayer() == player){
                            score += 1000;
                        }
                        //Not Connect 4 but possible win next move

                        if(cells[i+3][j+3].getPlayer() == null) {
                            if(i != 2 && cells[i+4][j+3].getPlayer() != null) {
                                score += 5;
                            }
                            score += 5;
                        }
                        if(j != 0 && i != 0 && cells[i-1][j-1].getPlayer() == null && cells[i][j-1].getPlayer() != null){
                            score += 5;
                        }
                    }
                }
            }
        }
        if(score > 0) return score;
        return 0;
    }

    private int Opponentscore(Player player){
        int score = 0;
        //Horizontal
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum - 3; j++){
                //Check for line of 2
                if(cells[i][j].getPlayer() == player && cells[i][j+1].getPlayer() == player ){
                    if(cells[i][j+2].getPlayer() == null) {
                        score += 2;
                    }
                    //Checks the left side
                    if(j != 0 && cells[i][j-1].getPlayer() == null){
                        score += 2;
                    }
                    //Checks for third token
                    if(cells[i][j+2].getPlayer() == player){
                        //Horizontal win!
                        if(cells[i][j+3].getPlayer() == player) {
                            score += 10000;

                        }
                        //Possible win
                        if(cells[i][j+3].getPlayer() == null) {
                            score += 1000;

                        }
                        //Checks the left side
                        if(j != 0 && cells[i][j-1].getPlayer() == null){
                            score += 1000;

                        }
                    }
                }
            }
        }


        //Vertical
        for(int i = 0; i < colNum; i++){
            for(int j = rowNum-1; j > 2; j--){
                if(cells[j][i].getPlayer() == player  && cells[j-1][i].getPlayer() == player){
                    if(cells[j-2][i].getPlayer() == null) {
                        score += 2;
                    }
                    if(cells[j-2][i].getPlayer() == player){
                        //Vertical Connect 4
                        if(cells[j-3][i].getPlayer() == player){
                            score += 10000;

                        }
                        //Possible vertical connect 4 next move
                        if(cells[j-3][i].getPlayer() == null) {
                            score += 1000;

                        }
                    }
                }
            }
        }



        //Diagonal
        for(int i = rowNum - 1; i > 2; i--){
            for(int j = 0; j < colNum - 3; j++){
                if(cells[i][j].getPlayer() == player  && cells[i-1][j+1].getPlayer() == player){
                    //line of 2 waiting to form line of 3
                    if(cells[i-2][j+2].getPlayer() == null && cells[i-1][j+2].getPlayer() != null) {
                        score += 2;
                    }
                    if(j != 0 && i < 4 && cells[i+1][j-1].getPlayer() == null && cells[i+2][j-1].getPlayer() != null){
                        score +=2;
                    }

                    if(cells[i-2][j+2].getPlayer() == player){
                        //Connect 4 WIN!
                        if(cells[i-3][j+3].getPlayer() == player){
                            score += 10000;

                        }
                        //Not Connect 4 but possible win next move
                        if(cells[i-3][j+3].getPlayer() == null && cells[i-2][j+3].getPlayer() != null) {
                            score += 1000;

                        }
                        //Possible connect 4 next turn
                        if(j != 0 && i < 4 && cells[i+1][j-1].getPlayer() == null && cells[i+2][j-1].getPlayer() != null){
                            score += 1000;
                        }
                        if(j != 0 && i == 4 && cells[i+1][j-1].getPlayer() == null){
                            score += 1000;
                        }
                    }
                }
            }
        }


        //Reverse Diagonal
        for(int i = 0; i < rowNum-3; i++){
            for(int j = 0; j < colNum-3; j++){
                if(cells[i][j].getPlayer() == player  && cells[i+1][j+1].getPlayer() == player ){
                    if(cells[i+2][j+2].getPlayer() == null && cells[i+3][j+2].getPlayer() != null) {
                        score += 2;
                    }
                    //Check left corner for possible line of 3
                    if(j != 0 && i != 0 && cells[i-1][j-1].getPlayer() == null && cells[i][j-1].getPlayer() != null){
                        score += 2;
                    }

                    //line of 3
                    if(cells[i+2][j+2].getPlayer() == player){
                        //Connect 4 WIN!
                        if(cells[i+3][j+3].getPlayer() == player){
                            score += 10000;

                        }
                        //Not Connect 4 but possible win next move
                        if(cells[i+3][j+3].getPlayer() == null) {
                            if(i != 2 && cells[i+4][j+3].getPlayer() != null) {
                                score += 1000;
                            }
                            score += 1000;
                        }
                        if(j != 0 && i != 0 && cells[i-1][j-1].getPlayer() == null && cells[i][j-1].getPlayer() != null){
                            score += 1000;
                        }
                        if(j > 1 && i > 1 && cells[i-1][j-1].getPlayer() == null && cells[i-2][j-2].getPlayer() == player){
                            score += 1000;
                        }
                    }
                }
            }
        }
        if(score > 0) return score;
        return 0;
    }


}