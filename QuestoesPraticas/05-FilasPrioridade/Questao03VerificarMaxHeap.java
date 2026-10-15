/**
 * Questão 03 - Verificar um max-heap.
 *
 * Confere se todo pai é maior ou igual aos seus filhos no vetor recebido.
 */
public class Questao03VerificarMaxHeap {

    public static boolean ehMaxHeap(int[] heap) {
        for (int pai = 0; pai < heap.length / 2; pai++) {
            int indiceEsquerdo = filhoEsquerdo(pai);
            int indiceDireito = filhoDireito(pai);

            if (heap[pai] < heap[indiceEsquerdo]) {
                return false;
            }
            if (indiceDireito < heap.length && heap[pai] < heap[indiceDireito]) {
                return false;
            }
        }
        return true;
    }

    private static int filhoEsquerdo(int indice) {
        return 2 * indice + 1;
    }

    private static int filhoDireito(int indice) {
        return 2 * indice + 2;
    }

    public static void main(String[] args) {
        int[] valido = { 20, 15, 18, 7, 9, 10 };
        int[] invalido = { 20, 22, 18 };

        System.out.println("Primeiro vetor é Max-Heap: " + ehMaxHeap(valido));
        System.out.println("Segundo vetor é Max-Heap: " + ehMaxHeap(invalido));
        System.out.println("Resultado esperado: true / false");
    }
}
