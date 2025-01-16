package software.ulpgc.imageviewer.persistence;

import software.ulpgc.imageviewer.model.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileImageLoader implements ImageLoader {
    private final File folder;

    public FileImageLoader(String folderPath) {
        this.folder = new File(folderPath);
    }

    @Override
    public List<Image> load() {
        return IntStream.range(0, getFiles().length)
                .filter(i -> isImage(getFiles()[i]))
                .mapToObj(i -> createImage(getFiles(), i))
                .collect(Collectors.toList());
    }

    private File[] getFiles() {
        return Objects.requireNonNull(folder.listFiles(), "Folder is empty or does not exist");
    }

    private Image createImage(File[] files, int index) {
        return new Image() {
            @Override
            public String name() {
                return files[index].getName();
            }

            @Override
            public byte[] content() {
                return readFileContent(files[index]);
            }

            @Override
            public Image next() {
                return hasNext(files, index) ?
                        createImage(files, index + 1) :
                        createImage(files, 0);
            }

            @Override
            public Image previous() {
                return hasPrevious(files, index) ?
                        createImage(files, index - 1) :
                        createImage(files, files.length-1);
            }

            private boolean hasPrevious(File[] files, int index) {
                return index - 1 >= 0 && isImage(files[index - 1]);
            }

            private boolean hasNext(File[] files, int index) {
                return index + 1 < files.length && isImage(files[index + 1]);
            }

        };
    }

    private boolean isImage(File file) {
        String[] extensions = {"jpg", "png", "jpeg", "gif"};
        String extension = getFileExtension(file.getName());
        return List.of(extensions).contains(extension);
    }

    private byte[] readFileContent(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file content: " + file.getAbsolutePath(), e);
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1).toLowerCase();
    }

}
