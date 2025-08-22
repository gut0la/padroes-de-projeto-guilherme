package comportamentais.chainofresponsibility.compadrao.handlers;

import comportamentais.chainofresponsibility.compadrao.classes.HandlerBase;

/**
 * Handler concreto para aprovação de despesas pelo Gerente
 * Pode aprovar despesas até R$ 5.000
 */
public class GerenteHandler extends HandlerBase {
    
    private static final double LIMITE_APROVACAO = 5000.0;
    
    @Override
    protected boolean podeProcessar(double valor) {
        return valor <= LIMITE_APROVACAO;
    }
    
    @Override
    protected void aprovar(String item, double valor) {
        System.out.println("APROVADO pelo Gerente");
        System.out.println("Motivo: Valor R$ " + valor + " dentro do limite (até R$ " + LIMITE_APROVACAO + ")");
        registrarLog(item, valor, "APROVADO - GERENTE");
    }
}