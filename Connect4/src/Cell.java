public class Cell {
    private Player player;

    public Cell(){
        player = null;
    }


    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public String toString() {
        if(player == null) return "-";
        else return player.getCircle();
    }
}
