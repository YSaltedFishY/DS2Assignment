import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public Main() {
    }
    static void clearScreen(){
        System.out.print("\n".repeat(25));
        System.out.flush();
    }
    static void printMenuHome(){
        System.out.println("WELCOME TO\nCONNECT 4\n");
        System.out.println("select a game mode:\n" +
                "1. Player v Player\n" +
                "2.Player v AI\n" +
                "3.exit");
    }
    static void printMenuOptions(){
        System.out.println("Select a difficulty level:\n" +
                "1.Easy\n" +
                "2.Medium\n" +
                "3.Hard (might be slow)\n" +
                "4.Go back");
    }
    static int DifficultyLevel(String input){
        return switch (input) {
            case "1" -> 2;
            case "2" -> 4;
            case "3" -> 8;
            case "4" -> 0;
            default -> -1;
        };
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner obj= new Scanner(System.in);
        while(true) {
            clearScreen();
            printMenuHome();
            String input=obj.next().trim();
            if(input.matches("\\d")){
                clearScreen();
                if(input.equals("1")){  //1.P v P
                    System.out.print("Player 1 name: ");
                    String p1Name=obj.next();
                    System.out.print("\nPlayer 2 name: ");
                    String p2Name= obj.next();
                    Game game = new Game(p1Name,p2Name);
                    game.gameStart();
                }else if(input.equals("2")){    //2. P v AI
                    //difficulty option
                    int diff;

                    do {
                        printMenuOptions();
                        input = obj.next().trim();
                        diff = DifficultyLevel(input);
                    } while (diff == -1);
                    if(diff==0)continue;
                    System.out.print("Player 1 name: ");
                    String p1Name=obj.next().trim();
                    Game game = new Game(p1Name,diff);
                    game.gameStart();
                }else break;//3. exit
            }else{
                System.out.println("Enter a correct menu option");
                continue;
            }
            System.out.println("Do you wanna start over?");
            if(!obj.next().trim().equalsIgnoreCase("y")){
                break;
            }
        }
    }
}
