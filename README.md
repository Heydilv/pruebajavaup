Descripción
Se plantean dos opciones de examen:

Primera opción

Deberá desarrollar una aplicación web que cumpla con los siguientes requisitos:

Un grupo de investigadores desean compartir informaciones sobre los documentos 
que leen. El que lee registra los datos como:

Autores (ninguno, uno o más de uno), año de publicación, tipo de publicación 
(artículos de revistas, conferencias, capítulos de libros, libros, etc.), 
fuente de publicación (nombres de revistas, libros, conferencias), número de 
citas recibidas, resumen (Texto nomás de 300 palabras).
Datos sobre los hallazgos en el documento como:
Su(s) objetivo(s) (Texto abundante. no más de 250 palabras) -- hallazgo -> objetivo | Varios datos estadisticos
Datos estadísticos (El nombre o identificador del dato es a consideración del 
lector. Por ejemplo: si el considera que la proyección del número de dispositivos 
desplegados para el 2030 es un dato importante entonces crea el nombre del dato: 
con una descripción Número de dispositivos para el 2030, y le da su valor: 
50 mil millones. Otro ejemplo, si cree que el nombre de la planta que trata en 
el documento es importante, entonces crea el dato con una descripción: Nombre de 
la planta, y le da su valor: Juglans regia. Antes de crear el dato, debe buscar 
sino ha sido creado antes, caso de haber sido creado debe usar la misma 
descripción para asignar el valor del nuevo documento encontrado. Para esto 
debe usar un evento ajax para que, cuando esté ingresando las palabras le vaya 
mostrando los elementos que ya están almacenados.
Datos importantes de los logros (Uno o más de uno: Cada uno no más de 250 palabras)
La aplicación web debe tener dos roles de usuarios. El rol investigador y el rol 
administrador. El rol investigador, ingresa y consulta. Las consultas deben ser:

* Investigador:
    - Ingresa
    - consulta

Por título, año, fuente de publicación, por autor, por hallazo. Entre los datos 
que debe mostrar considere mostrar siempre el número de citas recibas. 
Con la opción que en el reporte lo pueda ordenar ascendente o descendente.
Las demás consultas para supletorio
El usuario administrador debe aprobar todos los ingresos que el investigador ha 
ingresado. Mientras no las apruebe no serán consideradas en las consultas de los 
usuarios diferentes a quién ingresó.

* Administrador: 
    - debe aprobar todos los ingresos

Deberá subir el pdf  con:

Cada opción que haya completado:

Captura de las pantallas
Código [vistas -HTML, CSS, JavaScript-, controlador (template), modelo (clases)]. 
Puede variar según el framework que haya escogido.
Tablas de la base de datos que intervienen.
 Al final el enlace a un git donde se encuentre el proyecto completo, si es 
necesario, parte del proyecto será archivo de texto Readme.txt donde especifique 
que hacer para poderlo ejecutar y probar el proyecto (base de datos, datos de 
autenticación, versiones de las herramientas usadas, etc). Preferible la base 
de datos en el servidor proporcionado. Cualquier inconveniente con el servidor 
informar a tiempo, para que se solucione, caso contrario tomar las decisiones 
pertinentes.


// Tareas por hacer:
- Crear un buscador: Por título, año, fuente de publicación, por autor, por hallazo. (+)
- Agregar un campo de aprobado en publicación (+)
- Registrar una publicacion (+)
- crear una pagina para aprobar un ingreso (+)
- Arreglar las consultas de publicación, para que muestro solo las aprobadas (+)
- cambiar el rol de visitante a investigador.(+)
- Crear el modelo de hallazgo (+)
- Crear la tabla de datos (+)
- Crear la tabla de Objetivos (+)
- Crear un boton para ordenas las publicaciones (-)
- Crear la consulta para aprobar una publicación (+)
- [Registrar publicación] Cambiar el tipo de datos en autor.(+)
- [Registrar publicación] Agregar Datos en el registro de una publicación (+)
- [Buscador] se va a filtrar por la tabla hallazgo (+)