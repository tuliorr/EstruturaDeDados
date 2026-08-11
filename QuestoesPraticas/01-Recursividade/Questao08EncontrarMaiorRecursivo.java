/**
 * Questão 08 - Encontrar Maior Recursivamente.
 *
 * Encontra recursivamente o maior valor de um vetor não vazio.
 */
public class Questao08EncontrarMaiorRecursivo {

    public static int maior(int[] vetor) {
        return maiorAPartirDe(vetor, 0);
    }

    private static int maiorAPartirDe(int[] vetor, int posicao) {
        if (posicao == vetor.length - 1) {
            return vetor[posicao];
        }

        int maiorDoRestante = maiorAPartirDe(vetor, posicao + 1);
        if (vetor[posicao] > maiorDoRestante) {
            return vetor[posicao];
        }
        return maiorDoRestante;
    }

    public static void main(String[] args) {
        int[] vetor = {7, 2, 9, 4};

        System.out.println("Maior valor: " + maior(vetor));
        System.out.println("Resultado esperado: 9");
    }
}
