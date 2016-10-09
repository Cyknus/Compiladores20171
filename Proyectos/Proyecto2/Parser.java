//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "Arith.y"
  import java.lang.Math;
  import java.io.*;
//#line 20 "Parser.java"




public class Parser
             implements ParserTokens
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    3,    5,    5,    6,
    6,    6,    9,    9,    7,    4,    4,   10,   13,   13,
   14,   14,   11,   12,   12,   15,   15,    8,   16,   18,
   18,   17,   20,   20,   19,   19,   21,   21,   23,   24,
   24,   24,   24,   24,   24,   24,   24,   22,   26,   26,
   25,   28,   28,   28,   28,   28,   27,   27,   29,   29,
   30,   32,   32,   31,   31,   31,   31,   31,
};
final static short yylen[] = {                            2,
    1,    2,    0,    1,    1,    1,    2,    1,    1,    1,
    3,    3,    1,    1,    2,    1,    1,    6,    5,    0,
    3,    0,    4,    1,    4,    2,    1,    1,    2,    3,
    0,    2,    3,    0,    2,    1,    2,    1,    3,    1,
    1,    1,    1,    1,    1,    1,    2,    2,    5,    3,
    2,    3,    3,    3,    3,    0,    2,    1,    1,    1,
    2,    2,    0,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,   64,   65,   67,   66,   68,    0,   59,   60,
    0,    0,    0,    2,    4,    5,    0,    8,    9,    0,
   16,   17,   28,   31,   34,   36,   38,    0,   56,    0,
   58,    0,   15,    0,    0,   35,    7,    0,   13,   14,
    0,    0,    0,    0,    0,    0,   57,    0,   61,    0,
    0,   11,   12,    0,    0,   40,   41,   44,   43,   42,
   45,    0,   46,    0,    0,    0,    0,    0,    0,    0,
   62,    0,   24,   20,   23,   30,   33,   47,   39,    0,
   50,   52,   55,   53,   54,    0,    0,    0,   27,    0,
    0,    0,   18,   49,   25,   26,    0,    0,    0,   21,
   19,
};
final static short yydgoto[] = {                          1,
    2,   14,   73,   16,   17,   18,   19,   20,   41,   21,
   22,   74,   87,   93,   90,   23,   24,   42,   25,   43,
   26,   27,   44,   64,   28,   45,   29,   46,   30,   31,
   32,   49,
};
final static short yysindex[] = {                         0,
    0, -190,    0,    0,    0,    0,    0, -155,    0,    0,
 -155, -155, -155,    0,    0,    0, -234,    0,    0, -259,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -145,
    0, -238,    0, -249, -227,    0,    0, -155,    0,    0,
 -155, -239, -226, -254, -237, -229,    0, -145,    0, -250,
 -250,    0,    0, -155, -155,    0,    0,    0,    0,    0,
    0, -224,    0, -145, -145, -145, -145, -145, -145, -145,
    0, -186,    0,    0,    0,    0,    0,    0,    0, -219,
    0,    0,    0,    0,    0, -190, -241, -187,    0, -201,
 -155, -203,    0,    0,    0,    0, -202, -250, -250,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,   82,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  188,    0,    0,  178,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  144,  110,  236,   75,   38,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  221,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  -62,   -2,    0,    0,    0,    0,   -6,    0,    0,
    0,  -48,    0,    0,    0,    0,   33,    0,   -9,    0,
    0,   26,    0,    0,  -18,    0,  -15,    0,    0,    0,
    0,    0,
};
final static int YYTABLESIZE=525;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         15,
   63,   33,   75,   36,   34,   35,   72,    3,    4,    5,
    6,    7,    8,   38,   47,    9,   10,   39,   40,   56,
   57,   58,   37,   89,   59,   60,   61,   96,   65,   66,
   48,   52,   71,   50,   53,   62,   63,   51,   67,   13,
   68,   69,   70,   91,   92,   77,   80,   81,   54,  100,
  101,   82,   83,   84,   85,   51,    3,    4,    5,    6,
    7,    8,   55,   95,    9,   10,   78,    3,    4,    5,
    6,    7,    8,   88,   48,    9,   10,   86,   94,   98,
   99,    1,   11,   15,   97,   12,   76,   15,   13,   79,
    0,    0,    0,   11,    0,    0,   12,    0,    0,   13,
    0,    0,    3,    4,    5,    6,    7,    0,    0,   32,
    9,   10,    3,    4,    5,    6,    7,    0,    0,    0,
    9,   10,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   13,    0,    0,    0,    0,    0,
    0,    0,    0,   29,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   10,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   22,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   37,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   63,   63,   63,
   63,   63,   63,   63,    0,   63,   63,   63,   63,    0,
   63,   63,   63,   63,   63,   63,   63,   63,   63,   63,
   63,   63,    0,   63,   63,   63,   63,   63,   63,   63,
   63,   63,    0,   63,   51,   51,   51,   51,   51,   51,
   51,    0,   51,   51,   51,    0,    0,    0,    0,    0,
   51,   51,   51,   51,   51,   51,   51,   51,   51,    0,
   51,   51,   51,   51,   51,   51,   51,   51,   51,    0,
   51,   48,   48,   48,   48,   48,   48,   48,    0,   48,
    0,    0,    0,    0,    0,    0,    0,   48,   48,   48,
   48,   48,   48,   48,   48,   48,    0,   48,   48,   48,
   48,   48,   48,   48,   48,   48,   32,   32,   32,   32,
   32,   32,   32,    0,   32,   32,   32,    0,    0,    0,
    0,    0,   32,    0,    0,    0,   32,   32,    0,    0,
    0,    0,   32,   32,   32,   32,   32,   32,    0,   32,
   29,   29,   29,   29,   29,   29,   29,    0,   29,   29,
   29,    0,    0,    0,    0,    0,   29,    0,    0,    0,
   29,   29,    0,    0,    0,    0,   29,   29,   29,   29,
   29,    0,    0,   29,   10,   10,   10,   10,   10,   10,
   10,    0,   10,   10,   10,    6,    6,    6,    6,    6,
    6,    0,    6,    6,    6,    0,    0,    0,    0,    0,
    0,   10,   10,   10,   10,    0,    0,   10,    0,    0,
    0,    6,    6,    6,    6,    0,    0,    6,   22,   22,
   22,   22,   22,   22,    0,   22,   22,   22,    0,    0,
    0,    0,   37,   37,   37,   37,   37,   37,   37,    0,
   37,   37,   37,    0,   22,    0,    0,   22,   37,    0,
   22,    0,   37,   37,    0,    0,    0,    0,   37,   37,
   37,   37,   37,   37,   37,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          2,
    0,    8,   51,   13,   11,   12,  257,  258,  259,  260,
  261,  262,  263,  273,   30,  266,  267,  277,  278,  274,
  275,  276,  257,   86,  279,  280,  281,   90,  266,  267,
  269,   38,   48,  283,   41,  290,  291,    0,  268,  290,
  270,  271,  272,  285,  286,   55,   65,   66,  288,   98,
   99,   67,   68,   69,   70,  283,  258,  259,  260,  261,
  262,  263,  289,  265,  266,  267,  291,  258,  259,  260,
  261,  262,  263,  293,    0,  266,  267,  264,  266,  283,
  283,    0,  284,   86,   91,  287,   54,   90,  290,   64,
   -1,   -1,   -1,  284,   -1,   -1,  287,   -1,   -1,  290,
   -1,   -1,  258,  259,  260,  261,  262,   -1,   -1,    0,
  266,  267,  258,  259,  260,  261,  262,   -1,   -1,   -1,
  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  290,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,    0,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,    0,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,    0,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
    0,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,    0,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,   -1,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  281,   -1,  283,  284,  285,  286,  287,  288,  289,
  290,  291,   -1,  293,  257,  258,  259,  260,  261,  262,
  263,   -1,  265,  266,  267,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  279,  280,  281,   -1,
  283,  284,  285,  286,  287,  288,  289,  290,  291,   -1,
  293,  257,  258,  259,  260,  261,  262,  263,   -1,  265,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  279,  280,  281,   -1,  283,  284,  285,
  286,  287,  288,  289,  290,  291,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,  266,  267,   -1,   -1,   -1,
   -1,   -1,  273,   -1,   -1,   -1,  277,  278,   -1,   -1,
   -1,   -1,  283,  284,  285,  286,  287,  288,   -1,  290,
  257,  258,  259,  260,  261,  262,  263,   -1,  265,  266,
  267,   -1,   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,
  277,  278,   -1,   -1,   -1,   -1,  283,  284,  285,  286,
  287,   -1,   -1,  290,  257,  258,  259,  260,  261,  262,
  263,   -1,  265,  266,  267,  258,  259,  260,  261,  262,
  263,   -1,  265,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,  284,  285,  286,  287,   -1,   -1,  290,   -1,   -1,
   -1,  284,  285,  286,  287,   -1,   -1,  290,  258,  259,
  260,  261,  262,  263,   -1,  265,  266,  267,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,  263,   -1,
  265,  266,  267,   -1,  284,   -1,   -1,  287,  273,   -1,
  290,   -1,  277,  278,   -1,   -1,   -1,   -1,  283,  284,
  285,  286,  287,  288,  289,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=293;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"NEWLINE","IDENTIFIER","ENTERO","REAL","CADENA","BOOLEAN",
"PRINT","INDENT","DEDENT","MAS","MENOS","POR","POTENCIA","DIV","DIVENTERA",
"MODULO","EQ","MENORQ","MAYORQ","MENOREQ","MASEQ","MENOSEQ","MAYOREQ","EQEQ",
"DIF","PUNTCOMA","DOSPUNT","IF","ELIF","ELSE","WHILE","OR","AND","NOT","IN",
"THEN","$prec",
};
final static String yyrule[] = {
"$accept : file_input",
"file_input : aux0",
"aux0 : aux0 stmt",
"aux0 :",
"stmt : simple_stmt",
"stmt : compound_stmt",
"simple_stmt : small_stmt",
"simple_stmt : small_stmt NEWLINE",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : test",
"expr_stmt : test EQ test",
"expr_stmt : test augassign test",
"augassign : MASEQ",
"augassign : MENOSEQ",
"print_stmt : PRINT test",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : IF test DOSPUNT suite if_stmt_aux aux_else",
"if_stmt_aux : if_stmt_aux ELIF test DOSPUNT suite",
"if_stmt_aux :",
"aux_else : ELSE DOSPUNT suite",
"aux_else :",
"while_stmt : WHILE test DOSPUNT suite",
"suite : simple_stmt",
"suite : NEWLINE INDENT aux2 DEDENT",
"aux2 : aux2 stmt",
"aux2 : stmt",
"test : or_test",
"or_test : and_test aux3",
"aux3 : aux3 OR and_test",
"aux3 :",
"and_test : not_test aux4",
"aux4 : aux4 AND not_test",
"aux4 :",
"not_test : NOT not_test",
"not_test : comparison",
"comparison : expr aux5",
"comparison : expr",
"aux5 : aux5 comp_op expr",
"comp_op : MENORQ",
"comp_op : MAYORQ",
"comp_op : EQEQ",
"comp_op : MAYOREQ",
"comp_op : MENOREQ",
"comp_op : DIF",
"comp_op : IN",
"comp_op : NOT IN",
"expr : term aux6",
"aux6 : aux6 MAS term $prec MAS",
"aux6 : aux6 MENOS term",
"term : factor aux7",
"aux7 : aux7 POR factor",
"aux7 : aux7 DIVENTERA factor",
"aux7 : aux7 MODULO factor",
"aux7 : aux7 DIV factor",
"aux7 :",
"factor : factor_aux factor",
"factor : power",
"factor_aux : MAS",
"factor_aux : MENOS",
"power : atom power_aux",
"power_aux : POTENCIA factor",
"power_aux :",
"atom : IDENTIFIER",
"atom : ENTERO",
"atom : CADENA",
"atom : REAL",
"atom : BOOLEAN",
};

//#line 169 "Arith.y"
/* a reference to the lexer object */
private Flexer lexer;

/* interface to the lexer */
private int yylex () {
    int yyl_return = -1;
    try {
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
}

/* error reporting */ 
public void yyerror (String error) {
    System.err.println ("Error: " + error);
}

/* lexer is created in the constructor */
public Parser(Reader r) {
    lexer = new Flexer(r, this);
}

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse();    
}

//#line 444 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 23 "Arith.y"
{ System.out.println("Reconocimiento Exitoso");}
break;
//#line 597 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
