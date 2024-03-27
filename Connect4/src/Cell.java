public class Cell {
    private Player player;

    // constructor
    public Cell(){
        player = null;
    }

 // Set the player
    public void setPlayer(Player player){
        this.player = player;
    }
 // Get the player 
    public Player getPlayer(){
        return player;
    }

    // Print the player 
    @Override
    public String toString() {
        if(player == null) return "-";
        else return player.getCircle();
    }
}
