import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 07 - Ordenação Radix.
 *
 * Dez filas separam números pelo algarismo atual. A coleta dos baldes de 0 a 9
 * mantém a ordem necessária para processar o próximo algarismo.
 */
public class Questao07OrdenacaoRadix {

    /**
     * Ordena uma cópia do vetor de inteiros não negativos.
     */
    public static int[] ordenar(int[] valores) {
        int[] resultado = Arrays.copyOf(valores, valores.length);
        int maior = 0;
        for (int valor : resultado) {
            maior = Math.max(maior, valor);
        }

        List<FilaDinamica<Integer>> baldes = criarBaldes();
        for (long divisor = 1; maior / divisor > 0; divisor *= 10) {
            for (int valor : resultado) {
                int algarismo = (int) ((valor / divisor) % 10);
                baldes.get(algarismo).enfileirar(valor);
            }

            int indice = 0;
            for (FilaDinamica<Integer> balde : baldes) {
                while (!balde.estaVazia()) {
                    resultado[indice] = balde.desenfileirar();
                    indice++;
                }
            }
        }
        return resultado;
    }

    private static List<FilaDinamica<Integer>> criarBaldes() {
        List<FilaDinamica<Integer>> baldes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            baldes.add(new FilaDinamica<Integer>());
        }
        return baldes;
    }

    public static void main(String[] args) {
        int[] valores = { 170, 45, 75, 90, 802, 24, 2, 66 };

        System.out.println("Valores: " + Arrays.toString(valores));
        System.out.println("Ordenados: " + Arrays.toString(ordenar(valores)));
        System.out.println("Resultado esperado: [2, 24, 45, 66, 75, 90, 170, 802]");

        // Experimente acrescentar números com um, dois e quatro algarismos.
    }
}
