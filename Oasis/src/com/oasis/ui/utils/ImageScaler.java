package com.oasis.ui.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.OP_BRIGHTER;

public class ImageScaler {
    public static Image resizeImage(Image sourceImage, int size){
        BufferedImage sourceBufferedImage = SwingFXUtils.fromFXImage(sourceImage, null);
        BufferedImage resizedBufferedImage = Scalr.resize(sourceBufferedImage, Scalr.Method.QUALITY,
                60, OP_ANTIALIAS, OP_BRIGHTER, OP_BRIGHTER);
        Image resizedImage = SwingFXUtils.toFXImage(resizedBufferedImage, null);

        return resizedImage;
    }
}
