import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Translator {
    

    public static void main(String[] args) {
        AssemblyGenerator generator = new AssemblyGenerator();
        Parser parser = new Parser(generator);
        String fileName = args[0];
        File vmFile = new File(fileName);
        String baseFileName = fileName.substring(0, fileName.length() - 2);
        String assemblyFileName = baseFileName + "asm";
        String assemblyCode = "";

        try {
            File myObj = new File(assemblyFileName); // Create File object
            if (myObj.createNewFile()) { // Try to create the file
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }

        String cleanVMCode = parser.prettify(vmFile);

        assemblyCode = parser.generateAssembly(cleanVMCode, baseFileName);

        try {
            FileWriter myWriter = new FileWriter(assemblyFileName);
            myWriter.write(assemblyCode);
            myWriter.close(); // must close manually
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}