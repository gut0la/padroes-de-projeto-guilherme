package comportamentais.visitor.compadrao;

/**
 * Classes de funcionários que implementam ElementoVisitavel
 * 
 * Vantagens desta abordagem:
 * - Classes focam apenas nos dados (Single Responsibility)
 * - Operações são delegadas para visitors especializados
 * - Fácil adição de novas operações sem modificar estas classes
 * - Separação clara entre estrutura de dados e algoritmos
 * - Reutilização de operações entre diferentes contextos
 */

// Classe base abstrata para funcionários
abstract class Funcionario implements ElementoVisitavel {
    protected String nome;
    protected double salarioBase;
    
    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }
    
    // Getters - apenas dados, sem lógica de negócio
    public String getNome() { return nome; }
    public double getSalarioBase() { return salarioBase; }
    
    // Método abstrato para aceitar visitors
    @Override
    public abstract void aceitar(FuncionarioVisitor visitor);
}

// Desenvolvedor - foca apenas nos dados específicos
class Desenvolvedor extends Funcionario {
    private int linhasCodigo;
    private int bugsCorrigidos;
    
    public Desenvolvedor(String nome, double salarioBase, int linhasCodigo, int bugsCorrigidos) {
        super(nome, salarioBase);
        this.linhasCodigo = linhasCodigo;
        this.bugsCorrigidos = bugsCorrigidos;
    }
    
    @Override
    public void aceitar(FuncionarioVisitor visitor) {
        // Double dispatch - delega para o método específico do visitor
        visitor.visitDesenvolvedor(this);
    }
    
    // Getters específicos - apenas acesso aos dados
    public int getLinhasCodigo() { return linhasCodigo; }
    public int getBugsCorrigidos() { return bugsCorrigidos; }
    
    @Override
    public String toString() {
        return String.format("Desenvolvedor{nome='%s', salarioBase=%.2f, linhasCodigo=%d, bugsCorrigidos=%d}",
                           nome, salarioBase, linhasCodigo, bugsCorrigidos);
    }
}

// Gerente - foca apenas nos dados específicos
class Gerente extends Funcionario {
    private int equipeSize;
    private int projetosConcluidos;
    
    public Gerente(String nome, double salarioBase, int equipeSize, int projetosConcluidos) {
        super(nome, salarioBase);
        this.equipeSize = equipeSize;
        this.projetosConcluidos = projetosConcluidos;
    }
    
    @Override
    public void aceitar(FuncionarioVisitor visitor) {
        // Double dispatch - delega para o método específico do visitor
        visitor.visitGerente(this);
    }
    
    // Getters específicos - apenas acesso aos dados
    public int getEquipeSize() { return equipeSize; }
    public int getProjetosConcluidos() { return projetosConcluidos; }
    
    @Override
    public String toString() {
        return String.format("Gerente{nome='%s', salarioBase=%.2f, equipeSize=%d, projetosConcluidos=%d}",
                           nome, salarioBase, equipeSize, projetosConcluidos);
    }
}

// Estagiário - foca apenas nos dados específicos
class Estagiario extends Funcionario {
    private int horasEstudo;
    private int tarefasConcluidas;
    
    public Estagiario(String nome, double salarioBase, int horasEstudo, int tarefasConcluidas) {
        super(nome, salarioBase);
        this.horasEstudo = horasEstudo;
        this.tarefasConcluidas = tarefasConcluidas;
    }
    
    @Override
    public void aceitar(FuncionarioVisitor visitor) {
        // Double dispatch - delega para o método específico do visitor
        visitor.visitEstagiario(this);
    }
    
    // Getters específicos - apenas acesso aos dados
    public int getHorasEstudo() { return horasEstudo; }
    public int getTarefasConcluidas() { return tarefasConcluidas; }
    
    @Override
    public String toString() {
        return String.format("Estagiario{nome='%s', salarioBase=%.2f, horasEstudo=%d, tarefasConcluidas=%d}",
                           nome, salarioBase, horasEstudo, tarefasConcluidas);
    }
}