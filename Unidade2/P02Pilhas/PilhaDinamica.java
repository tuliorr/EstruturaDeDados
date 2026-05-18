package Unidade2.P02Pilhas;

/**
 * Pilha dinamica generica. Em vez de um vetor fixo, cada elemento novo cria um
 * nodo que aponta para o antigo topo.
 */
public class PilhaDinamica<T> {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        T elemento;
        Nodo abaixo;

        Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Nodo topo;
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
    // Operacoes da pilha
    // =========================

    public void empilhar(T elemento) {
        Nodo novo = new Nodo(elemento);
        novo.abaixo = topo;
        topo = novo;
        nElementos++;
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha esta vazia.");
        }

        T removido = topo.elemento;
        topo = topo.abaixo;
        nElementos--;
        return removido;
    }

    public T consultarTopo() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha esta vazia.");
        }
        return topo.elemento;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        if (estaVazia()) {
            return "Pilha: vazia";
        }

        StringBuilder sb = new StringBuilder("Topo -> ");
        Nodo atual = topo;
        while (atual != null) {
            sb.append("[").append(atual.elemento).append("]");
            if (atual.abaixo != null) {
                sb.append("\n        ");
            }
            atual = atual.abaixo;
        }
        sb.append("\n        Base");
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: PilhaDinamica ===");

        // Cada empilhamento troca a referencia topo para o novo nodo.
        PilhaDinamica<String> pilha = new PilhaDinamica<>();
        pilha.empilhar("primeiro");
        pilha.empilhar("segundo");
        pilha.empilhar("terceiro");

        System.out.println(pilha);
        System.out.println("Desempilhado: " + pilha.desempilhar());
        System.out.println("Topo atual: " + pilha.consultarTopo());
    }
}

