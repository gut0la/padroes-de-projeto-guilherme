package comportamentais.chainofresponsibility.compadrao.interfaces;

/**
 * Interface Handler do padrão Chain of Responsibility
 * Define o contrato para processamento de solicitações
 */
public interface Handler {
    
    /**
     * Define o próximo handler na cadeia
     * @param proximoHandler o próximo handler a ser chamado
     */
    void setProximo(Handler proximoHandler);
    
    /**
     * Processa a solicitação de despesa
     * @param item descrição do item
     * @param valor valor da despesa
     */
    void processar(String item, double valor);
}