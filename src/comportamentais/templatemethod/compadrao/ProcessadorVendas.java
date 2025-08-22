package comportamentais.templatemethod.compadrao;

import java.util.*;

/**
 * Processador de Vendas - Implementação concreta do Template Method
 * 
 * Esta classe herda de ProcessadorDadosTemplate e implementa
 * os métodos específicos para processamento de dados de vendas.
 * 
 * VANTAGENS:
 * - Reutiliza toda a estrutura comum do template
 * - Foca apenas na lógica específica de vendas
 * - Mantém consistência com outros processadores
 * - Fácil de manter e testar
 */
public class ProcessadorVendas extends ProcessadorDadosTemplate {
    
    private static final String FILTRO_VENDAS = "venda";
    private double limiteVendaAlta = 500.0;
    
    @Override
    protected String getTipoProcessamento() {
        return "VENDAS";
    }
    
    @Override
    protected List<String> carregarDados(List<String> dadosOriginais) {
        System.out.println("2. Carregando dados de vendas...");
        
        // VANTAGEM: Reutiliza método utilitário da classe pai
        List<String> dadosVendas = filtrarDados(dadosOriginais, FILTRO_VENDAS);
        
        log(dadosVendas.size() + " registros de vendas carregados");
        System.out.println("💰 Dados de vendas preparados para processamento");
        
        return dadosVendas;
    }
    
    @Override
    protected Object processarDadosEspecificos(List<String> dadosCarregados) {
        System.out.println("3. Processando vendas...");
        
        RelatorioVendas relatorio = new RelatorioVendas();
        
        for (String venda : dadosCarregados) {
            // Simula extração de dados da venda
            double valor = extrairValorVenda(venda);
            String produto = extrairProdutoVenda(venda);
            
            relatorio.adicionarVenda(produto, valor);
            
            // Log detalhado
            log("Processada venda: " + produto + " - R$ " + String.format("%.2f", valor));
        }
        
        relatorio.calcularEstatisticas();
        
        System.out.println("💰 Total de vendas: R$ " + String.format("%.2f", relatorio.getTotalVendas()));
        System.out.println("📊 Número de vendas: " + relatorio.getNumeroVendas());
        System.out.println("📈 Ticket médio: R$ " + String.format("%.2f", relatorio.getTicketMedio()));
        System.out.println("🏆 Produto mais vendido: " + relatorio.getProdutoMaisVendido());
        
        // VANTAGEM: Reutiliza método utilitário da classe pai
        mostrarEstatisticas(dadosCarregados, relatorio);
        
        return relatorio;
    }
    
    // HOOK METHODS - Implementações específicas opcionais
    
    @Override
    protected boolean validacaoAdicional(List<String> dados) {
        // Validação específica para dados de vendas
        for (String dado : dados) {
            if (dado.contains(FILTRO_VENDAS) && !dado.matches(".*\\d+.*")) {
                System.out.println("❌ Dados de venda sem informação numérica: " + dado);
                return false;
            }
        }
        
        log("Validação específica de vendas aprovada");
        return true;
    }
    
    @Override
    protected void salvamentoAdicional(Object resultado) {
        if (resultado instanceof RelatorioVendas) {
            RelatorioVendas relatorio = (RelatorioVendas) resultado;
            
            // Salvamento adicional específico para vendas
            System.out.println("💾 Salvando relatório detalhado de vendas...");
            System.out.println("💾 Salvando dados para dashboard de vendas...");
            
            // Verifica se há vendas de alto valor para relatório especial
            if (relatorio.getVendaMaxima() > limiteVendaAlta) {
                System.out.println("🎯 Salvando relatório de vendas de alto valor...");
            }
        }
    }
    
    @Override
    protected void limpezaAdicional() {
        // Limpeza específica para processamento de vendas
        log("Limpando cache de produtos");
        log("Liberando conexões com sistema de vendas");
    }
    
    // MÉTODOS AUXILIARES ESPECÍFICOS
    
    private double extrairValorVenda(String venda) {
        // Simula extração de valor da string de venda
        // Em um caso real, faria parsing dos dados
        return 100 + (Math.random() * 900); // Valor entre 100 e 1000
    }
    
    private String extrairProdutoVenda(String venda) {
        // Simula extração do nome do produto
        String[] partes = venda.split("_");
        return partes.length > 2 ? partes[2] : "PRODUTO_GENERICO";
    }
    
    /**
     * Classe interna para encapsular dados do relatório de vendas
     */
    public static class RelatorioVendas {
        private double totalVendas = 0;
        private int numeroVendas = 0;
        private double vendaMaxima = 0;
        private double vendaMinima = Double.MAX_VALUE;
        private Map<String, Integer> vendasPorProduto = new HashMap<>();
        private Map<String, Double> valorPorProduto = new HashMap<>();
        
        public void adicionarVenda(String produto, double valor) {
            totalVendas += valor;
            numeroVendas++;
            
            if (valor > vendaMaxima) vendaMaxima = valor;
            if (valor < vendaMinima) vendaMinima = valor;
            
            vendasPorProduto.put(produto, vendasPorProduto.getOrDefault(produto, 0) + 1);
            valorPorProduto.put(produto, valorPorProduto.getOrDefault(produto, 0.0) + valor);
        }
        
        public void calcularEstatisticas() {
            // Cálculos adicionais podem ser feitos aqui
        }
        
        public double getTotalVendas() { return totalVendas; }
        public int getNumeroVendas() { return numeroVendas; }
        public double getTicketMedio() { 
            return numeroVendas > 0 ? totalVendas / numeroVendas : 0; 
        }
        public double getVendaMaxima() { return vendaMaxima; }
        public double getVendaMinima() { return vendaMinima; }
        
        public String getProdutoMaisVendido() {
            return vendasPorProduto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("NENHUM");
        }
        
        @Override
        public String toString() {
            return "RelatorioVendas{" +
                    "total=" + String.format("%.2f", totalVendas) +
                    ", vendas=" + numeroVendas +
                    ", ticketMedio=" + String.format("%.2f", getTicketMedio()) +
                    '}';
        }
    }
}