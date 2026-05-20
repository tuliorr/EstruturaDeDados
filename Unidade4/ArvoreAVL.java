package Unidade4;

import Unidade2.P03Filas.FilaDinamica;

/**
 * Arvore AVL generica. Ela combina a regra da arvore binaria de busca com
 * rotacoes que mantem a diferenca de altura entre as subarvores controlada.
 */
public class ArvoreAVL<T extends Comparable<T>> {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        T elemento;
        int altura;
        Nodo esquerdo;
        Nodo direito;

        Nodo(T elemento) {
            this.elemento = elemento;
            this.altura = 0;
        }
    }

    /**
     * A altura armazenada no nodo evita recalcular a altura recursivamente a cada
     * operacao. Ela olha para baixo: tratamos o nodo como raiz da sua subarvore e
     * usamos a maior altura entre o filho esquerdo e o filho direito. Portanto, a
     * altura nao e o nivel do nodo a partir da raiz principal da arvore.
     */

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
     * Insere um elemento e rebalanceia a arvore no caminho de volta da recursao.
     * Como a AVL e tambem uma arvore de busca, valores repetidos nao entram.
     */
    public boolean inserir(T elemento) {
        if (elemento == null) {
            return false;
        }

        if (contem(elemento)) {
            return false;
        }

        raiz = inserir(raiz, elemento);
        nElementos++;
        return true;
    }

    private Nodo inserir(Nodo nodo, T elemento) {
        if (nodo == null) {
            return new Nodo(elemento);
        }

        int comparacao = elemento.compareTo(nodo.elemento);
        if (comparacao < 0) {
            nodo.esquerdo = inserir(nodo.esquerdo, elemento);
        } else {
            nodo.direito = inserir(nodo.direito, elemento);
        }

        // So este nodo e seus ancestrais precisam ser verificados na volta da
        // recursao, porque apenas esse caminho teve altura alterada.
        return balancear(nodo);
    }

    // =========================
    // Remocao
    // =========================

    /**
     * Remove um elemento e rebalanceia os ancestrais afetados. A remocao em AVL
     * segue a remocao da arvore de busca, com rotacoes no retorno da recursao.
     */
    public boolean remover(T elemento) {
        if (elemento == null) {
            return false;
        }

        if (!contem(elemento)) {
            return false;
        }

        raiz = remover(raiz, elemento);
        nElementos--;
        return true;
    }

    private Nodo remover(Nodo nodo, T elemento) {
        if (nodo == null) {
            return null;
        }

        int comparacao = elemento.compareTo(nodo.elemento);
        if (comparacao < 0) {
            nodo.esquerdo = remover(nodo.esquerdo, elemento);
        } else if (comparacao > 0) {
            nodo.direito = remover(nodo.direito, elemento);
        } else {
            // Sem filho ou com apenas um filho: o filho existente ocupa o lugar.
            if (nodo.esquerdo == null) {
                return nodo.direito;
            }
            if (nodo.direito == null) {
                return nodo.esquerdo;
            }

            // Com dois filhos, usamos o sucessor para preservar a ordem da busca.
            Nodo sucessor = menorNodo(nodo.direito);
            nodo.elemento = sucessor.elemento;
            nodo.direito = removerMenor(nodo.direito);
        }

        // A remocao tambem so pode desbalancear os nodos do caminho percorrido.
        // Por isso balanceamos na volta da recursao, do ponto removido ate a raiz.
        return balancear(nodo);
    }

    private Nodo removerMenor(Nodo nodo) {
        if (nodo.esquerdo == null) {
            return nodo.direito;
        }

        nodo.esquerdo = removerMenor(nodo.esquerdo);
        // Ao remover o menor, apenas os ancestrais desse menor podem mudar de altura.
        return balancear(nodo);
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean contem(T elemento) {
        return buscar(elemento) != null;
    }

    public T buscar(T elemento) {
        if (elemento == null) {
            return null;
        }

        Nodo atual = raiz;
        while (atual != null) {
            int comparacao = elemento.compareTo(atual.elemento);
            if (comparacao == 0) {
                return atual.elemento;
            }
            atual = comparacao < 0 ? atual.esquerdo : atual.direito;
        }
        return null;
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
        return nodo.altura;
    }

    // =========================
    // Balanceamento
    // =========================

    /**
     * Fator positivo significa que a direita esta mais alta. Fator negativo
     * significa que a esquerda esta mais alta. Em AVL, os valores permitidos sao
     * -1, 0 e 1.
     */
    private int fatorBalanco(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaEsquerda = altura(nodo.esquerdo);
        int alturaDireita = altura(nodo.direito);

        return alturaDireita - alturaEsquerda;
    }

    /**
     * A altura armazenada no nodo evita recalcular a altura recursivamente a cada
     * operacao. Ela olha para baixo: tratamos o nodo como raiz da sua subarvore e
     * usamos a maior altura entre o filho esquerdo e o filho direito. Portanto, a
     * altura nao e o nivel do nodo a partir da raiz principal da arvore.
     */
    private void atualizarAltura(Nodo nodo) {
        int alturaEsquerda = altura(nodo.esquerdo);
        int alturaDireita = altura(nodo.direito);

        nodo.altura = 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    private Nodo balancear(Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        atualizarAltura(nodo);
        int fator = fatorBalanco(nodo);

        if (fator > 1) {
            // Caso direita-esquerda: primeiro corrigimos o filho direito.
            if (fatorBalanco(nodo.direito) < 0) {
                System.out.println("Balanceando " + nodo.elemento + ": rotacao dupla direita-esquerda");
                nodo.direito = rotacaoDireita(nodo.direito);
                return rotacaoEsquerda(nodo);
            }

            // Caso direita-direita: uma rotacao simples a esquerda resolve.
            System.out.println("Balanceando " + nodo.elemento + ": rotacao simples a esquerda");
            return rotacaoEsquerda(nodo);
        }

        if (fator < -1) {
            // Caso esquerda-direita: primeiro corrigimos o filho esquerdo.
            if (fatorBalanco(nodo.esquerdo) > 0) {
                System.out.println("Balanceando " + nodo.elemento + ": rotacao dupla esquerda-direita");
                nodo.esquerdo = rotacaoEsquerda(nodo.esquerdo);
                return rotacaoDireita(nodo);
            }

            // Caso esquerda-esquerda: uma rotacao simples a direita resolve.
            System.out.println("Balanceando " + nodo.elemento + ": rotacao simples a direita");
            return rotacaoDireita(nodo);
        }

        return nodo;
    }

    /**
     * Rotacao a direita: o filho esquerdo sobe e o nodo desbalanceado desce para a
     * direita. Na nomenclatura dos slides, p e o nodo desbalanceado, u e seu
     * filho esquerdo, e t3 e a subarvore direita de u que precisa continuar entre
     * u e p.
     */
    private Nodo rotacaoDireita(Nodo p) {
        Nodo u = p.esquerdo;
        Nodo t3 = u.direito;

        u.direito = p;
        p.esquerdo = t3;

        atualizarAltura(p);
        atualizarAltura(u);
        return u;
    }

    /**
     * Rotacao a esquerda: o filho direito sobe e o nodo desbalanceado desce para a
     * esquerda. Na nomenclatura dos slides, p e o nodo desbalanceado, u e seu
     * filho direito, e t2 e a subarvore esquerda de u que precisa continuar entre
     * p e u.
     */
    private Nodo rotacaoEsquerda(Nodo p) {
        Nodo u = p.direito;
        Nodo t2 = u.esquerdo;

        u.esquerdo = p;
        p.direito = t2;

        atualizarAltura(p);
        atualizarAltura(u);
        return u;
    }

    // =========================
    // Percursos
    // =========================

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

    public void imprimirPreOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        System.out.print(nodo.elemento + "(h=" + nodo.altura + ",fb=" + fatorBalanco(nodo) + ") ");
        preOrdem(nodo.esquerdo);
        preOrdem(nodo.direito);
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
            System.out.print(atual.elemento + "(fb=" + fatorBalanco(atual) + ") ");

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
        System.out.println("=== Teste: ArvoreAVL ===");

        // A sequencia forcaria uma arvore inclinada; a AVL rotaciona para equilibrar.
        ArvoreAVL<Integer> arvore = new ArvoreAVL<>();
        Integer[] valores = { 30, 20, 10, 25, 40, 50, 45 };
        for (Integer valor : valores) {
            arvore.inserir(valor);
        }

        System.out.print("Em ordem: ");
        arvore.imprimirEmOrdem();
        System.out.print("Pre-ordem com alturas: ");
        arvore.imprimirPreOrdem();
        System.out.print("Largura com fatores: ");
        arvore.imprimirEmLargura();

        // A remocao tambem pode exigir rebalanceamento no caminho de volta.
        System.out.println("Remove 20: " + arvore.remover(20));
        System.out.print("Depois da remocao: ");
        arvore.imprimirEmLargura();
        System.out.println("Altura: " + arvore.altura());
    }
}
