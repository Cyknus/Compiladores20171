/********************************************************************************
**  Proyecto 2: Analizador léxico para p--, subconjunto de Python.                               **

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
           cadena = ""; yyparser.yylval = new HojaCadena(yytext());
           return Parser.CADENA;}
  {NEWLINE}       { System.out.println("Unexpected newline. Line "+(yyline+1));
                                         System.exit(1);}
}
<NORMAL>{
  \"            { yybegin(CADENA); }
  {REAL}        { yyparser.yylval = new HojaReal(Double.parseDouble(yytext())); return Parser.REAL;}
  {ENTERO}      { yyparser.yylval = new HojaEntera(Integer.parseInt(yytext())); return Parser.ENTERO; }
  "+"           { yyparser.yylval = new NodoMas(yytext()); return Parser.MAS;} 
  "-"           { yyparser.yylval = new NodoMenos(yytext());return Parser.MENOS;} 
  "*"           { yyparser.yylval = new NodoPor(yytext());return Parser.POR;} 
  "**"          { yyparser.yylval = new NodoPotencia(yytext());return Parser.POTENCIA;} 
  "/"           { yyparser.yylval = new NodoDiv(yytext());return Parser.DIV;} 
  "//"          { yyparser.yylval = new NodoDivEntera(yytext());return Parser.DIVENTERA;}
  "%"           { yyparser.yylval = new NodoModulo(yytext());return Parser.MODULO;}
  "="           { yyparser.yylval = new NodoEq(yytext());return Parser.EQ;}
  "<"           { yyparser.yylval = new NodoMenorQ(yytext());return Parser.MENORQ;}
  ">"           { yyparser.yylval = new NodoMayorQ(yytext());return Parser.MAYORQ;}
  "<="          { yyparser.yylval = new NodoMenorEq(yytext());return Parser.MENOREQ;}
  "+="          { yyparser.yylval = new NodoMasEq(yytext());return Parser.MASEQ;}
  "-="        { yyparser.yylval = new NodoMenosEq(yytext());return Parser.MENOSEQ;}
  ">="        { yyparser.yylval = new NodoMayorEq(yytext());return Parser.MAYOREQ;}
  "=="        { yyparser.yylval = new NodoEqEq(yytext());return Parser.EQEQ;}
  "!="        { yyparser.yylval = new NodoDif(yytext());return Parser.DIF;}
  "if"        { yyparser.yylval = new NodoIf(yytext()); return Parser.IF;}
  "or"         { yyparser.yylval = new NodoOr(yytext());return Parser.OR;}
  "and"        { yyparser.yylval = new NodoAnd(yytext());return Parser.AND;}
  "not"        {yyparser.yylval = new NodoNot(yytext()); return Parser.NOT;}
  ";"              { yyparser.yylval = new NodoPuntComa(yytext()); return Parser.PUNTCOMA;}
  ":"              { yyparser.yylval = new NodoComa(yytext()); return Parser.DOSPUNT;}
  "elif"         { yyparser.yylval = new NodoElif(yytext()); return Parser.ELIF;}
  "else"         { yyparser.yylval = new NodoElse(yytext()); return Parser.ELSE;}
  "while"        { yyparser.yylval = new NodoWhile(yytext()); return Parser.WHILE;}
  "in"       { yyparser.yylval = new NodoIn(yytext()); return Parser.IN;}
  "not in"   { yyparser.yylval = new NodoNotIn(yytext()); return Parser.NOTIN;}


{BOOLEAN}            { yyparser.yylval = new HojaBoleano(Boolean.parseBoolean(yytext())); return Parser.BOOLEAN;}
  "print"       { yyparser.yylval = new NodoPrint(yytext()); return Parser.PRINT;}  
  {IDENTIFIER}                  { yyparser.yylval = new HojaIdentificador(yytext()); return Parser.IDENTIFIER;}
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
