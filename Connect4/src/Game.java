import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Game {
    private Board gBoard;
    private Player human;
    private Player AIplayer;
    private Player current;
    private boolean gameOver;



    public Game(String p1, String p2){
        gBoard = new Board();
        human = new Player(p1,"P1");
        AIplayer = new Player(p2,"P2");
//        player1 = new AI_Player("p1","P1","weak");
//        player2 = new AI_Player("p2","P2","weak");
        current = human;
        gameOver = false;
    }

    public void gameStart() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while(!gameOver){
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
                col = current.CPUMove();
                Thread.sleep(300);
                if (col == -1) {
                    System.out.println("Something wrong with CPU input");
                    continue;
                }
            }

//            if(col == 7){
//                COPYBoard = new Board(gBoard.getNumMove(), gBoard.cells);
//                System.out.println("Display new board here: ");
//                COPYBoard.printBoard();
//                input = scanner.next();
//            }

            Move currentMove = new Move(col);

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
//        String[] alph={"A","B","C","D","E","F","G"};
//        col=col.toUpperCase();
//        for(int i =0;i<alph.length;i++){
//            if(col.equals(alph[i])) return i;
//        }
//        return -1;
        if(col.equalsIgnoreCase("a")){
            return 0;
        }
        if(col.equalsIgnoreCase("b")) {
            return 1;
        }
        if (col.equalsIgnoreCase("c")) {
            return 2;
        }
        if (col.equalsIgnoreCase("d")) {
            return 3;
        }
        if (col.equalsIgnoreCase("e")) {
            return 4;
        }
        if (col.equalsIgnoreCase("f")) {
            return 5;
        }
        if (col.equalsIgnoreCase("g")) {
            return 6;
        }

        return -1;

    }
    public int calculate_move(Move playerMove){
        return minimax(gBoard, 42-gBoard.getNumMove(), -999999,999999,playerMove, true)[0];
    }

    public int[] minimax(Board b, int depth, int alpha, int beta, Move m, boolean isMax ) {
        Player p;

        if (!isMax) p = human;
        else p = AIplayer;
        boolean is_won = b.winCheck(p, m);
        if (depth == 0 || is_won) {
            if(is_won) {
                if (p.equals(AIplayer)) return new int[]{-1, 999999};
                else if (p.equals(human)) return new int[]{-1, -999999};
            }
            else return new int[]{-1, 0};

        }
        if (isMax) {
            int value = -9999999;
            int column = 0;
            for (int col = 0; col < 7; col++) {
                Move currentMove = new Move(col);

                Board b_copy = new Board(b.cells);
                if (!b_copy.makeMove(currentMove, current)) {
                    continue;
                }
                int new_score = minimax(b_copy, depth - 1, alpha, beta, currentMove, false)[1];
                if (new_score > value) {
                    value = new_score;
                    column = col; //the col of the next best move

                }
                alpha = max(value, alpha);
                if (alpha >= beta) break;

            }
            return new int[]{column, value};

        }
        else {
            int value = 9999999;
            int column = 0;
            for (int col = 0; col < 7; col++) {
                Move currentMove = new Move(col);

                Board b_copy = new Board(b.cells);
                if (!b_copy.makeMove(currentMove, current)) {
                    continue;
                }
                int new_score = minimax(b_copy, depth - 1, alpha, beta, currentMove, true)[1];
                if (new_score < value) {
                    value = new_score;
                    column = col; //the col of the next best move

                }
                beta = min(value, beta);
                if (alpha >= beta) break;

            }
            return new int[]{column, value};

        }
    }



}