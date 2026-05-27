import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questao 04 - Contar Valores Maiores que X.
 *
 * Conta quantos valores de uma arvore binaria sao maiores que um limite
 * informado.
 */
public class Questao04ContarMaioresQueX {

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

        int contarNos() {
            return contarNos(raiz);
        }

        private int contarNos(Nodo nodo) {
            if (nodo == null) {
                return 0;
            }
            return 1 + contarNos(nodo.esquerda) + contarNos(nodo.direita);
        }

        int contarMaioresQueRecursivo(int x) {
            return contarMaioresQueRecursivo(raiz, x);
        }

        private int contarMaioresQueRecursivo(Nodo nodo, int x) {
            if (nodo == null) {
                return 0;
            }

            int quantidadeAtual = nodo.valor > x ? 1 : 0;

            // Como nao estamos assumindo BST, os dois lados precisam ser avaliados.
            return quantidadeAtual
                    + contarMaioresQueRecursivo(nodo.esquerda, x)
                    + contarMaioresQueRecursivo(nodo.direita, x);
        }

        int contarMaioresQueIterativo(int x) {
            if (raiz == null) {
                return 0;
            }

            int quantidade = 0;
            PilhaDinamica<Nodo> pilha = new PilhaDinamica<>();
            pilha.empilhar(raiz);

            while (!pilha.estaVazia()) {
                Nodo atual = pilha.desempilhar();
                if (atual.valor > x) {
                    quantidade++;
                }
                if (atual.direita != null) {
                    pilha.empilhar(atual.direita);
                }
                if (atual.esquerda != null) {
                    pilha.empilhar(atual.esquerda);
                }
            }

            return quantidade;
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(8);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 3);
        Nodo direita = arvore.inserirDireita(raiz, 10);
        arvore.inserirEsquerda(esquerda, 1);
        arvore.inserirDireita(esquerda, 6);
        arvore.inserirDireita(direita, 14);

        int x = 6;
        System.out.println("Maiores que " + x + " (recursivo): " + arvore.contarMaioresQueRecursivo(x));
        System.out.println("Maiores que " + x + " (iterativo): " + arvore.contarMaioresQueIterativo(x));
        System.out.println("Total de nos: " + arvore.contarNos());
        System.out.println("Resultado esperado: 3");
    }
}
