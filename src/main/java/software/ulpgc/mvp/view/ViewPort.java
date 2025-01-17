package software.ulpgc.mvp.view;

public record ViewPort(int x, int y, int width, int height) {
    public static ViewPort ofSize(int width, int height) {
        return new ViewPort(0, 0, width, height);
    }
}
