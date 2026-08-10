import Unidade2.P01Listas.ListaVetor;

/**
 * Questão 03 - Intercalar Listas Ordenadas.
 *
 * Combina duas listas crescentes em uma terceira lista também crescente, sem
 * alterar as listas recebidas.
 */
public class Questao03IntercalarListasOrdenadas {

    public static ListaVetor intercalarListasOrdenadas(ListaVetor primeira, ListaVetor segunda) {
        ListaVetor resultado = new ListaVetor(primeira.tamanho() + segunda.tamanho());
        int i = 0;
        int j = 0;

        while (i < primeira.tamanho() && j < segunda.tamanho()) {
            if (primeira.obtem(i) <= segunda.obtem(j)) {
                resultado.insereFinal(primeira.obtem(i));
                i++;
            } else {
                resultado.insereFinal(segunda.obtem(j));
                j++;
            }
        }

        while (i < primeira.tamanho()) {
            resultado.insereFinal(primeira.obtem(i));
            i++;
        }
        while (j < segunda.tamanho()) {
            resultado.insereFinal(segunda.obtem(j));
            j++;
        }
        return resultado;
    }

    public static void main(String[] args) {
        ListaVetor primeira = new ListaVetor(3);
        primeira.insereFinal(1);
        primeira.insereFinal(4);
        primeira.insereFinal(7);

        ListaVetor segunda = new ListaVetor(4);
        segunda.insereFinal(2);
        segunda.insereFinal(3);
        segunda.insereFinal(8);
        segunda.insereFinal(9);

        ListaVetor resultado = intercalarListasOrdenadas(primeira, segunda);
        System.out.println("Primeira: " + primeira);
        System.out.println("Segunda: " + segunda);
        System.out.println("Intercalada: " + resultado);
        System.out.println("Resultado esperado: [1, 2, 3, 4, 7, 8, 9]");
    }
}
