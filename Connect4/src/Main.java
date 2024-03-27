// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
       AI_Player ai = new AI_Player("AI", "P2");
       Player humanPlayer = new Player("Human", "P1");
       Game game = new Game(humanPlayer, ai);
         game.gameStart();
        

<<<<<<< Updated upstream
=======
    public static void main(String[] args) throws InterruptedException {
        
        Game game = new Game("p1", "p2");
        game.gameStart();
>>>>>>> Stashed changes
    }
}