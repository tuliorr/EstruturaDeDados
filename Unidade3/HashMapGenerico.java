package Unidade3;

/**
 * Mapa hash generico com encadeamento e redimensionamento. Cada entrada guarda
 * chave e valor, permitindo recuperar rapidamente o valor a partir da chave.
 */
public class HashMapGenerico<K, V> {

    // =========================
    // Classe interna do no
    // =========================

    private static class No<K, V> {
        K chave;
        V valor;
        No<K, V> proximo;

        No(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Object[] tabela;
    private int capacidade;
    private int nElementos;
    private static final double FATOR_CARGA_MAXIMO = 0.75;

    // =========================
    // Construtor
    // =========================

    public HashMapGenerico(int capacidadeInicial) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidadeInicial;
        this.tabela = new Object[capacidadeInicial];
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
     * criamos um novo no no inicio da lista daquele indice.
     */
    public void inserir(K chave, V valor) {
        if ((double) (nElementos + 1) / capacidade > FATOR_CARGA_MAXIMO) {
            redimensionar();
        }

        int indice = hash(chave);
        No<K, V> atual = no(indice);
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                atual.valor = valor;
                return;
            }
            atual = atual.proximo;
        }

        No<K, V> novo = new No<>(chave, valor);
        novo.proximo = no(indice);
        tabela[indice] = novo;
        nElementos++;
    }

    /**
     * Ao crescer a tabela, todos os indices precisam ser recalculados porque o
     * modulo passa a usar uma nova capacidade.
     */
    private void redimensionar() {
        Object[] tabelaAntiga = tabela;
        int capacidadeAntiga = capacidade;

        capacidade *= 2;
        tabela = new Object[capacidade];

        for (int i = 0; i < capacidadeAntiga; i++) {
            No<K, V> atual = no(tabelaAntiga[i]);
            while (atual != null) {
                No<K, V> proximo = atual.proximo;
                int novoIndice = hash(atual.chave);
                atual.proximo = no(novoIndice);
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
        No<K, V> atual = no(indice);
        No<K, V> anterior = null;

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
        No<K, V> atual = no(hash(chave));
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                return atual.valor;
            }
            atual = atual.proximo;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private No<K, V> no(int indice) {
        return (No<K, V>) tabela[indice];
    }

    @SuppressWarnings("unchecked")
    private No<K, V> no(Object valor) {
        return (No<K, V>) valor;
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
            No<K, V> atual = no(i);
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
