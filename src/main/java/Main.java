import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import parser.Parser;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new CompilerFacade(new Scanner(new File("src/main/resources/code"))).compile();
    }
}
