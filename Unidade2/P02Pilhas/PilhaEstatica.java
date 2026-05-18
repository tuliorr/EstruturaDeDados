package Unidade2.P02Pilhas;

/**
 * Pilha estatica de caracteres. A pilha usa a regra LIFO: o ultimo elemento
 * empilhado e sempre o primeiro a sair.
 */
public class PilhaEstatica {

    // =========================
    // Atributos
    // =========================

    private char[] itens;
    private int topo;
    private int capacidade;

    // =========================
    // Construtor
    // =========================

    public PilhaEstatica(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.capacidade = capacidade;
        this.itens = new char[capacidade];
        this.topo = -1;
    }

    // =========================
    // Metodos basicos
    // =========================

    public boolean estaVazia() {
        return topo == -1;
    }

    public boolean estaCheia() {
        return topo == capacidade - 1;
    }

    public int tamanho() {
        return topo + 1;
    }

    // =========================
    // Operacoes da pilha
    // =========================

    public void empilhar(char item) {
        if (estaCheia()) {
            throw new IllegalStateException("A pilha esta cheia.");
        }
        topo++;
        itens[topo] = item;
    }

    public char desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha esta vazia.");
        }
        char item = itens[topo];
        topo--;
        return item;
    }

    public char consultarTopo() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha esta vazia.");
        }
        return itens[topo];
    }

    // =========================
    // Exemplo de uso
    // =========================

    /**
     * Usa a pilha para verificar se cada simbolo de fechamento encontra a abertura
     * correspondente no topo.
     */
    public static boolean verificaExpressao(String expressao) {
        if (expressao == null) {
            throw new IllegalArgumentException("A expressao nao pode ser null.");
        }
        if (expressao.isEmpty()) {
            return true;
        }

        PilhaEstatica pilha = new PilhaEstatica(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);

            if (caractere == '(' || caractere == '[' || caractere == '{') {
                pilha.empilhar(caractere);
            } else if (caractere == ')' || caractere == ']' || caractere == '}') {
                if (pilha.estaVazia()) {
                    return false;
                }

                char abertura = pilha.desempilhar();
                if (!combinam(abertura, caractere)) {
                    return false;
                }
            }
        }

        return pilha.estaVazia();
    }

    private static boolean combinam(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')')
                || (abertura == '[' && fechamento == ']')
                || (abertura == '{' && fechamento == '}');
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
        for (int i = topo; i >= 0; i--) {
            sb.append("[").append(itens[i]).append("]");
            if (i > 0) {
                sb.append("\n        ");
            }
        }
        sb.append("\n        Base");
        return sb.toString();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: PilhaEstatica ===");

        // Empilhamos letras para visualizar que a ultima vira o topo.
        PilhaEstatica pilha = new PilhaEstatica(5);
        pilha.empilhar('A');
        pilha.empilhar('B');
        pilha.empilhar('C');
        System.out.println(pilha);

        System.out.println("Desempilhado: " + pilha.desempilhar());
        System.out.println("Topo atual: " + pilha.consultarTopo());

        // O mesmo comportamento LIFO resolve o pareamento de simbolos.
        String expressao = "{[(2+3) * 4]}";
        System.out.println("Expressao balanceada? " + verificaExpressao(expressao));
    }
}

