package software.ulpgc.imageviewer.application;


import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.persistence.FileImageLoader;
import software.ulpgc.imageviewer.presenter.ImagePresenter;

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