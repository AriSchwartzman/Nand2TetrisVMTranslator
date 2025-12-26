package VMTranslator;

import java.util.HashMap;

public class AssemblyGenerator {
    private HashMap<String, Integer> symbolTable = new HashMap<>();
    
    public enum Command {
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

    public AssemblyGenerator() {
        symbolTable.put("SP", 0);
        symbolTable.put("LCL", 1);
        symbolTable.put("ARG", 2);
        symbolTable.put("THIS", 3);
        symbolTable.put("THAT", 4);
        symbolTable.put("R0", 0);
        symbolTable.put("R1", 1);
        symbolTable.put("R2", 2);
        symbolTable.put("R3", 3);
        symbolTable.put("R4", 4);
        symbolTable.put("R5", 5);
        symbolTable.put("R6", 6);
        symbolTable.put("R7", 7);
        symbolTable.put("R8", 8);
        symbolTable.put("R9", 9);
        symbolTable.put("R10", 10);
        symbolTable.put("R11", 11);
        symbolTable.put("R12", 12);
        symbolTable.put("R13", 13);
        symbolTable.put("R14", 14);
        symbolTable.put("R15", 15);
        symbolTable.put("SCREEN", 16384);
        symbolTable.put("KBD", 24576);
    }

    public String generateAssembly(String line, int counter) {
        if (line.contains("push")) {
            return stackCommand(line, Command.PUSH);
        } else if (line.contains("pop")) {
            return stackCommand(line, Command.POP);
        } else if (line.contains("add")) {
            return logicCommand(line, Command.ADD, counter);
        } else if (line.contains("sub")) {
            return logicCommand(line, Command.SUB, counter);
        } else if (line.contains("neg")) {
            return logicCommand(line, Command.NEG, counter);
        } else if (line.contains("eq")) {
            return logicCommand(line, Command.EQ, counter);
        } else if (line.contains("gt")) {
            return logicCommand(line, Command.GT, counter);
        } else if (line.contains("lt")) {
            return logicCommand(line, Command.LT, counter);
        } else if (line.contains("and")) {
            return logicCommand(line, Command.AND, counter);
        } else if (line.contains("or")) {
            return logicCommand(line, Command.OR, counter);
        } else if (line.contains("not")) {
            return logicCommand(line, Command.NOT, counter);
        } else {
            return "";
        }
    }


    public String stackCommand(String line, Command command) {
        return line + "\n";
    }

    public String logicCommand(String line, Command command, int counter) {
        String assembly;

        switch (command) {
            case ADD:
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

            case SUB:
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

            case NEG:
                assembly = "// " + line + "\n" + """
                    @SP
                    A=M-1
                    M=-M
                    """;
                break;

            case EQ:
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

            case GT:
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

            case LT:
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

            case AND:
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

            case OR:
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

            case NOT:
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

        return assembly;
    }
}