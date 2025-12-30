import java.util.Stack;

public class AssemblyGenerator {

    private Stack<String> functionStack = new Stack<>();

    public AssemblyGenerator() {
        this.functionStack.push(null);
    }

    public String generateAssembly(String line, int counter, String baseFileName) {
        String[] splitCommand = line.split(" ");
        if (splitCommand[0].equals("push") || splitCommand[0].equals("pop")) {
            return stackCommand(line, splitCommand[0], splitCommand[1], splitCommand[2], baseFileName,
                    functionStack.peek(), counter);
        } else if (splitCommand.length == 1 && !line.equals("return")) {
            return logicCommand(line, splitCommand[0], baseFileName, functionStack.peek(), counter);
        } else if (splitCommand[0].equals("label") || splitCommand[0].contains("goto")) {
            return branchingCommand(line, splitCommand[0], splitCommand[1], baseFileName, functionStack.peek(),
                    counter);
        } else if (splitCommand[0].equals("return")) {
            return returnCommand();
        } else {
            return functionCommand(line, splitCommand[0], splitCommand[1], splitCommand[2], baseFileName, counter);
        }
    }

    private String stackCommand(String line, String command, String segment, String data, String baseFileName,
            String functionName, int counter) {
        String assembly;
        if (command.equals("push")) {
            switch (segment) {
                case "local":
                    assembly = "// " + line + "\n@LCL\nD=M\n@%1$s\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                case "argument":
                    assembly = "// " + line + "\n@ARG\nD=M\n@%1$s\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                case "static":
                    assembly = "// " + line + "\n@%2$s%1$s\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                case "constant":
                    assembly = "// " + line + "\n@%1$s\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                case "this":
                    assembly = "// " + line + "\n@THIS\nD=M\n@%1$s\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                case "that":
                    assembly = "// " + line + "\n@THAT\nD=M\n@%1$s\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                case "temp":
                    assembly = "// " + line + "\n@%1$s\nD=A\n@5\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                case "pointer":
                    assembly = "// " + line
                            + "\n@%1$s\nD=A\n@POINTER%3$d\nD;JEQ\n@THAT\nD=A\n@ADDSTACK%3$d\n0;JMP\n(POINTER%3$d)\n@THIS\nD=A\n(ADDSTACK%3$d)\nA=D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                default:
                    assembly = "";
                    break;
            }
        } else {
            switch (segment) {
                case "local":
                    assembly = "// " + line
                            + "\n@LCL\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "argument":
                    assembly = "// " + line
                            + "\n@ARG\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "static":
                    assembly = "// " + line + "\n@SP\nAM=M-1\nD=M\n@%2$s%1$s\nM=D\n";
                    break;

                case "constant":
                    throw new RuntimeException("error: cannot pop to constant segment");

                case "this":
                    assembly = "// " + line
                            + "\n@THIS\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "that":
                    assembly = "// " + line
                            + "\n@THAT\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "temp":
                    assembly = "// " + line + "\n@%1$s\nD=A\n@5\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "pointer":
                    assembly = "// " + line
                            + "\n@%1$s\nD=A\n@%2$s.%4$s$POINTER%3$d\nD;JEQ\n@THAT\nD=A\n@%2$s.%4$s$POPSTACK%3$d\n0;JMP\n(%2$s.%4$s$POINTER%3$d)\n@THIS\nD=A\n(%2$s.%4$s$POPSTACK%3$d)\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                default:
                    assembly = "// " + line + "\n";
                    break;
            }
        }
        assembly = String.format(assembly, data, baseFileName, counter,
                functionName == null ? baseFileName : functionName);
        return assembly + "\n";
    }

    private String logicCommand(String line, String command, String baseFileName, String functionName, int counter) {
        String assembly;

        switch (command) {
            case "add":
                assembly = "// " + line + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nM=D+M\n@SP\nM=M-1\n";
                break;

            case "sub":
                assembly = "// " + line + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nM=M-D\n@SP\nM=M-1\n";
                break;

            case "neg":
                assembly = "// " + line + "\n@SP\nA=M-1\nM=-M\n";
                break;

            case "eq":
                assembly = "// " + line
                        + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nD=M-D\n@%2$s.%3$s$EQUAL%1$d\nD;JEQ\nD=0\n@%2$s.%3$s$SET%1$d\n0;JMP\n(%2$s.%3$s$EQUAL%1$d)\nD=-1\n(%2$s.%3$s$SET%1$d)\n@SP\nA=M-1\nA=A-1\nM=D\n@SP\nM=M-1\n";
                assembly = String.format(assembly, counter, baseFileName,
                        functionName == null ? baseFileName : functionName);
                break;

            case "gt":
                assembly = "// " + line
                        + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nD=M-D\n@%2$s.%3$s$EQUAL%1$d\nD;JGT\nD=0\n@%2$s.%3$s$SET%1$d\n0;JMP\n(%2$s.%3$s$EQUAL%1$d)\nD=-1\n(%2$s.%3$s$SET%1$d)\n@SP\nA=M-1\nA=A-1\nM=D\n@SP\nM=M-1\n";
                assembly = String.format(assembly, counter, baseFileName,
                        functionName == null ? baseFileName : functionName);
                break;

            case "lt":
                assembly = "// " + line
                        + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nD=M-D\n@%2$s.%3$s$EQUAL%1$d\nD;JLT\nD=0\n@%2$s.%3$s$SET%1$d\n0;JMP\n(%2$s.%3$s$EQUAL%1$d)\nD=-1\n(%2$s.%3$s$SET%1$d)\n@SP\nA=M-1\nA=A-1\nM=D\n@SP\nM=M-1\n";
                assembly = String.format(assembly, counter, baseFileName,
                        functionName == null ? baseFileName : functionName);
                break;

            case "and":
                assembly = "// " + line + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nM=D&M\n@SP\nM=M-1\n";
                break;

            case "or":
                assembly = "// " + line + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nM=D|M\n@SP\nM=M-1\n";
                break;

            case "not":
                assembly = "// " + line + "\n@SP\nA=M-1\nM=!M\n";
                break;

            default:
                assembly = "// " + line + "\n";
                break;
        }
        return assembly + "\n";
    }

    private String branchingCommand(String line, String command, String data, String baseFileName, String functionName,
            int counter) {
        String assembly;

        switch (command) {
            case "label":
                if (functionName == null) {
                    assembly = "// " + line + "\n(" + baseFileName + "." + baseFileName + "$" + data + ")\n";
                    break;
                }
                assembly = "// " + line + "\n(" + baseFileName + "." + functionName + "$" + data + ")\n";
                break;

            case "goto":
                if (functionName == null) {
                    assembly = "// " + line + "\n@" + baseFileName + "." + baseFileName + "$" + data + "\n0;JMP\n";
                    break;
                }
                assembly = "// " + line + "\n@" + baseFileName + "." + functionName + "$" + data + "\n0;JMP\n";
                break;

            case "if-goto":
                if (functionName == null) {
                    assembly = "// " + line + "\n@SP\nAM=M-1\nD=M\n@" + baseFileName + "." + baseFileName + "$" + data
                            + "\nD;JGT\nM=M+1\n";
                    break;
                }
                assembly = "// " + line + "\n@SP\nAD=M\n@" + baseFileName + "." + functionName + "$" + data
                        + "\nD;JGT\n";
                break;

            default:
                assembly = "// " + line + "\n";
                break;
        }
        return assembly + "\n";
    }

    private String returnCommand() {
        return "// return\n@SP\nD=M\n@ARG\nA=M\nM=D\n@ARG\nD=M\n@SP\nM=D+1\n@LCL\nD=M\n@R14\nAM=D-1\nD=M\n@THAT\nM=D\n@R14\nAM=M-1\nD=M\n@THIS\nM=D\n@R14\nAM=M-1\nD=M\n@ARG\nM=D\n@R14\nAM=M-1\nD=M\n@LCL\nM=D\n@R14\nA=M-1\nA=M\n0;JMP";
    }

    private String functionCommand(String line, String command, String newFunctionName, String data,
            String baseFileName,
            int counter) {
        String assembly;
        assembly = "// " + line + "\n";
        return assembly;
    }
}