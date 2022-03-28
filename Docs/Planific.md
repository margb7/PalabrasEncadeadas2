Documentación temporal en markdown.


# Planificación Scrabble II

Este documento non representa todos os métodos das clases pero si os básicos precisos para a realización do programa.

### Clases:

1. **Partida**:

- Atributos: 
    
    1. **PUNTOS_VICTORIA**: _os puntos necesarios para gañar a partida_

    1. **xogadores**: _neste caso dous_

    1. **taboleiro**: _as casillas vacías, con letras ou con multiplicadores_

    1. **numTurno**: _o número de turno da partida_

- Constructores, getters e setters 

- Métodos: 

    1. **xogarPartida()**:  _método para xogar. Ao acabar a partida invócase o método <strong>"amosarResultados"</strong>_

    1. **iniciarTaboleiro()**: _método que coloca os multiplicadores no taboleiro. O obxectivo inicial é que a súa posición sexa aleatoria_

    1. **amosarResultados()**: _método para amosar os resultados da partida. Contempla tamén a posibilidade dun empate entre os xogadores._

    1. **seguinteXogador()**: _método para cambiar de xogador. Non é un método preciso ao ter en conta que son dos xogadores pero separalo permitirá que engadir máis xogadores sexa máis fácil_.

    1. **colocarPalabra()**: _usado para colocar as palabras no taboleiro. Toma como argumentos a posicion, a dirección e a palabra_


2. **Xogador**

    - Atributos: 

        1. **nome**: _o nome do xogador_

        1. **puntuación**: _os puntos gañados na partida_

        1. **numRendicions**: _o número de veces que o xogador pasou na partida. En principio no programa contémplase que o xogador so pode pasar tres veces ata perder._

    - Constructor, getters e setters

    - Métodos:

         1. **podeColocarPalabra()**: _o método úsase para comprobar se o xogador ten as letras para compoñer a palabra dada. Se pode colocala devolverá 1, se pode colocala usando algún comodín devolverá 0 e se non a pode colocar devolve -1._

        1. **retirarLetras()**: _este método retira as letras ou comodíns das letras dispoñibles_

    - Implantar o método toString para reflexar o estado do xogador (a súa puntuación e o seu nome).


3. **Caixa**

    - Atributos: 

        1. **contido**: _vacío se é unha caixa baleira. Tamén pode conter "x2" se é un multiplicador, unha letra,"ch" ou "ll"_

        1. **eMultiplicador**: _booleano para facilitar saber se é un multiplicador. Cando se cambie o valor do contido este booleano debe ser actualizado_

        1. **valor**: _cando o contido non é un multiplicador ou unha cadea vacía conterá os puntos da Caixa_

    - Constructor, getters e setters

    - Métodos:

        1. **calcularPuntuacion()**: _chamará a clase Scrabble para pedir a puntuacion da cadea e se é un multiplicador multiplicará ese valor polo valor do multiplicador_.

4. **Scrabble**

    Clase con métodos estáticos para calcular puntuacións

5. **EntradaSaída**

    Clase para xestionar a entrada do programa e tamén algúns métodos para imprimir erros ou imprimir con cores.
