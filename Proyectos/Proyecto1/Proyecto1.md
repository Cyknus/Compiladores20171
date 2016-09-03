# Proyecto 1

## 1. Funcionalidad de contextos que provee JFlex. 

JFlex reconoce una pequeña cantidad del contexto que le rodea. Los dos operadores más simples para éstos son el signo de intercalación ( ^ ) y el signo de dólar ( $ ). Si el primer carácter de una expresión es un signo ^, entonces la expresión sólo coincide al principio de la línea (después de un carácter newline, o al principio del input). Esto nunca se puede confundir con el otro significado del signo ^, complementación de las clases de caracteres, puesto que la complementación sólo se aplica dentro de corchetes. Si el primer carácter es el signo de dólar, la expresión sólo coincide al final de una línea (cuando va seguido inmediatamente de un carácter newline). Este último operador es un caso especial del operador barra ( / ) , el cual indica contexto al final. 


 En otras palabras el operador (^), por ejemplo, es un operador de contexto previo, que reconoce inmediatamente contexto que precede por la izquierda del mismo modo que el signo de dólar ($) reconoce el contexto que va inmediatamente a la derecha.
 
## 2. ¿Cómo usar la función de depuración de JFlex?
 
 Si incluimos la directiva %debug en lugar de  %standalone JFlex creará una función main () de la clase yylex, lo que nos permite correr el analizador léxico sin un analizador adjunto. La función main () recibirá un nombre de archivo de entrada de la línea de comandos. Se procesará los tokens en ese archivo y reportará información sobre cada token.
 
## 3. Reconocer atomos DEDENT, INDENT y NEWLINE
 
## 4. En el uso del lenguage ¿La identación significativa fácilita echar código?
 
 Se supone que la indentación significativa de Python tiene razones técnicas prácticas:
 * Hace más clara la sintaxis. De hecho, se escribe menos.
 * Automáticamente da mantenimiento al código, al no requerir de programas para ordenar un código formateado "libremente".
 * A fin de cuentas, la indentación la debería de manejar un editor especial para Python, de modo que es automática también.

##¿La identación significativa fácilita el reconocimiento de bloques?
Sí, pues se utiliza para delimitar la estructura del programa permitiendo establecer bloques de código.
