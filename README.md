# Facultad de Ciencias, UNAM

## Heurísticas de Optimización Combinatoria
## *Travelling Salesman Problem - Threshold Accepting*

![](/report/assets/img/xkdc-tsp.png)

# Reporte
El reporte del proyecto está [aquí](/report/report.pdf).

## Información del curso
* Profesor: Canek Peláez Valdés
* Ayudante: Víctor Zamora Gutiérrez

## Entonrno
* Java: `openjdk version "11.0.3" 2019-04-16 LTS`
* SO: `macOS Mojave Version 10.14.6` (aunque puede funcionar en cualquier SO siempre y 
  cuando se pueda ejecutar `gradlew`)

## Ejecución

### Programa principal
Se le tendrá que pasar un archivo con la lista de ciudades, otro con las semillas (
aunque también puede ser solamente 1) y al final indicando si se quiere imprimir 
todas las evaluaciones o que muestre como van mejorando. Se ejecuta de la
sguiente manera:

```bash
$ ./gradlew run -Pcities=FILE -Pseeds=FILE -PprintBestSols=[true|false]
```

Ejemplo de ejecución:
```bash
$ ./gradlew run -Pcities=samples/input/40-cities.txt -Pseeds=samples/input/40-best-seed.txt -PprintBestSols=true
```

El formato de la entrada para las ciudades debe ser [este](samples/input/40-cities.txt) 
para las semillas como [este](samples/input/40-best-seed.txt).


---
### Genración de semillas
Como la generación de número *aleatorios* para las semillas es un proceso engorroso, 
se hizo un *task* en Gradle para así generar `n` semillas.

Para generar `n` semillas, ejecutar el siguiente comando.
```bash
$ ./gradlew generateSeeds -Pn='N'
```

Imprimiendo `n` números aleatorios para ser usados como semillas.

Ejemplo de ejecución:
```bash
$ ./gradlew generateSeeds -Pn='100'
```


---
### Parser de una solución a un *json*
Hice este *task* para que dada una lista de ciudades, lo convierta a un objeto json 
que uso para así poder mostrar la ruta en un [mapa](/report/assets/img/tsp_best_150.png).
Hice un script en *javascript* para que con el
[API](https://developers.google.com/maps/documentation/javascript/tutorial) de 
Google Maps dado el *json* que muestra el programa, pueda trazar la ruta.

Para ejecutarlo:
```bash
$ ./gradlew parseIds -Pfile=FILE
```

Ejemplo de ejecución
```bash
$ ./gradlew parseIds -Pfile=samples/input/40-best-seed-parser-input.txt
```


---
### Pruebas unitarias
Para ejecutar las pruebas unitarias, las cuales solo evaluan que el `nomalizador`, 
la `distancia máxima` y `función de costo` sean correctas.

```bash
$ ./gradlew test 
```

Para ello, hace uso de los archivos `samples/input/40-cities.txt` y
`samples/input/150-cities.txt` y los valores esperados se nos fueron dados.

---
### Limpieza del proyecto
Para limpiar todos los archivos generados por el proyecto, ejecutar:
```bash
$ ./gradlew clean
```
