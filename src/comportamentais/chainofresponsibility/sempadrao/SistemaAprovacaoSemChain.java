package comportamentais.chainofresponsibility.sempadrao;

/**
 * Sistema de aprovação de despesas SEM o padrão Chain of Responsibility
 * 
 * PROBLEMAS:
 * - Lógica de aprovação centralizada e complexa
 * - Difícil de adicionar novos níveis de aprovação
 * - Código acoplado e difícil de manter
 * - Violação do princípio Single Responsibility
 */
public class SistemaAprovacaoSemChain {
    
    public static void main(String[] args) {
        SistemaAprovacaoSemChain sistema = new SistemaAprovacaoSemChain();
        
        // Testando diferentes valores de despesas
        sistema.processarDespesa("Canetas", 50.0);
        sistema.processarDespesa("Notebook", 2500.0);
        sistema.processarDespesa("Reforma escritório", 15000.0);
        sistema.processarDespesa("Aquisição empresa", 500000.0);
    }
    
    /**
     * Método centralizado que contém toda a lógica de aprovação
     * PROBLEMA: Violação do Single Responsibility Principle
     */
    public void processarDespesa(String item, double valor) {
        System.out.println("\n=== PROCESSANDO DESPESA ===");
        System.out.println("Item: " + item);
        System.out.println("Valor: R$ " + valor);
        
        // PROBLEMA: Lógica complexa e acoplada
        if (valor <= 100) {
            // Supervisor pode aprovar até R$ 100
            System.out.println("APROVADO pelo Supervisor");
            System.out.println("Motivo: Valor dentro do limite do supervisor");
            
        } else if (valor <= 5000) {
            // Gerente pode aprovar até R$ 5.000
            System.out.println("APROVADO pelo Gerente");
            System.out.println("Motivo: Valor dentro do limite do gerente");
            
        } else if (valor <= 50000) {
            // Diretor pode aprovar até R$ 50.000
            System.out.println("APROVADO pelo Diretor");
            System.out.println("Motivo: Valor dentro do limite do diretor");
            
        } else {
            // Valores acima de R$ 50.000 são rejeitados
            System.out.println("REJEITADO");
            System.out.println("Motivo: Valor excede limite máximo de aprovação");
        }
        
        // PROBLEMA: Lógica adicional misturada
        registrarLog(item, valor);
    }
    
    /**
     * PROBLEMA: Responsabilidades misturadas no mesmo método
     */
    private void registrarLog(String item, double valor) {
        System.out.println("LOG: Despesa registrada - " + item + " - R$ " + valor);
    }
    
    /*
     * PROBLEMAS DESTA ABORDAGEM:
     * 
     * 1. RIGIDEZ: Para adicionar um novo nível (ex: Presidente), 
     *    precisa modificar o método processarDespesa
     * 
     * 2. ACOPLAMENTO: Toda lógica está em um só lugar, 
     *    dificultando manutenção
     * 
     * 3. VIOLAÇÃO SRP: O método faz muitas coisas: 
     *    valida, aprova, registra log
     * 
     * 4. DIFICULDADE DE TESTE: Não é possível testar 
     *    cada nível de aprovação isoladamente
     * 
     * 5. CÓDIGO DUPLICADO: Lógica similar repetida 
     *    em cada condição
     */
}