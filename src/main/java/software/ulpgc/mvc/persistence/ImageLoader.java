package software.ulpgc.mvc.persistence;

import software.ulpgc.mvc.model.Image;

import java.util.List;

public interface ImageLoader {
    List<Image> load();
}
