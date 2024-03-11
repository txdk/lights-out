package com.txdk.controller;

public interface Controller {
    
    public boolean[][] getGameState();

    public void setBoardSize(int boardSize);

    public boolean getStateFromIndex(int index);
    
    public void handleButtonPress(int index);

    public void randomiseGameState();

    public boolean checkIfWinningState();
}
