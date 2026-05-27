import Unidade2.P02Pilhas.PilhaDinamica;

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

        boolean contemPontuacao(int pontuacao) {
            Nodo atual = raiz;
            while (atual != null) {
                if (pontuacao == atual.valor) {
                    return true;
                }
                atual = pontuacao < atual.valor ? atual.esquerda : atual.direita;
            }
            return false;
        }

        void imprimirRankingRecursivo() {
            imprimirDecrescenteRecursivo(raiz);
            System.out.println();
        }

        private void imprimirDecrescenteRecursivo(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            // Pontuacoes maiores ficam na direita, entao aparecem primeiro.
            imprimirDecrescenteRecursivo(nodo.direita);
            System.out.print(nodo.valor + " ");
            imprimirDecrescenteRecursivo(nodo.esquerda);
        }

        void imprimirRankingIterativo() {
            PilhaDinamica<Nodo> pilha = new PilhaDinamica<>();
            Nodo atual = raiz;

            while (atual != null || !pilha.estaVazia()) {
                while (atual != null) {
                    pilha.empilhar(atual);
                    atual = atual.direita;
                }

                atual = pilha.desempilhar();
                System.out.print(atual.valor + " ");
                atual = atual.esquerda;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Ranking ranking = new Ranking();
        int[] pontuacoes = { 80, 95, 70, 100, 85 };

        for (int pontuacao : pontuacoes) {
            ranking.inserirPontuacao(pontuacao);
        }

        System.out.print("Ranking (recursivo): ");
        ranking.imprimirRankingRecursivo();
        System.out.print("Ranking (iterativo): ");
        ranking.imprimirRankingIterativo();
        System.out.println("Resultado esperado: 100 95 85 80 70");
    }
}
