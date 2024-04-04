import java.util.Scanner;

public class Menu {
    private boolean exitMenu;

    public Menu(){
        exitMenu = false;
    }

    public void ConsoleMenu() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        while(!exitMenu){
            System.out.println("-------GameStart-------");
            System.out.println("-------Connect 4-------");
            System.out.println("Please select game mode: ");
            System.out.println("1.Player vs Player ");
            System.out.println("2.Player vs CPU ");
            String option = input.nextLine().trim();

            if(option.equals("1")){
                System.out.println("You have selected Human vs Human!");
                String player1,player2;

                    while (true) {
                        System.out.println("Please enter your name for Player 1!");
                        player1 = input.nextLine().trim();
                        if (player1.isEmpty()) {
                            System.out.println("Player 1 name can not be empty please try again!\n");
                            continue;
                        }
                        System.out.println("Player 1: " + player1 +" has been successfully registered!");
                        break;
                    }



                    while (true) {
                        System.out.println("Please enter your name for Player 2!");
                        player2 = input.nextLine().trim();
                        if (player2.isEmpty()) {
                            System.out.println("Player 2 name can not be empty please try again!\n");
                            continue;
                        }
                        System.out.println("Player 1: " + player2 +" has been successfully registered!");
                        break;
                    }

                Game game = new Game(player1, player2);
                game.gameStart();
            }
            else if(option.equals("2")) {
                String choice;
                String name;
                String order;
                String difficulty;
                System.out.println("You have selected Human vs CPU!");
                while(true){
                    System.out.println("Please select the difficulty of the CPU!");
                    System.out.println("1.Weak");
                    System.out.println("2.Strong");
                    choice = input.next();
                    if(choice.equals("1")){
                        System.out.println("You have selected weak CPU!");
                        difficulty = "weak";
                    }else if(choice.equals("2")) {
                        System.out.println("You have selected strong CPU!");
                        difficulty = "strong";
                    }else{
                        System.out.println("You have not enter a valid option please enter 1 or 2");
                        continue;
                    }
                    break;
                }

                //Prevents it from having "Player name can not be empty please try again!\n" without entering anything
                input.nextLine();

                while (true) {
                    System.out.println("Please enter your name!");
                    name = input.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Player name can not be empty please try again!\n");
                        continue;
                    }
                    System.out.println("Player: " + name +" has been successfully registered!");
                    break;
                }

                while(true){
                    System.out.println("Do you wish to go first or second?");
                    System.out.println("1.Play as P1");
                    System.out.println("2.Play as P2");
                    order = input.nextLine().trim();
                    if(order.equals("1") || order.equals("2")){
                        break;
                    }else{
                        System.out.println("You have not enter a valid option please enter 1 or 2");
                    }
                }
                if(order.equals("1")){
                    System.out.println("You have selected to play first!");
                    Game game = new Game(name,difficulty,"P2");
                    game.gameStart();
                } else if(order.equals("2")) {
                    System.out.println("You have selected to play second!");
                    Game game = new Game(name,difficulty,"P1");
                    game.gameStart();
                }

            }else{
                System.out.println("Invalid option please enter 1 or 2");
                continue;
            }

            while(true) {
                System.out.println("Do you wish to play another game? (Y/N)");
                option = input.nextLine().trim();
                if (option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")) {
                    break;
                }else{
                    System.out.println("Invalid option please enter Y or N\n");
                }
            }

            if (option.equalsIgnoreCase("y")) {
                System.out.println("Starting a new game...");
            } else if (option.equalsIgnoreCase("n")) {
                System.out.println("Exiting connect 4....");
                exitMenu = true;
            }

        }

    }






}
