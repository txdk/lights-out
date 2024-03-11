package com.txdk.frontend;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.txdk.Constants;
import com.txdk.files.IconResizer;

public class TitleLabel extends JLabel {

    public TitleLabel(String defaultText)
    {
        super(defaultText);
        
        IconResizer iconResizer = new IconResizer();
        ImageIcon icon = new ImageIcon(getClass().getResource(Constants.PATH_TO_TITLE_ICON));
        icon = iconResizer.resize(icon, Constants.ICON_WIDTH, Constants.ICON_HEIGHT);

        this.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, Constants.TITLE_FONT_SIZE));
        this.setIcon(icon);
        this.setSize(Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.setVisible(true);
    }
}
