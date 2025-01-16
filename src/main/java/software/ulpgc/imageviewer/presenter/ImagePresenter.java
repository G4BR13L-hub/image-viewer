package software.ulpgc.imageviewer.presenter;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;
import software.ulpgc.imageviewer.view.ViewPort;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;
    private ViewPort viewPort;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.viewPort = ViewPort.ofSize(800, 600); // Default size
        this.display.on((ImageDisplay.Displacement) this::displace);
        this.display.on((ImageDisplay.Releasement) this::release);
        this.display.onResize(this::updateViewPort);
    }

    private void displace(int offset) {
        display.clear();
        display.drawImage(image.name(), offset);
        if (offset > 0) display.drawImage(image.previous().name(), offset - viewPort.width());
        else display.drawImage(image.next().name(), viewPort.width() + offset);
    }

    private void release(int offset) {
        if (Math.abs(offset) >= viewPort.width() / 2)
            image = offset > 0 ? image.previous() : image.next();
        redraw();
    }

    public void show(Image image) {
        this.image = image;
        redraw();
    }

    private void updateViewPort(int width, int height) {
        this.viewPort = ViewPort.ofSize(width, height);
        redraw();
    }

    private void redraw() {
        this.display.clear();
        this.display.drawImage(image.name(), 0);
    }
}