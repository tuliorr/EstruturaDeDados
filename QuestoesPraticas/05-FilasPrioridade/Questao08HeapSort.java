import java.util.Arrays;

import Unidade2.P04FilasPrioridade.FilaPrioridadeHeap;

/**
 * Questão 08 - Heap Sort.
 *
 * Remove os maiores valores do heap e preenche o resultado de trás para frente.
 */
public class Questao08HeapSort {

    public static int[] heapSort(int[] valores) {
        int[] ordenados = new int[valores.length];
        if (valores.length == 0) {
            return ordenados;
        }

        FilaPrioridadeHeap<Integer> heap = new FilaPrioridadeHeap<>(valores.length);
        for (int valor : valores) {
            heap.enfileirar(valor);
        }

        for (int i = ordenados.length - 1; i >= 0; i--) {
            ordenados[i] = heap.desenfileirar();
        }
        return ordenados;
    }

    public static void main(String[] args) {
        int[] valores = { 4, 1, 5, 2, 3 };

        System.out.println("Ordem crescente: " + Arrays.toString(heapSort(valores)));
        System.out.println("Resultado esperado: [1, 2, 3, 4, 5]");
    }
}
