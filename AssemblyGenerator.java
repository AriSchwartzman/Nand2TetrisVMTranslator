public class AssemblyGenerator {

    public String generateAssembly(String line, int counter, String baseFileName) {
        String[] splitCommand = line.split(" ");
        if (line.contains("push") || line.contains("pop")) {
            return stackCommand(line, splitCommand[0], splitCommand[1], splitCommand[2], baseFileName, counter);
        } else {
            return logicCommand(line, splitCommand[0], counter);
        }
    }


    public String stackCommand(String line, String command, String segment, String data, String baseFileName, int counter) {
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
                    assembly = "// " + line + "\n@%1$s\nD=A\n@POINTER%3$d\nD;JEQ\n@THAT\nD=A\n@ADDSTACK%3$d\n0;JMP\n(POINTER%3$d)\n@THIS\nD=A\n(ADDSTACK%3$d)\nA=D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                    break;

                default:
                    assembly = "";
                    break;
            }
        } else {
            switch (segment) {
                case "local":
                    assembly = "// " + line + "\n@LCL\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "argument":
                    assembly = "// " + line + "\n@ARG\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "static":
                    assembly = "// " + line + "\n@SP\nAM=M-1\nD=M\n@%2$s%1$s\nM=D\n";
                    break;

                case "constant":
                    throw new RuntimeException("error: cannot pop to constant segment");

                case "this":
                    assembly = "// " + line + "\n@THIS\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "that":
                    assembly = "// " + line + "\n@THAT\nD=M\n@%1$s\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "temp":
                    assembly = "// " + line + "\n@%1$s\nD=A\n@5\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                case "pointer":
                    assembly = "// " + line + "\n@%1$s\nD=A\n@POINTER%3$d\nD;JEQ\n@THAT\nD=A\n@POPSTACK%3$d\n0;JMP\n(POINTER%3$d)\n@THIS\nD=A\n(POPSTACK%3$d)\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n";
                    break;

                default:
                    assembly = "";
                    break;
            }
        }
        assembly = String.format(assembly, data, baseFileName, counter);
        return assembly + "\n";
    }


    public String logicCommand(String line, String command, int counter) {
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
                assembly = "// " + line + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nD=M-D\n@EQUAL%1$d\nD;JEQ\nD=0\n@SET%1$d\n0;JMP\n(EQUAL%1$d)\nD=-1\n(SET%1$d)\n@SP\nA=M-1\nA=A-1\nM=D\n@SP\nM=M-1\n";
                assembly = String.format(assembly, counter);
                break;

            case "gt":
                assembly = "// " + line + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nD=M-D\n@EQUAL%1$d\nD;JGT\nD=0\n@SET%1$d\n0;JMP\n(EQUAL%1$d)\nD=-1\n(SET%1$d)\n@SP\nA=M-1\nA=A-1\nM=D\n@SP\nM=M-1\n";
                assembly = String.format(assembly, counter);
                break;

            case "lt":
                assembly = "// " + line + "\n@SP\nA=M-1\nD=M\nM=0\nA=A-1\nD=M-D\n@EQUAL%1$d\nD;JLT\nD=0\n@SET%1$d\n0;JMP\n(EQUAL%1$d)\nD=-1\n(SET%1$d)\n@SP\nA=M-1\nA=A-1\nM=D\n@SP\nM=M-1\n";
                assembly = String.format(assembly, counter);
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
                assembly = "";
                break;
        }

        return assembly + "\n";
    }
}