/**
 * Questao 03 - Somar Valores Pares.
 *
 * Soma todos os valores pares armazenados em uma arvore binaria de inteiros.
 */
public class Questao03SomarPares {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    public static int somarPares(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int somaAtual = 0;
        if (nodo.valor % 2 == 0) {
            somaAtual = nodo.valor;
        }

        int somaEsquerda = somarPares(nodo.esquerda);
        int somaDireita = somarPares(nodo.direita);

        return somaAtual + somaEsquerda + somaDireita;
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(8);
        raiz.esquerda = new Nodo(3);
        raiz.direita = new Nodo(10);
        raiz.esquerda.esquerda = new Nodo(1);
        raiz.direita.esquerda = new Nodo(6);
        raiz.direita.direita = new Nodo(14);

        System.out.println("Soma dos pares: " + somarPares(raiz));
        System.out.println("Resultado esperado: 38");
    }
}
