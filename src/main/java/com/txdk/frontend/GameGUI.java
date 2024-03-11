package com.txdk.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.txdk.Constants;
import com.txdk.controller.Controller;

public class GameGUI extends JFrame {
    
    private int selectedBoardSize;

    private JLabel titleLabel;
    private TextLabel textLabel;
    private JButton newGameButton;
    private JSlider slider;
    private JPanel buttonPanel;
    private JPanel containerPanel;
    private JPanel utilityPanel;
    private ArrayList<JButton> buttonArray;

    private Controller gameController;
    
    public GameGUI(Controller gameController)
    {
        this.gameController = gameController;

        selectedBoardSize = Constants.INITIAL_BOARD_SIZE;
        
        this.setTitle(Constants.APP_NAME);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.GUI_WIDTH, Constants.GUI_HEIGHT);
    }

    public void addTitle()
    {
        titleLabel = new TitleLabel(Constants.APP_NAME);
        containerPanel.add(titleLabel);
    }

    public void addText()
    {
        textLabel = new TextLabel(Constants.PATH_TO_DISPLAY_TEXT);
        containerPanel.add(textLabel);
    }

    public void createSlider()
    {
        slider = new Slider(selectedBoardSize);
        slider.addChangeListener(event -> {selectedBoardSize = slider.getValue();});
        utilityPanel.add(slider);
    }

    public void createButtonPanel(int boardSize)
    {
        buttonPanel = new JPanel();
        buttonArray = new ArrayList<JButton>();
        buttonPanel.setLayout(new GridLayout(boardSize, boardSize));
        int numButtons = boardSize * boardSize;

        for (int i = 0; i < numButtons; i++)
        {
            int index = i;
            JButton button = new GameButton();
            button.addActionListener(event -> {
                gameController.handleButtonPress(index);
                syncGUItoGameState();
            });
            buttonPanel.add(button);
            buttonArray.add(button);
        }

        buttonPanel.setPreferredSize(new Dimension(Constants.BUTTON_PANEL_WIDTH, Constants.BUTTON_PANEL_HEIGHT));
        containerPanel.add(buttonPanel);
    }

    public void createNewGameButton()
    {
        newGameButton = new JButton(Constants.NEW_GAME_TEXT);
        newGameButton.addActionListener(
            event -> {
                this.remove(containerPanel);
                initialiseUI();
                startGame(selectedBoardSize);
            });
        newGameButton.setFocusable(false);
        utilityPanel.add(newGameButton);
    }

    public Color assignColor(boolean state)
    {
        if (state) return Color.green;
        return Color.red;
    }

    public void syncGUItoGameState()
    {
        for (int i = 0; i < buttonArray.size(); i++)
        {
            boolean state = gameController.getStateFromIndex(i);
            buttonArray.get(i).setBackground(assignColor(state));
        }
        if (gameController.checkIfWinningState()) displayWinScreen();
    }

    public void disableButtons()
    {
        for (JButton button : buttonArray)
        {
            button.setEnabled(false);
        }
    }

    public void initialiseUI()
    {
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setSize(Constants.CONTAINER_PANEL_WIDTH, Constants.CONTAINER_PANEL_HEIGHT);
        addTitle();
        addText();
        utilityPanel = new JPanel();
        createNewGameButton();
        createSlider();
        containerPanel.add(utilityPanel);
    }

    public void startGame(int boardSize)
    {
        gameController.setBoardSize(boardSize);
        createButtonPanel(boardSize);
        this.getContentPane().add(containerPanel);
        this.setVisible(true);
        gameController.randomiseGameState();
        syncGUItoGameState();
    }

    public void displayWinScreen()
    {
        disableButtons();
        containerPanel.remove(titleLabel);
        textLabel.displayWinText();
    }
}
