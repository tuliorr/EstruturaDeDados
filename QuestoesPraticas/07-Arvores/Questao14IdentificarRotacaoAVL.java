/**
 * Questao 14 - Identificar Rotacao AVL.
 *
 * Identifica qual rotacao corrige o primeiro desbalanceamento em uma sequencia
 * curta de insercao.
 */
public class Questao14IdentificarRotacaoAVL {

    private static class Nodo {
        int valor;
        int altura;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private static class ArvoreAVL {
        private Nodo raiz;
        private String ultimoCaso = "sem desbalanceamento";
        private String ultimaRotacao = "sem rotacao";

        void inserir(int valor) {
            raiz = inserir(raiz, valor);
        }

        private Nodo inserir(Nodo nodo, int valor) {
            if (nodo == null) {
                return new Nodo(valor);
            }

            if (valor < nodo.valor) {
                nodo.esquerda = inserir(nodo.esquerda, valor);
            } else if (valor > nodo.valor) {
                nodo.direita = inserir(nodo.direita, valor);
            } else {
                return nodo;
            }

            atualizarAltura(nodo);
            return balancear(nodo, valor);
        }

        boolean buscar(int valor) {
            Nodo atual = raiz;
            while (atual != null) {
                if (valor == atual.valor) {
                    return true;
                }
                atual = valor < atual.valor ? atual.esquerda : atual.direita;
            }
            return false;
        }

        String identificarCaso() {
            return ultimoCaso;
        }

        String identificarRotacao() {
            return ultimaRotacao;
        }

        private Nodo balancear(Nodo nodo, int valorInserido) {
            int fator = fatorBalanceamento(nodo);

            // Fator positivo indica peso maior na esquerda; negativo, na direita.
            if (fator > 1 && valorInserido < nodo.esquerda.valor) {
                ultimoCaso = "esquerda-esquerda";
                ultimaRotacao = "simples a direita";
                return rotacionarDireita(nodo);
            }

            if (fator < -1 && valorInserido > nodo.direita.valor) {
                ultimoCaso = "direita-direita";
                ultimaRotacao = "simples a esquerda";
                return rotacionarEsquerda(nodo);
            }

            if (fator > 1 && valorInserido > nodo.esquerda.valor) {
                ultimoCaso = "esquerda-direita";
                ultimaRotacao = "dupla esquerda-direita";
                nodo.esquerda = rotacionarEsquerda(nodo.esquerda);
                return rotacionarDireita(nodo);
            }

            if (fator < -1 && valorInserido < nodo.direita.valor) {
                ultimoCaso = "direita-esquerda";
                ultimaRotacao = "dupla direita-esquerda";
                nodo.direita = rotacionarDireita(nodo.direita);
                return rotacionarEsquerda(nodo);
            }

            return nodo;
        }

        private Nodo rotacionarDireita(Nodo desbalanceado) {
            Nodo novaRaiz = desbalanceado.esquerda;
            Nodo subarvoreTransferida = novaRaiz.direita;

            novaRaiz.direita = desbalanceado;
            desbalanceado.esquerda = subarvoreTransferida;

            atualizarAltura(desbalanceado);
            atualizarAltura(novaRaiz);
            return novaRaiz;
        }

        private Nodo rotacionarEsquerda(Nodo desbalanceado) {
            Nodo novaRaiz = desbalanceado.direita;
            Nodo subarvoreTransferida = novaRaiz.esquerda;

            novaRaiz.esquerda = desbalanceado;
            desbalanceado.direita = subarvoreTransferida;

            atualizarAltura(desbalanceado);
            atualizarAltura(novaRaiz);
            return novaRaiz;
        }

        private int altura(Nodo nodo) {
            return nodo == null ? -1 : nodo.altura;
        }

        private void atualizarAltura(Nodo nodo) {
            nodo.altura = 1 + Math.max(altura(nodo.esquerda), altura(nodo.direita));
        }

        private int fatorBalanceamento(Nodo nodo) {
            return altura(nodo.esquerda) - altura(nodo.direita);
        }
    }

    public static String identificarRotacaoRecursivo(int primeiro, int segundo, int terceiro) {
        ArvoreAVL arvore = new ArvoreAVL();
        arvore.inserir(primeiro);
        arvore.inserir(segundo);
        arvore.inserir(terceiro);
        return arvore.identificarRotacao();
    }

    public static String identificarCasoRecursivo(int primeiro, int segundo, int terceiro) {
        ArvoreAVL arvore = new ArvoreAVL();
        arvore.inserir(primeiro);
        arvore.inserir(segundo);
        arvore.inserir(terceiro);
        return arvore.identificarCaso();
    }

    public static String identificarRotacaoIterativo(int primeiro, int segundo, int terceiro) {
        String caso = identificarCasoIterativo(primeiro, segundo, terceiro);

        if (caso.equals("esquerda-esquerda")) {
            return "simples a direita";
        }
        if (caso.equals("direita-direita")) {
            return "simples a esquerda";
        }
        if (caso.equals("esquerda-direita")) {
            return "dupla esquerda-direita";
        }
        if (caso.equals("direita-esquerda")) {
            return "dupla direita-esquerda";
        }
        return "sem rotacao";
    }

    public static String identificarCasoIterativo(int primeiro, int segundo, int terceiro) {
        // Com tres insercoes, basta acompanhar para qual lado cada valor iria.
        if (segundo < primeiro && terceiro < segundo) {
            return "esquerda-esquerda";
        }
        if (segundo > primeiro && terceiro > segundo) {
            return "direita-direita";
        }
        if (segundo < primeiro && terceiro > segundo && terceiro < primeiro) {
            return "esquerda-direita";
        }
        if (segundo > primeiro && terceiro < segundo && terceiro > primeiro) {
            return "direita-esquerda";
        }
        return "sem desbalanceamento";
    }

    public static String identificarRotacao(int primeiro, int segundo, int terceiro) {
        return identificarRotacaoRecursivo(primeiro, segundo, terceiro);
    }

    public static String identificarCaso(int primeiro, int segundo, int terceiro) {
        return identificarCasoRecursivo(primeiro, segundo, terceiro);
    }

    public static void main(String[] args) {
        int primeiro = 30;
        int segundo = 10;
        int terceiro = 20;

        System.out.println("Caso (recursivo): " + identificarCasoRecursivo(primeiro, segundo, terceiro));
        System.out.println("Rotacao (recursivo): " + identificarRotacaoRecursivo(primeiro, segundo, terceiro));
        System.out.println("Caso (iterativo): " + identificarCasoIterativo(primeiro, segundo, terceiro));
        System.out.println("Rotacao (iterativo): " + identificarRotacaoIterativo(primeiro, segundo, terceiro));
        System.out.println("Resultado esperado: esquerda-direita / dupla esquerda-direita");
    }
}
