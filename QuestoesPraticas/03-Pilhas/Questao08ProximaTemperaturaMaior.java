import java.util.Arrays;

import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 08 - Próxima temperatura maior.
 *
 * Uma pilha monótona guarda índices cujas temperaturas ainda não encontraram
 * um dia futuro mais quente.
 */
public class Questao08ProximaTemperaturaMaior {

    /**
     * Para cada dia, retorna quantos dias faltam para uma temperatura maior.
     * Quando ela não existe, mantém zero na resposta.
     */
    public static int[] calcularEsperas(int[] temperaturas) {
        int[] esperas = new int[temperaturas.length];
        PilhaDinamica<Integer> indices = new PilhaDinamica<>();

        for (int dia = 0; dia < temperaturas.length; dia++) {
            while (!indices.estaVazia()
                    && temperaturas[dia] > temperaturas[indices.consultarTopo()]) {
                int diaAnterior = indices.desempilhar();
                esperas[diaAnterior] = dia - diaAnterior;
            }
            indices.empilhar(dia);
        }
        return esperas;
    }

    public static void main(String[] args) {
        int[] temperaturas = { 30, 40, 35, 50, 45 };

        System.out.println("Temperaturas: " + Arrays.toString(temperaturas));
        System.out.println("Esperas: " + Arrays.toString(calcularEsperas(temperaturas)));
        System.out.println("Resultado esperado: [1, 2, 1, 0, 0]");

        // Experimente uma sequência sempre decrescente.
    }
}
