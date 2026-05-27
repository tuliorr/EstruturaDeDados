/**
 * Questao 02 - Contar Nos com Apenas Um Filho.
 *
 * Conta quantos nodos possuem exatamente um filho.
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

        int altura() {
            return altura(raiz);
        }

        private int altura(Nodo nodo) {
            if (nodo == null) {
                return -1;
            }
            return 1 + Math.max(altura(nodo.esquerda), altura(nodo.direita));
        }

        int contarNosComUmFilho() {
            return contarNosComUmFilho(raiz);
        }

        private int contarNosComUmFilho(Nodo nodo) {
            if (nodo == null) {
                return 0;
            }

            boolean temApenasFilhoEsquerdo = nodo.esquerda != null && nodo.direita == null;
            boolean temApenasFilhoDireito = nodo.esquerda == null && nodo.direita != null;
            int quantidadeAtual = temApenasFilhoEsquerdo || temApenasFilhoDireito ? 1 : 0;

            // A recursao soma a resposta do nodo atual com as subarvores.
            return quantidadeAtual
                    + contarNosComUmFilho(nodo.esquerda)
                    + contarNosComUmFilho(nodo.direita);
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(10);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 5);
        Nodo direita = arvore.inserirDireita(raiz, 20);
        arvore.inserirEsquerda(esquerda, 3);
        arvore.inserirDireita(direita, 30);

        System.out.println("Nos com apenas um filho: " + arvore.contarNosComUmFilho());
        System.out.println("Altura da arvore: " + arvore.altura());
        System.out.println("Resultado esperado: 2");
    }
}
