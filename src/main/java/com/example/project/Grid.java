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

    public void placeSprite(Sprite s) { // place sprite in new spot
        grid[s.getX()][s.getY()] = s;
    }

    public void placeSprite(Sprite s, String direction) { // place sprite in a new spot based on direction
        int x = s.getX();
        int y = s.getY();
        grid[x][y] = new Dot(x, y); // Clear the current position
        s.move(direction);
        grid[s.getX()][s.getY()] = s;
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
                } else if (grid[j][i] instanceof Treasure) {
                    System.out.print(" T");
                } else if (grid[j][i] instanceof Trophy) {
                    System.out.print(" C");
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