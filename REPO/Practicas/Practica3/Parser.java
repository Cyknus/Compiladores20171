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
//## **user defined:Nodo
String   yytext;//user variable to return contextual strings
Nodo yyval; //used to return semantic vals from action routines
Nodo yylval;//the 'lval' (result) I got from yylex()
Nodo valstk[] = new Nodo[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Nodo();
  yylval=new Nodo();
  valptr=-1;
}
final void val_push(Nodo val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Nodo[] newstack = new Nodo[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Nodo val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Nodo val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Nodo dup_yyval(Nodo val)
{
  return val;
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
   46,   47,   50,   49,   48,   51,   52,   53,   43,    0,
    0,    0,   55,    0,    0,    0,    0,    0,    0,    0,
    0,   14,   15,   34,    0,   38,    0,    0,    0,    0,
   62,   65,   63,   64,    0,    0,    0,    0,   74,    0,
   27,    0,   26,   35,   39,   45,   58,   59,   66,   69,
   67,   68,    0,    0,    0,    0,   29,    0,    0,    0,
    0,   28,   30,    0,   22,    0,    0,   23,   25,
};
final static short yydgoto[] = {                         13,
   14,   15,   91,   17,   18,   19,   20,   21,   45,   46,
   22,   23,   92,  106,  108,   24,   25,   48,   26,   50,
   27,   28,   59,   60,   29,   63,   30,   68,   31,   32,
};
final static short yysindex[] = {                      -240,
    0,    0,    0,    0,    0,    0, -190,  123,  123, -190,
 -190, -190,    0, -229,    0,    0,    0, -246,    0,    0,
 -263,    0,    0,    0, -275, -253,    0,   96, -242,   33,
    0, -226,    0,    0,    0, -237, -234,    0,    0,    0,
    0, -190,    0,    0,    0, -190, -190, -235, -190, -238,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  123,
  123,  123,    0,  123,  123,  123,  123,  124,  123,  -19,
  -19,    0,    0,    0, -190,    0, -190,   96, -242, -242,
    0,    0,    0,    0,  123,  123,  123,  123,    0, -198,
    0, -244,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   64, -190, -209, -208,    0,   54, -204,  -19,
 -203,    0,    0,  -19,    0,  -19, -197,    0,    0,
};
final static short yyrindex[] = {                        81,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   82,    0,    0,    0,    0,    0,    0,
 -170,    0,    0,    0,  -27, -182,    0,   -8,  -80, -132,
    0, -159,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   86,    0,   79,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -105,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   72,  -55,   19,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -196,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   -9,    2,    0,    0,    0,    0,   -7,    0,    0,
    0,    0,  -62,  -28,    0,    0,  -35,    0,   -4,    0,
    0,   32,   15,    0,   -5,  -20,   -2,    0,    0,    0,
};
final static int YYTABLESIZE=396;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   21,   16,   36,   37,   40,   34,   35,   38,   93,   42,
   41,   74,   47,   43,   44,   16,    1,    2,    3,    4,
    5,    6,    7,   61,   62,    8,    9,   39,    2,    3,
    4,    5,    6,    7,   72,   49,    8,    9,   73,   94,
  104,  105,   69,   10,   76,   70,   11,  115,   71,   12,
   77,  117,   75,  118,   10,   79,   80,   11,   97,   98,
   12,   81,   82,   83,   84,  103,   89,    2,    3,    4,
    5,    6,   95,  110,   36,    8,    9,  111,  114,  116,
    1,    2,   99,  100,  101,  102,   12,  104,  119,   24,
   36,   78,   96,  107,   36,   36,  109,   73,  113,   12,
   36,    0,    0,    0,   16,   36,   73,   73,   73,   16,
   73,   73,   73,   73,   73,   73,   73,   73,   73,   73,
   73,   73,    0,   73,   60,    0,    0,    0,   73,   73,
    0,   73,   73,   60,   60,    0,    0,    0,    0,    0,
   60,   60,   60,   60,   60,   60,   60,   60,   60,    0,
   60,   61,    0,    0,    0,   60,   60,    0,   60,   60,
   61,   61,    0,    0,    0,    0,    0,   61,   61,   61,
   61,   61,   61,   61,   61,   61,   54,   61,    0,    0,
    0,    0,   61,   61,    0,   61,   61,    0,    0,    0,
    0,    0,   54,   54,   54,   54,   54,   54,   54,   54,
   54,   56,   54,    0,    0,    0,    0,   54,   54,    0,
   54,   54,    0,    0,    0,    0,    0,   56,   56,   56,
   56,   56,   56,   56,   56,   56,    0,   56,    0,   32,
    0,    0,   56,   56,    0,   56,   56,   90,    2,    3,
    4,    5,    6,    7,    0,   32,    8,    9,   42,   32,
   32,    0,    0,    0,    0,   32,    0,   21,   21,   21,
   21,   21,   21,   21,   42,   21,   21,   21,   42,   42,
   12,    0,    0,    0,   42,   57,    0,    0,    0,   42,
   42,    0,    0,    0,   21,    0,    0,   21,    0,    0,
   21,   57,   57,   57,   57,   57,   57,   57,   57,   57,
   64,   57,   65,   66,   67,    0,   57,   57,    0,   57,
   57,    2,    3,    4,    5,    6,    7,    0,  112,    8,
    9,    2,    3,    4,    5,    6,    7,    0,   44,    8,
    9,    0,    0,    0,    0,   37,    0,   10,    0,    0,
   11,    0,   33,   12,   44,    0,    0,   10,   44,   44,
   11,   37,    0,   12,   44,   37,   37,    0,   33,   44,
   44,   37,   33,   33,    0,    0,   37,    0,   33,   51,
   52,   53,    0,    0,   54,   55,   56,    0,    0,    0,
    2,    3,    4,    5,    6,    0,   57,   58,    8,    9,
    0,   85,    0,   86,   87,   88,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          7,
    0,    0,   10,   11,   14,    8,    9,   12,   71,  273,
  257,   47,  288,  277,  278,   14,  257,  258,  259,  260,
  261,  262,  263,  266,  267,  266,  267,  257,  258,  259,
  260,  261,  262,  263,   42,  289,  266,  267,   46,   75,
  285,  286,  269,  284,   49,  283,  287,  110,  283,  290,
  289,  114,  288,  116,  284,   61,   62,  287,   79,   80,
  290,   64,   65,   66,   67,  264,   69,  258,  259,  260,
  261,  262,   77,  283,  257,  266,  267,  286,  283,  283,
    0,    0,   85,   86,   87,   88,  257,  285,  117,  286,
  273,   60,   78,  103,  277,  278,  104,  257,  108,  290,
  283,   -1,   -1,   -1,  103,  288,  266,  267,  268,  108,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  281,   -1,  283,  257,   -1,   -1,   -1,  288,  289,
   -1,  291,  292,  266,  267,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,  279,  280,  281,   -1,
  283,  257,   -1,   -1,   -1,  288,  289,   -1,  291,  292,
  266,  267,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  279,  280,  281,  257,  283,   -1,   -1,
   -1,   -1,  288,  289,   -1,  291,  292,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,  277,  278,  279,  280,
  281,  257,  283,   -1,   -1,   -1,   -1,  288,  289,   -1,
  291,  292,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  279,  280,  281,   -1,  283,   -1,  257,
   -1,   -1,  288,  289,   -1,  291,  292,  257,  258,  259,
  260,  261,  262,  263,   -1,  273,  266,  267,  257,  277,
  278,   -1,   -1,   -1,   -1,  283,   -1,  257,  258,  259,
  260,  261,  262,  263,  273,  265,  266,  267,  277,  278,
  290,   -1,   -1,   -1,  283,  257,   -1,   -1,   -1,  288,
  289,   -1,   -1,   -1,  284,   -1,   -1,  287,   -1,   -1,
  290,  273,  274,  275,  276,  277,  278,  279,  280,  281,
  268,  283,  270,  271,  272,   -1,  288,  289,   -1,  291,
  292,  258,  259,  260,  261,  262,  263,   -1,  265,  266,
  267,  258,  259,  260,  261,  262,  263,   -1,  257,  266,
  267,   -1,   -1,   -1,   -1,  257,   -1,  284,   -1,   -1,
  287,   -1,  257,  290,  273,   -1,   -1,  284,  277,  278,
  287,  273,   -1,  290,  283,  277,  278,   -1,  273,  288,
  289,  283,  277,  278,   -1,   -1,  288,   -1,  283,  274,
  275,  276,   -1,   -1,  279,  280,  281,   -1,   -1,   -1,
  258,  259,  260,  261,  262,   -1,  291,  292,  266,  267,
   -1,  268,   -1,  270,  271,  272,
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
"if_stmt_aux : ELIF test DOSPUNT suite if_stmt_aux",
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
"aux5 : comp_op expr aux5",
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
"aux6 : MAS term aux6",
"aux6 : MENOS term aux6",
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

//#line 184 "Parser.y"
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
    yydebug = true;
}

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse();    
}

//#line 432 "Parser.java"
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
{ System.out.println("Reconocimiento Exitoso");}
break;
//#line 589 "Parser.java"
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
