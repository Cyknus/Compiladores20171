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

/* Grammar follows */
%%
 /* stmt: (simple_stmt)* ENDMARKER */
stmt:  { System.out.println("Reconocimiento Exitoso");}
      | aux0 { System.out.println("Reconocimiento Exitoso");}
;

aux0:  simple_stmt
     | aux0 simple_stmt
;

/* simple_stmt: small_stmt NEWLINE */
simple_stmt:  small_stmt NEWLINE
;

/* small_stmt: expr_stmt | print_stmt  */
small_stmt:  expr_stmt
           | print_stmt
;

/* expr_stmt: test ['=' test] */
expr_stmt:  test
           | test EQ test
;

/* print_stmt: 'print' [ test ] */
print_stmt: PRINT
          | PRINT test
;

/* test: term (('+'|'-') term)* */
test:  term
     | term aux8
;
aux8:  MAS term
     | MENOS term
     | aux8 MAS term 
     | aux8 MENOS term 
;
/* term: factor (('*'|'/'|'%'|'//') factor)* */
term:  factor
      | factor aux9
;
aux9:  POR factor
     | DIVENTERA factor
     | MODULO factor
     | DIV factor
     | aux9 POR factor 
     | aux9 DIVENTERA factor
     | aux9 MODULO factor
     | aux9 DIV factor
;
/* factor: ('+'|'-') factor | power */
factor:  MAS factor
       | MENOS factor
       | power
;
/* power: atom ['**' factor] */
power:  atom
      | atom POTENCIA factor
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
