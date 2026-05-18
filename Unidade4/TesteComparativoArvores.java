package Unidade4;

/**
 * Compara tres arvores recebendo a mesma sequencia de valores. O objetivo e
 * observar como a regra de insercao muda a forma e a altura da estrutura.
 */
public class TesteComparativoArvores {

    public static void main(String[] args) {
        System.out.println("=== Comparacao: Arvore Binaria x ABB x AVL ===");

        Integer[] valores = { 30, 20, 10, 25, 40, 50, 45 };

        ArvoreBinaria<Integer> arvoreBinaria = new ArvoreBinaria<>();
        ArvoreBinariaBusca<Integer> arvoreBusca = new ArvoreBinariaBusca<>();
        ArvoreAVL<Integer> arvoreAvl = new ArvoreAVL<>();

        for (Integer valor : valores) {
            // A arvore binaria comum nao ordena: aqui preenchemos por largura.
            arvoreBinaria.inserirEmLargura(valor);

            // A ABB organiza pela regra menor-esquerda/maior-direita.
            arvoreBusca.inserir(valor);

            // A AVL usa a mesma regra da ABB, mas rebalanceia depois das insercoes.
            arvoreAvl.inserir(valor);
        }

        System.out.print("Arvore binaria em largura: ");
        arvoreBinaria.imprimirEmLargura();
        System.out.println("Altura da arvore binaria: " + arvoreBinaria.altura());

        System.out.print("ABB em ordem: ");
        arvoreBusca.imprimirEmOrdem();
        System.out.print("ABB em largura: ");
        arvoreBusca.imprimirEmLargura();
        System.out.println("Altura da ABB: " + arvoreBusca.altura());

        System.out.print("AVL em ordem: ");
        arvoreAvl.imprimirEmOrdem();
        System.out.print("AVL em largura: ");
        arvoreAvl.imprimirEmLargura();
        System.out.println("Altura da AVL: " + arvoreAvl.altura());
    }
}
