package software.ulpgc.mvc.control;

import software.ulpgc.mvc.model.Image;
import software.ulpgc.mvc.view.ImageDisplay;

public class NextCommand implements Command {
    private final ImageDisplay display;

    public NextCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        Image currentImage = display.currentImage();
        if (currentImage != null && currentImage.next() != null) {
            display.showImage(currentImage.next());
        }
    }
}
