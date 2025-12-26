package VMTranslator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private AssemblyGenerator generator;

    public Parser(AssemblyGenerator generator) {
        this.generator = generator;
    }

    public String prettify(File VMFile) {
        String cleanFile = "";

        try (Scanner scanner = new Scanner(VMFile)) {
            while (scanner.hasNextLine()) {
                // Reads next line in assembly file
                String line = scanner.nextLine();

                // Eliminates comments
                if (line.contains("//")) {
                    String[] lineWithComments = line.split("//");
                    line = lineWithComments[0];
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
    
    public String generateAssembly(String vmCode, String baseFileName) {
        String assemblyCode = "";
        int counter = 0;

        Scanner scanner = new Scanner(vmCode);
        while (scanner.hasNextLine()) {
            // Reads next line in assembly file
            String line = scanner.nextLine();
            assemblyCode = assemblyCode + generator.generateAssembly(line, counter, baseFileName);
            counter++;
        }

        scanner.close();

        

        return assemblyCode;
    }

}
