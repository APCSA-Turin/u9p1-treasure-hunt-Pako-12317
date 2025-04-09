package com.example.project;

// DO NOT DELETE ANY METHODS BELOW
public class Grid {
    private Sprite[][] grid;
    private int size;

    // Create a grid
    public Grid(int size) {
        this.size = size;
        grid = new Sprite[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Dot(j, i); // Fill spot with a Dot
            }
        }
    }

    // Get the grid array
    public Sprite[][] getGrid() {
        return grid;
    }

    // Place a sprite at its current coordinates
    public void placeSprite(Sprite s) {
        grid[size - 1 - s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) {
        int newX = s.getX();
        int newY = s.getY();
        int oldX = newX;
        int oldY = newY;

        if (direction.equals("w")) { // Update sprite's position after moving
            oldY = newY - 1;
        } else if (direction.equals("s")) {
            oldY = newY + 1;
        } else if (direction.equals("a")) {
            oldX = newX + 1;
        } else if (direction.equals("d")) {
            oldX = newX - 1;
        }

        if (oldX >= 0 && oldX < size && oldY >= 0 && oldY < size) {
            grid[size - 1 - oldY][oldX] = new Dot(oldX, oldY); // Clears the old spot
        }

        // Place sprite in new spot
        grid[size - 1 - newY][newX] = s;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Sprite sprite = grid[i][j];
                if (sprite instanceof Dot) {
                    System.out.print(". "); // Display a dot for empty spaces
                } else if (sprite instanceof Player) {
                    System.out.print("P "); // Display 'P' for the player
                } else if (sprite instanceof Enemy) {
                    System.out.print("E "); // Display 'E' for enemies
                } else if (sprite instanceof Trophy) {
                    System.out.print("C "); // Display 'C' for the trophy
                } else if (sprite instanceof Treasure) {
                    System.out.print("T "); // Display 'T' for treasures
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void gameover() { // Lose
        System.out.println("Game Over! You lost.");
    }

    public void win() { // Win
        System.out.println("Congratulations! You won!");
    }
}