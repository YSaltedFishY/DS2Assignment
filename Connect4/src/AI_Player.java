import java.util.Random;

public class AI_Player extends Player{
    private String diffculty;
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
    public int CPUMove(){
        if(diffculty.equals("weak")){
            return weakCPU();
        }
        return -1;
    }

//    public int CPUMove(Move move){
//        return game.calculateMove(move);
//        //return -1;
//    }

    public String getName(){
        return name;
    }

    public int weakCPU(){
        Random random = new Random();
        int randMove = random.nextInt(7);
        return randMove;
    }

}
