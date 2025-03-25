package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = new Dot();
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}

    public int getSize(){return size;}

    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[s.getX()-1][s.getY()-1] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        for (Sprite[] sprites : grid) {
            for (Sprite sprite : sprites) {
                if(sprite.getCoords().equals(s.getCoords())){
                    sprite.move(direction);
                    grid[sprite.getX()][sprite.getY()] = sprite;
                }
            }
        }
    }


    public void display() { //print out the current grid to the screen 
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                switch (grid[j][i].getClass().getName()) {
                    case "com.example.project.Dot":
                        System.out.print("⬜");
                        break;
                    case "com.example.project.Enemy":
                        System.out.print(" E");
                        break;
                    case "com.example.project.Player":
                        System.out.print(" P");
                        break;
                    case "com.example.project.Treasure":
                        System.out.print(" T");
                        break;
                    case "com.example.project.Trophy":
                        System.out.print(" C");
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
    }

    public void win(){ //use this method to display a win 
    }


}