package Unidade2.P03Filas;

/**
 * Fila estatica circular. O primeiro a entrar e o primeiro a sair, e os indices
 * inicio/fim giram no vetor para reaproveitar espacos livres.
 */
public class FilaEstatica<T> {

    // =========================
    // Atributos
    // =========================

    private Object[] itens;
    private int inicio;
    private int fim;
    private int nElementos;
    private int capacidade;

    // =========================
    // Construtor
    // =========================

    public FilaEstatica(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.itens = new Object[capacidade];
        this.inicio = 0;
        this.fim = 0;
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

        itens[fim] = elemento;
        fim = (fim + 1) % capacidade;
        nElementos++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }

        T removido = elementoNaPosicao(inicio);
        itens[inicio] = null;
        inicio = (inicio + 1) % capacidade;
        nElementos--;
        return removido;
    }

    public T consultarFrente() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }
        return elementoNaPosicao(inicio);
    }

    @SuppressWarnings("unchecked")
    private T elementoNaPosicao(int posicao) {
        return (T) itens[posicao];
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        if (estaVazia()) {
            return "Fila: vazia";
        }

        StringBuilder sb = new StringBuilder("Frente -> ");
        for (int i = 0; i < nElementos; i++) {
            int indice = (inicio + i) % capacidade;
            sb.append("[").append(itens[indice]).append("]");
            if (i < nElementos - 1) {
                sb.append(" - ");
            }
        }
        sb.append(" <- Fim");
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: FilaEstatica ===");

        // A fila circular reutiliza posicoes liberadas pelo desenfileiramento.
        FilaEstatica<String> fila = new FilaEstatica<>(4);
        fila.enfileirar("Ana");
        fila.enfileirar("Bruno");
        fila.enfileirar("Carlos");
        System.out.println(fila);

        System.out.println("Saiu: " + fila.desenfileirar());
        fila.enfileirar("Daniela");
        System.out.println("Estado final: " + fila);
    }
}

