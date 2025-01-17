package software.ulpgc.mvp.persistence;

import software.ulpgc.mvp.model.Image;

import java.util.List;

public interface ImageLoader {
    List<Image> load();
}