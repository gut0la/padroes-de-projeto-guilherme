package comportamentais.visitor.compadrao;

/**
 * Visitor concreto para gerar relatórios salariais
 * 
 * Vantagens:
 * - Centraliza toda a lógica de relatórios salariais em uma classe
 * - Facilita manutenção e modificação da lógica de cálculo
 * - Permite reutilização da lógica em diferentes contextos
 * - Separa responsabilidades: dados vs operações
 * - Facilita testes unitários específicos
 */
public class RelatorioSalarioVisitor implements FuncionarioVisitor {
    
    private StringBuilder relatorio;
    
    public RelatorioSalarioVisitor() {
        this.relatorio = new StringBuilder();
    }
    
    @Override
    public void visitDesenvolvedor(Desenvolvedor desenvolvedor) {
        double bonus = calcularBonusDesenvolvedor(desenvolvedor);
        double salarioTotal = desenvolvedor.getSalarioBase() + bonus;
        
        relatorio.append(String.format(
            "=== RELATÓRIO SALARIAL - DESENVOLVEDOR ===\n" +
            "Nome: %s\n" +
            "Salário Base: R$ %.2f\n" +
            "Bônus por Performance: R$ %.2f\n" +
            "  - Linhas de Código (%d): R$ %.2f\n" +
            "  - Bugs Corrigidos (%d): R$ %.2f\n" +
            "Salário Total: R$ %.2f\n",
            desenvolvedor.getNome(),
            desenvolvedor.getSalarioBase(),
            bonus,
            desenvolvedor.getLinhasCodigo(),
            desenvolvedor.getLinhasCodigo() * 0.5,
            desenvolvedor.getBugsCorrigidos(),
            desenvolvedor.getBugsCorrigidos() * 2.0,
            salarioTotal
        ));
    }
    
    @Override
    public void visitGerente(Gerente gerente) {
        double bonus = calcularBonusGerente(gerente);
        double salarioTotal = gerente.getSalarioBase() + bonus;
        
        relatorio.append(String.format(
            "=== RELATÓRIO SALARIAL - GERENTE ===\n" +
            "Nome: %s\n" +
            "Salário Base: R$ %.2f\n" +
            "Bônus por Gestão: R$ %.2f\n" +
            "  - Equipe (%d pessoas): R$ %.2f\n" +
            "  - Projetos Concluídos (%d): R$ %.2f\n" +
            "Salário Total: R$ %.2f\n",
            gerente.getNome(),
            gerente.getSalarioBase(),
            bonus,
            gerente.getEquipeSize(),
            gerente.getEquipeSize() * 100.0,
            gerente.getProjetosConcluidos(),
            gerente.getProjetosConcluidos() * 500.0,
            salarioTotal
        ));
    }
    
    @Override
    public void visitEstagiario(Estagiario estagiario) {
        double bonus = calcularBonusEstagiario(estagiario);
        double salarioTotal = estagiario.getSalarioBase() + bonus;
        
        relatorio.append(String.format(
            "=== RELATÓRIO SALARIAL - ESTAGIÁRIO ===\n" +
            "Nome: %s\n" +
            "Salário Base: R$ %.2f\n" +
            "Bônus por Aprendizado: R$ %.2f\n" +
            "  - Horas de Estudo (%d): R$ %.2f\n" +
            "  - Tarefas Concluídas (%d): R$ %.2f\n" +
            "Salário Total: R$ %.2f\n",
            estagiario.getNome(),
            estagiario.getSalarioBase(),
            bonus,
            estagiario.getHorasEstudo(),
            estagiario.getHorasEstudo() * 1.0,
            estagiario.getTarefasConcluidas(),
            estagiario.getTarefasConcluidas() * 5.0,
            salarioTotal
        ));
    }
    
    // Métodos auxiliares para cálculo de bônus - centralizados e reutilizáveis
    private double calcularBonusDesenvolvedor(Desenvolvedor dev) {
        return (dev.getLinhasCodigo() * 0.5) + (dev.getBugsCorrigidos() * 2.0);
    }
    
    private double calcularBonusGerente(Gerente gerente) {
        return (gerente.getEquipeSize() * 100.0) + (gerente.getProjetosConcluidos() * 500.0);
    }
    
    private double calcularBonusEstagiario(Estagiario estagiario) {
        return (estagiario.getHorasEstudo() * 1.0) + (estagiario.getTarefasConcluidas() * 5.0);
    }
    
    /**
     * Retorna o relatório gerado e limpa o buffer interno
     * @return String contendo o relatório completo
     */
    public String getRelatorio() {
        String resultado = relatorio.toString();
        relatorio.setLength(0); // Limpa o buffer para próximo uso
        return resultado;
    }
    
    /**
     * Limpa o relatório atual
     */
    public void limparRelatorio() {
        relatorio.setLength(0);
    }
}