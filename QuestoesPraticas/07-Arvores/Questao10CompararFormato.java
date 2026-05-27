/**
 * Questao 10 - Comparar Formato de Duas Arvores.
 *
 * Verifica se duas arvores possuem o mesmo formato, ignorando os valores.
 */
public class Questao10CompararFormato {

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

        boolean mesmoFormato(ArvoreBinaria outra) {
            return mesmoFormato(raiz, outra.raiz);
        }

        private boolean mesmoFormato(Nodo primeiro, Nodo segundo) {
            if (primeiro == null && segundo == null) {
                return true;
            }
            if (primeiro == null || segundo == null) {
                return false;
            }

            // Os valores nao importam: comparamos somente a existencia dos filhos.
            return mesmoFormato(primeiro.esquerda, segundo.esquerda)
                    && mesmoFormato(primeiro.direita, segundo.direita);
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvoreA = new ArvoreBinaria();
        Nodo raizA = arvoreA.inserirRaiz(10);
        arvoreA.inserirEsquerda(raizA, 5);
        arvoreA.inserirDireita(raizA, 20);

        ArvoreBinaria arvoreB = new ArvoreBinaria();
        Nodo raizB = arvoreB.inserirRaiz(8);
        arvoreB.inserirEsquerda(raizB, 2);
        arvoreB.inserirDireita(raizB, 9);

        System.out.println("Mesmo formato: " + arvoreA.mesmoFormato(arvoreB));
        System.out.println("Resultado esperado: true");
    }
}
