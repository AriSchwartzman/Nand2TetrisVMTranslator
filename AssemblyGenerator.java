package VMTranslator;

import java.util.HashMap;

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
                    assembly = "// " + line + "\n" + """
                            @LCL
                            D=M
                            @%1$s
                            A=D+A
                            D=M
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                case "argument":
                    assembly = "// " + line + "\n" + """
                            @ARG
                            D=M
                            @%1$s
                            A=D+A
                            D=M
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                case "static":
                    assembly = "// " + line + "\n" + """
                            @%2$s%1$s
                            D=M
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                case "constant":
                    assembly = "// " + line + "\n" + """
                            @%1$s
                            D=A
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                case "this":
                    assembly = "// " + line + "\n" + """
                            @THIS
                            D=M
                            @%1$s
                            A=D+A
                            D=M
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                case "that":
                    assembly = "// " + line + "\n" + """
                            @THAT
                            D=M
                            @%1$s
                            A=D+A
                            D=M
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                case "temp":
                    assembly = "// " + line + "\n" + """
                            @%1$s
                            D=A
                            @5
                            A=D+A
                            D=M
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                case "pointer":
                    assembly = "// " + line + "\n" + """
                            @%1$s
                            D=A
                            @POINTER%3$d
                            D;JEQ
                            @THAT
                            D=A
                            @ADDSTACK%3$d
                            0;JMP
                            (POINTER%3$d)
                            @THIS
                            D=A
                            (ADDSTACK%3$d)
                            A=D
                            D=M
                            @SP
                            A=M
                            M=D
                            @SP
                            M=M+1
                            """;
                    break;

                default:
                    assembly = "";
                    break;
            }
        } else {
            switch (segment) {
                case "local":
                    assembly = "// " + line + "\n" + """
                            @LCL
                            D=M
                            @%1$s
                            D=D+A
                            @R13
                            M=D
                            @SP
                            AM=M-1
                            D=M
                            @R13
                            A=M
                            M=D
                            """;
                    break;

                case "argument":
                    assembly = "// " + line + "\n" + """
                            @ARG
                            D=M
                            @%1$s
                            D=D+A
                            @R13
                            M=D
                            @SP
                            AM=M-1
                            D=M
                            @R13
                            A=M
                            M=D
                            """;
                    break;

                case "static":
                    assembly = """
                            @SP
                            AM=M-1
                            D=M
                            @%2$s%1$s
                            M=D
                            """;;
                    break;

                case "constant":
                    throw new RuntimeException("error: cannot pop to constant segment");

                case "this":
                    assembly = "// " + line + "\n" + """
                            @THIS
                            D=M
                            @%1$s
                            D=D+A
                            @R13
                            M=D
                            @SP
                            AM=M-1
                            D=M
                            @R13
                            A=M
                            M=D
                            """;
                    break;

                case "that":
                    assembly = "// " + line + "\n" + """
                            @THAT
                            D=M
                            @%1$s
                            D=D+A
                            @R13
                            M=D
                            @SP
                            AM=M-1
                            D=M
                            @R13
                            A=M
                            M=D
                            """;
                    break;

                case "temp":
                    assembly = "// " + line + "\n" + """
                            @%1$s
                            D=A
                            @5
                            D=D+A
                            @R13
                            M=D
                            @SP
                            AM=M-1
                            D=M
                            @R13
                            A=M
                            M=D
                            """;
                    break;

                case "pointer":
                    assembly = "// " + line + "\n" + """
                            @%1$s
                            D=A
                            @POINTER%3$d
                            D;JEQ
                            @THAT
                            D=A
                            @POPSTACK%3$d
                            0;JMP
                            (POINTER%3$d)
                            @THIS
                            D=A
                            (POPSTACK%3$d)
                            @R13
                            M=D
                            @SP
                            AM=M-1
                            D=M
                            @R13
                            A=M
                            M=D
                            """;
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
                assembly = "// " + line + "\n" + """
                        @SP
                        A=M-1
                        D=M
                        M=0
                        A=A-1
                        M=D+M
                        @SP
                        M=M-1
                        """;
                break;

            case "sub":
                assembly = "// " + line + "\n" + """
                        @SP
                        A=M-1
                        D=M
                        M=0
                        A=A-1
                        M=M-D
                        @SP
                        M=M-1
                        """;
                break;

            case "neg":
                assembly = "// " + line + "\n" + """
                        @SP
                        A=M-1
                        M=-M
                        """;
                break;

            case "eq":
                assembly = "// " + line + "\n" + """
                        @SP
                        A=M-1
                        D=M
                        M=0
                        A=A-1
                        D=M-D
                        @EQUAL%1$d
                        D;JEQ
                        D=0
                        @SET%1$d
                        0;JMP
                        (EQUAL%1$d)
                        D=-1
                        (SET%1$d)
                        @SP
                        A=M-1
                        A=A-1
                        M=D
                        @SP
                        M=M-1
                        """;
                assembly = String.format(assembly, counter);
                break;

            case "gt":
                assembly = "// " + line + "\n" + """
                            @SP
                            A=M-1
                            D=M
                            M=0
                            A=A-1
                            D=M-D
                            @EQUAL%1$d
                            D;JGT
                            D=0
                            @SET%1$d
                            0;JMP
                            (EQUAL%1$d)
                            D=-1
                            (SET%1$d)
                            @SP
                            A=M-1
                            A=A-1
                            M=D
                            @SP
                            M=M-1
                            """;
                assembly = String.format(assembly, counter);
                break;

            case "lt":
                assembly = "// " + line + "\n" + """
                            @SP
                            A=M-1
                            D=M
                            M=0
                            A=A-1
                            D=M-D
                            @EQUAL%1$d
                            D;JLT
                            D=0
                            @SET%1$d
                            0;JMP
                            (EQUAL%1$d)
                            D=-1
                            (SET%1$d)
                            @SP
                            A=M-1
                            A=A-1
                            M=D
                            @SP
                            M=M-1
                            """;
                assembly = String.format(assembly, counter);
                break;

            case "and":
                assembly = "// " + line + "\n" + """
                            @SP
                            A=M-1
                            D=M
                            M=0
                            A=A-1
                            M=D&M
                            @SP
                            M=M-1
                            """;
                break;

            case "or":
                assembly = "// " + line + "\n" + """
                            @SP
                            A=M-1
                            D=M
                            M=0
                            A=A-1
                            M=D|M
                            @SP
                            M=M-1
                            """;
                break;

            case "not":
                assembly = "// " + line + "\n" + """
                            @SP
                            A=M-1
                            M=!M
                            """;
                break;
        
            default:
                assembly = "";
                break;
        }

        return assembly + "\n";
    }
}