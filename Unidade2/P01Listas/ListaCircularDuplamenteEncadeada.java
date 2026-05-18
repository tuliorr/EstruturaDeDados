package Unidade2.P01Listas;

/**
 * Lista circular duplamente encadeada. O ultimo nodo aponta para o primeiro e o
 * primeiro aponta de volta para o ultimo, fechando o ciclo.
 */
public class ListaCircularDuplamenteEncadeada<T extends Comparable<T>> {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        T elemento;
        Nodo anterior;
        Nodo proximo;

        Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Nodo inicio;
    private Nodo fim;
    private int nElementos;

    // =========================
    // Metodos basicos
    // =========================

    public boolean estaVazia() {
        return nElementos == 0;
    }

    public int tamanho() {
        return nElementos;
    }

    // =========================
    // Insercao
    // =========================

    public void insereInicio(T elemento) {
        Nodo novo = new Nodo(elemento);

        if (estaVazia()) {
            inicio = novo;
            fim = novo;
            novo.proximo = novo;
            novo.anterior = novo;
        } else {
            novo.proximo = inicio;
            novo.anterior = fim;
            inicio.anterior = novo;
            fim.proximo = novo;
            inicio = novo;
        }
        nElementos++;
    }

    public void insereFinal(T elemento) {
        if (estaVazia()) {
            insereInicio(elemento);
            return;
        }

        Nodo novo = new Nodo(elemento);
        novo.proximo = inicio;
        novo.anterior = fim;
        fim.proximo = novo;
        inicio.anterior = novo;
        fim = novo;
        nElementos++;
    }

    public void insereOrdenado(T elemento) {
        if (estaVazia() || elemento.compareTo(inicio.elemento) <= 0) {
            insereInicio(elemento);
            return;
        }
        if (elemento.compareTo(fim.elemento) >= 0) {
            insereFinal(elemento);
            return;
        }

        Nodo atual = inicio.proximo;
        while (atual != inicio && atual.elemento.compareTo(elemento) < 0) {
            atual = atual.proximo;
        }

        Nodo novo = new Nodo(elemento);
        novo.anterior = atual.anterior;
        novo.proximo = atual;
        atual.anterior.proximo = novo;
        atual.anterior = novo;
        nElementos++;
    }

    // =========================
    // Remocao
    // =========================

    public T removeInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("A lista esta vazia.");
        }

        T removido = inicio.elemento;
        if (nElementos == 1) {
            inicio = null;
            fim = null;
        } else {
            inicio = inicio.proximo;
            inicio.anterior = fim;
            fim.proximo = inicio;
        }
        nElementos--;
        return removido;
    }

    public T removeFinal() {
        if (estaVazia()) {
            throw new IllegalStateException("A lista esta vazia.");
        }

        T removido = fim.elemento;
        if (nElementos == 1) {
            inicio = null;
            fim = null;
        } else {
            fim = fim.anterior;
            fim.proximo = inicio;
            inicio.anterior = fim;
        }
        nElementos--;
        return removido;
    }

    public void limpar() {
        inicio = null;
        fim = null;
        nElementos = 0;
    }

    // =========================
    // Busca e consulta
    // =========================

    public int busca(T elemento) {
        Nodo atual = inicio;
        for (int i = 0; i < nElementos; i++) {
            if (atual.elemento.equals(elemento)) {
                return i;
            }
            atual = atual.proximo;
        }
        return -1;
    }

    public T obtem(int posicao) {
        if (posicao < 0 || posicao >= nElementos) {
            throw new IndexOutOfBoundsException("Posicao invalida.");
        }

        Nodo atual = inicio;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }
        return atual.elemento;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        if (estaVazia()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Nodo atual = inicio;
        for (int i = 0; i < nElementos; i++) {
            sb.append(atual.elemento);
            if (i < nElementos - 1) {
                sb.append(" <-> ");
            }
            atual = atual.proximo;
        }
        sb.append("] (circular)");
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: ListaCircularDuplamenteEncadeada ===");

        // A exibicao mostra o ciclo sem entrar em loop infinito.
        ListaCircularDuplamenteEncadeada<Integer> lista = new ListaCircularDuplamenteEncadeada<>();
        lista.insereOrdenado(30);
        lista.insereOrdenado(10);
        lista.insereOrdenado(20);
        lista.insereFinal(40);

        System.out.println("Lista circular: " + lista);
        System.out.println("Elemento na posicao 2: " + lista.obtem(2));
        System.out.println("Removido do inicio: " + lista.removeInicio());
        System.out.println("Estado final: " + lista);
    }
}

