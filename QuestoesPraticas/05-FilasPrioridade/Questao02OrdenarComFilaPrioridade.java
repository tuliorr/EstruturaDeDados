import java.util.Arrays;

import Unidade2.P04FilasPrioridade.FilaPrioridadeOrdenada;

/**
 * Questão 02 - Ordenar com Fila de Prioridade.
 *
 * Remove os elementos de uma fila ordenada para obter a ordem decrescente.
 */
public class Questao02OrdenarComFilaPrioridade {

    public static int[] ordenarDecrescente(int[] valores) {
        FilaPrioridadeOrdenada<Integer> fila = new FilaPrioridadeOrdenada<>();

        for (int valor : valores) {
            fila.enfileirar(valor);
        }

        int[] ordenados = new int[valores.length];
        for (int i = 0; i < ordenados.length; i++) {
            ordenados[i] = fila.desenfileirar();
        }
        return ordenados;
    }

    public static void main(String[] args) {
        int[] valores = { 4, 1, 5, 2, 3 };

        System.out.println("Ordem decrescente: " + Arrays.toString(ordenarDecrescente(valores)));
        System.out.println("Resultado esperado: [5, 4, 3, 2, 1]");
    }
}
