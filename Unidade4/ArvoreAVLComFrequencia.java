package Unidade4;

import Unidade2.P03Filas.FilaDinamica;

/**
 * Arvore AVL generica com tratamento de duplicatas por frequencia. Cada valor
 * distinto aparece em apenas um nodo, e esse nodo guarda quantas ocorrencias
 * daquele valor existem na estrutura.
 */
public class ArvoreAVLComFrequencia<T extends Comparable<T>> {

    // =========================
    // Classe interna do nodo
    // =========================

    private class Nodo {
        T elemento;
        int frequencia;
        int altura;
        Nodo esquerdo;
        Nodo direito;

        Nodo(T elemento) {
            this.elemento = elemento;
            this.frequencia = 1;
            this.altura = 0;
        }
    }

    // =========================
    // Atributos
    // =========================

    private Nodo raiz;
    private int nElementos;
    private int nDistintos;

    // =========================
    // Metodos basicos
    // =========================

    public boolean estaVazia() {
        return nElementos == 0;
    }

    /**
     * Retorna o total de ocorrencias, contando valores repetidos.
     */
    public int tamanho() {
        return nElementos;
    }

    /**
     * Retorna quantos valores distintos existem na arvore.
     */
    public int distintos() {
        return nDistintos;
    }

    public void limpar() {
        raiz = null;
        nElementos = 0;
        nDistintos = 0;
    }

    // =========================
    // Insercao
    // =========================

    /**
     * Insere uma ocorrencia do elemento. Se o valor ja existe, apenas aumentamos
     * sua frequencia; se nao existe, criamos um novo nodo e rebalanceamos o caminho
     * de volta da recursao.
     */
    public boolean inserir(T elemento) {
        if (elemento == null) {
            return false;
        }

        boolean novoValor = !contem(elemento);

        raiz = inserir(raiz, elemento);
        nElementos++;
        if (novoValor) {
            nDistintos++;
        }
        return true;
    }

    private Nodo inserir(Nodo nodo, T elemento) {
        if (nodo == null) {
            return new Nodo(elemento);
        }

        int comparacao = elemento.compareTo(nodo.elemento);
        if (comparacao < 0) {
            nodo.esquerdo = inserir(nodo.esquerdo, elemento);
        } else if (comparacao > 0) {
            nodo.direito = inserir(nodo.direito, elemento);
        } else {
            nodo.frequencia++;
            return nodo;
        }

        // Apenas o caminho percorrido pode ter mudado de altura.
        return balancear(nodo);
    }

    // =========================
    // Remocao
    // =========================

    /**
     * Remove uma ocorrencia do elemento. Se a frequencia for maior que 1, apenas
     * decrementamos a contagem. O nodo so sai da arvore quando a ultima ocorrencia
     * e removida.
     */
    public boolean remover(T elemento) {
        if (elemento == null) {
            return false;
        }

        int quantidadeAntes = frequencia(elemento);
        if (quantidadeAntes == 0) {
            return false;
        }

        raiz = remover(raiz, elemento);
        nElementos--;
        if (quantidadeAntes == 1) {
            nDistintos--;
        }
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
            if (nodo.frequencia > 1) {
                nodo.frequencia--;
                return nodo;
            }

            // A ultima ocorrencia sera removida, entao o nodo precisa sair.
            if (nodo.esquerdo == null) {
                return nodo.direito;
            }
            if (nodo.direito == null) {
                return nodo.esquerdo;
            }

            /*
             * Com dois filhos, copiamos o sucessor inteiro, incluindo sua frequencia.
             * Depois removemos o nodo original do sucessor por completo. O contador
             * nElementos ja foi decrementado uma unica vez no metodo publico.
             */
            Nodo sucessor = menorNodo(nodo.direito);
            nodo.elemento = sucessor.elemento;
            nodo.frequencia = sucessor.frequencia;
            nodo.direito = removerMenorCompleto(nodo.direito);
        }

        // A remocao estrutural so pode afetar o caminho percorrido.
        return balancear(nodo);
    }

    /**
     * Remove o menor nodo da subarvore, ignorando a frequencia dele. Este metodo e
     * usado depois que o sucessor ja foi copiado para outro nodo.
     */
    private Nodo removerMenorCompleto(Nodo nodo) {
        if (nodo.esquerdo == null) {
            return nodo.direito;
        }

        nodo.esquerdo = removerMenorCompleto(nodo.esquerdo);
        return balancear(nodo);
    }

    // =========================
    // Busca e consulta
    // =========================

    public boolean contem(T elemento) {
        return buscarNodo(elemento) != null;
    }

    public T buscar(T elemento) {
        Nodo nodo = buscarNodo(elemento);
        if (nodo == null) {
            return null;
        }
        return nodo.elemento;
    }

    public int frequencia(T elemento) {
        Nodo nodo = buscarNodo(elemento);
        if (nodo == null) {
            return 0;
        }
        return nodo.frequencia;
    }

    private Nodo buscarNodo(T elemento) {
        if (elemento == null) {
            return null;
        }

        Nodo atual = raiz;
        while (atual != null) {
            int comparacao = elemento.compareTo(atual.elemento);
            if (comparacao == 0) {
                return atual;
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
     * significa que a esquerda esta mais alta.
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
     * A altura depende da forma da arvore, nao da frequencia guardada no nodo.
     * Inserir uma duplicata nao muda a altura; inserir um valor novo pode mudar.
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
            if (fatorBalanco(nodo.direito) < 0) {
                System.out.println("Balanceando " + nodo.elemento + ": rotacao dupla direita-esquerda");
                nodo.direito = rotacaoDireita(nodo.direito);
                return rotacaoEsquerda(nodo);
            }
            System.out.println("Balanceando " + nodo.elemento + ": rotacao simples a esquerda");
            return rotacaoEsquerda(nodo);
        }

        if (fator < -1) {
            if (fatorBalanco(nodo.esquerdo) > 0) {
                System.out.println("Balanceando " + nodo.elemento + ": rotacao dupla esquerda-direita");
                nodo.esquerdo = rotacaoEsquerda(nodo.esquerdo);
                return rotacaoDireita(nodo);
            }
            System.out.println("Balanceando " + nodo.elemento + ": rotacao simples a direita");
            return rotacaoDireita(nodo);
        }

        return nodo;
    }

    private Nodo rotacaoDireita(Nodo p) {
        Nodo u = p.esquerdo;
        Nodo t3 = u.direito;

        u.direito = p;
        p.esquerdo = t3;

        atualizarAltura(p);
        atualizarAltura(u);
        return u;
    }

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
        System.out.print(nodo.elemento + "(f=" + nodo.frequencia + ") ");
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

        System.out.print(nodo.elemento + "(f=" + nodo.frequencia + ",h=" + nodo.altura + ",fb="
                + fatorBalanco(nodo) + ") ");
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
            System.out.print(atual.elemento + "(f=" + atual.frequencia + ",fb=" + fatorBalanco(atual) + ") ");

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
        System.out.println("=== Teste: ArvoreAVLComFrequencia ===");

        ArvoreAVLComFrequencia<Integer> arvore = new ArvoreAVLComFrequencia<>();
        Integer[] valores = { 30, 20, 10, 20, 30, 30, 25, 40, 50, 45, 45 };
        for (Integer valor : valores) {
            arvore.inserir(valor);
        }

        System.out.print("Em ordem com frequencias: ");
        arvore.imprimirEmOrdem();
        System.out.print("Pre-ordem com frequencias e alturas: ");
        arvore.imprimirPreOrdem();
        System.out.print("Largura com fatores: ");
        arvore.imprimirEmLargura();

        System.out.println("Total de ocorrencias: " + arvore.tamanho());
        System.out.println("Valores distintos: " + arvore.distintos());
        System.out.println("Frequencia de 30: " + arvore.frequencia(30));
        System.out.println("Remove 30 uma vez: " + arvore.remover(30));
        System.out.println("Frequencia de 30: " + arvore.frequencia(30));
        System.out.println("Remove 20 uma vez: " + arvore.remover(20));
        System.out.println("Frequencia de 20: " + arvore.frequencia(20));
        System.out.print("Depois das remocoes: ");
        arvore.imprimirEmLargura();
    }
}
