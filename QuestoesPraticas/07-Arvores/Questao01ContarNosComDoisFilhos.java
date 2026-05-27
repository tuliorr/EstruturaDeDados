import Unidade2.P02Pilhas.PilhaDinamica;

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

        int contarNosComDoisFilhosRecursivo() {
            return contarNosComDoisFilhosRecursivo(raiz);
        }

        private int contarNosComDoisFilhosRecursivo(Nodo nodo) {
            if (nodo == null) {
                return 0;
            }

            // Um nodo tem dois filhos quando as duas referencias foram preenchidas.
            int quantidadeAtual = 0;
            if (nodo.esquerda != null && nodo.direita != null) {
                quantidadeAtual = 1;
            }

            int quantidadeEsquerda = contarNosComDoisFilhosRecursivo(nodo.esquerda);
            int quantidadeDireita = contarNosComDoisFilhosRecursivo(nodo.direita);

            return quantidadeAtual + quantidadeEsquerda + quantidadeDireita;
        }

        int contarNosComDoisFilhosIterativo() {
            if (raiz == null) {
                return 0;
            }

            int quantidade = 0;
            PilhaDinamica<Nodo> pilha = new PilhaDinamica<>();
            pilha.empilhar(raiz);

            while (!pilha.estaVazia()) {
                Nodo atual = pilha.desempilhar();

                if (atual.esquerda != null && atual.direita != null) {
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
        Nodo raiz = arvore.inserirRaiz(10);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 5);
        arvore.inserirDireita(raiz, 20);
        arvore.inserirEsquerda(esquerda, 3);

        System.out.println("Nos com dois filhos (recursivo): " + arvore.contarNosComDoisFilhosRecursivo());
        System.out.println("Nos com dois filhos (iterativo): " + arvore.contarNosComDoisFilhosIterativo());
        System.out.println("Resultado esperado: 1");
    }
}
