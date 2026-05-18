import java.util.HashSet;
import java.util.Set;

/**
 * Questao 06 - Comparar Conjuntos.
 *
 * Verifica se dois vetores possuem os mesmos valores, ignorando ordem e
 * repeticoes.
 */
public class Questao06CompararConjuntos {

    public static boolean possuemMesmosValores(int[] vetorA, int[] vetorB) {
        Set<Integer> conjuntoA = criarConjunto(vetorA);
        Set<Integer> conjuntoB = criarConjunto(vetorB);
        return conjuntoA.equals(conjuntoB);
    }

    private static Set<Integer> criarConjunto(int[] valores) {
        Set<Integer> conjunto = new HashSet<>();
        for (int valor : valores) {
            conjunto.add(valor);
        }
        return conjunto;
    }

    public static void main(String[] args) {
        int[] vetorA = { 3, 1, 2, 2 };
        int[] vetorB = { 2, 3, 1 };

        System.out.println("Possuem os mesmos valores: " + possuemMesmosValores(vetorA, vetorB));
        System.out.println("Resultado esperado: true");
    }
}
