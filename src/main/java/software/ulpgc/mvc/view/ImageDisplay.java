package software.ulpgc.mvc.view;

import software.ulpgc.mvc.model.Image;

public interface ImageDisplay {
    void showImage(Image image);
    Image currentImage();
}
