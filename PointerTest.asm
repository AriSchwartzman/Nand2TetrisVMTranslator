// push constant 3030
@3030
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 0
@0
D=A
@POINTER1
D;JEQ
@THAT
D=A
@POPSTACK1
0;JMP
(POINTER1)
@THIS
D=A
(POPSTACK1)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 3040
@3040
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 1
@1
D=A
@POINTER3
D;JEQ
@THAT
D=A
@POPSTACK3
0;JMP
(POINTER3)
@THIS
D=A
(POPSTACK3)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 32
@32
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop this 2
@THIS
D=M
@2
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 46
@46
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop that 6
@THAT
D=M
@6
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push pointer 0
@0
D=A
@POINTER8
D;JEQ
@THAT
D=A
@ADDSTACK8
0;JMP
(POINTER8)
@THIS
D=A
(ADDSTACK8)
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// push pointer 1
@1
D=A
@POINTER9
D;JEQ
@THAT
D=A
@ADDSTACK9
0;JMP
(POINTER9)
@THIS
D=A
(ADDSTACK9)
A=D
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

// push this 2
@THIS
D=M
@2
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

// push that 6
@THAT
D=M
@6
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

