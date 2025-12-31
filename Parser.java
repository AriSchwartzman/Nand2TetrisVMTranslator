import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private final AssemblyGenerator generator;

    public Parser(AssemblyGenerator generator) {
        this.generator = generator;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public String prettify(File VMFile) {
        String cleanFile = "";

        try (Scanner scanner = new Scanner(VMFile)) {
            while (scanner.hasNextLine()) {
                // Reads next line in assembly file
                String line = scanner.nextLine();
                System.out.println(line);
                // Eliminates comments
                if (line.contains("//")) {
                    if (line.equals("//")) {
                        line = "";
                    } else {
                        String[] lineWithComments = line.split("//");
                        line = lineWithComments[0];
                    }
                }

                // Eliminates whitespace around command
                line = line.trim();

                if (!line.isBlank()) {
                    cleanFile = cleanFile + line + "\n";
                }
            }

        } catch (FileNotFoundException error) {
            System.out.println("File not found");
            error.printStackTrace();
        }

        return cleanFile;
    }

    public String getBootstrap() {
        String bootstrap = generator.generateBootstrap();
        bootstrap = "// bootstrap\n" + bootstrap;
        return bootstrap;
    }

    public String generateAssembly(String vmCode, String baseFileName) {
        String assemblyCode = "";
        int counter = 0;

        try (Scanner scanner = new Scanner(vmCode)) {
            while (scanner.hasNextLine()) {
                // Reads next line in assembly file
                String line = scanner.nextLine();
                // System.out.println(line);
                assemblyCode = assemblyCode + "// " + line + "\n"
                        + generator.generateAssembly(line, counter, baseFileName);
                counter++;
            }
        }

        return assemblyCode;
    }

}
