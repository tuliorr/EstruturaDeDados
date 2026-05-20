package Unidade4;

import java.util.Objects;

import Unidade2.P03Filas.FilaDinamica;

/**
 * Arvore N-aria generica. Cada nodo pode ter qualquer quantidade de filhos.
 *
 * A representacao usada aqui e "primeiro filho / proximo irmao": cada nodo
 * guarda uma referencia para seu primeiro filho e outra para o proximo filho do
 * mesmo pai. Assim a arvore N-aria e implementada sem usar uma lista pronta.
 */
public class ArvoreNaria<T> {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        T elemento;
        Nodo primeiroFilho;
        Nodo proximoIrmao;

        Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Nodo raiz;
    private int nElementos;

    // =========================
    // Metodos basicos
    // =========================

    public boolean estaVazia() {
        return nElementos == 0;
    }

    public int tamanho() {
        return nElementos;
    }

    public T obterRaiz() {
        return raiz == null ? null : raiz.elemento;
    }

    public void limpar() {
        raiz = null;
        nElementos = 0;
    }

    // =========================
    // Insercao
    // =========================

    /**
     * Insere a raiz. Como toda arvore tem apenas uma raiz, a operacao falha se a
     * estrutura ja tiver algum nodo.
     */
    public boolean inserirRaiz(T elemento) {
        if (elemento == null || !estaVazia()) {
            return false;
        }

        raiz = new Nodo(elemento);
        nElementos++;
        return true;
    }

    /**
     * Insere um novo filho no pai informado. Se o pai ainda nao tem filhos, o novo
     * nodo vira o primeiro filho. Caso contrario, percorremos os irmaos ate o fim
     * e ligamos o novo nodo como ultimo filho.
     */
    public boolean inserirFilho(T elemento, T pai) {
        if (elemento == null || pai == null) {
            return false;
        }

        Nodo nodoPai = buscarNodo(raiz, pai);
        if (nodoPai == null) {
            return false;
        }

        Nodo novo = new Nodo(elemento);
        if (nodoPai.primeiroFilho == null) {
            nodoPai.primeiroFilho = novo;
        } else {
            Nodo ultimoFilho = nodoPai.primeiroFilho;
            while (ultimoFilho.proximoIrmao != null) {
                ultimoFilho = ultimoFilho.proximoIrmao;
            }
            ultimoFilho.proximoIrmao = novo;
        }

        nElementos++;
        return true;
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean contem(T elemento) {
        if (elemento == null) {
            return false;
        }

        return buscarNodo(raiz, elemento) != null;
    }

    /**
     * Busca recursiva em profundidade. Visitamos o nodo atual, depois seus filhos
     * da esquerda para a direita. Como nao ha ordenacao, nenhum ramo pode ser
     * descartado antes de ser visitado.
     */
    private Nodo buscarNodo(Nodo nodo, T elemento) {
        if (nodo == null || elemento == null) {
            return null;
        }
        if (Objects.equals(nodo.elemento, elemento)) {
            return nodo;
        }

        Nodo filho = nodo.primeiroFilho;
        while (filho != null) {
            Nodo encontrado = buscarNodo(filho, elemento);
            if (encontrado != null) {
                return encontrado;
            }
            filho = filho.proximoIrmao;
        }

        return null;
    }

    public int quantidadeFilhos(T elemento) {
        if (elemento == null) {
            return -1;
        }

        Nodo nodo = buscarNodo(raiz, elemento);
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

    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        int maiorAlturaFilho = -1;
        Nodo filho = nodo.primeiroFilho;
        while (filho != null) {
            int alturaFilho = altura(filho);
            maiorAlturaFilho = Math.max(maiorAlturaFilho, alturaFilho);
            filho = filho.proximoIrmao;
        }

        return 1 + maiorAlturaFilho;
    }

    // =========================
    // Percursos
    // =========================

    public void imprimirPreOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        System.out.print(nodo.elemento + " ");

        Nodo filho = nodo.primeiroFilho;
        while (filho != null) {
            preOrdem(filho);
            filho = filho.proximoIrmao;
        }
    }

    public void imprimirEmLargura() {
        if (estaVazia()) {
            System.out.println("(arvore vazia)");
            return;
        }

        FilaDinamica<Nodo> fila = new FilaDinamica<>();
        fila.enfileirar(raiz);

        while (!fila.estaVazia()) {
            Nodo atual = fila.desenfileirar();
            System.out.print(atual.elemento + " ");

            Nodo filho = atual.primeiroFilho;
            while (filho != null) {
                fila.enfileirar(filho);
                filho = filho.proximoIrmao;
            }
        }
        System.out.println();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: ArvoreNaria ===");

        // A raiz representa a disciplina, e cada unidade pode ter varios topicos.
        ArvoreNaria<String> arvore = new ArvoreNaria<>();
        arvore.inserirRaiz("Estrutura de Dados");
        arvore.inserirFilho("Unidade 1", "Estrutura de Dados");
        arvore.inserirFilho("Unidade 2", "Estrutura de Dados");
        arvore.inserirFilho("Unidade 3", "Estrutura de Dados");
        arvore.inserirFilho("Unidade 4", "Estrutura de Dados");
        arvore.inserirFilho("Listas", "Unidade 2");
        arvore.inserirFilho("Pilhas", "Unidade 2");
        arvore.inserirFilho("Filas", "Unidade 2");
        arvore.inserirFilho("Arvores", "Unidade 4");

        System.out.print("Pre-ordem: ");
        arvore.imprimirPreOrdem();
        System.out.print("Largura: ");
        arvore.imprimirEmLargura();
        System.out.println("Filhos de Unidade 2: " + arvore.quantidadeFilhos("Unidade 2"));
        System.out.println("Altura: " + arvore.altura());
    }
}

