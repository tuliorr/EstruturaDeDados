package Unidade2.P02Pilhas;

/**
 * Pilha estatica generica. A pilha usa a regra LIFO: o ultimo elemento
 * empilhado e sempre o primeiro a sair.
 *
 * @param <T> tipo dos elementos armazenados na pilha
 */
public class PilhaEstatica<T> {

    // =========================
    // Atributos
    // =========================

    private T[] itens;
    private int topo;
    private int capacidade;

    // =========================
    // Construtor
    // =========================

    @SuppressWarnings("unchecked")
    public PilhaEstatica(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.itens = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    // =========================
    // Metodos basicos
    // =========================

    public boolean estaVazia() {
        return topo == -1;
    }

    public boolean estaCheia() {
        return topo == capacidade - 1;
    }

    public int tamanho() {
        return topo + 1;
    }

    // =========================
    // Operacoes da pilha
    // =========================

    public void empilhar(T item) {
        if (estaCheia()) {
            throw new IllegalStateException("A pilha esta cheia.");
        }
        topo++;
        itens[topo] = item;
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha esta vazia.");
        }
        T item = itens[topo];
        itens[topo] = null;
        topo--;
        return item;
    }

    public T consultarTopo() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha esta vazia.");
        }
        return itens[topo];
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        if (estaVazia()) {
            return "Pilha: vazia";
        }

        StringBuilder sb = new StringBuilder("Topo -> ");
        for (int i = topo; i >= 0; i--) {
            sb.append("[").append(itens[i]).append("]");
            if (i > 0) {
                sb.append("\n        ");
            }
        }
        sb.append("\n        Base");
        return sb.toString();
    }

}

