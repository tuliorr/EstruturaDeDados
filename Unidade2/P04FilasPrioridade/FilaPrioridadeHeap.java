package Unidade2.P04FilasPrioridade;

/**
 * Fila de prioridade com Max-Heap. A maior prioridade fica na raiz do heap, e
 * cada insercao/remocao reorganiza apenas um caminho da arvore.
 */
public class FilaPrioridadeHeap<T extends Comparable<T>> {

    // =========================
    // Atributos
    // =========================

    private Object[] heap;
    private int nElementos;
    private int capacidade;

    // =========================
    // Construtor
    // =========================

    public FilaPrioridadeHeap(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.heap = new Object[capacidade];
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
    // Operacoes da fila
    // =========================

    public void enfileirar(T elemento) {
        if (estaCheia()) {
            throw new IllegalStateException("A fila esta cheia.");
        }

        heap[nElementos] = elemento;
        subir(nElementos);
        nElementos++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }

        T removido = elemento(0);
        nElementos--;
        heap[0] = heap[nElementos];
        heap[nElementos] = null;
        descer(0);
        return removido;
    }

    public T consultarFrente() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }
        return elemento(0);
    }

    private void subir(int indice) {
        while (indice > 0) {
            int pai = (indice - 1) / 2;
            if (elemento(indice).compareTo(elemento(pai)) <= 0) {
                break;
            }
            trocar(indice, pai);
            indice = pai;
        }
    }

    private void descer(int indice) {
        while (true) {
            int esquerdo = 2 * indice + 1;
            int direito = 2 * indice + 2;
            int maior = indice;

            if (esquerdo < nElementos && elemento(esquerdo).compareTo(elemento(maior)) > 0) {
                maior = esquerdo;
            }
            if (direito < nElementos && elemento(direito).compareTo(elemento(maior)) > 0) {
                maior = direito;
            }
            if (maior == indice) {
                return;
            }

            trocar(indice, maior);
            indice = maior;
        }
    }

    private void trocar(int i, int j) {
        Object auxiliar = heap[i];
        heap[i] = heap[j];
        heap[j] = auxiliar;
    }

    @SuppressWarnings("unchecked")
    private T elemento(int indice) {
        return (T) heap[indice];
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Heap: [");
        for (int i = 0; i < nElementos; i++) {
            sb.append(heap[i]);
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
        System.out.println("=== Teste: FilaPrioridadeHeap ===");

        // O maior valor sobe para a raiz, mas o vetor nao fica totalmente ordenado.
        FilaPrioridadeHeap<Integer> fila = new FilaPrioridadeHeap<>(8);
        fila.enfileirar(3);
        fila.enfileirar(8);
        fila.enfileirar(5);
        fila.enfileirar(1);

        System.out.println(fila);
        System.out.println("Maior prioridade: " + fila.consultarFrente());
        System.out.println("Removido: " + fila.desenfileirar());
        System.out.println("Estado final: " + fila);
    }
}

