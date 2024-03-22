public class Player {
    private String name;
    private pType player;
    private String circle;

    //Console Text colour
    private final String RED = "\033[0;31m";
    private final String GREEN = "\033[0;32m";
    private final String WHITE = "\u001B[0m";

    public Player(String name,String type){
        if(type.equals("P1")){
            this.name = GREEN+name+WHITE;
            this.player = pType.player1;
            circle = GREEN+"●"+WHITE;
        } else if (type.equals("P2")) {
            this.name = RED+name+WHITE;
            this.player = pType.player2;
            circle = RED+"●"+WHITE;
        }else{
            this.player = pType.CPU;
            this.name = RED+"CPU"+WHITE;
            circle = RED+"●"+WHITE;
        }
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

    private enum pType{
        player1, player2, CPU;
    }
}
