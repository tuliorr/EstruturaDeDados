import java.util.Arrays;

import Unidade2.P04FilasPrioridade.FilaPrioridadeHeap;

/**
 * Questão 07 - Mesclar k Sequências Ordenadas.
 *
 * Inverte a comparação para que o menor valor tenha a maior prioridade no heap.
 */
public class Questao07MesclarSequenciasOrdenadas {

    private static class Item implements Comparable<Item> {
        int valor;
        int sequencia;
        int posicao;

        Item(int valor, int sequencia, int posicao) {
            this.valor = valor;
            this.sequencia = sequencia;
            this.posicao = posicao;
        }

        @Override
        public int compareTo(Item outro) {
            return Integer.compare(outro.valor, valor);
        }
    }

    public static int[] mesclarOrdenados(int[][] sequencias) {
        int total = 0;
        for (int[] sequencia : sequencias) {
            total += sequencia.length;
        }
        if (total == 0) {
            return new int[0];
        }

        FilaPrioridadeHeap<Item> heap = new FilaPrioridadeHeap<>(sequencias.length);
        for (int i = 0; i < sequencias.length; i++) {
            if (sequencias[i].length > 0) {
                heap.enfileirar(new Item(sequencias[i][0], i, 0));
            }
        }

        int[] resultado = new int[total];
        int indiceResultado = 0;
        while (!heap.estaVazia()) {
            Item menor = heap.desenfileirar();
            resultado[indiceResultado++] = menor.valor;

            int proximaPosicao = menor.posicao + 1;
            if (proximaPosicao < sequencias[menor.sequencia].length) {
                heap.enfileirar(new Item(
                        sequencias[menor.sequencia][proximaPosicao],
                        menor.sequencia,
                        proximaPosicao));
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        int[][] sequencias = { { 1, 4, 7 }, { 2, 5 }, { 3, 6, 8 } };
        System.out.println("Mescla: " + Arrays.toString(mesclarOrdenados(sequencias)));
        System.out.println("Resultado esperado: [1, 2, 3, 4, 5, 6, 7, 8]");
    }
}
