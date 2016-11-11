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
%token IF ELIF ELSE WHILE /*if | elif | else | while */
%token OR AND NOT IN NOTIN/* or | and | not | in | not in */



/* Grammar follows */
%%
 /* file_input: (NEWLINE | stmt)* ENDMARKER */
file_input:  { System.out.println("Reconocimiento Exitoso");}
          | aux0 {$$ = $1;}
;

aux0: stmt {$$ = $1;}
    | aux0 stmt
    | NEWLINE
    | aux0 NEWLINE
;

/* stmt: simple_stmt | compound_stmt */
stmt:  simple_stmt  {$$ = $1;}
      | compound_stmt {$$ = $1;}
;

/* simple_stmt: small_stmt NEWLINE  */
simple_stmt: small_stmt NEWLINE {$$ = $1;}
;

/* small_stmt: expr_stmt | print_stmt */
small_stmt:  expr_stmt {$$ = $1;}
           | print_stmt {$$ = $1;}
;

/* expr_stmt: test [('=' | augassign) test] */
expr_stmt: test {$$ = $1;}
          | test aux1
;

aux1: EQ test
    | augassign test
;


/* augassign: '+=' | '-=' */
augassign:  MASEQ
           | MENOSEQ
;

/* print_stmt: 'print' [test] */
print_stmt: PRINT test
;

/* compound_stmt: if_stmt | while_stmt */
compound_stmt: if_stmt {$$ = $1;}
           | while_stmt {$$ = $1;}
;

/* if_stmt: 'if' test ':' suite ('elif' test ':' suite)* ['else' ':' suite] */
if_stmt: IF test DOSPUNT suite {Nodo izq = (Nodo) $2.obj; Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $4.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
          | IF test DOSPUNT suite ELSE DOSPUNT suite
          | IF test DOSPUNT suite if_stmt_aux ELSE DOSPUNT suite
;

if_stmt_aux: ELIF test DOSPUNT suite {Nodo izq = (Nodo) $2.obj; Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $4.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
          | if_stmt_aux ELIF test DOSPUNT suite  
;



/* while_stmt: 'while' test ':' suite */
while_stmt: WHILE test DOSPUNT suite {Nodo izq = (Nodo) $2.obj; Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $4.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
;

/* suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT */
suite: simple_stmt {$$ = $1;}
      | NEWLINE INDENT aux2 DEDENT {$$ = $3;}
;

aux2: stmt {$$ = $1;}
      | aux2 stmt {$$ = $1; $$ = $2;}
;

/* test: or_test */
test: or_test {$$ = $1;}
;

/* or_test: and_test ('or' and_test)* */
or_test: and_test {$$ = $1;}
      | and_test aux3 {$$ = $1; $$ = $2;}
;

aux3: OR and_test {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
      | aux3 OR and_test {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
;

/* and_test: not_test ('and' not_test)* */
and_test: not_test {$$ = $1;}
      | not_test aux4 {$$ = $1; $$ = $2;}
;

aux4: AND not_test {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
      | aux4 AND not_test {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
;


/* not_test: 'not' not_test | comparison */
not_test: NOT not_test {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
      | comparison {$$ = $1;}
;

/* comparison: expr (comp_op expr)* */
comparison: expr {$$ = $1;}
      | expr aux5 {$$ = $1; $$ = $2;}
;

aux5: comp_op expr {$$ = $1;}
    | aux5 comp_op expr {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
;

/* comp_op: '<'|'>'|'=='|'>='|'<='|'!='|'in'|'not' 'in' */
comp_op: MENORQ {$$ = $1;}
      | MAYORQ {$$ = $1;}
      | EQEQ {$$ = $1;}
      | MAYOREQ {$$ = $1;}
      | MENOREQ {$$ = $1;}
      | DIF {$$ = $1;}
      | IN {$$ = $1;}
      | NOTIN {$$ = $1;}
;

/* expr: term (('+'|'-') term)* */
expr: term {$$ = $1;}
      | term aux6 {$$ = $1; $$ = $2;}
;

aux6: MAS term {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
    | MENOS term {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
    | aux6 MAS term {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
    | aux6 MENOS term {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
;


/* term: factor (('*'|'/'|'%'|'//') factor)* */
term:  factor {$$ = $1;}
      | factor aux7 {$$ = $1; $$ = $2;}
;
aux7:  POR factor {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
     | DIVENTERA factor {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
     | MODULO factor {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
     | DIV factor {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
     | aux7 POR factor {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
     | aux7 DIVENTERA factor {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
     | aux7 MODULO factor {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
     | aux7 DIV factor {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
;
/* factor: ('+'|'-') factor | power */
factor:  MAS factor  {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
       | MENOS factor {Nodo n = (Nodo) $1.obj; Nodo der = (Nodo) $2.obj; n.setHijoDer(der); $$ = new ParserVal(n);}
       | power {$$ = $1;}
;
/* power: atom ['**' factor] */
power:  atom {$$ = $1;}
      | atom POTENCIA factor {Nodo izq = (Nodo) $1.obj; Nodo n = (Nodo) $2.obj; Nodo der = (Nodo) $3.obj; n.setHijoIzq(izq); n.setHijoDer(der); $$ = new ParserVal(n);}
;

/* atom: IDENTIFIER | INTEGER | STRING | FLOAT */
atom:  IDENTIFIER {$$ = $1;}
     | ENTERO {$$ = $1;}
     | CADENA {$$ = $1;}
     | REAL {$$ = $1;}
     | BOOLEAN {$$ = $1;}
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

