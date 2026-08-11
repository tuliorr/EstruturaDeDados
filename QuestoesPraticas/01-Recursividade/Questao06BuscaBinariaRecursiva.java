/**
 * Questão 06 - Busca Binária Recursiva.
 *
 * Busca um valor em um vetor crescente e retorna o índice encontrado.
 */
public class Questao06BuscaBinariaRecursiva {

    public static int buscaBinaria(int[] vetor, int valor) {
        return buscarNoIntervalo(vetor, valor, 0, vetor.length - 1);
    }

    private static int buscarNoIntervalo(int[] vetor, int valor, int inicio, int fim) {
        if (inicio > fim) {
            return -1;
        }

        int meio = (inicio + fim) / 2;
        if (vetor[meio] == valor) {
            return meio;
        }
        if (valor < vetor[meio]) {
            return buscarNoIntervalo(vetor, valor, inicio, meio - 1);
        }
        return buscarNoIntervalo(vetor, valor, meio + 1, fim);
    }

    public static void main(String[] args) {
        int[] vetor = {2, 4, 6, 8, 10};

        System.out.println("Índice do valor 8: " + buscaBinaria(vetor, 8));
        System.out.println("Resultado esperado: 3");
    }
}
