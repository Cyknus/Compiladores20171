#Ejercicios

## Analizadores sintácticos descendentes (Top-Down)

* ¿Cuáles son las maneras de implementar un analizador sintáctico descendente?
Descenso recursivo, a pie y usando generadores automáticos: Byacc/J

* ¿Qué características debe cumplir una gramática libre del contexto para que puedan tener un reconocedor descendente recursivo sin caer en ciclos ni hacer backtrack?
Ser de tipo LL(1), pues se le va a quitar la recursividad izquierda.

## Analizadores sintácticos ascendentes (Bottom-UP)

* ¿Qué resultado da la evaluación de la expresión 3-2+8? 
