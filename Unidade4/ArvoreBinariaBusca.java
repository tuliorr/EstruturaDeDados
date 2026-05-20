package Unidade4;

import Unidade2.P03Filas.FilaDinamica;

/**
 * Arvore binaria de busca generica. Para cada nodo, valores menores ficam a
 * esquerda e valores maiores ficam a direita.
 */
public class ArvoreBinariaBusca<T extends Comparable<T>> {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        T elemento;
        Nodo esquerdo;
        Nodo direito;

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

    public void limpar() {
        raiz = null;
        nElementos = 0;
    }

    // =========================
    // Insercao
    // =========================

    /**
     * Insere respeitando a regra da busca binaria: menores a esquerda e maiores a
     * direita. Valores repetidos nao sao inseridos para manter uma unica
     * ocorrencia de cada elemento.
     */
    public boolean inserir(T elemento) {
        if (elemento == null) {
            return false;
        }

        if (estaVazia()) {
            raiz = new Nodo(elemento);
            nElementos++;
            return true;
        }
        return inserirRecursivo(raiz, elemento);
    }

    private boolean inserirRecursivo(Nodo nodo, T elemento) {
        int comparacao = elemento.compareTo(nodo.elemento);
        if (comparacao == 0) {
            return false;
        }

        if (comparacao < 0) {
            // Se o elemento e menor, so precisamos olhar para a subarvore esquerda.
            if (nodo.esquerdo == null) {
                nodo.esquerdo = new Nodo(elemento);
                nElementos++;
                return true;
            }
            return inserirRecursivo(nodo.esquerdo, elemento);
        }

        // Se o elemento e maior, o caminho possivel fica na subarvore direita.
        if (nodo.direito == null) {
            nodo.direito = new Nodo(elemento);
            nElementos++;
            return true;
        }
        return inserirRecursivo(nodo.direito, elemento);
    }

    /**
     * Versao iterativa da insercao. A ideia e descer atualizando o pai ate achar a
     * posicao vazia onde o novo nodo sera ligado.
     */
    public boolean inserirIterativo(T elemento) {
        if (elemento == null) {
            return false;
        }

        if (estaVazia()) {
            raiz = new Nodo(elemento);
            nElementos++;
            return true;
        }

        Nodo atual = raiz;
        Nodo pai = null;

        while (atual != null) {
            int comparacao = elemento.compareTo(atual.elemento);
            if (comparacao == 0) {
                return false;
            }
            pai = atual;
            atual = comparacao < 0 ? atual.esquerdo : atual.direito;
        }

        if (elemento.compareTo(pai.elemento) < 0) {
            pai.esquerdo = new Nodo(elemento);
        } else {
            pai.direito = new Nodo(elemento);
        }
        nElementos++;
        return true;
    }

    // =========================
    // Remocao
    // =========================

    /**
     * Remove um elemento da arvore. O contador e atualizado uma unica vez no metodo
     * publico; os metodos privados apenas reorganizam os links entre nodos.
     */
    public boolean remover(T elemento) {
        if (elemento == null) {
            return false;
        }

        if (!contem(elemento)) {
            return false;
        }
        raiz = removerRecursivo(raiz, elemento);
        nElementos--;
        return true;
    }

    private Nodo removerRecursivo(Nodo nodo, T elemento) {
        if (nodo == null) {
            return null;
        }

        int comparacao = elemento.compareTo(nodo.elemento);
        if (comparacao < 0) {
            nodo.esquerdo = removerRecursivo(nodo.esquerdo, elemento);
        } else if (comparacao > 0) {
            nodo.direito = removerRecursivo(nodo.direito, elemento);
        } else {
            // Caso 1 e 2: nenhum filho ou apenas um filho. O filho existente sobe.
            if (nodo.esquerdo == null) {
                return nodo.direito;
            }
            if (nodo.direito == null) {
                return nodo.esquerdo;
            }

            // Caso 3: dois filhos. Trocamos pelo sucessor, que e o menor da direita.
            Nodo sucessor = menorNodo(nodo.direito);
            nodo.elemento = sucessor.elemento;
            nodo.direito = removerMenor(nodo.direito);
        }
        return nodo;
    }

    private Nodo removerMenor(Nodo nodo) {
        if (nodo.esquerdo == null) {
            return nodo.direito;
        }
        nodo.esquerdo = removerMenor(nodo.esquerdo);
        return nodo;
    }

    public boolean removerIterativo(T elemento) {
        if (elemento == null) {
            return false;
        }

        Nodo atual = raiz;
        Nodo pai = null;

        while (atual != null && elemento.compareTo(atual.elemento) != 0) {
            pai = atual;
            atual = elemento.compareTo(atual.elemento) < 0 ? atual.esquerdo : atual.direito;
        }

        if (atual == null) {
            return false;
        }

        if (atual.esquerdo != null && atual.direito != null) {
            Nodo paiSucessor = atual;
            Nodo sucessor = atual.direito;
            while (sucessor.esquerdo != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.esquerdo;
            }
            atual.elemento = sucessor.elemento;
            pai = paiSucessor;
            atual = sucessor;
        }

        Nodo filho = atual.esquerdo != null ? atual.esquerdo : atual.direito;
        if (pai == null) {
            raiz = filho;
        } else if (pai.esquerdo == atual) {
            pai.esquerdo = filho;
        } else {
            pai.direito = filho;
        }

        nElementos--;
        return true;
    }

    // =========================
    // Busca e consulta
    // =========================

    public T buscar(T elemento) {
        if (elemento == null) {
            return null;
        }

        Nodo resultado = buscarNodo(raiz, elemento);
        return resultado == null ? null : resultado.elemento;
    }

    public boolean contem(T elemento) {
        return buscarNodo(raiz, elemento) != null;
    }

    private Nodo buscarNodo(Nodo nodo, T elemento) {
        if (nodo == null || elemento == null) {
            return null;
        }

        int comparacao = elemento.compareTo(nodo.elemento);
        if (comparacao == 0) {
            return nodo;
        }

        // A regra da arvore de busca descarta metade da arvore em cada passo.
        return comparacao < 0 ? buscarNodo(nodo.esquerdo, elemento) : buscarNodo(nodo.direito, elemento);
    }

    public T menorElemento() {
        if (estaVazia()) {
            return null;
        }
        return menorNodo(raiz).elemento;
    }

    public T maiorElemento() {
        if (estaVazia()) {
            return null;
        }

        Nodo atual = raiz;
        while (atual.direito != null) {
            atual = atual.direito;
        }
        return atual.elemento;
    }

    private Nodo menorNodo(Nodo nodo) {
        Nodo atual = nodo;
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = altura(nodo.esquerdo);
        int alturaDireita = altura(nodo.direito);

        return 1 + Math.max(alturaEsquerda, alturaDireita);
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
        preOrdem(nodo.esquerdo);
        preOrdem(nodo.direito);
    }

    public void imprimirEmOrdem() {
        emOrdem(raiz);
        System.out.println();
    }

    private void emOrdem(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        emOrdem(nodo.esquerdo);
        System.out.print(nodo.elemento + " ");
        emOrdem(nodo.direito);
    }

    public void imprimirPosOrdem() {
        posOrdem(raiz);
        System.out.println();
    }

    private void posOrdem(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        posOrdem(nodo.esquerdo);
        posOrdem(nodo.direito);
        System.out.print(nodo.elemento + " ");
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
            if (atual.esquerdo != null) {
                fila.enfileirar(atual.esquerdo);
            }
            if (atual.direito != null) {
                fila.enfileirar(atual.direito);
            }
        }
        System.out.println();
    }

    // =========================
    // Teste da estrutura
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Teste: ArvoreBinariaBusca ===");

        // A ordem de insercao cria uma arvore de busca com menores a esquerda.
        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();
        int[] valores = { 50, 30, 70, 20, 40, 60, 80 };
        for (int valor : valores) {
            arvore.inserir(valor);
        }

        System.out.print("Em ordem: ");
        arvore.imprimirEmOrdem();
        System.out.println("Menor: " + arvore.menorElemento());
        System.out.println("Maior: " + arvore.maiorElemento());
        System.out.println("Busca 60: " + arvore.buscar(60));
        System.out.println("Remove 30: " + arvore.remover(30));
        System.out.print("Depois da remocao: ");
        arvore.imprimirEmOrdem();
    }
}

