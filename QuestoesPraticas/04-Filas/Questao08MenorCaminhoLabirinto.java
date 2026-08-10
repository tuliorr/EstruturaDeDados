import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 08 - Menor caminho em um labirinto.
 *
 * A busca em largura (BFS) visita primeiro todas as posições a uma mesma
 * distância. Por isso, a primeira chegada ao destino usa o menor caminho.
 */
public class Questao08MenorCaminhoLabirinto {

    private static class Posicao {
        final int linha;
        final int coluna;
        final int distancia;

        Posicao(int linha, int coluna, int distancia) {
            this.linha = linha;
            this.coluna = coluna;
            this.distancia = distancia;
        }
    }

    /**
     * Retorna a quantidade mínima de passos, ou -1 quando não existe caminho.
     * Zero representa passagem livre e um representa parede.
     */
    public static int menorCaminho(int[][] labirinto,
            int linhaInicial, int colunaInicial,
            int linhaFinal, int colunaFinal) {
        if (labirinto[linhaInicial][colunaInicial] != 0
                || labirinto[linhaFinal][colunaFinal] != 0) {
            return -1;
        }

        boolean[][] visitado = new boolean[labirinto.length][labirinto[0].length];
        FilaDinamica<Posicao> fila = new FilaDinamica<>();
        fila.enfileirar(new Posicao(linhaInicial, colunaInicial, 0));
        visitado[linhaInicial][colunaInicial] = true;

        int[][] direcoes = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!fila.estaVazia()) {
            Posicao atual = fila.desenfileirar();
            if (atual.linha == linhaFinal && atual.coluna == colunaFinal) {
                return atual.distancia;
            }

            for (int[] direcao : direcoes) {
                int novaLinha = atual.linha + direcao[0];
                int novaColuna = atual.coluna + direcao[1];

                if (estaDentro(labirinto, novaLinha, novaColuna)
                        && labirinto[novaLinha][novaColuna] == 0
                        && !visitado[novaLinha][novaColuna]) {
                    visitado[novaLinha][novaColuna] = true;
                    fila.enfileirar(new Posicao(
                            novaLinha, novaColuna, atual.distancia + 1));
                }
            }
        }
        return -1;
    }

    private static boolean estaDentro(int[][] labirinto, int linha, int coluna) {
        return linha >= 0 && linha < labirinto.length
                && coluna >= 0 && coluna < labirinto[0].length;
    }

    public static void main(String[] args) {
        int[][] labirinto = {
            { 0, 1, 0, 0 },
            { 0, 0, 0, 1 },
            { 1, 1, 0, 0 },
            { 0, 0, 0, 0 }
        };

        System.out.println("Menor quantidade de passos: "
                + menorCaminho(labirinto, 0, 0, 3, 3));
        System.out.println("Resultado esperado: 6");

        // Como melhoria, valide matrizes vazias, irregulares e coordenadas inválidas.
    }
}
