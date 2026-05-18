package Unidade2.P04FilasPrioridade;

/**
 * Fila de prioridade nao ordenada. A insercao e barata, mas a remocao precisa
 * procurar a maior prioridade em toda a estrutura.
 */
public class FilaPrioridadeNaoOrdenada<T extends Comparable<T>> {

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
    // Operacoes da fila
    // =========================

    public void enfileirar(T elemento) {
        Nodo novo = new Nodo(elemento);

        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.proximo = novo;
            novo.anterior = fim;
        }
        fim = novo;
        nElementos++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }

        Nodo maior = encontrarMaiorPrioridade();
        removerNodo(maior);
        return maior.elemento;
    }

    public T consultarFrente() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }
        return encontrarMaiorPrioridade().elemento;
    }

    private Nodo encontrarMaiorPrioridade() {
        Nodo maior = inicio;
        Nodo atual = inicio.proximo;

        while (atual != null) {
            if (atual.elemento.compareTo(maior.elemento) > 0) {
                maior = atual;
            }
            atual = atual.proximo;
        }
        return maior;
    }

    private void removerNodo(Nodo nodo) {
        if (nodo.anterior != null) {
            nodo.anterior.proximo = nodo.proximo;
        } else {
            inicio = nodo.proximo;
        }

        if (nodo.proximo != null) {
            nodo.proximo.anterior = nodo.anterior;
        } else {
            fim = nodo.anterior;
        }
        nElementos--;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Entrada -> ");
        Nodo atual = inicio;
        while (atual != null) {
            sb.append("[").append(atual.elemento).append("]");
            if (atual.proximo != null) {
                sb.append(" - ");
            }
            atual = atual.proximo;
        }
        sb.append(" <- Fim");
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: FilaPrioridadeNaoOrdenada ===");

        // Inserimos rapido; a prioridade so e procurada no momento da saida.
        FilaPrioridadeNaoOrdenada<Integer> fila = new FilaPrioridadeNaoOrdenada<>();
        fila.enfileirar(2);
        fila.enfileirar(5);
        fila.enfileirar(1);

        System.out.println("Fila interna: " + fila);
        System.out.println("Maior prioridade removida: " + fila.desenfileirar());
        System.out.println("Estado final: " + fila);
    }
}

