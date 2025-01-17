package software.ulpgc.mvc.application;

import software.ulpgc.mvc.model.Image;
import software.ulpgc.mvc.view.ImageDisplay;
import software.ulpgc.mvc.view.ViewPort;

import javax.swing.*;
import java.awt.*;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private final SwingImageDeserializer deserializer;

    public SwingImageDisplay(SwingImageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (image != null) {
            paintImage(graphics);
        }
    }

    private void paintImage(Graphics graphics) {
        java.awt.Image awtImage = deserialize();
        ViewPort viewPort = ViewPort.ofSize(getWidth(), getHeight())
                .fit(awtImage.getWidth(null), awtImage.getHeight(null));
        graphics.drawImage(
                awtImage,
                viewPort.x(),
                viewPort.y(),
                viewPort.width(),
                viewPort.height(),
                this
        );
    }

    private java.awt.Image deserialize() {
        Object deserializedImage = deserializer.deserialize(image.content());
        if (!(deserializedImage instanceof java.awt.Image)) {
            throw new RuntimeException("Deserialization did not return a valid Image");
        }
        return (java.awt.Image) deserializedImage;
    }

    @Override
    public void showImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }
}
