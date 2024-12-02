package game;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.story();
        gameEngine.choose();
        gameEngine.startGame();
    }
}

interface Input {
    void story();
}

interface Select {
    void choose();
}

interface Type {
    void startGame();
}

class GameEngine implements Input, Select, Type {
    String name;
    int gameMode;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void story() {
        while (true) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }
    }

    public void choose() {
        System.out.println("Press 1 or 2 to select your game mode.");
        System.out.println("1 - Story");
        System.out.println("2 - Survival");

        while (true) {
            System.out.print("Your choice: ");
            String input = scanner.nextLine();

            try {
                gameMode = Integer.parseInt(input);
                if (gameMode == 1 || gameMode == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please select 1 (Story) or 2 (Survival).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value (1 or 2).");
            }
        }
    }

    public void startGame() {
        while (true) {
            System.out.println("\nPress P to start playing, " + name + ".");
            String startInput = scanner.nextLine();

            if (startInput.equalsIgnoreCase("P")) {
                if (gameMode == 1) {
                    System.out.println("Starting Story mode...");
                    playStoryMode();
                } else {
                    System.out.println("Starting Survival mode...");
                    playSurvivalMode();
                }
                break; 
            } else {
                System.out.println("Invalid input. Please press P to start or Ctrl+C to exit.");
            }
        }
    }

    private void playStoryMode() {
        Player player = new Player(name);
        StoryMode storyMode = new StoryMode();
        storyMode.start(player);
    }

    private void playSurvivalMode() {
        Player player = new Player(name);
        SurvivalMode survivalMode = new SurvivalMode();
        survivalMode.start(player);
    }
}

// Player Class
class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// GameMode Interface
interface GameMode {
    void start(Player player);
}

// StoryMode Class
class StoryMode implements GameMode {
    @Override
    public void start(Player player) {
        System.out.println(player.getName() + ", you find yourself in a ship...");
        System.out.println("After scanning the vicinity of your surroundings, you decided that you needed to escape...");
        System.out.print("\nAre you up for the challenge? (yes/no): ");

        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            continueStory(scanner);
        } else {
            System.out.println("You have chosen to exit the game. Goodbye!");
            return;
        }
    }

    private void continueStory(Scanner scanner) {
        System.out.println("\nYou find yourself in a dimly lit corridor. Two doors stand before you. Which do you choose?");
        System.out.println("A: Enter the door with a faint light.");
        System.out.println("B: Enter the dark door.");

        String choice1 = scanner.nextLine();

        if (choice1.equalsIgnoreCase("A")) {
            doorWithLight(scanner);
        } else {
            darkDoor(scanner);
        }
    }

    private void doorWithLight(Scanner scanner) {
        System.out.println("\nYou enter the room and see a scary creature lurking nearby. Do you sneak past it or find something to defend yourself?");
        System.out.println("A: Sneak past the creature.");
        System.out.println("B: Look for a weapon.");

        String choice2 = scanner.nextLine();

        if (choice2.equalsIgnoreCase("A")) {
            sneakPastCreature(scanner);
        } else {
            findWeapon(scanner);
        }
    }

    private void darkDoor(Scanner scanner) {
        System.out.println("\nYou enter a dark room and stepped on something loud. Do you sneak past the creature or find something to defend yourself?");
        System.out.println("A: Sneak past the creature.");
        System.out.println("B: Look for a weapon.");

        String choice2 = scanner.nextLine();

        if (choice2.equalsIgnoreCase("A")) {
            sneakPastCreature(scanner);
        } else {
            findWeapon(scanner);
        }
    }

    private void sneakPastCreature(Scanner scanner) {
        System.out.println("\nYou successfully sneak past the creature. You discover a map of the ship. Do you take the map or keep searching for supplies?");
        System.out.println("A: Take the map.");
        System.out.println("B: Search for supplies.");

        String choice3 = scanner.nextLine();

        if (choice3.equalsIgnoreCase("A")) {
            takeMap(scanner);
        } else {
            searchSupplies(scanner);
        }
    }

    private void findWeapon(Scanner scanner) {
        System.out.println("\nYou find a rusty pipe. Do you want to take it with you? (yes/no)");
        String takeWeapon = scanner.nextLine();

        if (takeWeapon.equalsIgnoreCase("yes")) {
            System.out.println("You now have a weapon!");
            sneakPastCreature(scanner);
        } else {
            System.out.println("You decide to leave the weapon behind.");
            sneakPastCreature(scanner);
        }
    }

    private void takeMap(Scanner scanner) {
        System.out.println("\nYou take the map. It shows a flooded chamber ahead. You reach a flooded chamber with a damaged boat. Do you repair the boat or look for another exit?");
        System.out.println("A: Repair the boat.");
        System.out.println("B: Search for another exit.");

        String choice4 = scanner.nextLine();

        if (choice4.equalsIgnoreCase("A")) {
            repairBoat(scanner);
        } else {
            searchExit(scanner);
        }
    }

    private void searchSupplies(Scanner scanner) {
        System.out.println("\nYou find some food and water. Do you want to take them? (yes/no)");
        String takeSupplies = scanner.nextLine();

        if (takeSupplies.equalsIgnoreCase("yes")) {
            System.out.println("You are now prepared with supplies!");
            takeMap(scanner);
        } else {
            System.out.println("You decide to keep searching.");
            takeMap(scanner);
        }
    }

    private void repairBoat(Scanner scanner) {
        System.out.println("\nYou repair the boat. Do you escape through a hatch or dive into the water?");
        System.out.println("A: Climb to the hatch.");
        System.out.println("B: Dive into the water.");

        String choice5 = scanner.nextLine();

        if (choice5.equalsIgnoreCase("A")) {
            System.out.println("\nYou escape through the hatch and find safety! You win!");
        } else {
            System.out.println("\nYou dive into the water but struggle to find your way.");
        }
    }

    private void searchExit(Scanner scanner) {
        System.out.println("\nYou search for another exit but find yourself trapped in the flooded chamber.");
    }
}

// SurvivalMode Class
class SurvivalMode implements GameMode {
    @Override
    public void start(Player player) {
        System.out.println(player.getName() + ", you find yourself on a deserted ship...");
        System.out.println("You must survive and find a way to escape!");

        int score = 0;

        score += firstChallenge();
        score += secondChallenge(score);
        score += thirdChallenge(score);
        score += fourthChallenge(score);
        score += fifthChallenge(score);

        if (score >= 60) {
            System.out.println("\nCongratulations! You have successfully escaped the ship!");
        } else {
            System.out.println("\nYou did not gather enough resources to survive. Game over.");
        }
    }

    private int firstChallenge() {
        System.out.println("\nYou see two paths ahead. Which do you choose?");
        System.out.println("A: Follow the path with the faint light.");
        System.out.println("B: Follow the dark path.");

        Scanner scanner = new Scanner(System.in);
        String choice1 = scanner.nextLine();

        if (choice1.equalsIgnoreCase("A")) {
            System.out.println("You find a stash of supplies!");
            return 15;
        } else {
            System.out.println("You find some old rations.");
            return 10;
        }
    }

    private int secondChallenge(int currentScore) {
        System.out.println("\nYou encounter a strange creature. What do you do?");
        System.out.println("A: Sneak past the creature.");
        System.out.println("B: Try to scare it away.");

        Scanner scanner = new Scanner(System.in);
        String choice2 = scanner.nextLine();

        if (choice2.equalsIgnoreCase("A")) {
            System.out.println("You successfully sneak past the creature!");
            return 15;
        } else {
            System.out.println("The creature is startled but doesn't leave. You manage to escape.");
            return 10;
        }
    }

    private int thirdChallenge(int currentScore) {
        System.out.println("\nYou find a stash of tools. Do you use them to secure your position?");
        System.out.println("A: Yes, secure.");
        System.out.println("B: No, keep moving.");

        Scanner scanner = new Scanner(System.in);
        String choice3 = scanner.nextLine();

        if (choice3.equalsIgnoreCase("A")) {
            System.out.println("You secured your position successfully!");
            return 15;
        } else {
            System.out.println("You decide to keep moving, but it's risky.");
            return 10;
        }
    }

    private int fourthChallenge(int currentScore) {
        System.out.println("\nYou come across a flooded chamber. What do you do?");
        System.out.println("A: Repair a damaged boat.");
        System.out.println("B: Search for another exit.");

        Scanner scanner = new Scanner(System.in);
        String choice4 = scanner.nextLine();

        if (choice4.equalsIgnoreCase("A")) {
            System.out.println("You repair the boat successfully!");
            return 15;
        } else {
            System.out.println("You find an exit, but it takes longer than expected.");
            return 10;
        }
    }

    private int fifthChallenge(int currentScore) {
        System.out.println("\nThe ship shakes violently. Do you escape through a hatch or dive into the water?");
        System.out.println("A: Climb to the hatch.");
        System.out.println("B: Dive into the water.");

        Scanner scanner = new Scanner(System.in);
        String choice5 = scanner.nextLine();

        if (choice5.equalsIgnoreCase("A")) {
            System.out.println("You escape through the hatch and find safety!");
            return 15;
        } else {
            System.out.println("You dive into the water but struggle to find your way.");
            return 10;
        }
    }
}