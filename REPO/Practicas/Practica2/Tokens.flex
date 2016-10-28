/********************************************************************************
**  Proyecto 1: Analizador léxico para p--, subconjunto de Python.                               **
**                                                                                                **
*********************************************************************************/
import java.util.Stack;
import java.util.Arrays;
%%
%public
%class Tokens
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
    public Tokens(java.io.Reader r, Parser parser){
    	   this(r);
    	   this.yyparser = parser;
    }

%}
DIGITO = [0-9]
NUMBER = {DIGITO}{DIGITO}* | 0 
MAS = "+"
MENOS = "-"
DIV = "/"
MULT = "*"
%%

{NUMBER}				{yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));return Parser.NUMBER;}
{MAS}					{return Parser.MAS;}
{MENOS}					{return Parser.MENOS;}
{DIV}					{return Parser.DIV;}
{MULT}					{return Parser.MULT;}

<<EOF>>           			{return 0;}
[^]					{}
