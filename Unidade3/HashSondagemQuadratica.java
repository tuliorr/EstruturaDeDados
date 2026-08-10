package Unidade3;

/**
 * Tabela hash com enderecamento aberto e sondagem quadratica. Em cada tentativa,
 * somamos o quadrado da tentativa ao indice inicial.
 */
public class HashSondagemQuadratica {

    // =========================
    // Atributos
    // =========================

    private Integer[] tabela;
    private boolean[] removido;
    private int capacidade;
    private int nElementos;
    private static final double FATOR_CARGA_MAXIMO = 0.5;

    // =========================
    // Construtor
    // =========================

    public HashSondagemQuadratica(int capacidade) {
        if (capacidade < 2 || !ehPrimo(capacidade)) {
            throw new IllegalArgumentException("A capacidade deve ser um numero primo maior ou igual a 2.");
        }
        this.capacidade = capacidade;
        this.tabela = new Integer[capacidade];
        this.removido = new boolean[capacidade];
        this.nElementos = 0;
    }

    private boolean ehPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        for (int divisor = 2; (long) divisor * divisor <= numero; divisor++) {
            if (numero % divisor == 0) {
                return false;
            }
        }
        return true;
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

    /**
     * Calcula h(i) = (hash(chave) + i * i) % capacidade. O tipo long evita
     * overflow no calculo do quadrado.
     */
    private int indiceDaTentativa(int chave, int tentativa) {
        long deslocamento = (long) tentativa * tentativa;
        return (int) ((hash(chave) + deslocamento) % capacidade);
    }

    // =========================
    // Insercao
    // =========================

    /**
     * Insere sem ultrapassar fator de carga 0,5. Uma lapide pode ser reutilizada,
     * mas a sondagem continua ate garantir que a chave ainda nao existe.
     */
    public boolean inserir(int chave) {
        if ((double) (nElementos + 1) / capacidade > FATOR_CARGA_MAXIMO) {
            return false;
        }

        int primeiraLapide = -1;
        for (int tentativa = 0; tentativa <= capacidade / 2; tentativa++) {
            int indice = indiceDaTentativa(chave, tentativa);

            if (tabela[indice] != null) {
                if (tabela[indice].equals(chave)) {
                    return false;
                }
            } else if (removido[indice]) {
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
     * Remove usando uma lapide, preservando o caminho das buscas seguintes.
     */
    public boolean remover(int chave) {
        for (int tentativa = 0; tentativa <= capacidade / 2; tentativa++) {
            int indice = indiceDaTentativa(chave, tentativa);

            if (tabela[indice] == null && !removido[indice]) {
                return false;
            }
            if (tabela[indice] != null && tabela[indice].equals(chave)) {
                tabela[indice] = null;
                removido[indice] = true;
                nElementos--;
                return true;
            }
        }
        return false;
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean buscar(int chave) {
        for (int tentativa = 0; tentativa <= capacidade / 2; tentativa++) {
            int indice = indiceDaTentativa(chave, tentativa);

            if (tabela[indice] == null && !removido[indice]) {
                return false;
            }
            if (tabela[indice] != null && tabela[indice].equals(chave)) {
                return true;
            }
        }
        return false;
    }

    // =========================
    // Representacao
    // =========================

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hash com sondagem quadratica\n");
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
        System.out.println("=== Teste: HashSondagemQuadratica ===");

        // 5, 16 e 27 colidem na capacidade 11 e seguem deslocamentos quadrados.
        HashSondagemQuadratica hash = new HashSondagemQuadratica(11);
        hash.inserir(5);
        hash.inserir(16);
        hash.inserir(27);
        System.out.println(hash);

        // A lapide deixada pelo 16 nao interrompe a busca pelo 27.
        hash.remover(16);
        System.out.println("Busca 27 depois da remocao: " + hash.buscar(27));
        hash.inserir(38);
        System.out.println(hash);

        // Experimente trocar a capacidade e observe por que ela precisa ser prima.
    }
}
