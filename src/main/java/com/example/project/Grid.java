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
                grid[i][j] = new Dot(i, j);
            }
        }
    }

    public Sprite[][] getGrid() { return grid; }
    public int getSize() { return size; }

    public void placeSprite(Sprite s) {
        grid[s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) {
        // Clear the previous position
        grid[s.getY()][s.getX()] = new Dot(s.getX(), s.getY());

        // Update the sprite's position
        if (direction.equals("w")) {
            s.setY(s.getY() + 1); // Move up
        } else if (direction.equals("a")) {
            s.setX(s.getX() - 1); // Move left
        } else if (direction.equals("s")) {
            s.setY(s.getY() - 1); // Move down
        } else if (direction.equals("d")) {
            s.setX(s.getX() + 1); // Move right
        }

        // Place the sprite in the new position
        placeSprite(s);
    }

    public void display() { // print out the current grid to the screen
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[j][i] instanceof Dot) {
                    System.out.print("⬜");
                } else if (grid[j][i] instanceof Enemy) {
                    System.out.print(" E");
                } else if (grid[j][i] instanceof Player) {
                    System.out.print(" P");
                } else if (grid[j][i] instanceof Trophy) {
                    System.out.print(" C");
                } else if (grid[j][i] instanceof Treasure) {
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