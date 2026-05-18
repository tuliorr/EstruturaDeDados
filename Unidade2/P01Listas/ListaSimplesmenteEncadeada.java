package Unidade2.P01Listas;

/**
 * Lista simplesmente encadeada de inteiros. Cada nodo sabe apenas quem vem
 * depois dele, por isso algumas operacoes precisam percorrer a lista.
 */
public class ListaSimplesmenteEncadeada {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        int elemento;
        Nodo proximo;

        Nodo(int elemento) {
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

    public void insereInicio(int elemento) {
        Nodo novo = new Nodo(elemento);
        novo.proximo = inicio;
        inicio = novo;

        if (fim == null) {
            fim = novo;
        }
        nElementos++;
    }

    public void insereFinal(int elemento) {
        Nodo novo = new Nodo(elemento);

        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.proximo = novo;
        }
        fim = novo;
        nElementos++;
    }

    public void inserePosicao(int elemento, int posicao) {
        validarPosicaoInsercao(posicao);

        if (posicao == 0) {
            insereInicio(elemento);
            return;
        }
        if (posicao == nElementos) {
            insereFinal(elemento);
            return;
        }

        Nodo anterior = nodoDaPosicao(posicao - 1);
        Nodo novo = new Nodo(elemento);
        novo.proximo = anterior.proximo;
        anterior.proximo = novo;
        nElementos++;
    }

    public void insereOrdenado(int elemento) {
        if (estaVazia() || elemento <= inicio.elemento) {
            insereInicio(elemento);
            return;
        }

        Nodo atual = inicio;
        while (atual.proximo != null && atual.proximo.elemento < elemento) {
            atual = atual.proximo;
        }

        Nodo novo = new Nodo(elemento);
        novo.proximo = atual.proximo;
        atual.proximo = novo;
        if (novo.proximo == null) {
            fim = novo;
        }
        nElementos++;
    }

    // =========================
    // Remocao
    // =========================

    public int removeInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("A lista esta vazia.");
        }

        int removido = inicio.elemento;
        inicio = inicio.proximo;
        nElementos--;

        if (nElementos == 0) {
            fim = null;
        }
        return removido;
    }

    public int removeFinal() {
        return removePosicao(nElementos - 1);
    }

    public int removePosicao(int posicao) {
        validarPosicaoOcupada(posicao);

        if (posicao == 0) {
            return removeInicio();
        }

        Nodo anterior = nodoDaPosicao(posicao - 1);
        Nodo removido = anterior.proximo;
        anterior.proximo = removido.proximo;

        if (removido == fim) {
            fim = anterior;
        }
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

    public int busca(int elemento) {
        Nodo atual = inicio;
        int posicao = 0;

        while (atual != null) {
            if (atual.elemento == elemento) {
                return posicao;
            }
            atual = atual.proximo;
            posicao++;
        }
        return -1;
    }

    public int obtem(int posicao) {
        validarPosicaoOcupada(posicao);
        return nodoDaPosicao(posicao).elemento;
    }

    private Nodo nodoDaPosicao(int posicao) {
        Nodo atual = inicio;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
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
        StringBuilder sb = new StringBuilder();
        Nodo atual = inicio;

        while (atual != null) {
            sb.append(atual.elemento).append(" -> ");
            atual = atual.proximo;
        }
        sb.append("null");
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: ListaSimplesmenteEncadeada ===");

        // A lista encadeada cresce criando nodos e ligando cada um ao proximo.
        ListaSimplesmenteEncadeada lista = new ListaSimplesmenteEncadeada();
        lista.insereFinal(20);
        lista.insereInicio(10);
        lista.insereFinal(40);
        lista.inserePosicao(30, 2);

        System.out.println("Lista apos insercoes: " + lista);
        System.out.println("Elemento na posicao 2: " + lista.obtem(2));
        System.out.println("Removido do final: " + lista.removeFinal());
        System.out.println("Estado final: " + lista);
    }
}

