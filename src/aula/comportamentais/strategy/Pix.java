package aula.comportamentais.strategy;

public class Pix implements MetodoPagamento {
    @Override
    public void pagar(double valor)
    {
        System.out.println
        ("Pagamento de R$ " + valor + " realizado via PIX");
    }
}