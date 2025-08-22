package comportamentais.visitor.compadrao;

import java.util.*;

/**
 * Exemplo COM o padrão Visitor
 * 
 * Vantagens demonstradas:
 * 1. Separação clara entre dados e operações
 * 2. Facilidade para adicionar novas operações sem modificar classes existentes
 * 3. Centralização de lógica relacionada em visitors específicos
 * 4. Eliminação de código duplicado
 * 5. Princípio Open/Closed respeitado
 * 6. Double dispatch para polimorfismo avançado
 * 7. Reutilização de operações em diferentes contextos
 * 8. Facilidade de manutenção e teste
 */
public class SistemaRelatoriosComVisitor {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE RELATÓRIOS COM VISITOR PATTERN ===");
        System.out.println("\n✅ VANTAGENS DEMONSTRADAS:");
        System.out.println("1. Separação clara entre dados e operações");
        System.out.println("2. Facilidade para adicionar novas operações");
        System.out.println("3. Eliminação de código duplicado");
        System.out.println("4. Centralização de lógica relacionada");
        System.out.println("5. Princípio Open/Closed respeitado");
        System.out.println("6. Double dispatch implementado");
        System.out.println("7. Reutilização e manutenibilidade\n");
        
        // Criando funcionários - classes focam apenas nos dados
        List<Funcionario> funcionarios = Arrays.asList(
            new Desenvolvedor("Ana Silva", 5000.0, 1500, 25),
            new Gerente("Carlos Santos", 8000.0, 8, 12),
            new Estagiario("João Oliveira", 1200.0, 40, 15),
            new Desenvolvedor("Maria Costa", 6500.0, 2200, 18),
            new Gerente("Pedro Lima", 9500.0, 12, 8)
        );
        
        // Demonstrando o uso dos diferentes visitors
        demonstrarRelatoriosSalariais(funcionarios);
        demonstrarRelatoriosDesempenho(funcionarios);
        demonstrarRelatoriosTributarios(funcionarios);
        
        // Demonstrando facilidade de extensão
        demonstrarNovaOperacao(funcionarios);
        
        // Demonstrando reutilização de visitors
        demonstrarReutilizacaoVisitors(funcionarios);
        
        // Demonstrando vantagens do padrão
        demonstrarVantagensDoVisitor();
    }
    
    private static void demonstrarRelatoriosSalariais(List<Funcionario> funcionarios) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIOS SALARIAIS - USANDO VISITOR");
        System.out.println("=".repeat(60));
        
        RelatorioSalarioVisitor salarioVisitor = new RelatorioSalarioVisitor();
        
        for (Funcionario funcionario : funcionarios) {
            funcionario.aceitar(salarioVisitor); // Double dispatch!
            System.out.println(salarioVisitor.getRelatorio());
            System.out.println("-".repeat(40));
        }
    }
    
    private static void demonstrarRelatoriosDesempenho(List<Funcionario> funcionarios) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIOS DE DESEMPENHO - USANDO VISITOR");
        System.out.println("=".repeat(60));
        
        RelatorioDesempenhoVisitor desempenhoVisitor = new RelatorioDesempenhoVisitor();
        
        for (Funcionario funcionario : funcionarios) {
            funcionario.aceitar(desempenhoVisitor);
            System.out.println(desempenhoVisitor.getRelatorio());
            System.out.println("-".repeat(40));
        }
    }
    
    private static void demonstrarRelatoriosTributarios(List<Funcionario> funcionarios) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIOS TRIBUTÁRIOS - USANDO VISITOR");
        System.out.println("=".repeat(60));
        
        RelatorioTributarioVisitor tributarioVisitor = new RelatorioTributarioVisitor();
        
        for (Funcionario funcionario : funcionarios) {
            funcionario.aceitar(tributarioVisitor);
            System.out.println(tributarioVisitor.getRelatorio());
            System.out.println("-".repeat(40));
        }
        
        // Demonstrando funcionalidade adicional do visitor
        tributarioVisitor.gerarResumoTributario();
        System.out.println(tributarioVisitor.getRelatorio());
    }
    
    private static void demonstrarNovaOperacao(List<Funcionario> funcionarios) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("NOVA OPERAÇÃO - ESTATÍSTICAS (SEM MODIFICAR CLASSES EXISTENTES)");
        System.out.println("=".repeat(60));
        
        // Criando um novo visitor sem modificar as classes de funcionário!
        EstatisticasVisitor estatisticasVisitor = new EstatisticasVisitor();
        
        for (Funcionario funcionario : funcionarios) {
            funcionario.aceitar(estatisticasVisitor);
        }
        
        System.out.println(estatisticasVisitor.getEstatisticas());
    }
    
    private static void demonstrarReutilizacaoVisitors(List<Funcionario> funcionarios) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("REUTILIZAÇÃO DE VISITORS - RELATÓRIO CONSOLIDADO");
        System.out.println("=".repeat(60));
        
        // Reutilizando visitors para criar um relatório consolidado
        RelatorioSalarioVisitor salarioVisitor = new RelatorioSalarioVisitor();
        RelatorioDesempenhoVisitor desempenhoVisitor = new RelatorioDesempenhoVisitor();
        
        System.out.println("=== RELATÓRIO CONSOLIDADO POR FUNCIONÁRIO ===");
        
        for (Funcionario funcionario : funcionarios) {
            System.out.println("\n" + "*".repeat(50));
            System.out.println("FUNCIONÁRIO: " + funcionario.getNome().toUpperCase());
            System.out.println("*".repeat(50));
            
            // Aplicando múltiplos visitors no mesmo funcionário
            funcionario.aceitar(salarioVisitor);
            System.out.println(salarioVisitor.getRelatorio());
            
            funcionario.aceitar(desempenhoVisitor);
            System.out.println(desempenhoVisitor.getRelatorio());
        }
    }
    
    private static void demonstrarVantagensDoVisitor() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("VANTAGENS DO PADRÃO VISITOR DEMONSTRADAS");
        System.out.println("=".repeat(60));
        
        System.out.println("\n1. SEPARAÇÃO DE RESPONSABILIDADES:");
        System.out.println("   ✅ Classes de funcionário focam apenas nos dados");
        System.out.println("   ✅ Visitors focam apenas nas operações");
        System.out.println("   ✅ Cada visitor tem uma responsabilidade específica");
        
        System.out.println("\n2. PRINCÍPIO OPEN/CLOSED:");
        System.out.println("   ✅ Novas operações adicionadas sem modificar classes existentes");
        System.out.println("   ✅ EstatisticasVisitor criado sem tocar em Funcionario");
        System.out.println("   ✅ Sistema extensível para futuras operações");
        
        System.out.println("\n3. ELIMINAÇÃO DE DUPLICAÇÃO:");
        System.out.println("   ✅ Lógica tributária centralizada em um visitor");
        System.out.println("   ✅ Cálculos de bônus organizados por visitor");
        System.out.println("   ✅ Formatação de relatórios padronizada");
        
        System.out.println("\n4. DOUBLE DISPATCH:");
        System.out.println("   ✅ Polimorfismo duplo implementado");
        System.out.println("   ✅ Método correto chamado baseado em ambos os tipos");
        System.out.println("   ✅ Flexibilidade máxima na escolha de operações");
        
        System.out.println("\n5. REUTILIZAÇÃO E COMPOSIÇÃO:");
        System.out.println("   ✅ Visitors podem ser reutilizados em diferentes contextos");
        System.out.println("   ✅ Múltiplos visitors podem ser aplicados ao mesmo objeto");
        System.out.println("   ✅ Fácil criação de relatórios compostos");
        
        System.out.println("\n6. MANUTENIBILIDADE:");
        System.out.println("   ✅ Mudanças em operações isoladas em visitors específicos");
        System.out.println("   ✅ Testes unitários focados e independentes");
        System.out.println("   ✅ Debugging simplificado por responsabilidade");
        
        System.out.println("\n🎯 COMPARAÇÃO COM IMPLEMENTAÇÃO SEM VISITOR:");
        System.out.println("   ❌ Sem Visitor: Adicionar operação = modificar N classes");
        System.out.println("   ✅ Com Visitor: Adicionar operação = criar 1 visitor");
        System.out.println("   ❌ Sem Visitor: Lógica espalhada e duplicada");
        System.out.println("   ✅ Com Visitor: Lógica centralizada e reutilizável");
        System.out.println("   ❌ Sem Visitor: Violação do princípio Open/Closed");
        System.out.println("   ✅ Com Visitor: Princípio Open/Closed respeitado");
    }
}

/**
 * Exemplo de novo visitor criado sem modificar classes existentes
 * Demonstra a extensibilidade do padrão Visitor
 */
class EstatisticasVisitor implements FuncionarioVisitor {
    private int totalDesenvolvedores = 0;
    private int totalGerentes = 0;
    private int totalEstagiarios = 0;
    private double totalSalarios = 0.0;
    private int totalLinhasCodigo = 0;
    private int totalBugsCorrigidos = 0;
    private int totalProjetosConcluidos = 0;
    private int totalHorasEstudo = 0;
    
    @Override
    public void visitDesenvolvedor(Desenvolvedor desenvolvedor) {
        totalDesenvolvedores++;
        totalSalarios += desenvolvedor.getSalarioBase();
        totalLinhasCodigo += desenvolvedor.getLinhasCodigo();
        totalBugsCorrigidos += desenvolvedor.getBugsCorrigidos();
    }
    
    @Override
    public void visitGerente(Gerente gerente) {
        totalGerentes++;
        totalSalarios += gerente.getSalarioBase();
        totalProjetosConcluidos += gerente.getProjetosConcluidos();
    }
    
    @Override
    public void visitEstagiario(Estagiario estagiario) {
        totalEstagiarios++;
        totalSalarios += estagiario.getSalarioBase();
        totalHorasEstudo += estagiario.getHorasEstudo();
    }
    
    public String getEstatisticas() {
        int totalFuncionarios = totalDesenvolvedores + totalGerentes + totalEstagiarios;
        double salarioMedio = totalFuncionarios > 0 ? totalSalarios / totalFuncionarios : 0;
        
        return String.format(
            "=== ESTATÍSTICAS GERAIS ===\n" +
            "Total de Funcionários: %d\n" +
            "  - Desenvolvedores: %d (%.1f%%)\n" +
            "  - Gerentes: %d (%.1f%%)\n" +
            "  - Estagiários: %d (%.1f%%)\n" +
            "\nMétricas Financeiras:\n" +
            "  - Total em Salários: R$ %.2f\n" +
            "  - Salário Médio: R$ %.2f\n" +
            "\nMétricas de Produtividade:\n" +
            "  - Total Linhas de Código: %d\n" +
            "  - Total Bugs Corrigidos: %d\n" +
            "  - Total Projetos Concluídos: %d\n" +
            "  - Total Horas de Estudo: %d\n" +
            "\n📊 Esta operação foi adicionada SEM modificar as classes existentes!\n",
            totalFuncionarios,
            totalDesenvolvedores, totalFuncionarios > 0 ? (totalDesenvolvedores * 100.0 / totalFuncionarios) : 0,
            totalGerentes, totalFuncionarios > 0 ? (totalGerentes * 100.0 / totalFuncionarios) : 0,
            totalEstagiarios, totalFuncionarios > 0 ? (totalEstagiarios * 100.0 / totalFuncionarios) : 0,
            totalSalarios,
            salarioMedio,
            totalLinhasCodigo,
            totalBugsCorrigidos,
            totalProjetosConcluidos,
            totalHorasEstudo
        );
    }
}