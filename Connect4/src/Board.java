import java.util.Hashtable;



public class Board {
    int[][] cells;
    private final int rowNum;
    private final int colNum;
    private int numMove;
    static Hashtable<Integer, String> circles;
    static{
        circles=new Hashtable<Integer, String>();
        circles.put(1,"\033[0;32m●\u001B[0m");
        circles.put(-1,"\033[0;31m●\u001B[0m");
        circles.put(0,"_");


    }

    public Board(){
        numMove = 0;
        rowNum = 6;
        colNum = 7;
        cells = new int[rowNum][colNum];
//        for(int i = 0; i < rowNum; i++){
//            for(int j = 0; j < colNum; j++){
//                cells[i][j] = 0;
//            }
//        }
    }
    public Board(int[][] pieces){
        numMove = 0;
        rowNum = 6;
        colNum = 7;
        cells = new int[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                cells[i][j] = pieces[i][j];
                if(pieces[i][j]!=0){
                    numMove++;
                }
            }
        }
    }

    public int getNumMove() {
        return numMove;
    }

    public Board(int numMove, int[][] c){
        this.numMove = numMove;
        rowNum = 6;
        colNum = 7;
        cells = new int[rowNum][colNum];
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
                System.out.print(circles.get(cells[i][j]) + " ");
            }
            System.out.print("| "+i+"\n");
//            System.out.print("|\n");
        }
        System.out.println("- 0 1 2 3 4 5 6 -");
        System.out.println("- A B C D E F G -");
    }

    public boolean makeMove(Move move,Player player){
        for(int row = rowNum - 1; row >= 0; row--){
            if(cells[row][move.getCol()] == 0){
                cells[row][move.getCol()]=player.value;
                move.setRow(row);
                numMove++;
                return true;
            }
        }
        return false;
    }

    public boolean isFull(){
        return numMove == rowNum * colNum;
    }

    public boolean winCheck(Player player, Move move){
        int count;
        int row=move.getRow();
        int col=move.getCol();

        //Horizontal 4
        for(int j = 0; j < colNum-3; j++) {
            if (cells[row][j]+cells[row][j+1]+cells[row][j+2]+cells[row][j+3]== player.value*4) {
                return true;
            }
        }
        //Vertical 4 optimizable
        //System.out.println(move.getRow());

            for(int j = move.getRow(); j < rowNum-3; j++){
                if (cells[j][col]+cells[j+1][col]+cells[j+2][col]+cells[j+3][col]== player.value*4) {
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

            if (cells[r_start][c_start] == player.value) {
                count++;
                //System.out.println("current row,col: " + r_start + "," + (c_start) + " count: " + count + cells[r_start][c_start].getPlayer().getName());
                if (count == 4) {
                    //System.out.println("Diagonal win");
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
                //System.out.println("current row,col: " + r_start + "," + (c_start) + cells[r_start][c_start]);

                if(cells[r_start][c_start] == player.value) {
                    count++;

                    if(count == 4){
                        //System.out.println("Reverse Diagonal win");
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
    public int boardScore(Player player){

        int score = 0;

        //checking horizontal
        for(int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum - 3; j++) {
                //int[] window= Arrays.copyOfRange(cells[i],i, i+3);
                int sum=cells[i][j]+cells[i][j+1]+cells[i][j+2]+cells[i][j+3];
                //sum=sum*player.value;
                //
                //System.out.println("horizontal score"+sum);

                if (sum >= 3) {
                    score += 5000
                    ;} else if(sum==2){
                    score+=1000;
                };
                if(player.value==-1) sum=-sum;
                if(cells[i][j]!=1 && cells[i][j+1]!=1 && cells[i][j+2]!=1&& cells[i][j+3]!=1)
                    score-= (int) Math.pow(10,sum);
                if(cells[i][j]!=-1 && cells[i][j+1]!=-1 && cells[i][j+2]!=-1&& cells[i][j+3]!=-1)
                    score+= (int) Math.pow(10,sum);


            }
        }
        //checking vertical
        for(int j = 0; j < colNum; j++) {
            for (int i = 0; i < rowNum - 3; i++) {
                int sum=cells[i][j]+cells[i+1][j]+cells[i+2][j]+cells[i+3][j];
                ///sum=sum*player.value;
                //System.out.println("Sum"+sum);
                //System.out.println("vertical score"+sum);

                if (sum >= 3) {
                    score += 5000
                    ;} else if(sum==2){
                    score+=1000;
                };
                if(player.value==-1) sum=-sum;
                if(cells[i][j]!=1 && cells[i+1][j]!=1 && cells[i+2][j]!=1&& cells[i+3][j]!=1)
                    score-= (int) Math.pow(10,sum);
                if(cells[i][j]!=-1 && cells[i+1][j]!=-1 && cells[i+2][j]!=-1&& cells[i+3][j]!=-1)
                    score+= (int) Math.pow(10,sum);
            }
        }

        //Diagonal 4
        //diagonials starting from top row
        for(int col = 3; col < colNum; col++) {
            for (int row = 0; row < rowNum - 3; row++) {
                int sum = cells[row][col] + cells[row + 1][col - 1] + cells[row + 2][col - 2] + cells[row + 3][col - 3];
               // if(player.value==-1) sum=-sum;
                if (sum >= 3) {
                    score += 5000
                ;} else if(sum==2){
                    score+=1000;
                };
                if(player.value==-1) sum=-sum;
                if(cells[row][col]!=1 && cells[row+1][col-1]!=1 && cells[row+2][col-2]!=1&& cells[row+3][col-3]!=1)
                    score -= (int) Math.pow(10, sum);
                if(cells[row][col]!=-1 && cells[row+1][col-1]!=-1 && cells[row+2][col-2]!=-1&& cells[row+3][col-3]!=-1)
                    score += (int) Math.pow(10, sum);
            }
        }



        for(int col = 0; col < colNum-3; col++) {
            for(int row=0; row<rowNum-3;row++ ){
                int sum = cells[row][col] + cells[row + 1][col + 1] + cells[row + 2][col + 2] + cells[row + 3][col + 3];
                //if(player.value==-1) sum=-sum;
                if (sum >= 3) {
                    score += 5000
                    ;} else if(sum==2){
                    score+=1000;
                };
                if(player.value==-1) sum=-sum;
                if(cells[row][col]!=1 && cells[row+1][col+1]!=1 && cells[row+2][col+2]!=1&& cells[row+3][col+3]!=1)
                    score -= (int) Math.pow(10, sum);
                if(cells[row][col]!=-1 && cells[row+1][col+1]!=-1 && cells[row+2][col+2]!=-1&& cells[row+3][col+3]!=-1)
                    score += (int) Math.pow(10, sum);

            }
        }

        //System.out.println("Total score "+score);
        return score;


    }
}

