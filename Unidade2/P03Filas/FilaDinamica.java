package Unidade2.P03Filas;

/**
 * Fila dinamica generica. Mantemos referencias para inicio e fim para enfileirar
 * e desenfileirar em tempo constante.
 */
public class FilaDinamica<T> {

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
    // Operacoes da fila
    // =========================

    public void enfileirar(T elemento) {
        Nodo novo = new Nodo(elemento);

        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.proximo = novo;
        }
        fim = novo;
        nElementos++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }

        T removido = inicio.elemento;
        inicio = inicio.proximo;
        nElementos--;

        if (nElementos == 0) {
            fim = null;
        }
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
        if (estaVazia()) {
            return "Fila: vazia";
        }

        StringBuilder sb = new StringBuilder("Frente -> ");
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
        System.out.println("=== Teste: FilaDinamica ===");

        // O primeiro elemento enfileirado permanece na frente ate ser removido.
        FilaDinamica<String> fila = new FilaDinamica<>();
        fila.enfileirar("Ana");
        fila.enfileirar("Bruno");
        fila.enfileirar("Carlos");

        System.out.println(fila);
        System.out.println("Saiu: " + fila.desenfileirar());
        System.out.println("Frente atual: " + fila.consultarFrente());
    }
}

