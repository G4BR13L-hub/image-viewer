package software.ulpgc.imageviewer.model;

public interface Image {
    String name();
    byte[] content();
    Image next();
    Image previous();

}
