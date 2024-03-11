package com.txdk.service;

public interface Service {

    public boolean[][] getGameState();

    public void setBoardSize(int boardSize);

    public boolean getStateFromIndex(int index);

    public void toggleCellAtCoords(int row, int col);

    public void toggleNeighboursAtCoords(int row, int col);

    public void clickCellAtCoords(int row, int col);

    public boolean checkIfWinningState();

    public int getRowFromCellIndex(int index);

    public int getColumnFromCellIndex(int index);

    public void randomiseGameState();

}
