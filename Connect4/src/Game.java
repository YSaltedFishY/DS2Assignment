import java.util.Scanner;

public class Game {
    private Board gBoard;
    private Board COPYBoard;
    private Player player1;
    private Player player2;
    private Player AI_Player;
    private Player current;
    private boolean gameOver;



    public Game(String p1, String p2){
        gBoard = new Board();
        player1 = new Player(p1,"P1");
        player2 = new Player(p2,"P2");
        current = player1;
        gameOver = false;
    }

   


    public void gameStart(){
        Scanner scanner = new Scanner(System.in);

        while(!gameOver){
            // Display the board
            gBoard.printBoard();
            System.out.println(current.getName() + "'s turn [token: " + current.getCircle() + "]");
            System.out.print("Please enter the column you wish to insert: ");
            String input = scanner.next();
            int col = checkCol(input);
            //System.out.println(col);
            if(col == -1){
                System.out.println("Invalid column. Try again.");
                continue;
            }

//            if(col == 7){
//                COPYBoard = new Board(gBoard.getNumMove(), gBoard.cells);
//                System.out.println("Display new board here: ");
//                COPYBoard.printBoard();
//                input = scanner.next();
//            }
            int[] pos=gBoard.makeMove(new Move(col), current);
            if(pos[0]==-1){
                System.out.println("Unable to add more circles. Try a different column!");
                continue;
            }

            if(gBoard.winCheck(current, pos[0], pos[1])){
                gBoard.printBoard();
                System.out.println(current.getName() + " has won!");
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
        String[] alph={"A","B","C","D","E","F","G"};
        col=col.toUpperCase();
        for(int i =0;i<alph.length;i++){
            if(col.equals(alph[i])) return i;
        }
        return -1;
    }



}
