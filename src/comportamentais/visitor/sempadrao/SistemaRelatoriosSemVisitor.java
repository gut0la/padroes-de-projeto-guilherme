package comportamentais.visitor.sempadrao;

import java.util.*;

/**
 * Exemplo SEM o padrão Visitor
 * 
 * Problemas demonstrados:
 * 1. Violação do princípio Open/Closed
 * 2. Lógica de operações espalhada nas classes de dados
 * 3. Dificuldade para adicionar novas operações
 * 4. Mistura de responsabilidades
 * 5. Código duplicado e difícil de manter
 * 6. Acoplamento forte entre dados e operações
 */

// Classes que representam diferentes tipos de funcionários
abstract class Funcionario {
    protected String nome;
    protected double salarioBase;
    
    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }
    
    // PROBLEMA: Cada nova operação requer modificação em todas as classes
    public abstract String gerarRelatorioSalario();
    public abstract String gerarRelatorioDesempenho();
    public abstract String gerarRelatorioTributario();
    public abstract double calcularBonus();
    public abstract double calcularImpostos();
    
    // Getters
    public String getNome() { return nome; }
    public double getSalarioBase() { return salarioBase; }
}

class Desenvolvedor extends Funcionario {
    private int linhasCodigo;
    private int bugsCorrigidos;
    
    public Desenvolvedor(String nome, double salarioBase, int linhasCodigo, int bugsCorrigidos) {
        super(nome, salarioBase);
        this.linhasCodigo = linhasCodigo;
        this.bugsCorrigidos = bugsCorrigidos;
    }
    
    @Override
    public String gerarRelatorioSalario() {
        // PROBLEMA: Lógica de relatório misturada com dados
        return String.format("=== RELATÓRIO SALARIAL - DESENVOLVEDOR ===\n" +
                           "Nome: %s\n" +
                           "Salário Base: R$ %.2f\n" +
                           "Bônus por Performance: R$ %.2f\n" +
                           "Salário Total: R$ %.2f\n",
                           nome, salarioBase, calcularBonus(), salarioBase + calcularBonus());
    }
    
    @Override
    public String gerarRelatorioDesempenho() {
        // PROBLEMA: Cada classe precisa implementar todas as operações
        double produtividade = (linhasCodigo * 0.1) + (bugsCorrigidos * 0.5);
        String nivel;
        if (produtividade >= 100) nivel = "Excelente";
        else if (produtividade >= 70) nivel = "Bom";
        else if (produtividade >= 40) nivel = "Regular";
        else nivel = "Insatisfatório";
        
        return String.format("=== RELATÓRIO DE DESEMPENHO - DESENVOLVEDOR ===\n" +
                           "Nome: %s\n" +
                           "Linhas de Código: %d\n" +
                           "Bugs Corrigidos: %d\n" +
                           "Produtividade: %.2f\n" +
                           "Nível: %s\n",
                           nome, linhasCodigo, bugsCorrigidos, produtividade, nivel);
    }
    
    @Override
    public String gerarRelatorioTributario() {
        // PROBLEMA: Código duplicado entre classes
        double impostos = calcularImpostos();
        double salarioLiquido = (salarioBase + calcularBonus()) - impostos;
        
        return String.format("=== RELATÓRIO TRIBUTÁRIO - DESENVOLVEDOR ===\n" +
                           "Nome: %s\n" +
                           "Salário Bruto: R$ %.2f\n" +
                           "Impostos: R$ %.2f\n" +
                           "Salário Líquido: R$ %.2f\n",
                           nome, salarioBase + calcularBonus(), impostos, salarioLiquido);
    }
    
    @Override
    public double calcularBonus() {
        // PROBLEMA: Lógica de cálculo espalhada
        return (linhasCodigo * 0.5) + (bugsCorrigidos * 2.0);
    }
    
    @Override
    public double calcularImpostos() {
        double salarioTotal = salarioBase + calcularBonus();
        if (salarioTotal <= 2000) return salarioTotal * 0.05;
        else if (salarioTotal <= 5000) return salarioTotal * 0.10;
        else return salarioTotal * 0.15;
    }
    
    // Getters específicos
    public int getLinhasCodigo() { return linhasCodigo; }
    public int getBugsCorrigidos() { return bugsCorrigidos; }
}

class Gerente extends Funcionario {
    private int equipeSize;
    private int projetosConcluidos;
    
    public Gerente(String nome, double salarioBase, int equipeSize, int projetosConcluidos) {
        super(nome, salarioBase);
        this.equipeSize = equipeSize;
        this.projetosConcluidos = projetosConcluidos;
    }
    
    @Override
    public String gerarRelatorioSalario() {
        // PROBLEMA: Código similar ao Desenvolvedor, mas não reutilizável
        return String.format("=== RELATÓRIO SALARIAL - GERENTE ===\n" +
                           "Nome: %s\n" +
                           "Salário Base: R$ %.2f\n" +
                           "Bônus por Gestão: R$ %.2f\n" +
                           "Salário Total: R$ %.2f\n",
                           nome, salarioBase, calcularBonus(), salarioBase + calcularBonus());
    }
    
    @Override
    public String gerarRelatorioDesempenho() {
        double eficiencia = (projetosConcluidos * 10.0) + (equipeSize * 2.0);
        String nivel;
        if (eficiencia >= 80) nivel = "Excelente";
        else if (eficiencia >= 60) nivel = "Bom";
        else if (eficiencia >= 40) nivel = "Regular";
        else nivel = "Insatisfatório";
        
        return String.format("=== RELATÓRIO DE DESEMPENHO - GERENTE ===\n" +
                           "Nome: %s\n" +
                           "Tamanho da Equipe: %d\n" +
                           "Projetos Concluídos: %d\n" +
                           "Eficiência: %.2f\n" +
                           "Nível: %s\n",
                           nome, equipeSize, projetosConcluidos, eficiencia, nivel);
    }
    
    @Override
    public String gerarRelatorioTributario() {
        // PROBLEMA: Código quase idêntico ao Desenvolvedor
        double impostos = calcularImpostos();
        double salarioLiquido = (salarioBase + calcularBonus()) - impostos;
        
        return String.format("=== RELATÓRIO TRIBUTÁRIO - GERENTE ===\n" +
                           "Nome: %s\n" +
                           "Salário Bruto: R$ %.2f\n" +
                           "Impostos: R$ %.2f\n" +
                           "Salário Líquido: R$ %.2f\n",
                           nome, salarioBase + calcularBonus(), impostos, salarioLiquido);
    }
    
    @Override
    public double calcularBonus() {
        return (equipeSize * 100.0) + (projetosConcluidos * 500.0);
    }
    
    @Override
    public double calcularImpostos() {
        // PROBLEMA: Código duplicado - mesma lógica do Desenvolvedor
        double salarioTotal = salarioBase + calcularBonus();
        if (salarioTotal <= 2000) return salarioTotal * 0.05;
        else if (salarioTotal <= 5000) return salarioTotal * 0.10;
        else return salarioTotal * 0.15;
    }
    
    // Getters específicos
    public int getEquipeSize() { return equipeSize; }
    public int getProjetosConcluidos() { return projetosConcluidos; }
}

class Estagiario extends Funcionario {
    private int horasEstudo;
    private int tarefasConcluidas;
    
    public Estagiario(String nome, double salarioBase, int horasEstudo, int tarefasConcluidas) {
        super(nome, salarioBase);
        this.horasEstudo = horasEstudo;
        this.tarefasConcluidas = tarefasConcluidas;
    }
    
    @Override
    public String gerarRelatorioSalario() {
        // PROBLEMA: Mais código duplicado
        return String.format("=== RELATÓRIO SALARIAL - ESTAGIÁRIO ===\n" +
                           "Nome: %s\n" +
                           "Salário Base: R$ %.2f\n" +
                           "Bônus por Aprendizado: R$ %.2f\n" +
                           "Salário Total: R$ %.2f\n",
                           nome, salarioBase, calcularBonus(), salarioBase + calcularBonus());
    }
    
    @Override
    public String gerarRelatorioDesempenho() {
        double dedicacao = (horasEstudo * 0.5) + (tarefasConcluidas * 2.0);
        String nivel;
        if (dedicacao >= 50) nivel = "Excelente";
        else if (dedicacao >= 35) nivel = "Bom";
        else if (dedicacao >= 20) nivel = "Regular";
        else nivel = "Insatisfatório";
        
        return String.format("=== RELATÓRIO DE DESEMPENHO - ESTAGIÁRIO ===\n" +
                           "Nome: %s\n" +
                           "Horas de Estudo: %d\n" +
                           "Tarefas Concluídas: %d\n" +
                           "Dedicação: %.2f\n" +
                           "Nível: %s\n",
                           nome, horasEstudo, tarefasConcluidas, dedicacao, nivel);
    }
    
    @Override
    public String gerarRelatorioTributario() {
        // PROBLEMA: Mesmo código tributário repetido
        double impostos = calcularImpostos();
        double salarioLiquido = (salarioBase + calcularBonus()) - impostos;
        
        return String.format("=== RELATÓRIO TRIBUTÁRIO - ESTAGIÁRIO ===\n" +
                           "Nome: %s\n" +
                           "Salário Bruto: R$ %.2f\n" +
                           "Impostos: R$ %.2f\n" +
                           "Salário Líquido: R$ %.2f\n",
                           nome, salarioBase + calcularBonus(), impostos, salarioLiquido);
    }
    
    @Override
    public double calcularBonus() {
        return (horasEstudo * 1.0) + (tarefasConcluidas * 5.0);
    }
    
    @Override
    public double calcularImpostos() {
        // PROBLEMA: Código tributário idêntico em todas as classes
        double salarioTotal = salarioBase + calcularBonus();
        if (salarioTotal <= 2000) return salarioTotal * 0.05;
        else if (salarioTotal <= 5000) return salarioTotal * 0.10;
        else return salarioTotal * 0.15;
    }
    
    // Getters específicos
    public int getHorasEstudo() { return horasEstudo; }
    public int getTarefasConcluidas() { return tarefasConcluidas; }
}

// Classe principal para demonstrar os problemas
public class SistemaRelatoriosSemVisitor {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE RELATÓRIOS SEM VISITOR PATTERN ===");
        System.out.println("\n⚠️  PROBLEMAS DEMONSTRADOS:");
        System.out.println("1. Violação do princípio Open/Closed");
        System.out.println("2. Lógica de operações espalhada nas classes");
        System.out.println("3. Código duplicado (especialmente cálculo de impostos)");
        System.out.println("4. Dificuldade para adicionar novas operações");
        System.out.println("5. Mistura de responsabilidades");
        System.out.println("6. Acoplamento forte entre dados e operações\n");
        
        // Criando funcionários
        List<Funcionario> funcionarios = Arrays.asList(
            new Desenvolvedor("Ana Silva", 5000.0, 1500, 25),
            new Gerente("Carlos Santos", 8000.0, 8, 12),
            new Estagiario("João Oliveira", 1200.0, 40, 15)
        );
        
        // PROBLEMA: Para cada nova operação, precisamos modificar todas as classes
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIOS SALARIAIS");
        System.out.println("=".repeat(60));
        
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.gerarRelatorioSalario());
            System.out.println("-".repeat(40));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIOS DE DESEMPENHO");
        System.out.println("=".repeat(60));
        
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.gerarRelatorioDesempenho());
            System.out.println("-".repeat(40));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIOS TRIBUTÁRIOS");
        System.out.println("=".repeat(60));
        
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.gerarRelatorioTributario());
            System.out.println("-".repeat(40));
        }
        
        // PROBLEMA: Se quisermos adicionar um novo tipo de relatório,
        // precisamos modificar TODAS as classes de funcionário
        System.out.println("\n⚠️  PARA ADICIONAR UM NOVO RELATÓRIO:");
        System.out.println("- Modificar classe abstrata Funcionario");
        System.out.println("- Implementar método em Desenvolvedor");
        System.out.println("- Implementar método em Gerente");
        System.out.println("- Implementar método em Estagiario");
        System.out.println("- Repetir para cada nova classe de funcionário");
        System.out.println("\n❌ VIOLAÇÃO DO PRINCÍPIO OPEN/CLOSED!");
        
        demonstrarProblemasEspecificos();
    }
    
    private static void demonstrarProblemasEspecificos() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PROBLEMAS ESPECÍFICOS IDENTIFICADOS");
        System.out.println("=".repeat(60));
        
        System.out.println("\n1. CÓDIGO DUPLICADO:");
        System.out.println("   - Lógica de cálculo de impostos repetida 3 vezes");
        System.out.println("   - Estrutura de relatórios similar em todas as classes");
        System.out.println("   - Formatação de saída duplicada");
        
        System.out.println("\n2. VIOLAÇÃO DE RESPONSABILIDADES:");
        System.out.println("   - Classes de dados fazendo formatação");
        System.out.println("   - Lógica de negócio misturada com apresentação");
        System.out.println("   - Cálculos espalhados pelas classes");
        
        System.out.println("\n3. DIFICULDADE DE MANUTENÇÃO:");
        System.out.println("   - Mudança na lógica de impostos requer 3 alterações");
        System.out.println("   - Novo formato de relatório requer N alterações");
        System.out.println("   - Testes precisam cobrir todas as combinações");
        
        System.out.println("\n4. EXTENSIBILIDADE LIMITADA:");
        System.out.println("   - Adicionar nova operação = modificar todas as classes");
        System.out.println("   - Adicionar novo tipo de funcionário = implementar todas as operações");
        System.out.println("   - Não é possível reutilizar operações entre contextos");
        
        System.out.println("\n✅ O PADRÃO VISITOR RESOLVE TODOS ESSES PROBLEMAS!");
    }
}