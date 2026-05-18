/**
 * Questao 02 - Contar Nos com Apenas Um Filho.
 *
 * Conta quantos nos possuem exatamente um filho.
 */
public class Questao02ContarNosComUmFilho {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    public static int contarNosComUmFilho(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        boolean temApenasFilhoEsquerdo = nodo.esquerda != null && nodo.direita == null;
        boolean temApenasFilhoDireito = nodo.esquerda == null && nodo.direita != null;

        int quantidadeAtual = 0;
        if (temApenasFilhoEsquerdo || temApenasFilhoDireito) {
            quantidadeAtual = 1;
        }

        int quantidadeEsquerda = contarNosComUmFilho(nodo.esquerda);
        int quantidadeDireita = contarNosComUmFilho(nodo.direita);

        return quantidadeAtual + quantidadeEsquerda + quantidadeDireita;
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(10);
        raiz.esquerda = new Nodo(5);
        raiz.direita = new Nodo(20);
        raiz.esquerda.esquerda = new Nodo(3);
        raiz.direita.direita = new Nodo(30);

        System.out.println("Nos com apenas um filho: " + contarNosComUmFilho(raiz));
        System.out.println("Resultado esperado: 2");
    }
}
