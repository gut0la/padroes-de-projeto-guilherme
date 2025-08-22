package comportamentais.templatemethod.compadrao;

import java.util.*;

/**
 * Classe abstrata que define o Template Method
 * 
 * Esta classe implementa o padrão Template Method, definindo
 * o esqueleto do algoritmo de processamento de dados e permitindo
 * que as subclasses implementem passos específicos.
 * 
 * VANTAGENS:
 * - Define estrutura comum para algoritmos similares
 * - Elimina duplicação de código
 * - Garante consistência na execução dos passos
 * - Facilita manutenção e extensão
 * - Implementa o princípio DRY (Don't Repeat Yourself)
 * - Permite customização de passos específicos
 */
public abstract class ProcessadorDadosTemplate {
    
    /**
     * TEMPLATE METHOD - Define o esqueleto do algoritmo
     * 
     * Este método define a sequência de passos para processar dados.
     * É final para garantir que a estrutura não seja alterada pelas subclasses.
     * 
     * ESTRUTURA DO ALGORITMO:
     * 1. Validar dados de entrada
     * 2. Carregar dados específicos
     * 3. Processar dados (implementação específica)
     * 4. Salvar resultados
     * 5. Limpar recursos
     */
    public final void processarDados(List<String> dados) {
        System.out.println("=== INICIANDO PROCESSAMENTO: " + getTipoProcessamento() + " ===");
        
        // Passo 1: Validação (implementação comum)
        if (!validarDados(dados)) {
            System.out.println("❌ Processamento cancelado devido a dados inválidos\n");
            return;
        }
        
        // Passo 2: Carregamento (implementação específica)
        List<String> dadosCarregados = carregarDados(dados);
        
        // Passo 3: Processamento principal (implementação específica)
        Object resultado = processarDadosEspecificos(dadosCarregados);
        
        // Passo 4: Salvamento (implementação comum com hook)
        salvarResultados(resultado);
        
        // Passo 5: Limpeza (implementação comum com hook)
        limparRecursos(dadosCarregados);
        
        System.out.println("✅ Processamento de " + getTipoProcessamento() + " concluído\n");
    }
    
    // MÉTODOS CONCRETOS (implementação comum)
    
    /**
     * Validação comum para todos os tipos de dados
     * VANTAGEM: Lógica centralizada, evita duplicação
     */
    protected boolean validarDados(List<String> dados) {
        System.out.println("1. Validando dados de entrada...");
        
        if (dados == null) {
            System.out.println("❌ Dados são nulos!");
            return false;
        }
        
        if (dados.isEmpty()) {
            System.out.println("❌ Lista de dados está vazia!");
            return false;
        }
        
        // Validação adicional específica (hook method)
        if (!validacaoAdicional(dados)) {
            return false;
        }
        
        System.out.println("✅ Dados válidos (" + dados.size() + " registros)");
        return true;
    }
    
    /**
     * Salvamento comum para todos os tipos de resultado
     * VANTAGEM: Lógica centralizada, comportamento consistente
     */
    protected void salvarResultados(Object resultado) {
        System.out.println("4. Salvando resultados...");
        
        String nomeArquivo = getTipoProcessamento().toLowerCase() + "_processado.txt";
        System.out.println("💾 Dados salvos em: " + nomeArquivo);
        
        // Hook para salvamento adicional
        salvamentoAdicional(resultado);
    }
    
    /**
     * Limpeza comum de recursos
     * VANTAGEM: Garante liberação consistente de recursos
     */
    protected void limparRecursos(List<String> dadosCarregados) {
        System.out.println("5. Limpando recursos...");
        
        if (dadosCarregados != null) {
            dadosCarregados.clear();
        }
        
        // Hook para limpeza adicional
        limpezaAdicional();
        
        System.out.println("🧹 Recursos liberados");
    }
    
    // MÉTODOS ABSTRATOS (devem ser implementados pelas subclasses)
    
    /**
     * Retorna o tipo de processamento
     * Usado para logs e identificação
     */
    protected abstract String getTipoProcessamento();
    
    /**
     * Carrega dados específicos do tipo de processamento
     * Cada subclasse implementa sua lógica de carregamento
     */
    protected abstract List<String> carregarDados(List<String> dadosOriginais);
    
    /**
     * Processa os dados de forma específica
     * Núcleo da lógica específica de cada tipo
     */
    protected abstract Object processarDadosEspecificos(List<String> dadosCarregados);
    
    // HOOK METHODS (métodos opcionais que podem ser sobrescritos)
    
    /**
     * Hook para validação adicional específica
     * Subclasses podem sobrescrever para adicionar validações específicas
     */
    protected boolean validacaoAdicional(List<String> dados) {
        // Implementação padrão: sem validação adicional
        return true;
    }
    
    /**
     * Hook para salvamento adicional
     * Subclasses podem sobrescrever para adicionar salvamentos específicos
     */
    protected void salvamentoAdicional(Object resultado) {
        // Implementação padrão: sem salvamento adicional
    }
    
    /**
     * Hook para limpeza adicional
     * Subclasses podem sobrescrever para adicionar limpezas específicas
     */
    protected void limpezaAdicional() {
        // Implementação padrão: sem limpeza adicional
    }
    
    // MÉTODOS UTILITÁRIOS
    
    /**
     * Método utilitário para filtrar dados
     * VANTAGEM: Reutilização de código comum
     */
    protected List<String> filtrarDados(List<String> dados, String filtro) {
        List<String> dadosFiltrados = new ArrayList<>();
        for (String dado : dados) {
            if (dado.toLowerCase().contains(filtro.toLowerCase())) {
                dadosFiltrados.add(dado.toUpperCase());
            }
        }
        return dadosFiltrados;
    }
    
    /**
     * Método utilitário para logging
     * VANTAGEM: Logging consistente em todas as subclasses
     */
    protected void log(String mensagem) {
        System.out.println("[" + getTipoProcessamento() + "] " + mensagem);
    }
    
    /**
     * Método para obter estatísticas básicas
     * VANTAGEM: Funcionalidade comum disponível para todas as subclasses
     */
    protected void mostrarEstatisticas(List<String> dados, Object resultado) {
        System.out.println("📊 Estatísticas:");
        System.out.println("   - Registros processados: " + dados.size());
        System.out.println("   - Tipo de resultado: " + resultado.getClass().getSimpleName());
        System.out.println("   - Timestamp: " + new Date());
    }
}