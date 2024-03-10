package com.txdk.repository;

public interface Repository {

    public boolean[][] getGameState();

    public void setGameState(boolean[][] state);

    public boolean getStateAtCoords(int row, int col);
    
    public void setStateAtCoords(boolean value, int row, int col);
}
