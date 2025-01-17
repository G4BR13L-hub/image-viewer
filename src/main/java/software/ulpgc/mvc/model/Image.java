package software.ulpgc.mvc.model;

public interface Image {
    byte[] content();
    Image next();
    Image previous();
}
