// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// eq
@SP
A=M-1
D=M
M=0
A=A-1
D=M-D
@EQUAL2
D;JEQ
D=0
@SET2
0;JMP
(EQUAL2)
D=-1
(SET2)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// eq
@SP
A=M-1
D=M
M=0
A=A-1
D=M-D
@EQUAL5
D;JEQ
D=0
@SET5
0;JMP
(EQUAL5)
D=-1
(SET5)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// eq
@SP
A=M-1
D=M
M=0
A=A-1
D=M-D
@EQUAL8
D;JEQ
D=0
@SET8
0;JMP
(EQUAL8)
D=-1
(SET8)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 891
@891
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
@EQUAL11
D;JLT
D=0
@SET11
0;JMP
(EQUAL11)
D=-1
(SET11)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 892
@892
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
@EQUAL14
D;JLT
D=0
@SET14
0;JMP
(EQUAL14)
D=-1
(SET14)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 891
@891
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
@EQUAL17
D;JLT
D=0
@SET17
0;JMP
(EQUAL17)
D=-1
(SET17)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// gt
@SP
A=M-1
D=M
M=0
A=A-1
D=M-D
@EQUAL20
D;JGT
D=0
@SET20
0;JMP
(EQUAL20)
D=-1
(SET20)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// gt
@SP
A=M-1
D=M
M=0
A=A-1
D=M-D
@EQUAL23
D;JGT
D=0
@SET23
0;JMP
(EQUAL23)
D=-1
(SET23)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// gt
@SP
A=M-1
D=M
M=0
A=A-1
D=M-D
@EQUAL26
D;JGT
D=0
@SET26
0;JMP
(EQUAL26)
D=-1
(SET26)
@SP
A=M-1
A=A-1
M=D
@SP
M=M-1

// push constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 53
@53
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

// push constant 112
@112
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

// neg
@SP
A=M-1
M=-M

// and
@SP
A=M-1
D=M
M=0
A=A-1
M=D&M
@SP
M=M-1

// push constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1

// or
@SP
A=M-1
D=M
M=0
A=A-1
M=D|M
@SP
M=M-1

// not
@SP
A=M-1
M=!M

