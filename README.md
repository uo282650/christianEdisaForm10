# Repositorio prácticas Java 2025

## Descripción
Este repositorio contiene un scaffolding de un proyecto Maven sobre el que se harán los ejercicios de las prácticas de Java.


## Instrucciones de la práctica
Para la realización de los ejercicios, se debe clonar este repositorio, una vez clonado, se deberá crear una rama nueva cuyo nombre sea vuestro nombre y apellidos separados por _. Ejemplo: Si mi nombre es Ismael Alvarez Iglesias, la rama que debo crear será ismael_alvarez_iglesias.

Los ejercicios deberán subirse (push) a la rama que se acaba de crear con nuestro nombre.

## Ejercicios

### Ejercicio 1
Desarrollar una aplicación de consola que genere ficheros jpg con códigos QR.

La aplicación recibirá los siguientes argumentos:
- Texto del que se desea generar el código de barras QR.
- Ruta en la que se desea generar el fichero de imagen (por ejemplo c:\Users\aaa\Documents\codigoqr.jpg)


Para la generación de códigos QR, se podrá usar la libreria ZXing Core de Google (https://mvnrepository.com/artifact/com.google.zxing/core).
La documentación del API de esta librería se encuentra en https://zxing.github.io/zxing/apidocs/
En el siguiente enlace a Github se encuentra la librería  y más documentación sobre la misma: https://github.com/zxing/zxing

Tras realizar este ejercicio, deberá realizarse al menos un push al repositorio remoto indicando la finalización del ejercicio.

### Ejercicio 2
Una vez realizado el primer ejercicio, se ampliará la funcionalidad de la aplicación de la siguiente manera.

Se modificarán los argumenteos de entrada pasando a ser tres:
- Texto del que se desea generar el código de barras QR.
- Ruta en la que se desea generar el fichero de imagen (por ejemplo c:\Users\aaa\Documents\codigoqr.jpg)
- Formato del código de barras a genera (Qr, EAN-13, etc.).

La aplicación generará el código de barras como un fichero jpg en una carpeta con el formato indicado como parámetro.

