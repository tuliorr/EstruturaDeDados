/**
 * Questao 07 - Remover Valores Pares.
 *
 * Remove todos os valores pares de uma tabela hash com encadeamento separado.
 */
public class Questao07RemoverPares {

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

        void removerPares() {
            for (int i = 0; i < tabela.length; i++) {
                while (tabela[i] != null && tabela[i].valor % 2 == 0) {
                    tabela[i] = tabela[i].proximo;
                }

                Nodo atual = tabela[i];
                while (atual != null && atual.proximo != null) {
                    if (atual.proximo.valor % 2 == 0) {
                        atual.proximo = atual.proximo.proximo;
                    } else {
                        atual = atual.proximo;
                    }
                }
            }
        }

        void imprimirValores() {
            for (Nodo inicio : tabela) {
                Nodo atual = inicio;
                while (atual != null) {
                    System.out.print(atual.valor + " ");
                    atual = atual.proximo;
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TabelaHash hash = new TabelaHash(10);
        int[] valores = { 4, 7, 10, 13, 22, 31 };

        for (int valor : valores) {
            hash.inserir(valor);
        }

        hash.removerPares();
        System.out.print("Tabela apos remover pares: ");
        hash.imprimirValores();
        System.out.println("Resultado esperado: 31 13 7");
    }
}
