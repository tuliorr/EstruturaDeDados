package Unidade3;

/**
 * Mapa hash generico com encadeamento e redimensionamento. Cada entrada guarda
 * chave e valor, permitindo recuperar rapidamente o valor a partir da chave.
 */
public class HashMapGenerico<K, V> {

    // =========================
    // Classe interna do nodo
    // =========================

    private static class Nodo<K, V> {
        K chave;
        V valor;
        Nodo<K, V> proximo;

        Nodo(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Nodo<K, V>[] tabela;
    private int capacidade;
    private int nElementos;
    private static final double FATOR_CARGA_MAXIMO = 0.75;

    // =========================
    // Construtor
    // =========================

    @SuppressWarnings("unchecked")
    public HashMapGenerico(int capacidadeInicial) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidadeInicial;
        this.tabela = (Nodo<K, V>[]) new Nodo<?, ?>[capacidadeInicial];
        this.nElementos = 0;
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

    private int hash(K chave) {
        if (chave == null) {
            throw new IllegalArgumentException("A chave nao pode ser null.");
        }
        return Math.floorMod(chave.hashCode(), capacidade);
    }

    // =========================
    // Insercao
    // =========================

    /**
     * Insere ou atualiza. Se a chave ja existe, mudamos o valor; se nao existe,
     * criamos um novo nodo no inicio da lista daquele indice.
     */
    public void inserir(K chave, V valor) {
        if ((double) (nElementos + 1) / capacidade > FATOR_CARGA_MAXIMO) {
            redimensionar();
        }

        int indice = hash(chave);
        Nodo<K, V> atual = tabela[indice];
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                atual.valor = valor;
                return;
            }
            atual = atual.proximo;
        }

        Nodo<K, V> novo = new Nodo<>(chave, valor);
        novo.proximo = tabela[indice];
        tabela[indice] = novo;
        nElementos++;
    }

    /**
     * Ao crescer a tabela, todos os indices precisam ser recalculados porque o
     * modulo passa a usar uma nova capacidade.
     */
    @SuppressWarnings("unchecked")
    private void redimensionar() {
        Nodo<K, V>[] tabelaAntiga = tabela;
        int capacidadeAntiga = capacidade;

        capacidade *= 2;
        tabela = (Nodo<K, V>[]) new Nodo<?, ?>[capacidade];

        for (int i = 0; i < capacidadeAntiga; i++) {
            Nodo<K, V> atual = tabelaAntiga[i];
            while (atual != null) {
                Nodo<K, V> proximo = atual.proximo;
                int novoIndice = hash(atual.chave);
                atual.proximo = tabela[novoIndice];
                tabela[novoIndice] = atual;
                atual = proximo;
            }
        }
    }

    // =========================
    // Remocao
    // =========================

    public boolean remover(K chave) {
        int indice = hash(chave);
        Nodo<K, V> atual = tabela[indice];
        Nodo<K, V> anterior = null;

        while (atual != null) {
            if (atual.chave.equals(chave)) {
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

    // =========================
    // Busca e consulta
    // =========================

    public V buscar(K chave) {
        Nodo<K, V> atual = tabela[hash(chave)];
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                return atual.valor;
            }
            atual = atual.proximo;
        }
        return null;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HashMapGenerico ");
        sb.append("(").append(nElementos).append("/").append(capacidade).append(")\n");

        for (int i = 0; i < capacidade; i++) {
            sb.append(i).append(": ");
            Nodo<K, V> atual = tabela[i];
            if (atual == null) {
                sb.append("[vazio]");
            }
            while (atual != null) {
                sb.append("[").append(atual.chave).append("=").append(atual.valor).append("] -> ");
                atual = atual.proximo;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: HashMapGenerico ===");

        // A capacidade inicial pequena provoca redimensionamento durante o exemplo.
        HashMapGenerico<String, Integer> notas = new HashMapGenerico<>(4);
        notas.inserir("Ana", 9);
        notas.inserir("Bruno", 7);
        notas.inserir("Carlos", 8);
        notas.inserir("Ana", 10);

        System.out.println(notas);
        System.out.println("Nota de Ana: " + notas.buscar("Ana"));
        System.out.println("Remove Bruno: " + notas.remover("Bruno"));
        System.out.println(notas);
    }
}
