
public class AssemblyGenerator {

    private String currentFunction;
    private int returns;

    public AssemblyGenerator() {
        this.currentFunction = null;
        returns = 0;
    }

    public String generateBootstrap() {
        return "@261\n" + //
                "D=A\n" + //
                "@SP\n" + //
                "D=D-M\n" + //
                "@bootstrapSet\n" + //
                "D;JEQ\n" + //
                "@256\n" + //
                "D=A\n" + //
                "@SP\n" + //
                "M=D\n" + // SP = 256
                "\n// call Sys.init\n" +
                "@LCL\n" + //
                "D=M\n" + //
                "@SP\n" + //
                "AM=M+1\n" + //
                "M=D\n" + // push LCL
                "@ARG\n" + //
                "D=M\n" + //
                "@SP\n" + //
                "AM=M+1\n" + //
                "M=D\n" + // push ARG
                "@THIS\n" + //
                "D=M\n" + //
                "@SP\n" + //
                "AM=M+1\n" + //
                "M=D\n" + // push THIS
                "@THAT\n" + //
                "D=M\n" + //
                "@SP\n" + //
                "AM=M+1\n" + //
                "M=D\n" + // push THAT
                "@SP\n" + //
                "MD=M+1\n" + //
                "@LCL\n" + //
                "M=D\n" + // LCL = SP
                "@5\n" + //
                "D=D-A\n" + //
                "@ARG\n" + //
                "M=D\n" + // ARG = SP - 5
                "(bootstrapSet)\n" + //
                "@Sys.init\n" + //
                "0;JMP\n" + // goto Sys.init
                "";
    }

    public String generateAssembly(String line, int counter, String baseFileName) {
        String[] splitCommand = line.split(" ");
        if (splitCommand[0].equals("push") || splitCommand[0].equals("pop")) {
            return stackCommand(splitCommand[0], splitCommand[1], splitCommand[2], baseFileName,
                    currentFunction, counter);
        } else if (splitCommand.length == 1 && !line.equals("return")) {
            return logicCommand(splitCommand[0], baseFileName, currentFunction, counter);
        } else if (splitCommand[0].equals("label") || splitCommand[0].contains("goto")) {
            return branchingCommand(splitCommand[0], splitCommand[1], baseFileName, currentFunction);
        } else if (splitCommand[0].equals("return")) {
            return returnCommand();
        } else {
            return functionCommand(splitCommand[0], splitCommand[1], splitCommand[2]);
        }
    }

    private String stackCommand(String command, String segment, String data, String baseFileName,
            String functionName, int counter) {
        String assembly;
        if (command.equals("push")) {
            switch (segment) {
                case "local":
                    assembly = "@LCL\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "A=D+A\n" + //
                            "D=M\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                case "argument":
                    assembly = "@ARG\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "A=D+A\n" + //
                            "D=M\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                case "static":
                    assembly = "@%2$s%1$s\n" + //
                            "D=M\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                case "constant":
                    assembly = "@%1$s\n" + //
                            "D=A\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                case "this":
                    assembly = "@THIS\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "A=D+A\n" + //
                            "D=M\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                case "that":
                    assembly = "@THAT\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "A=D+A\n" + //
                            "D=M\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                case "temp":
                    assembly = "@%1$s\n" + //
                            "D=A\n" + //
                            "@5\n" + //
                            "A=D+A\n" + //
                            "D=M\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                case "pointer":
                    assembly = "@%1$s\n" + //
                            "D=A\n" + //
                            "@POINTER%3$d\n" + //
                            "D;JEQ\n" + //
                            "@THAT\n" + //
                            "D=A\n" + //
                            "@ADDSTACK%3$d\n" + //
                            "0;JMP\n" + //
                            "(POINTER%3$d)\n" + //
                            "@THIS\n" + //
                            "D=A\n" + //
                            "(ADDSTACK%3$d)\n" + //
                            "A=D\n" + //
                            "D=M\n" + //
                            "@SP\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "";
                    break;
                default:
                    assembly = "";
                    break;
            }
            ;
        } else {
            switch (segment) {
                case "local":
                    assembly = "@LCL\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "D=D+A\n" + //
                            "@R13\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@R13\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "";
                    break;

                case "argument":
                    assembly = "@ARG\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "D=D+A\n" + //
                            "@R13\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@R13\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "";
                    break;

                case "static":
                    assembly = "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@%2$s%1$s\n" + //
                            "M=D\n" + //
                            "";
                    break;

                case "constant":
                    throw new RuntimeException("error: cannot pop to constant segment");

                case "this":
                    assembly = "@THIS\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "D=D+A\n" + //
                            "@R13\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@R13\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "";
                    break;

                case "that":
                    assembly = "@THAT\n" + //
                            "D=M\n" + //
                            "@%1$s\n" + //
                            "D=D+A\n" + //
                            "@R13\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@R13\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "";
                    break;

                case "temp":
                    assembly = "@%1$s\n" + //
                            "D=A\n" + //
                            "@5\n" + //
                            "D=D+A\n" + //
                            "@R13\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@R13\n" + //
                            "A=M\n" + //
                            "M=D\n";
                    break;

                case "pointer":
                    assembly = "@%1$s\n" + //
                            "D=A\n" + //
                            "@%4$s$POINTER%3$d\n" + //
                            "D;JEQ\n" + //
                            "@THAT\n" + //
                            "D=A\n" + //
                            "@%4$s$POPSTACK%3$d\n" + //
                            "0;JMP\n" + //
                            "(%4$s$POINTER%3$d)\n" + //
                            "@THIS\n" + //
                            "D=A\n" + //
                            "(%4$s$POPSTACK%3$d)\n" + //
                            "@R13\n" + //
                            "M=D\n" + //
                            "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@R13\n" + //
                            "A=M\n" + //
                            "M=D\n" + //
                            "";
                    break;

                default:
                    assembly = "";
                    break;
            }
        }
        assembly = String.format(assembly, data, baseFileName, counter,
                functionName == null ? baseFileName : functionName);
        return assembly + "\n";
    }

    private String logicCommand(String command, String baseFileName, String functionName, int counter) {
        String assembly;

        switch (command) {
            case "add":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "D=M\n" + //
                        "M=0\n" + //
                        "A=A-1\n" + //
                        "M=D+M\n" + //
                        "@SP\n" + //
                        "M=M-1\n" + //
                        "";
                break;

            case "sub":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "D=M\n" + //
                        "M=0\n" + //
                        "A=A-1\n" + //
                        "M=M-D\n" + //
                        "@SP\n" + //
                        "M=M-1\n" + //
                        "";
                break;

            case "neg":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "M=-M\n" + //
                        "";
                break;

            case "eq":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "D=M\n" + //
                        "M=0\n" + //
                        "A=A-1\n" + //
                        "D=M-D\n" + //
                        "@%2$s$EQUAL%1$d\n" + //
                        "D;JEQ\n" + //
                        "D=0\n" + //
                        "@%2$s$SET%1$d\n" + //
                        "0;JMP\n" + //
                        "(%2$s$EQUAL%1$d)\n" + //
                        "D=-1\n" + //
                        "(%2$s$SET%1$d)\n" + //
                        "@SP\n" + //
                        "A=M-1\n" + //
                        "A=A-1\n" + //
                        "M=D\n" + //
                        "@SP\n" + //
                        "M=M-1\n" + //
                        "";
                assembly = String.format(assembly, counter,
                        functionName == null ? baseFileName : functionName);
                break;

            case "gt":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "D=M\n" + //
                        "M=0\n" + //
                        "A=A-1\n" + //
                        "D=M-D\n" + //
                        "@%2$s$EQUAL%1$d\n" + //
                        "D;JGT\n" + //
                        "D=0\n" + //
                        "@%2$s$SET%1$d\n" + //
                        "0;JMP\n" + //
                        "(%2$s$EQUAL%1$d)\n" + //
                        "D=-1\n" + //
                        "(%2$s$SET%1$d)\n" + //
                        "@SP\n" + //
                        "A=M-1\n" + //
                        "A=A-1\n" + //
                        "M=D\n" + //
                        "@SP\n" + //
                        "M=M-1\n" + //
                        "";
                assembly = String.format(assembly, counter,
                        functionName == null ? baseFileName : functionName);
                break;

            case "lt":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "D=M\n" + //
                        "M=0\n" + //
                        "A=A-1\n" + //
                        "D=M-D\n" + //
                        "@%2$s$EQUAL%1$d\n" + //
                        "D;JLT\n" + //
                        "D=0\n" + //
                        "@%2$s$SET%1$d\n" + //
                        "0;JMP\n" + //
                        "(%2$s$EQUAL%1$d)\n" + //
                        "D=-1\n" + //
                        "(%2$s$SET%1$d)\n" + //
                        "@SP\n" + //
                        "A=M-1\n" + //
                        "A=A-1\n" + //
                        "M=D\n" + //
                        "@SP\n" + //
                        "M=M-1\n" + //
                        "";
                assembly = String.format(assembly, counter,
                        functionName == null ? baseFileName : functionName);
                break;

            case "and":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "D=M\n" + //
                        "M=0\n" + //
                        "A=A-1\n" + //
                        "M=D&M\n" + //
                        "@SP\n" + //
                        "M=M-1\n" + //
                        "";
                break;

            case "or":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "D=M\n" + //
                        "M=0\n" + //
                        "A=A-1\n" + //
                        "M=D|M\n" + //
                        "@SP\n" + //
                        "M=M-1\n" + //
                        "";
                break;

            case "not":
                assembly = "@SP\n" + //
                        "A=M-1\n" + //
                        "M=!M\n" + //
                        "";
                break;

            default:
                assembly = "";
                break;
        }
        return assembly + "\n";
    }

    private String branchingCommand(String command, String data, String baseFileName,
            String functionName) {
        String assembly;

        switch (command) {
            case "label":
                if (functionName == null) {
                    assembly = "(" + baseFileName + "$" + data + ")\n";
                    break;
                }
                assembly = "(" + functionName + "$" + data + ")\n";
                break;

            case "goto":
                if (functionName == null) {
                    assembly = "@" + baseFileName + "$" + data
                            + "\n" + //
                            "0;JMP\n" + //
                            "";
                    break;
                }
                assembly = "@" + functionName + "$" + data
                        + "\n" + //
                        "0;JMP\n" + //
                        "";
                break;

            case "if-goto":
                if (functionName == null) {
                    assembly = "@SP\n" + //
                            "AM=M-1\n" + //
                            "D=M\n" + //
                            "@" + baseFileName + "$" + data //
                            + "\n" + //
                            "D;JNE\n" + //
                            "";
                    break;
                }
                assembly = "@SP\n" + //
                        "AM=M-1\n" + //
                        "D=M\n" + //
                        "@" + functionName + "$" + data //
                        + "\n" + //
                        "D;JNE\n" + //
                        "";
                break;

            default:
                assembly = "";
                break;
        }
        return assembly + "\n";
    }

    private String returnCommand() {
        return "@LCL\n" + //
                "D=M\n" + // endFrame = LCL
                "@5\n" + //
                "D=D-A\n" + //
                "@R14\n" + //
                "AM=D\n" + // R14 = endFrame - 5
                "D=M\n" + //
                "@R15\n" + //
                "M=D\n" + // R15 = retAddr
                "@ARG\n" + //
                "D=M\n" + //
                "@R13\n" + //
                "M=D\n" + //
                "@SP\n" + //
                "AM=M-1\n" + //
                "D=M\n" + //
                "M=0\n" + //
                "@R13\n" + //
                "A=M\n" + //
                "M=D\n" + // *ARG = pop()
                "@ARG\n" + //
                "D=M\n" + //
                "@SP\n" + //
                "M=D+1\n" + // SP = ARG + 1
                "@R14\n" + //
                "AM=M+1\n" + // endFrame++
                "D=M\n" + //
                "M=0\n" + //
                "@LCL\n" + //
                "M=D\n" + // LCL = *(endFrame - 4)
                "@R14\n" + //
                "AM=M+1\n" + // endFrame++
                "D=M\n" + //
                "M=0\n" + //
                "@ARG\n" + //
                "M=D\n" + // ARG = *(endFrame - 3)
                "@R14\n" + //
                "AM=M+1\n" + // endFrame++
                "D=M\n" + //
                "M=0\n" + //
                "@THIS\n" + //
                "M=D\n" + // THIS = *(endFrame - 2)
                "@R14\n" + //
                "A=M+1\n" + // endFrame++
                "D=M\n" + //
                "M=0\n" + //
                "@THAT\n" + //
                "M=D\n" + // THAT = *(endFrame - 1)
                "@R15\n" + //
                "D=M\n" + //
                "A=D\n" + //
                "0;JMP\n\n"; // goto (endFrame - 5)
    }

    private String functionCommand(String command, String functionName, String data) {
        String assembly;
        if (command.equals("call")) {
            assembly = "@%1$s$ret.%4$d\n" + //
                    "D=A\n" + //
                    "@SP\n" + //
                    "A=M\n" + //
                    "M=D\n" + // push caller$ret.i
                    "@LCL\n" + //
                    "D=M\n" + //
                    "@SP\n" + //
                    "AM=M+1\n" + //
                    "M=D\n" + // push LCL
                    "@ARG\n" + //
                    "D=M\n" + //
                    "@SP\n" + //
                    "AM=M+1\n" + //
                    "M=D\n" + // push ARG
                    "@THIS\n" + //
                    "D=M\n" + //
                    "@SP\n" + //
                    "AM=M+1\n" + //
                    "M=D\n" + // push THIS
                    "@THAT\n" + //
                    "D=M\n" + //
                    "@SP\n" + //
                    "AM=M+1\n" + //
                    "M=D\n" + // push THAT
                    "@SP\n" + //
                    "MD=M+1\n" + //
                    "@LCL\n" + //
                    "M=D\n" + // LCL = SP
                    "@5\n" + //
                    "D=D-A\n" + //
                    "@%3$s\n" + //
                    "D=D-A\n" + //
                    "@ARG\n" + //
                    "M=D\n" + // ARG = SP - 5 - nArgs
                    "@%2$s\n" + //
                    "0;JMP\n" + // goto functionName
                    "(%1$s$ret.%4$d)\n" + // label caller$ret.i
                    "";
            returns++;
            System.out.println("Returns: " + returns);
            assembly = String.format(assembly, currentFunction, functionName, data, returns);
        } else {
            assembly = "(%1$s)\n" + // label fileName.functionName
                    ("@0\n" + //
                            "D=A\n" + //
                            "@SP\n" + //
                            "M=M+1\n" + //
                            "A=M-1\n" + //
                            "M=D\n") // push 0
                            .repeat(Integer.parseInt(data)); // Repeats push 0 nVars times
            currentFunction = functionName;
            assembly = String.format(assembly, functionName);
        }
        return assembly + "\n";
    }
}