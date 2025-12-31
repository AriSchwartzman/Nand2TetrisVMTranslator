import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class VMTranslator {
    public static void main(String[] args) {
        AssemblyGenerator generator = new AssemblyGenerator();
        Parser parser = new Parser(generator);
        File[] files;
        String assemblyCode = "";
        String assemblyFilePath;
        boolean hasSys = false;

        if (args[0].endsWith(".vm")) {
            files = new File[1];
            files[0] = new File(args[0]);
            assemblyFilePath = args[0].substring(0, args[0].length() - 2) + "asm";
        } else {
            File directory = new File(args[0]);
            if (Arrays.asList(directory.list()).contains("Sys.vm")) {
                hasSys = true;
            }
            files = directory
                    .listFiles((File f, String fileType) -> fileType.endsWith(".vm") && !fileType.equals("Sys.vm"));
            assemblyFilePath = args[0] + "/" + args[0] + ".asm";
            System.out.println(assemblyFilePath);
        }

        try {
            File asmFile = new File(assemblyFilePath); // Create File object
            if (asmFile.createNewFile()) { // Try to create the file
                System.out.println("File created: " + asmFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }

        if (hasSys) {
            File vmFile = new File(args[0] + "/Sys.vm");
            String cleanVMCode = parser.prettify(vmFile);
            if (cleanVMCode.contains("Sys.init")) {
                assemblyCode += parser.getBootstrap();
            }
            String baseFileName = "Sys";
            // System.out.println(vmFile.getAbsolutePath());
            assemblyCode += parser.generateAssembly(cleanVMCode, baseFileName);
        }

        for (File vmFile : files) {
            String fileName = vmFile.getName();
            String baseFileName = fileName.substring(0, fileName.length() - 3);
            // System.out.println(baseFileName);
            String cleanVMCode = parser.prettify(vmFile);
            assemblyCode += parser.generateAssembly(cleanVMCode, baseFileName);
        }

        try (FileWriter myWriter = new FileWriter(assemblyFilePath)) {
            myWriter.write(assemblyCode);
            myWriter.close(); // must close manually
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}