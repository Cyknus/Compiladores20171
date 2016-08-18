## Práctica 1

# 1. ¿Qué es un analizador léxico?
  Es la primer fase de un compilador, Su  principal  función  consiste  en  leer  la  secuencia  de caracteres  del  programa  fuente,  carácter  a  carácter,  y  elaborar  como  salida  la  secuencia  de componentes léxicos que utiliza el analizador sintáctico.
  
# 2. ¿Cómo funcionan los generadores de analizadores léxicos?
  En particular recorren la entrada extándar hasta que encuentran un lexema correspondiente al lenguaje de algunas de las expresiones regulares representadas por los patrones, entonces ejecuta el código asociado y permite acceder a la información asociada al lexema (string, longitud del mismo, número de linea en el fuente, etc.)

# 3. ¿Cuáles son los pasos que se siguen para obtener un AFD a partir de un conjunto de expreciones regulares?
  Informalmente primero tenemos que generar el autómata con transiciones epsilon aplicando el algoritmo de Thompson, hecho esto vamos construyendo los estados del AFD viendo la E-cerradura de cada estado, esto nos generara una tabla de transiciones, y al final tan solo debemos minimizar el autómata que habiamos optenido.
