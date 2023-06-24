import rar.UnRAR;
import rar.UnRARLibrary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private final static String root = "C:\\Users\\Kirito\\Desktop\\Code\\Java\\JRar\\src\\main\\resources\\rar-files\\UnRARDLL.rar";
    private final static UnRAR library = UnRARLibrary.getInstance();


    public static void main(String[] args) throws IOException {

        String path = Files.createTempDirectory("java.temp.dir.").toFile().getAbsolutePath();
        System.out.println(path);
        Files.deleteIfExists(Paths.get(path)) ;
    }
}
