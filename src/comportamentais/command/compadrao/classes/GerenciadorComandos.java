package comportamentais.command.compadrao.classes;

import comportamentais.command.compadrao.interfaces.Command;
import java.util.*;
import java.util.concurrent.*;

/**
 * Gerenciador de Comandos - INVOKER no padrão Command
 * 
 * PADRÃO COMMAND - INVOKER:
 * O Invoker é responsável por:
 * - Executar comandos sem conhecer sua implementação
 * - Manter histórico de comandos executados
 * - Implementar funcionalidades de undo/redo
 * - Gerenciar filas de comandos
 * - Executar comandos compostos e assíncronos
 * 
 * BENEFÍCIOS:
 * - Desacoplamento entre quem solicita e quem executa
 * - Histórico completo de operações
 * - Funcionalidade de undo/redo robusta
 * - Suporte a macros e batch operations
 * - Execução assíncrona quando necessário
 */
public class GerenciadorComandos {
    
    // Histórico de comandos executados
    private final Stack<Command> historicoComandos;
    
    // Pilha de comandos desfeitos (para redo)
    private final Stack<Command> pilhaRedo;
    
    // Fila de comandos pendentes
    private final Queue<Command> filaPendentes;
    
    // Executor para comandos assíncronos
    private final ExecutorService executorAssincrono;
    
    // Configurações
    private int tamanhoMaximoHistorico;
    private boolean modoDebug;
    
    // Estatísticas
    private int totalComandosExecutados;
    private int totalComandosDesfeitos;
    private int totalComandosRefeitos;
    
    /**
     * Construtor do gerenciador
     */
    public GerenciadorComandos() {
        this.historicoComandos = new Stack<>();
        this.pilhaRedo = new Stack<>();
        this.filaPendentes = new LinkedList<>();
        this.executorAssincrono = Executors.newFixedThreadPool(3);
        this.tamanhoMaximoHistorico = 50;
        this.modoDebug = false;
        this.totalComandosExecutados = 0;
        this.totalComandosDesfeitos = 0;
        this.totalComandosRefeitos = 0;
    }
    
    /**
     * Executa um comando imediatamente
     * 
     * @param comando Comando a ser executado
     * @return true se executado com sucesso
     */
    public boolean executarComando(Command comando) {
        if (comando == null) {
            System.out.println("ERRO: Comando inválido (null)!");
            return false;
        }
        
        if (modoDebug) {
            System.out.println("\n🔍 [DEBUG] Executando: " + comando.getDescricao());
        }
        
        try {
            boolean sucesso = comando.executar();
            
            if (sucesso) {
                // Adiciona ao histórico
                adicionarAoHistorico(comando);
                
                // Limpa pilha de redo (nova ação invalida redos)
                pilhaRedo.clear();
                
                totalComandosExecutados++;
                
                if (modoDebug) {
                    System.out.println("DEBUG: Comando executado com sucesso!");
                }
            } else {
                if (modoDebug) {
                    System.out.println("DEBUG: Falha na execução do comando!");
                }
            }
            
            return sucesso;
            
        } catch (Exception e) {
            System.out.println("ERRO na execução do comando: " + e.getMessage());
            if (modoDebug) {
                e.printStackTrace();
            }
            return false;
        }
    }
    
    /**
     * Adiciona comando à fila para execução posterior
     * 
     * @param comando Comando a ser enfileirado
     */
    public void enfileirarComando(Command comando) {
        if (comando == null) {
            System.out.println("ERRO: Não é possível enfileirar comando inválido!");
            return;
        }
        
        filaPendentes.offer(comando);
        System.out.println("Comando enfileirado: " + comando.getDescricao());
        
        if (modoDebug) {
            System.out.println("🔍 [DEBUG] Fila tem " + filaPendentes.size() + " comandos pendentes");
        }
    }
    
    /**
     * Executa todos os comandos na fila
     * 
     * @return número de comandos executados com sucesso
     */
    public int executarFilaPendentes() {
        if (filaPendentes.isEmpty()) {
            System.out.println("INFO: Nenhum comando pendente na fila.");
            return 0;
        }
        
        System.out.println("\nExecutando " + filaPendentes.size() + " comandos da fila...");
        
        int sucessos = 0;
        int total = filaPendentes.size();
        
        while (!filaPendentes.isEmpty()) {
            Command comando = filaPendentes.poll();
            
            if (executarComando(comando)) {
                sucessos++;
            }
        }
        
        System.out.println("SUCESSO: Execução da fila concluída: " + sucessos + "/" + total + " sucessos");
        return sucessos;
    }
    
    /**
     * Desfaz o último comando executado
     * 
     * @return true se desfeito com sucesso
     */
    public boolean desfazerUltimoComando() {
        if (historicoComandos.isEmpty()) {
            System.out.println("INFO: Nenhum comando para desfazer.");
            return false;
        }
        
        Command ultimoComando = historicoComandos.peek();
        
        if (!ultimoComando.podeSerDesfeito()) {
            System.out.println("ERRO: O último comando não pode ser desfeito: " + ultimoComando.getDescricao());
            return false;
        }
        
        if (modoDebug) {
            System.out.println("\n🔍 [DEBUG] Desfazendo: " + ultimoComando.getDescricao());
        }
        
        try {
            boolean sucesso = ultimoComando.desfazer();
            
            if (sucesso) {
                // Move comando do histórico para pilha de redo
                Command comando = historicoComandos.pop();
                pilhaRedo.push(comando);
                
                totalComandosDesfeitos++;
                
                if (modoDebug) {
                    System.out.println("DEBUG: Comando desfeito com sucesso!");
                }
            }
            
            return sucesso;
            
        } catch (Exception e) {
            System.out.println("ERRO ao desfazer comando: " + e.getMessage());
            if (modoDebug) {
                e.printStackTrace();
            }
            return false;
        }
    }
    
    /**
     * Refaz o último comando desfeito
     * 
     * @return true se refeito com sucesso
     */
    public boolean refazerUltimoComando() {
        if (pilhaRedo.isEmpty()) {
            System.out.println("INFO: Nenhum comando para refazer.");
            return false;
        }
        
        Command comando = pilhaRedo.pop();
        
        if (modoDebug) {
            System.out.println("\n🔍 [DEBUG] Refazendo: " + comando.getDescricao());
        }
        
        try {
            boolean sucesso = comando.executar();
            
            if (sucesso) {
                // Move comando de volta para o histórico
                adicionarAoHistorico(comando);
                totalComandosRefeitos++;
                
                if (modoDebug) {
                    System.out.println("DEBUG: Comando refeito com sucesso!");
                }
            } else {
                // Se falhou, coloca de volta na pilha de redo
                pilhaRedo.push(comando);
            }
            
            return sucesso;
            
        } catch (Exception e) {
            System.out.println("ERRO ao refazer comando: " + e.getMessage());
            // Coloca de volta na pilha de redo
            pilhaRedo.push(comando);
            if (modoDebug) {
                e.printStackTrace();
            }
            return false;
        }
    }
    
    /**
     * Executa comando de forma assíncrona
     * 
     * @param comando Comando a ser executado
     * @return Future para acompanhar execução
     */
    public Future<Boolean> executarComandoAssincrono(Command comando) {
        if (comando == null) {
            return CompletableFuture.completedFuture(false);
        }
        
        System.out.println("Executando comando assíncrono: " + comando.getDescricao());
        
        return executorAssincrono.submit(() -> {
            try {
                boolean sucesso = comando.executar();
                
                if (sucesso) {
                    // Sincroniza acesso ao histórico
                    synchronized (this) {
                        adicionarAoHistorico(comando);
                        pilhaRedo.clear();
                        totalComandosExecutados++;
                    }
                    
                    System.out.println("SUCESSO: Comando assíncrono concluído: " + comando.getDescricao());
                } else {
                    System.out.println("ERRO: Falha no comando assíncrono: " + comando.getDescricao());
                }
                
                return sucesso;
                
            } catch (Exception e) {
                System.out.println("ERRO no comando assíncrono: " + e.getMessage());
                return false;
            }
        });
    }
    
    /**
     * Executa múltiplos comandos em sequência (macro)
     * 
     * @param comandos Lista de comandos
     * @return número de comandos executados com sucesso
     */
    public int executarMacro(List<Command> comandos) {
        if (comandos == null || comandos.isEmpty()) {
            System.out.println("INFO: Macro vazio ou inválido.");
            return 0;
        }
        
        System.out.println("\nExecutando macro com " + comandos.size() + " comandos...");
        
        int sucessos = 0;
        
        for (int i = 0; i < comandos.size(); i++) {
            Command comando = comandos.get(i);
            System.out.println("\nComando " + (i + 1) + "/" + comandos.size() + ":");
            
            if (executarComando(comando)) {
                sucessos++;
            } else {
                System.out.println("AVISO: Macro interrompido devido a falha no comando " + (i + 1));
                break;
            }
        }
        
        System.out.println("\n🏁 Macro concluído: " + sucessos + "/" + comandos.size() + " sucessos");
        return sucessos;
    }
    
    /**
     * Adiciona comando ao histórico, respeitando limite
     */
    private void adicionarAoHistorico(Command comando) {
        historicoComandos.push(comando);
        
        // Remove comandos antigos se exceder limite
        while (historicoComandos.size() > tamanhoMaximoHistorico) {
            // Remove do fundo da pilha (mais antigo)
            Stack<Command> temp = new Stack<>();
            while (historicoComandos.size() > 1) {
                temp.push(historicoComandos.pop());
            }
            historicoComandos.pop(); // Remove o mais antigo
            while (!temp.isEmpty()) {
                historicoComandos.push(temp.pop());
            }
        }
    }
    
    /**
     * Limpa todo o histórico
     */
    public void limparHistorico() {
        historicoComandos.clear();
        pilhaRedo.clear();
        System.out.println("🧹 Histórico de comandos limpo.");
    }
    
    /**
     * Exibe histórico de comandos
     */
    public void exibirHistorico() {
        System.out.println("\nHISTÓRICO DE COMANDOS:");
        System.out.println("═══════════════════════════════════════");
        
        if (historicoComandos.isEmpty()) {
            System.out.println("   (Nenhum comando executado)");
        } else {
            Stack<Command> temp = new Stack<>();
            int contador = historicoComandos.size();
            
            // Mostra do mais recente para o mais antigo
            while (!historicoComandos.isEmpty()) {
                Command cmd = historicoComandos.pop();
                temp.push(cmd);
                
                String status = cmd.podeSerDesfeito() ? "[REVERSÍVEL]" : "[IRREVERSÍVEL]";
                System.out.printf("%2d. %s %s\n", contador--, cmd.getDescricao(), status);
                System.out.println("    Timestamp: " + new Date(cmd.getTimestamp()));
            }
            
            // Restaura histórico
            while (!temp.isEmpty()) {
                historicoComandos.push(temp.pop());
            }
        }
        
        if (!pilhaRedo.isEmpty()) {
            System.out.println("\nCOMANDOS DISPONÍVEIS PARA REDO: " + pilhaRedo.size());
        }
        
        if (!filaPendentes.isEmpty()) {
            System.out.println("COMANDOS NA FILA: " + filaPendentes.size());
        }
    }
    
    /**
     * Exibe estatísticas do gerenciador
     */
    public void exibirEstatisticas() {
        System.out.println("\nESTATÍSTICAS DO GERENCIADOR:");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Total de comandos executados: " + totalComandosExecutados);
        System.out.println("Total de comandos desfeitos: " + totalComandosDesfeitos);
        System.out.println("Total de comandos refeitos: " + totalComandosRefeitos);
        System.out.println("Comandos no histórico: " + historicoComandos.size());
        System.out.println("Comandos disponíveis para redo: " + pilhaRedo.size());
        System.out.println("Comandos na fila: " + filaPendentes.size());
         System.out.println("Tamanho máximo do histórico: " + tamanhoMaximoHistorico);
         System.out.println("Modo debug: " + (modoDebug ? "ATIVO" : "INATIVO"));
    }
    
    // Getters e Setters
    public int getTamanhoMaximoHistorico() {
        return tamanhoMaximoHistorico;
    }
    
    public void setTamanhoMaximoHistorico(int tamanho) {
        if (tamanho > 0) {
            this.tamanhoMaximoHistorico = tamanho;
            System.out.println("📏 Tamanho máximo do histórico alterado para: " + tamanho);
        }
    }
    
    public boolean isModoDebug() {
        return modoDebug;
    }
    
    public void setModoDebug(boolean modoDebug) {
        this.modoDebug = modoDebug;
        System.out.println("Modo debug: " + (modoDebug ? "ATIVADO" : "DESATIVADO"));
    }
    
    public int getTotalComandosExecutados() {
        return totalComandosExecutados;
    }
    
    public int getTamanhoHistorico() {
        return historicoComandos.size();
    }
    
    public int getTamanhoFilaPendentes() {
        return filaPendentes.size();
    }
    
    public boolean temComandosParaDesfazer() {
        return !historicoComandos.isEmpty() && 
               historicoComandos.peek().podeSerDesfeito();
    }
    
    public boolean temComandosParaRefazer() {
        return !pilhaRedo.isEmpty();
    }
    
    /**
     * Finaliza o gerenciador e libera recursos
     */
    public void finalizar() {
        System.out.println("\n🔚 Finalizando gerenciador de comandos...");
        
        // Para executor assíncrono
        executorAssincrono.shutdown();
        try {
            if (!executorAssincrono.awaitTermination(5, TimeUnit.SECONDS)) {
                executorAssincrono.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorAssincrono.shutdownNow();
        }
        
        // Limpa estruturas
        limparHistorico();
        filaPendentes.clear();
        
        System.out.println("SUCESSO: Gerenciador finalizado com sucesso!");
    }
}