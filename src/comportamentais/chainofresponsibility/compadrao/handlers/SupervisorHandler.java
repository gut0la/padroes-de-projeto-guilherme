package comportamentais.chainofresponsibility.compadrao.handlers;

import comportamentais.chainofresponsibility.compadrao.classes.HandlerBase;

/**
 * Handler concreto para aprovação de despesas pelo Supervisor
 * Pode aprovar despesas até R$ 100
 */
public class SupervisorHandler extends HandlerBase {
    
    private static final double LIMITE_APROVACAO = 100.0;
    
    @Override
    protected boolean podeProcessar(double valor) {
        return valor <= LIMITE_APROVACAO;
    }
    
    @Override
    protected void aprovar(String item, double valor) {
        System.out.println("APROVADO pelo Supervisor");
        System.out.println("Motivo: Valor R$ " + valor + " dentro do limite (até R$ " + LIMITE_APROVACAO + ")");
        registrarLog(item, valor, "APROVADO - SUPERVISOR");
    }
}