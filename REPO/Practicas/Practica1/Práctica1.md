# Práctica 1

## 1. ¿Qué es un analizador léxico?
  Es la primer fase de un compilador, Su  principal  función  consiste  en  leer  la  secuencia  de caracteres  del  programa  fuente,  carácter  a  carácter,  y  elaborar  como  salida  la  secuencia  de componentes léxicos que utiliza el analizador sintáctico.
  
## 2. ¿Cómo funcionan los generadores de analizadores léxicos?
  En particular recorren la entrada extándar hasta que encuentran un lexema correspondiente al lenguaje de algunas de las expresiones regulares representadas por los patrones, entonces ejecuta el código asociado y permite acceder a la información asociada al lexema (string, longitud del mismo, número de linea en el fuente, etc.)

## 3. ¿Cuáles son los pasos que se siguen para obtener un AFD a partir de un conjunto de expreciones regulares?
  Informalmente primero tenemos que generar el autómata con transiciones epsilon aplicando el algoritmo de Thompson, hecho esto vamos construyendo los estados del AFD viendo la E-cerradura de cada estado, esto nos generara una tabla de transiciones, y al final tan solo debemos minimizar el autómata que habiamos optenido.


# Ejercicios para la próxima semana

## 1. ¿Qué implementación se utilizó?
  La implementación que se utilizó en el analizador léxico que reconociera cadenas formadas por las expresiones regulares ab + (ab)*c fue la implementación por tablas ya que al comenzar con un AFD nos simplifica el diseño y la comprensión del código, pues esta expresión regular tiene una peculiaridad la cuál es que si la cadena entrante fuera (ab)^3 = ababab, como se utiliza el principio de mordida máxima primero trataria de encontrar la cadena "abababc" pero como no tenemos la "c" al final se tendría que hacer un retroceso hasta llegar al primer token e irnos por otro camino... esto tomaría bastante tiempo si la implementación la hicieramos por medio de tablas que hacen uso intensivo de indexación y también sí códificaramos el código directamente. La forma en que se asegura que el reconocimiento es lineal es porque en el procesamiento de la cadena se utiliza una tabla de dos dimiensiones (tupla [q,j]) que tabula pares encontrados previamente de los estados y el índice de su posición que no condujo a la identificación de un token más largo, teniendo esto se evita repetir esas busquedas que anteriormente fallaron. Entonces... se puede encontrar con un par dado [q,j] a lo más |Q| veces donde Q son los estados. Debido a que para una entrada de longitud n sólo hay O(n) pares de la forma [q,j] el reconocimiento es lineal, y la constante de proporcionalidad depende de |Q|.

## 2. Analizador léxico para el lenguaje 0(1+01)
  Para éste analizador léxico lo único que tendría que cambiarse sería la quintupla del autómata en los parámetros del analizador, es decir:
  * estados = q0, q1, q2, q3
  * alfabeto = 0,1
  * inicial = q0
  * finales = q3
  * funcion: (q0,0)=q1, (q1,1)=q3, (q1,0)=q2, (q2,1)=q3

## 3. Usando herramienta JFlex. ¿Cómo garantiza el reconocimiento lineal?. 
  Lo que hace JFlex es que a partir de las expresiones regulares definidas produce un programa llamado yylex que reconoce las cadenas que cumplen dichas reglas, es decir, produce el AFD de la e.r., a cada regla se le asocia un conjunto de acciones y cuando yylex encuentra una cadena que cumple una regla, ejecuta las acciones asociadas a esa regla

## 4. Tipo de implementación del analizador léxico que genera JFlex.
  Usa el principio de mordida máxima y su implementación es por tablas
