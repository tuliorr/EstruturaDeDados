import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 02 - Intercalar filas.
 *
 * Consome duas filas e forma uma terceira, alternando seus elementos. Quando
 * uma delas termina, os elementos restantes da outra são acrescentados.
 */
public class Questao02IntercalarFilas {

    /**
     * Intercala as filas de entrada e retorna a nova fila.
     */
    public static <T> FilaDinamica<T> intercalar(
            FilaDinamica<T> primeira, FilaDinamica<T> segunda) {
        FilaDinamica<T> resultado = new FilaDinamica<>();

        while (!primeira.estaVazia() || !segunda.estaVazia()) {
            if (!primeira.estaVazia()) {
                resultado.enfileirar(primeira.desenfileirar());
            }
            if (!segunda.estaVazia()) {
                resultado.enfileirar(segunda.desenfileirar());
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        FilaDinamica<String> primeira = new FilaDinamica<>();
        primeira.enfileirar("A");
        primeira.enfileirar("B");
        primeira.enfileirar("C");

        FilaDinamica<String> segunda = new FilaDinamica<>();
        segunda.enfileirar("1");
        segunda.enfileirar("2");

        System.out.println("Fila intercalada: " + intercalar(primeira, segunda));
        System.out.println("Resultado esperado: A, 1, B, 2, C");

        // Experimente inverter os tamanhos das duas filas.
    }
}
