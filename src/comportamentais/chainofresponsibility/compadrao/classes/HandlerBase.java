package comportamentais.chainofresponsibility.compadrao.classes;

import comportamentais.chainofresponsibility.compadrao.interfaces.Handler;

/**
 * Classe base abstrata para implementação do padrão Chain of Responsibility
 * Contém a lógica comum de encadeamento
 */
public abstract class HandlerBase implements Handler {
    
    protected Handler proximoHandler;
    
    @Override
    public void setProximo(Handler proximoHandler) {
        this.proximoHandler = proximoHandler;
    }
    
    @Override
    public void processar(String item, double valor) {
        if (podeProcessar(valor)) {
            aprovar(item, valor);
        } else if (proximoHandler != null) {
            proximoHandler.processar(item, valor);
        } else {
            rejeitar(item, valor);
        }
    }
    
    /**
     * Verifica se este handler pode processar a solicitação
     * @param valor valor da despesa
     * @return true se pode processar, false caso contrário
     */
    protected abstract boolean podeProcessar(double valor);
    
    /**
     * Aprova a solicitação
     * @param item descrição do item
     * @param valor valor da despesa
     */
    protected abstract void aprovar(String item, double valor);
    
    /**
     * Rejeita a solicitação quando nenhum handler pode processar
     * @param item descrição do item
     * @param valor valor da despesa
     */
    protected void rejeitar(String item, double valor) {
        System.out.println("REJEITADO");
        System.out.println("Motivo: Valor R$ " + valor + " excede todos os limites de aprovação");
        registrarLog(item, valor, "REJEITADO");
    }
    
    /**
     * Registra log da operação
     * @param item descrição do item
     * @param valor valor da despesa
     * @param status status da aprovação
     */
    protected void registrarLog(String item, double valor, String status) {
        System.out.println("LOG: " + status + " - " + item + " - R$ " + valor);
    }
}