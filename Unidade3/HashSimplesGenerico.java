package Unidade3;

/**
 * Versao generica da tabela hash simples. Usa hashCode do objeto para calcular
 * o indice, mas ainda deixa colisao substituir o valor antigo.
 */
public class HashSimplesGenerico<T> {

    // =========================
    // Atributos
    // =========================

    private Object[] tabela;
    private int capacidade;
    private int nElementos;

    // =========================
    // Construtor
    // =========================

    public HashSimplesGenerico(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.tabela = new Object[capacidade];
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
        int indice = hash(chave);
        if (tabela[indice] == null) {
            nElementos++;
        } else if (!tabela[indice].equals(chave)) {
            System.out.println("Colisao no indice " + indice + ": " + tabela[indice] + " substituido por " + chave);
        }
        tabela[indice] = chave;
    }

    // =========================
    // Remocao
    // =========================

    public boolean remover(T chave) {
        int indice = hash(chave);
        if (tabela[indice] != null && tabela[indice].equals(chave)) {
            tabela[indice] = null;
            nElementos--;
            return true;
        }
        return false;
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean buscar(T chave) {
        int indice = hash(chave);
        return tabela[indice] != null && tabela[indice].equals(chave);
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tabela hash generica simples\n");
        for (int i = 0; i < capacidade; i++) {
            sb.append(i).append(": ");
            sb.append(tabela[i] == null ? "[vazio]" : tabela[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: HashSimplesGenerico ===");

        // Strings mostram que a funcao hash pode vir do proprio objeto.
        HashSimplesGenerico<String> hash = new HashSimplesGenerico<>(8);
        hash.inserir("Ana");
        hash.inserir("Bruno");
        hash.inserir("Carlos");

        System.out.println(hash);
        System.out.println("Busca Bruno: " + hash.buscar("Bruno"));
        System.out.println("Remove Ana: " + hash.remover("Ana"));
        System.out.println(hash);
    }
}
