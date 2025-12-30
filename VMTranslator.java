import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class VMTranslator {
    public static void main(String[] args) {
        AssemblyGenerator generator = new AssemblyGenerator();
        Parser parser = new Parser(generator);
        File[] files;
        String assemblyCode = "";
        String assemblyFilePath;

        if (args[0].endsWith(".vm")) {
            files = new File[1];
            files[0] = new File(args[0]);
            assemblyFilePath = args[0].substring(0, args[0].length() - 2) + "asm";
        } else {
            File directory = new File(args[0]);

            files = directory.listFiles(new FilenameFilter() {
                public boolean accept(File f, String fileType) {
                    return fileType.endsWith(".vm");}
            });
            System.out.println(files.length);
            assemblyFilePath = args[0] + ".asm";
        }


        try {
            File myObj = new File(assemblyFilePath); // Create File object
            if (myObj.createNewFile()) { // Try to create the file
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }

        for (File vmFile : files) {
            String fileName = vmFile.getName();
            String baseFileName = fileName.substring(0, fileName.length() - 3);
            System.out.println(baseFileName);
            String cleanVMCode = parser.prettify(vmFile);
            assemblyCode += parser.generateAssembly(cleanVMCode, baseFileName);
        }


        try {
            FileWriter myWriter = new FileWriter(assemblyFilePath);
            myWriter.write(assemblyCode);
            myWriter.close(); // must close manually
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}