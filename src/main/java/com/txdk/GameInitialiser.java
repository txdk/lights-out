package com.txdk;

import com.txdk.controller.Controller;
import com.txdk.controller.GameController;
import com.txdk.frontend.GameGUI;
import com.txdk.repository.GameRepository;
import com.txdk.repository.Repository;
import com.txdk.service.GameService;
import com.txdk.service.Service;

public class GameInitialiser {

    public void run()
    {
        int boardSize = Constants.INITIAL_BOARD_SIZE;
        Repository gameRepository = new GameRepository(boardSize);
        Service gameService = new GameService(gameRepository);
        Controller gameController = new GameController(gameService);
        GameGUI gui = new GameGUI(gameController);
        gui.initialiseUI();
        gui.startGame(boardSize);
    }
    
}
