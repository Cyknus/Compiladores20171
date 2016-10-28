%{
  import java.lang.Math;
  import java.io.*;
%}
/* YACC Declarations */
%token NEWLINE IDENTIFIER ENTERO REAL CADENA
%token BOOLEAN 
%token PRINT INDENT DEDENT
%token MAS MENOS POR POTENCIA DIV  /* + | - | * | ** | / */
%token DIVENTERA MODULO EQ /* // | % | =*/
%token MENORQ MAYORQ MENOREQ MASEQ MENOSEQ MAYOREQ EQEQ DIF /* < | > | <= | += | -= | >= | == | != */
%token PUNTCOMA DOSPUNT  /* ; | : */
%token IF ELIF ELSE
%token WHILE /*if | elif | else | while */
%token OR AND NOT IN NOTIN /* or | and | not | in | not in*/



/* Grammar follows */
%%
 /* file_input: (NEWLINE | stmt)* ENDMARKER */
file_input: aux0 { System.out.println("Reconocimiento Exitoso");}
;

aux0: aux0 stmt | 
;

/* stmt: simple_stmt | compound_stmt */
stmt:  simple_stmt 
      | compound_stmt
;

/* simple_stmt: small_stmt NEWLINE  */
simple_stmt: small_stmt | small_stmt NEWLINE
;

/* small_stmt: expr_stmt | print_stmt */
small_stmt:  expr_stmt
           | print_stmt
;

/* expr_stmt: test [('=' | augassign) test] */
expr_stmt: test
          | test EQ test
          | test augassign test 
;

/* augassign: '+=' | '-=' */
augassign:  MASEQ
           | MENOSEQ
;

/* print_stmt: 'print' [test] */
print_stmt: PRINT test
;


/* compound_stmt: if_stmt | while_stmt */
compound_stmt: if_stmt
           | while_stmt
;

/* if_stmt: 'if' test ':' suite ('elif' test ':' suite)* ['else' ':' suite] */
if_stmt: IF test DOSPUNT suite if_stmt_aux aux_else
;

if_stmt_aux:  if_stmt_aux ELIF test DOSPUNT suite |  
;

aux_else: ELSE DOSPUNT suite |   
;


/* while_stmt: 'while' test ':' suite */
while_stmt: WHILE test DOSPUNT suite
;

/* suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT */
suite: simple_stmt
      | NEWLINE INDENT aux2 DEDENT
;

aux2: aux2 stmt
      | stmt
;

/* test: or_test */
test: or_test
;

/* or_test: and_test ('or' and_test)* */
or_test: and_test aux3
;

aux3: aux3 OR and_test |
;

/* and_test: not_test ('and' not_test)* */
and_test: not_test aux4
;

aux4: aux4 AND not_test |
;

/* not_test: 'not' not_test | comparison */
not_test: NOT not_test
      | comparison
;

/* comparison: expr (comp_op expr)* */
comparison: expr aux5 | expr
;

aux5: aux5 comp_op expr 
;

/* comp_op: '<'|'>'|'=='|'>='|'<='|'!='|'in'|'not' 'in' */
comp_op: MENORQ
      | MAYORQ
      | EQEQ
      | MAYOREQ
      | MENOREQ
      | DIF
      | IN
      | NOTIN
;

/* expr: term (('+'|'-') term)* */
expr: term 
      | term aux6
;

aux6: aux6 MAS term 
    | aux6 MENOS term 
;


/* term: factor (('*'|'/'|'%'|'//') factor)* */
term: factor aux7
;
aux7: aux7 POR factor 
     | aux7 DIVENTERA factor
     | aux7 MODULO factor
     | aux7 DIV factor |
;
/* factor: ('+'|'-') factor | power */
factor:  factor_aux factor | power
;

factor_aux: MAS | MENOS 
;

/* power: atom ['**' factor] */
power:  atom power_aux
;

power_aux: POTENCIA factor | 
;

/* atom: IDENTIFIER | INTEGER | STRING | FLOAT */
atom:  IDENTIFIER
     | ENTERO
     | CADENA
     | REAL
     | BOOLEAN
;
%%
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

