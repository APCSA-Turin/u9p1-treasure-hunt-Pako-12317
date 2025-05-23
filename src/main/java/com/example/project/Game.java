package com.example.project;
import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size;

    public Game(int size) { // The constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { // Do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            grid.display();

            System.out.println("Enter your move (w/a/s/d) or 'q' to quit: ");
            String direction = scanner.nextLine();

            // Check if the player wants to quit
            if (direction.equals("q")) {
                System.out.println("You quit the game. Goodbye!");
                break;
            }

            // Get the target object based on the player's intended move
            int targetX = player.getX();
            int targetY = player.getY();
            if (direction.equals("w")) targetY++;
            if (direction.equals("a")) targetX--;
            if (direction.equals("s")) targetY--;
            if (direction.equals("d")) targetX++;

            // Ensure the target is within bounds
            if (targetX < 0 || targetX >= size || targetY < 0 || targetY >= size) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            Object target = grid.getGrid()[targetY][targetX];

            // Check if the move is valid
            if (player.isValid(size, direction, target, treasures.length)) {
                Object originalTarget = target; // Store the target before moving
                player.move(direction);
                grid.placeSprite(player, direction);
                player.interact(size, direction, treasures.length, originalTarget);

                // Check for win or loss
                if (player.getWin()) {
                    clearScreen();
                    grid.display();
                    System.out.println("Congratulations! You collected all treasures and reached the trophy!");
                    break;
                } else if (player.getLives() <= 0) {
                    clearScreen();
                    grid.display();
                    System.out.println("Game Over! You lost all your lives.");
                    break;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    public void initialize() {
        grid = new Grid(size);
        player = new Player(0, 0);
        grid.placeSprite(player);

        // Initialize enemies, treasures, and trophy
        enemies = new Enemy[] { new Enemy(8, 8), new Enemy(5, 5) };
        treasures = new Treasure[] { new Treasure(2, 2), new Treasure(7, 2) };
        trophy = new Trophy(9, 9);

        for (Enemy enemy : enemies) {
            grid.placeSprite(enemy);
        }

        for (Treasure treasure : treasures) {
            grid.placeSprite(treasure);
        }

        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        new Game(10);
    }
}