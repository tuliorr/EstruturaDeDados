import Unidade2.P03Filas.FilaDinamica;

/**
 * Questao 11 - Contar Subcategorias em Arvore N-aria.
 *
 * Usa uma arvore N-aria para contar os filhos diretos de uma categoria.
 */
public class Questao11ContarSubcategorias {

    private static class Nodo {
        String valor;
        Nodo primeiroFilho;
        Nodo proximoIrmao;

        Nodo(String valor) {
            this.valor = valor;
        }
    }

    private static class ArvoreNaria {
        private Nodo raiz;

        void inserirRaiz(String valor) {
            raiz = new Nodo(valor);
        }

        boolean estaVazia() {
            return raiz == null;
        }

        boolean inserirFilho(String pai, String filho) {
            Nodo nodoPai = buscarRecursivo(pai);
            if (nodoPai == null) {
                return false;
            }

            Nodo novo = new Nodo(filho);
            if (nodoPai.primeiroFilho == null) {
                nodoPai.primeiroFilho = novo;
                return true;
            }

            // Irmaos formam uma lista encadeada simples de filhos do mesmo pai.
            Nodo atual = nodoPai.primeiroFilho;
            while (atual.proximoIrmao != null) {
                atual = atual.proximoIrmao;
            }
            atual.proximoIrmao = novo;
            return true;
        }

        Nodo buscarRecursivo(String valor) {
            return buscarRecursivo(raiz, valor);
        }

        private Nodo buscarRecursivo(Nodo nodo, String valor) {
            if (nodo == null) {
                return null;
            }
            if (mesmoValor(nodo.valor, valor)) {
                return nodo;
            }

            Nodo filho = nodo.primeiroFilho;
            while (filho != null) {
                Nodo encontrado = buscarRecursivo(filho, valor);
                if (encontrado != null) {
                    return encontrado;
                }
                filho = filho.proximoIrmao;
            }

            return null;
        }

        private boolean mesmoValor(String primeiro, String segundo) {
            return primeiro == null ? segundo == null : primeiro.equals(segundo);
        }

        Nodo buscarIterativo(String valor) {
            if (raiz == null) {
                return null;
            }

            FilaDinamica<Nodo> fila = new FilaDinamica<>();
            fila.enfileirar(raiz);

            while (!fila.estaVazia()) {
                Nodo atual = fila.desenfileirar();
                if (mesmoValor(atual.valor, valor)) {
                    return atual;
                }

                Nodo filho = atual.primeiroFilho;
                while (filho != null) {
                    fila.enfileirar(filho);
                    filho = filho.proximoIrmao;
                }
            }

            return null;
        }

        int contarSubcategoriasDiretasRecursivo(String categoria) {
            Nodo nodo = buscarRecursivo(categoria);
            if (nodo == null) {
                return -1;
            }

            return contarIrmaosRecursivo(nodo.primeiroFilho);
        }

        private int contarIrmaosRecursivo(Nodo nodo) {
            if (nodo == null) {
                return 0;
            }
            return 1 + contarIrmaosRecursivo(nodo.proximoIrmao);
        }

        int contarSubcategoriasDiretasIterativo(String categoria) {
            Nodo nodo = buscarIterativo(categoria);
            if (nodo == null) {
                return -1;
            }

            int quantidade = 0;
            Nodo filho = nodo.primeiroFilho;
            while (filho != null) {
                quantidade++;
                filho = filho.proximoIrmao;
            }
            return quantidade;
        }
    }

    public static void main(String[] args) {
        ArvoreNaria arvore = new ArvoreNaria();
        arvore.inserirRaiz("Loja");
        arvore.inserirFilho("Loja", "Informatica");
        arvore.inserirFilho("Loja", "Livros");
        arvore.inserirFilho("Loja", "Jogos");
        arvore.inserirFilho("Informatica", "Notebooks");
        arvore.inserirFilho("Informatica", "Monitores");

        System.out.println("Subcategorias diretas de Informatica (recursivo): "
                + arvore.contarSubcategoriasDiretasRecursivo("Informatica"));
        System.out.println("Subcategorias diretas de Informatica (iterativo): "
                + arvore.contarSubcategoriasDiretasIterativo("Informatica"));
        System.out.println("Resultado esperado: 2");
    }
}
