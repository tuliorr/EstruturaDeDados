/**
 * Questão 08 - Aplicar Desconto em Produto.
 *
 * Mostra que duas variáveis podem guardar referências para o mesmo objeto e,
 * por isso, observar a mesma alteração feita no produto.
 */
public class Questao08AplicarDescontoProduto {

    public static class Produto {
        private double preco;

        public Produto(double preco) {
            this.preco = preco;
        }

        public double getPreco() {
            return preco;
        }
    }

    public static void aplicarDesconto(Produto produto, double percentual) {
        produto.preco -= produto.preco * percentual / 100.0;
    }

    public static void main(String[] args) {
        Produto produto = new Produto(200.0);
        Produto outraReferencia = produto;

        aplicarDesconto(produto, 10.0);

        System.out.println("Preço pela primeira referência: " + produto.getPreco());
        System.out.println("Preço pela segunda referência: " + outraReferencia.getPreco());
        System.out.println("Resultado esperado: 180.0 nas duas referências");
    }
}
