/**
 * Questao 01 - Contar Colisoes.
 *
 * Dada uma tabela hash com encadeamento separado, conta quantas posicoes da
 * tabela possuem mais de um elemento.
 */
public class Questao01ContarColisoes {

    private static class Nodo {
        int valor;
        Nodo proximo;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private static class TabelaHash {
        private Nodo[] tabela;

        TabelaHash(int capacidade) {
            tabela = new Nodo[capacidade];
        }

        private int hash(int valor) {
            return Math.floorMod(valor, tabela.length);
        }

        void inserir(int valor) {
            int indice = hash(valor);
            Nodo novo = new Nodo(valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
        }

        int contarColisoes() {
            int colisoes = 0;

            for (Nodo inicio : tabela) {
                int quantidade = contarElementos(inicio);
                if (quantidade > 1) {
                    colisoes++;
                }
            }

            return colisoes;
        }

        private int contarElementos(Nodo inicio) {
            int quantidade = 0;
            Nodo atual = inicio;
            while (atual != null) {
                quantidade++;
                atual = atual.proximo;
            }
            return quantidade;
        }
    }

    public static void main(String[] args) {
        TabelaHash hash = new TabelaHash(10);
        int[] valores = { 7, 17, 27, 3, 13 };

        for (int valor : valores) {
            hash.inserir(valor);
        }

        System.out.println("Quantidade de posicoes com colisao: " + hash.contarColisoes());
        System.out.println("Resultado esperado: 2");
    }
}
