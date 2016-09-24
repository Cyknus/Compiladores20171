%{
  import java.lang.Math;
  import java.io.*;
%}
/* YACC Declarations */
%token<sval> ELT
%type<sval> List Start
/* Grammar follows  --- LEFT RECURSION --- */
%%
Start: List                        { dump_stacks(stateptr); System.out.println("Reconocimiento exitoso de: "+$$);}
;
/* List:  ELT | List ELT   */
List:   ELT                       {dump_stacks(stateptr); $$ = $1 ;}
      | List ELT                  {dump_stacks(stateptr); $$ = $1+$2;}
;
%%
/* a reference to the lexer object */
private List lexer;

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
    lexer = new List(r, this);
    yydebug = true;
}

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse();    
}
