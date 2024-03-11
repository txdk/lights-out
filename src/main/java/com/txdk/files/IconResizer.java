package com.txdk.files;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconResizer {
    public ImageIcon resize(ImageIcon icon, int width, int height)
    {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
