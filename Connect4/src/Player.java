public class Player {
    protected String name;
    protected pType player;
    protected String circle;
    protected int value;
    private int difficulty=0;

    //Console Text colour
    protected final String RED = "\033[0;31m";
    protected final String GREEN = "\033[0;32m";
    protected final String WHITE = "\u001B[0m";

    public Player(String name,String type){
        if(type.equals("P1")){
            this.name = GREEN+name+WHITE;
            this.player = pType.player1;
            this.value=1;
            circle = GREEN+"●"+WHITE;

        } else if (type.equals("P2")) {
            this.name = RED+name+WHITE;
            this.player = pType.player2;
            this.value=-1;
            circle = RED+"●"+WHITE;
        }else{
            this.player = pType.CPU;
            this.name = "CPU";
            this.value=-1;
            circle = RED+"●"+WHITE;
            this.difficulty=3;
        }
    }
    public Player(int difficulty) {
        this.name = "CPU";
        this.value=-1;
        this.player = pType.CPU;
        circle = RED + "●" + WHITE;
        this.difficulty = difficulty;
    }
    public String getName() {
        return name;
    }

    public pType getPlayer() {
        return player;
    }

    public String getCircle() {
        return circle;
    }
    public int getDifficulty(){
        return difficulty;
    }

    protected enum pType{
        player1, player2, CPU;
    }
}