package comportamentais.templatemethod.sempadrao;

import java.util.*;

/**
 * Implementação SEM o padrão Template Method
 * 
 * Esta implementação demonstra os problemas que surgem quando
 * não utilizamos o padrão Template Method:
 * 
 * PROBLEMAS IDENTIFICADOS:
 * 1. Duplicação de código entre diferentes tipos de processamento
 * 2. Falta de estrutura comum para algoritmos similares
 * 3. Dificuldade para manter consistência entre implementações
 * 4. Violação do princípio DRY (Don't Repeat Yourself)
 * 5. Dificuldade para adicionar novos tipos de processamento
 * 6. Lógica comum espalhada em múltiplos lugares
 */
public class ProcessamentoDadosSemTemplate {
    
    /**
     * Processamento de dados de vendas
     * PROBLEMA: Lógica duplicada com outros tipos de processamento
     */
    public static class ProcessadorVendas {
        
        public void processarDadosVendas(List<String> dados) {
            System.out.println("=== PROCESSAMENTO DE VENDAS ===");
            
            // PROBLEMA: Validação duplicada em cada processador
            System.out.println("1. Validando dados de entrada...");
            if (dados == null || dados.isEmpty()) {
                System.out.println("❌ Dados inválidos!");
                return;
            }
            System.out.println("✅ Dados válidos");
            
            // PROBLEMA: Lógica de carregamento duplicada
            System.out.println("2. Carregando dados de vendas...");
            List<String> dadosCarregados = new ArrayList<>();
            for (String dado : dados) {
                if (dado.contains("venda")) {
                    dadosCarregados.add(dado.toUpperCase());
                }
            }
            System.out.println("📊 " + dadosCarregados.size() + " registros de vendas carregados");
            
            // Processamento específico de vendas
            System.out.println("3. Processando vendas...");
            double totalVendas = 0;
            for (String venda : dadosCarregados) {
                // Simula extração de valor da venda
                totalVendas += Math.random() * 1000;
            }
            System.out.println("💰 Total de vendas: R$ " + String.format("%.2f", totalVendas));
            
            // PROBLEMA: Lógica de salvamento duplicada
            System.out.println("4. Salvando resultados de vendas...");
            System.out.println("💾 Dados salvos em: vendas_processadas.txt");
            
            // PROBLEMA: Lógica de limpeza duplicada
            System.out.println("5. Limpando recursos de vendas...");
            dadosCarregados.clear();
            System.out.println("🧹 Recursos liberados");
            
            System.out.println("✅ Processamento de vendas concluído\n");
        }
    }
    
    /**
     * Processamento de dados de estoque
     * PROBLEMA: Código muito similar ao processamento de vendas
     */
    public static class ProcessadorEstoque {
        
        public void processarDadosEstoque(List<String> dados) {
            System.out.println("=== PROCESSAMENTO DE ESTOQUE ===");
            
            // PROBLEMA: Validação DUPLICADA - mesma lógica do ProcessadorVendas
            System.out.println("1. Validando dados de entrada...");
            if (dados == null || dados.isEmpty()) {
                System.out.println("❌ Dados inválidos!");
                return;
            }
            System.out.println("✅ Dados válidos");
            
            // PROBLEMA: Lógica de carregamento DUPLICADA
            System.out.println("2. Carregando dados de estoque...");
            List<String> dadosCarregados = new ArrayList<>();
            for (String dado : dados) {
                if (dado.contains("estoque")) {
                    dadosCarregados.add(dado.toUpperCase());
                }
            }
            System.out.println("📦 " + dadosCarregados.size() + " registros de estoque carregados");
            
            // Processamento específico de estoque (única parte diferente)
            System.out.println("3. Processando estoque...");
            int totalItens = 0;
            for (String item : dadosCarregados) {
                // Simula contagem de itens
                totalItens += (int)(Math.random() * 100);
            }
            System.out.println("📊 Total de itens em estoque: " + totalItens);
            
            // PROBLEMA: Lógica de salvamento DUPLICADA
            System.out.println("4. Salvando resultados de estoque...");
            System.out.println("💾 Dados salvos em: estoque_processado.txt");
            
            // PROBLEMA: Lógica de limpeza DUPLICADA
            System.out.println("5. Limpando recursos de estoque...");
            dadosCarregados.clear();
            System.out.println("🧹 Recursos liberados");
            
            System.out.println("✅ Processamento de estoque concluído\n");
        }
    }
    
    /**
     * Processamento de dados de clientes
     * PROBLEMA: Mais duplicação de código
     */
    public static class ProcessadorClientes {
        
        public void processarDadosClientes(List<String> dados) {
            System.out.println("=== PROCESSAMENTO DE CLIENTES ===");
            
            // PROBLEMA: Validação TRIPLICADA - mesma lógica dos outros processadores
            System.out.println("1. Validando dados de entrada...");
            if (dados == null || dados.isEmpty()) {
                System.out.println("❌ Dados inválidos!");
                return;
            }
            System.out.println("✅ Dados válidos");
            
            // PROBLEMA: Lógica de carregamento TRIPLICADA
            System.out.println("2. Carregando dados de clientes...");
            List<String> dadosCarregados = new ArrayList<>();
            for (String dado : dados) {
                if (dado.contains("cliente")) {
                    dadosCarregados.add(dado.toUpperCase());
                }
            }
            System.out.println("👥 " + dadosCarregados.size() + " registros de clientes carregados");
            
            // Processamento específico de clientes (única parte diferente)
            System.out.println("3. Processando clientes...");
            Set<String> clientesUnicos = new HashSet<>();
            for (String cliente : dadosCarregados) {
                // Simula extração de ID do cliente
                clientesUnicos.add("CLIENTE_" + (int)(Math.random() * 1000));
            }
            System.out.println("👤 Clientes únicos processados: " + clientesUnicos.size());
            
            // PROBLEMA: Lógica de salvamento TRIPLICADA
            System.out.println("4. Salvando resultados de clientes...");
            System.out.println("💾 Dados salvos em: clientes_processados.txt");
            
            // PROBLEMA: Lógica de limpeza TRIPLICADA
            System.out.println("5. Limpando recursos de clientes...");
            dadosCarregados.clear();
            clientesUnicos.clear();
            System.out.println("🧹 Recursos liberados");
            
            System.out.println("✅ Processamento de clientes concluído\n");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO SEM TEMPLATE METHOD ===");
        System.out.println("Problemas: Duplicação de código e falta de estrutura\n");
        
        // Dados de exemplo
        List<String> dadosVendas = Arrays.asList(
            "venda_001_produto_A", "venda_002_produto_B", "venda_003_produto_C"
        );
        
        List<String> dadosEstoque = Arrays.asList(
            "estoque_produto_A_50", "estoque_produto_B_30", "estoque_produto_C_20"
        );
        
        List<String> dadosClientes = Arrays.asList(
            "cliente_joao_silva", "cliente_maria_santos", "cliente_pedro_oliveira"
        );
        
        // PROBLEMA: Cada processador tem sua própria implementação
        // com código duplicado
        ProcessadorVendas processadorVendas = new ProcessadorVendas();
        processadorVendas.processarDadosVendas(dadosVendas);
        
        ProcessadorEstoque processadorEstoque = new ProcessadorEstoque();
        processadorEstoque.processarDadosEstoque(dadosEstoque);
        
        ProcessadorClientes processadorClientes = new ProcessadorClientes();
        processadorClientes.processarDadosClientes(dadosClientes);
        
        // Demonstração dos problemas
        System.out.println("❌ === PROBLEMAS IDENTIFICADOS ===");
        System.out.println("1. 🔄 Código duplicado em validação, carregamento, salvamento e limpeza");
        System.out.println("2. 🏗️ Falta de estrutura comum para algoritmos similares");
        System.out.println("3. 🔧 Dificuldade para manter consistência entre implementações");
        System.out.println("4. 📝 Violação do princípio DRY (Don't Repeat Yourself)");
        System.out.println("5. ➕ Dificuldade para adicionar novos tipos de processamento");
        System.out.println("6. 🌐 Lógica comum espalhada em múltiplos lugares");
        System.out.println("7. 🧪 Dificuldade para testar partes comuns isoladamente");
        System.out.println("8. 🔄 Mudanças na lógica comum requerem alterações em múltiplas classes");
        
        System.out.println("\n💡 Solução: Usar o padrão Template Method!");
        System.out.println("Veja ProcessamentoDadosComTemplate para a implementação correta.");
    }
}