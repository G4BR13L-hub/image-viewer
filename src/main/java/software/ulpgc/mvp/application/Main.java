package software.ulpgc.mvp.application;


import software.ulpgc.mvp.model.Image;
import software.ulpgc.mvp.persistence.FileImageLoader;
import software.ulpgc.mvp.presenter.ImagePresenter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image().get(0));
        frame.setVisible(true);
    }

    private static List<Image> image() {
        return new FileImageLoader("src/main/resources").load();
    }
}