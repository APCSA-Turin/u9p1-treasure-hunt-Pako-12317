package com.example.project;

// DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount; // treasures collected
    private int numLives;      // lives remaining
    private boolean win;       // win status

    public Player(int x, int y) {
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }

    public int getTreasureCount() { return treasureCount; }
    public int getLives() { return numLives; }
    public boolean getWin() { return win; }

    @Override
    public void move(String direction) {
        if (direction.equals("w")) {
            setY(getY() + 1); // Move up
        } else if (direction.equals("a")) {
            setX(getX() - 1); // Move left
        } else if (direction.equals("s")) {
            setY(getY() - 1); // Move down
        } else if (direction.equals("d")) {
            setX(getX() + 1); // Move right
        }
    }

    public void interact(int size, String direction, int numTreasures, Object obj) {
        if (obj instanceof Treasure && !(obj instanceof Trophy)) {
            treasureCount++; // Collect treasure
        } else if (obj instanceof Enemy) {
            numLives--; // Encounter enemy
        } else if (obj instanceof Trophy) {
            // Only allow interaction with the Trophy if all treasures are collected
            if (treasureCount >= numTreasures) {
                win = true;
            } else {
                System.out.println("You need to collect all treasures before interacting with the Trophy!");
            }
        }
    }

    public boolean isValid(int size, String direction) {
        if (direction.equals("w")) {
            return getY() < size - 1;
        } else if (direction.equals("a")) {
            return getX() > 0;
        } else if (direction.equals("s")) {
            return getY() > 0;
        } else if (direction.equals("d")) {
            return getX() < size - 1;
        } else {
            return false;
        }
    }

    public boolean isValid(int size, String direction, Object target, int numTreasures) {
        // Check if the move is within bounds
        if (!isValid(size, direction)) {
            return false;
        }

        // Check if the target is a Trophy and if all treasures are collected
        if (target instanceof Trophy && treasureCount < numTreasures) {
            System.out.println("You need to collect all treasures before interacting with the Trophy!");
            return false;
        }

        return true;
    }

    @Override
    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }
}