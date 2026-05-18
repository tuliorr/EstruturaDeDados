package Unidade2.P01Listas;

/**
 * Lista simplesmente encadeada generica. A comparacao entre elementos aparece
 * apenas na insercao ordenada; as ligacoes continuam iguais para qualquer tipo.
 */
public class ListaSimplesmenteEncadeadaGenerica<T extends Comparable<T>> {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        T elemento;
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
        inicio = novo;

        if (fim == null) {
            fim = novo;
        }
        nElementos++;
    }

    public void insereFinal(T elemento) {
        Nodo novo = new Nodo(elemento);

        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.proximo = novo;
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

        Nodo anterior = nodoDaPosicao(posicao - 1);
        Nodo novo = new Nodo(elemento);
        novo.proximo = anterior.proximo;
        anterior.proximo = novo;
        nElementos++;
    }

    public void insereOrdenado(T elemento) {
        if (estaVazia() || elemento.compareTo(inicio.elemento) <= 0) {
            insereInicio(elemento);
            return;
        }

        Nodo atual = inicio;
        while (atual.proximo != null && atual.proximo.elemento.compareTo(elemento) < 0) {
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

    public T removeInicio() {
        if (estaVazia()) {
            throw new IllegalStateException("A lista esta vazia.");
        }

        T removido = inicio.elemento;
        inicio = inicio.proximo;
        nElementos--;

        if (nElementos == 0) {
            fim = null;
        }
        return removido;
    }

    public T removeFinal() {
        return removePosicao(nElementos - 1);
    }

    public T removePosicao(int posicao) {
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
        System.out.println("=== Teste: ListaSimplesmenteEncadeadaGenerica ===");

        // A insercao ordenada usa compareTo para manter os nomes em ordem crescente.
        ListaSimplesmenteEncadeadaGenerica<String> lista = new ListaSimplesmenteEncadeadaGenerica<>();
        lista.insereOrdenado("Carlos");
        lista.insereOrdenado("Ana");
        lista.insereOrdenado("Bruno");

        System.out.println("Lista ordenada: " + lista);
        System.out.println("Indice de Bruno: " + lista.busca("Bruno"));
        System.out.println("Removido do inicio: " + lista.removeInicio());
        System.out.println("Estado final: " + lista);
    }
}

