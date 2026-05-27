/**
 * Questao 10 - Rehash Simplificado.
 *
 * Dobra a capacidade da tabela quando o fator de carga passa de 0.75 e reinsere
 * todos os elementos.
 */
public class Questao10RehashSimplificado {

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
        private static final double FATOR_MAXIMO = 0.75;

        @SuppressWarnings("unchecked")
        TabelaHash(int capacidade) {
            if (capacidade <= 0) {
                throw new IllegalArgumentException("A capacidade deve ser positiva.");
            }
            tabela = (Nodo<T>[]) new Nodo<?>[capacidade];
        }

        int capacidade() {
            return tabela.length;
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
            inserirSemRehash(valor);

            if (fatorDeCarga() > FATOR_MAXIMO) {
                rehash();
            }
        }

        private void inserirSemRehash(T valor) {
            int indice = hash(valor);
            Nodo<T> novo = new Nodo<>(valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
            nElementos++;
        }

        @SuppressWarnings("unchecked")
        private void rehash() {
            Nodo<T>[] tabelaAntiga = tabela;
            tabela = (Nodo<T>[]) new Nodo<?>[tabelaAntiga.length * 2];
            nElementos = 0;

            for (Nodo<T> inicio : tabelaAntiga) {
                Nodo<T> atual = inicio;
                while (atual != null) {
                    // O indice muda porque o modulo agora usa a nova capacidade.
                    inserirSemRehash(atual.valor);
                    atual = atual.proximo;
                }
            }
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

    public static void main(String[] args) {
        TabelaHash<Integer> hash = new TabelaHash<>(4);
        System.out.println("Capacidade antes do rehash: " + hash.capacidade());

        int[] valores = { 1, 2, 3, 4 };
        for (int valor : valores) {
            hash.inserir(valor);
        }

        boolean todosBuscaveis = true;
        for (int valor : valores) {
            todosBuscaveis = todosBuscaveis && hash.buscar(valor);
        }

        System.out.println("Capacidade depois do rehash: " + hash.capacidade());
        System.out.println("Todos os valores continuam buscaveis: " + todosBuscaveis);
        System.out.println("Resultado esperado: 8 / true");
    }
}
