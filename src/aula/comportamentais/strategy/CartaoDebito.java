package aula.comportamentais.strategy;

public class CartaoDebito implements MetodoPagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado com Cartão de Débito");
    }
}