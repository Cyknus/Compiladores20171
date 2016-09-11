/*User Code Section */

import java.util.Stack;

/*Variables and functions that are used inside scanner actions.*/
%%

%public
%class Flexer
%standalone
%state INDENT
%line


%{
Stack<Integer> inden_levels;
int current_line_indent;
int TAB;
int ESP;


public void clear(){
  current_line_indent = 0;
} 

public void increase_ident(int n){
  current_line_indent += n;
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
IDENTIFICADOR = ({LETRA} | "_" ) ({LETRA} | {DIGITO} | "_" )*
BOOLEANO = True | False
ENTERO = {DIGITO}{DIGITO}* | 0 
REAL = {PUNTO}{DIGITO}+ | {ENTERO}{PUNTO}{DIGITO}+ | {ENTERO}
CADENA = "\""[^\n]*"\""
KEYWORD = "and" | "or" | "not" | "for" | "while" | "if" | "else" | "elif" | "print" | "in"
OPERADOR = "+" | "-" | "*" | "**" | "/" | "//" | "%" | ">" | "<" | ">=" | "<=" | "==" | "!=" | "-=" | "+="
SEPARADOR = "(" | ")" | ":" | ";" | "=" 
NEWLINE = "\n"
TABULADOR = "\t"
ESPACIO = " "
COMENTARIO = "#"[^\n]*



%%
<YYINITIAL>{
  
^{ESPACIO}        {if(yyline == 0){
                    System.out.println("Error de indentación. Línea " +(yyline+1));
                      System.exit(-1);};
                    }
^{COMENTARIO}\n   {yybegin(YYINITIAL);}
{COMENTARIO}      {System.out.print("COMENTARIO("+yytext()+")");}                    
{CADENA}          {System.out.print("CADENA("+yytext()+")");}
{BOOLEANO}        {System.out.print("BOOLEANO("+yytext()+")");}
{KEYWORD}         {System.out.print("KEYWORD("+yytext()+")");}
{IDENTIFICADOR}   {System.out.print("IDENTIFICADOR("+yytext()+")");}
{ENTERO}          {System.out.print("ENTERO("+yytext()+")");}
{REAL}            {System.out.print("REAL("+yytext()+")");}
{SEPARADOR}       {System.out.print("SEPARADOR("+yytext()+")");}
{OPERADOR}        {System.out.print("OPERADOR("+yytext()+")");}
{NEWLINE}         {yybegin(INDENT); clear();System.out.println("NEWLINE");}
}

<INDENT>{ 

{ESPACIO}       {increase_ident(1);}
{TABULADOR}     {increase_ident(4);}
  
.                {if(current_line_indent > top()){
                   push(current_line_indent);
                   System.out.print("INDENT(" + (current_line_indent) + ")");
                  }else if(current_line_indent < top()){
                   pop();
                  if(current_line_indent > top()){
                      System.out.println("Error de indentación. Línea " +(yyline+1));
                      System.exit(-1);
                  }
                   yypushback(yylength());
                   System.out.println("DEDENT");
                   }
                  yybegin(YYINITIAL);
                  yypushback(yylength());

                 }
}






