package comportamentais.visitor.compadrao;

/**
 * Visitor concreto para gerar relatórios tributários
 * 
 * Vantagens:
 * - Centraliza toda a lógica tributária em uma única classe
 * - Facilita manutenção quando há mudanças na legislação
 * - Permite diferentes estratégias de cálculo por tipo de funcionário
 * - Reutilizável para diferentes tipos de relatórios fiscais
 * - Elimina duplicação de código tributário
 */
public class RelatorioTributarioVisitor implements FuncionarioVisitor {
    
    private StringBuilder relatorio;
    
    // Configurações tributárias centralizadas
    private static final double ALIQUOTA_BAIXA = 0.05;   // 5% até R$ 2.000
    private static final double ALIQUOTA_MEDIA = 0.10;   // 10% de R$ 2.001 até R$ 5.000
    private static final double ALIQUOTA_ALTA = 0.15;    // 15% acima de R$ 5.000
    private static final double LIMITE_BAIXO = 2000.0;
    private static final double LIMITE_MEDIO = 5000.0;
    
    // Taxas específicas por tipo de funcionário
    private static final double TAXA_DESENVOLVEDOR = 0.02; // Taxa adicional para desenvolvedores
    private static final double DESCONTO_ESTAGIARIO = 0.50; // 50% de desconto para estagiários
    private static final double TAXA_GERENTE = 0.03; // Taxa adicional para gerentes
    
    public RelatorioTributarioVisitor() {
        this.relatorio = new StringBuilder();
    }
    
    @Override
    public void visitDesenvolvedor(Desenvolvedor desenvolvedor) {
        double salarioBase = desenvolvedor.getSalarioBase();
        double bonus = calcularBonusDesenvolvedor(desenvolvedor);
        double salarioBruto = salarioBase + bonus;
        
        double impostoBase = calcularImpostoBase(salarioBruto);
        double taxaAdicional = salarioBruto * TAXA_DESENVOLVEDOR;
        double impostoTotal = impostoBase + taxaAdicional;
        double salarioLiquido = salarioBruto - impostoTotal;
        
        relatorio.append(String.format(
            "=== RELATÓRIO TRIBUTÁRIO - DESENVOLVEDOR ===\n" +
            "Nome: %s\n" +
            "Composição Salarial:\n" +
            "  - Salário Base: R$ %.2f\n" +
            "  - Bônus por Performance: R$ %.2f\n" +
            "  - Salário Bruto: R$ %.2f\n" +
            "\nCálculo Tributário:\n" +
            "  - Imposto Base: R$ %.2f (%.1f%%)\n" +
            "  - Taxa Profissional: R$ %.2f (%.1f%%)\n" +
            "  - Total de Impostos: R$ %.2f\n" +
            "  - Salário Líquido: R$ %.2f\n" +
            "\nFaixa Tributária: %s\n",
            desenvolvedor.getNome(),
            salarioBase,
            bonus,
            salarioBruto,
            impostoBase,
            (impostoBase / salarioBruto) * 100,
            taxaAdicional,
            TAXA_DESENVOLVEDOR * 100,
            impostoTotal,
            salarioLiquido,
            determinarFaixaTributaria(salarioBruto)
        ));
    }
    
    @Override
    public void visitGerente(Gerente gerente) {
        double salarioBase = gerente.getSalarioBase();
        double bonus = calcularBonusGerente(gerente);
        double salarioBruto = salarioBase + bonus;
        
        double impostoBase = calcularImpostoBase(salarioBruto);
        double taxaAdicional = salarioBruto * TAXA_GERENTE;
        double impostoTotal = impostoBase + taxaAdicional;
        double salarioLiquido = salarioBruto - impostoTotal;
        
        relatorio.append(String.format(
            "=== RELATÓRIO TRIBUTÁRIO - GERENTE ===\n" +
            "Nome: %s\n" +
            "Composição Salarial:\n" +
            "  - Salário Base: R$ %.2f\n" +
            "  - Bônus por Gestão: R$ %.2f\n" +
            "  - Salário Bruto: R$ %.2f\n" +
            "\nCálculo Tributário:\n" +
            "  - Imposto Base: R$ %.2f (%.1f%%)\n" +
            "  - Taxa de Liderança: R$ %.2f (%.1f%%)\n" +
            "  - Total de Impostos: R$ %.2f\n" +
            "  - Salário Líquido: R$ %.2f\n" +
            "\nFaixa Tributária: %s\n",
            gerente.getNome(),
            salarioBase,
            bonus,
            salarioBruto,
            impostoBase,
            (impostoBase / salarioBruto) * 100,
            taxaAdicional,
            TAXA_GERENTE * 100,
            impostoTotal,
            salarioLiquido,
            determinarFaixaTributaria(salarioBruto)
        ));
    }
    
    @Override
    public void visitEstagiario(Estagiario estagiario) {
        double salarioBase = estagiario.getSalarioBase();
        double bonus = calcularBonusEstagiario(estagiario);
        double salarioBruto = salarioBase + bonus;
        
        double impostoBase = calcularImpostoBase(salarioBruto);
        double desconto = impostoBase * DESCONTO_ESTAGIARIO; // Desconto para estagiários
        double impostoTotal = impostoBase - desconto;
        double salarioLiquido = salarioBruto - impostoTotal;
        
        relatorio.append(String.format(
            "=== RELATÓRIO TRIBUTÁRIO - ESTAGIÁRIO ===\n" +
            "Nome: %s\n" +
            "Composição Salarial:\n" +
            "  - Salário Base: R$ %.2f\n" +
            "  - Bônus por Aprendizado: R$ %.2f\n" +
            "  - Salário Bruto: R$ %.2f\n" +
            "\nCálculo Tributário:\n" +
            "  - Imposto Base: R$ %.2f (%.1f%%)\n" +
            "  - Desconto Estudantil: -R$ %.2f (%.1f%%)\n" +
            "  - Total de Impostos: R$ %.2f\n" +
            "  - Salário Líquido: R$ %.2f\n" +
            "\nFaixa Tributária: %s (com desconto estudantil)\n",
            estagiario.getNome(),
            salarioBase,
            bonus,
            salarioBruto,
            impostoBase,
            (impostoBase / salarioBruto) * 100,
            desconto,
            DESCONTO_ESTAGIARIO * 100,
            impostoTotal,
            salarioLiquido,
            determinarFaixaTributaria(salarioBruto)
        ));
    }
    
    // Métodos auxiliares centralizados para cálculos tributários
    
    /**
     * Calcula o imposto base baseado nas faixas tributárias
     */
    private double calcularImpostoBase(double salarioBruto) {
        if (salarioBruto <= LIMITE_BAIXO) {
            return salarioBruto * ALIQUOTA_BAIXA;
        } else if (salarioBruto <= LIMITE_MEDIO) {
            return salarioBruto * ALIQUOTA_MEDIA;
        } else {
            return salarioBruto * ALIQUOTA_ALTA;
        }
    }
    
    /**
     * Determina a faixa tributária baseada no salário bruto
     */
    private String determinarFaixaTributaria(double salarioBruto) {
        if (salarioBruto <= LIMITE_BAIXO) {
            return String.format("Faixa 1 - %.0f%% (até R$ %.0f)", ALIQUOTA_BAIXA * 100, LIMITE_BAIXO);
        } else if (salarioBruto <= LIMITE_MEDIO) {
            return String.format("Faixa 2 - %.0f%% (R$ %.0f a R$ %.0f)", ALIQUOTA_MEDIA * 100, LIMITE_BAIXO + 1, LIMITE_MEDIO);
        } else {
            return String.format("Faixa 3 - %.0f%% (acima de R$ %.0f)", ALIQUOTA_ALTA * 100, LIMITE_MEDIO);
        }
    }
    
    // Métodos para cálculo de bônus (reutilizados de outros visitors)
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
     * Gera um resumo tributário consolidado
     */
    public void gerarResumoTributario() {
        relatorio.append("\n" + "=".repeat(50) + "\n");
        relatorio.append("RESUMO DAS CONFIGURAÇÕES TRIBUTÁRIAS\n");
        relatorio.append("=".repeat(50) + "\n");
        relatorio.append(String.format(
            "Faixas de Imposto:\n" +
            "  - Até R$ %.0f: %.0f%%\n" +
            "  - R$ %.0f a R$ %.0f: %.0f%%\n" +
            "  - Acima de R$ %.0f: %.0f%%\n" +
            "\nTaxas Adicionais:\n" +
            "  - Desenvolvedores: +%.0f%%\n" +
            "  - Gerentes: +%.0f%%\n" +
            "  - Estagiários: -%.0f%% (desconto)\n",
            LIMITE_BAIXO, ALIQUOTA_BAIXA * 100,
            LIMITE_BAIXO + 1, LIMITE_MEDIO, ALIQUOTA_MEDIA * 100,
            LIMITE_MEDIO, ALIQUOTA_ALTA * 100,
            TAXA_DESENVOLVEDOR * 100,
            TAXA_GERENTE * 100,
            DESCONTO_ESTAGIARIO * 100
        ));
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