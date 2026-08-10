import java.util.Arrays;

import Unidade2.P04FilasPrioridade.FilaPrioridadeHeap;

/**
 * Questão 04 - Selecionar as k Maiores Prioridades.
 *
 * Usa remoções sucessivas de um max-heap para obter apenas os maiores valores.
 */
public class Questao04SelecionarMaioresPrioridades {

    public static int[] maioresK(int[] valores, int k) {
        if (k == 0) {
            return new int[0];
        }

        FilaPrioridadeHeap<Integer> heap = new FilaPrioridadeHeap<>(valores.length);
        for (int valor : valores) {
            heap.enfileirar(valor);
        }

        int[] maiores = new int[k];
        for (int i = 0; i < k; i++) {
            maiores[i] = heap.desenfileirar();
        }
        return maiores;
    }

    public static void main(String[] args) {
        int[] valores = { 8, 3, 10, 1, 7 };
        System.out.println("Três maiores: " + Arrays.toString(maioresK(valores, 3)));
        System.out.println("Resultado esperado: [10, 8, 7]");
    }
}
