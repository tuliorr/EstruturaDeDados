/**
 * Questão 03 - Verificar um max-heap.
 *
 * Confere se todo pai é maior ou igual aos seus filhos no vetor recebido.
 */
public class Questao03VerificarMaxHeap {

    public static boolean ehMaxHeap(int[] heap) {
        for (int pai = 0; pai < heap.length / 2; pai++) {
            int esquerdo = 2 * pai + 1;
            int direito = 2 * pai + 2;

            if (heap[pai] < heap[esquerdo]) {
                return false;
            }
            if (direito < heap.length && heap[pai] < heap[direito]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] valido = { 20, 15, 18, 7, 9, 10 };
        int[] invalido = { 20, 22, 18 };

        System.out.println("Primeiro vetor é Max-Heap: " + ehMaxHeap(valido));
        System.out.println("Segundo vetor é Max-Heap: " + ehMaxHeap(invalido));
        System.out.println("Resultado esperado: true / false");
    }
}
