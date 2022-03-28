# TESTS:

## Método **podeColocarPalabra()** (clase Xogador) :

+ Contido: letras dispoñibles do xogador
+ Palabra: palabra para comprobar


Caso 	| Contido		| Palabra		| Comodins	| Resultado
C1   	| h o l a		| hola			| 0		| true
C2   	| h "" l a		| hola			| 0 		| false
C3	| h o l a		| h ola			| 0		| false
C4	| hello			| hola			| 0		| true 
C5	| h o			| hola 			| 2		| true
