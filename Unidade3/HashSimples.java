package Unidade3;

/**
 * Tabela hash simples para inteiros, sem tratamento real de colisao. Ela serve
 * para enxergar a ideia central: transformar uma chave em indice de vetor.
 */
public class HashSimples {

    // =========================
    // Atributos
    // =========================

    private Integer[] tabela;
    private int capacidade;
    private int nElementos;

    // =========================
    // Construtor
    // =========================

    public HashSimples(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.tabela = new Integer[capacidade];
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

    private int hash(int chave) {
        return Math.floorMod(chave, capacidade);
    }

    // =========================
    // Insercao
    // =========================

    /**
     * Insere diretamente no indice calculado. Se houver colisao, este exemplo
     * substitui o valor antigo para deixar o problema visivel.
     */
    public void inserir(int chave) {
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

    public boolean remover(int chave) {
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

    public boolean buscar(int chave) {
        int indice = hash(chave);
        return tabela[indice] != null && tabela[indice].equals(chave);
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tabela hash simples\n");
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
        System.out.println("=== Teste: HashSimples ===");

        // As chaves 12 e 22 colidem quando a capacidade e 10.
        HashSimples hash = new HashSimples(10);
        hash.inserir(12);
        hash.inserir(5);
        hash.inserir(22);

        System.out.println(hash);
        System.out.println("Busca 22: " + hash.buscar(22));
        System.out.println("Remove 5: " + hash.remover(5));
        System.out.println(hash);
    }
}
