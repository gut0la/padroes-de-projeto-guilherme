package comportamentais.templatemethod.compadrao;

import java.util.*;

/**
 * Demonstração do padrão Template Method
 * 
 * Esta classe mostra como o padrão Template Method resolve os problemas
 * da implementação sem padrão, proporcionando:
 * 
 * VANTAGENS:
 * - Eliminação de duplicação de código
 * - Estrutura comum para algoritmos similares
 * - Consistência na execução dos passos
 * - Facilidade para manter e estender
 * - Implementação do princípio DRY
 * - Reutilização de código comum
 * - Flexibilidade através de hooks
 */
public class ProcessamentoDadosComTemplate {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DO PADRÃO TEMPLATE METHOD ===");
        System.out.println("Processamento de Dados com Estrutura Comum\n");
        
        // Preparação dos dados de exemplo
        List<String> dadosVendas = Arrays.asList(
            "venda_001_produto_A", "venda_002_produto_B", "venda_003_produto_C",
            "venda_004_produto_A", "venda_005_produto_D"
        );
        
        List<String> dadosEstoque = Arrays.asList(
            "estoque_produto_A_50", "estoque_produto_B_30", "estoque_produto_C_5",
            "estoque_produto_D_75", "estoque_produto_E_2"
        );
        
        List<String> dadosClientes = Arrays.asList(
            "cliente_joao_silva", "cliente_maria_santos", "cliente_pedro_oliveira",
            "cliente_ana_costa", "cliente_carlos_ferreira"
        );
        
        // Demonstração 1: Processamento básico com Template Method
        System.out.println("\n🎯 === PROCESSAMENTO BÁSICO ===");
        
        // VANTAGEM: Mesma interface para todos os processadores
        ProcessadorDadosTemplate processadorVendas = new ProcessadorVendas();
        ProcessadorDadosTemplate processadorEstoque = new ProcessadorEstoque();
        ProcessadorDadosTemplate processadorClientes = new ProcessadorClientes();
        
        // VANTAGEM: Estrutura comum garantida pelo Template Method
        processadorVendas.processarDados(dadosVendas);
        processadorEstoque.processarDados(dadosEstoque);
        processadorClientes.processarDados(dadosClientes);
        
        // Demonstração 2: Processamento em lote
        System.out.println("\n📦 === PROCESSAMENTO EM LOTE ===");
        demonstrarProcessamentoLote();
        
        // Demonstração 3: Tratamento de erros consistente
        System.out.println("\n❌ === TRATAMENTO DE ERROS ===");
        demonstrarTratamentoErros();
        
        // Demonstração 4: Flexibilidade do padrão
        System.out.println("\n🔧 === FLEXIBILIDADE DO PADRÃO ===");
        demonstrarFlexibilidade();
        
        // Demonstração 5: Comparação de performance
        System.out.println("\n⚡ === COMPARAÇÃO DE PERFORMANCE ===");
        demonstrarPerformance();
        
        // Demonstração das vantagens
        System.out.println("\n✅ === VANTAGENS DEMONSTRADAS ===");
        System.out.println("1. ✓ Eliminação de duplicação de código");
        System.out.println("2. ✓ Estrutura comum garantida");
        System.out.println("3. ✓ Consistência na execução");
        System.out.println("4. ✓ Facilidade de manutenção");
        System.out.println("5. ✓ Princípio DRY implementado");
        System.out.println("6. ✓ Reutilização de código comum");
        System.out.println("7. ✓ Flexibilidade através de hooks");
        System.out.println("8. ✓ Fácil extensão com novos processadores");
        
        System.out.println("\n🎯 Padrão Template Method implementado com sucesso!");
        System.out.println("Compare com ProcessamentoDadosSemTemplate para ver as diferenças.");
    }
    
    /**
     * Demonstra processamento em lote usando polimorfismo
     */
    private static void demonstrarProcessamentoLote() {
        // VANTAGEM: Polimorfismo permite tratar todos os processadores igualmente
        List<ProcessadorDadosTemplate> processadores = Arrays.asList(
            new ProcessadorVendas(),
            new ProcessadorEstoque(),
            new ProcessadorClientes()
        );
        
        List<List<String>> conjuntosDados = Arrays.asList(
            Arrays.asList("venda_lote_001", "venda_lote_002"),
            Arrays.asList("estoque_lote_A_100", "estoque_lote_B_200"),
            Arrays.asList("cliente_lote_usuario1", "cliente_lote_usuario2")
        );
        
        System.out.println("Processando " + processadores.size() + " tipos de dados em lote...");
        
        for (int i = 0; i < processadores.size(); i++) {
            ProcessadorDadosTemplate processador = processadores.get(i);
            List<String> dados = conjuntosDados.get(i);
            
            System.out.println("\n--- Processamento " + (i + 1) + " ---");
            processador.processarDados(dados);
        }
    }
    
    /**
     * Demonstra tratamento consistente de erros
     */
    private static void demonstrarTratamentoErros() {
        ProcessadorDadosTemplate processador = new ProcessadorVendas();
        
        // Teste com dados nulos
        System.out.println("Testando com dados nulos:");
        processador.processarDados(null);
        
        // Teste com lista vazia
        System.out.println("Testando com lista vazia:");
        processador.processarDados(new ArrayList<>());
        
        // Teste com dados inválidos
        System.out.println("Testando com dados inválidos:");
        processador.processarDados(Arrays.asList("dados_invalidos", "sem_formato_correto"));
        
        System.out.println("✅ Tratamento de erros consistente em todos os casos!");
    }
    
    /**
     * Demonstra a flexibilidade do padrão através de extensão
     */
    private static void demonstrarFlexibilidade() {
        // Criação de um novo processador personalizado
        ProcessadorDadosTemplate processadorPersonalizado = new ProcessadorPersonalizado();
        
        List<String> dadosPersonalizados = Arrays.asList(
            "custom_data_001", "custom_data_002", "custom_data_003"
        );
        
        System.out.println("Demonstrando extensibilidade com processador personalizado:");
        processadorPersonalizado.processarDados(dadosPersonalizados);
    }
    
    /**
     * Demonstra comparação de performance (simulada)
     */
    private static void demonstrarPerformance() {
        List<String> dadosGrandes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            dadosGrandes.add("venda_" + i + "_produto_" + (i % 10));
        }
        
        ProcessadorDadosTemplate processador = new ProcessadorVendas();
        
        long inicio = System.currentTimeMillis();
        processador.processarDados(dadosGrandes);
        long fim = System.currentTimeMillis();
        
        System.out.println("⚡ Processamento de " + dadosGrandes.size() + " registros:");
        System.out.println("   Tempo: " + (fim - inicio) + "ms");
        System.out.println("   Estrutura otimizada pelo Template Method");
    }
    
    /**
     * Processador personalizado para demonstrar extensibilidade
     */
    private static class ProcessadorPersonalizado extends ProcessadorDadosTemplate {
        
        @Override
        protected String getTipoProcessamento() {
            return "PERSONALIZADO";
        }
        
        @Override
        protected List<String> carregarDados(List<String> dadosOriginais) {
            System.out.println("2. Carregando dados personalizados...");
            
            // VANTAGEM: Reutiliza método utilitário da classe pai
            List<String> dadosPersonalizados = filtrarDados(dadosOriginais, "custom");
            
            log(dadosPersonalizados.size() + " registros personalizados carregados");
            System.out.println("🔧 Dados personalizados preparados");
            
            return dadosPersonalizados;
        }
        
        @Override
        protected Object processarDadosEspecificos(List<String> dadosCarregados) {
            System.out.println("3. Processando dados personalizados...");
            
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("total", dadosCarregados.size());
            resultado.put("processedAt", new Date());
            resultado.put("type", "CUSTOM");
            
            for (String dado : dadosCarregados) {
                log("Processando: " + dado);
            }
            
            System.out.println("🔧 Processamento personalizado concluído");
            
            // VANTAGEM: Reutiliza método utilitário da classe pai
            mostrarEstatisticas(dadosCarregados, resultado);
            
            return resultado;
        }
        
        @Override
        protected void salvamentoAdicional(Object resultado) {
            System.out.println("💾 Salvamento personalizado executado");
            System.out.println("🔧 Dados enviados para sistema personalizado");
        }
        
        @Override
        protected void limpezaAdicional() {
            log("Limpeza personalizada executada");
        }
    }
    
    /**
     * Método utilitário para demonstrar uso avançado
     */
    public static void processarMultiplosTipos(List<ProcessadorDadosTemplate> processadores,
                                               Map<String, List<String>> dadosPorTipo) {
        System.out.println("\n🔄 === PROCESSAMENTO AVANÇADO ===");
        
        for (ProcessadorDadosTemplate processador : processadores) {
            String tipo = processador.getTipoProcessamento().toLowerCase();
            List<String> dados = dadosPorTipo.get(tipo);
            
            if (dados != null && !dados.isEmpty()) {
                System.out.println("\nProcessando " + tipo + " com " + dados.size() + " registros:");
                processador.processarDados(dados);
            } else {
                System.out.println("\n⚠️ Sem dados para processar: " + tipo);
            }
        }
    }
    
    /**
     * Método para benchmark de diferentes implementações
     */
    public static void compararImplementacoes() {
        System.out.println("\n📊 === COMPARAÇÃO DE IMPLEMENTAÇÕES ===");
        
        List<String> dadosTeste = Arrays.asList(
            "venda_benchmark_001", "venda_benchmark_002", "venda_benchmark_003"
        );
        
        ProcessadorDadosTemplate processadorTemplate = new ProcessadorVendas();
        
        // Medição com Template Method
        long inicioTemplate = System.currentTimeMillis();
        processadorTemplate.processarDados(dadosTeste);
        long fimTemplate = System.currentTimeMillis();
        
        System.out.println("\n📈 Resultados do Benchmark:");
        System.out.println("Template Method: " + (fimTemplate - inicioTemplate) + "ms");
        System.out.println("✅ Código organizado, reutilizável e manutenível");
        System.out.println("✅ Estrutura consistente garantida");
        System.out.println("✅ Fácil extensão e modificação");
    }
}