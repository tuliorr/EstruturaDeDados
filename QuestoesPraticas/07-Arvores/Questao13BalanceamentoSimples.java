/**
 * Questao 13 - Verificar Balanceamento Simples.
 *
 * Verifica se, para cada no, a diferenca entre as alturas das subarvores e no
 * maximo 1.
 */
public class Questao13BalanceamentoSimples {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    public static boolean estaBalanceada(Nodo nodo) {
        if (nodo == null) {
            return true;
        }

        int alturaEsquerda = altura(nodo.esquerda);
        int alturaDireita = altura(nodo.direita);
        int diferenca = Math.abs(alturaEsquerda - alturaDireita);

        if (diferenca > 1) {
            return false;
        }

        boolean esquerdaBalanceada = estaBalanceada(nodo.esquerda);
        boolean direitaBalanceada = estaBalanceada(nodo.direita);

        return esquerdaBalanceada && direitaBalanceada;
    }

    private static int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = altura(nodo.esquerda);
        int alturaDireita = altura(nodo.direita);

        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(10);
        raiz.esquerda = new Nodo(5);
        raiz.direita = new Nodo(20);
        raiz.esquerda.esquerda = new Nodo(3);

        System.out.println("Balanceada: " + estaBalanceada(raiz));
        System.out.println("Resultado esperado: true");
    }
}
