package com.txdk.frontend;

import javax.swing.JSlider;

import com.txdk.Constants;

public class Slider extends JSlider {
    public Slider(int previousValue)
    {
        super(Constants.MIN_BOARD_SIZE, Constants.MAX_BOARD_SIZE);
        this.setPaintTrack(true);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMajorTickSpacing(1);
        this.setSnapToTicks(true);
        this.setValue(previousValue);
    }
}
