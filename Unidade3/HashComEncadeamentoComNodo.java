package Unidade3;

/**
 * Tabela hash com encadeamento externo usando apenas vetor de nodos.
 *
 * Esta versao implementa a mesma ideia de HashComEncadeamento, mas sem criar
 * uma classe ListaEncadeada para representar cada balde.
 */
public class HashComEncadeamentoComNodo<T> {

    // =========================
    // Nodo usado nos baldes
    // =========================

    private static class Nodo<T> {
        T valor;
        Nodo<T> proximo;

        Nodo(T valor) {
            this.valor = valor;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Nodo<T>[] tabela;
    private int capacidade;
    private int nElementos;

    // =========================
    // Construtor
    // =========================

    @SuppressWarnings("unchecked")
    public HashComEncadeamentoComNodo(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.tabela = (Nodo<T>[]) new Nodo<?>[capacidade];
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

    public double fatorDeCarga() {
        return (double) nElementos / capacidade;
    }

    // =========================
    // Funcao de hashing
    // =========================

    private int hash(T chave) {
        if (chave == null) {
            throw new IllegalArgumentException("A chave nao pode ser null.");
        }
        return Math.floorMod(chave.hashCode(), capacidade);
    }

    // =========================
    // Insercao
    // =========================

    public void inserir(T chave) {
        if (buscar(chave)) {
            return;
        }

        int indice = hash(chave);
        Nodo<T> novo = new Nodo<>(chave);

        // Encadeamento externo: o vetor guarda a primeira referencia do balde.
        novo.proximo = tabela[indice];
        tabela[indice] = novo;
        nElementos++;
    }

    // =========================
    // Remocao
    // =========================

    public boolean remover(T chave) {
        int indice = hash(chave);
        Nodo<T> atual = tabela[indice];
        Nodo<T> anterior = null;

        while (atual != null) {
            if (atual.valor.equals(chave)) {
                if (anterior == null) {
                    tabela[indice] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                nElementos--;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean buscar(T chave) {
        Nodo<T> atual = tabela[hash(chave)];
        while (atual != null) {
            if (atual.valor.equals(chave)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hash com encadeamento usando Nodo\n");
        for (int i = 0; i < capacidade; i++) {
            sb.append(i).append(": ");
            Nodo<T> atual = tabela[i];
            if (atual == null) {
                sb.append("[vazio]");
            }
            while (atual != null) {
                sb.append(atual.valor).append(" -> ");
                atual = atual.proximo;
            }
            if (tabela[i] != null) {
                sb.append("null");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: HashComEncadeamentoComNodo ===");

        HashComEncadeamentoComNodo<Integer> hash = new HashComEncadeamentoComNodo<>(10);
        hash.inserir(7);
        hash.inserir(17);
        hash.inserir(27);
        hash.inserir(3);

        System.out.println(hash);
        System.out.println("Busca 17: " + hash.buscar(17));
        System.out.println("Fator de carga: " + hash.fatorDeCarga());
        System.out.println("Remove 17: " + hash.remover(17));
        System.out.println(hash);
    }
}
