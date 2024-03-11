package com.txdk;

import com.txdk.controller.Controller;
import com.txdk.controller.GameController;
import com.txdk.frontend.GameGUI;
import com.txdk.repository.GameRepository;
import com.txdk.repository.Repository;
import com.txdk.service.GameService;
import com.txdk.service.Service;

public class GameInitialiser {
    private int boardSize;

    public GameInitialiser(int boardSize)
    {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void run()
    {
        Repository gameRepository = new GameRepository(boardSize);
        Service gameService = new GameService(gameRepository);
        Controller gameController = new GameController(gameService);
        GameGUI gui = new GameGUI(gameController);
        gui.initialiseUI();
        gui.startGame(boardSize);
    }
    
}
