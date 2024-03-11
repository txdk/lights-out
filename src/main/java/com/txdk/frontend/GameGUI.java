package com.txdk.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.txdk.Constants;
import com.txdk.controller.Controller;
import com.txdk.files.FileReader;
import com.txdk.files.IconResizer;

public class GameGUI extends JFrame {
    
    private int selectedBoardSize;

    private JLabel titleLabel;
    private JLabel textLabel;
    private JButton newGameButton;
    private JSlider slider;
    private JPanel buttonPanel;
    private JPanel containerPanel;
    private JPanel utilityPanel;
    private ArrayList<JButton> buttonArray;

    private IconResizer iconResizer;
    private FileReader fileReader;

    private Controller gameController;
    
    public GameGUI(Controller gameController)
    {
        this.gameController = gameController;
        iconResizer = new IconResizer();
        fileReader = new FileReader();

        selectedBoardSize = Constants.INITIAL_BOARD_SIZE;
        
        this.setTitle("Lights Out!");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
    }

    public void addTitle()
    {
        titleLabel = new JLabel("Lights Out!");
        ImageIcon icon = new ImageIcon(getClass().getResource("/static/lightbulb.png"));
        icon = iconResizer.resize(icon, 100, 100);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setIcon(icon);
        titleLabel.setSize(300, 50);
        titleLabel.setVisible(true);
        containerPanel.add(titleLabel);
    }

    public void addText()
    {
        String text = fileReader.readTextFromFile("static/text.html");
        textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        textLabel.setSize(300, 50);
        textLabel.setVisible(true);
        containerPanel.add(textLabel);
    }

    public void createButtons(int boardSize)
    {
        buttonPanel = new JPanel();
        buttonArray = new ArrayList<JButton>();
        buttonPanel.setLayout(new GridLayout(boardSize, boardSize));
        int numButtons = boardSize * boardSize;

        for (int i = 0; i < numButtons; i++)
        {
            int index = i;
            JButton button = new JButton();
            button.addActionListener(event -> {
                gameController.handleButtonPress(index);
                syncGUItoGameState();
            });
            button.setFocusable(false);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setBorder(BorderFactory.createEtchedBorder());
            buttonPanel.add(button);
            buttonArray.add(button);
        }

        buttonPanel.setPreferredSize(new Dimension(500, 500));
        containerPanel.add(buttonPanel);
    }

    public void createNewGameButton()
    {
        newGameButton = new JButton();
        newGameButton.addActionListener(
            event -> {
                this.remove(containerPanel);
                initialiseUI();
                startGame(selectedBoardSize);
            });
        newGameButton.setText("New Game");
        newGameButton.setFocusable(false);
        utilityPanel.add(newGameButton);
    }

    public void createSlider()
    {
        slider = new JSlider(Constants.MIN_BOARD_SIZE, Constants.MAX_BOARD_SIZE);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.setValue(selectedBoardSize);
        slider.addChangeListener(event -> {selectedBoardSize = slider.getValue();});
        utilityPanel.add(slider);
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
        containerPanel.setSize(1000, 1000);
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
        createButtons(boardSize);
        this.getContentPane().add(containerPanel);
        this.setVisible(true);
        gameController.randomiseGameState();
        syncGUItoGameState();
    }

    public void displayWinScreen()
    {
        disableButtons();
        containerPanel.remove(titleLabel);

        // Edit text label to display a victory message
        ImageIcon winIcon = new ImageIcon(getClass().getResource("/static/celebrate.png"));
        winIcon = iconResizer.resize(winIcon, 100, 100);
        textLabel.setIcon(winIcon);
        textLabel.setText("You win!");
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
    }
}
