package comportamentais.visitor.compadrao;

/**
 * Visitor concreto para gerar relatórios de desempenho
 * 
 * Vantagens:
 * - Centraliza toda a lógica de avaliação de desempenho
 * - Permite diferentes critérios de avaliação por tipo de funcionário
 * - Facilita modificação dos critérios sem afetar outras operações
 * - Reutilizável em diferentes contextos (avaliações, promoções, etc.)
 * - Testável independentemente
 */
public class RelatorioDesempenhoVisitor implements FuncionarioVisitor {
    
    private StringBuilder relatorio;
    
    public RelatorioDesempenhoVisitor() {
        this.relatorio = new StringBuilder();
    }
    
    @Override
    public void visitDesenvolvedor(Desenvolvedor desenvolvedor) {
        double produtividade = calcularProdutividadeDesenvolvedor(desenvolvedor);
        String nivel = determinarNivelDesempenho(produtividade, "desenvolvedor");
        String feedback = gerarFeedbackDesenvolvedor(desenvolvedor, produtividade);
        
        relatorio.append(String.format(
            "=== RELATÓRIO DE DESEMPENHO - DESENVOLVEDOR ===\n" +
            "Nome: %s\n" +
            "Métricas de Produtividade:\n" +
            "  - Linhas de Código: %d\n" +
            "  - Bugs Corrigidos: %d\n" +
            "  - Pontuação de Código: %.1f\n" +
            "  - Pontuação de Correções: %.1f\n" +
            "Produtividade Total: %.2f pontos\n" +
            "Nível de Desempenho: %s\n" +
            "Feedback: %s\n",
            desenvolvedor.getNome(),
            desenvolvedor.getLinhasCodigo(),
            desenvolvedor.getBugsCorrigidos(),
            desenvolvedor.getLinhasCodigo() * 0.1,
            desenvolvedor.getBugsCorrigidos() * 0.5,
            produtividade,
            nivel,
            feedback
        ));
    }
    
    @Override
    public void visitGerente(Gerente gerente) {
        double eficiencia = calcularEficienciaGerente(gerente);
        String nivel = determinarNivelDesempenho(eficiencia, "gerente");
        String feedback = gerarFeedbackGerente(gerente, eficiencia);
        
        relatorio.append(String.format(
            "=== RELATÓRIO DE DESEMPENHO - GERENTE ===\n" +
            "Nome: %s\n" +
            "Métricas de Gestão:\n" +
            "  - Tamanho da Equipe: %d pessoas\n" +
            "  - Projetos Concluídos: %d\n" +
            "  - Pontuação de Liderança: %.1f\n" +
            "  - Pontuação de Entrega: %.1f\n" +
            "Eficiência Total: %.2f pontos\n" +
            "Nível de Desempenho: %s\n" +
            "Feedback: %s\n",
            gerente.getNome(),
            gerente.getEquipeSize(),
            gerente.getProjetosConcluidos(),
            gerente.getEquipeSize() * 2.0,
            gerente.getProjetosConcluidos() * 10.0,
            eficiencia,
            nivel,
            feedback
        ));
    }
    
    @Override
    public void visitEstagiario(Estagiario estagiario) {
        double dedicacao = calcularDedicacaoEstagiario(estagiario);
        String nivel = determinarNivelDesempenho(dedicacao, "estagiario");
        String feedback = gerarFeedbackEstagiario(estagiario, dedicacao);
        
        relatorio.append(String.format(
            "=== RELATÓRIO DE DESEMPENHO - ESTAGIÁRIO ===\n" +
            "Nome: %s\n" +
            "Métricas de Aprendizado:\n" +
            "  - Horas de Estudo: %d\n" +
            "  - Tarefas Concluídas: %d\n" +
            "  - Pontuação de Estudo: %.1f\n" +
            "  - Pontuação de Execução: %.1f\n" +
            "Dedicação Total: %.2f pontos\n" +
            "Nível de Desempenho: %s\n" +
            "Feedback: %s\n",
            estagiario.getNome(),
            estagiario.getHorasEstudo(),
            estagiario.getTarefasConcluidas(),
            estagiario.getHorasEstudo() * 0.5,
            estagiario.getTarefasConcluidas() * 2.0,
            dedicacao,
            nivel,
            feedback
        ));
    }
    
    // Métodos auxiliares para cálculos específicos
    private double calcularProdutividadeDesenvolvedor(Desenvolvedor dev) {
        return (dev.getLinhasCodigo() * 0.1) + (dev.getBugsCorrigidos() * 0.5);
    }
    
    private double calcularEficienciaGerente(Gerente gerente) {
        return (gerente.getProjetosConcluidos() * 10.0) + (gerente.getEquipeSize() * 2.0);
    }
    
    private double calcularDedicacaoEstagiario(Estagiario estagiario) {
        return (estagiario.getHorasEstudo() * 0.5) + (estagiario.getTarefasConcluidas() * 2.0);
    }
    
    // Método para determinar nível baseado no tipo e pontuação
    private String determinarNivelDesempenho(double pontuacao, String tipo) {
        switch (tipo.toLowerCase()) {
            case "desenvolvedor":
                if (pontuacao >= 100) return "🌟 Excelente";
                else if (pontuacao >= 70) return "✅ Bom";
                else if (pontuacao >= 40) return "⚠️ Regular";
                else return "❌ Insatisfatório";
                
            case "gerente":
                if (pontuacao >= 80) return "🌟 Excelente";
                else if (pontuacao >= 60) return "✅ Bom";
                else if (pontuacao >= 40) return "⚠️ Regular";
                else return "❌ Insatisfatório";
                
            case "estagiario":
                if (pontuacao >= 50) return "🌟 Excelente";
                else if (pontuacao >= 35) return "✅ Bom";
                else if (pontuacao >= 20) return "⚠️ Regular";
                else return "❌ Insatisfatório";
                
            default:
                return "Não Avaliado";
        }
    }
    
    // Métodos para gerar feedback personalizado
    private String gerarFeedbackDesenvolvedor(Desenvolvedor dev, double produtividade) {
        if (produtividade >= 100) {
            return "Excelente produtividade! Continue mantendo a qualidade do código e a eficiência na correção de bugs.";
        } else if (produtividade >= 70) {
            return "Bom desempenho geral. Considere focar em aumentar a produtividade de código ou melhorar a detecção de bugs.";
        } else if (produtividade >= 40) {
            return "Desempenho regular. Recomenda-se treinamento adicional e mentoria para melhorar a produtividade.";
        } else {
            return "Desempenho abaixo do esperado. É necessário um plano de desenvolvimento individual urgente.";
        }
    }
    
    private String gerarFeedbackGerente(Gerente gerente, double eficiencia) {
        if (eficiencia >= 80) {
            return "Excelente liderança e gestão de projetos! Equipe bem gerenciada com alta taxa de entrega.";
        } else if (eficiencia >= 60) {
            return "Boa gestão geral. Considere estratégias para otimizar a entrega de projetos ou expandir a equipe.";
        } else if (eficiencia >= 40) {
            return "Gestão regular. Recomenda-se treinamento em liderança e metodologias de gestão de projetos.";
        } else {
            return "Gestão abaixo do esperado. É necessário suporte imediato e revisão das estratégias de liderança.";
        }
    }
    
    private String gerarFeedbackEstagiario(Estagiario estagiario, double dedicacao) {
        if (dedicacao >= 50) {
            return "Excelente dedicação aos estudos e execução de tarefas! Continue assim para um desenvolvimento acelerado.";
        } else if (dedicacao >= 35) {
            return "Boa dedicação geral. Tente equilibrar melhor o tempo entre estudos teóricos e prática.";
        } else if (dedicacao >= 20) {
            return "Dedicação regular. Recomenda-se maior foco nos estudos e busca por mais oportunidades práticas.";
        } else {
            return "Dedicação abaixo do esperado. É necessário maior comprometimento com o programa de estágio.";
        }
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