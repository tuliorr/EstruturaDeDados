/**
 * Questao 01 - Contar Nos com Dois Filhos.
 *
 * Conta quantos nos de uma arvore binaria possuem filho esquerdo e filho
 * direito.
 */
public class Questao01ContarNosComDoisFilhos {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    public static int contarNosComDoisFilhos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int quantidadeAtual = 0;
        if (nodo.esquerda != null && nodo.direita != null) {
            quantidadeAtual = 1;
        }

        int quantidadeEsquerda = contarNosComDoisFilhos(nodo.esquerda);
        int quantidadeDireita = contarNosComDoisFilhos(nodo.direita);

        return quantidadeAtual + quantidadeEsquerda + quantidadeDireita;
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(10);
        raiz.esquerda = new Nodo(5);
        raiz.direita = new Nodo(20);
        raiz.esquerda.esquerda = new Nodo(3);

        System.out.println("Nos com dois filhos: " + contarNosComDoisFilhos(raiz));
        System.out.println("Resultado esperado: 1");
    }
}
