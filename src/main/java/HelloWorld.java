import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HelloWorld {
	public static Set<String> availableFormats = Arrays.stream(ImageIO.getReaderFormatNames())
			.map(String::toLowerCase)
			.collect(Collectors.toSet());

	public static void main(String[] args) {
		List<String> arguments = ManagementFactory.getRuntimeMXBean().getInputArguments();

		boolean isPreviewEnabled = arguments.contains("--enable-preview");
		boolean isNativeAccessEnabled = arguments.stream().anyMatch(arg -> arg.startsWith("--enable-native-access"));


		System.out.printf("""
		ImageIO Reader Test
		
		Min Java Required: 21, Current Version %s
		
		--enable-preview %b
		--enable-native-access %b
		
		Java Library Path Must Include HEIF Library:
		%s
		
		%d Enabled Formats: %s
		
		""", System.getProperty("java.version"),
				isPreviewEnabled, isNativeAccessEnabled,
				System.getProperty("java.library.path"),
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