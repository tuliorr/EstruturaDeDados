package Unidade2.P01Listas;

/**
 * Lista duplamente encadeada generica. Cada nodo conhece o anterior e o proximo,
 * o que torna a navegacao e a remocao nas extremidades mais diretas.
 */
public class ListaDuplamenteEncadeada<T extends Comparable<T>> {

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
        novo.proximo = inicio;

        if (inicio != null) {
            inicio.anterior = novo;
        } else {
            fim = novo;
        }
        inicio = novo;
        nElementos++;
    }

    public void insereFinal(T elemento) {
        Nodo novo = new Nodo(elemento);
        novo.anterior = fim;

        if (fim != null) {
            fim.proximo = novo;
        } else {
            inicio = novo;
        }
        fim = novo;
        nElementos++;
    }

    public void inserePosicao(T elemento, int posicao) {
        validarPosicaoInsercao(posicao);

        if (posicao == 0) {
            insereInicio(elemento);
            return;
        }
        if (posicao == nElementos) {
            insereFinal(elemento);
            return;
        }

        Nodo atual = nodoDaPosicao(posicao);
        Nodo novo = new Nodo(elemento);
        novo.anterior = atual.anterior;
        novo.proximo = atual;
        atual.anterior.proximo = novo;
        atual.anterior = novo;
        nElementos++;
    }

    public void insereOrdenado(T elemento) {
        int posicao = 0;
        Nodo atual = inicio;
        while (atual != null && atual.elemento.compareTo(elemento) < 0) {
            atual = atual.proximo;
            posicao++;
        }
        inserePosicao(elemento, posicao);
    }

    // =========================
    // Remocao
    // =========================

    public T removeInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("A lista esta vazia.");
        }

        T removido = inicio.elemento;
        inicio = inicio.proximo;
        if (inicio != null) {
            inicio.anterior = null;
        } else {
            fim = null;
        }
        nElementos--;
        return removido;
    }

    public T removeFinal() {
        if (estaVazia()) {
            throw new IllegalStateException("A lista esta vazia.");
        }

        T removido = fim.elemento;
        fim = fim.anterior;
        if (fim != null) {
            fim.proximo = null;
        } else {
            inicio = null;
        }
        nElementos--;
        return removido;
    }

    public T removePosicao(int posicao) {
        validarPosicaoOcupada(posicao);

        if (posicao == 0) {
            return removeInicio();
        }
        if (posicao == nElementos - 1) {
            return removeFinal();
        }

        Nodo removido = nodoDaPosicao(posicao);
        removido.anterior.proximo = removido.proximo;
        removido.proximo.anterior = removido.anterior;
        nElementos--;
        return removido.elemento;
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
        int posicao = 0;

        while (atual != null) {
            if (atual.elemento.equals(elemento)) {
                return posicao;
            }
            atual = atual.proximo;
            posicao++;
        }
        return -1;
    }

    public T obtem(int posicao) {
        validarPosicaoOcupada(posicao);
        return nodoDaPosicao(posicao).elemento;
    }

    /**
     * Usa a extremidade mais proxima para reduzir a quantidade media de passos.
     */
    private Nodo nodoDaPosicao(int posicao) {
        if (posicao < nElementos / 2) {
            Nodo atual = inicio;
            for (int i = 0; i < posicao; i++) {
                atual = atual.proximo;
            }
            return atual;
        }

        Nodo atual = fim;
        for (int i = nElementos - 1; i > posicao; i--) {
            atual = atual.anterior;
        }
        return atual;
    }

    private void validarPosicaoInsercao(int posicao) {
        if (posicao < 0 || posicao > nElementos) {
            throw new IndexOutOfBoundsException("Posicao invalida para insercao.");
        }
    }

    private void validarPosicaoOcupada(int posicao) {
        if (posicao < 0 || posicao >= nElementos) {
            throw new IndexOutOfBoundsException("Posicao invalida.");
        }
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("null <- ");
        Nodo atual = inicio;

        while (atual != null) {
            sb.append(atual.elemento);
            sb.append(atual.proximo != null ? " <-> " : " -> null");
            atual = atual.proximo;
        }
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: ListaDuplamenteEncadeada ===");

        // O exemplo destaca insercao e remocao nas duas extremidades.
        ListaDuplamenteEncadeada<Integer> lista = new ListaDuplamenteEncadeada<>();
        lista.insereFinal(20);
        lista.insereInicio(10);
        lista.insereFinal(40);
        lista.inserePosicao(30, 2);

        System.out.println("Lista apos insercoes: " + lista);
        System.out.println("Removido do inicio: " + lista.removeInicio());
        System.out.println("Removido do final: " + lista.removeFinal());
        System.out.println("Estado final: " + lista);
    }
}

