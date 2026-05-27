import Unidade2.P02Pilhas.PilhaDinamica;

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

        boolean buscar(int valor) {
            return buscar(raiz, valor);
        }

        private boolean buscar(Nodo nodo, int valor) {
            if (nodo == null) {
                return false;
            }
            if (nodo.valor == valor) {
                return true;
            }
            return buscar(nodo.esquerda, valor) || buscar(nodo.direita, valor);
        }

        int somarParesRecursivo() {
            return somarParesRecursivo(raiz);
        }

        private int somarParesRecursivo(Nodo nodo) {
            if (nodo == null) {
                return 0;
            }

            int somaAtual = 0;
            if (nodo.valor % 2 == 0) {
                somaAtual = nodo.valor;
            }

            // Todo nodo precisa ser visitado, pois uma arvore binaria comum nao e ordenada.
            return somaAtual + somarParesRecursivo(nodo.esquerda) + somarParesRecursivo(nodo.direita);
        }

        int somarParesIterativo() {
            if (raiz == null) {
                return 0;
            }

            int soma = 0;
            PilhaDinamica<Nodo> pilha = new PilhaDinamica<>();
            pilha.empilhar(raiz);

            while (!pilha.estaVazia()) {
                Nodo atual = pilha.desempilhar();
                if (atual.valor % 2 == 0) {
                    soma += atual.valor;
                }
                if (atual.direita != null) {
                    pilha.empilhar(atual.direita);
                }
                if (atual.esquerda != null) {
                    pilha.empilhar(atual.esquerda);
                }
            }

            return soma;
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(8);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 3);
        Nodo direita = arvore.inserirDireita(raiz, 10);
        arvore.inserirEsquerda(esquerda, 1);
        arvore.inserirEsquerda(direita, 6);
        arvore.inserirDireita(direita, 14);

        System.out.println("Soma dos pares (recursivo): " + arvore.somarParesRecursivo());
        System.out.println("Soma dos pares (iterativo): " + arvore.somarParesIterativo());
        System.out.println("Resultado esperado: 38");
    }
}
