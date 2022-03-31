# PalabrasEncadeadas2

- Proxecto en netbeans con ant
- Diagrama de clases creado con [draw.io](https://app.diagrams.net)

## Especificación

Vamos a partir das normas xerais do Scrabble que podes atopar no ficheiro adxunto. De forma xenérica coméntanse a continuación:

- O taboleiro será de 21x21 (modificación sobre as regras xerais)
- Haberá só 2 xogadores. (modificación sobre as regras xerais)
- Cada xogador parte con 7 letras extraídas do total de letras a usar.
- A primeira palabra do xogo debe ter algunha das letras no centro do taboleiro.
- Puntuarase polas letras usadas e tamén por usar casiñas con premio.
- Se se empregan nunha xogada as 7 letras farase Scrabble polo que se puntuará directamente con 50 puntos ademais da puntuación da propia xogada.
- Ao final da partida, colleranse tantas letras como letras foron usadas ata un total de 7 ou ata que non haxa pezas dispoñibles.
- Nas seguintes partidas, o xogador debe formar unha palabra, usando polo menos unha letra das que xa están colocadas sobre o taboleiro.
-Cada xogador pode pasar nunha das partidas.
- O xogo remata cando:
  - Algún xogador pasou 3 veces. (modificación sobre as regras xerais)
  - Xa non hai fichas dispoñibles e un xogador xa non ten letras para xogar.
- Para saber onde colocar a palabra o usuario terá que indicar:
  - A referencia usada para indicar a casiña onde se empezará a colocar a primeira letra da palabra.
  - Se a palabra se coloca en horizontal ou en vertical.
  - A propia palabra. Non será preciso comprobar que a palabra sexa correcta. Daremos por suposto que o usuario introduce correctamente unha palabra de castelán.
- Cando se escribe unha palabra, excepto a primeira terá que ter letras coincidentes coas colocadas no taboleiro.
- Cada vez que se escriba unha palabra, puntuarase a palabra completa, incluídas as letras que xa estiveran no taboleiro.

Manterase a puntuación das letras usada ata o momento aínda que difira das normas xerais, pero engadirase a CH, a LL, a RR e eliminarase a W e a K. A puntuación destas novas letras será:
CH: 5 puntos.
LL e RR: 8 puntos

Haberá dous comodíns que se poden empregar en substitución dunha letra cada un. En cada xogada só se poderá usar un comodín.

As letras dispoñibles para xogar son as que se mostran na seguinte táboa (os últimos dous ocos representan aos 2 comodíns: (modificación sobre as regras xerais)

NOTA: para calquera aclaración das puntuacións e letras dispoñibles ver o enlace que podes atopar aquí da Wikipedia.

Despois de cada xogada deberase mostrar o seguinte:

- Puntuación obtida polo xogador na propia xogada.
- Clasificación xeral mostrando a puntuación dos dous xogadores.
- Mostrar como queda o taboleiro de xogo para poder realizar a seguinte xogada.

As casiñas de premio repartiranse aleatoriamente ao principio do xogo polo taboleiro e serán as que se listan a continuación (non se seguen as regas xerais do xogo). Deberase ter en conta que cando se use unha casiña de premio, xa non se poderá usar máis no xogo.

> 2 casiñas onde a palabra vale 2 veces a súa puntuación.
> 3 casiñas onde a letra vale 4 veces a súa puntuación.
> 4 casiñas onde a letra vale 3 veces a súa puntuación.
> 5 casiñas onde a letra vale 2 veces a súa puntuación.
