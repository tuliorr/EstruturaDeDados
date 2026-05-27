/**
 * Questao 06 - Comparar Conjuntos.
 *
 * Verifica se dois vetores possuem os mesmos valores, ignorando ordem e
 * repeticoes.
 */
public class Questao06CompararConjuntos {

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
            if (buscar(valor)) {
                return;
            }

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
    }

    public static boolean possuemMesmosValores(int[] vetorA, int[] vetorB) {
        TabelaHash<Integer> conjuntoA = criarConjunto(vetorA);
        TabelaHash<Integer> conjuntoB = criarConjunto(vetorB);

        if (conjuntoA.tamanho() != conjuntoB.tamanho()) {
            return false;
        }

        // Como os tamanhos unicos sao iguais, basta conferir se A esta contido em B.
        for (int valor : vetorA) {
            if (!conjuntoB.buscar(valor)) {
                return false;
            }
        }

        return true;
    }

    private static TabelaHash<Integer> criarConjunto(int[] valores) {
        TabelaHash<Integer> conjunto = new TabelaHash<>(valores.length * 2 + 1);
        for (int valor : valores) {
            conjunto.inserir(valor);
        }
        return conjunto;
    }

    public static void main(String[] args) {
        int[] vetorA = { 3, 1, 2, 2 };
        int[] vetorB = { 2, 3, 1 };

        System.out.println("Possuem os mesmos valores: " + possuemMesmosValores(vetorA, vetorB));
        System.out.println("Resultado esperado: true");
    }
}
