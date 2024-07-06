import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class HelloWorld {
	public static Set<String> availableFormats = Arrays.stream(ImageIO.getReaderFormatNames())
			.map(String::toLowerCase)
			.collect(Collectors.toSet());

	public static void main(String[] args) {
		System.out.printf("""
		ImageIO Reader Test
		
		Min Java Required: 21, Current Version %s%n
		
		Java Library Path Must Include HEIF Library:
		%s
		
		%d Enabled Formats: %s
		
		""", System.getProperty("java.version"), System.getProperty("java.library.path"),
				availableFormats.size(), availableFormats);

		File heicFile = new File("IMG.HEIC");
		System.out.printf("%s - exists: %b, BufferedImage: ", heicFile.getName(), heicFile.exists());

		try {
			BufferedImage bi = ImageIO.read(heicFile);
			System.out.println((bi == null? "UNABLE" : "Able")+ " to read.");
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}