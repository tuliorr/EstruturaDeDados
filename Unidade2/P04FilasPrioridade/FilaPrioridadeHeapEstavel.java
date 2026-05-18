package Unidade2.P04FilasPrioridade;

/**
 * Max-Heap estavel. Quando dois elementos tem a mesma prioridade, sai primeiro
 * aquele que chegou antes.
 */
public class FilaPrioridadeHeapEstavel<T extends Comparable<T>> {

    // =========================
    // Classe interna do item
    // =========================

    private class ItemEstavel implements Comparable<ItemEstavel> {
        T elemento;
        long ordemChegada;

        ItemEstavel(T elemento, long ordemChegada) {
            this.elemento = elemento;
            this.ordemChegada = ordemChegada;
        }

        @Override
        public int compareTo(ItemEstavel outro) {
            int prioridade = elemento.compareTo(outro.elemento);
            if (prioridade != 0) {
                return prioridade;
            }
            return Long.compare(outro.ordemChegada, ordemChegada);
        }

        @Override
        public String toString() {
            return elemento + "#" + ordemChegada;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Object[] heap;
    private int nElementos;
    private int capacidade;
    private long contadorSequencial;

    // =========================
    // Construtor
    // =========================

    public FilaPrioridadeHeapEstavel(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.heap = new Object[capacidade];
        this.nElementos = 0;
        this.contadorSequencial = 0;
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

    // =========================
    // Operacoes da fila
    // =========================

    public void enfileirar(T elemento) {
        if (nElementos == capacidade) {
            throw new IllegalStateException("A fila esta cheia.");
        }

        heap[nElementos] = new ItemEstavel(elemento, contadorSequencial);
        contadorSequencial++;
        subir(nElementos);
        nElementos++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }

        ItemEstavel removido = item(0);
        nElementos--;
        heap[0] = heap[nElementos];
        heap[nElementos] = null;
        descer(0);
        return removido.elemento;
    }

    public T consultarFrente() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }
        return item(0).elemento;
    }

    private void subir(int indice) {
        while (indice > 0) {
            int pai = (indice - 1) / 2;
            if (item(indice).compareTo(item(pai)) <= 0) {
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

            if (esquerdo < nElementos && item(esquerdo).compareTo(item(maior)) > 0) {
                maior = esquerdo;
            }
            if (direito < nElementos && item(direito).compareTo(item(maior)) > 0) {
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
    private ItemEstavel item(int indice) {
        return (ItemEstavel) heap[indice];
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Heap estavel: [");
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
        System.out.println("=== Teste: FilaPrioridadeHeapEstavel ===");

        // Os dois valores 5 tem mesma prioridade; o primeiro 5 deve sair antes.
        FilaPrioridadeHeapEstavel<Integer> fila = new FilaPrioridadeHeapEstavel<>(8);
        fila.enfileirar(5);
        fila.enfileirar(3);
        fila.enfileirar(5);
        fila.enfileirar(7);

        System.out.println(fila);
        System.out.println("Removido: " + fila.desenfileirar());
        System.out.println("Removido: " + fila.desenfileirar());
        System.out.println("Estado final: " + fila);
    }
}

