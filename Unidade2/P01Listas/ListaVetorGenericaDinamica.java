package Unidade2.P01Listas;

/**
 * Lista sequencial generica com crescimento automatico. Ela mostra como uma
 * estrutura baseada em vetor pode parecer ilimitada para quem a usa.
 */
public class ListaVetorGenericaDinamica<T extends Comparable<T>> {

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
    public ListaVetorGenericaDinamica(int capacidadeInicial) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidadeInicial;
        this.vetor = (T[]) new Comparable<?>[capacidadeInicial];
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

    public int capacidade() {
        return capacidade;
    }

    // =========================
    // Redimensionamento
    // =========================

    @SuppressWarnings("unchecked")
    private void garantirCapacidade() {
        if (nElementos < capacidade) {
            return;
        }

        capacidade *= 2;
        T[] novoVetor = (T[]) new Comparable<?>[capacidade];
        for (int i = 0; i < nElementos; i++) {
            novoVetor[i] = vetor[i];
        }
        vetor = novoVetor;
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
        garantirCapacidade();

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
        System.out.println("=== Teste: ListaVetorGenericaDinamica ===");

        // A capacidade inicial pequena deixa o redimensionamento facil de perceber.
        ListaVetorGenericaDinamica<Integer> lista = new ListaVetorGenericaDinamica<>(2);
        lista.insereFinal(10);
        lista.insereFinal(30);
        lista.inserePosicao(20, 1);

        System.out.println("Lista apos crescer: " + lista);
        System.out.println("Tamanho: " + lista.tamanho());
        System.out.println("Capacidade atual: " + lista.capacidade());
    }
}

