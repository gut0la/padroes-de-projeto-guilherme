package aula.comportamentais.strategy;

public class Principal {
    public static void main(String[] args) {
        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.setMetodoPagamento(new CartaoCredito());
        carrinho.finalizarCompra(150.00);
        carrinho.setMetodoPagamento(new CartaoDebito());
        carrinho.finalizarCompra(89.90);
        carrinho.setMetodoPagamento(new Pix());
        carrinho.finalizarCompra(250.75);
    }
}
