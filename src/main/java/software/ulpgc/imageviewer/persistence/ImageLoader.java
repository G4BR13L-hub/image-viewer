package software.ulpgc.imageviewer.persistence;

import software.ulpgc.imageviewer.model.Image;

import java.util.List;

public interface ImageLoader {
    List<Image> load();
}