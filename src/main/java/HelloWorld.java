import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HelloWorld {
	public static void main(String[] args) {
		System.out.printf("Min Java 21, Current Version %s%n", System.getProperty("java.version"));
		System.out.printf("Java Library Path: %s%n", System.getProperty("java.library.path"));
		File heicFile = new File("IMG.HEIC");
		System.out.printf("%s exists: %b%n", heicFile.getName(), heicFile.exists());
        try {
			BufferedImage bi = ImageIO.read(heicFile);
			System.out.println((bi == null? "UNABLE" : "Able")+ " to read BufferedImage.");
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}