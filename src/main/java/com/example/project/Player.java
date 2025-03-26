package com.example.project;

// DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

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
            setY(getY() + 1); // Moving up should increase the y-coordinate
        } else if (direction.equals("a")) {
            setX(getX() - 1);
        } else if (direction.equals("s")) {
            setY(getY() - 1); // Moving down should decrease the y-coordinate
        } else if (direction.equals("d")) {
            setX(getX() + 1);
        }
    }

    public void interact(int size, String direction, int numTreasures, Object obj) {
        if (obj instanceof Treasure) {
            treasureCount++;
            if (treasureCount == numTreasures) {
                win = true;
            }
        } else if (obj instanceof Enemy) {
            numLives--;
            if (numLives == 0) {
                // Handle game over
            }
        } else if (obj instanceof Trophy) {
            if (treasureCount == numTreasures) {
                win = true;
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

    @Override
    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }
}



