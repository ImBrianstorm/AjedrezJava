# *Ajedrez: Juego de Extinción*

Variante de ajedrez "Juego de Extinción" hecha en Java


## Prerrequisitos

  * Java 8 o posterior
  * Ant

## Compilación

La primer cosa que tenemos que hacer es compilar el código (en la carpeta AjedrezExtincion) de la siguiente manera:

    $ cd ./AjedrezExtincion/
    $ ant compile

## Build

Podemos saltarnos la parte de compilación y construir el jar, de todas maneras, al construir,
ant se encargará de compilar previamente:

    $ cd ./AjedrezExtincion/
    $ ant build

## Jugar

Una vez que creamos el jar, para jugar a Juego de Extinción, lo podremos hacer de la siguiente manera:

    $ cd ./build/jar
    $ java -jar Ajedrez.jar

> Intenté hacer que corra con "ant run", pero no funciona como quisiera, y espero corregirlo para
futuras versiones.

## Objetivo

El objetivo de este ajedrez es hacer práctica de orientación a objetos, de su herencia, abstracción, encapsulamiento y polimorfismo, así como prácticar un poco Java. No está pensado en un juego para competir, en realidad.

> Dev: [Mauricio Chávez](https://twitter.com/ImBrianstorm)
