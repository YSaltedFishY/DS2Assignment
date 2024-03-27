public class AI_Player extends Player {

<<<<<<< Updated upstream
    private static final int MAX_DEPTH = 4; 

    public AI_Player(String name, String type) {
        super(name, type);
    }

    public int chooseMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int col = 0; col < board.getColNum(); col++) {
            // check if top cells are empty
            if (board.getCell(0, col).getPlayer() == null) {
                // create a copy of the board 
                Board newBoard = board.other(); 
                // Make the move on the copy of the board
                newBoard.makeMove(new Move(col), this); 
                int score = minimax(newBoard, 0, false);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = col;
                }
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        if (depth == MAX_DEPTH) return 0; 

        if (board.winCheck(this, -1, -1)) {
            return isMaximizing ? 10 : -10;
        }

        if (board.isFull()){
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int col = 0; col < board.getColNum(); col++) {
                if (board.getCell(0, col).getPlayer() == null) {
                    Board newBoard = board.other();
                    newBoard.makeMove(new Move(col), this);
                    int score = minimax(newBoard, depth + 1, false);
                    bestScore = Math.max(bestScore, score);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            Player opponent = new Player("Opponent", "P2"); 
            for (int col = 0; col < board.getColNum(); col++) {
                if (board.getCell(0, col).getPlayer() == null) {
                    Board newBoard = board.other();
                    newBoard.makeMove(new Move(col), opponent);
                    int score = minimax(newBoard, depth + 1, true);
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }
=======
public class AI_Player extends Player{
    private String difficulty;
    private static final int MAX_DEPTH = 4;
    public AI_Player(String name, String type, String difficulty) {
        super(name, type);
        if(name.equals("p1")) {
            this.name = "CPU";
            this.player = pType.CPU;
            circle = GREEN + "●" + WHITE;
            this.difficulty = difficulty;
        }else{
            this.name = "CPU";
            this.player = pType.CPU;
            circle = RED + "●" + WHITE;
            this.difficulty = difficulty;
        }
    }

    @Override
    public int CPUMove(){
        if(difficulty.equals("weak")){
            return weakCPU();
        } 
        return -1;
    }

    public String getName(){
        return name;
    }

    public int weakCPU(){
        Random random = new Random();
        int randMove = random.nextInt(7);
        return randMove;
    }
    public int CPUMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int col = 0; col < board.getColNum(); col++) {
            // check if top cells are empty
            if (board.getCell(0, col).getPlayer() == null) {
                // create a copy of the board 
                Board newBoard = board.other(); 
                // Make the move on the copy of the board
                newBoard.makeMove(new Move(col), this); 
                int score = minimax(newBoard, 0, false);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = col;
                }
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        if (depth == MAX_DEPTH) return 0; 

        if (board.winCheck(this)) {
            return isMaximizing ? 10 : -10;
        }

        if (board.isFull()){
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int col = 0; col < board.getColNum(); col++) {
                if (board.getCell(0, col).getPlayer() == null) {
                    Board newBoard = board.other();
                    newBoard.makeMove(new Move(col), this);
                    int score = minimax(newBoard, depth + 1, false);
                    bestScore = Math.max(bestScore, score);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            Player opponent = new Player("Opponent", "P2"); 
            for (int col = 0; col < board.getColNum(); col++) {
                if (board.getCell(0, col).getPlayer() == null) {
                    Board newBoard = board.other();
                    newBoard.makeMove(new Move(col), opponent);
                    int score = minimax(newBoard, depth + 1, true);
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }

>>>>>>> Stashed changes
}
