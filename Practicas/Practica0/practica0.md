# Práctica 0

## Explica cómo se genera el identificador de 40 dígitos para el commit

Aplicando el hash criptográfico SHA-1 al contenido del commit.

## Escribe el comando para regresar al commit 911e8c9

git checkout 911e8c9

## Describe qué son las ramas, para qué sirven y los comandos relacionados con ellas.

Son una desviación de la línea actual de trabajo para trabajar en algún componente sin afectar la línea principal (master).

* git branch *branch_name*

Crea la rama *branch_name*

* git checkout *testing*

Cambia el directorio actual de trabajo a la rama *testing*

* git merge *branch*

Mezcla la rama actual de trabajo con la rama *branch*
