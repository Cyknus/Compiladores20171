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
%unicode
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
        }else yybegin(NORMAL);
    }
%}
PUNTO     = \.
DIGIT             =       [0-9]
CERO              =        0+
ENTERO      =   {CERO} | {DIGIT}+
REAL      =   {ENTERO}? {PUNTO} {ENTERO}?
NEWLINE           =       "\n"
IDENTIFIER        =   ([:letter:] | "_" )([:letter:] | "_" | [0-9])*
CHAR_LITERAL    =   ([:letter:] | [:digit:] | "_" | "$" | " " | "#"
            | {OPERADOR} | {SEPARADOR})
OPERADOR      =       ("+" | "-" | "*" | "**" | "/" | "//" | "%" |
               "<" | ">" | "<=" | "+=" | "-=" | ">=" | "==" | "!=" | "<>" | "=" )            
SEPARADOR     =       ("(" | ")" | ":"  | ";" )
COMENTARIO    =       "#".*
BOOLEAN   = ("True" | "False")
%%
<YYINITIAL>{
  (" " | "\t" )+[^" ""\t""#""\n"]         {System.out.println("IndentationError: unexpected indent. Line "+(yyline+1));
            System.exit(1);}
  {NEWLINE}                             {}
  [^" ""\t"]                              {yypushback(1); yybegin(NORMAL);}   
}
{COMENTARIO}        {}
<DEDENT>{
  .                                       {yypushback(1);
              if(dedents > 0){
            dedents--;
            return Parser.DEDENT;
              }
            yybegin(NORMAL); }
}
<CADENA>{
  {CHAR_LITERAL}+     {cadena = yytext();}
  \"          { yybegin(NORMAL);
           cadena = "";
           return Parser.CADENA;}
  {NEWLINE}       { System.out.println("Unexpected newline. Line "+(yyline+1));
                                         System.exit(1);}
}
<NORMAL>{
  \"          { yybegin(CADENA); }
  {REAL}        { return Parser.REAL;}
  {ENTERO}              { return Parser.ENTERO; }
  "+"                       { return Parser.MAS;} 
  "-"               { return Parser.MENOS;} 
  "*"              { return Parser.POR;} 
  "**"               { return Parser.POTENCIA;} 
  "/"              { return Parser.DIV;} 
  "//"               { return Parser.DIVENTERA;}
  "%"              { return Parser.MODULO;}
  "="              { return Parser.EQ;}
  "<"         { return Parser.MENORQ;}
  ">"         { return Parser.MAYORQ;}
  "<="          { return Parser.MENOREQ;}
  "+="          { return Parser.MASEQ;}
  "-="        { return Parser.MENOSEQ;}
  ">="        { return Parser.MAYOREQ;}
  "=="        { return Parser.EQEQ;}
  "!="        { return Parser.DIF;}
  "if"               { return Parser.IF;}
  "or"          { return Parser.OR;}
  "and"        { return Parser.AND;}
  "not"        { return Parser.NOT;}
  ";"              { return Parser.PUNTCOMA;}
  ":"              { return Parser.DOSPUNT;}
  "elif"         { return Parser.ELIF;}
  "else"         { return Parser.ELSE;}
  "while"          { return Parser.WHILE;}
  "in"        { return Parser.IN;}
  "not in"   {return Parser.NOTIN;}


{BOOLEAN}            { return Parser.BOOLEAN;}
  "print"       { return Parser.PRINT;}  
  {IDENTIFIER}                  { return Parser.IDENTIFIER;}
  {NEWLINE}                     { yybegin(INDENT); actual=0;
              return Parser.NEWLINE;
          }
  " "                           { }
}
<INDENT>{
  {NEWLINE}       { actual = 0;}
  " "                             { actual++;}
  \t                              { actual += 4;}
  .                                 { yypushback(1); 
                                         this.indentacion(actual);
           //yybegin(NORMAL);
           if(indents == 1){
              indents = 0;
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
[^]           {}
