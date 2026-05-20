package Unidade4;

import java.util.Objects;

import Unidade2.P03Filas.FilaDinamica;
import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Arvore binaria generica sem regra de ordenacao. O usuario escolhe em qual lado
 * de qual pai cada elemento sera inserido.
 */
public class ArvoreBinaria<T> {

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

    public T obterRaiz() {
        return raiz == null ? null : raiz.elemento;
    }

    public void limpar() {
        raiz = null;
        nElementos = 0;
    }

    // =========================
    // Insercao manual
    // =========================

    /**
     * A raiz e o primeiro nodo da arvore. Em uma arvore binaria comum, so faz
     * sentido inserir a raiz quando a estrutura ainda esta vazia.
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
     * Insere o elemento na primeira vaga encontrada em percurso por largura. Esse
     * metodo e util para exemplos comparativos, pois permite adicionar uma
     * sequencia de valores sem escolher manualmente cada pai.
     */
    public boolean inserirEmLargura(T elemento) {
        if (elemento == null) {
            return false;
        }

        if (estaVazia()) {
            return inserirRaiz(elemento);
        }

        FilaDinamica<Nodo> fila = new FilaDinamica<>();
        fila.enfileirar(raiz);

        while (!fila.estaVazia()) {
            Nodo atual = fila.desenfileirar();

            if (atual.esquerdo == null) {
                atual.esquerdo = new Nodo(elemento);
                nElementos++;
                return true;
            }
            fila.enfileirar(atual.esquerdo);

            if (atual.direito == null) {
                atual.direito = new Nodo(elemento);
                nElementos++;
                return true;
            }
            fila.enfileirar(atual.direito);
        }

        return false;
    }

    /**
     * Insere como filho esquerdo do pai informado. Como nao ha ordenacao, primeiro
     * precisamos buscar o pai na arvore inteira. A insercao falha se o pai nao
     * existir ou se ele ja tiver filho esquerdo.
     */
    public boolean inserirEsquerda(T elemento, T pai) {
        if (elemento == null || pai == null) {
            return false;
        }

        Nodo nodoPai = buscarNodo(raiz, pai);
        if (nodoPai == null || nodoPai.esquerdo != null) {
            return false;
        }
        nodoPai.esquerdo = new Nodo(elemento);
        nElementos++;
        return true;
    }

    /**
     * Insere como filho direito do pai informado. A logica e simetrica ao caso da
     * esquerda: primeiro encontramos o pai e depois verificamos se ha espaco.
     */
    public boolean inserirDireita(T elemento, T pai) {
        if (elemento == null || pai == null) {
            return false;
        }

        Nodo nodoPai = buscarNodo(raiz, pai);
        if (nodoPai == null || nodoPai.direito != null) {
            return false;
        }
        nodoPai.direito = new Nodo(elemento);
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
     * Busca em profundidade recursiva. Em uma arvore sem regra de ordenacao, pode
     * ser necessario visitar todos os nodos.
     */
    private Nodo buscarNodo(Nodo nodo, T elemento) {
        if (nodo == null || elemento == null) {
            return null;
        }
        if (Objects.equals(nodo.elemento, elemento)) {
            return nodo;
        }

        Nodo encontrado = buscarNodo(nodo.esquerdo, elemento);
        if (encontrado != null) {
            return encontrado;
        }
        return buscarNodo(nodo.direito, elemento);
    }

    /**
     * Busca em largura usando fila. Ela visita a arvore nivel por nivel.
     */
    public T buscarIterativo(T elemento) {
        if (elemento == null || estaVazia()) {
            return null;
        }

        FilaDinamica<Nodo> fila = new FilaDinamica<>();
        fila.enfileirar(raiz);

        while (!fila.estaVazia()) {
            Nodo atual = fila.desenfileirar();
            if (Objects.equals(atual.elemento, elemento)) {
                return atual.elemento;
            }
            if (atual.esquerdo != null) {
                fila.enfileirar(atual.esquerdo);
            }
            if (atual.direito != null) {
                fila.enfileirar(atual.direito);
            }
        }
        return null;
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
    // Percursos recursivos
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

    // =========================
    // Percursos iterativos
    // =========================

    public void imprimirEmProfundidade() {
        if (estaVazia()) {
            System.out.println("(arvore vazia)");
            return;
        }

        PilhaDinamica<Nodo> pilha = new PilhaDinamica<>();
        pilha.empilhar(raiz);

        while (!pilha.estaVazia()) {
            Nodo atual = pilha.desempilhar();
            System.out.print(atual.elemento + " ");

            // Empilhamos primeiro a direita para que a esquerda seja processada antes.
            if (atual.direito != null) {
                pilha.empilhar(atual.direito);
            }
            if (atual.esquerdo != null) {
                pilha.empilhar(atual.esquerdo);
            }
        }
        System.out.println();
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

            // A fila preserva a ordem dos nodos por nivel.
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
        System.out.println("=== Teste: ArvoreBinaria ===");

        // Esta arvore nao ordena os dados: escolhemos explicitamente cada filho.
        ArvoreBinaria<String> arvore = new ArvoreBinaria<>();
        arvore.inserirRaiz("Ana");
        arvore.inserirEsquerda("Bruno", "Ana");
        arvore.inserirDireita("Carlos", "Ana");
        arvore.inserirEsquerda("Daniela", "Bruno");
        arvore.inserirDireita("Eduardo", "Bruno");

        System.out.println("Raiz: " + arvore.obterRaiz());
        System.out.print("Pre-ordem: ");
        arvore.imprimirPreOrdem();
        System.out.print("Largura: ");
        arvore.imprimirEmLargura();
        System.out.println("Altura: " + arvore.altura());
        System.out.println("Busca iterativa por Daniela: " + arvore.buscarIterativo("Daniela"));
    }
}

