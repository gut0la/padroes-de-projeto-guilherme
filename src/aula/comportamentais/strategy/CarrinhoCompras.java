package aula.comportamentais.strategy;

public class CarrinhoCompras {
    private MetodoPagamento metodoPagamento;
    
    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
    
    public void finalizarCompra(double valor) {
        metodoPagamento.pagar(valor);
    }
}