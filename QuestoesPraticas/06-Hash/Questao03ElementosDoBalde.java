/**
 * Questao 03 - Elementos de um Balde.
 *
 * Imprime os elementos armazenados em uma posicao especifica de uma tabela hash
 * com encadeamento externo.
 */
public class Questao03ElementosDoBalde {

    private static class Nodo<T> {
        T valor;
        Nodo<T> proximo;

        Nodo(T valor) {
            this.valor = valor;
        }
    }

    private static class TabelaHash<T> {
        private Nodo<T>[] tabela;
        private int nElementos;

        @SuppressWarnings("unchecked")
        TabelaHash(int capacidade) {
            if (capacidade <= 0) {
                throw new IllegalArgumentException("A capacidade deve ser positiva.");
            }
            tabela = (Nodo<T>[]) new Nodo<?>[capacidade];
        }

        int tamanho() {
            return nElementos;
        }

        double fatorDeCarga() {
            return (double) nElementos / tabela.length;
        }

        private int hash(T valor) {
            if (valor == null) {
                throw new IllegalArgumentException("O valor nao pode ser null.");
            }
            return Math.floorMod(valor.hashCode(), tabela.length);
        }

        void inserir(T valor) {
            int indice = hash(valor);
            Nodo<T> novo = new Nodo<>(valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
            nElementos++;
        }

        boolean buscar(T valor) {
            Nodo<T> atual = tabela[hash(valor)];
            while (atual != null) {
                if (atual.valor.equals(valor)) {
                    return true;
                }
                atual = atual.proximo;
            }
            return false;
        }

        boolean remover(T valor) {
            int indice = hash(valor);
            Nodo<T> atual = tabela[indice];
            Nodo<T> anterior = null;

            while (atual != null) {
                if (atual.valor.equals(valor)) {
                    if (anterior == null) {
                        tabela[indice] = atual.proximo;
                    } else {
                        anterior.proximo = atual.proximo;
                    }
                    nElementos--;
                    return true;
                }
                anterior = atual;
                atual = atual.proximo;
            }
            return false;
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
            Nodo<T> atual = tabela[indice];
            while (atual != null) {
                // Cada nodo visitado e um elemento que colidiu naquele indice.
                System.out.print(atual.valor + " ");
                atual = atual.proximo;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TabelaHash<Integer> hash = new TabelaHash<>(10);
        int[] valores = { 7, 17, 27, 3 };

        for (int valor : valores) {
            hash.inserir(valor);
        }

        hash.imprimirBalde(7);
        System.out.println("Resultado esperado: Elementos do balde 7: 27 17 7");
    }
}
