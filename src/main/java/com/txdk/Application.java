package com.txdk;

public class Application {
    public static void main(String[] args) {
        int boardSize = 3;
        GameInitialiser gameInitialiser = new GameInitialiser(boardSize);
        gameInitialiser.run();
    }
}