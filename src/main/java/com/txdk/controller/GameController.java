package com.txdk.controller;

import com.txdk.service.Service;

public class GameController implements Controller {

    private Service gameService;
    
    public GameController(Service gameService)
    {
        this.gameService = gameService;
    }

    public boolean[][] getGameState()
    {
        return gameService.getGameState();
    }

    public boolean getStateFromIndex(int index)
    {
        return gameService.getStateFromIndex(index);
    }

    public void handleButtonPress(int index)
    {
        int row = gameService.getRowFromCellIndex(index);
        int col = gameService.getColumnFromCellIndex(index);
        gameService.clickCellAtCoords(row, col);
    }

    public void randomiseGameState()
    {
        gameService.randomiseGameState();
    }

    public boolean checkIfWinningState()
    {
        return gameService.checkIfWinningState();
    }
}
