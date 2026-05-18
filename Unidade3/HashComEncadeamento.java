package Unidade3;

/**
 * Tabela hash com encadeamento separado. Cada posicao da tabela guarda uma lista
 * para acomodar todos os elementos que colidem naquele indice.
 */
public class HashComEncadeamento<T> {

    // =========================
    // Lista interna para colisoes
    // =========================

    /*
     * Esta lista e propositalmente interna e minima. Ela funciona como "balde" da
     * tabela hash: guarda apenas os elementos que colidiram no mesmo indice.
     *
     * A lista generica da Unidade 2 exige Comparable para permitir insercao
     * ordenada. Aqui isso nao e necessario, porque uma tabela hash precisa apenas
     * de hashCode para escolher o indice e equals para comparar chaves no balde.
     */
    private static class ListaEncadeada<E> {
        private static class Nodo<E> {
            E elemento;
            Nodo<E> proximo;

            Nodo(E elemento) {
                this.elemento = elemento;
            }
        }

        private Nodo<E> inicio;

        void inserir(E elemento) {
            Nodo<E> novo = new Nodo<>(elemento);
            novo.proximo = inicio;
            inicio = novo;
        }

        boolean contem(E elemento) {
            Nodo<E> atual = inicio;
            while (atual != null) {
                if (atual.elemento.equals(elemento)) {
                    return true;
                }
                atual = atual.proximo;
            }
            return false;
        }

        boolean remover(E elemento) {
            if (inicio == null) {
                return false;
            }
            if (inicio.elemento.equals(elemento)) {
                inicio = inicio.proximo;
                return true;
            }

            Nodo<E> atual = inicio;
            while (atual.proximo != null) {
                if (atual.proximo.elemento.equals(elemento)) {
                    atual.proximo = atual.proximo.proximo;
                    return true;
                }
                atual = atual.proximo;
            }
            return false;
        }

        @Override
        public String toString() {
            if (inicio == null) {
                return "[vazio]";
            }

            StringBuilder sb = new StringBuilder();
            Nodo<E> atual = inicio;
            while (atual != null) {
                sb.append(atual.elemento).append(" -> ");
                atual = atual.proximo;
            }
            sb.append("null");
            return sb.toString();
        }
    }

    // =========================
    // Atributos
    // =========================

    private ListaEncadeada<T>[] tabela;
    private int capacidade;
    private int nElementos;

    // =========================
    // Construtor
    // =========================

    @SuppressWarnings("unchecked")
    public HashComEncadeamento(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        /*
         * Java nao permite criar diretamente um array de ListaEncadeada<T>, porque
         * genericos sao apagados em tempo de execucao. O cast fica isolado aqui e e
         * seguro porque todas as posicoes do array recebem ListaEncadeada<T>.
         */
        this.tabela = (ListaEncadeada<T>[]) new ListaEncadeada<?>[capacidade];
        this.nElementos = 0;

        for (int i = 0; i < capacidade; i++) {
            tabela[i] = new ListaEncadeada<>();
        }
    }

    // =========================
    // Metodos basicos
    // =========================

    public boolean estaVazia() {
        return nElementos == 0;
    }

    public int tamanho() {
        return nElementos;
    }

    public double fatorDeCarga() {
        return (double) nElementos / capacidade;
    }

    // =========================
    // Funcao de hashing
    // =========================

    private int hash(T chave) {
        if (chave == null) {
            throw new IllegalArgumentException("A chave nao pode ser null.");
        }
        return Math.floorMod(chave.hashCode(), capacidade);
    }

    // =========================
    // Insercao
    // =========================

    public void inserir(T chave) {
        int indice = hash(chave);
        if (!tabela[indice].contem(chave)) {
            tabela[indice].inserir(chave);
            nElementos++;
        }
    }

    // =========================
    // Remocao
    // =========================

    public boolean remover(T chave) {
        int indice = hash(chave);
        if (tabela[indice].remover(chave)) {
            nElementos--;
            return true;
        }
        return false;
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean buscar(T chave) {
        return tabela[hash(chave)].contem(chave);
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hash com encadeamento\n");
        for (int i = 0; i < capacidade; i++) {
            sb.append(i).append(": ").append(tabela[i]).append("\n");
        }
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: HashComEncadeamento ===");

        // 7, 17 e 27 caem no mesmo indice quando a capacidade e 10.
        HashComEncadeamento<Integer> hash = new HashComEncadeamento<>(10);
        hash.inserir(7);
        hash.inserir(17);
        hash.inserir(27);
        hash.inserir(3);

        System.out.println(hash);
        System.out.println("Busca 17: " + hash.buscar(17));
        System.out.println("Fator de carga: " + hash.fatorDeCarga());
        System.out.println("Remove 17: " + hash.remover(17));
        System.out.println(hash);
    }
}
