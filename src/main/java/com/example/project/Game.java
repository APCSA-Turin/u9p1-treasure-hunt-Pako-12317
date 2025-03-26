package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
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

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);

        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();

            String direction = scanner.nextLine();
            if (player.isValid(size, direction)) {
                grid.placeSprite(player, direction);
            }

            if (player.getLives() == 0) {
                grid.gameover();
                break;
            }

            if (player.getWin()) {
                grid.win();
                break;
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
        treasures = new Treasure[] { new Treasure(1, 2), new Treasure(7, 2) };
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