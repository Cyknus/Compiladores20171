/********************************************************************************
**  Proyecto 1: Analizador léxico para p--, subconjunto de Python.                               **
**                                                                                                **
*********************************************************************************/
import java.util.Stack;
import java.util.Arrays;
%%
%public
%class List
%byaccj
%line
%unicode
%debug
%{
    private Parser yyparser;

    /** Nuevo constructor
    * @param FileReader r
    * @param Parser parser - parser
    */
    public List(java.io.Reader r, Parser parser){
    	   this(r);
    	   this.yyparser = parser;
    }

%}
KEYWORD = 'if' | 'else' 
%%
e					{ yyparser.yylval = new ParserVal(yytext());
					 return Parser.ELT;}
<<EOF>>           			{ return 0;}
[^]					{}
