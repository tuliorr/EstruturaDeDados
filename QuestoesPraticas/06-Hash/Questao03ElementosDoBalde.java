/**
 * Questao 03 - Elementos de um Balde.
 *
 * Imprime os elementos armazenados em uma posicao especifica de uma tabela hash
 * com encadeamento separado.
 */
public class Questao03ElementosDoBalde {

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

        void imprimirBalde(int indice) {
            if (indice < 0 || indice >= tabela.length) {
                System.out.println("Indice invalido: " + indice);
                return;
            }

            if (tabela[indice] == null) {
                System.out.println("Balde " + indice + " vazio.");
                return;
            }

            System.out.print("Elementos do balde " + indice + ": ");
            Nodo atual = tabela[indice];
            while (atual != null) {
                System.out.print(atual.valor + " ");
                atual = atual.proximo;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TabelaHash hash = new TabelaHash(10);
        int[] valores = { 7, 17, 27, 3 };

        for (int valor : valores) {
            hash.inserir(valor);
        }

        hash.imprimirBalde(7);
        System.out.println("Resultado esperado: Elementos do balde 7: 27 17 7");
    }
}
