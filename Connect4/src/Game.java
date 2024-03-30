import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    private Board gBoard;
    private Board COPYBoard;
    private Player player1;
    private Player player2;
    private Player current;
    private boolean gameOver;



    public Game(String p1, String p2){
        gBoard = new Board();
        player1 = new Player(p1,"P1");
        player2 = new Player(p2,"P2");
//        player1 = new AI_Player("p1","P1","weak");
//        player2 = new AI_Player("p2","P2","weak");
        current = player1;
        gameOver = false;
    }

    public void gameStart() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while(!gameOver){
            gBoard.printBoard();
            int col = 0;
            if(current == player1) {
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

            if(gBoard.makeMove(currentMove, current)[0]==-1){
                System.out.println("Unable to add more circles. Try a different column!");
                continue;
            }

            //Version 1
//            if(gBoard.winCheck(current)){
//                gBoard.printBoard();
//                System.out.println(current.getName() + " has won!");
//                break;
//            }

            if(gBoard.winCheck(current,currentMove.getRow(),currentMove.getCol())){
                gBoard.printBoard();
                if(current == player1) {
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

            if(current == player1){
                current = player2;
            }else{
                current = player1;
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



}