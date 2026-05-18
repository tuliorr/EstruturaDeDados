import java.util.Objects;

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

        boolean inserirFilho(String pai, String filho) {
            Nodo nodoPai = buscar(raiz, pai);
            if (nodoPai == null) {
                return false;
            }

            Nodo novo = new Nodo(filho);
            if (nodoPai.primeiroFilho == null) {
                nodoPai.primeiroFilho = novo;
                return true;
            }

            Nodo atual = nodoPai.primeiroFilho;
            while (atual.proximoIrmao != null) {
                atual = atual.proximoIrmao;
            }
            atual.proximoIrmao = novo;
            return true;
        }

        private Nodo buscar(Nodo nodo, String valor) {
            if (nodo == null) {
                return null;
            }
            if (Objects.equals(nodo.valor, valor)) {
                return nodo;
            }

            Nodo filho = nodo.primeiroFilho;
            while (filho != null) {
                Nodo encontrado = buscar(filho, valor);
                if (encontrado != null) {
                    return encontrado;
                }
                filho = filho.proximoIrmao;
            }

            return null;
        }

        int contarSubcategoriasDiretas(String categoria) {
            Nodo nodo = buscar(raiz, categoria);
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

        System.out.println("Subcategorias diretas de Informatica: "
                + arvore.contarSubcategoriasDiretas("Informatica"));
        System.out.println("Resultado esperado: 2");
    }
}
