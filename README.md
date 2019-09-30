# Facultad de Ciencias, UNAM


# TSP-Threshold-Accepting
## Heurísticas de Optimización Combinatoria


## Información del curso

* Profesor: Canek Peláez Valdés
* Ayudante: Víctor Zamora Gutiérrez

# Entonrno
* Java: `openjdk version "11.0.3" 2019-04-16 LTS`
* SQLite: `SQLite version 3.24.0 2018-06-04 14:10:15`

# Ejecución
Se le tendrá que pasar un archivo con la lista de ciudades y otra con las semillas:
```bash
$ ./gradlew run -Pcities=FILE -Pseeds=FILE
```
Ejemplo:
```bash
$ ./gradlew run -Pcities=examples/input/40-cities.txt -Pseeds=examples/input/seeds_chidas.txt```

Tiene un archivo para generar semillas y su ejecución es así:
```bash
$ ./gradlew generateSeeds -Pn='N'
```
Ejemplo:
```bash
$ ./gradlew generateSeeds -Pn='1'
```
Se puede ver el formato de entrada en `examples/input/40-cities.txt`.


Para ejecutar las pruebas unitarias
```bash
$ ./gradlew test 
```


# TODO
- Mejorar el readme
- Completar los TODOs
- Hacerlo concurrente para cada semilla
- Pasarle bandera para imprimir solo las evaluaciones para pasarla al gnuplot
- Buscar plantilla para el articulo
- Empezar el articulo
- preguntarle al cnake los parámetros
- ver porque tarda un chingo para 150