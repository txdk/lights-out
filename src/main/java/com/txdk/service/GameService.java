package com.txdk.service;

import com.txdk.repository.Repository;

public class GameService implements Service {
    
    private Repository gameRepository;

    public GameService(Repository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public boolean[][] getGameState()
    {
        return gameRepository.getGameState();
    }

    public boolean getStateFromIndex(int index)
    {
        int row = getRowFromCellIndex(index);
        int col = getColumnFromCellIndex(index);
        return gameRepository.getStateAtCoords(row, col);
    }

    public void toggleCellAtCoords(int row, int col)
    {
        boolean stateAtCoords = gameRepository.getStateAtCoords(row, col);
        gameRepository.setStateAtCoords(!stateAtCoords, row, col);
    }

    public void toggleNeighboursAtCoords(int row, int col)
    {
        int boardSize = this.getGameState().length;

        int[] xDirectionVector = {1, -1, 0, 0};
        int[] yDirectionVector = {0, 0, 1, -1};

        for (int i = 0; i < xDirectionVector.length; i++) {
            int adjacentRow = row + xDirectionVector[i];
            int adjacentCol = col + yDirectionVector[i];
            
            if (adjacentRow < 0 | adjacentRow >= boardSize) continue;
            if (adjacentCol < 0 | adjacentCol >= boardSize) continue;
            toggleCellAtCoords(adjacentRow, adjacentCol);
        }
    }

    public void clickCellAtCoords(int row, int col)
    {
        toggleCellAtCoords(row, col);
        toggleNeighboursAtCoords(row, col);
    }

    public boolean checkIfWinningState()
    {
        for (boolean[] gameBoardRow : getGameState()) {
            for (boolean cellState : gameBoardRow) if (!cellState) return false;
        }
        return true;
    }

    public int getRowFromCellIndex(int index)
    {
        int boardSize = this.getGameState().length;
        return index / boardSize;
    }

    public int getColumnFromCellIndex(int index)
    {
        int boardSize = this.getGameState().length;
        return index % boardSize;
    }

    public void randomiseGameState()
    {
        int numCells = this.getGameState().length * this.getGameState().length;
        for (int i = 0; i < numCells; i++) {
            double randomNumber = Math.random();
            if (randomNumber >= 0.5) clickCellAtCoords(getRowFromCellIndex(i), getColumnFromCellIndex(i)); 
        }
    }
}
