import java.util.Arrays;

import Unidade2.P03Filas.FilaEstatica;

/**
 * Questão 03 - Média móvel.
 *
 * Uma fila estática representa uma janela de tamanho fixo. A soma acumulada
 * evita percorrer novamente todos os elementos para calcular cada média.
 */
public class Questao03MediaMovel {

    /**
     * Retorna as médias de todas as janelas completas.
     */
    public static double[] calcular(double[] valores, int tamanhoJanela) {
        FilaEstatica<Double> janela = new FilaEstatica<>(tamanhoJanela);
        double[] medias = new double[valores.length - tamanhoJanela + 1];
        double soma = 0.0;
        int proximaMedia = 0;

        for (double valor : valores) {
            if (janela.estaCheia()) {
                soma -= janela.desenfileirar();
            }
            janela.enfileirar(valor);
            soma += valor;

            if (janela.tamanho() == tamanhoJanela) {
                medias[proximaMedia] = soma / tamanhoJanela;
                proximaMedia++;
            }
        }
        return medias;
    }

    public static void main(String[] args) {
        double[] valores = { 10, 20, 30, 40, 50 };

        System.out.println("Valores: " + Arrays.toString(valores));
        System.out.println("Médias de tamanho 3: " + Arrays.toString(calcular(valores, 3)));
        System.out.println("Resultado esperado: [20.0, 30.0, 40.0]");

        // Experimente janelas de tamanho 1 e 5.
    }
}
