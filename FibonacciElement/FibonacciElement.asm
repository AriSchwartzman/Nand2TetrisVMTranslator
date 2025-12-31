// bootstrap
@261
D=A
@SP
D=D-M
@bootstrapSet
D;JEQ
@256
D=A
@SP
M=D

// call Sys.init
@LCL
D=M
@SP
AM=M+1
M=D
@ARG
D=M
@SP
AM=M+1
M=D
@THIS
D=M
@SP
AM=M+1
M=D
@THAT
D=M
@SP
AM=M+1
M=D
@SP
MD=M+1
@LCL
M=D
@5
D=D-A
@ARG
M=D
(bootstrapSet)
@Sys.init
0;JMP
// function Sys.init 0
(Sys.init)

// push constant 4
@4
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Main.fibonacci 1
@Sys.init$ret.1
D=A
@SP
A=M
M=D
@LCL
D=M
@SP
AM=M+1
M=D
@ARG
D=M
@SP
AM=M+1
M=D
@THIS
D=M
@SP
AM=M+1
M=D
@THAT
D=M
@SP
AM=M+1
M=D
@SP
MD=M+1
@LCL
M=D
@5
D=D-A
@1
D=D-A
@ARG
M=D
@Main.fibonacci
0;JMP
(Sys.init$ret.1)

// label END
(Sys.init$END)

// goto END
@Sys.init$END
0;JMP

// function Main.fibonacci 0
(Main.fibonacci)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1

// lt
@SP
A=M-1
D=M
M=0
A=A-1
D=M-D
@Main.fibonacci$EQUAL3
D;JLT
D=0
@Main.fibonacci$SET3
0;JMP
(Main.fibonacci$EQUAL3)
D=-1
(Main.fibonacci$SET3)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// if-goto N_LT_2
@SP
AM=M-1
D=M
@Main.fibonacci$N_LT_2
D;JNE

// goto N_GE_2
@Main.fibonacci$N_GE_2
0;JMP

// label N_LT_2
(Main.fibonacci$N_LT_2)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// return
@LCL
D=M
@5
D=D-A
@R14
AM=D
D=M
@R15
M=D
@ARG
D=M
@R13
M=D
@SP
AM=M-1
D=M
M=0
@R13
A=M
M=D
@ARG
D=M
@SP
M=D+1
@R14
AM=M+1
D=M
M=0
@LCL
M=D
@R14
AM=M+1
D=M
M=0
@ARG
M=D
@R14
AM=M+1
D=M
M=0
@THIS
M=D
@R14
A=M+1
D=M
M=0
@THAT
M=D
@R15
D=M
A=D
0;JMP

// label N_GE_2
(Main.fibonacci$N_GE_2)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1

// sub
@SP
A=M-1
D=M
M=0
A=A-1
M=M-D
@SP
M=M-1

// call Main.fibonacci 1
@Main.fibonacci$ret.2
D=A
@SP
A=M
M=D
@LCL
D=M
@SP
AM=M+1
M=D
@ARG
D=M
@SP
AM=M+1
M=D
@THIS
D=M
@SP
AM=M+1
M=D
@THAT
D=M
@SP
AM=M+1
M=D
@SP
MD=M+1
@LCL
M=D
@5
D=D-A
@1
D=D-A
@ARG
M=D
@Main.fibonacci
0;JMP
(Main.fibonacci$ret.2)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1

// sub
@SP
A=M-1
D=M
M=0
A=A-1
M=M-D
@SP
M=M-1

// call Main.fibonacci 1
@Main.fibonacci$ret.3
D=A
@SP
A=M
M=D
@LCL
D=M
@SP
AM=M+1
M=D
@ARG
D=M
@SP
AM=M+1
M=D
@THIS
D=M
@SP
AM=M+1
M=D
@THAT
D=M
@SP
AM=M+1
M=D
@SP
MD=M+1
@LCL
M=D
@5
D=D-A
@1
D=D-A
@ARG
M=D
@Main.fibonacci
0;JMP
(Main.fibonacci$ret.3)

// add
@SP
A=M-1
D=M
M=0
A=A-1
M=D+M
@SP
M=M-1

// return
@LCL
D=M
@5
D=D-A
@R14
AM=D
D=M
@R15
M=D
@ARG
D=M
@R13
M=D
@SP
AM=M-1
D=M
M=0
@R13
A=M
M=D
@ARG
D=M
@SP
M=D+1
@R14
AM=M+1
D=M
M=0
@LCL
M=D
@R14
AM=M+1
D=M
M=0
@ARG
M=D
@R14
AM=M+1
D=M
M=0
@THIS
M=D
@R14
A=M+1
D=M
M=0
@THAT
M=D
@R15
D=M
A=D
0;JMP

