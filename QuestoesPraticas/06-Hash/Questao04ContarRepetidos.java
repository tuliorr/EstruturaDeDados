import java.util.HashMap;
import java.util.Map;

/**
 * Questao 04 - Contar Valores Repetidos.
 *
 * Conta quantos valores diferentes aparecem mais de uma vez em um vetor.
 */
public class Questao04ContarRepetidos {

    public static int contarValoresRepetidos(int[] valores) {
        Map<Integer, Integer> frequencias = new HashMap<>();

        for (int valor : valores) {
            int frequenciaAtual = frequencias.getOrDefault(valor, 0);
            frequencias.put(valor, frequenciaAtual + 1);
        }

        int repetidos = 0;
        for (int frequencia : frequencias.values()) {
            if (frequencia > 1) {
                repetidos++;
            }
        }

        return repetidos;
    }

    public static void main(String[] args) {
        int[] valores = { 4, 7, 4, 2, 7, 9, 7 };

        System.out.println("Quantidade de valores repetidos: " + contarValoresRepetidos(valores));
        System.out.println("Resultado esperado: 2");
    }
}
