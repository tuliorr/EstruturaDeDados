package Unidade2.P04FilasPrioridade;

/**
 * Fila de prioridade ordenada. Pagamos o custo na insercao para manter a maior
 * prioridade sempre no inicio.
 */
public class FilaPrioridadeOrdenada<T extends Comparable<T>> {

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
    // Operacoes da fila
    // =========================

    public void enfileirar(T elemento) {
        Nodo novo = new Nodo(elemento);

        if (inicio == null || elemento.compareTo(inicio.elemento) > 0) {
            novo.proximo = inicio;
            inicio = novo;
        } else {
            Nodo atual = inicio;
            while (atual.proximo != null && atual.proximo.elemento.compareTo(elemento) >= 0) {
                atual = atual.proximo;
            }
            novo.proximo = atual.proximo;
            atual.proximo = novo;
        }
        nElementos++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }

        T removido = inicio.elemento;
        inicio = inicio.proximo;
        nElementos--;
        return removido;
    }

    public T consultarFrente() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }
        return inicio.elemento;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Maior prioridade -> ");
        Nodo atual = inicio;
        while (atual != null) {
            sb.append("[").append(atual.elemento).append("]");
            if (atual.proximo != null) {
                sb.append(" - ");
            }
            atual = atual.proximo;
        }
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: FilaPrioridadeOrdenada ===");

        // A estrutura ja fica organizada no momento da insercao.
        FilaPrioridadeOrdenada<Integer> fila = new FilaPrioridadeOrdenada<>();
        fila.enfileirar(2);
        fila.enfileirar(5);
        fila.enfileirar(1);

        System.out.println("Fila ordenada: " + fila);
        System.out.println("Saiu: " + fila.desenfileirar());
        System.out.println("Estado final: " + fila);
    }
}

