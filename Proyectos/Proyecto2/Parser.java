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
    0,    0,    1,    1,    2,    2,    3,    3,    5,    5,
    6,    6,    6,    9,    9,    7,    7,    4,    4,   10,
   10,   10,   13,   13,   11,   12,   12,   14,   14,    8,
   15,   15,   17,   17,   16,   16,   19,   19,   18,   18,
   20,   20,   22,   22,   23,   23,   23,   23,   23,   23,
   23,   23,   21,   21,   25,   25,   25,   25,   24,   24,
   27,   27,   27,   27,   27,   27,   27,   27,   26,   26,
   26,   28,   28,   29,   29,   29,   29,   29,
};
final static short yylen[] = {                            2,
    0,    1,    1,    2,    1,    1,    1,    2,    1,    1,
    1,    3,    3,    1,    1,    1,    2,    1,    1,    4,
    7,    8,    4,    5,    4,    1,    4,    1,    2,    1,
    1,    2,    2,    3,    1,    2,    2,    3,    2,    1,
    1,    2,    2,    3,    1,    1,    1,    1,    1,    1,
    1,    2,    1,    2,    2,    2,    3,    3,    1,    2,
    2,    2,    2,    2,    3,    3,    3,    3,    2,    2,
    1,    1,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   74,   75,   77,   76,   78,    0,    0,    0,    0,    0,
    0,    0,    0,    3,    5,    6,    0,    9,   10,    0,
   18,   19,   30,    0,    0,   40,    0,    0,    0,   71,
    0,   17,   69,   70,    0,    0,   39,    4,    8,    0,
   14,   15,    0,    0,    0,    0,    0,   45,   46,   49,
   48,   47,   50,    0,   51,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   12,   13,
   33,    0,   37,    0,   52,    0,   43,   55,   56,    0,
    0,   61,   64,   62,   63,    0,    0,    0,    0,   73,
    0,   26,    0,   25,   34,   38,   44,   57,   58,   65,
   68,   66,   67,    0,    0,    0,    0,   28,    0,    0,
    0,    0,    0,   27,   29,    0,   21,    0,    0,   23,
    0,   22,   24,
};
final static short yydgoto[] = {                         12,
   13,   14,   92,   16,   17,   18,   19,   20,   43,   21,
   22,   93,  107,  109,   23,   24,   45,   25,   47,   26,
   27,   56,   57,   28,   60,   29,   65,   30,   31,
};
final static short yysindex[] = {                      -145,
    0,    0,    0,    0,    0, -227, -112, -112, -227, -227,
 -227,    0, -145,    0,    0,    0, -245,    0,    0, -267,
    0,    0,    0, -246, -239,    0, -147, -237, -211,    0,
 -207,    0,    0,    0, -219, -217,    0,    0,    0, -227,
    0,    0, -227, -227, -209, -227, -205,    0,    0,    0,
    0,    0,    0, -206,    0, -147, -112, -112, -112, -222,
 -112, -112, -112, -112, -184, -112, -240, -240,    0,    0,
    0, -227,    0, -227,    0, -112,    0,    0,    0, -112,
 -112,    0,    0,    0,    0, -112, -112, -112, -112,    0,
 -175,    0, -234,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -145, -227, -193, -231,    0, -185, -186,
 -240, -227, -183,    0,    0, -240,    0, -182, -240,    0,
 -240,    0,    0,
};
final static short yyrindex[] = {                        98,
    0,    0,    0,    0,    0,  251,    0,    0,    0,    0,
    0,    0,  104,    0,    0,    0,  353,    0,    0,  318,
    0,    0,    0,  176,  210,    0,  423,  106,   36,    0,
    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  244,    0,  283,    0,    0,    0,
    0,    0,    0,    0,    0,  455,    0,    0,    0,  141,
    0,    0,    0,    0,   71,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  388,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -13,    3,    0,    0,    0,    0,   -2,    0,    0,
    0,  -63,    0,    0,    0,  -29,    0,   -9,    0,    0,
  -48,    0,   52,  -34,    0,    6,    0,    0,    0,
};
final static int YYTABLESIZE=743;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
   72,   37,   15,   32,   94,   40,   35,   36,   77,   41,
   42,   39,   33,   34,   71,   15,   91,    1,    2,    3,
    4,    5,    6,   78,   79,    7,    8,   97,   58,   59,
    1,    2,    3,    4,    5,   59,   73,   69,    7,    8,
   70,   44,   95,   80,   81,   98,   99,  117,   46,   11,
  105,  106,  120,  112,  113,  122,   61,  123,   62,   63,
   64,   66,   11,   67,   96,   68,   82,   83,   84,   85,
   60,   90,    1,    2,    3,    4,    5,    6,   72,  114,
    7,    8,   74,   86,   75,   87,   88,   89,  104,  111,
  108,  100,  101,  102,  103,  115,  116,    1,    9,  119,
  121,   10,  110,    2,   11,   53,   15,   76,    0,  118,
    0,   15,    1,    2,    3,    4,    5,    6,    0,    0,
    7,    8,    0,    0,    0,    0,   48,   49,   50,    0,
    0,   51,   52,   53,    0,    0,    0,    0,    9,    0,
   54,   10,   54,   55,   11,    1,    2,    3,    4,    5,
    0,    0,    0,    7,    8,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   31,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   35,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   32,    0,    0,    0,    0,    0,    0,
   16,    0,    0,    0,    0,    0,    0,   72,   72,   72,
   72,   72,   72,   72,    0,   72,   72,   72,   72,    0,
   72,   72,   72,   72,   72,   72,   72,   72,   72,   72,
   72,   72,   36,   72,   72,   72,   72,   72,   72,    0,
   72,   72,   59,   59,   59,   59,   59,   59,   59,    0,
   59,   59,   59,    0,    0,    0,    0,    0,   59,   59,
   59,   59,   59,   59,   59,   59,   59,   11,   59,   59,
   59,   59,   59,   59,    0,   59,   59,   60,   60,   60,
   60,   60,   60,   60,    0,   60,   60,   60,    0,    0,
    0,    0,    0,   60,   60,   60,   60,   60,   60,   60,
   60,   60,    7,   60,   60,   60,   60,   60,   60,    0,
   60,   60,   53,   53,   53,   53,   53,   53,   53,    0,
   53,    0,    0,    0,    0,    0,    0,    0,   53,   53,
   53,   53,   53,   53,   53,   53,   53,   20,   53,   53,
   53,   53,   53,   53,    0,   53,   53,   54,   54,   54,
   54,   54,   54,   54,    0,   54,    0,    0,    0,    0,
    0,    0,    0,   54,   54,   54,   54,   54,   54,   54,
   54,   54,   41,   54,   54,   54,   54,   54,   54,    0,
   54,   54,   31,   31,   31,   31,   31,   31,   31,    0,
   31,   31,   31,    0,    0,    0,    0,    0,   31,    0,
    0,    0,   31,   31,   42,    0,    0,    0,   31,   31,
   31,   31,   31,    0,    0,   31,   35,   35,   35,   35,
   35,   35,   35,    0,   35,   35,   35,    0,    0,    0,
    0,    0,   35,    0,    0,    0,   35,   35,    0,    0,
    0,    0,   35,   35,   35,   35,   35,    0,    0,   35,
   32,   32,   32,   32,   32,   32,   32,   16,   32,   32,
   32,    0,    0,   16,    0,   16,   32,    0,    0,    0,
   32,   32,    0,    0,    0,    0,   32,   32,   32,   32,
   32,    0,    0,   32,   16,   16,   16,   16,    0,   36,
   36,   36,   36,   36,   36,   36,    0,   36,   36,   36,
    0,    0,    0,    0,    0,   36,    0,    0,    0,   36,
   36,    0,    0,    0,    0,   36,   36,   36,   36,   36,
    0,    0,   36,    0,   11,   11,   11,   11,   11,   11,
   11,    0,   11,   11,   11,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   11,   11,   11,   11,    0,    0,   11,    0,    0,
    7,    7,    7,    7,    7,    7,    0,    7,    7,    7,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    7,    7,    7,    7,
    0,    0,    7,    0,    0,   20,   20,   20,   20,   20,
   20,    0,   20,   20,   20,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   20,    0,    0,   20,    0,    0,   20,    0,   41,
   41,   41,   41,   41,   41,   41,    0,   41,   41,   41,
    0,    0,    0,    0,    0,   41,    0,    0,    0,   41,
   41,    0,    0,    0,    0,   41,   41,   41,   41,   41,
   41,   42,   42,   42,   42,   42,   42,   42,    0,   42,
   42,   42,    0,    0,    0,    0,    0,   42,    0,    0,
    0,   42,   42,    0,    0,    0,    0,   42,   42,   42,
   42,   42,   42,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         13,
    0,   11,    0,    6,   68,  273,    9,   10,   57,  277,
  278,  257,    7,    8,   44,   13,  257,  258,  259,  260,
  261,  262,  263,   58,   59,  266,  267,   76,  266,  267,
  258,  259,  260,  261,  262,    0,   46,   40,  266,  267,
   43,  288,   72,  266,  267,   80,   81,  111,  288,  290,
  285,  286,  116,  285,  286,  119,  268,  121,  270,  271,
  272,  269,  290,  283,   74,  283,   61,   62,   63,   64,
    0,   66,  258,  259,  260,  261,  262,  263,  288,  265,
  266,  267,  288,  268,  291,  270,  271,  272,  264,  283,
  104,   86,   87,   88,   89,  109,  283,    0,  284,  283,
  283,  287,  105,    0,  290,    0,  104,   56,   -1,  112,
   -1,  109,  258,  259,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,   -1,   -1,   -1,  274,  275,  276,   -1,
   -1,  279,  280,  281,   -1,   -1,   -1,   -1,  284,   -1,
    0,  287,  290,  291,  290,  258,  259,  260,  261,  262,
   -1,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,    0,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,    0,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,    0,   -1,   -1,   -1,   -1,   -1,   -1,
    0,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,   -1,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  281,    0,  283,  284,  285,  286,  287,  288,   -1,
  290,  291,  257,  258,  259,  260,  261,  262,  263,   -1,
  265,  266,  267,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,  279,  280,  281,    0,  283,  284,
  285,  286,  287,  288,   -1,  290,  291,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,  266,  267,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  279,
  280,  281,    0,  283,  284,  285,  286,  287,  288,   -1,
  290,  291,  257,  258,  259,  260,  261,  262,  263,   -1,
  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,  279,  280,  281,    0,  283,  284,
  285,  286,  287,  288,   -1,  290,  291,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,  279,
  280,  281,    0,  283,  284,  285,  286,  287,  288,   -1,
  290,  291,  257,  258,  259,  260,  261,  262,  263,   -1,
  265,  266,  267,   -1,   -1,   -1,   -1,   -1,  273,   -1,
   -1,   -1,  277,  278,    0,   -1,   -1,   -1,  283,  284,
  285,  286,  287,   -1,   -1,  290,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,  266,  267,   -1,   -1,   -1,
   -1,   -1,  273,   -1,   -1,   -1,  277,  278,   -1,   -1,
   -1,   -1,  283,  284,  285,  286,  287,   -1,   -1,  290,
  257,  258,  259,  260,  261,  262,  263,  257,  265,  266,
  267,   -1,   -1,  263,   -1,  265,  273,   -1,   -1,   -1,
  277,  278,   -1,   -1,   -1,   -1,  283,  284,  285,  286,
  287,   -1,   -1,  290,  284,  285,  286,  287,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,  265,  266,  267,
   -1,   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,  277,
  278,   -1,   -1,   -1,   -1,  283,  284,  285,  286,  287,
   -1,   -1,  290,   -1,  257,  258,  259,  260,  261,  262,
  263,   -1,  265,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  284,  285,  286,  287,   -1,   -1,  290,   -1,   -1,
  258,  259,  260,  261,  262,  263,   -1,  265,  266,  267,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,  287,
   -1,   -1,  290,   -1,   -1,  258,  259,  260,  261,  262,
  263,   -1,  265,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  284,   -1,   -1,  287,   -1,   -1,  290,   -1,  257,
  258,  259,  260,  261,  262,  263,   -1,  265,  266,  267,
   -1,   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,  277,
  278,   -1,   -1,   -1,   -1,  283,  284,  285,  286,  287,
  288,  257,  258,  259,  260,  261,  262,  263,   -1,  265,
  266,  267,   -1,   -1,   -1,   -1,   -1,  273,   -1,   -1,
   -1,  277,  278,   -1,   -1,   -1,   -1,  283,  284,  285,
  286,  287,  288,
};
}
final static short YYFINAL=12;
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
"THEN",
};
final static String yyrule[] = {
"$accept : file_input",
"file_input :",
"file_input : aux0",
"aux0 : stmt",
"aux0 : aux0 stmt",
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
"print_stmt : PRINT",
"print_stmt : PRINT test",
"compound_stmt : if_stmt",
"compound_stmt : while_stmt",
"if_stmt : IF test DOSPUNT suite",
"if_stmt : IF test DOSPUNT suite ELSE DOSPUNT suite",
"if_stmt : IF test DOSPUNT suite aux1 ELSE DOSPUNT suite",
"aux1 : ELIF test DOSPUNT suite",
"aux1 : aux1 ELIF test DOSPUNT suite",
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
"aux4 : OR not_test",
"aux4 : aux4 OR not_test",
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
"comp_op : NOT IN",
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

//#line 180 "Arith.y"
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

//#line 503 "Parser.java"
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
case 2:
//#line 24 "Arith.y"
{ System.out.println("Reconocimiento Exitoso");}
break;
//#line 660 "Parser.java"
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
