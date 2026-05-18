package Unidade1;

import java.util.Arrays;

/**
 * Reune exemplos classicos de recursividade usados para observar o mesmo
 * padrao: definir um caso base simples e reduzir o problema ate chegar nele.
 */
public class Recursividade {

    // =========================
    // Somatorio
    // =========================

    /**
     * Soma os inteiros de 1 ate n. A intuicao e separar o problema em "n" mais a
     * soma dos valores menores que n.
     */
    public static int somarAteN(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + somarAteN(n - 1);
    }

    // =========================
    // Sequencia
    // =========================

    /**
     * Monta a sequencia de 0 ate n. A chamada recursiva vem antes da escrita para
     * que os menores valores aparecam primeiro.
     */
    public static String sequenciaCrescente(int n) {
        if (n < 0) {
            return "";
        }
        if (n == 0) {
            return "0";
        }
        return sequenciaCrescente(n - 1) + " " + n;
    }

    // =========================
    // Divisao inteira
    // =========================

    /**
     * Calcula a divisao inteira por subtracoes sucessivas. A parte recursiva
     * trabalha com valores positivos; o metodo publico trata o sinal antes.
     */
    public static int divisaoInteira(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("O divisor nao pode ser zero.");
        }

        int sinal = (dividendo < 0) ^ (divisor < 0) ? -1 : 1;
        int quociente = divisaoInteiraPositiva(Math.abs(dividendo), Math.abs(divisor));
        return sinal * quociente;
    }

    private static int divisaoInteiraPositiva(int dividendo, int divisor) {
        if (dividendo < divisor) {
            return 0;
        }
        return 1 + divisaoInteiraPositiva(dividendo - divisor, divisor);
    }

    // =========================
    // Potencia de dois
    // =========================

    /**
     * Calcula 2 elevado ao expoente. A cada chamada reduzimos o expoente e
     * deixamos uma multiplicacao por 2 pendente.
     */
    public static int potenciaDeDois(int expoente) {
        if (expoente < 0) {
            throw new IllegalArgumentException("O expoente deve ser maior ou igual a zero.");
        }
        if (expoente == 0) {
            return 1;
        }
        return 2 * potenciaDeDois(expoente - 1);
    }

    // =========================
    // Vetores
    // =========================

    /**
     * Soma um vetor considerando que a soma a partir de uma posicao e o valor
     * daquela posicao mais a soma do restante do vetor.
     */
    public static int somaVetor(int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            return 0;
        }
        return somaVetor(vetor, 0);
    }

    private static int somaVetor(int[] vetor, int posicao) {
        if (posicao == vetor.length) {
            return 0;
        }
        return vetor[posicao] + somaVetor(vetor, posicao + 1);
    }

    /**
     * Inverte o vetor trocando as extremidades. Depois da troca, o problema
     * restante e inverter o miolo do vetor.
     */
    public static void inverterVetor(int[] vetor) {
        if (vetor == null) {
            return;
        }
        inverterVetor(vetor, 0, vetor.length - 1);
    }

    private static void inverterVetor(int[] vetor, int inicio, int fim) {
        if (inicio >= fim) {
            return;
        }

        int auxiliar = vetor[inicio];
        vetor[inicio] = vetor[fim];
        vetor[fim] = auxiliar;

        inverterVetor(vetor, inicio + 1, fim - 1);
    }

    // =========================
    // MDC
    // =========================

    /**
     * Calcula o maximo divisor comum pelo algoritmo de Euclides. A intuicao e
     * substituir o par (a, b) por (b, resto), preservando o MDC.
     */
    public static int mdc(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (b == 0) {
            return a;
        }
        return mdc(b, a % b);
    }

    // =========================
    // Torre de Hanoi
    // =========================

    /**
     * Exibe os movimentos da Torre de Hanoi. Para mover n discos, movemos n-1
     * para o auxiliar, o maior para o destino e depois n-1 para o destino.
     */
    public static void torreHanoi(int discos, char origem, char auxiliar, char destino) {
        if (discos <= 0) {
            return;
        }

        torreHanoi(discos - 1, origem, destino, auxiliar);
        System.out.println("Mover disco de " + origem + " para " + destino);
        torreHanoi(discos - 1, auxiliar, origem, destino);
    }

    // =========================
    // Teste dos exemplos
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Exemplos de Recursividade ===");

        // Somatorio: mostra a reducao n + soma(n - 1).
        System.out.println("Somatorio de 1 ate 5: " + somarAteN(5));

        // Sequencia: a chamada recursiva antes da escrita organiza a ordem crescente.
        System.out.println("Sequencia crescente ate 6: " + sequenciaCrescente(6));

        // Divisao: 17 / 3 cabe cinco vezes quando subtraimos 3 repetidamente.
        System.out.println("Divisao inteira 17 / 3: " + divisaoInteira(17, 3));
        System.out.println("Divisao inteira -17 / 3: " + divisaoInteira(-17, 3));

        // Potencia: cada nivel da pilha de chamadas representa uma multiplicacao por 2.
        System.out.println("2 elevado a 6: " + potenciaDeDois(6));

        int[] valores = { 3, 2, 5, 4, 6 };
        System.out.println("Vetor original: " + Arrays.toString(valores));
        System.out.println("Soma do vetor: " + somaVetor(valores));

        // Inversao: as extremidades se encontram ate nao restar nada para trocar.
        inverterVetor(valores);
        System.out.println("Vetor invertido: " + Arrays.toString(valores));

        // MDC: exemplo classico do algoritmo de Euclides.
        System.out.println("MDC de 48 e 18: " + mdc(48, 18));

        // Hanoi: tres discos produzem poucos movimentos e deixam a estrategia visivel.
        System.out.println("Movimentos da Torre de Hanoi com 3 discos:");
        torreHanoi(3, 'A', 'B', 'C');
    }
}
