/*User Code Section */

import java.util.Stack;

/*Variables and functions that are used inside scanner actions.*/
%%

%public
%class Flexer
%standalone


%{
Stack<Integer> inden_levels;
int current_line_indent;
int TAB;
int ESP;


public void increase_ident(int n){
  current_line_indent = current_line_indent + n;
}

public int top(){
  return inden_levels.peek();
}

public void push(int n){
  inden_levels.push(n);
}

public void pop(){
  inden_levels.pop();
}

%}

/*Initialize variables in the construct*/

%init{
  inden_levels = new Stack<>();
  current_line_indent = 0;
  inden_levels.push(0);
  TAB = 4;
  ESP = 1;
%init}

/*Macro declarations*/
LETRA = [a-zA-Z]
DIGITO = [0-9]
PUNTO = "."
IDENTIFICADOR = ({LETRA} | "_" ) ({LETRA} | DIGITO | "_" )*
BOOLEANO = True | False
ENTERO = {DIGITO}{DIGITO}* | 0 
REAL = {PUNTO}{DIGITO}+ | {ENTERO}{PUNTO}{DIGITO}+ | {ENTERO}
CADENA = {IDENTIFICADOR} | {OPERADOR} | {ENTERO} | {REAL} | {SEPARADOR}
CADENAS = ("{CADENA}")*
KEYWORD = "and" | "or" | "not" | "for" | "while" | "if" | "else" | "elif" | "print" | "in"
OPERADOR = "+" | "-" | "*" | "**" | "/" | "//" | "%" | ">" | "<" | ">=" | "<=" | "==" | "!=" | "-=" | "+="
SEPARADOR = "(" | ")" | ":" | ";" | "=" 
NEWLINE = [\n$]
TABULADOR = [\t]
ESPACIO = " "
IDENTACION = {ESPACIO}	| {TABULADOR}
PYTHON = {IDENTACION}* | {IDENTIFICADOR}* | {OPERADOR}* | {BOOLEANO}* |{ENTERO}* | {REAL}* | {SEPARADOR}*



%%

{NEWLINE}         {System.out.println("NEWLINE");}
^{TABULADOR}*       {increase_ident(TAB);}
^{ESPACIO}*         {increase_ident(ESP);}

{CADENAS}         {System.out.println("CADENA("+yytext()+")");}
{BOOLEANO}        {System.out.println("BOOLEANO("+yytext()+")");}
{KEYWORD}         {System.out.println("KEYWORD("+yytext()+")");}
{IDENTIFICADOR}   {System.out.println("IDENTIFICADOR("+yytext()+")");}
{ENTERO}          {System.out.println("ENTERO("+yytext()+")");}
{REAL}            {System.out.println("REAL("+yytext()+")");}
{SEPARADOR}       {System.out.println("SEPARADOR("+yytext()+")");}
{OPERADOR}        {System.out.println("OPERADOR("+yytext()+")");}

{PYTHON} 				 {if(current_line_indent > top()){
							     push(current_line_indent);
							     System.out.print("INDENT(" + (current_line_indent) + ")");
                  }else if(current_line_indent < top()){
	                 pop();
	                 System.out.println("DEDENT");}
                  } 




