/**
 * Questao 01 - Contar Nos com Dois Filhos.
 *
 * Conta quantos nodos de uma arvore binaria possuem filho esquerdo e filho
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

    private static class ArvoreBinaria {
        private Nodo raiz;

        Nodo inserirRaiz(int valor) {
            raiz = new Nodo(valor);
            return raiz;
        }

        Nodo inserirEsquerda(Nodo pai, int valor) {
            pai.esquerda = new Nodo(valor);
            return pai.esquerda;
        }

        Nodo inserirDireita(Nodo pai, int valor) {
            pai.direita = new Nodo(valor);
            return pai.direita;
        }

        boolean estaVazia() {
            return raiz == null;
        }

        int contarNosComDoisFilhos() {
            return contarNosComDoisFilhos(raiz);
        }

        private int contarNosComDoisFilhos(Nodo nodo) {
            if (nodo == null) {
                return 0;
            }

            // Um nodo tem dois filhos quando as duas referencias foram preenchidas.
            int quantidadeAtual = 0;
            if (nodo.esquerda != null && nodo.direita != null) {
                quantidadeAtual = 1;
            }

            int quantidadeEsquerda = contarNosComDoisFilhos(nodo.esquerda);
            int quantidadeDireita = contarNosComDoisFilhos(nodo.direita);

            return quantidadeAtual + quantidadeEsquerda + quantidadeDireita;
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(10);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 5);
        arvore.inserirDireita(raiz, 20);
        arvore.inserirEsquerda(esquerda, 3);

        System.out.println("Nos com dois filhos: " + arvore.contarNosComDoisFilhos());
        System.out.println("Resultado esperado: 1");
    }
}
