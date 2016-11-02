*Pequeño analizador léxico*
* Para compilar y probar:
	$ make parser file=_archivoDePrueba_
	Ejemplo:
	$make parser file=primeraPrac.py

* Para probar
	$ make test file=_archivoDePrueba_
	Ejemplo:
	$make test file=primeraPrac.py


**Respuesta al punto 4: Explicar como resolvieron los conflictos en caso de que les hayan surgido.
* Si surgieron conflictos shift/reduce y reduce/reduce y lo que se hizo fue factorizar algunas gramáticas ya que sin esto durante el reconocimiento se va a encontrar con varias alternativas,  por lo tanto no se sabe que camino elegir.
