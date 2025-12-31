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

// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 8
@8
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Class1.set 2
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
@2
D=D-A
@ARG
M=D
@Class1.set
0;JMP
(Sys.init$ret.1)

// pop temp 0
@0
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

// push constant 23
@23
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 15
@15
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Class2.set 2
@Sys.init$ret.2
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
@2
D=D-A
@ARG
M=D
@Class2.set
0;JMP
(Sys.init$ret.2)

// pop temp 0
@0
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

// call Class1.get 0
@Sys.init$ret.3
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
@0
D=D-A
@ARG
M=D
@Class1.get
0;JMP
(Sys.init$ret.3)

// call Class2.get 0
@Sys.init$ret.4
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
@0
D=D-A
@ARG
M=D
@Class2.get
0;JMP
(Sys.init$ret.4)

// label END
(Sys.init$END)

// goto END
@Sys.init$END
0;JMP

// function Class1.set 0
(Class1.set)

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

// pop static 0
@SP
AM=M-1
D=M
@Class10
M=D

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

// pop static 1
@SP
AM=M-1
D=M
@Class11
M=D

// push constant 0
@0
D=A
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

// function Class1.get 0
(Class1.get)

// push static 0
@Class10
D=M
@SP
A=M
M=D
@SP
M=M+1

// push static 1
@Class11
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

// function Class2.set 0
(Class2.set)

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

// pop static 0
@SP
AM=M-1
D=M
@Class20
M=D

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

// pop static 1
@SP
AM=M-1
D=M
@Class21
M=D

// push constant 0
@0
D=A
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

// function Class2.get 0
(Class2.get)

// push static 0
@Class20
D=M
@SP
A=M
M=D
@SP
M=M+1

// push static 1
@Class21
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

