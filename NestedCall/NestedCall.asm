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

// push constant 4000
@4000
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 0
@0
D=A
@Sys.init$POINTER2
D;JEQ
@THAT
D=A
@Sys.init$POPSTACK2
0;JMP
(Sys.init$POINTER2)
@THIS
D=A
(Sys.init$POPSTACK2)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 5000
@5000
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 1
@1
D=A
@Sys.init$POINTER4
D;JEQ
@THAT
D=A
@Sys.init$POPSTACK4
0;JMP
(Sys.init$POINTER4)
@THIS
D=A
(Sys.init$POPSTACK4)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// call Sys.main 0
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
@0
D=D-A
@ARG
M=D
@Sys.main
0;JMP
(Sys.init$ret.1)

// pop temp 1
@1
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

// label LOOP
(Sys.init$LOOP)

// goto LOOP
@Sys.init$LOOP
0;JMP

// function Sys.main 5
(Sys.main)
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
@0
D=A
@SP
M=M+1
A=M-1
M=D

// push constant 4001
@4001
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 0
@0
D=A
@Sys.main$POINTER11
D;JEQ
@THAT
D=A
@Sys.main$POPSTACK11
0;JMP
(Sys.main$POINTER11)
@THIS
D=A
(Sys.main$POPSTACK11)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 5001
@5001
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 1
@1
D=A
@Sys.main$POINTER13
D;JEQ
@THAT
D=A
@Sys.main$POPSTACK13
0;JMP
(Sys.main$POINTER13)
@THIS
D=A
(Sys.main$POPSTACK13)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 200
@200
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop local 1
@LCL
D=M
@1
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 40
@40
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop local 2
@LCL
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

// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop local 3
@LCL
D=M
@3
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 123
@123
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Sys.add12 1
@Sys.main$ret.2
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
@Sys.add12
0;JMP
(Sys.main$ret.2)

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

// push local 2
@LCL
D=M
@2
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push local 3
@LCL
D=M
@3
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push local 4
@LCL
D=M
@4
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

// add
@SP
A=M-1
D=M
M=0
A=A-1
M=D+M
@SP
M=M-1

// add
@SP
A=M-1
D=M
M=0
A=A-1
M=D+M
@SP
M=M-1

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

// function Sys.add12 0
(Sys.add12)

// push constant 4002
@4002
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 0
@0
D=A
@Sys.add12$POINTER35
D;JEQ
@THAT
D=A
@Sys.add12$POPSTACK35
0;JMP
(Sys.add12$POINTER35)
@THIS
D=A
(Sys.add12$POPSTACK35)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

// push constant 5002
@5002
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 1
@1
D=A
@Sys.add12$POINTER37
D;JEQ
@THAT
D=A
@Sys.add12$POPSTACK37
0;JMP
(Sys.add12$POINTER37)
@THIS
D=A
(Sys.add12$POPSTACK37)
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D

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

// push constant 12
@12
D=A
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

