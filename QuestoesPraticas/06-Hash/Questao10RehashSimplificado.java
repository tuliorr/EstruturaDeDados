/**
 * Questao 10 - Rehash Simplificado.
 *
 * Dobra a capacidade da tabela quando o fator de carga passa de 0.75 e reinsere
 * todos os elementos.
 */
public class Questao10RehashSimplificado {

    private static class Nodo {
        int valor;
        Nodo proximo;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private static class TabelaHash {
        private Nodo[] tabela;
        private int nElementos;

        TabelaHash(int capacidade) {
            tabela = new Nodo[capacidade];
        }

        int capacidade() {
            return tabela.length;
        }

        private int hash(int valor) {
            return Math.floorMod(valor, tabela.length);
        }

        double fatorDeCarga() {
            return (double) nElementos / tabela.length;
        }

        void inserir(int valor) {
            inserirSemRehash(valor);

            if (fatorDeCarga() > 0.75) {
                rehash();
            }
        }

        private void inserirSemRehash(int valor) {
            int indice = hash(valor);
            Nodo novo = new Nodo(valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
            nElementos++;
        }

        private void rehash() {
            Nodo[] tabelaAntiga = tabela;
            tabela = new Nodo[tabelaAntiga.length * 2];
            nElementos = 0;

            for (Nodo inicio : tabelaAntiga) {
                Nodo atual = inicio;
                while (atual != null) {
                    inserirSemRehash(atual.valor);
                    atual = atual.proximo;
                }
            }
        }

        boolean buscar(int valor) {
            int indice = hash(valor);
            Nodo atual = tabela[indice];

            while (atual != null) {
                if (atual.valor == valor) {
                    return true;
                }
                atual = atual.proximo;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        TabelaHash hash = new TabelaHash(4);
        System.out.println("Capacidade antes do rehash: " + hash.capacidade());

        int[] valores = { 1, 2, 3, 4 };
        for (int valor : valores) {
            hash.inserir(valor);
        }

        boolean todosBuscaveis = true;
        for (int valor : valores) {
            todosBuscaveis = todosBuscaveis && hash.buscar(valor);
        }

        System.out.println("Capacidade depois do rehash: " + hash.capacidade());
        System.out.println("Todos os valores continuam buscaveis: " + todosBuscaveis);
        System.out.println("Resultado esperado: 8 / true");
    }
}
