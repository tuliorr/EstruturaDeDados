package Unidade2.P01Listas;

/**
 * Versao generica da lista sequencial estatica. O tipo T precisa ser
 * comparavel apenas para permitir a insercao ordenada.
 */
public class ListaVetorGenerica<T extends Comparable<T>> {

    // =========================
    // Atributos
    // =========================

    private T[] vetor;
    private int nElementos;
    private int capacidade;

    // =========================
    // Construtor
    // =========================

    @SuppressWarnings("unchecked")
    public ListaVetorGenerica(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.vetor = (T[]) new Comparable<?>[capacidade];
        this.nElementos = 0;
    }

    // =========================
    // Metodos basicos
    // =========================

    public boolean estaVazia() {
        return nElementos == 0;
    }

    public boolean estaCheia() {
        return nElementos == capacidade;
    }

    public int tamanho() {
        return nElementos;
    }

    // =========================
    // Insercao
    // =========================

    public void insereInicio(T elemento) {
        inserePosicao(elemento, 0);
    }

    public void insereFinal(T elemento) {
        inserePosicao(elemento, nElementos);
    }

    public void inserePosicao(T elemento, int posicao) {
        validarPosicaoInsercao(posicao);
        if (estaCheia()) {
            throw new IllegalStateException("A lista esta cheia.");
        }

        for (int i = nElementos; i > posicao; i--) {
            vetor[i] = vetor[i - 1];
        }
        vetor[posicao] = elemento;
        nElementos++;
    }

    public void insereOrdenado(T elemento) {
        int posicao = 0;
        while (posicao < nElementos && vetor[posicao].compareTo(elemento) < 0) {
            posicao++;
        }
        inserePosicao(elemento, posicao);
    }

    // =========================
    // Remocao
    // =========================

    public T removeInicio() {
        return removePosicao(0);
    }

    public T removeFinal() {
        return removePosicao(nElementos - 1);
    }

    public T removePosicao(int posicao) {
        validarPosicaoOcupada(posicao);

        T removido = vetor[posicao];
        for (int i = posicao; i < nElementos - 1; i++) {
            vetor[i] = vetor[i + 1];
        }
        nElementos--;
        vetor[nElementos] = null;
        return removido;
    }

    public void limpar() {
        for (int i = 0; i < nElementos; i++) {
            vetor[i] = null;
        }
        nElementos = 0;
    }

    // =========================
    // Busca e consulta
    // =========================

    public int busca(T elemento) {
        for (int i = 0; i < nElementos; i++) {
            if (vetor[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public T obtem(int posicao) {
        validarPosicaoOcupada(posicao);
        return vetor[posicao];
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
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nElementos; i++) {
            sb.append(vetor[i]);
            if (i < nElementos - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: ListaVetorGenerica ===");

        // Strings permitem mostrar que a lista nao depende mais apenas de int.
        ListaVetorGenerica<String> nomes = new ListaVetorGenerica<>(6);
        nomes.insereFinal("Bruno");
        nomes.insereInicio("Ana");
        nomes.insereFinal("Daniela");
        nomes.insereOrdenado("Carlos");

        System.out.println("Lista de nomes: " + nomes);
        System.out.println("Indice de Carlos: " + nomes.busca("Carlos"));
        System.out.println("Removido do inicio: " + nomes.removeInicio());
        System.out.println("Estado final: " + nomes);
    }
}

