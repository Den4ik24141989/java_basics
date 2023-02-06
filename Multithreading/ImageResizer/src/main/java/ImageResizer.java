import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {
    private int newWidth;
    private String dstFolder;
    private File[] files;
    private long start;

    public ImageResizer(int newWidth, String dstFolder, File[] files, long start) {
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.files = files;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage newImage = simpleResizeImage(image, newWidth);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Поток завершен за " + (System.currentTimeMillis() - start) + " ms");
    }

    BufferedImage simpleResizeImage(BufferedImage originalImage, int newWidth) throws Exception {
        return Scalr.resize(originalImage, newWidth);
    }
}
