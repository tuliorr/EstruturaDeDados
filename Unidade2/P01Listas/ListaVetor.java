package Unidade2.P01Listas;

/**
 * Lista sequencial estatica de inteiros. A intuicao e guardar os elementos em um
 * vetor e usar nElementos para separar a parte ocupada da parte livre.
 */
public class ListaVetor {

    // =========================
    // Atributos
    // =========================

    private int[] vetor;
    private int nElementos;
    private int capacidade;

    // =========================
    // Construtor
    // =========================

    public ListaVetor(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.vetor = new int[capacidade];
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

    public void insereInicio(int elemento) {
        inserePosicao(elemento, 0);
    }

    public void insereFinal(int elemento) {
        inserePosicao(elemento, nElementos);
    }

    /**
     * Insere em uma posicao logica. Como o vetor e contiguo, abrimos espaco
     * deslocando para a direita os elementos posteriores.
     */
    public void inserePosicao(int elemento, int posicao) {
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

    public void insereOrdenado(int elemento) {
        int posicao = 0;
        while (posicao < nElementos && vetor[posicao] < elemento) {
            posicao++;
        }
        inserePosicao(elemento, posicao);
    }

    // =========================
    // Remocao
    // =========================

    public int removeInicio() {
        return removePosicao(0);
    }

    public int removeFinal() {
        return removePosicao(nElementos - 1);
    }

    /**
     * Remove uma posicao logica. Os elementos da direita andam uma casa para a
     * esquerda para manter o vetor sem buracos.
     */
    public int removePosicao(int posicao) {
        validarPosicaoOcupada(posicao);

        int removido = vetor[posicao];
        for (int i = posicao; i < nElementos - 1; i++) {
            vetor[i] = vetor[i + 1];
        }
        nElementos--;
        return removido;
    }

    public void limpar() {
        nElementos = 0;
    }

    // =========================
    // Busca e consulta
    // =========================

    public int busca(int elemento) {
        for (int i = 0; i < nElementos; i++) {
            if (vetor[i] == elemento) {
                return i;
            }
        }
        return -1;
    }

    public int obtem(int posicao) {
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
        if (estaVazia()) {
            return "[]";
        }

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
        System.out.println("=== Teste: ListaVetor ===");

        // O exemplo mistura insercoes para mostrar o custo dos deslocamentos.
        ListaVetor lista = new ListaVetor(8);
        lista.insereFinal(20);
        lista.insereFinal(40);
        lista.insereInicio(10);
        lista.inserePosicao(30, 2);
        System.out.println("Lista apos insercoes: " + lista);

        // A insercao ordenada encontra a posicao antes de reutilizar a insercao geral.
        lista.insereOrdenado(25);
        System.out.println("Depois de inserir 25 ordenado: " + lista);

        System.out.println("Indice do 30: " + lista.busca(30));
        System.out.println("Removido da posicao 2: " + lista.removePosicao(2));
        System.out.println("Estado final: " + lista);
    }
}

