package software.ulpgc.mvc.application;

import software.ulpgc.mvc.control.NextCommand;
import software.ulpgc.mvc.control.PreviousCommand;
import software.ulpgc.mvc.model.Image;
import software.ulpgc.mvc.persistence.FileImageLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.create();
        mainFrame.initWith(startToShow())
                .add("next", new NextCommand(mainFrame.imageDisplay()))
                .add("previous", new PreviousCommand(mainFrame.imageDisplay()))
                .setVisible(true);
    }
    private static List<Image> startToShow() {
        return new FileImageLoader("src/main/resources").load();
    }
}
