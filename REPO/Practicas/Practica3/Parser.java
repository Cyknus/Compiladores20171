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






//#line 2 "Parser.y"
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
    0,    0,    1,    1,    1,    1,    2,    2,    3,    5,
    5,    6,    6,    9,    9,   10,   10,    7,    4,    4,
   11,   11,   11,   14,   14,   12,   13,   13,   15,   15,
    8,   16,   16,   18,   18,   17,   17,   20,   20,   19,
   19,   21,   21,   23,   23,   24,   24,   24,   24,   24,
   24,   24,   24,   22,   22,   26,   26,   26,   26,   25,
   25,   28,   28,   28,   28,   28,   28,   28,   28,   27,
   27,   27,   29,   29,   30,   30,   30,   30,   30,
};
final static short yylen[] = {                            2,
    0,    1,    1,    2,    1,    2,    1,    1,    2,    1,
    1,    1,    2,    2,    2,    1,    1,    2,    1,    1,
    4,    7,    8,    4,    5,    4,    1,    4,    1,    2,
    1,    1,    2,    2,    3,    1,    2,    2,    3,    2,
    1,    1,    2,    2,    3,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    2,    2,    2,    3,    3,    1,
    2,    2,    2,    2,    2,    3,    3,    3,    3,    2,
    2,    1,    1,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    5,   75,   76,   78,   77,   79,    0,    0,    0,    0,
    0,    0,    0,    0,    3,    7,    8,    0,   10,   11,
    0,   19,   20,   31,    0,    0,   41,    0,    0,    0,
   72,    0,   18,   70,   71,    0,    0,   40,    6,    4,
    9,    0,   16,   17,   13,    0,    0,    0,    0,    0,
   46,   47,   50,   49,   48,   51,   52,   53,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   14,   15,   34,    0,   38,    0,    0,   44,   56,
   57,    0,    0,   62,   65,   63,   64,    0,    0,    0,
    0,   74,    0,   27,    0,   26,   35,   39,   45,   58,
   59,   66,   69,   67,   68,    0,    0,    0,    0,   29,
    0,    0,    0,    0,    0,   28,   30,    0,   22,    0,
    0,   24,    0,   23,   25,
};
final static short yydgoto[] = {                         13,
   14,   15,   94,   17,   18,   19,   20,   21,   45,   46,
   22,   23,   95,  109,  111,   24,   25,   48,   26,   50,
   27,   28,   59,   60,   29,   63,   30,   68,   31,   32,
};
final static short yysindex[] = {                      -240,
    0,    0,    0,    0,    0,    0, -184, -168, -168, -184,
 -184, -184,    0,  -15,    0,    0,    0, -245,    0,    0,
 -193,    0,    0,    0, -263, -255,    0,  101, -226, -257,
    0, -223,    0,    0,    0, -228, -225,    0,    0,    0,
    0, -184,    0,    0,    0, -184, -184, -222, -184, -246,
    0,    0,    0,    0,    0,    0,    0,    0,  101, -168,
 -168, -168, -214, -168, -168, -168, -168,  -38, -168,   20,
   20,    0,    0,    0, -184,    0, -184, -168,    0,    0,
    0, -168, -168,    0,    0,    0,    0, -168, -168, -168,
 -168,    0, -196,    0, -190,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -230, -184, -212, -182,    0,
   34, -211,   20, -184, -204,    0,    0,   20,    0, -202,
   20,    0,   20,    0,    0,
};
final static short yyrindex[] = {                       105,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  109,    0,    0,    0,    0,    0,    0,
 -131,    0,    0,    0,   88,   66,    0,   49,  -77, -129,
    0, -156,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   91,    0,   74,
    0,    0,    0,    0,    0,    0,    0,    0,   52,    0,
    0,    0,  -52,    0,    0,    0,    0, -102,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   -9,    2,    0,    0,    0,    0,   -7,    0,    0,
    0,    0,  -62,    0,    0,    0,  -37,    0,   -4,    0,
    0,  -36,    0,   70,  -13,    0,   -2,    0,    0,    0,
};
final static int YYTABLESIZE=393;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   21,   16,   36,   37,   40,   34,   35,   38,   96,   74,
   64,   41,   65,   66,   67,   16,    1,    2,    3,    4,
    5,    6,    7,   79,   47,    8,    9,    2,    3,    4,
    5,    6,    7,   49,   72,    8,    9,   97,   73,   61,
   62,   99,   77,   10,   76,   69,   11,   80,   81,   12,
  119,   82,   83,   10,   70,  122,   11,   71,  124,   12,
  125,   84,   85,   86,   87,   75,   92,  106,  100,  101,
  113,  118,   98,    2,    3,    4,    5,    6,  121,   42,
  123,    8,    9,   43,   44,  102,  103,  104,  105,    2,
    3,    4,    5,    6,  107,  108,  110,    8,    9,  112,
   73,  117,  114,  115,    1,   12,  120,   16,    2,   73,
   73,   73,   16,   73,   73,   73,   73,   73,   73,   73,
   73,   73,   73,   73,   73,   12,   73,   60,   78,    0,
    0,   73,   73,    0,   73,   73,   60,   60,    0,    0,
    0,    0,    0,   60,   60,   60,   60,   60,   60,   60,
   60,   60,    0,   60,   61,    0,    0,    0,   60,   60,
    0,   60,   60,   61,   61,    0,    0,    0,    0,    0,
   61,   61,   61,   61,   61,   61,   61,   61,   61,   54,
   61,    0,    0,    0,    0,   61,   61,    0,   61,   61,
    0,    0,    0,    0,    0,   54,   54,   54,   54,   54,
   54,   54,   54,   54,   55,   54,    0,    0,    0,    0,
   54,   54,    0,   54,   54,    0,    0,    0,    0,    0,
   55,   55,   55,   55,   55,   55,   55,   55,   55,   88,
   55,   89,   90,   91,    0,   55,   55,    0,   55,   55,
    0,   39,    2,    3,    4,    5,    6,    7,    0,    0,
    8,    9,    0,    0,    0,    0,    0,   21,   21,   21,
   21,   21,   21,   21,    0,   21,   21,   21,   10,    0,
    0,   11,    0,    0,   12,    0,   93,    2,    3,    4,
    5,    6,    7,    0,   21,    8,    9,   21,    0,    0,
   21,    2,    3,    4,    5,    6,    7,    0,  116,    8,
    9,    0,    0,    0,    0,   42,    0,    0,   43,   12,
    0,    0,    0,    0,    0,    0,    0,   10,    0,    0,
   11,   42,   36,   12,   43,   42,   42,    0,   43,   43,
   37,   42,    0,    0,   43,    0,   42,   42,   36,   43,
   43,    0,   36,   36,   32,    0,   37,   33,   36,    0,
   37,   37,    0,   36,    0,    0,   37,    0,    0,    0,
   32,   37,    0,   33,   32,   32,    0,   33,   33,    0,
   32,    0,    0,   33,   51,   52,   53,    0,    0,   54,
   55,   56,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   57,   58,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          7,
    0,    0,   10,   11,   14,    8,    9,   12,   71,   47,
  268,  257,  270,  271,  272,   14,  257,  258,  259,  260,
  261,  262,  263,   60,  288,  266,  267,  258,  259,  260,
  261,  262,  263,  289,   42,  266,  267,   75,   46,  266,
  267,   78,  289,  284,   49,  269,  287,   61,   62,  290,
  113,  266,  267,  284,  283,  118,  287,  283,  121,  290,
  123,   64,   65,   66,   67,  288,   69,  264,   82,   83,
  283,  283,   77,  258,  259,  260,  261,  262,  283,  273,
  283,  266,  267,  277,  278,   88,   89,   90,   91,  258,
  259,  260,  261,  262,  285,  286,  106,  266,  267,  107,
  257,  111,  285,  286,    0,  290,  114,  106,    0,  266,
  267,  268,  111,  270,  271,  272,  273,  274,  275,  276,
  277,  278,  279,  280,  281,  257,  283,  257,   59,   -1,
   -1,  288,  289,   -1,  291,  292,  266,  267,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  279,
  280,  281,   -1,  283,  257,   -1,   -1,   -1,  288,  289,
   -1,  291,  292,  266,  267,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  279,  280,  281,  257,
  283,   -1,   -1,   -1,   -1,  288,  289,   -1,  291,  292,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,  279,  280,  281,  257,  283,   -1,   -1,   -1,   -1,
  288,  289,   -1,  291,  292,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  279,  280,  281,  268,
  283,  270,  271,  272,   -1,  288,  289,   -1,  291,  292,
   -1,  257,  258,  259,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,  266,  267,  284,   -1,
   -1,  287,   -1,   -1,  290,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,  284,  266,  267,  287,   -1,   -1,
  290,  258,  259,  260,  261,  262,  263,   -1,  265,  266,
  267,   -1,   -1,   -1,   -1,  257,   -1,   -1,  257,  290,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,   -1,   -1,
  287,  273,  257,  290,  273,  277,  278,   -1,  277,  278,
  257,  283,   -1,   -1,  283,   -1,  288,  289,  273,  288,
  289,   -1,  277,  278,  257,   -1,  273,  257,  283,   -1,
  277,  278,   -1,  288,   -1,   -1,  283,   -1,   -1,   -1,
  273,  288,   -1,  273,  277,  278,   -1,  277,  278,   -1,
  283,   -1,   -1,  283,  274,  275,  276,   -1,   -1,  279,
  280,  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  291,  292,
};
}
final static short YYFINAL=13;
final static short YYMAXTOKEN=292;
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
"NOTIN",
};
final static String yyrule[] = {
"$accept : file_input",
"file_input :",
"file_input : aux0",
"aux0 : stmt",
"aux0 : aux0 stmt",
"aux0 : NEWLINE",
"aux0 : aux0 NEWLINE",
"stmt : simple_stmt",
"stmt : compound_stmt",
"simple_stmt : small_stmt NEWLINE",
"small_stmt : expr_stmt",
"small_stmt : print_stmt",
"expr_stmt : test",
"expr_stmt : test aux1",
"aux1 : EQ test",
"aux1 : augassign test",
"augassign : MASEQ",
"augassign : MENOSEQ",
"print_stmt : PRINT test",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : IF test DOSPUNT suite",
"if_stmt : IF test DOSPUNT suite ELSE DOSPUNT suite",
"if_stmt : IF test DOSPUNT suite if_stmt_aux ELSE DOSPUNT suite",
"if_stmt_aux : ELIF test DOSPUNT suite",
"if_stmt_aux : if_stmt_aux ELIF test DOSPUNT suite",
"while_stmt : WHILE test DOSPUNT suite",
"suite : simple_stmt",
"suite : NEWLINE INDENT aux2 DEDENT",
"aux2 : stmt",
"aux2 : aux2 stmt",
"test : or_test",
"or_test : and_test",
"or_test : and_test aux3",
"aux3 : OR and_test",
"aux3 : aux3 OR and_test",
"and_test : not_test",
"and_test : not_test aux4",
"aux4 : AND not_test",
"aux4 : aux4 AND not_test",
"not_test : NOT not_test",
"not_test : comparison",
"comparison : expr",
"comparison : expr aux5",
"aux5 : comp_op expr",
"aux5 : aux5 comp_op expr",
"comp_op : MENORQ",
"comp_op : MAYORQ",
"comp_op : EQEQ",
"comp_op : MAYOREQ",
"comp_op : MENOREQ",
"comp_op : DIF",
"comp_op : IN",
"comp_op : NOTIN",
"expr : term",
"expr : term aux6",
"aux6 : MAS term",
"aux6 : MENOS term",
"aux6 : aux6 MAS term",
"aux6 : aux6 MENOS term",
"term : factor",
"term : factor aux7",
"aux7 : POR factor",
"aux7 : DIVENTERA factor",
"aux7 : MODULO factor",
"aux7 : DIV factor",
"aux7 : aux7 POR factor",
"aux7 : aux7 DIVENTERA factor",
"aux7 : aux7 MODULO factor",
"aux7 : aux7 DIV factor",
"factor : MAS factor",
"factor : MENOS factor",
"factor : power",
"power : atom",
"power : atom POTENCIA factor",
"atom : IDENTIFIER",
"atom : ENTERO",
"atom : CADENA",
"atom : REAL",
"atom : BOOLEAN",
};

//#line 185 "Parser.y"
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

//#line 434 "Parser.java"
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
//#line 21 "Parser.y"
{ System.out.println("Reconocimiento Exitoso");}
break;
case 2:
//#line 22 "Parser.y"
{yyval = val_peek(0);}
break;
case 3:
//#line 25 "Parser.y"
{yyval = val_peek(0);}
break;
case 7:
//#line 32 "Parser.y"
{yyval = val_peek(0);}
break;
case 8:
//#line 33 "Parser.y"
{yyval = val_peek(0);}
break;
case 9:
//#line 37 "Parser.y"
{yyval = val_peek(1);}
break;
case 10:
//#line 41 "Parser.y"
{yyval = val_peek(0);}
break;
case 11:
//#line 42 "Parser.y"
{yyval = val_peek(0);}
break;
case 12:
//#line 46 "Parser.y"
{yyval = val_peek(0);}
break;
case 19:
//#line 65 "Parser.y"
{yyval = val_peek(0);}
break;
case 20:
//#line 66 "Parser.y"
{yyval = val_peek(0);}
break;
case 21:
//#line 70 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(3).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 24:
//#line 75 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(3).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 26:
//#line 82 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(3).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 27:
//#line 86 "Parser.y"
{yyval = val_peek(0);}
break;
case 28:
//#line 87 "Parser.y"
{yyval = val_peek(1);}
break;
case 29:
//#line 90 "Parser.y"
{yyval = val_peek(0);}
break;
case 30:
//#line 91 "Parser.y"
{yyval = val_peek(1); yyval = val_peek(0);}
break;
case 31:
//#line 95 "Parser.y"
{yyval = val_peek(0);}
break;
case 32:
//#line 99 "Parser.y"
{yyval = val_peek(0);}
break;
case 33:
//#line 100 "Parser.y"
{yyval = val_peek(1); yyval = val_peek(0);}
break;
case 34:
//#line 103 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 35:
//#line 104 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 36:
//#line 108 "Parser.y"
{yyval = val_peek(0);}
break;
case 37:
//#line 109 "Parser.y"
{yyval = val_peek(1); yyval = val_peek(0);}
break;
case 38:
//#line 112 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 39:
//#line 113 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 40:
//#line 118 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 41:
//#line 119 "Parser.y"
{yyval = val_peek(0);}
break;
case 42:
//#line 123 "Parser.y"
{yyval = val_peek(0);}
break;
case 43:
//#line 124 "Parser.y"
{yyval = val_peek(1); yyval = val_peek(0);}
break;
case 44:
//#line 127 "Parser.y"
{yyval = val_peek(1);}
break;
case 45:
//#line 128 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 46:
//#line 132 "Parser.y"
{yyval = val_peek(0);}
break;
case 47:
//#line 133 "Parser.y"
{yyval = val_peek(0);}
break;
case 48:
//#line 134 "Parser.y"
{yyval = val_peek(0);}
break;
case 49:
//#line 135 "Parser.y"
{yyval = val_peek(0);}
break;
case 50:
//#line 136 "Parser.y"
{yyval = val_peek(0);}
break;
case 51:
//#line 137 "Parser.y"
{yyval = val_peek(0);}
break;
case 52:
//#line 138 "Parser.y"
{yyval = val_peek(0);}
break;
case 53:
//#line 139 "Parser.y"
{yyval = val_peek(0);}
break;
case 54:
//#line 143 "Parser.y"
{yyval = val_peek(0);}
break;
case 55:
//#line 144 "Parser.y"
{yyval = val_peek(1); yyval = val_peek(0);}
break;
case 56:
//#line 147 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 57:
//#line 148 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 58:
//#line 149 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 59:
//#line 150 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 60:
//#line 155 "Parser.y"
{yyval = val_peek(0);}
break;
case 61:
//#line 156 "Parser.y"
{yyval = val_peek(1); yyval = val_peek(0);}
break;
case 62:
//#line 158 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 63:
//#line 159 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 64:
//#line 160 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 65:
//#line 161 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 66:
//#line 162 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 67:
//#line 163 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 68:
//#line 164 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 69:
//#line 165 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 70:
//#line 168 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 71:
//#line 169 "Parser.y"
{Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 72:
//#line 170 "Parser.y"
{yyval = val_peek(0);}
break;
case 73:
//#line 173 "Parser.y"
{yyval = val_peek(0);}
break;
case 74:
//#line 174 "Parser.y"
{Nodo izq = (Nodo) val_peek(2).obj; Nodo n = (Nodo) val_peek(1).obj; Nodo der = (Nodo) val_peek(0).obj; n.setHijoIzq(izq); n.setHijoDer(der); yyval = new ParserVal(n);}
break;
case 75:
//#line 178 "Parser.y"
{yyval = val_peek(0);}
break;
case 76:
//#line 179 "Parser.y"
{yyval = val_peek(0);}
break;
case 77:
//#line 180 "Parser.y"
{yyval = val_peek(0);}
break;
case 78:
//#line 181 "Parser.y"
{yyval = val_peek(0);}
break;
case 79:
//#line 182 "Parser.y"
{yyval = val_peek(0);}
break;
//#line 851 "Parser.java"
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
