package comportamentais.templatemethod.compadrao;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Processador de Clientes - Implementação concreta do Template Method
 * 
 * Esta classe herda de ProcessadorDadosTemplate e implementa
 * os métodos específicos para processamento de dados de clientes.
 * 
 * VANTAGENS:
 * - Reutiliza toda a estrutura comum do template
 * - Foca apenas na lógica específica de clientes
 * - Mantém consistência com outros processadores
 * - Fácil de manter e testar
 */
public class ProcessadorClientes extends ProcessadorDadosTemplate {
    
    private static final String FILTRO_CLIENTES = "cliente";
    private Set<String> clientesVip = new HashSet<>();
    
    @Override
    protected String getTipoProcessamento() {
        return "CLIENTES";
    }
    
    @Override
    protected List<String> carregarDados(List<String> dadosOriginais) {
        System.out.println("2. Carregando dados de clientes...");
        
        // VANTAGEM: Reutiliza método utilitário da classe pai
        List<String> dadosClientes = filtrarDados(dadosOriginais, FILTRO_CLIENTES);
        
        log(dadosClientes.size() + " registros de clientes carregados");
        System.out.println("👥 Dados de clientes preparados para processamento");
        
        return dadosClientes;
    }
    
    @Override
    protected Object processarDadosEspecificos(List<String> dadosCarregados) {
        System.out.println("3. Processando clientes...");
        
        RelatorioClientes relatorio = new RelatorioClientes();
        
        for (String dadoCliente : dadosCarregados) {
            // Simula extração de dados do cliente
            String nomeCliente = extrairNomeCliente(dadoCliente);
            String categoriaCliente = determinarCategoriaCliente(nomeCliente);
            
            relatorio.adicionarCliente(nomeCliente, categoriaCliente);
            
            // Log detalhado
            log("Processado cliente: " + nomeCliente + " - Categoria: " + categoriaCliente);
            
            // Verifica se é cliente VIP
            if ("VIP".equals(categoriaCliente)) {
                clientesVip.add(nomeCliente);
                System.out.println("⭐ Cliente VIP identificado: " + nomeCliente);
            }
        }
        
        relatorio.calcularEstatisticas();
        
        System.out.println("👤 Total de clientes únicos: " + relatorio.getClientesUnicos());
        System.out.println("📊 Clientes por categoria:");
        relatorio.getClientesPorCategoria().forEach((categoria, quantidade) -> 
            System.out.println("   - " + categoria + ": " + quantidade + " clientes")
        );
        System.out.println("🏆 Categoria predominante: " + relatorio.getCategoriaPredominante());
        
        if (!clientesVip.isEmpty()) {
            System.out.println("⭐ Clientes VIP encontrados: " + clientesVip.size());
        }
        
        // VANTAGEM: Reutiliza método utilitário da classe pai
        mostrarEstatisticas(dadosCarregados, relatorio);
        
        return relatorio;
    }
    
    // HOOK METHODS - Implementações específicas opcionais
    
    @Override
    protected boolean validacaoAdicional(List<String> dados) {
        // Validação específica para dados de clientes
        Set<String> nomesEncontrados = new HashSet<>();
        
        for (String dado : dados) {
            if (dado.contains(FILTRO_CLIENTES)) {
                String nome = extrairNomeCliente(dado);
                
                // Verifica se o nome não está vazio
                if (nome.trim().isEmpty() || "CLIENTE_DESCONHECIDO".equals(nome)) {
                    System.out.println("❌ Dados de cliente sem nome válido: " + dado);
                    return false;
                }
                
                // Verifica duplicatas (opcional - pode ser permitido)
                if (nomesEncontrados.contains(nome)) {
                    log("⚠️ Cliente duplicado encontrado: " + nome);
                }
                nomesEncontrados.add(nome);
                
                // Verifica formato básico do nome
                if (nome.length() < 3) {
                    System.out.println("❌ Nome de cliente muito curto: " + nome);
                    return false;
                }
            }
        }
        
        log("Validação específica de clientes aprovada (" + nomesEncontrados.size() + " clientes únicos)");
        return true;
    }
    
    @Override
    protected void salvamentoAdicional(Object resultado) {
        if (resultado instanceof RelatorioClientes) {
            RelatorioClientes relatorio = (RelatorioClientes) resultado;
            
            // Salvamento adicional específico para clientes
            System.out.println("💾 Salvando base de dados de clientes...");
            System.out.println("💾 Atualizando sistema CRM...");
            
            // Salva dados de clientes VIP separadamente
            if (!clientesVip.isEmpty()) {
                System.out.println("⭐ Salvando lista de clientes VIP...");
                System.out.println("📧 Preparando campanhas personalizadas para VIPs...");
            }
            
            // Salva segmentação por categoria
            System.out.println("🎯 Salvando segmentação de clientes por categoria...");
            
            // Gera relatórios para marketing
            if (relatorio.getClientesUnicos() > 10) {
                System.out.println("📊 Gerando relatório para equipe de marketing...");
            }
        }
    }
    
    @Override
    protected void limpezaAdicional() {
        // Limpeza específica para processamento de clientes
        log("Limpando cache de clientes");
        log("Liberando conexões com sistema CRM");
        clientesVip.clear();
        log("Lista de clientes VIP limpa");
    }
    
    // MÉTODOS AUXILIARES ESPECÍFICOS
    
    private String extrairNomeCliente(String dadoCliente) {
        // Simula extração do nome do cliente
        // Formato esperado: "cliente_nome_sobrenome"
        String[] partes = dadoCliente.split("_");
        if (partes.length >= 3) {
            // Junta nome e sobrenome
            StringBuilder nomeCompleto = new StringBuilder();
            for (int i = 1; i < partes.length; i++) {
                if (i > 1) nomeCompleto.append(" ");
                nomeCompleto.append(capitalize(partes[i]));
            }
            return nomeCompleto.toString();
        }
        return "CLIENTE_DESCONHECIDO";
    }
    
    private String determinarCategoriaCliente(String nomeCliente) {
        // Simula lógica de categorização de clientes
        // Em um caso real, consultaria histórico de compras, etc.
        
        int hash = nomeCliente.hashCode();
        int categoria = Math.abs(hash) % 100;
        
        if (categoria < 10) {
            return "VIP";
        } else if (categoria < 30) {
            return "PREMIUM";
        } else if (categoria < 70) {
            return "REGULAR";
        } else {
            return "NOVO";
        }
    }
    
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    /**
     * Classe interna para encapsular dados do relatório de clientes
     */
    public static class RelatorioClientes {
        private Set<String> clientesUnicos = new HashSet<>();
        private Map<String, Integer> clientesPorCategoria = new HashMap<>();
        private Map<String, String> clienteCategoria = new HashMap<>();
        private List<String> todosClientes = new ArrayList<>();
        
        public void adicionarCliente(String nome, String categoria) {
            clientesUnicos.add(nome);
            todosClientes.add(nome);
            clienteCategoria.put(nome, categoria);
            clientesPorCategoria.put(categoria, 
                clientesPorCategoria.getOrDefault(categoria, 0) + 1);
        }
        
        public void calcularEstatisticas() {
            // Cálculos adicionais podem ser feitos aqui
        }
        
        public int getClientesUnicos() { 
            return clientesUnicos.size(); 
        }
        
        public int getTotalRegistros() {
            return todosClientes.size();
        }
        
        public Map<String, Integer> getClientesPorCategoria() {
            return new HashMap<>(clientesPorCategoria);
        }
        
        public String getCategoriaPredominante() {
            return clientesPorCategoria.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("NENHUMA");
        }
        
        public List<String> getClientesPorCategoria(String categoria) {
            return clienteCategoria.entrySet().stream()
                .filter(entry -> categoria.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        }
        
        public Set<String> getTodasCategorias() {
            return new HashSet<>(clientesPorCategoria.keySet());
        }
        
        public double getPercentualCategoria(String categoria) {
            int total = getTotalRegistros();
            int quantidade = clientesPorCategoria.getOrDefault(categoria, 0);
            return total > 0 ? (double) quantidade / total * 100 : 0;
        }
        
        public List<String> getClientesOrdenados() {
            return clientesUnicos.stream()
                .sorted()
                .collect(Collectors.toList());
        }
        
        @Override
        public String toString() {
            return "RelatorioClientes{" +
                    "clientesUnicos=" + clientesUnicos.size() +
                    ", totalRegistros=" + todosClientes.size() +
                    ", categorias=" + clientesPorCategoria.size() +
                    ", categoriaPredominante='" + getCategoriaPredominante() + '\'' +
                    '}';
        }
    }
}