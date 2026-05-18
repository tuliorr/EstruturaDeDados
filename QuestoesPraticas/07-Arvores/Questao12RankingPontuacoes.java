/**
 * Questao 12 - Listar Pontuacoes em Ranking.
 *
 * Usa uma BST para imprimir pontuacoes da maior para a menor.
 */
public class Questao12RankingPontuacoes {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private static class Ranking {
        private Nodo raiz;

        void inserirPontuacao(int pontuacao) {
            raiz = inserir(raiz, pontuacao);
        }

        private Nodo inserir(Nodo nodo, int valor) {
            if (nodo == null) {
                return new Nodo(valor);
            }
            if (valor < nodo.valor) {
                nodo.esquerda = inserir(nodo.esquerda, valor);
            } else if (valor > nodo.valor) {
                nodo.direita = inserir(nodo.direita, valor);
            }
            return nodo;
        }

        void imprimirRanking() {
            imprimirDecrescente(raiz);
            System.out.println();
        }

        private void imprimirDecrescente(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            imprimirDecrescente(nodo.direita);
            System.out.print(nodo.valor + " ");
            imprimirDecrescente(nodo.esquerda);
        }
    }

    public static void main(String[] args) {
        Ranking ranking = new Ranking();
        int[] pontuacoes = { 80, 95, 70, 100, 85 };

        for (int pontuacao : pontuacoes) {
            ranking.inserirPontuacao(pontuacao);
        }

        System.out.print("Ranking: ");
        ranking.imprimirRanking();
        System.out.println("Resultado esperado: 100 95 85 80 70");
    }
}
