import java.util.Arrays;

import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 08 - Próximo elemento maior.
 *
 * Para cada valor, encontra o primeiro elemento maior à sua direita. A pilha
 * guarda os índices que ainda esperam uma resposta.
 */
public class Questao08ProximoElementoMaior {

    /**
     * Retorna o próximo valor maior, ou -1 quando ele não existe.
     */
    public static int[] encontrar(int[] valores) {
        int[] resposta = new int[valores.length];
        Arrays.fill(resposta, -1);

        PilhaDinamica<Integer> indices = new PilhaDinamica<>();

        for (int i = 0; i < valores.length; i++) {
            while (!indices.estaVazia()
                    && valores[i] > valores[indices.consultarTopo()]) {
                int indice = indices.desempilhar();
                resposta[indice] = valores[i];
            }
            indices.empilhar(i);
        }

        return resposta;
    }

    public static void main(String[] args) {
        int[] valores = { 4, 5, 2, 10, 8 };

        System.out.println("Valores: " + Arrays.toString(valores));
        System.out.println("Próximos maiores: " + Arrays.toString(encontrar(valores)));
        System.out.println("Resultado esperado: [5, 10, 10, -1, -1]");

        // Experimente uma sequência sempre decrescente.
    }
}
