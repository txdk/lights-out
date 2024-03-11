package com.txdk.repository;

import java.util.Arrays;

public class GameRepository implements Repository {
    private boolean[][] gameState;

    public GameRepository(int boardSize)
    {
        this.gameState = new boolean[boardSize][boardSize];
    }

    public boolean[][] getGameState()
    {
        int boardSize = gameState.length;
        boolean[][] copyOfState = new boolean[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            copyOfState[i] = Arrays.copyOf(gameState[i], boardSize);
        }
        return copyOfState;
    }

    public void setGameState(boolean[][] state)
    {
        int boardSize = state.length;
        gameState = new boolean[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            gameState[i] = Arrays.copyOf(state[i], boardSize);
        }
    }

    public boolean getStateAtCoords(int row, int col)
    {
        return gameState[row][col];
    }

    public void setStateAtCoords(boolean value, int row, int col)
    {
        gameState[row][col] = value;
    }
}
