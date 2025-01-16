package software.ulpgc.imageviewer.application;

import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.imageio.ImageIO.read;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Displacement displacement = Displacement.Null;
    private Releasement releasement = Releasement.Null;
    private int coordinateX;
    private final List<ImageInfo> imageInfo = new ArrayList<>();
    private SizeListener sizeListener = SizeListener.NULL;

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
        this.addComponentListener(componentListener());
    }

    private ComponentListener componentListener(){
        return new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                sizeListener.resized(getWidth(), getHeight());
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        };
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                coordinateX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                releasement.offset(e.getX() - coordinateX);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                displacement.offset(e.getX() - coordinateX);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    @Override
    public void drawImage(String id, int offset) {
        imageInfo.add(new ImageInfo(id, offset));
        repaint();
    }

    @Override
    public void clear() {
        imageInfo.clear();
    }

    @Override
    public void onResize(SizeListener sizeListener) {
        this.sizeListener = sizeListener != null ? sizeListener : (width, height) -> {};
    }

    @Override
    public void paint(Graphics g) {
        for (ImageInfo info : imageInfo) {
            try {
                BufferedImage originalImage = read(new File("src/main/resources/" + info.id));
                BufferedImage resizedImage = resizeImage(originalImage, getWidth(), getHeight());
                g.drawImage(resizedImage, info.offset, 0, this);
            } catch (IOException e) {
                throw new RuntimeException("Error loading image: " + info.id, e);
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image resultingImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return resizedImage;
    }

    @Override
    public void on(Displacement displacement) {
        this.displacement = displacement != null ? displacement : Displacement.Null;
    }

    @Override
    public void on(Releasement released) {
        this.releasement = releasement != null ? released : Releasement.Null;
    }

    private record ImageInfo(String id, int offset) {
    }
}
