/********************************************************************************
**  Proyecto 2: Analizador léxico para p--, subconjunto de Python.                               **
**  @author Diana Montes                                                                       **
*********************************************************************************/
import java.util.Stack;
import java.util.Arrays;
%%
%public
%class Flexer
%byaccj
%line
%state INDENT CADENA DEDENT NORMAL EOF
%standalone

%{
    /** Variables auxiliares para
    * manejar la indentación.*/
    static Stack<Integer> pila = new Stack<Integer>();
    static Integer actual = 0;
    static String cadena = "";
    static int dedents = 0;
    static int indents = 0;

    private Parser yyparser;

    /** Nuevo constructor
    * @param FileReader r
    * @param Parser parser - parser
    */
    public Flexer(java.io.Reader r, Parser parser){
    	   this(r);
    	   this.yyparser = parser;
    }

    /** Función que maneja los niveles de indetación e imprime
    * átomos INDENT y DEDENT.
    * @param int espacios - nivel de indetación actual.
    * @return void
    */
    public void indentacion(int espacios){
        if(pila.empty()){ //ponerle un cero a la pila si esta vacia
             pila.push(new Integer(0));
        }

        Integer tope = pila.peek();

        if(tope != espacios){
	    //Se debe emitir un DEDENT por cada nivel mayor al actual
            if(tope > espacios){
                while(pila.peek() > espacios &&  pila.peek()!=0 ){
                    pila.pop();
                    dedents += 1;
                }
                if(pila.peek() == espacios){
		    yybegin(DEDENT);
                }else{
		    System.out.println("IndentationError: line "+(yyline+1));
		    System.exit(1);
		}
			return;
            }
   	    //El nivel actual de indentación es mayor a los anteriores.
            pila.push(espacios);
	    yybegin(NORMAL);
            indents = 1;
        }
    }
%}
PUNTO			=	\.
DIGIT           	=       [0-9]
CERO             	=        0+
ENTERO			= 	{CERO} | {DIGIT}+
REAL			= 	{ENTERO}? {PUNTO} {ENTERO}?
NEWLINE          	=       "\n"
IDENTIFIER       	= 	([:letter:] | "_" )([:letter:] | "_" | [0-9])*
CHAR_LITERAL   	= 	([:letter:] | [:digit:] | "_" | "$" | " " | "#"
				    | {OPERADOR} | {SEPARADOR})
OPERADOR  		=       ("+" | "-" | "*" | "**" | "/" | "//" | "%" |
			         "<" | ">" | "<=" | "+=" | "-=" | ">=" | "==" | "!=" | "<>" | "=" )
SEPARADOR  		=       ("(" | ")" | ":"  | ";" )
COMENTARIO 		=     	"#".*
BOOLEAN		=	("True" | "False")
%%
<YYINITIAL>{
  (" " | "\t" )+[^" ""\t""#""\n"]         {System.out.println("IndentationError: unexpected indent. Line "+(yyline+1));
					  System.exit(1);}
  {NEWLINE}                             {System.out.println("NEWLINE");}
  [^" ""\t"]                              {yypushback(1); yybegin(NORMAL);}
}
{COMENTARIO}  			{System.out.print("COMENTARIO("+yytext()+")");}
<DEDENT>{
  .                                       {yypushback(1);
  					  if(dedents > 0){
						dedents--;
            System.out.println("DEDENT");
						return Parser.DEDENT;
  					  }
					  yybegin(NORMAL); }
}
<CADENA>{
  {CHAR_LITERAL}+			{System.out.print("CADENA("+yytext()+")");cadena = yytext();}
  \"					{ System.out.print("CADENA("+yytext()+")"); yybegin(NORMAL);
					 cadena = "";
					 return Parser.CADENA;}
  {NEWLINE}				{ System.out.println("Unexpected newline. Line "+(yyline+1));
                                         System.exit(1);}
}
<NORMAL>{
  \"					{ System.out.print("CADENA("+yytext()+")"); yybegin(CADENA); }
  {REAL}			 	{ System.out.print("REAL("+yytext()+")"); return Parser.REAL;}
  {ENTERO}		        	{System.out.print("ENTERO("+yytext()+")"); return Parser.ENTERO;}
  "+"         			        { System.out.print("OPERADOR("+yytext()+")"); return Parser.MAS;}
  "-"				      	{  System.out.print("OPERADOR("+yytext()+")"); return Parser.MENOS;}
  "*"				       {  System.out.print("OPERADOR("+yytext()+")"); return Parser.POR;}
  "**"				       {  System.out.print("OPERADOR("+yytext()+")"); return Parser.POTENCIA;}
  "/"				       {  System.out.print("OPERADOR("+yytext()+")"); return Parser.DIV;}
  "//"				       {  System.out.print("OPERADOR("+yytext()+")");return Parser.DIVENTERA;}
  "%"				       {  System.out.print("OPERADOR("+yytext()+")");return Parser.MODULO;}
  "="				       {  System.out.print("OPERADOR("+yytext()+")");return Parser.EQ;}
{BOOLEAN}			       {  System.out.print("BOOLEANO("+yytext()+")");return Parser.BOOLEAN;}
  "print"				{ System.out.print("KEYWORD("+yytext()+")"); return Parser.PRINT;}
  {IDENTIFIER}              		{ System.out.print("IDENTIFICADOR("+yytext()+")");return Parser.IDENTIFIER;}
  {NEWLINE}                 		{ System.out.println("NEWLINE"); yybegin(INDENT); actual=0;
  					  return Parser.NEWLINE;
					}
  " "                        		{ }
}
<INDENT>{
  {NEWLINE}				{ actual = 0;}
  " "                            	{ actual++;}
  \t                             	{ actual += 4;}
  .                               	{ yypushback(1);
                                         this.indentacion(actual);
					 yybegin(NORMAL);
					 if(indents == 1){
					    indents = 0;
               System.out.print("INDENT(" + (actual) + ")");
					    return Parser.INDENT;
					 }

					}
}
<<EOF>>                                 {this.indentacion(0);
					if(dedents > 0){
					    dedents--;
					    return Parser.DEDENT;
					}else{
					    return 0;
					}
                                        }
[^] 					{}
