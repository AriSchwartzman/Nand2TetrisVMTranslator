// function SimpleFunction.test 2
(SimpleFunction.test)
@0
D=A
@SP
M=M+1
A=M-1
M=D
@0
D=A
@SP
M=M+1
A=M-1
M=D

// push local 0
@LCL
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push local 1
@LCL
D=M
@1
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// add
@SP
A=M-1
D=M
M=0
A=A-1
M=D+M
@SP
M=M-1

// not
@SP
A=M-1
M=!M

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

// add
@SP
A=M-1
D=M
M=0
A=A-1
M=D+M
@SP
M=M-1

// push argument 1
@ARG
D=M
@1
A=D+A
D=M
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

