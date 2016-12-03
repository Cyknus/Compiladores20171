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
%right ELIF ELSE


/* Grammar follows */
%%
 /* file_input: (NEWLINE | stmt)* ENDMARKER */
file_input:  { System.out.println("Reconocimiento Exitoso");}
          | aux0 { raiz.acepta(visitante); System.out.println("Reconocimiento Exitoso");}
;

aux0: stmt {raiz.setHijo($1);}
    | aux0 stmt {raiz.setHijo($2);}
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
          | test EQ test {$$ = $2;$2.setHijoIzq($1);$2.setHijoDer($3);}
          | test augassign test {$$ = $2;$2.setHijoIzq($1);$2.setHijoDer($3);}
;


/* augassign: '+=' | '-=' */
augassign:  MASEQ {$$ = $1;}
           | MENOSEQ {$$ = $1;}
;

/* print_stmt: 'print' [test] */
print_stmt: PRINT test 
;

/* compound_stmt: if_stmt | while_stmt */
compound_stmt: if_stmt {$$ = $1;}
           | while_stmt {$$ = $1;}
;

/* if_stmt: 'if' test ':' suite ('elif' test ':' suite)* ['else' ':' suite] */
if_stmt: IF test DOSPUNT suite {$$ = new NodoIfAux("IF"); $$.setHijo($1); $1.setHijoIzq($2); $1.setHijoDer($4);}
          | IF test DOSPUNT suite ELSE DOSPUNT suite {$$ = new NodoIfAux("IF"); $$.setHijo($1); $1.setHijoIzq($2); $1.setHijoDer($4);$$.setHijo($5);$5.setHijoDer($7);}
          | IF test DOSPUNT suite if_stmt_aux ELSE DOSPUNT suite {$$ = new NodoIfAux("IF"); $$.setHijo($1); $1.setHijoIzq($2); $1.setHijoDer($4); $$.setHijoo($5); $$.setHijo($6); $6.setHijoDer($8);}
;

if_stmt_aux: ELIF test DOSPUNT suite {$$ = new NodoIfAux("ELIF"); $1.setHijoIzq($2); $1.setHijoDer($4); $$.setHijo($1); }
          | ELIF test DOSPUNT suite if_stmt_aux {$$ = new NodoIfAux("ELIF"); $$.setHijo($1); $1.setHijoIzq($2); $1.setHijoDer($4); $$.setHijoo($5);}
;


/* while_stmt: 'while' test ':' suite */
while_stmt: WHILE test DOSPUNT suite {$1.setHijoIzq($2); $$ = $1; $1.setHijoDer($4);}
;

/* suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT */
suite: simple_stmt {$$ = $1;}
      | NEWLINE INDENT aux2 DEDENT {$$ = $3;}
;

aux2: stmt {$$ = new NodoSuite("Suite"); $$.setHijo($1);}
      | aux2 stmt {$$ = new NodoSuite("Suite"); $$.setHijoo($1);$$.setHijo($2);}
;

/* test: or_test */
test: or_test {$$ = $1;}
;

/* or_test: and_test ('or' and_test)* */
or_test: and_test {$$ = $1;}
      | and_test aux3 {$$ = $2; $2.setHijoIzq($1);}
;

aux3: OR and_test {$$ = $1; $1.setHijoDer($2);}
      | aux3 OR and_test {$$ = $2; $2.setHijoDer($3); $2.setHijoIzq($1);}
;

/* and_test: not_test ('and' not_test)* */
and_test: not_test {$$ = $1;}
      | not_test aux4 {$$ = $2; $2.setHijoIzq($1);}
;

aux4: AND not_test {$$ = $1; $1.setHijoDer($2);}
      | aux4 AND not_test {$$ = $2; $2.setHijoDer($3); $2.setHijoIzq($1);}
;


/* not_test: 'not' not_test | comparison */
not_test: NOT not_test {$$ = $1; $1.setHijoDer($2);}
      | comparison {$$ = $1;}
;

/* comparison: expr (comp_op expr)* */
comparison: expr {$$ = $1;}
      | expr aux5 {$$ = $2; $2.setHijoDer($1);}
;

aux5: comp_op expr {$$ = $1; $1.setHijoDer($2);}
    | comp_op expr aux5 {$$ = $1; $3.setHijoIzq($2); $1.setHijoDer($3);}
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
      | term aux6 {$$ = $2; $2.setHijoDer($1);}
;

aux6: MAS term {$$ = $1; $1.setHijoDer($2);}
    | MENOS term {$$ = $1; $1.setHijoDer($2);}
    | MAS term aux6 {$$ = $1; $3.setHijoIzq($2); $1.setHijoDer($3);}
    | MENOS term aux6 {$$ = $1; $3.setHijoIzq($2); $1.setHijoDer($3);}
;


/* term: factor (('*'|'/'|'%'|'//') factor)* */
term:  factor {$$ = $1;}
      | factor aux7 {$$ = $2; $2.setHijoDer($1);}
;
aux7:  POR factor {$$ = $1; $1.setHijoDer($2);}
     | DIVENTERA factor {$$ = $1; $1.setHijoDer($2);}
     | MODULO factor {$$ = $1; $1.setHijoDer($2);}
     | DIV factor {$$ = $1; $1.setHijoDer($2);}
     | aux7 POR factor {$$ = $1; $3.setHijoIzq($2); $1.setHijoDer($3);}
     | aux7 DIVENTERA factor {$$ = $1; $3.setHijoIzq($2); $1.setHijoDer($3);}
     | aux7 MODULO factor {$$ = $1; $3.setHijoIzq($2); $1.setHijoDer($3);}
     | aux7 DIV factor {$$ = $1; $3.setHijoIzq($2); $1.setHijoDer($3);}
;
/* factor: ('+'|'-') factor | power */
factor:  MAS factor {$$ = $1; $1.setHijoIzq($2);}
       | MENOS factor {$$ = $1; $1.setHijoIzq($2);}
       | power {$$ = $1;}
;
/* power: atom ['**' factor] */
power:  atom {$$ = $1;}
      | atom POTENCIA factor {$$ = $2; $2.setHijoIzq($1); $2.setHijoDer($3);}
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
NodoRaiz raiz;
VisitantePrint visitante;

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
    raiz = new NodoRaiz("raiz");
    visitante = new VisitantePrint();
}

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse();    
}

