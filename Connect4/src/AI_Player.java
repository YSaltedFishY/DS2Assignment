import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AI_Player extends Player{
    private String diffculty;
    private Player human;
    private Player AI;

    public AI_Player(String name, String type, String diffculty) {
        super(name, type);
        if(name.equals("p1")) {
            this.name = "CPU";
            this.player = pType.CPU;
            circle = GREEN + "●" + WHITE;
            this.diffculty = diffculty;
        }else{
            this.name = "CPU";
            this.player = pType.CPU;
            circle = RED + "●" + WHITE;
            this.diffculty = diffculty;
        }
    }

    @Override
    public int CPUMove(Move move,Board gboard,Player human,Player AI){
        if(diffculty.equals("weak")){
            return weakCPU();
        }else if(diffculty.equals("strong")){
            this.human = human;
            this.AI = AI;
            return minMaxAI(move,gboard);
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


    public int minMaxAI(Move move,Board gboard)
    {
        return minimax(gboard,8,Integer.MIN_VALUE,Integer.MAX_VALUE,move,false)[0];
    }


    public int[] minimax(Board b,int depth, int alpha,int beta, Move m, boolean isMax){

        Player p,o;
        if(!isMax){
            p = AI;o=human;
        }else{
            p= human;o = AI;
        }

        boolean is_won = b.winCheck(o,p,m);

        if(depth == 0 || is_won){
            if(is_won){
                if (o.getPlayer() == AI.getPlayer()) return new int[]{m.getCol(), -999999};
                else if (o.getPlayer() == human.getPlayer()) return new int[]{m.getCol(), 999999};
            }else{
                return new int[] {m.getCol(),b.getScore()};
            }
        }

        if(isMax){
            int value = Integer.MIN_VALUE;
            int column = 0;
            for(int col = 0; col < 7; col++){
                Move currentMove = new Move();
                currentMove.setCol(col);

                Board b_copy = new Board(b.getNumMove(),b.getCells());
                if(!b_copy.makeMove(currentMove,p)){
                    continue;
                }
                int new_score = minimax(b_copy,(depth-1),alpha,beta,currentMove,false)[1];
                if(new_score > value){
                    value = new_score;
                    column = col;

                }
                alpha = max(value,alpha);
                if(alpha>beta) break;
            }
            return new int[]{column,value};
        }else{
            int value = Integer.MAX_VALUE;
            int column = 0;
            for(int col = 0; col < 7; col++){
                Move currentMove = new Move();
                currentMove.setCol(col);

                Board b_copy = new Board(b.getNumMove(),b.getCells());
                if(!b_copy.makeMove(currentMove,p)){
                    continue;
                }
                int new_score = minimax(b_copy,(depth-1),alpha,beta,currentMove,true)[1];
                if(new_score < value){
                    value = new_score;
                    column = col;
                }
                beta = min(value,beta);
                if(alpha >= beta)break;
            }
            return new int[]{column,value};
        }

    }







}
