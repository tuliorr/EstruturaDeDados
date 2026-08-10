import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questao 12 - Listar Pontuacoes em Ranking.
 *
 * Usa uma BST para imprimir pontuacoes da maior para a menor.
 */
public class Questao12RankingPontuacoes {

    private static class Nodo {
        int valor;
        int frequencia;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
            this.frequencia = 1;
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
            } else {
                nodo.frequencia++;
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
            imprimirPontuacao(nodo);
            imprimirDecrescenteRecursivo(nodo.esquerda);
        }

        private void imprimirPontuacao(Nodo nodo) {
            for (int i = 0; i < nodo.frequencia; i++) {
                System.out.print(nodo.valor + " ");
            }
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
                imprimirPontuacao(atual);
                atual = atual.esquerda;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Ranking ranking = new Ranking();
        int[] pontuacoes = { 80, 95, 70, 100, 85, 95, 80 };

        for (int pontuacao : pontuacoes) {
            ranking.inserirPontuacao(pontuacao);
        }

        System.out.print("Ranking (recursivo): ");
        ranking.imprimirRankingRecursivo();
        System.out.print("Ranking (iterativo): ");
        ranking.imprimirRankingIterativo();
        System.out.println("Resultado esperado: 100 95 95 85 80 80 70");
    }
}
