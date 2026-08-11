import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 02 - Intercalar metades.
 *
 * Intercala a primeira metade com a segunda metade de uma fila de tamanho par.
 */
public class Questao02IntercalarMetades {

    /**
     * Altera a própria fila para alternar os elementos de suas duas metades.
     */
    public static <T> void intercalar(FilaDinamica<T> fila) {
        int metade = fila.tamanho() / 2;
        FilaDinamica<T> primeiraMetade = new FilaDinamica<>();

        for (int i = 0; i < metade; i++) {
            primeiraMetade.enfileirar(fila.desenfileirar());
        }

        while (!primeiraMetade.estaVazia()) {
            fila.enfileirar(primeiraMetade.desenfileirar());
            fila.enfileirar(fila.desenfileirar());
        }
    }

    public static void main(String[] args) {
        FilaDinamica<Integer> fila = new FilaDinamica<>();
        for (int valor = 1; valor <= 6; valor++) {
            fila.enfileirar(valor);
        }

        intercalar(fila);
        System.out.println("Fila intercalada: " + fila);
        System.out.println("Resultado esperado: 1, 4, 2, 5, 3, 6");

        // Experimente uma fila com oito elementos.
    }
}
