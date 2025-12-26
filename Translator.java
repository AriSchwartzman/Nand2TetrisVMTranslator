package VMTranslator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Translator {
    public static enum Command {
        PUSH,
        POP,
        ADD,
        SUB,
        NEG,
        EQ,
        GT,
        LT,
        AND,
        OR,
        NOT 
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        String fileName = args[0];
        File vmFile = new File(fileName);
        String assemblyFileName = fileName.replace("asm", "hack");
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

        assemblyCode = parser.prettify(vmFile);

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