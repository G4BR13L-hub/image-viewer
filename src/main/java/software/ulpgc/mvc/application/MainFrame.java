package software.ulpgc.mvc.application;

import software.ulpgc.mvc.control.Command;
import software.ulpgc.mvc.control.NextCommand;
import software.ulpgc.mvc.control.PreviousCommand;
import software.ulpgc.mvc.model.Image;
import software.ulpgc.mvc.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    private MainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = createImageDisplay(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(button("previous"));
        panel.add(button("next"));
        return panel;
    }

    private Component button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> commands.get(name).execute());
        return button;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    public MainFrame initWith(List<Image> images) {
        if (!images.isEmpty()) {
            imageDisplay.showImage(images.get(0));
            add("next", new NextCommand(imageDisplay));
            add("previous", new PreviousCommand(imageDisplay));
        }
        return this;
    }

    public static MainFrame create() {
        return new MainFrame();
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }
}
