package software.ulpgc.mvp.view;

public interface ImageDisplay {
    void drawImage(String id, int offset);
    void clear();
    void onResize(SizeListener listener);
    void on(Displacement shift);
    void on(Releasement released);

    interface SizeListener {
        void resized(int width, int height);
        SizeListener NULL = (width, height) -> {};
    }

    interface Displacement {
        void offset(int offset);
        Displacement Null = offset -> {};

    }

    interface Releasement {
        void offset(int offset);
        Releasement Null = offset -> {};
    }
}
