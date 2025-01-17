package software.ulpgc.mvc.control;

import software.ulpgc.mvc.model.Image;
import software.ulpgc.mvc.view.ImageDisplay;

public class PreviousCommand implements Command {
    private final ImageDisplay display;

    public PreviousCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        Image currentImage = display.currentImage();
        if (currentImage != null && currentImage.previous() != null) {
            display.showImage(currentImage.previous());
        }
    }
}
