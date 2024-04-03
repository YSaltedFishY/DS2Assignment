import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Game {
    private Board gBoard;
    private Player human;
    private Player AIplayer;
    private Player current;

    public Game(String p1,int difficulty){
        //pve
        gBoard = new Board();
        human = new Player(p1,"P1");
        AIplayer = new Player(difficulty);

        current = human;
    }

    public Game(String p1, String p2){
        //pvp
        gBoard = new Board();
        human = new Player(p1,"P1");
        AIplayer = new Player(p2,"P2");
        current = human;
    }

    public void gameStart() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Move currentMove = null;

        while(true){
            gBoard.printBoard();
            int col = 0;
            if(current == human) {
                System.out.println("P1: " + current.getName() + "'s turn [token: " + current.getCircle() + "]");
            }else{
                System.out.println("P2: " + current.getName() + "'s turn [token: " + current.getCircle() + "]");
            }

            if(!current.getName().equals("CPU")) {
            System.out.print("Please enter the column you wish to insert: ");
                String input = scanner.next();
                col = checkCol(input);
                //System.out.println(col);
                if (col == -1) {
                    System.out.println("Invalid column. Try again.");
                    continue;
                }
            }else{
                //col = current.CPUMove(currentMove);
                col=calculate_move(currentMove);
                Thread.sleep(300);
                if (col == -1) {
                    System.out.println("Something wrong with CPU input");
                    continue;
                }
            }

             currentMove= new Move(col);

            if(!gBoard.makeMove(currentMove, current)){
                System.out.println("Unable to add more circles. Try a different column!");
                continue;
            }

            //Version 1
//            if(gBoard.winCheck(current)){
//                gBoard.printBoard();
//                System.out.println(current.getName() + " has won!");
//                break;
//            }

            if(gBoard.winCheck(current,currentMove)){
                gBoard.printBoard();
                if(current == human) {
                    System.out.println(current.getName() + " " + current.getCircle() + " P1 has won!");
                }else{
                    System.out.println(current.getName() + " " + current.getCircle() + " P2 has won!");
                }
                break;
            }

            if(gBoard.isFull()){
                System.out.println("The game ends in a draw!");
                break;
            }

            if(current == human){
                current = AIplayer;
            }else{
                current = human;
            }
        }

    }

    private int checkCol(String col){
        String[] alph={"A","B","C","D","E","F","G"};
        col=col.toUpperCase();
        for(int i =0;i<alph.length;i++){
            if(col.equals(alph[i])) return i;
        }
        return -1;

    }
    public int calculate_move(Move playerMove){
        //depth is set to 6 to make the code run fast enough
        return minimax(gBoard, AIplayer.getDifficulty(), -9999999,9999999,playerMove, false)[0];
    }

    public int[] minimax(Board b, int depth, int alpha, int beta, Move m, boolean isMax ) {

        Player p,o;
        //human is max, player is min
        if (!isMax) {p = AIplayer;o=human;}
        else {p = human;o=AIplayer;}
        boolean is_won = b.winCheck(o, m);

        //System.out.println("player is" +p.getName());
        if (depth == 0 || is_won) {
            if(is_won) {
                if (o.equals(AIplayer)) return new int[]{m.getCol(), -999999};
                else if (o.equals(human)) return new int[]{m.getCol(), 999999};
            }
            else{
                return new int[]{m.getCol(), b.boardScore(p)};

            }
        }
        if(isMax){
            int value = Integer.MIN_VALUE;
            int column = 0;
            for (int col = 0; col < 7; col++) {
                Move currentMove = new Move(col);

                Board b_copy = b.other();
                if (!b_copy.makeMove(currentMove, p)) {
                    continue;
                }
                int new_score = minimax(b_copy, depth - 1, alpha, beta, currentMove, false)[1];
                if (new_score > value) {
                    value = new_score;
                    column = col; //the col of the next best move
                    //System.out.println("Next winning column for "+p.getName()+" is "+col);


                }
                alpha = max(value, alpha);
                if (alpha >= beta) break;

            }
            return new int[]{column, value};

        }
        else {
            int value = Integer.MAX_VALUE;
            int column = 0;
            for (int col = 0; col < 7; col++) {
                Move currentMove = new Move(col);

                Board b_copy = new Board(b.cells);
                if (!b_copy.makeMove(currentMove, p)) {
                    continue;
                }
                int new_score = minimax(b_copy, depth - 1, alpha, beta, currentMove, true)[1];
                if (new_score < value) {
                    value = new_score;
                    column = col; //the col of the next best move
                    //System.out.println("Next winning column for "+p.getName()+" is "+col);

                }
                beta = min(value, beta);
                if (alpha >= beta) break;

            }
            return new int[]{column, value};

        }
    }



}