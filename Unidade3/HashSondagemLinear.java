package Unidade3;

/**
 * Tabela hash com enderecamento aberto e sondagem linear. Quando uma posicao
 * esta ocupada, procuramos a proxima posicao livre em sequencia circular.
 */
public class HashSondagemLinear {

    // =========================
    // Atributos
    // =========================

    private Integer[] tabela;
    private boolean[] removido;
    private int capacidade;
    private int nElementos;

    // =========================
    // Construtor
    // =========================

    public HashSondagemLinear(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.tabela = new Integer[capacidade];
        this.removido = new boolean[capacidade];
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
     * Insere caminhando de uma posicao para a proxima ate encontrar espaco. Uma
     * posicao marcada como removida pode ser reaproveitada.
     */
    public boolean inserir(int chave) {
        if (nElementos == capacidade) {
            return false;
        }

        int indice = hash(chave);
        int primeiraLapide = -1;

        for (int tentativas = 0; tentativas < capacidade; tentativas++) {
            if (tabela[indice] != null && tabela[indice].equals(chave)) {
                return false;
            }

            if (tabela[indice] == null) {
                if (removido[indice]) {
                    if (primeiraLapide == -1) {
                        primeiraLapide = indice;
                    }
                } else {
                    int indiceInsercao = primeiraLapide != -1 ? primeiraLapide : indice;
                    tabela[indiceInsercao] = chave;
                    removido[indiceInsercao] = false;
                    nElementos++;
                    return true;
                }
            }

            indice = (indice + 1) % capacidade;
        }

        if (primeiraLapide != -1) {
            tabela[primeiraLapide] = chave;
            removido[primeiraLapide] = false;
            nElementos++;
            return true;
        }

        return false;
    }

    // =========================
    // Remocao
    // =========================

    /**
     * Remove usando uma lapide. A lapide preserva o caminho de busca para valores
     * que colidiram e foram posicionados mais adiante.
     */
    public boolean remover(int chave) {
        int indice = hash(chave);
        for (int tentativas = 0; tentativas < capacidade; tentativas++) {
            if (tabela[indice] == null && !removido[indice]) {
                return false;
            }
            if (tabela[indice] != null && tabela[indice].equals(chave)) {
                tabela[indice] = null;
                removido[indice] = true;
                nElementos--;
                return true;
            }
            indice = (indice + 1) % capacidade;
        }
        return false;
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean buscar(int chave) {
        int indice = hash(chave);
        for (int tentativas = 0; tentativas < capacidade; tentativas++) {
            if (tabela[indice] == null && !removido[indice]) {
                return false;
            }
            if (tabela[indice] != null && tabela[indice].equals(chave)) {
                return true;
            }
            indice = (indice + 1) % capacidade;
        }
        return false;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hash com sondagem linear\n");
        for (int i = 0; i < capacidade; i++) {
            sb.append(i).append(": ");
            if (tabela[i] != null) {
                sb.append(tabela[i]);
            } else if (removido[i]) {
                sb.append("[removido]");
            } else {
                sb.append("[vazio]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: HashSondagemLinear ===");

        // 5, 15 e 25 colidem em capacidade 10; a sondagem empurra os valores.
        HashSondagemLinear hash = new HashSondagemLinear(10);
        hash.inserir(5);
        hash.inserir(15);
        hash.inserir(25);
        System.out.println(hash);

        // Remover o 15 cria uma lapide para a busca do 25 continuar funcionando.
        hash.remover(15);
        System.out.println("Busca 25 depois da remocao: " + hash.buscar(25));
        System.out.println(hash);
    }
}
