package com.example.project;

// DO NOT DELETE ANY METHODS BELOW
public class Grid {
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { // initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Dot(j, i);
            }
        }
    }

    public Sprite[][] getGrid() { return grid; }
    public int getSize() { return size; }

    public void placeSprite(Sprite s) {
        grid[s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) {
        // Clear the previous position before the move
        int oldX = s.getX();
        int oldY = s.getY();
        
        // Move according to direction (already done in Player.move(), this is just to track the old position)
        if (direction.equals("w")) {
            oldY--; // Previous y before moving up
        } else if (direction.equals("a")) {
            oldX++; // Previous x before moving left
        } else if (direction.equals("s")) {
            oldY++; // Previous y before moving down
        } else if (direction.equals("d")) {
            oldX--; // Previous x before moving right
        }
        
        // Place a Dot at the old position
        grid[oldY][oldX] = new Dot(oldX, oldY);
        
        // Place the sprite at its new position
        grid[s.getY()][s.getX()] = s;
    }

    public void display() { // print out the current grid to the screen
        for (int i = size - 1; i >= 0; i--) { // Start from top row (highest y)
            for (int j = 0; j < size; j++) {
                if (grid[i][j] instanceof Dot) {
                    System.out.print("⬜");
                } else if (grid[i][j] instanceof Enemy) {
                    System.out.print(" E");
                } else if (grid[i][j] instanceof Player) {
                    System.out.print(" P");
                } else if (grid[i][j] instanceof Trophy) {
                    System.out.print(" C");
                } else if (grid[i][j] instanceof Treasure) {
                    System.out.print(" T");
                }
            }
            System.out.println();
        }
    }

    public void gameover() { // use this method to display a loss
        System.out.println("Game Over! You have no more lives.");
    }

    public void win() { // use this method to display a win
        System.out.println("Congratulations! You collected all treasures and reached the trophy.");
    }
}