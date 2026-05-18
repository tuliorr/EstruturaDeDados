/**
 * Questao 09 - Classificar Fator de Carga.
 *
 * Calcula o fator de carga de uma tabela hash e retorna uma classificacao
 * textual.
 */
public class Questao09ClassificarFatorCarga {

    public static double fatorDeCarga(int nElementos, int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        return (double) nElementos / capacidade;
    }

    public static String classificarFatorDeCarga(int nElementos, int capacidade) {
        double fator = fatorDeCarga(nElementos, capacidade);

        if (fator < 0.5) {
            return "leve";
        }
        if (fator <= 0.75) {
            return "moderada";
        }
        return "cheia";
    }

    public static void main(String[] args) {
        int nElementos = 6;
        int capacidade = 10;

        System.out.println("Fator de carga: " + fatorDeCarga(nElementos, capacidade));
        System.out.println("Classificacao: " + classificarFatorDeCarga(nElementos, capacidade));
        System.out.println("Resultado esperado: 0.6 / moderada");
    }
}
