import java.util.Arrays;

import Unidade2.P04FilasPrioridade.FilaPrioridadeHeap;

/**
 * Questão 06 - Selecionar os k Pontos Mais Próximos.
 *
 * Mantém um max-heap limitado: sua raiz é o mais distante entre os selecionados.
 */
public class Questao06PontosMaisProximos {

    static class Ponto implements Comparable<Ponto> {
        private int x;
        private int y;

        Ponto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        long distanciaAoQuadrado() {
            return (long) x * x + (long) y * y;
        }

        @Override
        public int compareTo(Ponto outro) {
            return Long.compare(distanciaAoQuadrado(), outro.distanciaAoQuadrado());
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public static Ponto[] maisProximos(Ponto[] pontos, int k) {
        if (k == 0) {
            return new Ponto[0];
        }

        FilaPrioridadeHeap<Ponto> selecionados = new FilaPrioridadeHeap<>(k);
        for (Ponto ponto : pontos) {
            if (selecionados.tamanho() < k) {
                selecionados.enfileirar(ponto);
            } else if (ponto.compareTo(selecionados.consultarFrente()) < 0) {
                selecionados.desenfileirar();
                selecionados.enfileirar(ponto);
            }
        }

        Ponto[] resultado = new Ponto[k];
        for (int i = k - 1; i >= 0; i--) {
            resultado[i] = selecionados.desenfileirar();
        }
        return resultado;
    }

    public static void main(String[] args) {
        Ponto[] pontos = {
                new Ponto(1, 1), new Ponto(4, 0), new Ponto(2, 2),
                new Ponto(0, 3), new Ponto(1, 2)
        };

        System.out.println("Mais próximos: " + Arrays.toString(maisProximos(pontos, 3)));
        System.out.println("Resultado esperado: [(1,1), (1,2), (2,2)]");
    }
}
