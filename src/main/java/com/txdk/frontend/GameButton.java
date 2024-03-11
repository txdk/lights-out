package com.txdk.frontend;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class GameButton extends JButton {
 
    public GameButton()
    {
        this.setFocusable(false);
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setBorder(BorderFactory.createEtchedBorder());
    }
}
