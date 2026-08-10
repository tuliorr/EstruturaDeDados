import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 04 - Verificar sequência de desempilhamento.
 *
 * Verifica se uma ordem de saída pode ser obtida ao empilhar os valores na
 * ordem de entrada e desempilhar em momentos escolhidos.
 */
public class Questao04VerificarSequenciaDesempilhamento {

    /**
     * Retorna true quando a sequência de saída é possível.
     */
    public static boolean podeDesempilhar(int[] entrada, int[] saida) {
        if (entrada.length != saida.length) {
            return false;
        }

        PilhaDinamica<Integer> pilha = new PilhaDinamica<>();
        int proximaSaida = 0;

        for (int valor : entrada) {
            pilha.empilhar(valor);

            while (!pilha.estaVazia()
                    && proximaSaida < saida.length
                    && pilha.consultarTopo() == saida[proximaSaida]) {
                pilha.desempilhar();
                proximaSaida++;
            }
        }

        return proximaSaida == saida.length;
    }

    public static void main(String[] args) {
        int[] entrada = { 1, 2, 3, 4, 5 };
        int[] saidaPossivel = { 4, 5, 3, 2, 1 };
        int[] saidaImpossivel = { 4, 3, 5, 1, 2 };

        System.out.println("Primeira sequência é possível: "
                + podeDesempilhar(entrada, saidaPossivel));
        System.out.println("Segunda sequência é possível: "
                + podeDesempilhar(entrada, saidaImpossivel));
        System.out.println("Resultados esperados: true e false");

        // Experimente criar outra saída trocando apenas dois valores.
    }
}
