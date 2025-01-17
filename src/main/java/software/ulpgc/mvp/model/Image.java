package software.ulpgc.mvp.model;

public interface Image {
    String name();
    Image next();
    Image previous();

}
