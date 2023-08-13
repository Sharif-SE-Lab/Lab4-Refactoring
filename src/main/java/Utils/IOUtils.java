package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOUtils {
    private static IOUtils instance = null;
    public static IOUtils getInstance() {
        if (instance == null) {
            instance = new IOUtils();
        }
        return instance;
    }

    public String readWholeInput(Scanner scanner) {
        scanner.useDelimiter("\\Z");
        return scanner.next();
    }

    public String readFile(String pathname) throws FileNotFoundException {
        return readWholeInput(new Scanner(new File(pathname)));
    }
}
