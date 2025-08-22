package comportamentais.templatemethod.compadrao;

import java.util.*;

/**
 * Processador de Estoque - Implementação concreta do Template Method
 * 
 * Esta classe herda de ProcessadorDadosTemplate e implementa
 * os métodos específicos para processamento de dados de estoque.
 * 
 * VANTAGENS:
 * - Reutiliza toda a estrutura comum do template
 * - Foca apenas na lógica específica de estoque
 * - Mantém consistência com outros processadores
 * - Fácil de manter e testar
 */
public class ProcessadorEstoque extends ProcessadorDadosTemplate {
    
    private static final String FILTRO_ESTOQUE = "estoque";
    private int limiteEstoqueBaixo = 10;
    
    @Override
    protected String getTipoProcessamento() {
        return "ESTOQUE";
    }
    
    @Override
    protected List<String> carregarDados(List<String> dadosOriginais) {
        System.out.println("2. Carregando dados de estoque...");
        
        // VANTAGEM: Reutiliza método utilitário da classe pai
        List<String> dadosEstoque = filtrarDados(dadosOriginais, FILTRO_ESTOQUE);
        
        log(dadosEstoque.size() + " registros de estoque carregados");
        System.out.println("📦 Dados de estoque preparados para processamento");
        
        return dadosEstoque;
    }
    
    @Override
    protected Object processarDadosEspecificos(List<String> dadosCarregados) {
        System.out.println("3. Processando estoque...");
        
        RelatorioEstoque relatorio = new RelatorioEstoque();
        
        for (String itemEstoque : dadosCarregados) {
            // Simula extração de dados do estoque
            String produto = extrairProdutoEstoque(itemEstoque);
            int quantidade = extrairQuantidadeEstoque(itemEstoque);
            
            relatorio.adicionarItem(produto, quantidade);
            
            // Log detalhado
            log("Processado item: " + produto + " - Qtd: " + quantidade);
            
            // Verifica estoque baixo
            if (quantidade < limiteEstoqueBaixo) {
                relatorio.adicionarItemEstoqueBaixo(produto, quantidade);
                System.out.println("⚠️ ALERTA: Estoque baixo para " + produto + " (" + quantidade + " unidades)");
            }
        }
        
        relatorio.calcularEstatisticas();
        
        System.out.println("📊 Total de itens: " + relatorio.getTotalItens());
        System.out.println("📦 Produtos diferentes: " + relatorio.getNumeroProdutos());
        System.out.println("📈 Estoque médio por produto: " + String.format("%.1f", relatorio.getEstoqueMedio()));
        System.out.println("🏆 Produto com maior estoque: " + relatorio.getProdutoMaiorEstoque());
        
        if (relatorio.getItensEstoqueBaixo().size() > 0) {
            System.out.println("⚠️ Itens com estoque baixo: " + relatorio.getItensEstoqueBaixo().size());
        }
        
        // VANTAGEM: Reutiliza método utilitário da classe pai
        mostrarEstatisticas(dadosCarregados, relatorio);
        
        return relatorio;
    }
    
    // HOOK METHODS - Implementações específicas opcionais
    
    @Override
    protected boolean validacaoAdicional(List<String> dados) {
        // Validação específica para dados de estoque
        for (String dado : dados) {
            if (dado.contains(FILTRO_ESTOQUE)) {
                // Verifica se contém informação de quantidade
                if (!dado.matches(".*\\d+.*")) {
                    System.out.println("❌ Dados de estoque sem informação de quantidade: " + dado);
                    return false;
                }
                
                // Verifica formato básico
                String[] partes = dado.split("_");
                if (partes.length < 3) {
                    System.out.println("❌ Formato inválido para dados de estoque: " + dado);
                    return false;
                }
            }
        }
        
        log("Validação específica de estoque aprovada");
        return true;
    }
    
    @Override
    protected void salvamentoAdicional(Object resultado) {
        if (resultado instanceof RelatorioEstoque) {
            RelatorioEstoque relatorio = (RelatorioEstoque) resultado;
            
            // Salvamento adicional específico para estoque
            System.out.println("💾 Salvando relatório de inventário...");
            System.out.println("💾 Atualizando sistema de controle de estoque...");
            
            // Salva alertas de estoque baixo se houver
            if (!relatorio.getItensEstoqueBaixo().isEmpty()) {
                System.out.println("🚨 Salvando alertas de estoque baixo...");
                System.out.println("📧 Enviando notificações para equipe de compras...");
            }
            
            // Salva dados para reposição automática
            System.out.println("🔄 Atualizando sistema de reposição automática...");
        }
    }
    
    @Override
    protected void limpezaAdicional() {
        // Limpeza específica para processamento de estoque
        log("Limpando cache de produtos em estoque");
        log("Liberando conexões com sistema de inventário");
        log("Resetando contadores temporários");
    }
    
    // MÉTODOS AUXILIARES ESPECÍFICOS
    
    private String extrairProdutoEstoque(String itemEstoque) {
        // Simula extração do nome do produto
        // Formato esperado: "estoque_produto_X_quantidade"
        String[] partes = itemEstoque.split("_");
        if (partes.length >= 3) {
            return partes[1] + "_" + partes[2]; // produto_X
        }
        return "PRODUTO_DESCONHECIDO";
    }
    
    private int extrairQuantidadeEstoque(String itemEstoque) {
        // Simula extração da quantidade
        // Em um caso real, faria parsing dos dados
        String[] partes = itemEstoque.split("_");
        if (partes.length >= 4) {
            try {
                return Integer.parseInt(partes[3]);
            } catch (NumberFormatException e) {
                // Se não conseguir extrair, gera quantidade aleatória
                return (int)(Math.random() * 100) + 1;
            }
        }
        return (int)(Math.random() * 100) + 1; // Quantidade entre 1 e 100
    }
    
    /**
     * Classe interna para encapsular dados do relatório de estoque
     */
    public static class RelatorioEstoque {
        private int totalItens = 0;
        private Map<String, Integer> estoquePorProduto = new HashMap<>();
        private Map<String, Integer> itensEstoqueBaixo = new HashMap<>();
        private int maiorEstoque = 0;
        private String produtoMaiorEstoque = "";
        
        public void adicionarItem(String produto, int quantidade) {
            totalItens += quantidade;
            estoquePorProduto.put(produto, quantidade);
            
            if (quantidade > maiorEstoque) {
                maiorEstoque = quantidade;
                produtoMaiorEstoque = produto;
            }
        }
        
        public void adicionarItemEstoqueBaixo(String produto, int quantidade) {
            itensEstoqueBaixo.put(produto, quantidade);
        }
        
        public void calcularEstatisticas() {
            // Cálculos adicionais podem ser feitos aqui
        }
        
        public int getTotalItens() { return totalItens; }
        
        public int getNumeroProdutos() { return estoquePorProduto.size(); }
        
        public double getEstoqueMedio() {
            return estoquePorProduto.size() > 0 ? 
                (double) totalItens / estoquePorProduto.size() : 0;
        }
        
        public String getProdutoMaiorEstoque() { 
            return produtoMaiorEstoque.isEmpty() ? "NENHUM" : produtoMaiorEstoque; 
        }
        
        public Map<String, Integer> getItensEstoqueBaixo() { 
            return new HashMap<>(itensEstoqueBaixo); 
        }
        
        public Map<String, Integer> getEstoquePorProduto() {
            return new HashMap<>(estoquePorProduto);
        }
        
        public List<String> getProdutosOrdenadosPorEstoque() {
            return estoquePorProduto.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(java.util.stream.Collectors.toList());
        }
        
        @Override
        public String toString() {
            return "RelatorioEstoque{" +
                    "totalItens=" + totalItens +
                    ", produtos=" + estoquePorProduto.size() +
                    ", estoqueMedio=" + String.format("%.1f", getEstoqueMedio()) +
                    ", alertas=" + itensEstoqueBaixo.size() +
                    '}';
        }
    }
}