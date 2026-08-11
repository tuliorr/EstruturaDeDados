import Unidade2.P04FilasPrioridade.FilaPrioridadeNaoOrdenada;

/**
 * Questão 01 - Encontrar o Maior Elemento.
 *
 * Usa uma fila de prioridade não ordenada para encontrar o maior valor.
 */
public class Questao01EncontrarMaiorElemento {

    public static int encontrarMaior(int[] valores) {
        FilaPrioridadeNaoOrdenada<Integer> fila = new FilaPrioridadeNaoOrdenada<>();

        for (int valor : valores) {
            fila.enfileirar(valor);
        }

        return fila.desenfileirar();
    }

    public static void main(String[] args) {
        int[] valores = { 4, 1, 7, 3 };

        System.out.println("Maior elemento: " + encontrarMaior(valores));
        System.out.println("Resultado esperado: 7");
    }
}
