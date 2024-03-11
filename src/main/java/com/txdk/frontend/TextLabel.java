package com.txdk.frontend;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.txdk.Constants;
import com.txdk.files.FileReader;
import com.txdk.files.IconResizer;

public class TextLabel extends JLabel {

    public TextLabel(String textfilePath)
    {
        FileReader fileReader = new FileReader();
        String text = fileReader.readTextFromFile(textfilePath);
        this.setText(text);
        this.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, Constants.TEXT_FONT_SIZE));
        this.setSize(Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.setVisible(true);
    }

    public void displayWinText()
    {
        ImageIcon winIcon = new ImageIcon(getClass().getResource(Constants.PATH_TO_WIN_ICON));
        IconResizer iconResizer = new IconResizer();
        winIcon = iconResizer.resize(winIcon, Constants.ICON_WIDTH, Constants.ICON_HEIGHT);
        this.setIcon(winIcon);
        this.setText(Constants.WIN_TEXT);
        this.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, Constants.TITLE_FONT_SIZE));   
    }
    
}
