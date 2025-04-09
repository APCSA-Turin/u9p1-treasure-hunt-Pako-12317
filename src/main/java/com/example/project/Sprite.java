package com.example.project;

public class Sprite {
    private int x, y; // x = column, y = row

    public Sprite(int x, int y) {
        this.x = x; // Column
        this.y = y; // Row
    }

    public int getX() { return x; } // Column
    public int getY() { return y; } // Row

    public void setX(int x) { this.x = x; } // Column
    public void setY(int y) { this.y = y; } // Row

    public String getCoords() { // returns the coordinates of the sprite -> "(row, column)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size) { // returns the row and column of the sprite -> "[row][col]"
        // Note: In the tests, this appears to invert the row number
        return "[" + (size - 1 - y) + "][" + x + "]";
    }

    public void move(String direction) { // Default behavior (can be overridden by subclasses)
        // Leave empty for now
    }

    public void interact() { // Default behavior (can be overridden by subclasses)
        // Leave empty for now
    }
}