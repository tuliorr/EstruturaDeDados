import Unidade2.P03Filas.FilaDinamica;

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

        boolean mesmoFormatoRecursivo(ArvoreBinaria outra) {
            return mesmoFormatoRecursivo(raiz, outra.raiz);
        }

        private boolean mesmoFormatoRecursivo(Nodo primeiro, Nodo segundo) {
            if (primeiro == null && segundo == null) {
                return true;
            }
            if (primeiro == null || segundo == null) {
                return false;
            }

            // Os valores nao importam: comparamos somente a existencia dos filhos.
            return mesmoFormatoRecursivo(primeiro.esquerda, segundo.esquerda)
                    && mesmoFormatoRecursivo(primeiro.direita, segundo.direita);
        }

        boolean mesmoFormatoIterativo(ArvoreBinaria outra) {
            FilaDinamica<ParDeNodos> fila = new FilaDinamica<>();
            fila.enfileirar(new ParDeNodos(raiz, outra.raiz));

            while (!fila.estaVazia()) {
                ParDeNodos par = fila.desenfileirar();
                Nodo primeiro = par.primeiro;
                Nodo segundo = par.segundo;

                if (primeiro == null && segundo == null) {
                    continue;
                }
                if (primeiro == null || segundo == null) {
                    return false;
                }

                fila.enfileirar(new ParDeNodos(primeiro.esquerda, segundo.esquerda));
                fila.enfileirar(new ParDeNodos(primeiro.direita, segundo.direita));
            }

            return true;
        }
    }

    private static class ParDeNodos {
        Nodo primeiro;
        Nodo segundo;

        ParDeNodos(Nodo primeiro, Nodo segundo) {
            this.primeiro = primeiro;
            this.segundo = segundo;
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

        System.out.println("Mesmo formato (recursivo): " + arvoreA.mesmoFormatoRecursivo(arvoreB));
        System.out.println("Mesmo formato (iterativo): " + arvoreA.mesmoFormatoIterativo(arvoreB));
        System.out.println("Resultado esperado: true");
    }
}
